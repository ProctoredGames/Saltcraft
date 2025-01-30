package net.proctoredgames.saltcraft.worldgen.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.stream.Stream;

public class ModBiomeProvider extends BiomeSource {
    public static final Codec<ModBiomeProvider> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                    Codec.list(Biome.CODEC).fieldOf("biomes").forGetter(provider -> provider.biomes)
            ).apply(instance, ModBiomeProvider::new));

    private final List<Holder<Biome>> biomes;

    public ModBiomeProvider(List<Holder<Biome>> biomes) {
        this.biomes = biomes;
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return biomes.stream();
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        int index = Math.floorMod(x + y + z, biomes.size()); // Ensures no negative indexes
        return biomes.get(index);
    }
}
