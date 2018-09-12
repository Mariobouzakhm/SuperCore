package me.mariozgr8.supercore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.users.SPlayerManager;

public class InvseeEvents implements Listener {
	private SPlayerManager manager;
	private PermissionManager perms;
	
	public InvseeEvents(SuperCore core) {
		this.manager = core.getManager();
		this.perms = core.getPerms();
	}
	
	@EventHandler
	public void onClickEvent(InventoryClickEvent e) {
		if(e.getInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getClickedInventory();
		
		if(manager.containsUser(p) && manager.getTarget(p).getInventory().equals(inv)) {
			if(p.hasPermission(perms.invseeModifyPermission)) return;
			else {
				e.setCancelled(true);
			}
		}
		if(manager.containsOfflineUser(p)) {
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
		if(manager.containsUser(p)) {	
			try {
				if(inv.equals(manager.getTarget(p).getInventory())) {
					manager.removePlayer(p);
				}
			}
			catch(NullPointerException ex) {
				ex.printStackTrace();
				p.sendMessage(ChatColor.RED+"Error Occured please contact an admin !");
				manager.removePlayer(p);
			}
		}
		if(manager.containsOfflineUser(p)) {
			manager.saveBasedOnUUID(manager.getTargetUUID(p), inv);
			manager.toggleModifiedInv(manager.getTargetUUID(p));
			manager.removeOfflinePlayer(p);
		}
	}

}
