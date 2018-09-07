package me.mariozgr8.supercore.users;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.data.SettingsManager;
import me.mariozgr8.supercore.stats.StatisticsEntry;
/**
 * Class that represent an implementation of an SPlayer
 * @author Mario
 *
 */
public class SPlayer {
	private static SettingsManager settings = SettingsManager.getInstance();
	/**
	 * UUID of SPlayer, Unique Id to each mincraft player
	 */
	private UUID uuid;
	/**
	 * Player that corresponds to the SPlayer
	 *
	 */
	private Player p;
	/**
	 * Instance of StatisticsEntry that represent player stats on the server
	 */
	private StatisticsEntry stats;
	
	/**
	 * Constructor that build an SPlayer based on passed UUID
	 * @param uuid
	 */
	public SPlayer(UUID uuid) {
		this.uuid = uuid;
		this.p = Bukkit.getServer().getPlayer(uuid);
		this.stats = new StatisticsEntry(uuid);
	}
	/**
	 * @return uuid of SPlayer
	 */
	public UUID getUUID() {
		return uuid;
	}
	/**
	 * @return player instance that correspond SPlayer
	 */
	public Player getPlayer() {
		return p;
	}
	/**
	 * @return StatisticsEntry instance containing all player related stats
	 */
	public StatisticsEntry getStats() {
		return stats;
	}
	public void saveInventory() {
		String path = uuid+".inventory.";
		for(int i = 0; i<p.getInventory().getSize(); i++) {
			settings.getPlayersDataConfig().set(path+i, p.getInventory().getItem(i));
		}
		settings.savePlayersDataConfig();
	}
	public void toggleInvModify() {
		boolean toggle = settings.getPlayersDataConfig().getBoolean(uuid+".inv-modified");
		settings.getPlayersDataConfig().set(uuid+".inv-modified", !toggle);
		settings.savePlayersDataConfig();
	}
	public Inventory loadInventory() {
		boolean flag = settings.getPlayersDataConfig().getBoolean(uuid+".inv-modified");
		Inventory inv = null;
		if(flag) {
			inv = Bukkit.getServer().createInventory(null, 9*4, p.getName()+"'s Inventory");
			String path = p.getName()+".inventory.";
			for(int i = 0; i<p.getInventory().getSize(); i++) {
				ItemStack it = settings.getPlayersDataConfig().getItemStack(path+i);
				inv.setItem(i, it);
			}
		}
		return inv;
	}
}
