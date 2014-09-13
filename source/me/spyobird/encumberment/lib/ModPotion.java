package me.spyobird.encumberment.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModPotion extends Potion
{
	public static Potion encumbered = new ModPotion(33, true, 16717021).setPotionName("encumberment.potionEncumbered").setIconIndex(0, -11);
	public static Potion vitality = new ModPotion(34, true, 16130975).setPotionName("encumberment.potionVitality").setIconIndex(1, -11);
	
	public ModPotion(int id, boolean isBad, int colourMult)
    {
	    super(id, isBad, colourMult);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModData.TEXTURE_LOCATION, "textures/gui/Icons.png"));
		return true;
	}
}
