package me.mariozgr8.supercore.enchants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class EnchantManager {
	private List<IEnchant> activeEnchants;
	
	public EnchantManager() {
		activeEnchants = new ArrayList<IEnchant>();
	}
	
	//Method that register the enchant that are inside this plugin
	public void registerEnchants(IEnchant... ench) {
		for(IEnchant e: ench) {
			if(!activeEnchants.contains(e)) {
				activeEnchants.add(e);
			}
		}
	}
	
	//Method that return an instance of IEnchant that correspond to the rarity level
	public IEnchant getRandomIEnchantFromRarity(EnchantRarity rarity) {
		List<IEnchant> enchants = new ArrayList<IEnchant>();
		for(IEnchant ench: activeEnchants) {
			if(ench.getRarity().equals(rarity)) {
				enchants.add(ench);
			}
		}
		
		return enchants.get(randomNumber(enchants.size()));
	}
	public int returnRandomLevel(IEnchant ench) {
		Random random = new Random();
		return random.nextInt(ench.getMaxLevel());
	}
	private int randomNumber(int maxIndex) {
		Random random = new Random();
		return random.nextInt(maxIndex);
	}
	
	//Inventory And Items relating to the enchants GUI
	public Inventory openEnchInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 9*3, );
		
		return inv;
	}
 
}
