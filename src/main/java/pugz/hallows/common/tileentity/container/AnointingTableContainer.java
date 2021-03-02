package pugz.hallows.common.tileentity.container;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import pugz.hallows.common.item.crafting.AnointingRecipe;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AnointingTableContainer extends AbstractRepairContainer {
    private final World world;
    @Nullable
    private AnointingRecipe recipe;
    private final List<AnointingRecipe> recipes;

    public AnointingTableContainer(int id, PlayerInventory inventory) {
        this(id, inventory, IWorldPosCallable.DUMMY);
    }

    public AnointingTableContainer(int id, PlayerInventory inventory, IWorldPosCallable pos) {
        super(HallowsContainers.ANOINTING.get(), id, inventory, pos);
        this.world = inventory.player.world;
        this.recipes = this.world.getRecipeManager().getRecipesForType(HallowsRecipes.ANOINTING);
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
        this.field_234644_e_.consume((p_234653_0_, p_234653_1_) -> {
            p_234653_0_.playEvent(1044, p_234653_1_, 0);
        });
        return stack;
    }

    private void func_234654_d_(int index) {
        ItemStack itemstack = this.field_234643_d_.getStackInSlot(index);
        itemstack.shrink(1);
        this.field_234643_d_.setInventorySlotContents(index, itemstack);
    }

    public void updateRepairOutput() {
        List<AnointingRecipe> list = this.world.getRecipeManager().getRecipes(HallowsRecipes.ANOINTING, this.field_234643_d_, this.world);
        if (list.isEmpty()) {
            this.field_234642_c_.setInventorySlotContents(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            ItemStack itemstack = this.recipe.getCraftingResult(this.field_234643_d_);
            this.field_234642_c_.setRecipeUsed(this.recipe);
            this.field_234642_c_.setInventorySlotContents(0, itemstack);
        }

    }

    protected boolean func_241210_a_(ItemStack stack) {
        return this.recipes.stream().anyMatch((p_241444_1_) -> {
            return p_241444_1_.isValidAdditionItem(stack);
        });
    }

    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.field_234642_c_ && super.canMergeSlot(stack, slotIn);
    }
}