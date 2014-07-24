package me.spyobird.encumberment;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import me.spyobird.encumberment.lib.ModData;
import me.spyobird.encumberment.lib.PlayerWeightData;
import me.spyobird.encumberment.util.ModEventHooks;
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
	
	public static HashMap<UUID, PlayerWeightData> playerData = new HashMap<>();
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		config = new Configuration(new File(event.getModConfigurationDirectory(), "/Encumberment.cfg"));
		config.load();
			
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
