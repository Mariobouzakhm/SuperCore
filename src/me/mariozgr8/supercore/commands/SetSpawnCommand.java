package me.mariozgr8.supercore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.PermissionManager;
import me.mariozgr8.supercore.managers.WarpsManager;

public class SetSpawnCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private WarpsManager warps;
	
	public SetSpawnCommand(SuperCore sc) {
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
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(!p.hasPermission(perms.setSpawnPermission)) {
				this.chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			if(args.length != 0) {
				this.chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			warps.registerSpawn(p.getLocation());
			this.chat.sendMessageToPlayer(chat.getSetSpawnMessage(), p);
			return true;
			
		}
		return false;
	}
}
