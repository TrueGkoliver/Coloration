package com.gkoliver.coloration.core.registry;



import com.gkoliver.coloration.Coloration;
import com.gkoliver.coloration.common.block.PaintableBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ColorationBlock {
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, Coloration.MODID);

	public static final Block.Properties PAINTABLE_PROPERTIES = Block.Properties.create(Material.WOOD).notSolid();
	
	public static final RegistryObject<Block> PAINTABLE_BLOCK = BLOCKS.register("paintable_block", ()->new PaintableBlock(PAINTABLE_PROPERTIES));
	
	
}
