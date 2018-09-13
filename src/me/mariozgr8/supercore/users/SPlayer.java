package me.mariozgr8.supercore.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.data.SettingsManager;
import me.mariozgr8.supercore.homes.HomeEntry;
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
	 * List that hold instances of HomeEntry representing a player's home on the server
	 */
	private List<HomeEntry> homes;
	/**
	 * Constructor that build an SPlayer based on passed UUID
	 * @param uuid
	 */
	public SPlayer(UUID uuid) {
		this.uuid = uuid;
		this.p = Bukkit.getServer().getPlayer(uuid);
		this.stats = new StatisticsEntry(uuid);
		this.homes = new ArrayList<HomeEntry>();
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
	/**
	 * @return List<HomeEntry> containing the SPlayer's homes
	 */
	public List<HomeEntry> getHomes() {
		return homes;
	}
	/**
	 * @param name: String that represent the name of the home to be returned
	 * @return HomeEntry if home with passed name exists, null otherwise 
	 */
	public HomeEntry returnHomeFromName(String name) {
		for(HomeEntry home: homes) {
			if(home.getName().equalsIgnoreCase(name)) {
				return home;
			}
		}
		return null;
	}
	/**
	 * Method that file player inventory to playerdata.yml file
	 */
	public void saveInventory() {
		String path = uuid.toString()+".inventory.";
		for(int i = 0; i<p.getInventory().getSize(); i++) {
			if(p.getInventory().getItem(i) != null) {	
				settings.getPlayersDataConfig().set(path+i, p.getInventory().getItem(i));
			}
			else {
				settings.getPlayersDataConfig().set(path+i, null);
			}
		}
		settings.savePlayersDataConfig();
	}
	/**
	 * Methods that set the param "inv-modified" in the playerdata.yml config
	 * @param flag
	 */
	public void toggleInvModify(boolean flag) {
		settings.getPlayersDataConfig().set(uuid+".inv-modified", flag);
		settings.savePlayersDataConfig();
	}
	/**
	 * Method that load the player inventory from config if modified
	 * @return instance of Inventory if modified, otherwise null Inventory
	 */
	public Inventory loadInventory() {
		boolean flag = settings.getPlayersDataConfig().getBoolean(uuid.toString()+".inv-modified");
		Inventory inv = null;
		if(flag) {
			inv = Bukkit.getServer().createInventory(null, 9*4, p.getName()+"'s Inventory");
			String path = p.getUniqueId()+".inventory.";
			for(int i = 0; i < inv.getSize() - 1; i++) {
				ItemStack it = settings.getPlayersDataConfig().getItemStack(path+i);
				inv.setItem(i, it);
			}
		}
		return inv;
	}
}
