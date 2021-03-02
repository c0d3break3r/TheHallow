package pugz.hallows.common.tileentity.container;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;
import java.util.*;

public class AnointingTableContainer extends AbstractRepairContainer {
    private final World world;
    private HallowingRecipe recipe;
    private final List<HallowingRecipe> recipes;
    private PlayerEntity player;

    public AnointingTableContainer(int id, PlayerInventory inventory) {
        this(id, inventory, IWorldPosCallable.DUMMY);
        this.player = inventory.player;
    }

    public AnointingTableContainer(int id, PlayerInventory inventory, IWorldPosCallable pos) {
        super(HallowsContainers.ANOINTING.get(), id, inventory, pos);
        this.world = inventory.player.world;
        this.player = inventory.player;
        this.recipes = this.world.getRecipeManager().getRecipesForType(HallowsRecipes.Recipes.HALLOWING);
    }

    protected boolean func_230302_a_(BlockState state) {
        return state.isIn(Blocks.SMITHING_TABLE);
    }

    protected boolean func_230303_b_(PlayerEntity player, boolean p_230303_2_) {
        return this.recipe != null && this.recipe.matches(this.field_234643_d_, this.world);
    }

    @Nonnull
    protected ItemStack func_230301_a_(PlayerEntity player, ItemStack stack) {
        stack.onCrafting(player.world, player, stack.getCount());
        this.field_234642_c_.onCrafting(player);
        this.func_234654_d_(0);
        this.func_234654_d_(1);
        this.field_234644_e_.consume((world, pos) -> {
            world.playEvent(1044, pos, 0);
        });
        return stack;
    }

    private void func_234654_d_(int index) {
        ItemStack itemstack = this.field_234643_d_.getStackInSlot(index);
        itemstack.shrink(1);
        this.field_234643_d_.setInventorySlotContents(index, itemstack);
    }

    public void updateRepairOutput() {
        List<HallowingRecipe> list = this.world.getRecipeManager().getRecipes(HallowsRecipes.Recipes.HALLOWING, this.field_234643_d_, this.world);
        if (list.isEmpty()) {
            this.field_234642_c_.setInventorySlotContents(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            Attribute attribute = this.recipe.getAttribute();
            if (attribute == Attributes.FLYING_SPEED ||
                    attribute == Attributes.ZOMBIE_SPAWN_REINFORCEMENTS ||
                    attribute == Attributes.HORSE_JUMP_STRENGTH ||
                    attribute == Attributes.FOLLOW_RANGE) return;

            ItemStack itemstack = this.recipe.getCraftingResult(this.field_234643_d_);
            if (itemstack.getOrCreateTag().getBoolean("Anointed")) return;

            EquipmentSlotType slot = MobEntity.getSlotForItemStack(itemstack);
            Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
            double amount = 0;

            for (Map.Entry<Attribute, AttributeModifier> entry : itemstack.getAttributeModifiers(slot).entries()) {
                if (entry.getKey().getAttributeName().equals(attribute.getAttributeName())) {
                    amount = entry.getValue().getAmount();
                    amount += player.getBaseAttributeValue(attribute);
                } else {
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Anointment bonus", entry.getValue().getAmount() + player.getAttributeValue(entry.getKey()), AttributeModifier.Operation.ADDITION);
                    multimap.put(entry.getKey(), modifier);
                }
            }

            switch (attribute.getAttributeName()) {
                default:
                case "attribute.name.generic.armor_toughness":
                    amount += 1.0D;
                    break;
                case "attribute.name.generic.knockback_resistance":
                case "forge.swimSpeed":
                case "attribute.name.generic.movement_speed":
                    amount += 0.05D;
                    break;
                case "attribute.name.generic.attack_knockback":
                    amount += 0.2D;
                    break;
                case "attribute.name.generic.attack_speed":
                    amount += 0.4D;
                    break;
                case "attribute.name.generic.armor":
                case "attribute.name.generic.attack_damage":
                case "attribute.name.generic.max_health":
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
                    if (!entry.getKey().getAttributeName().equals(entry1.getKey().getAttributeName())) itemstack.addAttributeModifier(entry.getKey(), entry.getValue(), slot);
                }
            }

            itemstack.getOrCreateTag().putBoolean("Anointed", true);

            this.field_234642_c_.setRecipeUsed(this.recipe);
            this.field_234642_c_.setInventorySlotContents(0, itemstack);
        }
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        PlayerInventory playerinventory = playerIn.inventory;
        if (!playerinventory.getItemStack().isEmpty()) {
            playerIn.dropItem(playerinventory.getItemStack(), false);
            playerinventory.setItemStack(ItemStack.EMPTY);
        }
    }

    protected boolean func_241210_a_(ItemStack stack) {
        return this.recipes.stream().anyMatch((recipe) -> {
            return recipe.isValidAdditionItem(stack);
        });
    }

    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.field_234642_c_ && super.canMergeSlot(stack, slotIn);
    }
}