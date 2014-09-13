package me.spyobird.encumberment.lib;

import net.minecraft.block.Block;
import me.spyobird.encumberment.BlockScale;
import cpw.mods.fml.common.registry.GameRegistry;

public class ObjectInitializer
{
	public static Block blockScale;
	
	public static void initializeBlocks()
	{
		blockScale = new BlockScale(BlockData.BLOCK_SCALE_ID).setUnlocalizedName(BlockData.BLOCK_SCALE_KEY);
		
		GameRegistry.registerBlock(blockScale, BlockData.BLOCK_SCALE_KEY);
	}
}
