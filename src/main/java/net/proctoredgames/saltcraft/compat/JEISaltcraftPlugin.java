package net.proctoredgames.saltcraft.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

@JeiPlugin
public class JEISaltcraftPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.of(Saltcraft.MOD_ID, "jei_plugin");
    }
}
