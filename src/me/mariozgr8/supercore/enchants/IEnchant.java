package me.mariozgr8.supercore.enchants;

public abstract class IEnchant {
	private int id;
	private String name;
	private int maxLevel;
	
	private EnchantType type;
	private EnchantRarity rarity;
	
	public IEnchant(int id, String name, int maxLevel, EnchantType type, EnchantRarity rarity) {
		this.id = id;
		this.name = name;
		this.maxLevel = maxLevel;
		this.type = type;
		this.rarity = rarity;
	}
	
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
