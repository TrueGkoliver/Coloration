package com.gkoliver.coloration.client.render.block;

import java.util.List;
import java.util.Random;

import com.gkoliver.coloration.common.tile.PaintableTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.matrix.MatrixStack.Entry;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ILightReader;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.IModelData;

public class ColoredBlockModelRenderer extends BlockModelRenderer {

	public ColoredBlockModelRenderer() {
		super(Minecraft.getInstance().getBlockColors());
	}
	public static int getColor(BlockPos pos, ILightReader worldIn) {
		TileEntity tileEntityIn = worldIn.getTileEntity(pos);
		if (tileEntityIn instanceof PaintableTileEntity) {
			PaintableTileEntity fullMossy = (PaintableTileEntity) tileEntityIn;
			return fullMossy.color;
		}
		return 0;
	}
	@Override
	public void renderQuadSmooth(ILightReader blockAccessIn, BlockState stateIn, BlockPos posIn, IVertexBuilder buffer,
			Entry matrixEntry, BakedQuad quadIn, float colorMul0, float colorMul1, float colorMul2, float colorMul3,
			int brightness0, int brightness1, int brightness2, int brightness3, int combinedOverlayIn) {
		float f;
	      float f1;
	      float f2;
	      int i = getColor(posIn, blockAccessIn);
	      System.out.println(getColor(posIn, blockAccessIn));
	      f = (float)(i >> 16 & 255) / 255.0F;
	      f1 = (float)(i >> 8 & 255) / 255.0F;
	      f2 = (float)(i & 255) / 255.0F;
	      // FORGE: Apply diffuse lighting at render-time instead of baking it in
	      if (quadIn.shouldApplyDiffuseLighting()) {
	         // TODO this should be handled by the forge lighting pipeline
	         float l = net.minecraftforge.client.model.pipeline.LightUtil.diffuseLight(quadIn.getFace());
	         f *= l;
	         f1 *= l;
	         f2 *= l;
	      }

	      buffer.addQuad(matrixEntry, quadIn, new float[]{colorMul0, colorMul1, colorMul2, colorMul3}, f, f1, f2, new int[]{brightness0, brightness1, brightness2, brightness3}, combinedOverlayIn, true);
	}
	@Override
	public void renderModel(Entry matrixEntry, IVertexBuilder buffer, BlockState state, IBakedModel modelIn, float red,
			float green, float blue, int combinedLightIn, int combinedOverlayIn, IModelData modelData) {
		Random random = new Random();
	      long i = 42L;

	      for(Direction direction : Direction.values()) {
	         random.setSeed(42L);
	         renderModelBrightnessColorQuads(matrixEntry, buffer, red, green, blue, modelIn.getQuads(state, direction, random, modelData), combinedLightIn, combinedOverlayIn);
	      }

	      random.setSeed(42L);
	      renderModelBrightnessColorQuads(matrixEntry, buffer, red, green, blue, modelIn.getQuads(state, (Direction)null, random, modelData), combinedLightIn, combinedOverlayIn);
	}
	public static void renderModelBrightnessColorQuads(MatrixStack.Entry matrixEntry, IVertexBuilder buffer, float red, float green, float blue, List<BakedQuad> listQuads, int combinedLightIn, int combinedOverlayIn) {
	      for(BakedQuad bakedquad : listQuads) {
	         float f;
	         float f1;
	         float f2;
             f = MathHelper.clamp(red, 0.0F, 1.0F);
             f1 = MathHelper.clamp(green, 0.0F, 1.0F);
             f2 = MathHelper.clamp(blue, 0.0F, 1.0F);

	         buffer.addQuad(matrixEntry, bakedquad, f, f1, f2, combinedLightIn, combinedOverlayIn);
	      }

	   }
	
	

}
