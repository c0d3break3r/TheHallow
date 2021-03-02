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
import net.minecraftforge.common.ForgeMod;
import pugz.hallows.common.item.crafting.HallowingRecipe;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class AnointingTableContainer extends AbstractRepairContainer {
    private final World world;
    @Nullable
    private HallowingRecipe recipe;
    private final List<HallowingRecipe> recipes;

    public AnointingTableContainer(int id, PlayerInventory inventory) {
        this(id, inventory, IWorldPosCallable.DUMMY);
    }

    public AnointingTableContainer(int id, PlayerInventory inventory, IWorldPosCallable pos) {
        super(HallowsContainers.ANOINTING.get(), id, inventory, pos);
        this.world = inventory.player.world;
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
                    attribute == Attributes.HORSE_JUMP_STRENGTH) return;

            ItemStack itemstack = this.recipe.getCraftingResult(this.field_234643_d_);
            EquipmentSlotType slot = MobEntity.getSlotForItemStack(itemstack);
            double amount = 0;

            Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();

            for (Map.Entry<Attribute, AttributeModifier> entry : itemstack.getAttributeModifiers(slot).entries()) {
                if (entry.getKey().getAttributeName().equals(attribute.getAttributeName())) {
                    amount = entry.getValue().getAmount();
                } else {
                    multimap.put(entry.getKey(), entry.getValue());
                }
            }

            switch (attribute.getAttributeName()) {
                default:
                case "attribute.name.generic.armor":
                case "attribute.name.generic.armor_toughness":
                    amount += 1.0D;
                    break;
                case "attribute.name.generic.follow_range":
                    amount -= 4.0D;
                    break;
                case "attribute.name.generic.knockback_resistance":
                case "attribute.name.generic.movement_speed":
                case "forge.swimSpeed":
                    amount += 0.05D;
                    break;
                case "attribute.name.generic.attack_knockback":
                    amount += 0.2D;
                    break;
                case "attribute.name.generic.attack_speed":
                    amount -= 0.5D;
                    break;
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
                itemstack.addAttributeModifier(entry.getKey(), entry.getValue(), slot);
            }

            this.field_234642_c_.setRecipeUsed(this.recipe);
            this.field_234642_c_.setInventorySlotContents(0, itemstack);
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