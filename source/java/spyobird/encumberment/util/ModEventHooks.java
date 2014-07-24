package java.spyobird.encumberment.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ModEventHooks
{
	@ForgeSubscribe
	public void onUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		World world = entity.worldObj;
		
		if (world.isRemote && entity != null)
		{
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) entity;
				
				if (player.capabilities.isCreativeMode != false)
				{
					double encumberment = 0;
					int weight = 0;
					int weightMax = 30000;
					for (int i = 0; i <= 35; i++)
					{
						ItemStack stack = player.inventory.getStackInSlot(i);
					}
				
					Block block = null;

				}
			}
		}
	}
}

