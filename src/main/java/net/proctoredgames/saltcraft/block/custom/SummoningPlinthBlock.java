package net.proctoredgames.saltcraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class SummoningPlinthBlock extends Block implements FluidFillable, Waterloggable {
    public static final MapCodec<SummoningPlinthBlock> CODEC = createCodec(settings -> new SummoningPlinthBlock(5, settings));
    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
    public static final BooleanProperty CRACKED = Properties.CRACKED;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final int fireDamage;

    public SummoningPlinthBlock(int fireDamage, Settings settings) {
        super(settings);
        this.fireDamage = fireDamage;
        this.setDefaultState(this.stateManager.getDefaultState().with(CRACKED, false).with(LIT, false).with(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean notLit = !state.get(LIT);
        if (notLit && stack.getItem() == Items.FLINT_AND_STEEL) {
            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.BLOCK_SUSPICIOUS_GRAVEL_BREAK, SoundCategory.BLOCKS, 5.0F, 1.0F);
                stack.damage(1, player, net.minecraft.entity.EquipmentSlot.MAINHAND);
                world.setBlockState(pos, state.with(CRACKED, true).with(LIT, true), 3);
                return ItemActionResult.SUCCESS;
            } else {
                for (int i = 0; i < 20; ++i) {
                    makeParticles(world, pos);
                }
            }
            return ItemActionResult.CONSUME;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(LIT) && entity instanceof LivingEntity livingEntity && !hasFrostWalker(world, livingEntity)) {
            entity.damage(world.getDamageSources().magic(), (float) this.fireDamage);
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    private static boolean hasFrostWalker(World world, LivingEntity entity) {
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.FROST_WALKER)
                .map(enchantment -> EnchantmentHelper.getEquipmentLevel(enchantment, entity) > 0)
                .orElse(false);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
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
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            if (random.nextInt(10) == 0) {
                world.playSound((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + random.nextFloat(), random.nextFloat() * 0.7F + 0.6F, false);
            }
        }
    }

    @Override
    public boolean canFillWithFluid(PlayerEntity player, BlockView world, BlockPos pos, BlockState state, net.minecraft.fluid.Fluid fluid) {
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
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos pos = hit.getBlockPos();
        if (projectile.canModifyAt(world, pos) && projectile.isOnFire() && !state.get(LIT) && !state.get(WATERLOGGED)) {
            if (!world.isClient) {
                world.setBlockState(pos, state.with(CRACKED, true).with(LIT, true), 11);
            } else {
                for (int i = 0; i < 20; ++i) {
                    makeParticles(world, BlockPos.ofFloored(hit.getBlockPos().toCenterPos()));
                }
            }
        }
    }

    public static void makeParticles(World world, BlockPos pos) {
        Random random = world.getRandom();
        SimpleParticleType particleType = ParticleTypes.POOF;
        world.addImportantParticle(particleType, true, (double) pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1), (double) pos.getY() + random.nextDouble() + random.nextDouble(), (double) pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CRACKED, LIT, WATERLOGGED);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, net.minecraft.entity.ai.pathing.NavigationType type) {
        return false;
    }
}
