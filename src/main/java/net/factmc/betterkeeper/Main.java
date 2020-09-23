package net.factmc.betterkeeper;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		this.getCommand("thekeeper").setExecutor(new BetterKeeperCommand());
	}
    @Override
    public void onDisable() {
    	getLogger().info("onDisable has been invoked!");
    }

}
