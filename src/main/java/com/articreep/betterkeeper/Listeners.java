package com.articreep.betterkeeper;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Listeners implements Listener {
	Main plugin;
	public Listeners(Main plugin) {
		this.plugin = plugin;
	}
	
	boolean chat = false;
	Map<Player, Boolean> dropValues = new HashMap<Player, Boolean>();
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
	        	p.openInventory(BetterKeeperCommand.createInventoryPatchNotes(p));
	        }
	        if (e.getRawSlot() == 11) {
	        	p.sendMessage(ChatColor.GOLD + "Enter the server number of the server you want to join:");
	        	chat = true;
	        	p.closeInventory();
	        }
	        if (e.getRawSlot() == 14) {
	            Bukkit.dispatchCommand(p, "pittutorial");
	            p.closeInventory();
	        }
	        if (e.getRawSlot() == 15) {
	        	p.openInventory(BetterKeeperCommand.createInventorySettings(p));
	        }
	        if (e.getRawSlot() == 12) {
	        	Bukkit.dispatchCommand(p, "warp spawn");
	        	p.closeInventory();
	        }
	       	if (e.getRawSlot() == 18) {
	       		p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Hypixel Pit Forums!");
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/forums/the-pit.151/");
	       		p.closeInventory();
	       	}
    	}
        if (e.getView().getTitle() == "Patch Notes") {
        	e.setCancelled(true);
        	if (clickedItem == null || clickedItem.getType() == Material.AIR) {
        		return;
        	} 
        	if (e.getRawSlot() >= 0 && e.getRawSlot() <= 18) {
        		p.sendMessage(ChatColor.YELLOW + "Click the link below for the patch notes:");
        		p.closeInventory();
        	}
        	if (e.getRawSlot() == 0) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/new-ptl-game-the-hypixel-pit.1569497/");
        	}
        	if (e.getRawSlot() == 1) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/quick-patch-notes.1571988/");
        	}
        	if (e.getRawSlot() == 2) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-update-0-2-prestige-and-stats.1578662/");
        	}
        	if (e.getRawSlot() == 3) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/tiny-post-update-patch.1579902/");
        	}
        	if (e.getRawSlot() == 4) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-update-0-3-improved-map-events-and-contracts.1595492/");
        	}
        	if (e.getRawSlot() == 5) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/tiny-patch-0-3-1.1619060/");
        	}
        	if (e.getRawSlot() == 6) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-3-5-second-map-and-mystic-well.1729768/");
        	}
        	if (e.getRawSlot() == 7) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-3-6-fishing-club-patch.1739100/");
        	}
        	if (e.getRawSlot() == 8) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-3-7-pizza-patch.1761810/");
        	}
        	if (e.getRawSlot() == 9) {
        		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/recent-bugfixes.1806239/");
        	}
        	if (e.getRawSlot() == 10) {
        		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/drop-confirmation-improvements.1765674/");
        	}
        	if (e.getRawSlot() == 11) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-3-8-corals-map.1792819/");
        	}
        	if (e.getRawSlot() == 12) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-3-9-tiny-bugpatch.1982167/");
        	}
        	if (e.getRawSlot() == 13) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-update-0-4-events-extravaganza.2018236/");
        	}
        	if (e.getRawSlot() == 14) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-4-1-castle-map-dark-pants.2058146/");
        	}
        	if (e.getRawSlot() == 15) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/quick-patch.2633726/");
        	}
        	if (e.getRawSlot() == 16) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-0-4-2-genesis-map.2645445/");
        	}
        	if (e.getRawSlot() == 17) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-update-1-0-0-release.2804774/");
        	}
        	if (e.getRawSlot() == 18) {
	       		p.sendMessage(ChatColor.YELLOW + "https://hypixel.net/threads/the-pit-1-0-1-fixes.2829510/");
        	}
        	if (e.getRawSlot() == 31) {
        		Bukkit.dispatchCommand(p, "thekeeper");
        	}
        	
        }
        if (e.getView().getTitle() == "Settings") {
        	e.setCancelled(true);
        	Inventory inv = e.getInventory();
        	if (e.getRawSlot() == 10) {
        		if (BetterKeeperCommand.servernumber == false) {
        			inv.setItem(10, BetterKeeperCommand.createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "VISIBLE!", ChatColor.GRAY + "The server number shows up in the scoreboard!"));
        			BetterKeeperCommand.servernumber = true;
        		} else {
        			inv.setItem(10, BetterKeeperCommand.createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "DISABLED!", ChatColor.GRAY + "The server number will not show up in the scoreboard!"));
        			BetterKeeperCommand.servernumber = false;
        		}
        	}
        	if (e.getRawSlot() == 11) {
        		if (BetterKeeperCommand.dropconfirm == false) {
        			inv.setItem(11, BetterKeeperCommand.createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "ON!", ChatColor.GRAY + "You must tap twice to drop!"));
        			BetterKeeperCommand.dropconfirm = true;
        		} else {
        			inv.setItem(11, BetterKeeperCommand.createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "OFF!", ChatColor.GRAY + "No accidental drop protection!"));
        			BetterKeeperCommand.dropconfirm = false;
        		}
        	}
        	if (e.getRawSlot() == 12) {
        		if (BetterKeeperCommand.trades == false) {
        			inv.setItem(12, BetterKeeperCommand.createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "YES!", ChatColor.GRAY + "Allow trades from other players!"));
        			BetterKeeperCommand.trades = true;
        		} else {
        			inv.setItem(12, BetterKeeperCommand.createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "NO!", ChatColor.GRAY + "No commerce!"));
        			BetterKeeperCommand.trades = false;
        		}
        	}
        	if (e.getRawSlot() == 14) {
        		if (BetterKeeperCommand.sword == false) {
        			inv.setItem(14, BetterKeeperCommand.createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED!", ChatColor.GRAY + "You spawn with an iron sword!"));
        			BetterKeeperCommand.sword = true;
        		} else {
        			inv.setItem(14, BetterKeeperCommand.createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "DISABLED!", ChatColor.GRAY + "You don't spawn with a iron sword!"));
        			BetterKeeperCommand.sword = false;
        		}
        	}
        	if (e.getRawSlot() == 15) {
        		if (BetterKeeperCommand.bow == false) {
        			inv.setItem(15, BetterKeeperCommand.createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED!", ChatColor.GRAY + "You spawn with a bow!"));
        			BetterKeeperCommand.bow = true;
        		} else {
        			inv.setItem(15, BetterKeeperCommand.createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "DISABLED!", ChatColor.GRAY + "You don't spawn with a bow!"));
        			BetterKeeperCommand.bow = false;
        		}
        	}
        	if (e.getRawSlot() == 16) {
        		if (BetterKeeperCommand.itempickup == 0) {
            		inv.setItem(16, BetterKeeperCommand.createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "GIVE ME EVERYTHING!", ChatColor.GRAY + "Pick up everything you can!"));
            		BetterKeeperCommand.itempickup = 2;
            		return;
            	}
            	if (BetterKeeperCommand.itempickup == 2) {
            		inv.setItem(16, BetterKeeperCommand.createGuiItem(Material.ORANGE_DYE, ChatColor.GOLD + "" + ChatColor.BOLD + "NO USELESS ARMOR!", ChatColor.GRAY + "Pick up everything except armor that is ", ChatColor.GRAY + "equivalent or worse than your current armor!"));
            		BetterKeeperCommand.itempickup = 1;
            		return;
            	}
            	if (BetterKeeperCommand.itempickup == 1) {
            		inv.setItem(16, BetterKeeperCommand.createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "NOTHING!", ChatColor.GRAY + "Pick up literally nothing except items related to you!"));
            		BetterKeeperCommand.itempickup = 0;
            		return;
            	}
        	}
        	if (e.getRawSlot() == 29) {
        		p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 3.0F, 1);
        		p.sendMessage(ChatColor.RED + "Too lazy to code a replica of /pitchat!");
        	}
        	if (e.getRawSlot() == 33) {
        		p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 3.0F, 1);
        		p.sendMessage(ChatColor.RED + "Don't know what to put here..");
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
    		if (BetterKeeperCommand.dropconfirm == true) {
    			if (dropValues.get(player) == null) {
					player.sendMessage(ChatColor.RED + "You must double-tap to drop this item!");
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
}
