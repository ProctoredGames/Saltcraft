package net.proctoredgames.saltcraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation HALF_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/half_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(Saltcraft.MOD_ID,
            "textures/thirst/empty_thirst.png");

    public static final IGuiOverlay HUD_THIRST = ((gui, guiGraphics, partialTick, width, height) -> {
        Player player = Minecraft.getInstance().player;
        if (player == null || player.isSpectator() || player.isCreative()
                || !player.hasEffect(ModEffects.THIRST.get())) {
            return;
        }

        int thirst = ClientThirstData.getThirst();

        int x = width / 2;
        int y = height - ((player.isUnderWater() || player.getAirSupply() < player.getMaxAirSupply()) ? 59 : 49);

        // Empty background icons
        for (int i = 0; i < 10; i++) {
            guiGraphics.blit(EMPTY_THIRST, x + 10 + (i * 8), y, 0, 0, 9, 9, 9, 9);
        }

        // Filled / half icons, drawn right to left
        int fullIcons = thirst / 2;
        boolean hasHalf = thirst % 2 == 1;

        for (int i = 0; i < 10; i++) {
            int iconX = x + 10 + ((9 - i) * 8);
            if (i < fullIcons) {
                guiGraphics.blit(FILLED_THIRST, iconX, y, 0, 0, 9, 9, 9, 9);
            } else {
                if (hasHalf) {
                    guiGraphics.blit(HALF_THIRST, iconX, y, 0, 0, 9, 9, 9, 9);
                }
                break;
            }
        }
    });
}