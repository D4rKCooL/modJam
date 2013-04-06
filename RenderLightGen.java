package modJam;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderLightGen implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		renderer.setRenderBounds(0.35, 0.35, 0.35, 0.65, 0.65, 0.65);
		renderer.renderStandardBlock(block, x, y, z);
		renderAttachedHandles(world, x, y, z, block, modelId, renderer);
		return true;
	}

	public void renderAttachedHandles(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer){
		boolean[] map = getBlockSideMap(world, x, y, z);
		boolean[] thisMap = getBlockSideMap(world, x, y, z, block.blockID);
		if(map[0] && !thisMap[0]){
			renderer.setRenderBounds(0.45, -0.5, 0.45, 0.55, 0.35, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(map[1] && !thisMap[1]){
			renderer.setRenderBounds(0.45, 0.65, 0.45, 0.55, 1.5, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(map[2] && !thisMap[2]){
			renderer.setRenderBounds(-0.5, 0.45, 0.45, 0.35, 0.55, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(map[3] && !thisMap[3]){
			renderer.setRenderBounds(0.45, 0.45, 0.45, 1.5, 0.55, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(map[4] && !thisMap[4]){
			renderer.setRenderBounds(0.45, 0.45, -0.5, 0.55, 0.55, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(map[5] && !thisMap[5]){
			renderer.setRenderBounds(0.45, 0.45, 0.45, 0.55, 0.55, 1.5);
			renderer.renderStandardBlock(block, x, y, z);
		}
		if(thisMap[0]){
			renderer.setRenderBounds(0.45, 0, 0.45, 0.55, 0.35, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(thisMap[1]){
			renderer.setRenderBounds(0.45, 0.65, 0.45, 0.55, 1, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(thisMap[2]){
			renderer.setRenderBounds(0, 0.45, 0.45, 0.35, 0.55, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(thisMap[3]){
			renderer.setRenderBounds(0.45, 0.45, 0.45, 1, 0.55, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(thisMap[4]){
			renderer.setRenderBounds(0.45, 0.45, 0, 0.55, 0.55, 0.55);
			renderer.renderStandardBlock(block, x, y, z);
		}if(thisMap[5]){
			renderer.setRenderBounds(0.45, 0.45, 0.45, 0.55, 0.55, 1);
			renderer.renderStandardBlock(block, x, y, z);
		}
	}
	
	public boolean isBlockAir(IBlockAccess par1World, int par2, int par3, int par4){
		return par1World.getBlockId(par2, par3, par4) == 0;
	}
	
	public boolean[] getBlockSideMap(IBlockAccess par1World, int par2, int par3, int par4){
		boolean[] returnValue = new boolean[6];
		returnValue[0] = !isBlockAir(par1World, par2, par3 - 1, par4);
		returnValue[1] = !isBlockAir(par1World, par2, par3 + 1, par4);
		returnValue[2] = !isBlockAir(par1World, par2 - 1, par3, par4);
		returnValue[3] = !isBlockAir(par1World, par2 + 1, par3, par4);
		returnValue[4] = !isBlockAir(par1World, par2, par3, par4 - 1);
		returnValue[5] = !isBlockAir(par1World, par2, par3, par4 + 1);
		return returnValue;
	}
	
	public boolean[] getBlockSideMap(IBlockAccess par1World, int par2, int par3, int par4, int block){
		boolean[] returnValue = new boolean[6];
		returnValue[0] = !isBlockAir(par1World, par2, par3 - 1, par4) && par1World.getBlockId(par2, par3 - 1, par4) == block;
		returnValue[1] = !isBlockAir(par1World, par2, par3 + 1, par4) && par1World.getBlockId(par2, par3 + 1, par4) == block;
		returnValue[2] = !isBlockAir(par1World, par2 - 1, par3, par4) && par1World.getBlockId(par2 - 1, par3, par4) == block;
		returnValue[3] = !isBlockAir(par1World, par2 + 1, par3, par4) && par1World.getBlockId(par2 + 1, par3, par4) == block;
		returnValue[4] = !isBlockAir(par1World, par2, par3, par4 - 1) && par1World.getBlockId(par2, par3, par4 - 1) == block;
		returnValue[5] = !isBlockAir(par1World, par2, par3, par4 + 1) && par1World.getBlockId(par2, par3, par4 + 1) == block;
		return returnValue;
	}
	
	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxyModJam.lightGenRenderType;
	}

}
