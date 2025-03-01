package net.proctoredgames.saltcraft.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.InputType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.client.ThirstHudOverlay;
//import net.proctoredgames.saltcraft.networking.ModMessages;
//import net.proctoredgames.saltcraft.networking.packet.DrinkWaterC2SPacket;
//import net.proctoredgames.saltcraft.networking.packet.ExampleC2SPacket;
//import net.proctoredgames.saltcraft.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.proctoredgames.saltcraft.item.custom.StaffOfTheDesertItem;
import net.proctoredgames.saltcraft.networking.ModMessages;

import java.util.Objects;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Saltcraft.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
    }

    @Mod.EventBusSubscriber(modid = Saltcraft.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerBelowAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }
    }
}
