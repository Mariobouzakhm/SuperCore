package me.mariozgr8.supercore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.PermissionManager;
import me.mariozgr8.supercore.managers.SPlayerManager;
import me.mariozgr8.supercore.managers.StatisticsManager;
import me.mariozgr8.supercore.objects.SPlayer;

public class StatsCommand implements CommandExecutor {
	private SuperCore sc;
	private MessageManager chat;
	private PermissionManager perms;
	private SPlayerManager manager;
	private StatisticsManager stats;
	
	public StatsCommand(SuperCore sc) {
		this.sc = sc;
		this.chat = this.sc.getMessages();
		this.perms = this.sc.getPerms();
		this.manager = this.sc.getManager();
		this.stats = this.sc.getStats();
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("statistics")) {
			if(args.length >= 2) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			if(args.length == 0) {
				if(!p.hasPermission(perms.statsPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				SPlayer sp = manager.getSPlayerFromUUID(p.getUniqueId());
				p.openInventory(stats.openStatsInv(sp));
				return true;
			}
			if(args.length == 1) {
				if(!p.hasPermission(perms.statsOtherPermission)) {
					chat.sendMessageToPlayer(chat.getNoPerms(), p);
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					this.chat.sendMessageToPlayer(this.chat.getPlayerOffline(), p);
					return true;
				}
				SPlayer sp = manager.getSPlayerFromUUID(target.getUniqueId());
				p.openInventory(stats.openStatsInv(sp));
				return true;
			}
		}
		return false;
	}

}
