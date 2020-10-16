package thedarkcolour.futuremc.client.screen

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import thedarkcolour.futuremc.FutureMC
import thedarkcolour.futuremc.container.SmithingContainer

class SmithingScreen(container: SmithingContainer, playerInv: PlayerInventory, title: ITextComponent)
    : ContainerScreen<SmithingContainer>(container, playerInv, title) {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground()
        super.render(mouseX, mouseY, delta)
        RenderSystem.disableBlend()
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerBackgroundLayer(p_146976_1_: Float, p_146976_2_: Int, p_146976_3_: Int) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f)
        minecraft!!.textureManager.bindTexture(BACKGROUND)
        val i = guiLeft
        val j = guiTop
        blit(i, j, 0, 0, 176, 166)
        blit(i + 59, j + 20, 0, 166 + (if (container.inventory[0].isEmpty) 0 else 16), 110, 16)
        if ((!container.inventory[0].isEmpty || !container.inventory[1].isEmpty) && container.inventory[2].isEmpty) {
            blit(i + 99, j + 45, 176, 0, 28, 21)
        }
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        RenderSystem.disableBlend()
        font.drawString(title.formattedText, 60.0f, 20.0f, 4210752)
        font.drawString(playerInventory.displayName.formattedText, 8.0f, 72.0f, 4210752)
    }

    companion object {
        private val BACKGROUND = ResourceLocation(FutureMC.ID, "textures/gui/container/smithing.png")
    }
}