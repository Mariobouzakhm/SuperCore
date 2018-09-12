package me.mariozgr8.supercore.users;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.SettingsManager;

public class SPlayerManager {
	private SuperCore sc = SuperCore.getInstance();
	private SettingsManager settings = SettingsManager.getInstance();
	
	//HashMap Containing the players and their corresponding SPlayer
	private HashMap<UUID, SPlayer> splayers = new HashMap<UUID, SPlayer>();
	private HashMap<Player, Player> inInvsee = new HashMap<Player, Player>();
	private HashMap<Player, UUID> inOfflineInvsee = new HashMap<Player, UUID>();
	
	public void registerPlayer(Player p) {
		SPlayer sp = new SPlayer(p.getUniqueId());
		
		//Add the player StatisticsEntry to the StatisticsEntry List
		sc.getStats().addEntry(sp.getStats());
		
		//Load Player Inventory If Modified
		Inventory inv = sp.loadInventory();
		if(inv != null) {
			sp.getPlayer().getInventory().setContents(inv.getContents());
			sp.toggleInvModify(false);
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

	//Methods relating to the splayers HashMap
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
	//Methods relating to the inInvsee hashmap
	public void addPlayer(Player p, Player target) {
		inInvsee.put(p, target);
	}
	public void removePlayer(Player p) {
		if(inInvsee.containsKey(p)) {
			inInvsee.remove(p);
		}
	}
	public Player getTarget(Player p) {
		if(inInvsee.containsKey(p)) {
			return inInvsee.get(p);
		}
		return null;
	}
	public boolean containsUser(Player p) {
		return inInvsee.containsKey(p);
	}
	//Methods relating to the inOfflineInvsee hashmap
	public void addOfflinePlayer(Player p, UUID uuid) {
		inOfflineInvsee.put(p, uuid);
	}
	public void removeOfflinePlayer(Player p) {
		if(inOfflineInvsee.containsKey(p)) {
			inOfflineInvsee.remove(p);
		}
	}
	public UUID getTargetUUID(Player p) {
		if(inOfflineInvsee.containsKey(p)) {
			return inOfflineInvsee.get(p);
		}
		return null;
	}
	public boolean containsOfflineUser(Player p) {
		return inOfflineInvsee.containsKey(p);
	}
	@SuppressWarnings("deprecation")
	public UUID validateUUID(String name) {
		OfflinePlayer off = Bukkit.getServer().getOfflinePlayer(name);
		if(!off.hasPlayedBefore()) {
			return null;
		}
		UUID uuid = off.getUniqueId();
		if(settings.getPlayersDataConfig().get(uuid+"") == null) {
			return null;
		}
		return uuid;
	}
	public Inventory loadBasedOnUUID(UUID uuid) {
		Inventory inv = Bukkit.getServer().createInventory(null, 9*4);
		String path = uuid.toString()+".inventory.";
		try {
			for(int i = 0; i<inv.getSize(); i++) {
				ItemStack it = settings.getPlayersDataConfig().getItemStack(path+i);
				inv.setItem(i, it);
			}
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		return inv;	
	}
	public Inventory saveBasedOnUUID(UUID uuid, Inventory inv) {
		String path = uuid.toString()+".inventory.";
		try {
			for(int i = 0; i<inv.getSize(); i++) {
				settings.getPlayersDataConfig().set(path+i, inv.getItem(i));
			}
			settings.savePlayersDataConfig();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		return inv;
	}
	public void toggleModifiedInv(UUID uuid) {
		settings.getPlayersDataConfig().set(uuid.toString()+".inv-modified", true);
		settings.savePlayersDataConfig();
	}
 
}
