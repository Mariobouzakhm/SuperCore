package me.mariozgr8.supercore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.mariozgr8.supercore.Listeners.RegisterEvent;
import me.mariozgr8.supercore.Listeners.StatisticsEvents;
import me.mariozgr8.supercore.Listeners.WarpsEvents;
import me.mariozgr8.supercore.api.Glow;
import me.mariozgr8.supercore.commands.DelWarpCommand;
import me.mariozgr8.supercore.commands.FeedCommand;
import me.mariozgr8.supercore.commands.FlyCommand;
import me.mariozgr8.supercore.commands.GodCommand;
import me.mariozgr8.supercore.commands.HealCommand;
import me.mariozgr8.supercore.commands.SetSpawnCommand;
import me.mariozgr8.supercore.commands.SetWarpCommand;
import me.mariozgr8.supercore.commands.SpawnCommand;
import me.mariozgr8.supercore.commands.StatsCommand;
import me.mariozgr8.supercore.commands.WarpCommand;
import me.mariozgr8.supercore.managers.MessageManager;
import me.mariozgr8.supercore.managers.PermissionManager;
import me.mariozgr8.supercore.managers.SPlayerManager;
import me.mariozgr8.supercore.managers.SettingsManager;
import me.mariozgr8.supercore.managers.StatisticsManager;
import me.mariozgr8.supercore.managers.WarpsManager;

public class SuperCore extends JavaPlugin {
	private static SuperCore instance;
	public static SuperCore getInstance() {
		return instance;
	}
	
	private SettingsManager settings;
	private MessageManager chat;
	private PermissionManager perms;
	private StatisticsManager stats;
	private SPlayerManager splayers;
	private WarpsManager warps;
	
	@Override
	public void onEnable() {
		instance = this;
		
		//Register the Glow Enchantment
		Glow.registerGlow();
		
		//Setup up the configuration files
		settings = SettingsManager.getInstance();
		settings.setup(this);
		settings.updateMessageConfig();
		loadConfig();
		
		//Setup the plugin messages
		chat = new MessageManager();
		chat.setupMessages(settings);
		chat.setupConfig(this);
		
		//Setup the plugin's permissions
		perms = new PermissionManager();
		
		//Setup the splayers Handler
		splayers = new SPlayerManager();
		
		//Setup the statistics Manager
		stats = new StatisticsManager(this);
		stats.setup();
		
		//Setup the server warps and load server warps
		warps = new WarpsManager(this);
		
		//Register the Server Events and Commands
		registerCommands();
		registerEvents();
		
		//Register all the players already on the server to the plug-in and load their stats in case of a reload
		for(Player p: Bukkit.getServer().getOnlinePlayers()) {
			splayers.registerPlayer(p);
		}
		
		
	}
	
	@Override
	public void onDisable() {
		//Unregister All the players in case of a server reload to save all the changes to the files
		for(Player p: Bukkit.getServer().getOnlinePlayers()) { 
			splayers.unregisterPlayer(p);
		}
		
		//Save all Warps Created during current server session
		warps.saveWarps();
	}
	
	public void registerCommands() {
		//Basic Commands
		this.getCommand("fly").setExecutor(new FlyCommand(this));
		this.getCommand("god").setExecutor(new GodCommand(this));
		this.getCommand("feed").setExecutor(new FeedCommand(this));
		this.getCommand("heal").setExecutor(new HealCommand(this));
		
		//Statistics Commands
		this.getCommand("statistics").setExecutor(new StatsCommand(this));
		
		//Warps Commands
		this.getCommand("setwarp").setExecutor(new SetWarpCommand(this));
		this.getCommand("warp").setExecutor(new WarpCommand(this));
		this.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		this.getCommand("spawn").setExecutor(new SpawnCommand(this));
		this.getCommand("delwarp").setExecutor(new DelWarpCommand(this));
		
	}
	public void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new RegisterEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new StatisticsEvents(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WarpsEvents(this), this);
	}
	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	//Getters
	public PermissionManager getPerms() {
		return perms;
	}
	public MessageManager getMessages() {
		return chat;
	}
	public StatisticsManager getStats() {
		return stats;
	}
	public SPlayerManager getManager() {
		return splayers;
	}
	public WarpsManager getWarps() {
		return warps;
	}
}