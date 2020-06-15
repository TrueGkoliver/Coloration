package com.gkoliver.coloration;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gkoliver.coloration.core.registry.ColorationBlock;
import com.gkoliver.coloration.core.registry.ColorationItem;
import com.gkoliver.coloration.core.registry.ColorationTileEntities;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Coloration.MODID)
@Mod.EventBusSubscriber(modid=Coloration.MODID)
public class Coloration
{
	public static final String MODID = "coloration";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Coloration() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        eventBus.addListener(this::setup);
        ColorationBlock.BLOCKS.register(eventBus);
        ColorationItem.ITEMS.register(eventBus);
        ColorationTileEntities.TILE_ENTITIES.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        
    }

}
