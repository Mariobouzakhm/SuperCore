package me.mariozgr8.supercore.commands.core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;

public class TpHereCommand implements CommandExecutor {
	private PermissionManager perms;
	private MessageManager chat;
	
	public TpHereCommand(SuperCore sc) {
		this.perms = sc.getPerms();
		this.chat = sc.getMessages();
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("tphere")) {
			if(!p.hasPermission(perms.tpHerePermission)) {
				this.chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			if(args.length != 1) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if(target == null) {
				chat.sendMessageToPlayer(chat.getPlayerOffline(), p);
				return true;
			}
			
			target.teleport(p.getLocation());
			return true;
		}
		return false;
	}
}
