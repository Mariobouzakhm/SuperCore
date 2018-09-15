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
import me.mariozgr8.supercore.homes.HomeManager;
import me.mariozgr8.supercore.users.SPlayer;
import me.mariozgr8.supercore.users.SPlayerManager;

public class HomesCommand implements CommandExecutor {
	private HomeManager manager;
	private MessageManager chat;
	private PermissionManager perms;
	private SPlayerManager splayers;
	
	public HomesCommand(SuperCore sc) {
		manager = sc.getHomes();
		chat = sc.getMessages();
		perms = sc.getPerms();
		splayers = sc.getManager();
		
	}

	@Override @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("homes")) {
			if(args.length >= 2) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perms.homesPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				SPlayer sp = splayers.getSPlayerFromUUID(p.getUniqueId());
				chat.sendMessageToPlayer(manager.getHomesMessage(sp.getHomes()), p);
				return true;
			}
			if(args.length == 1) {
				if(!p.hasPermission(perms.homesOthersPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				String name = args[0];
				OfflinePlayer off = Bukkit.getServer().getOfflinePlayer(name);
				if(off == null) {
					chat.sendMessageToPlayer(chat.getPlayerNorFoundOffline(), p);
					return true;
				}
				chat.sendMessageToPlayer(manager.getHomesMessage(manager.getOfflinePlayerHomes(off)), p);
				return true;
			}
		}
		return false;
	}

}
