package me.mariozgr8.supercore.commands.core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;

public class FlyCommand implements CommandExecutor {
	private SuperCore core;
	private MessageManager chat;
	private PermissionManager perms;
	
	public FlyCommand(SuperCore core) {
		this.core = core;
		this.chat = this.core.getMessages();
		this.perms = this.core.getPerms();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("fly")) {
			if(args.length >= 2) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perms.flyPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				if(p.getAllowFlight()) {
					p.setAllowFlight(false);
					p.setFlying(false);
					
					chat.sendMessageToPlayer(chat.addPlayerName(chat.getFlyModeDisabled(), p), p);
				}
				else {
					p.setAllowFlight(true);
					
					chat.sendMessageToPlayer(chat.addPlayerName(chat.getFlyModeEnabled(), p), p);
				}
				return true;
			}
			if(args.length == 1) {
				if(!p.hasPermission(perms.flyOthersPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					chat.sendMessageToPlayer(chat.getPlayerOffline(), p);
					return true;
				}
				if(target.getAllowFlight()) {
					target.setAllowFlight(false);
					target.setFlying(false);
					
					chat.sendMessageToPlayer(chat.addPlayerName(chat.getFlyModeDisabled(), target), target);
				}
				else {
					target.setAllowFlight(true);
					chat.sendMessageToPlayer(chat.addPlayerName(chat.getFlyModeEnabled(), target), target);
				}
				return true;
			}
		}
		return false;
	}
}
