//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.block.custom;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class SummoningPlinthBlock extends Block implements SimpleWaterloggedBlock {
    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
    public static final BooleanProperty CRACKED;
    public static final BooleanProperty LIT;
    public static final BooleanProperty WATERLOGGED;
    private final int fireDamage;

    public SummoningPlinthBlock(int pFireDamage, Properties pProperties) {
        super(pProperties);
        this.fireDamage = pFireDamage;
        this.registerDefaultState(this.stateDefinition.any().setValue(CRACKED, false).setValue(LIT, false).setValue(WATERLOGGED, false));
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean flag = !(Boolean)pState.getValue(LIT);
        if(flag && pPlayer.getMainHandItem().getItem() == Items.FLINT_AND_STEEL){
            if (!pLevel.isClientSide()) {
                pLevel.playSound((Player)null, pPos, SoundEvents.SUSPICIOUS_GRAVEL_BREAK, SoundSource.BLOCKS, 5.0F, 1.0F);
                pPlayer.getMainHandItem().hurt(1, RandomSource.create(), (ServerPlayer) pPlayer);
                pLevel.setBlock(pPos, pState.setValue(CRACKED, true).setValue(LIT, true), 3);
                return InteractionResult.SUCCESS;
            } else{
                for(int i = 0; i < 20; ++i) {
                    makeParticles((Level)pLevel, pPos);
                }
            }
            return InteractionResult.CONSUME_PARTIAL;
        }
        return InteractionResult.PASS;
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pState.getValue(LIT) && pEntity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)pEntity)) {
            pEntity.hurt(pLevel.damageSources().magic(), (float)this.fireDamage);
        }

        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            if (pRandom.nextInt(10) == 0) {
                pLevel.playLocalSound((double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.6F, false);
            }
        }

    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!(Boolean)pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            pLevel.setBlock(pPos, pState.setValue(WATERLOGGED, true), 3);
            pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            return true;
        } else {
            return false;
        }
    }

    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        BlockPos blockpos = pHit.getBlockPos();
        if (pProjectile.mayInteract(pLevel, blockpos) && pProjectile.isOnFire() && !(Boolean)pState.getValue(LIT) && !(Boolean)pState.getValue(WATERLOGGED)) {
            if(!pLevel.isClientSide){
                pLevel.setBlock(blockpos, pState.setValue(BlockStateProperties.CAN_SUMMON, true).setValue(BlockStateProperties.LIT, true), 11);
            }else{
                for(int i = 0; i < 20; ++i) {
                    makeParticles((Level)pLevel, new BlockPos((int)pHit.getBlockPos().getCenter().x, (int)pHit.getBlockPos().getCenter().y, (int)pHit.getBlockPos().getCenter().z));
                }
            }

        }

    }

    public static void makeParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.getRandom();
        SimpleParticleType simpleparticletype = ParticleTypes.POOF;
        pLevel.addAlwaysVisibleParticle(simpleparticletype, true, (double)pPos.getX() + 0.5 + randomsource.nextDouble() / 3.0 * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pPos.getZ() + 0.5 + randomsource.nextDouble() / 3.0 * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);

    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{CRACKED, LIT, WATERLOGGED});
    }


    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    static {
        CRACKED = BlockStateProperties.CRACKED;
        LIT = BlockStateProperties.LIT;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }
}
