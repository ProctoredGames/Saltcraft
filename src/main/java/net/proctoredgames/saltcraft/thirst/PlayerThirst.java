package net.proctoredgames.saltcraft.thirst;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

public class PlayerThirst {
    private static final int MIN_THIRST = 0;
    private static final int MAX_THIRST = 20;

    public static final Codec<PlayerThirst> CODEC = Codec.INT.xmap(PlayerThirst::new, PlayerThirst::getThirst);

    public static final AttachmentType<PlayerThirst> THIRST = AttachmentRegistry.<PlayerThirst>builder()
            .persistent(CODEC)
            .copyOnDeath()
            .initializer(PlayerThirst::new)
            .buildAndRegister(Identifier.of(Saltcraft.MOD_ID, "thirst"));

    private int thirst;

    public PlayerThirst() {
        this.thirst = MAX_THIRST;
    }

    public PlayerThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getThirst() {
        return thirst;
    }

    public void addThirst(int add) {
        this.thirst = Math.min(thirst + add, MAX_THIRST);
    }

    public void subThirst(int sub) {
        this.thirst = Math.max(thirst - sub, MIN_THIRST);
    }
}
