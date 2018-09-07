package me.mariozgr8.supercore.users;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.mariozgr8.supercore.SuperCore;

public class SPlayerManager {
	private SuperCore sc = SuperCore.getInstance();
	
	//HashMap Containing the players and their corresponding SPlayer
	private HashMap<UUID, SPlayer> splayers = new HashMap<UUID, SPlayer>();
	
	public void registerPlayer(Player p) {
		SPlayer sp = new SPlayer(p.getUniqueId());
		
		//Add the player StatisticsEntry to the StatisticsEntry List
		sc.getStats().addEntry(sp.getStats());
		
		//Load Player Inventory If Modified
		Inventory inv = sp.loadInventory();
		if(inv != null) {
			for(int i = 0; i<p.getInventory().getSize(); i++) {
				p.getInventory().setItem(i, inv.getItem(i));
			}
		}
		
		//Add the SPlayer to the splayers list
		addPlayer(sp);
		
	}
	public void unregisterPlayer(Player p) {
		SPlayer sp = splayers.get(p.getUniqueId());
		
		//Save Player Invetory to config
		sp.saveInventory();
		
		//Save and Remove the stats entry from the list
		sp.getStats().saveStats();
		sc.getStats().removeEntry(sp.getStats());
		
		//Remove the SPLayer from the list
		removePlayer(sp);
	}
	
	public void addPlayer(SPlayer sp) {
		splayers.put(sp.getUUID(), sp);
	}
	public void removePlayer(SPlayer sp) {
		splayers.remove(sp.getUUID());
	}
	public SPlayer getSPlayerFromUUID(UUID uuid) {
		for(SPlayer sp: splayers.values()) {
			if(sp.getUUID() == uuid) {
				return sp;
			}
		}
		return null;
	}

}
