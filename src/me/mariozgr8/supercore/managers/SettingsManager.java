package me.mariozgr8.supercore.managers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {
	private static SettingsManager settings = new SettingsManager();
	private SettingsManager() { }
	
	public static SettingsManager getInstance() {
		return settings;
	}
	
	private FileConfiguration messageConfig;
	private File messageFile;
	
	private FileConfiguration statsConfig;
	private File statsFile;
	
	private FileConfiguration locationsConfig;
	private File locationsFile;
	
	public void setup(Plugin p) {
		if(!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		messageFile = new File(p.getDataFolder(), "messages.yml");
		if(!messageFile.exists()) {
			try {
				File en = new File(p.getDataFolder(), "/messages.yml");
				InputStream E = getClass().getResourceAsStream("/messages.yml");
				copyFile(E, en);
			} 
			catch (Exception e) {
				Bukkit.getServer().getLogger().severe("Could not create messages.yml !");
			}
		}
		messageConfig = YamlConfiguration.loadConfiguration(messageFile);
		
		statsFile = new File(p.getDataFolder(), "statistics.yml");
		if(!statsFile.exists()) {
			try {
				statsFile.createNewFile();
			}
			catch(IOException e) {
				Bukkit.getServer().getLogger().severe("Could not create statistics.yml !");
			}
		}
		statsConfig = YamlConfiguration.loadConfiguration(statsFile);
		
		locationsFile = new File(p.getDataFolder(), "locations.yml");
		if(!locationsFile.exists()) {
			try {
				locationsFile.createNewFile();
			}
			catch(IOException e) {
				Bukkit.getServer().getLogger().severe("Could not create statistics.yml !");
			}
		}
		locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);
	}
	public void updateMessageConfig() {
		if(messageConfig.get("messages.set-spawn") == null) {
			messageConfig.set("messages.set-spawn", "&6Spawn has been set to your current location !");
		}
		if(messageConfig.get("messages.spawn") == null) {
			messageConfig.set("messages.spawn", "&6Teleporting to spawn.");
		}
		if(messageConfig.get("messages.invalid-warp-material") == null) {
			messageConfig.set("messages.invalid-warp-material", "&cYou need to hold an item in your hand while creating a warp");
		}
		if(messageConfig.get("messages.invalid-warp-name") == null) {
			messageConfig.set("messages.invalid-warp-name", "&cWarp name cannot contain any special character");
		}
		if(messageConfig.get("messages.duplicate-warp-name") == null) {
			messageConfig.set("messages.duplicate-warp-name", "&cWarp with this name already exist");
		}
		if(messageConfig.get("messages.warp-created") == null) {
			messageConfig.set("messages.warp-created", "&aSuccessfully created warp !");
		}
		if(messageConfig.get("messages.no-permission-lore") == null) {
			messageConfig.set("messages.no-permission-lore", "&cNo Permission");
		}
		if(messageConfig.get("messages.warp-no-exist") == null) {
			messageConfig.set("messages.warp-no-exist", "&cCannot find warp with provided name !");
		}
		if(messageConfig.get("messages.warping") == null) {
			messageConfig.set("messages.warping", "&6Teleporting to %warp%");
		}
		if(messageConfig.get("messages.warp-deleted") == null) {
			messageConfig.set("messages.warp-deleted", "&aSuccessfully deleted warp !");
		}
		if(messageConfig.get("messages.fed") == null) {
			messageConfig.set("messages.fed", "&6You have been fed");
		}
		if(messageConfig.get("messages.healed") == null) {
			messageConfig.set("messages.healed", "&6You have been healed");
		}
		
		this.saveMessageConfig();
	}
	
	public static void copyFile(InputStream in, File out) throws Exception { // https://bukkit.org/threads/extracting-file-from-jar.16962/
		InputStream fis = in;
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			if(fis != null) {
				fis.close();
			}
			if(fos != null) {
				fos.close();
			}
		}
	}
	//Methods concerning the messages.yml file
	public FileConfiguration getMessageConfig() {
		return messageConfig;
	}
	public void saveMessageConfig() {
		try {
			messageConfig.save(messageFile);
		}
		catch(IOException e) {
			Bukkit.getServer().getLogger().severe("Could not save messages.yml file !");
		}
	}
	public void reloadMessageConfig() {
		messageConfig = YamlConfiguration.loadConfiguration(messageFile);
	}
	
	//Methods concerning the stats.yml file
	public FileConfiguration getStatsConfig() {
		return statsConfig;
	}
	public void saveStatsConfig() {
		try {
			statsConfig.save(statsFile);
		}
		catch(IOException e) {
			Bukkit.getServer().getLogger().severe("Could not create statistics.yml !");
		}
	}
	public void reloadStatsConfig() {
		statsConfig = YamlConfiguration.loadConfiguration(statsFile);
	}
	//Methods concerning the locations.yml file
	public FileConfiguration getLocationConfig() {
		return locationsConfig;
	}
	public void saveLocationConfig() {
		try {
			locationsConfig.save(locationsFile);
		}
		catch(IOException ex)  {
			Bukkit.getServer().getLogger().severe("Could not create locations.yml !");
		}
	}
	public void reloadLocationsConfig() {
		locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);
	}
}
