package net.proctoredgames.saltcraft.fluid;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluids;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Saltcraft.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_OIL = FLUIDS.register("oil_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.OIL_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_OIL = FLUIDS.register("flowing_oil_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_SALT_WATER = FLUIDS.register("salt_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SALT_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SALT_WATER = FLUIDS.register("flowing_salt_water_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SALT_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_PINK_SALT_WATER = FLUIDS.register("pink_salt_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.PINK_SALT_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_PINK_SALT_WATER = FLUIDS.register("flowing_pink_salt_water_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.PINK_SALT_WATER_PROPERTIES));


    public static final ForgeFlowingFluid.Properties OIL_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.OIL_FLUID_TYPE, SOURCE_OIL, FLOWING_OIL)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.OIL_BLOCK)
            .bucket(ModItems.OIL_BUCKET);

    public static final ForgeFlowingFluid.Properties SALT_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SALT_WATER_FLUID_TYPE, SOURCE_SALT_WATER, FLOWING_SALT_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.SALT_WATER_BLOCK)
            .bucket(ModItems.SALT_WATER_BUCKET);

    public static final ForgeFlowingFluid.Properties PINK_SALT_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.PINK_SALT_WATER_FLUID_TYPE, SOURCE_PINK_SALT_WATER, FLOWING_PINK_SALT_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.PINK_SALT_WATER_BLOCK)
            .bucket(ModItems.PINK_SALT_WATER_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}