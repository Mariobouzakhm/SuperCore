package me.mariozgr8.supercore.homes;

import org.bukkit.Location;

public class HomeEntry {
	private String name;
	private Location loc;
	
	public HomeEntry(String name, Location loc) { 
		this.name = name;
		this.loc = loc;
	}
	
	public String getName() {
		return name;
	}
	public Location getLocation() {
		return loc;
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
}