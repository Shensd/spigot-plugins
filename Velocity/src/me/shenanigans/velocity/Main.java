package me.shenanigans.velocity;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		getCommand("velocity").setExecutor(new VelocityCommand(this));
	}
}
