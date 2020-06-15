package com.gkoliver.coloration.core.registry;

import com.gkoliver.coloration.Coloration;
import com.gkoliver.coloration.common.item.ItemPaintroller;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ColorationItem {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, Coloration.MODID);
	public static final Item.Properties PAINTROLLER_PROP = new Item.Properties().group(ItemGroup.MISC);
	public static final RegistryObject<ItemPaintroller> PAINTROLLER = ITEMS.register("paintroller", ()->new ItemPaintroller(PAINTROLLER_PROP));
}
