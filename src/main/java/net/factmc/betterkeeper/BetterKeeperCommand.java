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
		inv.setItem(0,createGuiItem(Material.WRITABLE_BOOK, "�aPatch Notes", "�eEvery update about the Pit,","�eall in one place!"));
		inv.setItem(4,createGuiItem(Material.CHEST_MINECART,"�e�lITEM STASH!","�7There's nothing in your stash!"));
		inv.setItem(8, createGuiItem(Material.CLOCK, "�5Night Schedule", ChatColor.LIGHT_PURPLE + "It's currently " + getTime(player.getWorld()) + ".", "", 
				"�bDaytime lasts 24 minutes.", "�7Nighttime lasts 12 minutes."));
		inv.setItem(11,createGuiItem(Material.OAK_SIGN, "�aWarp to a specific instance", "�7Enter a server number!"));
		inv.setItem(12,createGuiItem(Material.DARK_OAK_DOOR, "�aBack to Spawn", "�7Tired of frantically left-clicking on other players?",
				"�8�oWait, that's not what you're here for..", "", "�eClick to warp!"));
		inv.setItem(13,createuserskull(player.getUniqueId(), "�3You", "�bLoading..."));
        inv.setItem(14,createGuiItem(Material.WOODEN_SWORD, "�aPitTutorial", "�7Need a refresher on how things work here?", "", "�eClick to warp!"));
        inv.setItem(15,createGuiItem(Material.COMPARATOR, "�cGame Settings", "�7Tweak some settings within the Pit."));
        inv.setItem(18,createGuiItem(Material.DIRT,"�aVisit the Hypixel Pit Forums!", "�eClick for the link!"));
        inv.setItem(22,createGuiItem(Material.EMERALD,"�a�lTip!","�7Use /play pit to move to a random Pit Instance!"));
        inv.setItem(26,createGuiItem(Material.MAP,"�aMap Rotation", "�eNext up: Castle on 9/1/2020", "�7Maps rotate every Tuesday 12:00 AM EST."));
        return inv;
    }
    public static Inventory createInventoryPatchNotes(Player player) {
    	Inventory inv;
		inv = Bukkit.createInventory(null, 36, "Patch Notes");
		inv.setItem(0,createGuiItem(Material.DIRT, "�aPit Initial Release", "�7The Pit v0.1", "", "�eClick for the link!"));
		inv.setItem(1,createcustomskull("Minikloon", "�aQuick Patch Notes", "�7The Pit v0.1.1", "", "�eClick for the link!"));
		inv.setItem(2,createGuiItem(Material.DIAMOND, "�aUpdate - Prestige and Stats!", "�7The Pit v0.2", "", "�eClick for the link!"));
		inv.setItem(3,createcustomskull("Minikloon", "�aTiny post-update patch", "�7The Pit v0.2.1", "", "�eClick for the link!"));
		inv.setItem(4,createGuiItem(Material.WRITABLE_BOOK, "�aImproved Map, Events, and Contracts!", "�7The Pit v0.3", "", "�eClick for the link!"));
		inv.setItem(5,createcustomskull("Minikloon", "�aTiny Patch", "�7The Pit v0.3.1", "", "�eClick for the link!"));
		inv.setItem(6,createGuiItem(Material.LEATHER_LEGGINGS, "�aSecond Map and Mystic Well!", "�7The Pit v0.3.5", "", "�eClick for the link!"));
		inv.setItem(7,createGuiItem(Material.FISHING_ROD, "�aFishing Club Patch", "�7The Pit v0.3.6", "", "�eClick for the link!"));
		inv.setItem(8,createGuiItem(Material.FURNACE, "�aPizza Patch", "�7The Pit v0.3.7", "", "�eClick for the link!"));
		inv.setItem(9,createcustomskull("Minikloon","�aRecent bugfixes", "�7The Pit v0.3.7", "", "�eClick for the link!"));
		inv.setItem(10,createGuiItem(Material.DROPPER, "�aDrop Confirmation Update", "�7The Pit v0.3.7", "", "�eClick for the link!"));
		inv.setItem(11,createGuiItem(Material.TUBE_CORAL, "�aCorals Map", "�7The Pit v0.3.8", "", "�eClick for the link!"));
		inv.setItem(12,createGuiItem(Material.MINECART, "�aTiny Bugpatch", "�7The Pit v0.3.9", "", "�eClick for the link!"));
		inv.setItem(13,createGuiItem(Material.CAKE, "�aEvents Extravaganza", "�7The Pit v0.4", "", "�eClick for the link!"));
		inv.setItem(14,createGuiItem(Material.GOLDEN_HELMET, "�aCastle Map & Dark Pants", "�7The Pit v0.4.1", "", "�eClick for the link!"));
		inv.setItem(15,createcustomskull("Minikloon","�aQuick Patch", "�7The Pit v0.4.1", "", "�eClick for the link!"));
		inv.setItem(16,createGuiItem(Material.DIAMOND_CHESTPLATE, "�aGenesis Map", "�7The Pit v0.4.2", "", "�eClick for the link!"));
		inv.setItem(17,createGuiItem(Material.CLOCK, "�aHypixel Pit Release!", "�7The Pit v1.0.0", "", "�eClick for the link!"));
		inv.setItem(18,createcustomskull("Minikloon","�aGeneral Fixes", "�7The Pit v1.0.1", "", "�eClick for the link!"));
		inv.setItem(31,createGuiItem(Material.ARROW, "�aGo Back", "�7The Keeper"));
        return inv;
    }
    public static Inventory createInventorySettings(Player player) {
    	Inventory inv;
    	inv = Bukkit.createInventory(null, 54, "Settings");
    	inv.setItem(1, createGuiItem(Material.NAME_TAG, "�aEnable Server Number in Scoreboard"));
    	inv.setItem(2, createGuiItem(Material.HOPPER, "�aDrop Confirmation"));
    	inv.setItem(3, createGuiItem(Material.MINECART, "�aAllow/Disallow Incoming Trade Requests"));
    	inv.setItem(5, createGuiItem(Material.IRON_SWORD, "�aSpawn with an Iron Sword"));
    	inv.setItem(6, createGuiItem(Material.BOW, "�aSpawn with a Bow"));
    	inv.setItem(7, createGuiItem(Material.IRON_CHESTPLATE, "�aItem Pickups"));
    	if (servernumber == true) {
    		inv.setItem(10, createGuiItem(Material.LIME_DYE, "�a�lVISIBLE!", "�7The server number shows up in the scoreboard!"));
    	} else inv.setItem(10, createGuiItem(Material.RED_DYE, "�c�lDISABLED!", "�7The server number will not show up in the scoreboard!"));
    	if (dropconfirm == true) {
    		inv.setItem(11, createGuiItem(Material.LIME_DYE, "�a�lON!", "�7You must tap twice to drop!"));
    	} else inv.setItem(11, createGuiItem(Material.RED_DYE, "�c�lOFF!", "�7No accidental drop protection!"));
    	if (trades == true) {
    		inv.setItem(12, createGuiItem(Material.LIME_DYE, "�a�lYES!", "�7Allow trades from other players!"));
    	} else inv.setItem(12, createGuiItem(Material.RED_DYE, "�c�lNO!", "�7No commerce!"));
    	if (sword == true) {
    		inv.setItem(14, createGuiItem(Material.LIME_DYE, "�a�lENABLED!", "�7You spawn with an iron sword!"));
    	} else inv.setItem(14, createGuiItem(Material.RED_DYE, "�c�lDISABLED!", "�7You don't spawn with a iron sword!"));
    	if (bow == true) {
    		inv.setItem(15, createGuiItem(Material.LIME_DYE, "�a�lENABLED!", "�7You spawn with a bow!"));
    	} else inv.setItem(15, createGuiItem(Material.RED_DYE, "�c�lDISABLED!", "�7You don't spawn with a bow!"));
    	if (itempickup == 2) {
    		inv.setItem(16, createGuiItem(Material.LIME_DYE, "�a�lGIVE ME EVERYTHING!", "�7Pick up everything you can!"));
    	}
    	if (itempickup == 1) {
    		inv.setItem(16, createGuiItem(Material.ORANGE_DYE, "�6�lNO USELESS ARMOR!", "�7Pick up everything except armor that is equivalent or worse than your current armor!"));
    	}
    	if (itempickup == 0) {
    		inv.setItem(16, createGuiItem(Material.LIME_DYE, "�a�cNOTHING!", "�7Pick up literally nothing!"));
    	}
    	inv.setItem(29, createGuiItem(Material.WRITABLE_BOOK, "�a/pitchat", "�7Click here to change your chat settings!"));
    	inv.setItem(33, createGuiItem(Material.COBWEB, "�aAPI Settings", "�7Click here to change your API settings!"));
    	inv.setItem(49, createGuiItem(Material.ARROW, "�aGo Back", "�7The Keeper"));
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
    	String string = (hours < 10 ? "0" : "") + hours + ":" + (minutes < 166.6 ? "0" : "") + Math.round(minutes / 16.6); //TODO fix math not displaying time correctly
    	return string;
    }
}


