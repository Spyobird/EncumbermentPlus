package me.spyobird.encumberment.util;

import me.spyobird.encumberment.EncumbermentPlus;
import me.spyobird.encumberment.lib.ConfigurationHandler;
import me.spyobird.encumberment.lib.ModPotion;
import me.spyobird.encumberment.lib.PlayerWeightData;
import me.spyobird.encumberment.lib.Weight;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
		
		boolean encumberment;
		int weight;
		int weightMax;
		Weight data = new Weight();
		
		if (world.isRemote && entity != null)
		{
			if (entity instanceof EntityPlayer)
			{
				encumberment = false;
				weight = ConfigurationHandler.initialWeight;
				weightMax = ConfigurationHandler.maximumWeight;
				
				EntityPlayer player = (EntityPlayer) entity;
				
				if (player.capabilities.isCreativeMode == false)
				{						
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
						encumberment = true;
					}
					
					PlayerWeightData playerData = new PlayerWeightData(encumberment, weight, weightMax);
					PlayerWeightData.playerData.put(player.getUniqueID(), playerData);

					player.addExhaustion( (float) (0.00001 * weight));
				}
				
				if (encumberment == true)
				{
					player.addPotionEffect(new PotionEffect(ModPotion.encumbered.id, ConfigurationHandler.extraEncumberedDuration, 0));;
				}
				
				ModPotion.checkPotionEffects(player);
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
		{
			event.left.add(String.valueOf(PlayerWeightData.playerData.get(player.getUniqueID()).weight));
		}
	}
}

