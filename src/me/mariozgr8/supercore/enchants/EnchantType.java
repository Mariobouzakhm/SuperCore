package me.mariozgr8.supercore.enchants;

import java.util.ArrayList;

import org.bukkit.Material;

public enum EnchantType {
	SWORD("Sword", getSwords()), 
	BOW("Bow", getBow()), 
	TOOLS("Tools", getTools()), 
	ARMOR("Armor", getArmors()), 
	HELMET("Helmet", getHelmets()), 
	BOOTS("Boots", getBoots()), 
	PICKAXES("Pickaxes", getPickaxes());
	
	private ArrayList<Material> validMaterials;
	private String name;
	
	private EnchantType(String name, ArrayList<Material> validMaterials) {
		this.name = name;
		this.validMaterials = validMaterials;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Material> getValidMaterials() {
		return validMaterials;
	}
	
	
	
	public static ArrayList<Material> getTools() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.add(Material.WOOD_AXE);
		list.add(Material.STONE_AXE);
		list.add(Material.IRON_AXE);
		list.add(Material.GOLD_AXE);
		list.add(Material.DIAMOND_AXE);
		
		list.add(Material.WOOD_SPADE);
		list.add(Material.STONE_SPADE);
		list.add(Material.IRON_SPADE);
		list.add(Material.GOLD_SPADE);
		list.add(Material.DIAMOND_SPADE);
		
		list.addAll(getPickaxes());
		return list;
	}
	public static ArrayList<Material> getSwords() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.add(Material.WOOD_SWORD);
		list.add(Material.STONE_SWORD);
		list.add(Material.IRON_SWORD);
		list.add(Material.GOLD_SWORD);
		list.add(Material.DIAMOND_SWORD);
		return list;
	}
	public static ArrayList<Material> getPickaxes() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.add(Material.WOOD_PICKAXE);
		list.add(Material.STONE_PICKAXE);
		list.add(Material.IRON_PICKAXE);
		list.add(Material.GOLD_PICKAXE);
		list.add(Material.DIAMOND_PICKAXE);
		return list;
	}
	public static ArrayList<Material> getBow() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.add(Material.BOW);
		return list;
	}
	public static ArrayList<Material> getArmors() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.addAll(getHelmets());
		
		list.add(Material.LEATHER_CHESTPLATE);
		list.add(Material.CHAINMAIL_CHESTPLATE);
		list.add(Material.IRON_CHESTPLATE);
		list.add(Material.GOLD_CHESTPLATE);
		list.add(Material.DIAMOND_CHESTPLATE);
		
		list.add(Material.LEATHER_LEGGINGS);
		list.add(Material.CHAINMAIL_LEGGINGS);
		list.add(Material.IRON_LEGGINGS);
		list.add(Material.GOLD_LEGGINGS);
		list.add(Material.DIAMOND_LEGGINGS);
		return list;
	}
	public static ArrayList<Material> getHelmets() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.add(Material.LEATHER_HELMET);
		list.add(Material.CHAINMAIL_HELMET);
		list.add(Material.IRON_HELMET);
		list.add(Material.GOLD_HELMET);
		list.add(Material.DIAMOND_HELMET);
		return list;
	}
	public static ArrayList<Material> getBoots() {
		ArrayList<Material> list = new ArrayList<Material>();
		list.add(Material.LEATHER_BOOTS);
		list.add(Material.CHAINMAIL_BOOTS);
		list.add(Material.IRON_BOOTS);
		list.add(Material.GOLD_BOOTS);
		list.add(Material.DIAMOND_BOOTS);
		return list;
	}
	
	
	
	
	

}
