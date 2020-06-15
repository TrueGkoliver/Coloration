package com.gkoliver.coloration.common.block;

import net.minecraft.util.IStringSerializable;

public enum EMossType implements IStringSerializable{
	NORMAL("normal"),
	WARPED("warped"),
	CRIMSON("crimson");
	private final String name;
	EMossType(String name) {
		this.name=name;
	}
	@Override
	public String getName() {
		return this.name;
	}

}
