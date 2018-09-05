package me.mariozgr8.supercore.managers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.api.ItemStackBuilder;
import me.mariozgr8.supercore.objects.WarpEntry;

public class WarpsManager {
	private SuperCore sc;
	private MessageManager chat;
	private SettingsManager settings;
	
	//List of warps containing all of the server warps;
	private ArrayList<WarpEntry> warps;
	
	//Spawn Location
	private Location spawnLocation;
	
	public WarpsManager(SuperCore sc) {
		this.sc = sc;
		this.chat = this.sc.getMessages();
		this.settings = SettingsManager.getInstance();
		
		this.warps = loadWarps();
		this.spawnLocation = loadSpawn();
	}
	
	@SuppressWarnings("deprecation")
	private ArrayList<WarpEntry> loadWarps() {
		ArrayList<WarpEntry> warps = new ArrayList<WarpEntry>();
		if(settings.getLocationConfig().get("warps") != null) {
			for(String name: settings.getLocationConfig().getConfigurationSection("warps").getKeys(false)) {
				if(settings.getLocationConfig().get("warps."+name) != null) {
					double x = settings.getLocationConfig().getDouble("warps."+name+".x");
					double y = settings.getLocationConfig().getDouble("warps."+name+".y");
					double z = settings.getLocationConfig().getDouble("warps."+name+".z");
					World w = Bukkit.getServer().getWorld(settings.getLocationConfig().getString("warps."+name+".world"));
					Material m;
					short durability = 0;
					
					String material = settings.getLocationConfig().getString("warps."+name+".icon");
					if(material.contains(":")) {
						int index = material.indexOf(":");
						
						durability = Short.parseShort(material.substring(index + 1));
						material = material.substring(0, index);
					}
					try {
						m = Material.getMaterial(Integer.parseInt(material));
					}
					catch(NumberFormatException e) {
						m = Material.getMaterial(material.toUpperCase().replaceAll(" ", "_"));
					}
					
					warps.add(new WarpEntry(name, durability, m, new Location(w, x, y, z)));
				}
			}
		}
		return warps;
	}
	public void registerWarp(WarpEntry warp) {
		warps.add(warp);
	}
	public void unregisterWarp(WarpEntry warp) {
		warps.remove(warp);
	}
	public void saveWarp(WarpEntry warp) { 
		String path = "warps."+warp.getName()+".";
		
		settings.getLocationConfig().set(path+"x", warp.getLocation().getX());
		settings.getLocationConfig().set(path+"y", warp.getLocation().getY());
		settings.getLocationConfig().set(path+"z", warp.getLocation().getZ());
		settings.getLocationConfig().set(path+"world", warp.getLocation().getWorld().getName());
		
		settings.getLocationConfig().set(path+"icon", getMaterialString(warp.getDurabillity(), warp.getIcon()));
		settings.saveLocationConfig();
	}
	public void deleteWarp(WarpEntry warp) {
		String path = "warps."+warp.getName();
		
		settings.getLocationConfig().set(path, null);
		settings.saveLocationConfig();
	}
	public void saveWarps() {
		for(WarpEntry w:  warps) {
			saveWarp(w);
		}
	}
	private Location loadSpawn() {
		if(settings.getLocationConfig().get("spawn") != null) {
			double x = settings.getLocationConfig().getDouble("spawn.x");
			double y = settings.getLocationConfig().getDouble("spawn.y");
			double z = settings.getLocationConfig().getDouble("spawn.z");
			World w = Bukkit.getServer().getWorld(settings.getLocationConfig().getString("spawn.world"));
			
			return new Location(w, x, y, z);
		}
		else {
			return null;
		}
	}
	public void registerSpawn(Location loc) {
		settings.getLocationConfig().set("spawn.x", loc.getX());
		settings.getLocationConfig().set("spawn.y", loc.getY());
		settings.getLocationConfig().set("spawn.z", loc.getZ());
		settings.getLocationConfig().set("spawn.world", loc.getWorld().getName());
		settings.saveLocationConfig();
		
		spawnLocation = loc;
	}
	public static String getMaterialString(short durability, Material m) {
		StringBuilder str = new StringBuilder();
		str.append(m.toString());
		if(durability != 0) {
			str.append(":"+durability);
		}
		return str.toString();
	}
	
	//Search methods for warps
	public WarpEntry getWarpFromName(String name) {
		for(WarpEntry w: this.warps) {
			if(w.getName().equalsIgnoreCase(name)) {
				return w;
			}
		} 
		return null;
 	}
	public boolean checkNameValidation(String name) {
		Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		Matcher m = special.matcher(name);
		
		return !m.find();
	}
	
	//Inventory
	public Inventory getWarpsInv(int page, Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, returnInvSize(), chat.getWarpsInvName());
		int maxSize = 27;
		int currentPlaceInInv = 0;
		
		int i = (0 +((page-1) * maxSize));
		int maxCapacity = (page*maxSize) -1;
		int maxPage = (warps.size() / maxCapacity) + 1;
		try {
			while(i<=maxCapacity) {
				inv.setItem(currentPlaceInInv, getWarpIcon(warps.get(i), p));
				currentPlaceInInv++;
				i++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(page < maxPage) {
			inv.setItem(34, getNextPage());
			inv.setItem(31, getCurrentPage(page));
		}
		if(page > 1) {
			inv.setItem(28, getPreviousPage());
			inv.setItem(31, getCurrentPage(page));
		}
		return inv;
	}
	public ItemStack getWarpIcon(WarpEntry w, Player p) {
		ItemStack i = new ItemStackBuilder(w.getIcon(), 1).setDisplayName(this.chat.getWarpItemName().replace("%warp_name%", w.getName()))
				.addLore((p.hasPermission("supercore.warp."+w.getName())|| p.hasPermission("supercore.warp.*") ? "" : chat.getNoPermsLore())).build();
		return i;
	}
	public ItemStack getNextPage() {
		ItemStack i = new ItemStackBuilder(Material.PAPER).setDisplayName("&7Next Page").build();
		return i;
	}
	public ItemStack getPreviousPage() {
		ItemStack i = new ItemStackBuilder(Material.PAPER).setDisplayName("&7Previous Page").build();
		return i;
	}
	public ItemStack getCurrentPage(int page) {
		ItemStack i = new ItemStackBuilder(Material.COMPASS).setDisplayName("&7Current Page: "+page).build();
		return i;
	}
	public int returnInvSize() {
		return (warps.size() > 27) ? 36: 27;
	}
	//Getters
	public Location getSpawnLoc() {
		return spawnLocation;
	}
	public ArrayList<WarpEntry> getWarps() {
		return warps;
	}
}
