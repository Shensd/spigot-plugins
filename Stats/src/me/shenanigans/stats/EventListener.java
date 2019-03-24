package me.shenanigans.stats;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener {
	Plugin plugin;
	
	public EventListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	private void incrementStat(UUID id, String stat) {
		String path = "players." + id + "." + stat;
		int attr = plugin.getConfig().getInt(path);
		plugin.getConfig().set(path, attr + 1);
		plugin.saveConfig();
	}
	
	/*
	private void incrementStat(UUID id, String stat, int val) {
		String path = "players." + id + "." + stat;
		int attr = plugin.getConfig().getInt(path);
		plugin.getConfig().set(path, attr + val);
		plugin.saveConfig();
	}
	*/
	
	@EventHandler
	public void onEntityTame(EntityTameEvent event) {
		incrementStat(event.getOwner().getUniqueId(), "wolvesTamed");
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		incrementStat(event.getEntity().getUniqueId(), "deaths");
	}
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event) {
		//Dirt (and grass) broken
		if(event.getBlock().getType() == Material.DIRT || event.getBlock().getType() == Material.GRASS) {
			incrementStat(event.getPlayer().getUniqueId(), "dirtBroken");
		}
		//Stone (and cobble) broken
		if(event.getBlock().getType() == Material.STONE || event.getBlock().getType() == Material.COBBLESTONE) {
			incrementStat(event.getPlayer().getUniqueId(), "stoneBroken");
		}
		if(event.getBlock().getType() == Material.DIAMOND_ORE) {
			incrementStat(event.getPlayer().getUniqueId(), "diamondsMined");
		}
	}
	
	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		if(event.getItem().getType() == Material.GOLDEN_CARROT) {
			incrementStat(event.getPlayer().getUniqueId(), "goldenCarrotsEaten");
		}
	}
}
