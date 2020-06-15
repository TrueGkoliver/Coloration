package com.gkoliver.coloration.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;

public class MossBlock extends Block {
	public static final EnumProperty<EMossType> MOSS_TYPE = EnumProperty.create("moss_type", EMossType.class);
	public MossBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(MOSS_TYPE);
	}

}
