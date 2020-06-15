package com.gkoliver.coloration.common.tile;

import com.gkoliver.coloration.core.registry.ColorationTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class PaintableTileEntity extends TileEntity {
	public BlockState blockToBePlaced = Blocks.CHISELED_STONE_BRICKS.getDefaultState();
	public int color = 0;
	public PaintableTileEntity() {
		super(ColorationTileEntities.PAINTABLE.get());
	}
	public void setColor(int color) {
		this.color = color;
		this.write(getTileData());
	}
	
	public int getColor() {
		return color;
	}
	
	public void setBlockToBePlaced(BlockState blockToBePlaced) {
		this.blockToBePlaced = blockToBePlaced; 
		this.write(getTileData());
	}
	public BlockState getBlockToBePlaced() {
		return blockToBePlaced;
	}
	@Override
	public void read(CompoundNBT compound) {
		CompoundNBT block = (CompoundNBT) compound.get("block");
		String blockID = block.getString("id");
		blockToBePlaced = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockID)).getDefaultState();
		color = block.getInt("color");
		super.read(compound);
	}
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		CompoundNBT blockData = new CompoundNBT();
		blockData.putString("id", blockToBePlaced.getBlock().getRegistryName().toString());
		compound.put("block", blockData);
		compound.putInt("color", this.color);
		return compound;
	}
	
	@Override
	public void remove() {
		Item itemIn = Item.getItemFromBlock(blockToBePlaced.getBlock());
		ItemEntity itemEntity = new ItemEntity(world, this.getPos().getX(), this.getPos().getY(),this.getPos().getZ(), new ItemStack(itemIn, 1));
		super.remove();
	}
	
	

}
