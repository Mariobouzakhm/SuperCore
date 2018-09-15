package me.mariozgr8.supercore.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.homes.HomeEntry;
import me.mariozgr8.supercore.homes.HomeManager;
import me.mariozgr8.supercore.users.SPlayer;
import me.mariozgr8.supercore.users.SPlayerManager;

public class SetHomeCommand implements CommandExecutor {
	private HomeManager manager;
	private MessageManager chat;
	private PermissionManager perms;
	private SPlayerManager splayers;
	
	public SetHomeCommand(SuperCore sc) {
		manager = sc.getHomes();
		chat = sc.getMessages();
		perms = sc.getPerms();
		splayers = sc.getManager();
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(!p.hasPermission(perms.sethomePermission)) {
				chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			if(args.length != 1) {
				chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			String name = args[0];
			SPlayer sp = splayers.getSPlayerFromUUID(p.getUniqueId());
			if(sp.returnHomeFromName(name) != null) {
				HomeEntry home = sp.returnHomeFromName(name);
				home.setLocation(p.getLocation());
			}
			else {
				if(manager.returnMaxPlayerHome(p) == sp.getNumberOfHomes()) {
					chat.sendMessageToPlayer(chat.getTooManyHomes(), p);
					return true;
				}
				HomeEntry home = new HomeEntry(name, p.getLocation());
				sp.addHome(home);
			}
			chat.sendMessageToPlayer(chat.getHomeCreated(), p);
			return true;
		}
		return false;
	}
}
