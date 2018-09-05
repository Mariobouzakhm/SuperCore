package me.mariozgr8.supercore.managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.api.DurabilityEnum;
import me.mariozgr8.supercore.api.Glow;
import me.mariozgr8.supercore.api.ItemStackBuilder;
import me.mariozgr8.supercore.api.Methods;
import me.mariozgr8.supercore.objects.SPlayer;
import me.mariozgr8.supercore.objects.StatisticsEntry;

public class StatisticsManager {	
	private SuperCore sc;
	private MessageManager chat;
	private Glow glow;
	
	public StatisticsManager(SuperCore sc) {
		this.sc = sc;
		this.chat = this.sc.getMessages();
		this.glow = new Glow(170);
	}
	
	//HashMap Containing all the StatisticsEntry and the corresponding UUID
	private HashMap<UUID, StatisticsEntry> statsEntries = new HashMap<UUID, StatisticsEntry>();

	public void setup() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!statsEntries.isEmpty()) {
					for(StatisticsEntry stats: statsEntries.values()) {
						stats.incrementTime();
					}
				}
			}	
		}.runTaskTimer(sc, 0, 20);
	}
	
	public void addEntry(StatisticsEntry entry) {
		statsEntries.put(entry.getUuid(), entry);
	}
	public void removeEntry(StatisticsEntry entry) {
		statsEntries.remove(entry.getUuid());
	}
	public StatisticsEntry getEntryFromUUID(UUID uuid) {
		for(StatisticsEntry stats: statsEntries.values()) {
			if(stats.getUuid().equals(uuid)) {
				return stats;
			}
		}
		return null;
	}
	
	//GUI That Displays the Informations about the an SPlayer and the items
	public Inventory openStatsInv(SPlayer sp) {
		Inventory inv = Bukkit.getServer().createInventory(null, 9*4, chat.getStatisticsInvName());
		inv.setItem(0, getLightBluePane());
		inv.setItem(1, getLightBluePane());
		inv.setItem(2, getLightBluePane());
		inv.setItem(3, getLightBluePane());
		inv.setItem(4, getLightBluePane());
		inv.setItem(5, getLightBluePane());
		inv.setItem(6, getLightBluePane());
		inv.setItem(7, getLightBluePane());
		inv.setItem(8, getLightBluePane());
		
		inv.setItem(9, getLightBluePane());
		inv.setItem(13, playerSkull(sp));
		inv.setItem(17, getLightBluePane());
		
		inv.setItem(18, getLightBluePane());
		inv.setItem(19, getTimeStats(sp));
		inv.setItem(20, getBlockStats(sp));
		inv.setItem(21, getDamageStats(sp));
		inv.setItem(22, getMobsStats(sp));
		inv.setItem(26, getLightBluePane());
		
		inv.setItem(27, getLightBluePane());
		inv.setItem(28, getLightBluePane());
		inv.setItem(29, getLightBluePane());
		inv.setItem(30, getLightBluePane());
		inv.setItem(31, getLightBluePane());
		inv.setItem(32, getLightBluePane());
		inv.setItem(33, getLightBluePane());
		inv.setItem(34, getLightBluePane());
		inv.setItem(35, getLightBluePane());

		return inv;	
	}
	public ItemStack getLightBluePane() {
		ItemStack i = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 1, DurabilityEnum.LIGHT_BLUE.getDurability())
				.setDisplayName(" ").build();
		return i;
	}
	public ItemStack playerSkull(SPlayer sp) {
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta imeta = (SkullMeta) i.getItemMeta();
		imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6"+sp.getPlayer().getName()+"'s statistics"));
		imeta.setOwner(sp.getPlayer().getName());
		imeta.addEnchant(glow, 1, true);
		
		i.setItemMeta(imeta);
		return i;
	}
	public ItemStack getTimeStats(SPlayer sp) {
		ItemStack i = new ItemStackBuilder(Material.WATCH, 1).setDisplayName(this.chat.getPlaytimeStatItemName())
				.addLore("&eTotal Playtime: " +Methods.convertTimeToString(sp.getStats().getTimeOnServer()))
				.addLore("&eTime Since Last Death: "+Methods.convertTimeToString(sp.getStats().getTimeSinceLastDeath()))
				.build();
		return i;
	}
	public ItemStack getDamageStats(SPlayer sp) {
		ItemStack i = new ItemStackBuilder(Material.DIAMOND_SWORD, 1).setDisplayName(this.chat.getDamageStatItemName())
				.addLore("&eDamage Dealt: "+String.format("%.1f", sp.getStats().getDamageDealt()))
				.addLore("&eDamage Taken: "+String.format("%.1f", sp.getStats().getDamageRecieved()))
				.build();
		return i;
	}
	public ItemStack getMobsStats(SPlayer sp) {
		ItemStack i = new ItemStackBuilder(Material.SKULL_ITEM, 1, (short) 2).setDisplayName(this.chat.getMobsStatItemName())
				.addLore("&eMobs Killed: "+sp.getStats().getMobsKilled())
				.addLore("&ePlayers Killed: "+sp.getStats().getPlayersKilled())
				.build();
		return i;
	}
	public ItemStack getBlockStats(SPlayer sp) {
		ItemStack i = new ItemStackBuilder(Material.GRASS, 1).setDisplayName(this.chat.getBlocksStatItemName())
				.addLore("&eBlocks Broken: "+sp.getStats().getBlocksBroken())
				.addLore("&eBlocks Placed: "+sp.getStats().getBlocksPlaced())
				.build();
		return i;
	}
	
}
