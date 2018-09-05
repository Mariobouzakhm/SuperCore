package me.mariozgr8.supercore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.PermissionManager;
import me.mariozgr8.supercore.managers.WarpsManager;
import me.mariozgr8.supercore.objects.WarpEntry;

public class DelWarpCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private WarpsManager warps;
	
	public DelWarpCommand(SuperCore sc) {
		chat = sc.getMessages();
		perms = sc.getPerms();
		warps = sc.getWarps();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("delwarp")) {
			if(!p.hasPermission(perms.delWarpPermission)) {
				chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			if(args.length != 1) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			WarpEntry w = warps.getWarpFromName(args[0]);
			if(w == null) {
				chat.sendMessageToPlayer(chat.getWarpNoExist(), p);
				return true;
			}
			warps.unregisterWarp(w);
			warps.deleteWarp(w);
			chat.sendMessageToPlayer(chat.getSuccessfullyDeletedWarp(), p);
			return true;
		}
		return false;
	}

}
