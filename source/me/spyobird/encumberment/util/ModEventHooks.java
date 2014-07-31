package me.spyobird.encumberment.util;

import me.spyobird.encumberment.lib.PlayerWeightData;
import me.spyobird.encumberment.lib.Weight;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModEventHooks
{
	@ForgeSubscribe
	public void onUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		World world = entity.worldObj;
		
		int encumberment;
		int weight;
		int weightMax;
		double modifier;
		Weight data = new Weight();
		
		if (world.isRemote && entity != null)
		{
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) entity;
				
				if (player.capabilities.isCreativeMode == false)
				{					
					encumberment = 0;
					weight = 0;
					weightMax = 30000;
					
					if (PlayerWeightData.playerData.containsKey(player.getUniqueID()))
					{
						weightMax = PlayerWeightData.playerData.get(player.getUniqueID()).weightMax;
					}
					
					for (Object object : player.inventoryContainer.inventorySlots)
					{
						Slot slot = (Slot) object;
						
						if (slot.getHasStack())
						{
							ItemStack stack = slot.getStack();
							Block block = null;
							Item item = null;
							int holder = 0;
						
							try
							{
								block = Block.blocksList[stack.getItem().itemID];
							}
							catch (Exception e) {}
						
							if (block != null)
							{
								holder = data.getBlockWeight(block) * stack.stackSize;
							}
							else
							{
								try
								{
									item = item.itemsList[stack.getItem().itemID];
								}
								catch (Exception e) {}
								
								//data.getItemWeight(item);
							}
							
							weight += holder;
						}
					}
					
					if (weight > weightMax)
					{
						encumberment = 100;
					}
					else if (weight > (Math.round(weightMax * 3 / 4)))
					{
						encumberment = Math.round((weight - Math.round(weightMax * 3 / 4)) * 100 / Math.round(weightMax * 3 / 4));
					}
					else if (weight < (Math.round(weightMax / 4)))
					{
						encumberment = - Math.round((Math.round(weightMax / 4) - weight) * 100 / Math.round(weightMax / 4));
					}
					else
					{
						encumberment = 0;
					}
					
					PlayerWeightData playerData = new PlayerWeightData(encumberment, weight, weightMax);
					PlayerWeightData.playerData.put(player.getUniqueID(), playerData);
					
					if (encumberment >= 100)
					{
						player.setVelocity(0, 0, 0);
					}
					else
					{
						modifier = (100 - encumberment) / 100;
						player.motionX *= (modifier - 1) * 0.8 + 1;
						player.motionZ *= (modifier - 1) * 0.8 + 1;
					}
					
					player.addExhaustion( (float) (0.00001 * weight));
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onRenderOverlay(RenderGameOverlayEvent.Text event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if (PlayerWeightData.playerData.containsKey(player.getUniqueID()))
			event.left.add(String.valueOf(PlayerWeightData.playerData.get(player.getUniqueID()).weight));
	}
}

