package com.gkoliver.coloration.core.event;

import com.gkoliver.coloration.client.render.block.PaintBlockModelRenderer;
import com.gkoliver.coloration.common.tile.PaintableTileEntity;
import com.gkoliver.coloration.core.registry.ColorationBlock;
import com.gkoliver.coloration.core.registry.ColorationItem;
import com.gkoliver.coloration.core.registry.ColorationTileEntities;
import com.gkoliver.coloration.Coloration;


import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
@Mod.EventBusSubscriber(modid=Coloration.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEvents {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		//ClientRegistry.bindTileEntityRenderer(ZoomyTileEntities.INTERDICTOR.get(), InterdictorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(ColorationTileEntities.PAINTABLE.get(), PaintBlockModelRenderer::new);
	}
	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item event) {
		event.getItemColors().register((stack, tint)->{
			return tint > 0 ? -1 : ColorationItem.PAINTROLLER.get().getColor(stack);
			//return ColorationItem.PAINTROLLER.get().getColor(stack);
		}, ColorationItem.PAINTROLLER.get());
	}
	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block event) {
		event.getBlockColors().register((block, reader, pos, integers)->{
			PaintableTileEntity te = (PaintableTileEntity) reader.getTileEntity(pos);
			return te.color;
		}, ColorationBlock.PAINTABLE_BLOCK.get());
	}

}
