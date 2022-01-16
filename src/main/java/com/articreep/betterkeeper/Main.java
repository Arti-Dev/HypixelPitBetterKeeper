package com.articreep.betterkeeper;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getLogger().info("Firing up BetterKeeper!");
		saveDefaultConfig(); // copies default config.yml file to plugins folder
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		this.getCommand("thekeeper").setExecutor(new BetterKeeperCommand(this));
		
	}
    @Override
    public void onDisable() {
    	getLogger().info("Disabling BetterKeeper!");
    }
    
}
