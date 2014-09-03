package me.spyobird.encumberment.lib;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class ModPotion extends Potion
{
	public static Potion encumbered = new ModPotion(33, true, 16717021).setPotionName("encumberment.potionEncumberment").setIconIndex(0, 0);
	
	public ModPotion(int id, boolean isBad, int colourMult)
    {
	    super(id, isBad, colourMult);
    }
	
	public static void checkPotionEffects(EntityPlayer player)
	{
		if (player.isPotionActive(encumbered))
		{
			if(player.getActivePotionEffect(encumbered).duration == 0)
			{
				player.removePotionEffect(encumbered.id);
			}
			
			player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setAttribute(0);
		}
		else
		{
			player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setAttribute(0.1);
		}
	}
}
