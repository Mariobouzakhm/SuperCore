package me.mariozgr8.supercore.objects;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SPlayer {
	private UUID uuid;
	private Player p;
	private StatisticsEntry stats;
	
	public SPlayer(UUID uuid) {
		this.uuid = uuid;
		this.p = Bukkit.getServer().getPlayer(uuid);
		this.stats = new StatisticsEntry(uuid);
	}
	public UUID getUUID() {
		return uuid;
	}
	public Player getPlayer() {
		return p;
	}
	public StatisticsEntry getStats() {
		return stats;
	}
	
}
