package net.proctoredgames.saltcraft.client;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.telemetry.TelemetryProperty;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.proctoredgames.saltcraft.Saltcraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.thirst.PlayerThirst;

public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation HALF_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/half_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/empty_thirst.png");
    private static Player player;
    private static boolean hasExtraRenderingTime = false;
    private static final int MAX_TICKS_UNTIL_STOP_RENDERING = 20;
    private static int ticksUntilStopRendering = MAX_TICKS_UNTIL_STOP_RENDERING;

    public static final IGuiOverlay HUD_THIRST = ((gui, guiGraphics, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;
        int j;

        int thirstBarYPosition = (player.isUnderWater() || player.getAirSupply() < player.getMaxAirSupply()) ? 59 : 49;

        boolean satisfiesRenderingConditions = ((player.hasEffect(ModEffects.THIRST.get())) || (ClientThirstData.getPlayerThirst() != 20));
        if(satisfiesRenderingConditions){
            hasExtraRenderingTime = true;
            ticksUntilStopRendering = MAX_TICKS_UNTIL_STOP_RENDERING;
        }

        if(hasExtraRenderingTime && ClientThirstData.getPlayerThirst() == 20){
            ticksUntilStopRendering --;
            if(ticksUntilStopRendering <= 0){
                hasExtraRenderingTime = false;
                ticksUntilStopRendering = MAX_TICKS_UNTIL_STOP_RENDERING;
            }
        }


        if((satisfiesRenderingConditions || (hasExtraRenderingTime && ClientThirstData.getPlayerThirst() == 20)) && !(player.isSpectator() || player.isCreative())){
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
    public static void setPlayer(Player pPlayer){
        player = pPlayer;
    }
}