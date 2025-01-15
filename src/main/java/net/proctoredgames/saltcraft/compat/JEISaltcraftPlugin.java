package net.proctoredgames.saltcraft.compat;

import mezz.jei.api.IModPlugin;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.Saltcraft;

public class JEISaltcraftPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Saltcraft.MOD_ID, "jei_plugin");
    }
}
