package net.factmc.betterkeeper;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getLogger().info("Firing up BetterKeeper!");
		getConfig().addDefault("hypixelapikey", "");
		getConfig().options().copyDefaults(true); //make config file
		saveConfig();
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		this.getCommand("thekeeper").setExecutor(new BetterKeeperCommand(this));
		
	}
    @Override
    public void onDisable() {
    	getLogger().info("Disabling BetterKeeper!");
    }
    
}
