package pugz.hallows.common.tileentity.container;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import pugz.hallows.common.item.crafting.AnointmentRecipe;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AnointmentTableContainer extends Container {
    protected final CraftResultInventory resultInventory = new CraftResultInventory();
    private final IInventory tableInventory = new Inventory(2) {
        public void markDirty() {
            super.markDirty();
        }
    };

    private final World world;
    @Nullable
    private AnointmentRecipe recipe;
    private final List<AnointmentRecipe> recipes;
    protected final IWorldPosCallable posCallable;
    protected final PlayerEntity player;

    public AnointmentTableContainer(@Nullable ContainerType<?> containerType, int id, PlayerInventory inventory, IWorldPosCallable posCallable) {
        super(containerType, id);
        this.posCallable = posCallable;
        this.player = inventory.player;
        this.world = this.player.world;
        this.recipes = this.world.getRecipeManager().getRecipesForType(HallowsRecipes.ANOINTMENT);
        this.addSlot(new Slot(this.tableInventory, 0, 27, 47));
        this.addSlot(new Slot(this.tableInventory, 1, 76, 47));
        this.addSlot(new Slot(this.tableInventory, 2, 134, 47) {

            public boolean isItemValid(ItemStack stack) {
                return false;
            }

            public boolean canTakeStack(PlayerEntity playerIn) {
                return AnointmentTableContainer.this.func_230303_b_(playerIn, this.getHasStack());
            }

            @Nonnull
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                return AnointmentTableContainer.this.func_230301_a_(thePlayer, stack);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }

    public AnointmentTableContainer(int p_i231591_1_, PlayerInventory inventory, IWorldPosCallable posCallable) {
        super(HallowsContainers.ANOINTING, p_i231591_1_);
        this.world = inventory.player.world;
        this.posCallable = posCallable;
        this.player = inventory.player;
        this.recipes = this.world.getRecipeManager().getRecipesForType(HallowsRecipes.ANOINTMENT);
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.posCallable.applyOrElse((p_234646_2_, p_234646_3_) -> {
            return this.func_230302_a_(p_234646_2_.getBlockState(p_234646_3_)) && playerIn.getDistanceSq((double) p_234646_3_.getX() + 0.5D, (double) p_234646_3_.getY() + 0.5D, (double) p_234646_3_.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    protected boolean func_230302_a_(BlockState state) {
        return state.isIn(Blocks.SMITHING_TABLE);
    }

    protected boolean func_230303_b_(PlayerEntity player, boolean p_230303_2_) {
        return this.recipe != null && this.recipe.matches(this.tableInventory, this.world);
    }

    protected ItemStack func_230301_a_(PlayerEntity player, ItemStack p_230301_2_) {
        p_230301_2_.onCrafting(player.world, player, p_230301_2_.getCount());
        this.resultInventory.onCrafting(player);
        this.func_234654_d_(0);
        this.func_234654_d_(1);
        this.posCallable.consume((p_234653_0_, p_234653_1_) -> {
            p_234653_0_.playEvent(1044, p_234653_1_, 0);
        });
        return p_230301_2_;
    }

    private void func_234654_d_(int p_234654_1_) {
        ItemStack itemstack = this.tableInventory.getStackInSlot(p_234654_1_);
        itemstack.shrink(1);
        this.tableInventory.setInventorySlotContents(p_234654_1_, itemstack);
    }

    public void updateRepairOutput() {
        List<AnointmentRecipe> list = this.world.getRecipeManager().getRecipes(HallowsRecipes.ANOINTMENT, this.tableInventory, this.world);
        if (list.isEmpty()) {
            this.resultInventory.setInventorySlotContents(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            ItemStack itemstack = this.recipe.getCraftingResult(this.tableInventory);
            this.resultInventory.setRecipeUsed(this.recipe);
            this.resultInventory.setInventorySlotContents(0, itemstack);
        }
    }

    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        if (inventoryIn == this.tableInventory) {
            this.updateRepairOutput();
        }
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.posCallable.consume((p_234647_2_, p_234647_3_) -> {
            this.clearContainer(playerIn, p_234647_2_, this.tableInventory);
        });
    }

    @Nonnull
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 0 && index != 1) {
                if (index >= 3 && index < 39) {
                    int i = this.func_241210_a_(itemstack) ? 1 : 0;
                    if (!this.mergeItemStack(itemstack1, i, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    protected boolean func_241210_a_(ItemStack stack) {
        return this.recipes.stream().anyMatch((p_241444_1_) -> {
            return p_241444_1_.isValidAdditionItem(stack);
        });
    }

    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.resultInventory && super.canMergeSlot(stack, slotIn);
    }
}