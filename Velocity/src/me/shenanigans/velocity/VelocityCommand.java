package me.shenanigans.velocity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class VelocityCommand implements CommandExecutor {
	
	Plugin plugin;
	
	public VelocityCommand(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {
				player.sendMessage("usage: /velocity [add|set] x y z");
				return true;
			}
			
			float x, y, z;
			x = y = z = 0.0F;
			
			if(isFloat(args[1])) {
				x = Float.parseFloat(args[1]);
			}
			if(isFloat(args[2])) {
				y = Float.parseFloat(args[2]);
			}
			if(isFloat(args[3])) {
				z = Float.parseFloat(args[3]);
			}
			
			if(args[0].equalsIgnoreCase("set")) {
				
				player.setVelocity(new Vector(x, y, z));
				
			} else if(args[0].equalsIgnoreCase("add")) {
				
				player.setVelocity(player.getVelocity().add(new Vector(x, y, z)));
				
			} else {
				
				player.sendMessage("usage: /velocity [add|set] x y z");
			
			}
			
			return true;
		}
		return false;
	}
	
	private boolean isFloat(String fl) {
		try {
			Float.parseFloat(fl);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
