package me.mariozgr8.supercore.commands.warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.warps.WarpEntry;
import me.mariozgr8.supercore.warps.WarpsManager;

public class WarpCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private WarpsManager warps;
	
	public WarpCommand(SuperCore sc) {
		this.chat = sc.getMessages();
		this.perms = sc.getPerms();
		this.warps = sc.getWarps();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("warp")) {
			if(args.length >= 2) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perms.warpPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				p.openInventory(warps.getWarpsInv(1, p));
				return true;
			}
			if(args.length == 1) {
				String name = args[0];
				WarpEntry w = warps.getWarpFromName(name);
				if(w == null) {
					chat.sendMessageToPlayer(chat.getWarpNoExist(), p);
					return true;
				}
				if(!p.hasPermission("supercore.warp."+w.getName())) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				p.teleport(w.getLocation());
				chat.sendMessageToPlayer(chat.getWarping().replace("%warp%", w.getName()), p);
				return true;
			}
		}
		return false;
	}

}
