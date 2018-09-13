package me.mariozgr8.supercore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import me.mariozgr8.supercore.CoreMethods;
import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.PermissionManager;

public class InvseeEvents implements Listener {
	private PermissionManager perms;
	private CoreMethods core;
	
	public InvseeEvents(SuperCore sc) {
		this.perms = sc.getPerms();
		this.core = sc.getCore();
	}
	
	@EventHandler
	public void onClickEvent(InventoryClickEvent e) {
		if(e.getInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getClickedInventory();
		
		if(core.containsUser(p) && core.getTarget(p).getInventory().equals(inv)) {
			if(p.hasPermission(perms.invseeModifyPermission)) return;
			else {
				e.setCancelled(true);
			}
		}
		if(core.containsOfflineUser(p)) {
			if(p.hasPermission(perms.invseeModifyPermission)) return;
			else {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onCloseEvent(InventoryCloseEvent e) {
		if(e.getInventory() == null) return;
		
		Player p = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		if(core.containsUser(p)) {	
			try {
				if(inv.equals(core.getTarget(p).getInventory())) {
					core.removePlayer(p);
				}
			}
			catch(NullPointerException ex) {
				ex.printStackTrace();
				p.sendMessage(ChatColor.RED+"Error Occured please contact an admin !");
				core.removePlayer(p);
			}
		}
		if(core.containsOfflineUser(p)) {
			core.saveBasedOnUUID(core.getTargetUUID(p), inv);
			core.toggleModifiedInv(core.getTargetUUID(p));
			core.removeOfflinePlayer(p);
		}
	}

}
