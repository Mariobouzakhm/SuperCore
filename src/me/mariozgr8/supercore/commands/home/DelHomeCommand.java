package me.mariozgr8.supercore.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.users.SPlayer;
import me.mariozgr8.supercore.users.SPlayerManager;

public class DelHomeCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private SPlayerManager splayers;
	
	public DelHomeCommand(SuperCore sc) {
		chat = sc.getMessages();
		perms = sc.getPerms();
		splayers = sc.getManager();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("delhome")) {
			if(!p.hasPermission(perms.delhomePermission)) {
				chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			if(args.length != 1) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			String name = args[0];
			SPlayer sp = splayers.getSPlayerFromUUID(p.getUniqueId());
			if(sp.returnHomeFromName(name) == null) {
				chat.sendMessageToPlayer(chat.getHomeNotExist(), p);
				return true;
			}
			else {
				sp.removeHome(name);
				sp.deleteHome(name);
				chat.sendMessageToPlayer(chat.getHomeDeleted(), p);
				return true;
			}
		}
		return false;
	}
}
