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

    public static final RegistryObject<FlowingFluid> OIL = FLUIDS.register("oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.OIL_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_OIL = FLUIDS.register("flowing_oil",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties OIL_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.OIL_FLUID_TYPE, OIL, FLOWING_OIL)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.OIL_BLOCK)
            .bucket(ModItems.OIL_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}