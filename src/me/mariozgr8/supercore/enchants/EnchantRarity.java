package me.mariozgr8.supercore.enchants;

import org.bukkit.ChatColor;

public enum EnchantRarity {
	COMMON("Common", ChatColor.GREEN), RARE("Rare", ChatColor.BLUE), LEGENDARY("LEGENDARY", ChatColor.RED);
	
	private String name;
	private ChatColor color;
	
	private EnchantRarity(String name, ChatColor color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}
	public ChatColor getColor() {
		return color;
	}
	
	

}
