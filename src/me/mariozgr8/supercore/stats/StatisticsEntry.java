package me.mariozgr8.supercore.stats;

import java.util.UUID;

import me.mariozgr8.supercore.data.SettingsManager;

public class StatisticsEntry {
	private static SettingsManager settings = SettingsManager.getInstance();
	
	private UUID uuid;
	private long timeOnServer;
	private long timeSinceLastDeath;
	
	private int blocksBroken;
	private int blocksPlaced;
	
	private double damageDealt;
	private double damageRecieved;
	
	private int mobsKilled;
	private int playersKilled;
	
	
	public StatisticsEntry(UUID uuid) {
		this.uuid = uuid;
		this.loadStats();
	}
	private void loadStats() {
		//Add the Missing stats to the player
		if(settings.getStatsConfig().get(uuid.toString()+".timeplayed") == null) {
			settings.getStatsConfig().set(uuid.toString()+".timeplayed", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".timesincelastdeath") == null) {
			settings.getStatsConfig().set(uuid.toString()+".timesincelastdeath", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".blocksbroken") == null) {
			settings.getStatsConfig().set(uuid.toString()+".blocksbroken", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".blocksplaced") == null) {
			settings.getStatsConfig().set(uuid.toString()+".blocksplaced", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".damagedealt") == null) {
			settings.getStatsConfig().set(uuid.toString()+".damagedealt", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".damagerecieved") == null) {
			settings.getStatsConfig().set(uuid.toString()+".damagerecieved", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".mobskilled") == null) {
			settings.getStatsConfig().set(uuid.toString()+".mobskilled", "0");
		}
		if(settings.getStatsConfig().get(uuid.toString()+".playerskilled") == null) {
			settings.getStatsConfig().set(uuid.toString()+".playerskilled", "0");
		}
		
		settings.saveStatsConfig();
		
		//Load the player stats
		this.timeOnServer = settings.getStatsConfig().getLong(uuid.toString()+".timeplayed");
		this.timeSinceLastDeath = settings.getStatsConfig().getLong(uuid.toString()+".timesincelastdeath");
		this.blocksBroken = settings.getStatsConfig().getInt(uuid.toString()+".blocksbroken");
		this.blocksPlaced = settings.getStatsConfig().getInt(uuid.toString()+".blocksplaced");
		this.damageDealt = settings.getStatsConfig().getDouble(uuid.toString()+".damagedealt");
		this.damageRecieved = settings.getStatsConfig().getDouble(uuid.toString()+".damagerecieved");
		this.mobsKilled = settings.getStatsConfig().getInt(uuid.toString()+".mobskilled");
		this.playersKilled = settings.getStatsConfig().getInt(uuid.toString()+".playerskilled");
 	}
	public void saveStats() {
		//Save the player stats to the .yml file
		settings.getStatsConfig().set(uuid.toString()+".timeplayed", timeOnServer);
		settings.getStatsConfig().set(uuid.toString()+".timesincelastdeath", timeSinceLastDeath);
		settings.getStatsConfig().set(uuid.toString()+".blocksbroken", blocksBroken);
		settings.getStatsConfig().set(uuid.toString()+".blocksplaced", blocksPlaced);
		settings.getStatsConfig().set(uuid.toString()+".damagedealt", damageDealt);
		settings.getStatsConfig().set(uuid.toString()+".damagerecieved", damageRecieved);
		settings.getStatsConfig().set(uuid.toString()+".mobskilled", mobsKilled);
		settings.getStatsConfig().set(uuid.toString()+".playerskilled", playersKilled);
		settings.saveStatsConfig();
	}
	
	//Methods that return the statistics of the player !
	public UUID getUuid() {
		return uuid;
	}
	//Statistics Getters
	public long getTimeOnServer() {
		return timeOnServer;
	}
	public long getTimeSinceLastDeath() {
		return timeSinceLastDeath;
	}
	public int getBlocksBroken() {
		return blocksBroken;
	}
	public int getBlocksPlaced() {
		return blocksPlaced;
	}
	public double getDamageDealt() {
		return damageDealt;
	}
	public double getDamageRecieved() {
		return damageRecieved;
	}
	public int getMobsKilled() {
		return mobsKilled;
	}
	//Methods used to increase/adjust the stats
	public int getPlayersKilled() {
		return playersKilled;
	}
	public void incrementTime() {
		this.timeOnServer = this.timeOnServer + 1;
		this.timeSinceLastDeath = this.timeSinceLastDeath + 1;
	}
	public void incrementBlocksBroken() {
		this.blocksBroken += 1;
	}
	public void incrementBlocksPlaced() {
		this.blocksPlaced += 1;
	}
	public void incrementMobsKilled() {
		this.mobsKilled += 1;
	}
	public void incrementDamageDealt(double amount) {
		this.damageDealt += amount;
	}
	public void incrementDamageRecieved(double amount) {
		this.damageRecieved += amount;
	}
	public void incrementPlayersKilled() {
		this.playersKilled += 1;
	}
	public void playerDeath() {
		this.timeSinceLastDeath = 0;
	}	
}
