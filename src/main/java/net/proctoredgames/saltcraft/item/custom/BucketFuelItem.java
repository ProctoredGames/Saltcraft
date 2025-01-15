package net.proctoredgames.saltcraft.item.custom;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class BucketFuelItem extends BucketItem {
    private final int burnTime;

    public BucketFuelItem(RegistryObject<FlowingFluid> fluid, Properties pProperties, int burnTime) {
        super(fluid, pProperties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }
}
