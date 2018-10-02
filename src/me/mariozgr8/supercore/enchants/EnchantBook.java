package me.mariozgr8.supercore.enchants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.api.ItemStackBuilder;

public class EnchantBook {
	private IEnchant ench;
	private int level;
	
	public EnchantBook(IEnchant ench, int level) {
		this.ench = ench;
		this.level = level;
	}
	
	private String returnBookName() {
		String name = ench.getRarity().getName();
		ChatColor chat = ench.getRarity().getColor();
		
		return chat+name+" Enchantment Book";
	}
	public ItemStack buildBook() {
		ItemStack i = new ItemStackBuilder(Material.BOOK, 1)
				.setDisplayName(returnBookName())
				.addLore("")
				.addLore("")
				.addLore("")
				.build();
		return i;
	}

	public IEnchant getIEnchant() {
		return ench;
	}
	public int getLevel() {
		return level;
	}
}


