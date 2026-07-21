package net.proctoredgames.saltcraft.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.proctoredgames.saltcraft.Saltcraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation HALF_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/half_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/empty_thirst.png");
    private static final int EXTRA_RENDER_TICKS = 20;
    private static int lastSatisfiedGuiTick = Integer.MIN_VALUE;

    public static final IGuiOverlay HUD_THIRST = ((gui, guiGraphics, partialTick, width, height) -> {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        int x = width / 2;
        int y = height;
        int j;

        int thirstBarYPosition = (player.isUnderWater() || player.getAirSupply() < player.getMaxAirSupply()) ? 59 : 49;

        boolean satisfiesRenderingConditions = ((player.hasEffect(ModEffects.THIRST.get())) || (ClientThirstData.getPlayerThirst() != 20));
        if(satisfiesRenderingConditions){
            lastSatisfiedGuiTick = gui.getGuiTicks();
        }
        // Keep the bar on screen for a moment after it refills; gui ticks rather than
        // frames so the fade time does not depend on the framerate
        boolean hasExtraRenderingTime = gui.getGuiTicks() - lastSatisfiedGuiTick < EXTRA_RENDER_TICKS;

        if((satisfiesRenderingConditions || hasExtraRenderingTime) && !(player.isSpectator() || player.isCreative())){
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, EMPTY_THIRST);
            for(int i = 0; i < 10; i++) {
                guiGraphics.blit(EMPTY_THIRST,x +10 + (i * 8), y - thirstBarYPosition,0,0,9,9,
                        9,9);
            }

            RenderSystem.setShaderTexture(0, FILLED_THIRST);
            for(int i = 0; i < 10; i++) {
                j=9-i;
                if(Math.floor((double)ClientThirstData.getPlayerThirst()/2) > i) {
                    guiGraphics.blit(FILLED_THIRST,x +10 + (j * 8),y - thirstBarYPosition,0,0,9,9,
                            9,9);
                } else {
                    if(ClientThirstData.getPlayerThirst()%2 == 1) {
                        guiGraphics.blit(HALF_THIRST, x + 10 + (j * 8), y - thirstBarYPosition, 0, 0, 9, 9,
                                9, 9);
                    }
                    break;
                }
            }
        }
    });
}
