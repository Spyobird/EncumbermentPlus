package me.spyobird.encumberment.lib;

import java.util.HashMap;
import java.util.UUID;

public class PlayerWeightData
{
	public boolean encumberment;
	public int weight;
	public int weightMax;
	
	public static HashMap<UUID, PlayerWeightData> playerData = new HashMap<>();
	
	public PlayerWeightData (boolean encumberment, int weight, int weightmax)
	{
		this.encumberment = encumberment;
		this.weight = weight;
		this.weightMax = weight;
	}
}
