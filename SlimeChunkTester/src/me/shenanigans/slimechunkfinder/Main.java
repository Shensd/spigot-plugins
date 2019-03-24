package me.shenanigans.slimechunkfinder;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private boolean isInSlimeChunk(Player player) {
		long seed = player.getWorld().getSeed();
		long chunkX = player.getLocation().getChunk().getX();
		long chunkZ = player.getLocation().getChunk().getZ();
		
		Random rand = new Random(
				seed +
				(chunkX * chunkX * 0x4c1906) +
				(chunkX * 0x5ac0db) +
				(chunkZ * chunkZ) * 0x4307a7L +
				(chunkZ * 0x5f24f) ^ 0x3ad8025f
				);
		return rand.nextInt(10) == 0;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		
		if(cmd.getName().equalsIgnoreCase("isslime") && sender instanceof Player) {
			
			Player player = (Player) sender;
			String output = "Current chunk " + ChatColor.BOLD;
			
			if(isInSlimeChunk(player)) {
				output += ChatColor.GREEN + "is";
			} else {
				output += ChatColor.RED + "is not";
			}
			
			output += ChatColor.RESET + " a slime chunk";
			
			player.sendMessage(output);
				
			return true;
		}
		
		return false;
	}
}
