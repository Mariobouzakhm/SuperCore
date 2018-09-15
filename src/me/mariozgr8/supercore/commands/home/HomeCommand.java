package me.mariozgr8.supercore.commands.home;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.homes.HomeEntry;
import me.mariozgr8.supercore.homes.HomeManager;
import me.mariozgr8.supercore.users.SPlayer;
import me.mariozgr8.supercore.users.SPlayerManager;

public class HomeCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private SPlayerManager splayers;
	private HomeManager manager;
	
	public HomeCommand(SuperCore sc) {
		chat = sc.getMessages();
		perms = sc.getPerms();
		splayers = sc.getManager();
		manager = sc.getHomes();
		
	}
	
	@Override @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("home")) {
			if(args.length == 0 || args.length >= 3) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 1) {
				if(!p.hasPermission(perms.homePermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				SPlayer sp = splayers.getSPlayerFromUUID(p.getUniqueId());
				HomeEntry home = sp.returnHomeFromName(args[0]);
				if(home == null) {
					chat.sendMessageToPlayer(chat.getHomeNotExist(), p);
					return true;
				}
				p.teleport(home.getLocation());
				return true;
			}
			if(args.length == 2) {
				if(!p.hasPermission(perms.homeOthersPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				OfflinePlayer off = Bukkit.getServer().getOfflinePlayer(args[0]);
				if(off == null) {
					chat.sendMessageToPlayer(chat.getPlayerNorFoundOffline(), p);
					return true;
				}
				
				HomeEntry home = manager.getOfflinePlayerHome(off, args[1]);
				if(home == null) {
					chat.sendMessageToPlayer(chat.getHomeNotExist(), p);
					return true;
				}
				
				p.teleport(home.getLocation());
				return true;
			}
		}
		return false;
	}
}
