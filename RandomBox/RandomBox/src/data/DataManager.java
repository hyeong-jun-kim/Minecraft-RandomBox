package data;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import main.Main;

public class DataManager {
	private Main plugin;
	private File file = null;
	private FileConfiguration dataFile = null;
	
	public DataManager(Main plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
		reloadFile();
	}
	public void reloadFile() {
		if(file == null) {
			file = new File(this.plugin.getDataFolder(), "data.yml");
		}
		dataFile = YamlConfiguration.loadConfiguration(file);
		
		InputStream inputStream = this.plugin.getResource("data.yml");
		if(inputStream!=null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
			dataFile.setDefaults(defaultConfig);
		}		
	}
	public FileConfiguration getFile() {
		if(dataFile == null)
			reloadFile();
		return dataFile;
	}
	public void saveConfig() {
		if(dataFile == null || file == null)
			return;
		try {
			getFile().save(file);
		}catch (Exception e) {
			this.plugin.getLogger().log(Level.SEVERE, "파일 저장에 실패했습니다");
		}
	}
	public void saveDefaultConfig() {
		if(file == null)
			file = new File(this.plugin.getDataFolder(), "data.yml");
		if(!file.exists())
			this.plugin.saveResource("data.yml", false);
	}
}
