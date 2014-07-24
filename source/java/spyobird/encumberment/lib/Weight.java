package java.spyobird.encumberment.lib;

import java.spyobird.encumberment.util.ICustomWeight;
import java.util.HashMap;

import net.minecraft.block.Block;

public class Weight
{
	HashMap<Integer, Integer> customBlockWeightValues = new HashMap<>();
	
	public int getBlockWeight(Block block)
	{
		int id = block.blockID;
		if (id <= 173)
		{
			switch(id)
			{
				case 1:
					return 1000;
				case 2:
					return 425;
				case 3:
					return 400;
				case 4:
					return 900;
				default:
					return 1000;
			}
		}
		else
		{
			return this.isCustomBlockValid(block) ? customBlockWeightValues.get(id) : 1000;
		}
	}
	
	public void setCustomBlockWeight(Block block)
	{
		int id = block.blockID;
		if (block instanceof ICustomWeight)
			customBlockWeightValues.put(id, ( (ICustomWeight) block).getCustomWeight());
	}
	
	public boolean isCustomBlockValid(Block block)
	{
		int id = block.blockID;
		return block instanceof ICustomWeight ? customBlockWeightValues.containsKey(id) : false;
	}
}
