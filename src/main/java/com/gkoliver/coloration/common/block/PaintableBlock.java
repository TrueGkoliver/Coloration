package com.gkoliver.coloration.common.block;

import com.gkoliver.coloration.common.item.ItemPaintroller;
import com.gkoliver.coloration.common.tile.PaintableTileEntity;


import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PaintableBlock extends ContainerBlock {

	public PaintableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new PaintableTileEntity();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		ItemStack stackIn = player.getHeldItem(handIn);
		if (!stackIn.isEmpty()) {
			if (stackIn.getItem() instanceof BlockItem) {
				PaintableTileEntity tileEntity = (PaintableTileEntity) worldIn.getTileEntity(pos);
				Block blockIn = Block.getBlockFromItem(stackIn.getItem());
				tileEntity.blockToBePlaced = blockIn.getStateForPlacement(blockIn.getDefaultState(), Direction.getFacingDirections(player)[0], blockIn.getDefaultState(), player.world, pos, player.getPosition(), handIn);
 				return ActionResultType.SUCCESS;
 			} else if (stackIn.getItem() instanceof ItemPaintroller) {
 				
 				PaintableTileEntity tileEntity = (PaintableTileEntity) worldIn.getTileEntity(pos);
 				ItemPaintroller roller = (ItemPaintroller) stackIn.getItem();
 				System.out.println(roller.getColor(stackIn));
 				tileEntity.color = roller.getColor(stackIn);
 				return ActionResultType.SUCCESS;
 			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		PaintableTileEntity tileEntity = (PaintableTileEntity) worldIn.getTileEntity(pos);
		if (tileEntity ==null) {
			return super.getRenderShape(state, worldIn, pos);
		} else if (tileEntity.blockToBePlaced==null) {
			System.out.println("GETBLOCK");
			return super.getRenderShape(state, worldIn, pos);
		} 
		else {
			System.out.println("ok this is firing");
			return tileEntity.blockToBePlaced.getBlock().getRenderShape(tileEntity.blockToBePlaced, worldIn,  pos);
		}
	}
	
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	
	

}
