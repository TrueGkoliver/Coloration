package com.gkoliver.coloration.client.render.block;

import com.gkoliver.coloration.common.tile.PaintableTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.data.EmptyModelData;

public class PaintBlockModelRenderer extends TileEntityRenderer<PaintableTileEntity> {
	public static ColoredBlockModelRenderer renderer = new ColoredBlockModelRenderer();
	public void renderBlock(BlockRendererDispatcher dispatcherIn, int color, BlockState blockStateIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferTypeIn, int combinedLightIn, int combinedOverlayIn, net.minecraftforge.client.model.data.IModelData modelData) {
	      BlockRenderType blockrendertype = blockStateIn.getRenderType();
	      if (blockrendertype != BlockRenderType.INVISIBLE) {
	         switch(blockrendertype) {
	         case MODEL:
	            IBakedModel ibakedmodel = dispatcherIn.getModelForState(blockStateIn);
	            float f = (float)(color >> 16 & 255) / 255.0F;
	            float f1 = (float)(color >> 8 & 255) / 255.0F;
	            float f2 = (float)(color & 255) / 255.0F;
	            renderer.renderModel(matrixStackIn.getLast(), bufferTypeIn.getBuffer(RenderTypeLookup.getRenderType(blockStateIn)), blockStateIn, ibakedmodel, f, f1, f2, combinedLightIn, combinedOverlayIn, modelData);
	            break;
	         case ENTITYBLOCK_ANIMATED:
	            ItemStack stack = new ItemStack(blockStateIn.getBlock());
	            stack.getItem().getItemStackTileEntityRenderer().render(stack, matrixStackIn, bufferTypeIn, combinedLightIn, combinedOverlayIn);
	         }

	      }
	   }
	public PaintBlockModelRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(PaintableTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		BlockRendererDispatcher dispatcherIn = Minecraft.getInstance().getBlockRendererDispatcher();
		renderBlock(dispatcherIn, tileEntityIn.color, tileEntityIn.blockToBePlaced, matrixStackIn, bufferIn, 15728688, combinedOverlayIn, EmptyModelData.INSTANCE);
		
		
		
		//Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ZoomyBlocks.MOSS_BLOCK.get().getDefaultState(), matrixStackIn, bufferIn, 15728688, combinedOverlayIn);
	}

}
