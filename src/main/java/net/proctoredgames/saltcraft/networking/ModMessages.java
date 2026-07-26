package net.proctoredgames.saltcraft.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncPayload;

public class ModMessages {
    public static void register() {
        PayloadTypeRegistry.playS2C().register(ThirstDataSyncPayload.ID, ThirstDataSyncPayload.CODEC);
    }

    public static void sendToPlayer(ThirstDataSyncPayload payload, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, payload);
    }
}
