package me.spyobird.encumberment.lib;

import java.util.HashMap;
import java.util.UUID;

public class PlayerWeightData
{
	public int encumberment;
	public int weight;
	public int weightMax;
	
	public static HashMap<UUID, PlayerWeightData> playerData = new HashMap<>();
	
	public PlayerWeightData (int encumberment, int weight, int weightmax)
	{
		this.weight = weight;
		this.weightMax = weight;
	}
}
