package me.mariozgr8.supercore.homes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.data.SettingsManager;

public class HomeManager {
	private PermissionManager perms;

	private SettingsManager settings;
	
	public HomeManager(SuperCore sc) {
		perms = sc.getPerms();
		settings = SettingsManager.getInstance();
	}
	
	/**
	 * Method that return the maximum number of home of a player based on their permissions
	 * @param p: Instance of player
	 * @return int max number of homes a player can have, -1 if unlimited, 0 if none
	 */
	public int returnMaxPlayerHome(Player p) {
		if(p.hasPermission(perms.unlimitedHomePermission)) {
			return -1;
		}
		else if(p.hasPermission(perms.twentyfiveHomePermission)) {
			return 25;
		}
		else if(p.hasPermission(perms.twentyHomePermission)) {
			return 20;
		}
		else if(p.hasPermission(perms.fifteenHomePermission)) {
			return 15;
		}
		else if(p.hasPermission(perms.tenHomePermission)) {
			return 10;
		}
		else if(p.hasPermission(perms.nineHomePermission)) {
			return 9;
		}
		else if(p.hasPermission(perms.eightHomePermission)) {
			return 8;
		}
		else if(p.hasPermission(perms.sevenHomePermission)) {
			return 7;
		}
		else if(p.hasPermission(perms.sixHomePermission)) {
			return 6;
		}
		else if(p.hasPermission(perms.fiveHomePermission)) {
			return 5;
		}
		else if(p.hasPermission(perms.fourHomePermission)) {
			return 4;
		}
		else if(p.hasPermission(perms.threeHomePermission)) {
			return 3;
		}
		else if(p.hasPermission(perms.twoHomePermission)) {
			return 2;
		}
		else if(p.hasPermission(perms.oneHomePermission)) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public String getHomesMessage(List<HomeEntry> homes) {
		StringBuilder str = new StringBuilder();
		str.append("&6Homes: ");
		if(!homes.isEmpty()) {	
			for(HomeEntry home: homes) {
				str.append("&c"+home.getName()+", ");
			}
		}
		return str.toString();
	}
	public List<HomeEntry> getOfflinePlayerHomes(OfflinePlayer off) {
		List<HomeEntry> homes = new ArrayList<HomeEntry>();
		UUID uuid = off.getUniqueId();
		if(settings.getPlayersDataConfig().get(uuid.toString()+".homes") != null) {
			String path = uuid.toString()+".homes.";
			for(String name: settings.getPlayersDataConfig().getConfigurationSection(uuid.toString()+".homes").getKeys(false)) {
				double x = settings.getPlayersDataConfig().getDouble(path+name+".X");
				double y = settings.getPlayersDataConfig().getDouble(path+name+".Y");
				double z = settings.getPlayersDataConfig().getDouble(path+name+".Z");
				World w = Bukkit.getServer().getWorld(settings.getPlayersDataConfig().getString(path+name+".World"));
				
				homes.add(new HomeEntry(name, new Location(w, x, y, z)));
			}
		}
		return homes;
	}
	public HomeEntry getOfflinePlayerHome(OfflinePlayer off, String name) {
		HomeEntry home = null;
		UUID uuid = off.getUniqueId();
		String path = uuid.toString()+".homes."+name;
		if(settings.getPlayersDataConfig().get(path) != null) {
			double x = settings.getPlayersDataConfig().getDouble(path+".X");
			double y = settings.getPlayersDataConfig().getDouble(path+".Y");
			double z = settings.getPlayersDataConfig().getDouble(path+".Z");
			World w = Bukkit.getServer().getWorld(settings.getPlayersDataConfig().getString(path+".World"));
			
			home = new HomeEntry(name, new Location(w, x, y, z));
		}
		return home;
	}

}
