package me.mariozgr8.supercore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.SPlayerManager;
import me.mariozgr8.supercore.objects.SPlayer;

public class StatisticsEvents implements Listener {
	private SuperCore sc;
	private SPlayerManager splayers;
	private MessageManager chat;
	
	public StatisticsEvents(SuperCore sc) {
		this.sc = sc;
		this.splayers = this.sc.getManager();
		this.chat = sc.getMessages();
	}
	
	@EventHandler
	public void onClickEvent(InventoryClickEvent e)  {
		if(e.getInventory() == null) {
			return;
		}
		if(e.getCurrentItem() == null) {
			return;
		}
		
		Inventory inv = e.getClickedInventory();
		if(inv.getName().equalsIgnoreCase(chat.getStatisticsInvName())) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onDeathEvent(PlayerDeathEvent e) {
		SPlayer sp = splayers.getSPlayerFromUUID(e.getEntity().getUniqueId());
		sp.getStats().playerDeath();
	}
	@EventHandler
	public void onPlaceEvent(BlockPlaceEvent e) {
		SPlayer sp = splayers.getSPlayerFromUUID(e.getPlayer().getUniqueId());
		sp.getStats().incrementBlocksPlaced();
	}
	@EventHandler
	public void onBreakEvent(BlockBreakEvent e) {
		SPlayer sp = splayers.getSPlayerFromUUID(e.getPlayer().getUniqueId());
		sp.getStats().incrementBlocksBroken();
	}
	@EventHandler
	public void onDeathEvent(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			SPlayer sp = splayers.getSPlayerFromUUID(e.getEntity().getKiller().getUniqueId());
			if(e.getEntity() instanceof Player) {
				sp.getStats().incrementPlayersKilled();
				return;
			}
			else {
				sp.getStats().incrementMobsKilled();
				return;
			}
		}
	}
	@EventHandler
	public void onDamadeEvent(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			SPlayer sp = splayers.getSPlayerFromUUID(e.getDamager().getUniqueId());
			sp.getStats().incrementDamageDealt(e.getDamage());
		}
		if(e.getEntity() instanceof Player) {
			SPlayer sp = splayers.getSPlayerFromUUID(e.getEntity().getUniqueId());
			sp.getStats().incrementDamageRecieved(e.getDamage());
		}
	}
	
}
