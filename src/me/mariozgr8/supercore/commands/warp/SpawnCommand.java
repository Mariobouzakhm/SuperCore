package me.mariozgr8.supercore.commands.warp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.warps.WarpsManager;

public class SpawnCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perm;
	private WarpsManager warps;
	
	public SpawnCommand(SuperCore sc) {
		this.chat = sc.getMessages();
		this.perm = sc.getPerms();
		this.warps = sc.getWarps();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(args.length >= 2) {
				this.chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perm.spawnPermission)) {
					this.chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				p.teleport(warps.getSpawnLoc());
				this.chat.sendMessageToPlayer(chat.getSpawnMessage(), p);
				return true;
			} 
			if(!p.hasPermission(perm.spawnOthersPermission)) {
				this.chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if(target == null) {
				this.chat.sendMessageToPlayer(chat.getPlayerOffline(), p);
				return true;
			}
			target.teleport(warps.getSpawnLoc());
			this.chat.sendMessageToPlayer(chat.getSpawnMessage(), p);
			return true;
		}
		return false;
	}

}
