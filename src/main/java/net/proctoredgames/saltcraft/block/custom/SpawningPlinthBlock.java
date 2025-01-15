package net.proctoredgames.saltcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.common.Mod;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;


public class SpawningPlinthBlock extends Block implements SimpleWaterloggedBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    public static final BooleanProperty LIT;
    public static final BooleanProperty WATERLOGGED;

    public SpawningPlinthBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false).setValue(WATERLOGGED, false));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!(Boolean)pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            pLevel.setBlock(pPos, (BlockState)pState.setValue(WATERLOGGED, true), 3);
            pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            return true;
        } else {
            return false;
        }
    }

    public FluidState getFluidState(BlockState pState) {
        return (Boolean)pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{LIT, WATERLOGGED});
    }


    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return true;
    }

    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        super.onProjectileHit(pLevel, pState, pHit, pProjectile);

        if(!(Boolean)pState.getValue(LIT)){
            if(canSpawn(pLevel, pHit.getBlockPos().getCenter())) {
                pLevel.setBlock(pHit.getBlockPos(), pState.setValue(LIT, true), 3);
                spawnSaltMage(pLevel, BlockPos.containing(pHit.getBlockPos().getCenter()));
            }
        }
    }

    public boolean canSpawn(Level level, Vec3 position){
        BlockState blockState;
        BlockPos blockPos;
        int relativeYLayer = 0;
        boolean checkingLayers = true;
        while(checkingLayers){
            for(int z = -100; z<=100; z++){
                for(int x = -100; x<=100; x++){
                    blockPos = new BlockPos((int)position.x+x, (int)position.y+relativeYLayer, (int)position.z+z);
                    blockState = level.getBlockState(blockPos);
                    if(blockState.is(ModBlocks.SUMMONING_PLINTH.get())){
                        if(!blockState.getValue(SummoningPlinthBlock.LIT)){
                            return false;
                        }
                    }
                }
            }
            if(relativeYLayer == 0) {
                relativeYLayer = 8;
            } else if(relativeYLayer == 8) {
                relativeYLayer = 10;
            }else if(relativeYLayer == 10){
                relativeYLayer = 12;
            } else{
                checkingLayers = false;
            }
        }
        return true;
    }

    public void spawnSaltMage(Level level, BlockPos position){
        SaltMage entity = new SaltMage(ModEntities.SALT_MAGE.get(), level);
        entity.setPos(position.getCenter());
        level.addFreshEntity(entity);
        if (!level.isClientSide) {
            level.explode(null, null, (ExplosionDamageCalculator)null, position.getX(),
                    position.getY()+2, position.getZ(), 2.0F, false, Level.ExplosionInteraction.NONE);
        }
        entity.SetSpawningPlinthPosition(position);
    }

    static {
        LIT = BlockStateProperties.LIT;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }
}
