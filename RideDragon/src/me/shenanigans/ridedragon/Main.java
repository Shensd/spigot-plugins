package me.shenanigans.ridedragon;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	HashMap<UUID, Entity[]> map = new HashMap<UUID, Entity[]>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getLabel().equalsIgnoreCase("ridedragon")) {
				if(args.length != 1) {
					player.sendMessage("usage: /ridedragon [on|off]");
					return true;
				}
				if(args[0].equalsIgnoreCase("On")) {
					if(map.containsKey(player.getUniqueId())) {
						player.sendMessage("You're already riding a dragon!");
					} else {
						Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
						EnderDragon dragon = (EnderDragon) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_DRAGON);
						
						dragon.setInvulnerable(true);
						pig.setInvulnerable(true);
						
						dragon.setPassenger(pig);
						pig.setPassenger(player);
						
						map.put(player.getUniqueId(), new Entity[] {pig, dragon});
					}
				} else if(args[0].equalsIgnoreCase("Off")) {
					if(!map.containsKey(player.getUniqueId())) {
						player.sendMessage("You aren't riding a dragon!");
					} else {
						map.get(player.getUniqueId())[0].remove();
						map.get(player.getUniqueId())[1].remove();
						map.remove(player.getUniqueId());
					}
				} 
				
				return true;
			}
		}
		return false;
	}
}
