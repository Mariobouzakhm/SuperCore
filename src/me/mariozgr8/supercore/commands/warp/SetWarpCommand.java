package me.mariozgr8.supercore.commands.warp;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.MessageManager;
import me.mariozgr8.supercore.data.PermissionManager;
import me.mariozgr8.supercore.warps.WarpEntry;
import me.mariozgr8.supercore.warps.WarpsManager;

public class SetWarpCommand implements CommandExecutor {
	private MessageManager chat;
	private PermissionManager perms;
	private WarpsManager warps;
	
	public SetWarpCommand(SuperCore sc) {
		chat = sc.getMessages();
		perms = sc.getPerms();
		warps = sc.getWarps();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(chat.getPlayerOnly());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("setwarp")) {
			if(!p.hasPermission(perms.setWarpPermission)) {
				this.chat.sendMessageToPlayer(chat.getNoPerms(), p);
				return true;
			}
			if(args.length != 1) {
				this.chat.sendMessageToPlayer(chat.getWrongArgs(), p);
				return true;
			}
			ItemStack i = p.getInventory().getItemInMainHand();
			if(i.getType() == Material.AIR) {
				chat.sendMessageToPlayer(chat.getInvalidWarpMat(), p);
				return true;
			}
			String name = args[0];
			if(!warps.checkNameValidation(name)) {
				chat.sendMessageToPlayer(chat.getInvalidWarpName(), p);
				return true;
			}
			if(warps.getWarpFromName(name) != null) {
				chat.sendMessageToPlayer(chat.getDuplicateWarpName(), p);
				return true;
			}
			
			WarpEntry w = new WarpEntry(name, i.getDurability(), i.getType(), p.getLocation());
			warps.registerWarp(w);
			chat.sendMessageToPlayer(chat.getSuccessfullyCreatedWarp(), p);
			return true;
			
		}
		return false;
	}

}
