package me.mariozgr8.supercore.homes;

import org.bukkit.Location;

import me.mariozgr8.supercore.users.SPlayer;

public class HomeEntry {
	private String name;
	private Location loc;
	private SPlayer sp;
	
	public HomeEntry(String name, Location loc, SPlayer sp) { 
		this.name = name;
		this.loc = loc;
		this.sp = sp;
	}
	
	public String getName() {
		return name;
	}
	public Location getLocation() {
		return loc;
	}
	public SPlayer getSP() {
		return sp;
	}
}