package me.mariozgr8.supercore.objects;

import org.bukkit.Location;
import org.bukkit.Material;

public class WarpEntry {
	private String name;
	private short durability;
	private Material icon;
	private Location loc;
	
	public WarpEntry(String name, short durability, Material icon, Location loc) {
		this.name = name;
		this.durability = durability;
		this.icon = icon;
		this.loc = loc;
	}
	//Getters
	public String getName() {
		return name;
	}
	public Location getLocation() {
		return loc;
	}
	public Material getIcon() {
		return icon;
	}
	public short getDurabillity() {
		return durability;
	}
}
