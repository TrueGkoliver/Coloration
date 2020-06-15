package com.gkoliver.coloration.common.item;

import com.gkoliver.coloration.common.tile.PaintableTileEntity;
import com.gkoliver.coloration.core.registry.ColorationBlock;

import net.minecraft.block.BlockState;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;

public class ItemPaintroller extends Item implements IDyeableArmorItem {

	public ItemPaintroller(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		BlockState state = context.getWorld().getBlockState(context.getPos());
		if (this.getColor(context.getPlayer().getHeldItem(context.getHand()))==0) {
			return super.onItemUse(context);
		} else if (context.getWorld().getTileEntity(context.getPos()) != null) {
			return super.onItemUse(context);
		} else {
			context.getWorld().setBlockState(context.getPos(), ColorationBlock.PAINTABLE_BLOCK.get().getDefaultState());
			TileEntity tileEntityIn = context.getWorld().getTileEntity(context.getPos());
			if (tileEntityIn instanceof PaintableTileEntity) {
				PaintableTileEntity tePaintable = (PaintableTileEntity) tileEntityIn; 
				tePaintable.setBlockToBePlaced(state);
				tePaintable.setColor(this.getColor(context.getPlayer().getHeldItem(context.getHand())));
			}
			return ActionResultType.SUCCESS;
		}
		
	}

}
