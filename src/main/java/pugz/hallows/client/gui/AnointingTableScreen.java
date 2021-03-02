package pugz.hallows.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.AbstractRepairScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.common.tileentity.container.AnointingTableContainer;
import pugz.hallows.core.Hallows;

@OnlyIn(Dist.CLIENT)
public class AnointingTableScreen extends AbstractRepairScreen<AnointingTableContainer> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Hallows.MOD_ID,"textures/gui/container/anointing.png");

    public AnointingTableScreen(AnointingTableContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title, GUI_TEXTURE);
        this.titleX = 60;
        this.titleY = 18;
    }

    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        RenderSystem.disableBlend();
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
    }
}
