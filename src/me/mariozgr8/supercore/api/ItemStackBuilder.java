package me.mariozgr8.supercore.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
	private ItemStack i;
	private ItemMeta imeta;
	
	public ItemStackBuilder(ItemStack i) {
		this.i = i;
		this.imeta = i.getItemMeta();
	}
	public ItemStackBuilder(Material m) {
		this.i = new ItemStack(m, 1);
		this.imeta = i.getItemMeta();
	}
	public ItemStackBuilder(Material m, int amount) {
		this.i = new ItemStack(m, amount);
		this.imeta = i.getItemMeta();
	}
	public ItemStackBuilder(Material m, short durability) {
		this.i = new ItemStack(m, 1, durability);
		this.imeta = i.getItemMeta();
	}
	public ItemStackBuilder(Material m, int amount, short durability) {
		this.i = new ItemStack(m, amount, durability);
		this.imeta = i.getItemMeta();
	}
	
	public ItemStackBuilder setDisplayName(String name) {
		imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		return this;
	}
	public ItemStackBuilder setAmount(int amount) {
		i.setAmount(amount);
		return this;
	}
	public ItemStackBuilder setDurability(short durability) {
		i.setDurability(durability);
		return this;
	}
	public ItemStackBuilder addLore(String lore) {
		ArrayList<String> newLore = new ArrayList<String>();
		if(imeta.hasLore()) {
			newLore.addAll(imeta.getLore());
		}
		newLore.add(ChatColor.translateAlternateColorCodes('&', lore));
		imeta.setLore(newLore);
		return this;
	}
	public ItemStackBuilder removeLore(int index) {
		if(imeta.hasLore()) {
			List<String> lore = imeta.getLore();
			try {
				lore.remove(index);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				
			}
			imeta.setLore(lore);
		}
		return this;
	}
	public ItemStackBuilder setLore(ArrayList<String> lore) {
		ArrayList<String> newLore = new ArrayList<String>();
		for(String s: lore) {
			newLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		imeta.setLore(newLore);
		return this;
	}
	public ItemStackBuilder addEnchant(Enchantment ench, int level) {
		if(!i.containsEnchantment(ench)) {
			imeta.addEnchant(ench, level, true);
		}
		return this;
	}
	public ItemStackBuilder addUnsafeEnchant(Enchantment ench, int level) {
		if(!i.containsEnchantment(ench)) {
			i.addUnsafeEnchantment(ench, level);
		}
		return this;
	}
	public ItemStackBuilder removeEnchant(Enchantment ench) {
		if(i.containsEnchantment(ench)) {
			i.removeEnchantment(ench);
		}
		return this;
	}
	public ItemStackBuilder addItemFlag(ItemFlag it) {
		if(!imeta.hasItemFlag(it)) {
			imeta.addItemFlags(it);
		}
		return this;
	}
	public ItemStackBuilder removeItemFlag(ItemFlag it) {
		if(imeta.hasItemFlag(it)) {
			imeta.removeItemFlags(it);
		}
		return this;
	}
	
	public ItemStack build() {
		i.setItemMeta(imeta);
		return i;
	}
}
