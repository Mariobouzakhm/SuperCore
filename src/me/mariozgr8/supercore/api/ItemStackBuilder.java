package me.mariozgr8.supercore.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
/**
 * Class that represent an implementation of ItemStackBuilder
 * Allow fast ItemStack builds
 * @author Mario
 *
 */
public class ItemStackBuilder {
	private ItemStack i;
	private ItemMeta imeta;
	
	/**
	 * Constructor that create an instance of ItemStackBuilder based of an existing ItemStack
	 * @param i ItemStack used in the build
	 */
	public ItemStackBuilder(ItemStack i) {
		this.i = i;
		this.imeta = i.getItemMeta();
	}
	/**
	 * Constructor that create an instance of ItemStackBuilder based of a Materia Enum with amount 1
	 * @param m Material of the ItemStack
	 */
	public ItemStackBuilder(Material m) {
		this.i = new ItemStack(m, 1);
		this.imeta = i.getItemMeta();
	}
	/**
	 * Constructor that create an instance of ItemStackBuilder based of a Materia Enum and the amount
	 * @param m Material of the ItemStack
	 * @param amount quantity of ItemStack
	 */
	public ItemStackBuilder(Material m, int amount) {
		this.i = new ItemStack(m, amount);
		this.imeta = i.getItemMeta();
	}
	/**
	 * Constructor that create an instance of ItemStackBuilder based of a Materia Enum and the durability of item with amount 1
	 * @param m Material of itemstack
	 * @param durability short representing the durability of item
	 */
	public ItemStackBuilder(Material m, short durability) {
		this.i = new ItemStack(m, 1, durability);
		this.imeta = i.getItemMeta();
	}
	/**
	 * Constructor that create an instance of ItemStackBuilder based of a Materia Enum and the amount and the durability of item
	 * @param m Material of itemstack
	 * @param amount quantity of ItemStack
	 * @param durability short representing the durability of item
	 */
	public ItemStackBuilder(Material m, int amount, short durability) {
		this.i = new ItemStack(m, amount, durability);
		this.imeta = i.getItemMeta();
	}
	
	/**
	 * Method to change the display name
	 * @param name represent the new display name
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder setDisplayName(String name) {
		imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		return this;
	}
	/**
	 * Method to change item amount
	 * @param amount represent the new item amount
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder setAmount(int amount) {
		i.setAmount(amount);
		return this;
	}
	/**
	 * Method that change item durability
	 * @param durability represent the new item durability
	 * @return instance of ItemStackBuiler
	 */
	public ItemStackBuilder setDurability(short durability) {
		i.setDurability(durability);
		return this;
	}
	/**
	 * Method that add a lore to item lore list
	 * @param lore represent the lore to be added
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder addLore(String lore) {
		ArrayList<String> newLore = new ArrayList<String>();
		if(imeta.hasLore()) {
			newLore.addAll(imeta.getLore());
		}
		newLore.add(ChatColor.translateAlternateColorCodes('&', lore));
		imeta.setLore(newLore);
		return this;
	}
	/**
	 * Method that remove a lore based on index 
	 * @param index represent the position of the lore to be removed from the list of lores
	 * @return instance of ItemStackBuilder
	 */
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
	/**
	 * Method that change the content of a certain line of lore
	 * @param lore represent the new lore line
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder setLore(ArrayList<String> lore) {
		ArrayList<String> newLore = new ArrayList<String>();
		for(String s: lore) {
			newLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		imeta.setLore(newLore);
		return this;
	}
	/**
	 * Method that an enchantment affected by a specific level
	 * @param ench Enchantment to be applied
	 * @param level Level of the enchant to be applied
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder addEnchant(Enchantment ench, int level) {
		if(!i.containsEnchantment(ench)) {
			imeta.addEnchant(ench, level, true);
		}
		return this;
	}
	/**
	 * Method that an unsafe enchantment affected by a specific level
	 * @param ench Enchantment to be applied
	 * @param level Level of the enchant to be applied
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder addUnsafeEnchant(Enchantment ench, int level) {
		if(!i.containsEnchantment(ench)) {
			i.addUnsafeEnchantment(ench, level);
		}
		return this;
	}
	/**
	 * Method that remove an enchant of item if the item contains the enchant
	 * @param ench Enchantment to remove
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder removeEnchant(Enchantment ench) {
		if(i.containsEnchantment(ench)) {
			i.removeEnchantment(ench);
		}
		return this;
	}
	/**
	 * Method that add an ItemFlag to item
	 * @param it ItemFlag to add
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder addItemFlag(ItemFlag it) {
		if(!imeta.hasItemFlag(it)) {
			imeta.addItemFlags(it);
		}
		return this;
	}
	/**
	 * Method that remove an ItemFlag from item if the item contains the ItemFlag
	 * @param it ItemFlag to be removed
	 * @return instance of ItemStackBuilder
	 */
	public ItemStackBuilder removeItemFlag(ItemFlag it) {
		if(imeta.hasItemFlag(it)) {
			imeta.removeItemFlags(it);
		}
		return this;
	}
	/**
	 * Method that build the itemstack
	 * @return instance of ItemStack
	 */
	public ItemStack build() {
		i.setItemMeta(imeta);
		return i;
	}
}
