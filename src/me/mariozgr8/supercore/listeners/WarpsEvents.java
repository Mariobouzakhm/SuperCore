package me.mariozgr8.supercore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.warps.WarpEntry;
import me.mariozgr8.supercore.warps.WarpsManager;

public class WarpsEvents implements Listener {
	private WarpsManager warps;
	private MessageManager chat;
	
	public WarpsEvents(SuperCore sc) {
		warps = sc.getWarps();
		chat = sc.getMessages();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory() == null) {
			return;
		}
		if(e.getCurrentItem() == null) {
			return;
		}
		
		Inventory inv = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		if(inv.getName().equalsIgnoreCase(chat.getWarpsInvName())) {
			e.setCancelled(true);
			if(i.equals(warps.getPreviousPage())) {
				int page = Integer.parseInt(
						ChatColor.stripColor(p.getOpenInventory().getItem(31).getItemMeta().getDisplayName())
						.replace("Current Page: ", ""));
				p.openInventory(warps.getWarpsInv(page - 1, p));
				return;
			}
			else if(i.equals(warps.getNextPage())) {
				int page = Integer.parseInt(
						ChatColor.stripColor(p.getOpenInventory().getItem(31).getItemMeta().getDisplayName())
						.replace("Current Page: ", ""));
				p.openInventory(warps.getWarpsInv(page + 1, p));
				return;
			}
			else {
				String warpName = ChatColor.stripColor(i.getItemMeta().getDisplayName()).replace(" Warp", "");
				if(p.hasPermission("supercore.warp."+warpName)) {
					WarpEntry w = warps.getWarpFromName(warpName);
					p.teleport(w.getLocation());
					chat.sendMessageToPlayer(chat.getWarping().replace("%warp%", w.getName()), p);
					return;
				}	
			}
		}
	}
}
