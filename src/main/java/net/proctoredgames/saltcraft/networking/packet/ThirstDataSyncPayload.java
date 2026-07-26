package net.proctoredgames.saltcraft.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

public record ThirstDataSyncPayload(int thirst) implements CustomPayload {
    public static final CustomPayload.Id<ThirstDataSyncPayload> ID =
            new CustomPayload.Id<>(Identifier.of(Saltcraft.MOD_ID, "thirst_sync"));

    public static final PacketCodec<RegistryByteBuf, ThirstDataSyncPayload> CODEC =
            PacketCodecs.VAR_INT.xmap(ThirstDataSyncPayload::new, ThirstDataSyncPayload::thirst)
                    .cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
