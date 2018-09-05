package me.mariozgr8.supercore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.SPlayerManager;

public class RegisterEvent implements Listener {
	private SPlayerManager manager = SuperCore.getInstance().getManager();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		manager.registerPlayer(p);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		manager.unregisterPlayer(p);
	}
}
