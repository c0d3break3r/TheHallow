package pugz.hallows.common.tileentity.container;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.*;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import pugz.hallows.common.item.crafting.HallowingRecipe;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.HallowsItems;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;
import java.util.*;

public class AnointingTableContainer extends AbstractRepairContainer {
    private final World world;
    private HallowingRecipe recipe;
    private final List<HallowingRecipe> recipes;
    private final PlayerEntity player;

    public AnointingTableContainer(int id, PlayerInventory inventory) {
        this(id, inventory, IWorldPosCallable.NULL);
    }

    public AnointingTableContainer(int id, PlayerInventory inventory, IWorldPosCallable pos) {
        super(HallowsContainers.ANOINTING.get(), id, inventory, pos);
        this.world = inventory.player.level;
        this.player = inventory.player;
        this.recipes = this.world.getRecipeManager().getAllRecipesFor(HallowsRecipes.Recipes.HALLOWING);
        //this.addSlot(new Slot(this.field_234643_d_, 0, 76, 67));
    }

    protected boolean isValidBlock(BlockState state) {
        return state.is(HallowsBlocks.ANOINTMENT_TABLE.get());
    }

    protected boolean mayPickup(PlayerEntity player, boolean p_230303_2_) {
        return this.recipe != null && this.recipe.matches(this.inputSlots, this.world);
    }

    @Nonnull
    protected ItemStack onTake(PlayerEntity player, ItemStack stack) {
        stack.onCraftedBy(player.level, player, stack.getCount());
        this.resultSlots.awardUsedRecipes(player);
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((level, pos) -> {
            level.levelEvent(1044, pos, 0);
        });
        return stack;
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemstack = this.inputSlots.getItem(index);
        itemstack.shrink(1);
        this.inputSlots.setItem(index, itemstack);
    }

    public void createResult() {
        List<HallowingRecipe> list = this.world.getRecipeManager().getRecipesFor(HallowsRecipes.Recipes.HALLOWING, this.inputSlots, this.world);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            Attribute attribute = this.recipe.getAttribute();
            if (attribute == Attributes.FLYING_SPEED ||
                    attribute == Attributes.SPAWN_REINFORCEMENTS_CHANCE ||
                    attribute == Attributes.JUMP_STRENGTH ||
                    attribute == Attributes.FOLLOW_RANGE) return;

            ItemStack itemstack = this.recipe.getResultItem();
            if (itemstack.getOrCreateTag().getBoolean("Anointed")) return;

            EquipmentSlotType slot = MobEntity.getEquipmentSlotForItem(itemstack);
            Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
            double amount = 0;

            for (Map.Entry<Attribute, AttributeModifier> entry : itemstack.getAttributeModifiers(slot).entries()) {
                if (entry.getKey().getDescriptionId().equals(attribute.getDescriptionId())) {
                    amount = entry.getValue().getAmount();
                    amount += player.getAttributeBaseValue(attribute);
                } else {
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Anointment bonus", entry.getValue().getAmount() + player.getAttributeValue(entry.getKey()), AttributeModifier.Operation.ADDITION);
                    multimap.put(entry.getKey(), modifier);
                }
            }

            switch (attribute.getDescriptionId()) {
                default:
                case "attribute.name.generic.armor":
                case "attribute.name.generic.max_health":
                case "attribute.name.generic.armor_toughness":
                    amount += 1.0D;
                    break;
                case "attribute.name.generic.knockback_resistance":
                case "forge.swimSpeed":
                case "attribute.name.generic.movement_speed":
                    amount += 0.05D;
                    break;
                case "attribute.name.generic.attack_speed":
                    amount += 0.4D;
                    break;
                case "attribute.name.generic.attack_knockback":
                case "attribute.name.generic.attack_damage":
                case "forge.reachDistance":
                    amount += 0.5D;
                    break;
                case "attribute.name.generic.luck":
                    amount += 2.0D;
                    break;
                case "forge.nameTagDistance":
                    amount -= 8.0D;
                    break;
                case "forge.entity_gravity":
                    amount -= 0.02D;
                    break;
            }
            AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Anointment bonus", amount, AttributeModifier.Operation.ADDITION);
            multimap.put(attribute, modifier);

            for (Map.Entry<Attribute, AttributeModifier> entry : multimap.entries()) {
                for (Map.Entry<Attribute, AttributeModifier> entry1 : itemstack.getAttributeModifiers(slot).entries()) {
                    if (!entry.getKey().getDescriptionId().equals(entry1.getKey().getDescriptionId())) itemstack.addAttributeModifier(entry.getKey(), entry.getValue(), slot);
                }
            }

            itemstack.getOrCreateTag().putBoolean("Anointed", true);

            this.resultSlots.setRecipeUsed(this.recipe);
            this.resultSlots.setItem(0, itemstack);
        }
    }

    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.access.execute((world, pos) -> {
            this.clearContainer(playerIn, world, this.inputSlots);
        });
    }

    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack stack) {
        return this.recipes.stream().anyMatch((recipe) -> {
            return recipe.isValidAdditionItem(stack);
        }) || stack.getItem() == HallowsItems.WITCHS_BREW.get();
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn) {
        return slotIn.container != this.resultSlots && super.canTakeItemForPickAll(stack, slotIn);
    }
}