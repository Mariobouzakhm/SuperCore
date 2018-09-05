package me.mariozgr8.supercore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.PermissionManager;

public class GodCommand implements CommandExecutor {
	private SuperCore core;
	private MessageManager chat;
	private PermissionManager perms;
		
	public GodCommand(SuperCore core) {
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
		if(cmd.getName().equalsIgnoreCase("god")) {
			if(args.length >= 2) {
				this.chat.sendMessageToPlayer(this.chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perms.godPermission)) {
					chat.sendMessageToPlayer(this.chat.getNoPerms(), p);
					return true;
				}
				p.setInvulnerable(!p.isInvulnerable());
				chat.sendMessageToPlayer(chat.addPlayerName((p.isInvulnerable()) ? chat.getGodModeEnabled() : chat.getGodModeDisabled(), p), p);
				return true;
			}
			if(args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					this.chat.sendMessageToPlayer(this.chat.getPlayerOffline(), p);
					return true;
				}
				target.setInvulnerable(!target.isInvulnerable());
				chat.sendMessageToPlayer(chat.addPlayerName((target.isInvulnerable()) ? chat.getGodModeEnabled() : chat.getGodModeDisabled(), target), target);
				return true;
			}
		}
		return false;
	}

}
