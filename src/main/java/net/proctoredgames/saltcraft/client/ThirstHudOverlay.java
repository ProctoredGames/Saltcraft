package net.proctoredgames.saltcraft.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ThirstHudOverlay {
    private static final Identifier FILLED_THIRST = Identifier.of(Saltcraft.MOD_ID, "textures/thirst/filled_thirst.png");
    private static final Identifier HALF_THIRST = Identifier.of(Saltcraft.MOD_ID, "textures/thirst/half_thirst.png");
    private static final Identifier EMPTY_THIRST = Identifier.of(Saltcraft.MOD_ID, "textures/thirst/empty_thirst.png");
    private static final int EXTRA_RENDER_TICKS = 20;
    private static int lastSatisfiedGuiTick = Integer.MIN_VALUE;

    public static final HudRenderCallback HUD_THIRST = (context, tickCounter) -> {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player == null) {
            return;
        }

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();
        int x = width / 2;
        int y = height;
        int j;

        int thirstBarYPosition = (player.isSubmergedInWater() || player.getAir() < player.getMaxAir()) ? 59 : 49;

        boolean satisfiesRenderingConditions = player.hasStatusEffect(ModEffects.THIRST) || ClientThirstData.getPlayerThirst() != 20;
        if (satisfiesRenderingConditions) {
            lastSatisfiedGuiTick = client.inGameHud.getTicks();
        }
        // Keep the bar on screen for a moment after it refills; gui ticks rather than
        // frames so the fade time does not depend on the framerate
        boolean hasExtraRenderingTime = client.inGameHud.getTicks() - lastSatisfiedGuiTick < EXTRA_RENDER_TICKS;

        if ((satisfiesRenderingConditions || hasExtraRenderingTime) && !(player.isSpectator() || player.isCreative())) {
            for (int i = 0; i < 10; i++) {
                context.drawTexture(EMPTY_THIRST, x + 10 + (i * 8), y - thirstBarYPosition, 0, 0, 9, 9, 9, 9);
            }

            for (int i = 0; i < 10; i++) {
                j = 9 - i;
                if (Math.floor((double) ClientThirstData.getPlayerThirst() / 2) > i) {
                    context.drawTexture(FILLED_THIRST, x + 10 + (j * 8), y - thirstBarYPosition, 0, 0, 9, 9, 9, 9);
                } else {
                    if (ClientThirstData.getPlayerThirst() % 2 == 1) {
                        context.drawTexture(HALF_THIRST, x + 10 + (j * 8), y - thirstBarYPosition, 0, 0, 9, 9, 9, 9);
                    }
                    break;
                }
            }
        }
    };
}
