package me.shenanigans.damplightning;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningEvent implements Listener {

	JavaPlugin plugin;
	
	public LightningEvent(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onLightningStrike(LightningStrikeEvent e) {
		Location loc = e.getLightning().getLocation();

		DelayRunnable dr = new DelayRunnable(loc, e.getWorld());
		
		dr.runTaskLater(this.plugin, 25);
	}
}


