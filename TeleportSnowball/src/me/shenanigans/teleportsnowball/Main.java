package me.shenanigans.teleportsnowball;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	@Override 
	public void onEnable() {
		this.getConfig().getDefaults();
		
		this.getConfig().addDefault("players", "ExamplePlayer");

		getServer().getPluginManager().registerEvents(this, this);
		
		this.saveConfig();
	}
	
	@EventHandler
	public void onEggThrown(PlayerEggThrowEvent e) {
		Player player = (Player) e.getPlayer();
		
		//player.teleport(e.getEgg().getLocation());
		String path = "players." + player.getUniqueId() + ".eggsThrown";
		int eggsThrown = this.getConfig().getInt(path);
		
		this.getConfig().set(path, eggsThrown + 1);
		
		this.saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("eggsthrown")) {
				
				String path = "players." + player.getName() + ".eggsThrown";
				int eggsThrown = this.getConfig().getInt(path);
				player.sendMessage("Eggs Thrown: " + eggsThrown);
				
				this.saveConfig();
				return true;
			}
		}
		return false;
	}
}
