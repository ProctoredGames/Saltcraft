package net.proctoredgames.saltcraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class SpawningPlinthBlock extends Block implements FluidFillable, Waterloggable {
    public static final MapCodec<SpawningPlinthBlock> CODEC = createCodec(SpawningPlinthBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Arena-sized radius; scanning further would force-load chunks and freeze the server
    public static final int PLINTH_SEARCH_RADIUS = 48;

    public SpawningPlinthBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false).with(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean canFillWithFluid(net.minecraft.entity.player.PlayerEntity player, BlockView world, BlockPos pos, BlockState state, net.minecraft.fluid.Fluid fluid) {
        return !state.get(WATERLOGGED) && fluid == Fluids.WATER;
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.get(WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
            world.setBlockState(pos, state.with(WATERLOGGED, true), 3);
            world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, net.minecraft.entity.ai.pathing.NavigationType type) {
        return true;
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        super.onProjectileHit(world, state, hit, projectile);

        if (!world.isClient && !state.get(LIT)) {
            if (canSpawn(world, hit.getBlockPos().toCenterPos())) {
                world.setBlockState(hit.getBlockPos(), state.with(LIT, true), 3);
                spawnSaltMage((ServerWorld) world, BlockPos.ofFloored(hit.getBlockPos().toCenterPos()));
            }
        }
    }

    public boolean canSpawn(World world, Vec3d position) {
        BlockState blockState;
        BlockPos blockPos;
        int relativeYLayer = 0;
        boolean checkingLayers = true;
        while (checkingLayers) {
            for (int z = -PLINTH_SEARCH_RADIUS; z <= PLINTH_SEARCH_RADIUS; z++) {
                for (int x = -PLINTH_SEARCH_RADIUS; x <= PLINTH_SEARCH_RADIUS; x++) {
                    blockPos = new BlockPos((int) position.getX() + x, (int) position.getY() + relativeYLayer, (int) position.getZ() + z);
                    if (!world.isChunkLoaded(blockPos)) {
                        continue;
                    }
                    blockState = world.getBlockState(blockPos);
                    if (blockState.isOf(ModBlocks.SUMMONING_PLINTH)) {
                        if (!blockState.get(SummoningPlinthBlock.LIT)) {
                            return false;
                        }
                    }
                }
            }
            if (relativeYLayer == 0) {
                relativeYLayer = 8;
            } else if (relativeYLayer == 8) {
                relativeYLayer = 10;
            } else if (relativeYLayer == 10) {
                relativeYLayer = 12;
            } else {
                checkingLayers = false;
            }
        }
        return true;
    }

    public void spawnSaltMage(ServerWorld world, BlockPos position) {
        SaltMage entity = new SaltMage(ModEntities.SALT_MAGE, world);
        entity.setPosition(position.toCenterPos());
        entity.setSpawningPlinthPosition(position);
        world.spawnEntity(entity);
        world.createExplosion(null, position.getX(), position.getY() + 2, position.getZ(), 2.0F, World.ExplosionSourceType.NONE);
    }
}
