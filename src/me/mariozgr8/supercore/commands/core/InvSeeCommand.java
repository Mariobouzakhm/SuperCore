package me.mariozgr8.supercore.commands.core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.users.SPlayerManager;

public class InvSeeCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private SPlayerManager manager;
	
	public InvSeeCommand(SuperCore sc) {
		this.chat = sc.getMessages();
		this.perms = sc.getPerms();
		this.manager = sc.getManager();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("invsee")) {
			if(args.length != 1) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(!p.hasPermission(perms.invseePermission)) {
				chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if(target == null) {
				if(!p.hasPermission(perms.offlineInvSeePermission)) {
					chat.sendMessageToPlayer(chat.getPlayerOffline(), p);
					return true;
				}
				else {
					
					return true;
				}
			}
			p.openInventory(target.getInventory());
			manager.addPlayer(p, target);
			return true;
		}
		return false;
	}
}
