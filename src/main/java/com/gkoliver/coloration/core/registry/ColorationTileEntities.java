package com.gkoliver.coloration.core.registry;

import com.gkoliver.coloration.Coloration;
import com.gkoliver.coloration.common.tile.PaintableTileEntity;
import com.google.common.collect.Sets;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ColorationTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, Coloration.MODID);

	public static final RegistryObject<TileEntityType<PaintableTileEntity>> PAINTABLE =  TILE_ENTITIES.register("paintable", ()->new TileEntityType<>(PaintableTileEntity::new, Sets.newHashSet(ColorationBlock.PAINTABLE_BLOCK.get()), null));



}
