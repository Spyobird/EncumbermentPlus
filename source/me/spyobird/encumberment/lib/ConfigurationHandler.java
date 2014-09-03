package me.spyobird.encumberment.lib;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
	public static final String CATAGORY_SETTINGS = "Settings";
	
	public static int initialWeight;
	public static int maximumWeight;
	public static int extraEncumberedDuration;
	
	public static void configSettings(Configuration config)
	{
		initialWeight = config.get(CATAGORY_SETTINGS, "initialWeight", 0, "weight when the player is not carrying anything").getInt();
		maximumWeight = config.get(CATAGORY_SETTINGS, "maximumWeight", 30000, "maximum weight the player can carry before being encumbered").getInt();
		extraEncumberedDuration = config.get(CATAGORY_SETTINGS, "extraEncumberedDuration", 20, "extra time in game ticks where the player cannot move after the player stops being encumbered").getInt();
	}
}
