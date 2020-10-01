package net.factmc.betterkeeper;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Listeners implements Listener {
	Main plugin;
	
	 public Listeners(Main plugin) {
		this.plugin = plugin;
	 }
	boolean chat = false;
	Map<Player, Boolean> dropValues = new HashMap<Player, Boolean>();
	BetterKeeperCommand invcreate = new BetterKeeperCommand(plugin);
    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
    	final Player p = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();
    	if (e.getView().getTitle() == "The Keeper") {
	        e.setCancelled(true);
	        // verify current item is not null
	        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
	        if (e.getRawSlot() == 0) {
	        	p.openInventory(invcreate.createInventoryPatchNotes(p));
	        }
	        if (e.getRawSlot() == 11) {
	        	p.sendMessage("§6Enter the server number of the server you want to join:");
	        	chat = true;
	        	p.closeInventory();
	        }
	        if (e.getRawSlot() == 14) {
	            Bukkit.dispatchCommand(p, "pittutorial");
	            p.closeInventory();
	        }
	        if (e.getRawSlot() == 15) {
	        	p.openInventory(invcreate.createInventorySettings(p));
	        }
	        if (e.getRawSlot() == 12) {
	        	Bukkit.dispatchCommand(p, "warp spawn");
	        	p.closeInventory();
	        }
	       	if (e.getRawSlot() == 18) {
	       		p.sendMessage("§e§lHypixel Pit Forums!");
	       		p.sendMessage("§ehttps://hypixel.net/forums/the-pit.151/");
	       		p.closeInventory();
	       	}
    	}
        if (e.getView().getTitle() == "Patch Notes") {
        	e.setCancelled(true);
        	if (clickedItem == null || clickedItem.getType() == Material.AIR) {
        		return;
        	} 
        	if (e.getRawSlot() >= 0 && e.getRawSlot() <= 18) {
        		p.sendMessage("§e§lClick the link below for the patch notes:");
        		p.closeInventory();
        	}
        	if (e.getRawSlot() == 0) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/new-ptl-game-the-hypixel-pit.1569497/");
        	}
        	if (e.getRawSlot() == 1) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/quick-patch-notes.1571988/");
        	}
        	if (e.getRawSlot() == 2) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-update-0-2-prestige-and-stats.1578662/");
        	}
        	if (e.getRawSlot() == 3) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/tiny-post-update-patch.1579902/");
        	}
        	if (e.getRawSlot() == 4) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-update-0-3-improved-map-events-and-contracts.1595492/");
        	}
        	if (e.getRawSlot() == 5) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/tiny-patch-0-3-1.1619060/");
        	}
        	if (e.getRawSlot() == 6) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-3-5-second-map-and-mystic-well.1729768/");
        	}
        	if (e.getRawSlot() == 7) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-3-6-fishing-club-patch.1739100/");
        	}
        	if (e.getRawSlot() == 8) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-3-7-pizza-patch.1761810/");
        	}
        	if (e.getRawSlot() == 9) {
        		p.sendMessage("§ehttps://hypixel.net/threads/recent-bugfixes.1806239/");
        	}
        	if (e.getRawSlot() == 10) {
        		p.sendMessage("§ehttps://hypixel.net/threads/drop-confirmation-improvements.1765674/");
        	}
        	if (e.getRawSlot() == 11) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-3-8-corals-map.1792819/");
        	}
        	if (e.getRawSlot() == 12) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-3-9-tiny-bugpatch.1982167/");
        	}
        	if (e.getRawSlot() == 13) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-update-0-4-events-extravaganza.2018236/");
        	}
        	if (e.getRawSlot() == 14) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-4-1-castle-map-dark-pants.2058146/");
        	}
        	if (e.getRawSlot() == 15) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/quick-patch.2633726/");
        	}
        	if (e.getRawSlot() == 16) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-0-4-2-genesis-map.2645445/");
        	}
        	if (e.getRawSlot() == 17) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-update-1-0-0-release.2804774/");
        	}
        	if (e.getRawSlot() == 18) {
	       		p.sendMessage("§ehttps://hypixel.net/threads/the-pit-1-0-1-fixes.2829510/");
        	}
        	if (e.getRawSlot() == 31) {
        		Bukkit.dispatchCommand(p, "thekeeper");
        	}
        	
        }
        if (e.getView().getTitle() == "Settings") {
        	e.setCancelled(true);
        	Inventory inv = e.getInventory();
        	if (e.getRawSlot() == 10) {
        		if (invcreate.servernumber == false) {
        			inv.setItem(10, invcreate.createGuiItem(Material.LIME_DYE, "§a§lVISIBLE!", "§7The server number shows up in the scoreboard!"));
        			invcreate.servernumber = true;
        		} else {
        			inv.setItem(10, invcreate.createGuiItem(Material.RED_DYE, "§c§lDISABLED!", "§7The server number will not show up in the scoreboard!"));
        			invcreate.servernumber = false;
        		}
        	}
        	if (e.getRawSlot() == 11) {
        		if (invcreate.dropconfirm == false) {
        			inv.setItem(11, invcreate.createGuiItem(Material.LIME_DYE, "§a§lON!", "§7You must tap twice to drop!"));
        			invcreate.dropconfirm = true;
        		} else {
        			inv.setItem(11, invcreate.createGuiItem(Material.RED_DYE, "§c§lOFF!", "§7No accidental drop protection!"));
        			invcreate.dropconfirm = false;
        		}
        	}
        	if (e.getRawSlot() == 12) {
        		if (invcreate.trades == false) {
        			inv.setItem(12, invcreate.createGuiItem(Material.LIME_DYE, "§a§lYES!", "§7Allow trades from other players!"));
        			invcreate.trades = true;
        		} else {
        			inv.setItem(12, invcreate.createGuiItem(Material.RED_DYE, "§c§lNO!", "§7No commerce!"));
        			invcreate.trades = false;
        		}
        	}
        	if (e.getRawSlot() == 14) {
        		if (invcreate.sword == false) {
        			inv.setItem(14, invcreate.createGuiItem(Material.LIME_DYE, "§a§lENABLED!", "§7You spawn with an iron sword!"));
        			invcreate.sword = true;
        		} else {
        			inv.setItem(14, invcreate.createGuiItem(Material.RED_DYE, "§c§lDISABLED!", "§7You don't spawn with a iron sword!"));
        			invcreate.sword = false;
        		}
        	}
        	if (e.getRawSlot() == 15) {
        		if (invcreate.bow == false) {
        			inv.setItem(15, invcreate.createGuiItem(Material.LIME_DYE, "§a§lENABLED!", "§7You spawn with a bow!"));
        			invcreate.bow = true;
        		} else {
        			inv.setItem(15, invcreate.createGuiItem(Material.RED_DYE, "§c§lDISABLED!", "§7You don't spawn with a bow!"));
        			invcreate.bow = false;
        		}
        	}
        	if (e.getRawSlot() == 16) {
        		if (invcreate.itempickup == 0) {
            		inv.setItem(16, invcreate.createGuiItem(Material.LIME_DYE, "§a§lGIVE ME EVERYTHING!", "§7Pick up everything you can!"));
            		invcreate.itempickup = 2;
            		return;
            	}
            	if (invcreate.itempickup == 2) {
            		inv.setItem(16, invcreate.createGuiItem(Material.ORANGE_DYE, "§6§lNO USELESS ARMOR!", "§7Pick up everything except armor that is ", "§7equivalent or worse than your current armor!"));
            		invcreate.itempickup = 1;
            		return;
            	}
            	if (invcreate.itempickup == 1) {
            		inv.setItem(16, invcreate.createGuiItem(Material.RED_DYE, "§c§lNOTHING!", "§7Pick up literally nothing except items related to you!"));
            		invcreate.itempickup = 0;
            		return;
            	}
        	}
        	if (e.getRawSlot() == 29) {
        		p.sendMessage("§cToo lazy to code a replica of /pitchat!");
        	}
        	if (e.getRawSlot() == 33) {
        		p.sendMessage("§cDon't know what to put here..");
        	}
        	if (e.getRawSlot() == 49) {
        		Bukkit.dispatchCommand(p, "thekeeper");
        	}
        }
}

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().getHolder() == null) {
          e.setCancelled(true);
        }
    }
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
    	if (chat == false) return;
    	event.setCancelled(true);
    	Player player = event.getPlayer();
    	new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.dispatchCommand(player, "warp " + event.getMessage());
				
			}
    		
    	}.runTask(plugin);
    	chat = false;
    }
    @EventHandler
    public void onMysticDrop(PlayerDropItemEvent event) {
    	Player player = event.getPlayer();
    	Item itemdrop = event.getItemDrop();
    	if (itemdrop.getItemStack().getType() == Material.GOLDEN_SWORD) {
    		if (invcreate.dropconfirm == true) {
    			if (dropValues.get(player) == null) {
					player.sendMessage("§cYou must double-tap to drop this item!");
					dropValues.put(player, true);
					event.setCancelled(true);
					new BukkitRunnable() {
						@Override
						public void run() {
							dropValues.remove(player);      // There are probably better methods of doing this, as this is slightly buggy
						}
					}.runTaskLater(plugin, 20);
					return;
    			}
    			for (Player i : dropValues.keySet()) {
    				if (i == player && dropValues.get(i) == true) {
    					dropValues.remove(player);
    					break;	
    				}
    			}
    		}
    	}
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (plugin.config.getBoolean("hypixelapikey")) {
            player.sendMessage("You are awesome!");
        } else {
            player.sendMessage("You are not awesome...");
        }
    }
}
