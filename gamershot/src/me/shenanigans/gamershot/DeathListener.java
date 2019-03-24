package me.shenanigans.gamershot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class DeathListener implements Listener {
	Plugin plugin;
	
	public DeathListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			//System.out.println(e.getEntity().getLastDamageCause().getCause());
			if(e.getEntity().getLastDamageCause().getCause().equals(DamageCause.PROJECTILE)) {
				e.getEntity().getKiller().sendMessage("G A M E R S H O T");
			}
		}
	}
}
