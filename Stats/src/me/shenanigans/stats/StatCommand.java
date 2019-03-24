package me.shenanigans.stats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class StatCommand implements CommandExecutor {
	Plugin plugin;
	
	public StatCommand(Plugin plugin) {
		this.plugin = plugin;
	}
	
	private void retrieveStats(Player player, String groupName, String path) {
		for(String key : plugin.getConfig().getConfigurationSection(groupName).getKeys(false)) {
			//All stats at the moment are stored as ints, 
			//but this may change in the furture which may call for a rewrite of this system
			int stat;
			if(plugin.getConfig().contains(path + "." + key)) {
				stat = plugin.getConfig().getInt(path + "." + key);
			} else {
				stat = 0;
			}
			//Retrive the description for the stat
			String name = plugin.getConfig().getString(groupName + "." + key);
			//Send player a message with the stat and description
			player.sendMessage("" + ChatColor.AQUA + stat + ChatColor.RESET + " " + name);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("stats")) {
				
				if(args.length > 0 && !args[0].equalsIgnoreCase("all")) {
					
					if(args[0].equalsIgnoreCase("list")) {
						String groups = "all, ";
						for(String k : plugin.getConfig().getConfigurationSection("statGroups").getKeys(false)) {
							groups += k + ", ";
						}
						groups = groups.substring(0, groups.length()-2);
						player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Available stat groups are: " + groups);
						return true;
					}
					
					String group = "statGroups." + args[0];
					
					if(!plugin.getConfig().contains(group)) {
						player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Selected stat group does not exist");
						player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Use " + ChatColor.GREEN + "/stats list" + ChatColor.RED + " to see all stat groups");
					} else {
						String[] items = (String[]) plugin.getConfig().get(group);
						for(String key : items) {
							String path = "players." + player.getUniqueId();
							
							int stat;
							if(plugin.getConfig().contains(path + "." + key)) {
								stat = plugin.getConfig().getInt(path + "." + key);
							} else {
								stat = 0;
							}
							String name = plugin.getConfig().getString("statNames." + key);
							player.sendMessage("" + ChatColor.AQUA + stat + ChatColor.RESET + " " + name);
						}
					}
					
					return true;
				}
				
				
				/*
				 * List of tracked stats:
				 * - Wolves Tamed (wolvesTamed)
				 * - Player deaths (deaths)
				 * - Dirt blocks broken (dirtBroken)
				 * - Stone (and cobble) block broken (stoneBroken)
				 */
				
				String path = "players." + player.getUniqueId();
				
				//Iterate through player stats
				player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Showing all stats");
				player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Your stats are:");
				retrieveStats(player, "statNames", path);
				
			}
			
			if(cmd.getName().equalsIgnoreCase("statpeek")) {
				Player referencePlayer = (Player) Bukkit.getOfflinePlayer(args[0]);
				
				if(referencePlayer == null) {
					player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Unable to find selected player");
					return true;
				}
				
				String path = "players." + referencePlayer.getUniqueId();
				
				//Iterate through player stats
				player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + " Showing all stats");
				player.sendMessage(ChatColor.BLUE + "[Stats]" + ChatColor.RED + referencePlayer.getDisplayName() + "'s stats are:");
				retrieveStats(referencePlayer, "statNames", path);
				
			}
			
			return true;
		}
		return false;
	}
}
