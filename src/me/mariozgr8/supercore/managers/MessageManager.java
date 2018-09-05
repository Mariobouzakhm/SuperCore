package me.mariozgr8.supercore.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MessageManager {
	
	//General messages
	private String prefix;
	private String wrongArgs;
	private String playerOnly;
	private String noPerms;
	private String playerOffline;
	private String noPermsLore;
	
	private String flyModeEnabled;
	private String flyModeDisabled;
	
	private String godModeEnabled;
	private String godModeDisabled;
	
	private String setSpawnMessage;
	private String spawnMessage;
	
	private String invalidWarpMat;
	private String invalidWarpName;
	private String duplicateWarpName;
	private String successfullyCreatedWarp;
	private String successfullyDeletedWarp;
	private String warpNoExist;
	private String warping;
	
	
	//Config Settings
	private String statisticsInvName;
	private String playtimeStatItemName;
	private String damageStatItemName;
	private String blocksStatItemName;
	private String mobsStatItemName;
	
	private String warpsInvName;
	private String warpsItemName;
	
	public void setupMessages(SettingsManager s) {
		prefix = s.getMessageConfig().getString("messages.prefix");
		wrongArgs = s.getMessageConfig().getString("messages.wrong-args");
		playerOnly = s.getMessageConfig().getString("messages.player-only");
		noPerms = s.getMessageConfig().getString("messages.no-permission");
		playerOffline = s.getMessageConfig().getString("messages.player-not-found");
		noPermsLore = s.getMessageConfig().getString("messages.no-permission-lore");
		
		flyModeEnabled = s.getMessageConfig().getString("messages.fly-enabled");
		flyModeDisabled = s.getMessageConfig().getString("messages.fly-disabled");
		
		godModeEnabled = s.getMessageConfig().getString("messages.god-enabled");
		godModeDisabled = s.getMessageConfig().getString("messages.god-disabled");
		
		setSpawnMessage = s.getMessageConfig().getString("messages.set-spawn");
		spawnMessage = s.getMessageConfig().getString("messages.spawn");
		
		invalidWarpMat = s.getMessageConfig().getString("messages.invalid-warp-material");
		invalidWarpName = s.getMessageConfig().getString("messages.invalid-warp-name");
		duplicateWarpName = s.getMessageConfig().getString("messages.duplicate-warp-name");
		successfullyCreatedWarp = s.getMessageConfig().getString("messages.warp-created");
		successfullyDeletedWarp = s.getMessageConfig().getString("messages.warp-deleted");
		warpNoExist = s.getMessageConfig().getString("messages.warp-no-exist");
		warping = s.getMessageConfig().getString("messages.warping");
		
	}
	public void setupConfig(Plugin p) {
		statisticsInvName = p.getConfig().getString("inventory.statistics.name");
		playtimeStatItemName = p.getConfig().getString("inventory.statistics.playtime-item.name");
		damageStatItemName = p.getConfig().getString("inventory.statistics.damage-item.name");
		blocksStatItemName = p.getConfig().getString("inventory.statistics.blocks-item.name");
		mobsStatItemName = p.getConfig().getString("inventory.statistics.mobs-item.name");
		
		warpsInvName = p.getConfig().getString("inventory.warps.name");
		warpsItemName = p.getConfig().getString("inventory.warps.warp-item.name");
		
	}
	
	public String getPrefix() {
		return prefix;
	}
	public String getWrongArgs() {
		return wrongArgs;
	}
	public String getPlayerOnly() {
		return playerOnly;
	}
	public String getNoPerms() {
		return noPerms;
	}
	public String getPlayerOffline() {
		return playerOffline;
	}
	public String getNoPermsLore() {
		return noPermsLore;
	}
	public String getFlyModeEnabled() {
		return flyModeEnabled;
	}
	public String getFlyModeDisabled() {
		return flyModeDisabled;
	}
	public String getGodModeEnabled() {
		return godModeEnabled;
	}
	public String getGodModeDisabled() {
		return godModeDisabled;
	}
	public String getSetSpawnMessage() {
		return setSpawnMessage;
	}
	public String getSpawnMessage() {
		return spawnMessage;
	}
	public String getInvalidWarpMat() {
		return invalidWarpMat;
	}
	public String getInvalidWarpName() {
		return invalidWarpName;
	}
	public String getDuplicateWarpName() {
		return duplicateWarpName;
	}
	public String getSuccessfullyCreatedWarp() {
		return successfullyCreatedWarp;
	}
	public String getSuccessfullyDeletedWarp() {
		return successfullyDeletedWarp;
	}
	public String getWarpNoExist() {
		return warpNoExist;
	}
	public String getWarping() {
		return warping;
	}
	//Config String Getters
	public String getStatisticsInvName() {
		return changeColor(this.statisticsInvName);
	}
	public String getWarpsInvName() {
		return changeColor(this.warpsInvName);
	}
	public String getPlaytimeStatItemName() {
		return this.playtimeStatItemName;
	}
	public String getDamageStatItemName() {
		return this.damageStatItemName;
	}
	public String getMobsStatItemName() {
		return this.mobsStatItemName;
	}
	public String getBlocksStatItemName() {
		return this.blocksStatItemName;
	}
	public String getWarpItemName() {
		return this.warpsItemName;
	}
	
	public String changeColor(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);		
	}
	public String addPlayerName(String message, Player p) {
		return message.replace("%player%", p.getName());
	}
	public void sendMessageToPlayer(String message, Player p) {
		p.sendMessage(changeColor(prefix+message));
	}
}
