package me.mariozgr8.supercore.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;

public class HomeCommand implements CommandExecutor {
	private MessageManager chat;
	
	public HomeCommand(SuperCore sc) {
		chat = sc.getMessages();
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
		}
		if(cmd.getName().equalsIgnoreCase("home")) {
			
		}
		return false;
	}

}
