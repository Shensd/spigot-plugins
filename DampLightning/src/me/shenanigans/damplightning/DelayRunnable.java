package me.shenanigans.damplightning;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class DelayRunnable extends BukkitRunnable {

	Location loc;
	World world;
	
	public DelayRunnable(Location loc, World world) {
		this.loc = loc;
		this.world = world;
	}
	
	@Override
	public void run() {
		System.out.println("Works");
		if(world.getBlockAt(loc).getType() == Material.FIRE) {
			world.getBlockAt(loc).setType(Material.AIR);
		}
	}
}
