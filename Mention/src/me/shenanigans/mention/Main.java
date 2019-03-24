package me.shenanigans.mention;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getConfig().getDefaults();
		
		this.getConfig().addDefault("players", "example");
		
		this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
		
		this.saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("togglesound")) {
				String path = "players." + player.getName();
				if(this.getConfig().contains(path)) {
					this.getConfig().set(path, !(this.getConfig().getBoolean(path)));
				} else {
					this.getConfig().set(path, 0);
				}

				this.saveConfig();
				
				player.sendMessage("Notification Sound Enabled set to " + this.getConfig().getBoolean(path));
				
			}
			
			return true;
		}
		
		return false;
	}
}
