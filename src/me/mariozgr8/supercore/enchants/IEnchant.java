package me.mariozgr8.supercore.enchants;

abstract class IEnchant {
	private int id;
	private String name;
	private int maxLevel;
	
	private EnchantType type;
	private EnchantRarity rarity;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public EnchantType getType() {
		return type;
	}
	public EnchantRarity getRarity() {
		return rarity;
	}
}
