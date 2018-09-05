package me.mariozgr8.supercore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.PermissionManager;

public class FeedCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	
	public FeedCommand(SuperCore sc) {
		chat = sc.getMessages();
		perms = sc.getPerms();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("feed")) {
			if(args.length >= 2) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perms.feedPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				p.setFoodLevel(20);
				chat.sendMessageToPlayer(chat.getFedMessage(), p);
				return true;
 			}
			if(args.length == 1) {
				if(!p.hasPermission(perms.feedOthersPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					chat.sendMessageToPlayer(chat.getPlayerOffline(), p);
					return true;
				}
				target.setFoodLevel(20);
				chat.sendMessageToPlayer(chat.getFedMessage(), target);
				return true;
			}
		}
		return false;
	}

}
