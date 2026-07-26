package net.proctoredgames.saltcraft.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.fluid.custom.OilFluid;

public class ModFluids {
    public static final FlowableFluid OIL = register("oil", new OilFluid.Still());
    public static final FlowableFluid FLOWING_OIL = register("flowing_oil", new OilFluid.Flowing());

    private static <T extends Fluid> T register(String name, T fluid) {
        return Registry.register(Registries.FLUID, Identifier.of(Saltcraft.MOD_ID, name), fluid);
    }

    public static void register() {
    }
}
