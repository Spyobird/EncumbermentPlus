package me.spyobird.encumberment.lib;

import java.util.HashMap;

import me.spyobird.encumberment.util.ICustomWeight;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Weight
{	
	HashMap<Integer, Integer> customBlockWeightValues = new HashMap<>();
	HashMap<Integer, Integer> customItemWeightValues = new HashMap<>();
	
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
	
	/*public int getItemWeight(Item item)
	{
		int id = item.itemID;
		if (id <= 165 || (id >= 2000 && id <= 2011))
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
			return this.isCustomItemValid(item) ? customItemWeightValues.get(id) : 1000;
		}
	}
	
	public void setCustomItemWeight(Item item)
	{
		int id = item.itemID;
		if (item instanceof ICustomWeight)
			customItemWeightValues.put(id, ( (ICustomWeight) item).getCustomWeight());
	}
	
	public boolean isCustomItemValid(Item item)
	{
		int id = item.itemID;
		return item instanceof ICustomWeight ? customItemWeightValues.containsKey(id) : false;
	}*/
}
