package me.mariozgr8.supercore.homes;

import org.bukkit.entity.Player;

import me.mariozgr8.supercore.SuperCore;
import me.mariozgr8.supercore.data.PermissionManager;

public class HomeManager {
	private PermissionManager perms;
	
	public HomeManager(SuperCore sc) {
		perms = sc.getPerms();
	}
	
	/**
	 * Method that return the maximum number of home of a player based on their permissions
	 * @param p: Instance of player
	 * @return int max number of homes a player can have, -1 if unlimited, 0 if none
	 */
	public int returnMaxPlayerHome(Player p) {
		if(p.hasPermission(perms.unlimitedHomePermission)) {
			return -1;
		}
		else if(p.hasPermission(perms.twentyfiveHomePermission)) {
			return 25;
		}
		else if(p.hasPermission(perms.twentyHomePermission)) {
			return 20;
		}
		else if(p.hasPermission(perms.fifteenHomePermission)) {
			return 15;
		}
		else if(p.hasPermission(perms.tenHomePermission)) {
			return 10;
		}
		else if(p.hasPermission(perms.nineHomePermission)) {
			return 9;
		}
		else if(p.hasPermission(perms.eightHomePermission)) {
			return 8;
		}
		else if(p.hasPermission(perms.sevenHomePermission)) {
			return 7;
		}
		else if(p.hasPermission(perms.sixHomePermission)) {
			return 6;
		}
		else if(p.hasPermission(perms.fiveHomePermission)) {
			return 5;
		}
		else if(p.hasPermission(perms.fourHomePermission)) {
			return 4;
		}
		else if(p.hasPermission(perms.threeHomePermission)) {
			return 3;
		}
		else if(p.hasPermission(perms.twoHomePermission)) {
			return 2;
		}
		else if(p.hasPermission(perms.oneHomePermission)) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
