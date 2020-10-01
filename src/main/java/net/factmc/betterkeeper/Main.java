package net.factmc.betterkeeper;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	FileConfiguration config = this.getConfig();
	@Override
	public void onEnable() {
		getLogger().info("Firing up BetterKeeper!");
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		this.getCommand("thekeeper").setExecutor(new BetterKeeperCommand(this));
		config.addDefault("hypixelapikey", true);
		config.options().copyDefaults(true); //make config file
		saveConfig();
		
	}
    @Override
    public void onDisable() {
    	getLogger().info("Disabling BetterKeeper!");
    }
    
}
