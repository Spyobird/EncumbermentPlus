package me.spyobird.encumberment;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import me.spyobird.encumberment.lib.ConfigurationHandler;
import me.spyobird.encumberment.lib.ModData;
import me.spyobird.encumberment.util.ModEventHooks;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModData.ID, name = ModData.NAME, version = ModData.VERSION, useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class EncumbermentPlus
{
	@Instance(ModData.ID)
	public static EncumbermentPlus instance = new EncumbermentPlus();
	
	public static Configuration config;
	
	//@SidedProxy(clientSide = "me.spyobird.ae.ClientProxy", serverSide = "me.spyobird.ae.CommonProxy")
	//public static CommonProxy proxy;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		Potion[] potionTypes = null;

	    for (Field f : Potion.class.getDeclaredFields())
	    {
	        f.setAccessible(true);
	        try
	        {
	            if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
	            {
	                Field modfield = Field.class.getDeclaredField("modifiers");
	                modfield.setAccessible(true);
	                modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

	                potionTypes = (Potion[])f.get(null);
	                final Potion[] modPotionTypes = new Potion[256];
	                System.arraycopy(potionTypes, 0, modPotionTypes, 0, potionTypes.length);
	                f.set(null, modPotionTypes);
	            }
	        } catch (Exception e)
	        {
	            System.err.println("Severe error, please report this to the mod author:");
	            System.err.println(e);
	        }
	    }

		config = new Configuration(new File(event.getModConfigurationDirectory(), "/Encumberment.cfg"));
		config.load();
		
		ConfigurationHandler.configSettings(config);
			
		if (config.hasChanged())
			config.save();
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{

	}
	
	@EventHandler
	public static void postLoad(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ModEventHooks());
	}
	
	public static EncumbermentPlus getInstance()
	{
		return instance;
	}
}
