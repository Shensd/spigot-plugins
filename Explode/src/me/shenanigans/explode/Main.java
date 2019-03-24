package me.shenanigans.explode;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(cmd.getLabel().equalsIgnoreCase("explode")) {
				
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 100, 1);
				for(int i = 0 ; i < 10; i++) {
					player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1);
				}
				player.setVelocity(new Vector(0, 3, 0));
			}
			
			return true;
		}
		
		return false;
	}
}
