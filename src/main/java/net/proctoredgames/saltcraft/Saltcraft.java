package net.proctoredgames.saltcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.client.CrystidRenderer;
import net.proctoredgames.saltcraft.entity.client.FlamingoRenderer;
import net.proctoredgames.saltcraft.entity.client.JellyfishRenderer;
import net.proctoredgames.saltcraft.entity.client.SaltMageRenderer;
import net.proctoredgames.saltcraft.entity.client.MirageRenderer;
import net.proctoredgames.saltcraft.fluid.ModFluidTypes;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.item.ModCreativeModeTabs;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.potion.ModPotions;
import net.proctoredgames.saltcraft.util.ModItemProperties;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;
import net.proctoredgames.saltcraft.worldgen.feature.ModFeatures;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Saltcraft.MOD_ID)
public class Saltcraft
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "saltcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Saltcraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModEntities.register(modEventBus);

        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);

        ModFeatures.register(modEventBus);


        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->{
//            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });

        ModMessages.register();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.JELLYFISH.get(), JellyfishRenderer::new);
            EntityRenderers.register(ModEntities.CRYSTID.get(), CrystidRenderer::new);
            EntityRenderers.register(ModEntities.SALT_MAGE.get(), SaltMageRenderer::new);
            EntityRenderers.register(ModEntities.FLAMINGO.get(), FlamingoRenderer::new);
            EntityRenderers.register(ModEntities.MIRAGE.get(), MirageRenderer::new);
            ModItemProperties.addCustomItemProperties();
        }
    }
}
