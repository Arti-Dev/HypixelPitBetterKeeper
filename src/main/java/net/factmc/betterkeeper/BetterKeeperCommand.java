package net.factmc.betterkeeper;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.gson.JsonObject;

import net.hypixel.api.HypixelAPI;
import net.md_5.bungee.api.ChatColor;


public class BetterKeeperCommand implements CommandExecutor {
	Main plugin;
	public BetterKeeperCommand(Main plugin) {
		this.plugin = plugin;
		this.api = new HypixelAPI(UUID.fromString(plugin.getConfig().getString("hypixelapikey")));
	}
	public static boolean servernumber = true;
	public static boolean dropconfirm = false;
	public static boolean trades = true;
	public static boolean sword = true;
	public static boolean bow = true;
	public static int itempickup = 2;
	HypixelAPI api = null;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			player.openInventory(createInventoryMainMenu(player));
			CompletableFuture<String> hypixel = getPlayTime(api, player.getUniqueId().toString());
			while(!hypixel.isDone()) {
				continue;
			}
			try {
				player.getOpenInventory().getTopInventory().setItem(13,createuserskull(player.getUniqueId(), ChatColor.DARK_AQUA + "You", ChatColor.AQUA + "You've joined The Pit " + hypixel.get() + " times!"));
			} catch (InterruptedException e) {
				e.printStackTrace();
				player.getOpenInventory().getTopInventory().setItem(13,createuserskull(player.getUniqueId(), ChatColor.DARK_AQUA + "You", ChatColor.AQUA + "There was an error grabbing your stats!"));
			} catch (ExecutionException e) {
				Bukkit.getLogger().severe("The API Key specified in config.yml is not valid!");
				player.getOpenInventory().getTopInventory().setItem(13,createuserskull(player.getUniqueId(), ChatColor.DARK_AQUA + "You", ChatColor.AQUA + "There was an error grabbing your stats!"));
			}
			return true;
		    }
		return false;
	}
	// Nice little method to create a gui item with a custom name, and description
    public static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        

        item.setItemMeta(meta);

        return item;
    }
    // Method to create player skulls (owners set by UUID)
    @SuppressWarnings("deprecation")
	protected static ItemStack createcustomskull(final String playername, final String name, final String... lore) { 
    	final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
    	SkullMeta head = (SkullMeta) item.getItemMeta();
    	head.setOwner(playername);
    	head.setLore(Arrays.asList(lore));
    	head.setDisplayName(name);
    	item.setItemMeta(head);
    	return item;
    }
    // Method to create the user's skull
    protected static ItemStack createuserskull(final UUID playerid, final String name, final String... lore) { 
    	final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
    	SkullMeta head = (SkullMeta) item.getItemMeta();
    	// Set player head name
    	Player player = Bukkit.getPlayer(playerid);
    	head.setOwningPlayer(player);
    	head.setLore(Arrays.asList(lore));
    	head.setDisplayName(name);
    	item.setItemMeta(head);
    	return item;
    }
    // Method to create the first inventory
    public static Inventory createInventoryMainMenu(Player player) {
    	Inventory inv;
		inv = Bukkit.createInventory(null, 27, "The Keeper");
		inv.setItem(0,createGuiItem(Material.WRITABLE_BOOK, ChatColor.GREEN + "Patch Notes", ChatColor.YELLOW + "Every update about the Pit,", ChatColor.YELLOW + "all in one place!"));
		inv.setItem(4,createGuiItem(Material.CHEST_MINECART, ChatColor.YELLOW + "" + ChatColor.BOLD + "ITEM STASH!", ChatColor.GRAY + "There's nothing in your stash!"));
		inv.setItem(8, createGuiItem(Material.CLOCK, ChatColor.DARK_PURPLE + "Night Schedule", ChatColor.LIGHT_PURPLE + "It's currently " + getTime(player.getWorld()) + ".", "", 
				ChatColor.AQUA + "Daytime lasts 24 minutes.", ChatColor.GRAY + "Nighttime lasts 12 minutes."));
		inv.setItem(11,createGuiItem(Material.OAK_SIGN, ChatColor.GREEN + "Warp to a specific instance", ChatColor.GRAY + "Enter a server number!"));
		inv.setItem(12,createGuiItem(Material.DARK_OAK_DOOR, ChatColor.GREEN + "Back to Spawn", ChatColor.GRAY + "Tired of frantically left-clicking on other players?",
				ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Wait, that's not what you're here for..", "", ChatColor.YELLOW + "Click to warp!"));
		inv.setItem(13,createuserskull(player.getUniqueId(), ChatColor.DARK_AQUA + "You", ChatColor.GRAY + "Loading..."));
        inv.setItem(14,createGuiItem(Material.WOODEN_SWORD, ChatColor.GREEN + "PitTutorial", ChatColor.GRAY + "Need a refresher on how things work here?", "", ChatColor.YELLOW + "Click to warp!"));
        inv.setItem(15,createGuiItem(Material.COMPARATOR, ChatColor.GREEN + "Game Settings", ChatColor.GRAY + "Tweak some settings within the Pit."));
        inv.setItem(18,createGuiItem(Material.DIRT, ChatColor.GREEN + "Visit the Hypixel Pit Forums!", ChatColor.YELLOW + "Click for the link!"));
        inv.setItem(22,createGuiItem(Material.EMERALD, ChatColor.GREEN + "" + ChatColor.BOLD + "Tip!", ChatColor.GRAY + "Use /play pit to move to a random Pit Instance!"));
        inv.setItem(26,createGuiItem(Material.MAP, ChatColor.GREEN + "Map Rotation", ChatColor.YELLOW + "Next up: Castle on 9/1/2020", ChatColor.GRAY + "Maps rotate every Tuesday 12:00 AM EST."));
        return inv;
    }
    public static Inventory createInventoryPatchNotes(Player player) {
    	Inventory inv;
		inv = Bukkit.createInventory(null, 36, "Patch Notes");
		inv.setItem(0,createGuiItem(Material.DIRT, ChatColor.GREEN + "Pit Initial Release", ChatColor.GRAY + "The Pit v0.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(1,createcustomskull("Minikloon", ChatColor.GREEN + "Quick Patch Notes", ChatColor.GRAY + "The Pit v0.1.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(2,createGuiItem(Material.DIAMOND, ChatColor.GREEN + "Update - Prestige and Stats!", ChatColor.GRAY + "The Pit v0.2", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(3,createcustomskull("Minikloon", ChatColor.GREEN + "Tiny post-update patch", ChatColor.GRAY + "The Pit v0.2.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(4,createGuiItem(Material.WRITABLE_BOOK, ChatColor.GREEN + "Improved Map, Events, and Contracts!", ChatColor.GRAY + "The Pit v0.3", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(5,createcustomskull("Minikloon", ChatColor.GREEN + "Tiny Patch", ChatColor.GRAY + "The Pit v0.3.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(6,createGuiItem(Material.LEATHER_LEGGINGS, ChatColor.GREEN + "Second Map and Mystic Well!", ChatColor.GRAY + "The Pit v0.3.5", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(7,createGuiItem(Material.FISHING_ROD, ChatColor.GREEN + "Fishing Club Patch", ChatColor.GRAY + "The Pit v0.3.6", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(8,createGuiItem(Material.FURNACE, ChatColor.GREEN + "Pizza Patch", ChatColor.GRAY + "The Pit v0.3.7", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(9,createcustomskull("Minikloon", ChatColor.GREEN + "Recent bugfixes", ChatColor.GRAY + "The Pit v0.3.7", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(10,createGuiItem(Material.DROPPER, ChatColor.GREEN + "Drop Confirmation Update", ChatColor.GRAY + "The Pit v0.3.7", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(11,createGuiItem(Material.TUBE_CORAL, ChatColor.GREEN + "Corals Map", ChatColor.GRAY + "The Pit v0.3.8", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(12,createGuiItem(Material.MINECART, ChatColor.GREEN + "Tiny Bugpatch", ChatColor.GRAY + "The Pit v0.3.9", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(13,createGuiItem(Material.CAKE, ChatColor.GREEN + "Events Extravaganza", ChatColor.GRAY + "The Pit v0.4", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(14,createGuiItem(Material.GOLDEN_HELMET, ChatColor.GREEN + "Castle Map & Dark Pants", ChatColor.GRAY + "The Pit v0.4.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(15,createcustomskull("Minikloon", ChatColor.GREEN + "Quick Patch", ChatColor.GRAY + "The Pit v0.4.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(16,createGuiItem(Material.DIAMOND_CHESTPLATE, ChatColor.GREEN + "Genesis Map", ChatColor.GRAY + "The Pit v0.4.2", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(17,createGuiItem(Material.CLOCK, ChatColor.GREEN + "Hypixel Pit Release!", ChatColor.GRAY + "The Pit v1.0.0", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(18,createcustomskull("Minikloon", ChatColor.GREEN + "General Fixes", ChatColor.GRAY + "The Pit v1.0.1", "", ChatColor.YELLOW + "Click for the link!"));
		inv.setItem(31,createGuiItem(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "The Keeper"));
        return inv;
    }
    public static Inventory createInventorySettings(Player player) {
    	Inventory inv;
    	inv = Bukkit.createInventory(null, 54, "Settings");
    	inv.setItem(1, createGuiItem(Material.NAME_TAG, ChatColor.GREEN + "Enable Server Number in Scoreboard"));
    	inv.setItem(2, createGuiItem(Material.HOPPER, ChatColor.GREEN + "Drop Confirmation"));
    	inv.setItem(3, createGuiItem(Material.MINECART, ChatColor.GREEN + "Allow/Disallow Incoming Trade Requests"));
    	inv.setItem(5, createGuiItem(Material.IRON_SWORD, ChatColor.GREEN + "Spawn with an Iron Sword"));
    	inv.setItem(6, createGuiItem(Material.BOW, ChatColor.GREEN + "Spawn with a Bow"));
    	inv.setItem(7, createGuiItem(Material.IRON_CHESTPLATE, ChatColor.GREEN + "Item Pickups"));
    	if (servernumber == true) {
    		inv.setItem(10, createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "VISIBLE!", ChatColor.GRAY + "The server number shows up in the scoreboard!"));
    	} else inv.setItem(10, createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "DISABLED!", ChatColor.GRAY + "The server number will not show up in the scoreboard!"));
    	if (dropconfirm == true) {
    		inv.setItem(11, createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "ON!", ChatColor.GRAY + "You must tap twice to drop!"));
    	} else inv.setItem(11, createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "OFF!", ChatColor.GRAY + "No accidental drop protection!"));
    	if (trades == true) {
    		inv.setItem(12, createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "YES!", ChatColor.GRAY + "Allow trades from other players!"));
    	} else inv.setItem(12, createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "NO!", ChatColor.GRAY + "No commerce!"));
    	if (sword == true) {
    		inv.setItem(14, createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED!", ChatColor.GRAY + "You spawn with an iron sword!"));
    	} else inv.setItem(14, createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "DISABLED!", ChatColor.GRAY + "You don't spawn with a iron sword!"));
    	if (bow == true) {
    		inv.setItem(15, createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED!", ChatColor.GRAY + "You spawn with a bow!"));
    	} else inv.setItem(15, createGuiItem(Material.RED_DYE, ChatColor.RED + "" + ChatColor.BOLD + "DISABLED!", ChatColor.GRAY + "You don't spawn with a bow!"));
    	if (itempickup == 2) {
    		inv.setItem(16, createGuiItem(Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "GIVE ME EVERYTHING!", ChatColor.GRAY + "Pick up everything you can!"));
    	}
    	if (itempickup == 1) {
    		inv.setItem(16, createGuiItem(Material.ORANGE_DYE, ChatColor.GOLD + "" + ChatColor.BOLD + "NO USELESS ARMOR!", ChatColor.GRAY + "Pick up everything except armor that is ", ChatColor.GRAY + "7equivalent or worse than your current armor!"));
    	}
    	if (itempickup == 0) {
    		inv.setItem(16, createGuiItem(Material.LIME_DYE, ChatColor.RED + "" + ChatColor.BOLD + "NOTHING!", ChatColor.GRAY + "Pick up literally nothing!"));
    	}
    	inv.setItem(29, createGuiItem(Material.WRITABLE_BOOK, ChatColor.GREEN + "/pitchat", ChatColor.GRAY + "Click here to change your chat settings!"));
    	inv.setItem(33, createGuiItem(Material.COBWEB, ChatColor.GREEN + "API Settings", ChatColor.GRAY + "Click here to change your API settings!"));
    	inv.setItem(49, createGuiItem(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "The Keeper"));
    	return inv;
    }
    public static CompletableFuture<String> getPlayTime(HypixelAPI api, String uuid) {
		return api.getPlayerByUuid(UUID.fromString(uuid)).thenApply((reply) -> {
			JsonObject player = reply.getPlayer();
			JsonObject stats = player.getAsJsonObject("stats");
			JsonObject pit = stats.getAsJsonObject("Pit");
			JsonObject pit2 = pit.getAsJsonObject("pit_stats_ptl");
			return pit2.get("joins").getAsString();
		});
    	
    }
    public static String getTime(World world) {
    	long time = world.getTime();
    	int hours = (int) (time / 1000);
    	int minutes = (int) (time % 1000);
    	String string = (hours < 10 ? "0" : "") + hours + ":" + (minutes < 166.6 ? "0" : "") + Math.round(minutes / 16.6);
    	return string;
    }
}


