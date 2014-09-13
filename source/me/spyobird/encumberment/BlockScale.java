package me.spyobird.encumberment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockScale extends Block
{
	public BlockScale(int id)
    {
	    super(id, Material.circuits);
	    this.setCreativeTab(CreativeTabs.tabRedstone);
    }
}
