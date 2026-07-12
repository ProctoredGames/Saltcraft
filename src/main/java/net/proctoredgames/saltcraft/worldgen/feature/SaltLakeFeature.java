//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.common.Mod;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;

@Deprecated
public class SaltLakeFeature extends Feature<NoneFeatureConfiguration> {
    private static final BlockState AIR;

    public SaltLakeFeature(Codec<NoneFeatureConfiguration> p_159834_) {
        super(p_159834_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        BlockPos $$1 = pContext.origin();
        WorldGenLevel $$2 = pContext.level();
        RandomSource $$3 = pContext.random();
        if ($$1.getY() <= $$2.getMinBuildHeight() + 2) {
            return false;
        } else {
            // Features may only place blocks in the decorated chunk and its direct neighbors.
            // The 32x32 area must be anchored to the chunk rather than the random origin,
            // or its far edge lands outside that range where setBlock silently fails and
            // the lake gets cut off along chunk borders.
            ChunkPos chunkPos = new ChunkPos($$1);
            $$1 = new BlockPos(chunkPos.getMinBlockX() - 8, $$1.getY(), chunkPos.getMinBlockZ() - 8).below(2);
            boolean[] $$5 = new boolean[8192];
            int $$6 = $$3.nextInt(8) + 8;

            for(int $$7 = 0; $$7 < $$6; ++$$7) {
                double $$8 = $$3.nextDouble() * 12.0 + 6.0;
                double $$9 = $$3.nextDouble() * 8.0 + 4.0;
                double $$10 = $$3.nextDouble() * 12.0 + 6.0;
                double $$11 = $$3.nextDouble() * (32.0 - $$8 - 4.0) + 2.0 + $$8 / 2.0;
                double $$12 = $$3.nextDouble() * (16.0 - $$9 - 8.0) + 4.0 + $$9 / 2.0;
                double $$13 = $$3.nextDouble() * (32.0 - $$10 - 4.0) + 2.0 + $$10 / 2.0;

                for(int $$14 = 1; $$14 < 31; ++$$14) {
                    for(int $$15 = 1; $$15 < 31; ++$$15) {
                        for(int $$16 = 1; $$16 < 7; ++$$16) {
                            double $$17 = ((double)$$14 - $$11) / ($$8 / 2.0);
                            double $$18 = ((double)$$16 - $$12) / ($$9 / 2.0);
                            double $$19 = ((double)$$15 - $$13) / ($$10 / 2.0);
                            double $$20 = $$17 * $$17 + $$18 * $$18 + $$19 * $$19;
                            if ($$20 < 3.0) {
                                $$5[($$14 * 32 + $$15) * 8 + $$16] = true;
                            }
                        }
                    }
                }
            }

            BlockState $$21 = ModBlocks.ROCK_SALT_SLAB.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,true);

            int $$33;
            boolean $$41;
            int $$22;
            int $$34;
            //make sure we have room to place the structure
            for($$22 = 0; $$22 < 32; ++$$22) {
                for($$33 = 0; $$33 < 32; ++$$33) {
                    for($$34 = 0; $$34 < 8; ++$$34) {
                        $$41 = !$$5[($$22 * 32 + $$33) * 8 + $$34] && ($$22 < 31 && $$5[(($$22 + 1) * 32 + $$33) * 8 + $$34] || $$22 > 0 && $$5[(($$22 - 1) * 32 + $$33) * 8 + $$34] || $$33 < 31 && $$5[($$22 * 32 + $$33 + 1) * 8 + $$34] || $$33 > 0 && $$5[($$22 * 32 + ($$33 - 1)) * 8 + $$34] || $$34 < 7 && $$5[($$22 * 32 + $$33) * 8 + $$34 + 1] || $$34 > 0 && $$5[($$22 * 32 + $$33) * 8 + ($$34 - 1)]);
                        if ($$41) {
                            BlockState $$26 = $$2.getBlockState($$1.offset($$22, $$34, $$33));
                            if ($$34 >= 2 && $$26.liquid()) {
                                return false;
                            }

                            if ($$34 < 2 && !$$26.isSolid() && $$2.getBlockState($$1.offset($$22, $$34, $$33)) != $$21) {
                                return false;
                            }
                        }
                    }
                }
            }

            boolean $$36;
            //places filler blocks
            for($$22 = 0; $$22 < 32; ++$$22) {
                for($$33 = 0; $$33 < 32; ++$$33) {
                    for($$34 = 0; $$34 < 8; ++$$34) {
                        if ($$5[($$22 * 32 + $$33) * 8 + $$34]) {
                            BlockPos $$30 = $$1.offset($$22, $$34, $$33);
                            if (this.canReplaceBlock($$2.getBlockState($$30))) {
                                $$36 = $$34 >= 2;
                                $$2.setBlock($$30, $$36 ? AIR : $$21, 2);
                                if ($$36) {
                                    $$2.scheduleTick($$30, AIR.getBlock(), 0);
                                    this.markAboveForPostProcessing($$2, $$30);
                                }
                                if(!($$36) && $$34==1 && $$3.nextInt(50)==0){
                                    Flamingo flamingo = new Flamingo(ModEntities.FLAMINGO.get(), $$2.getLevel());
                                    flamingo.setPos($$1.offset($$22, $$34+1, $$33).getCenter());
                                    $$2.addFreshEntity(flamingo);
                                }
                            }
                        }
                    }
                }
            }

            BlockState $$32 = ModBlocks.ROCK_SALT_BLOCK.get().defaultBlockState();
            //places border blocks
            if (!$$32.isAir()) {
                for($$33 = 0; $$33 < 32; ++$$33) {
                    for($$34 = 0; $$34 < 32; ++$$34) {
                        for(int $$35 = 0; $$35 < 8; ++$$35) {
                            $$36 = !$$5[($$33 * 32 + $$34) * 8 + $$35] && ($$33 < 31 && $$5[(($$33 + 1) * 32 + $$34) * 8 + $$35] || $$33 > 0 && $$5[(($$33 - 1) * 32 + $$34) * 8 + $$35] || $$34 < 31 && $$5[($$33 * 32 + $$34 + 1) * 8 + $$35] || $$34 > 0 && $$5[($$33 * 32 + ($$34 - 1)) * 8 + $$35] || $$35 < 7 && $$5[($$33 * 32 + $$34) * 8 + $$35 + 1] || $$35 > 0 && $$5[($$33 * 32 + $$34) * 8 + ($$35 - 1)]);
                            if ($$36 && ($$35 < 2 || $$3.nextInt(2) != 0)) {
                                BlockState $$37 = $$2.getBlockState($$1.offset($$33, $$35, $$34));
                                if ($$37.isSolid() && !$$37.is(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                    BlockPos $$38 = $$1.offset($$33, $$35, $$34);
                                    $$2.setBlock($$38, $$32, 2);
                                    this.markAboveForPostProcessing($$2, $$38);
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean canReplaceBlock(BlockState pState) {
        return !pState.is(BlockTags.FEATURES_CANNOT_REPLACE);
    }

    static {
        AIR = Blocks.CAVE_AIR.defaultBlockState();
    }
}
