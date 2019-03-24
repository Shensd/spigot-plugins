package me.shenanigans.mention;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener {
	Plugin plugin;
	
	public EventListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
		String msg = e.getMessage();
		String[] parts = msg.split(" ");
		ArrayList<String> refined = new ArrayList<>();
		
		for(int i = 0; i < parts.length; i++) {
			if(parts[i].length() >= 3 && parts[i].length() <= 16) {
				refined.add(parts[i]);
			}
		}
		
		boolean found = false;
		for(Player p : e.getRecipients()) {
			for(int i = 0; i < refined.size(); i++) {
				if(refined.get(i).equalsIgnoreCase(p.getName())) {
					
					//Play sound and color message red
					String path = "players." + p.getName();
					if(!plugin.getConfig().contains(path) || plugin.getConfig().getBoolean(path)) {
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
					}
					
					String message = "<" + e.getPlayer().getName() + "> ";
					for(int j = 0; j < parts.length; j++) {
						if(parts[j].equalsIgnoreCase(p.getName())) {
							message += ChatColor.ITALIC + e.getPlayer().getName() + ChatColor.RESET + " ";
						} else {
							message += parts[j] + " ";
						}
					}
					p.sendMessage(message);
					
					
					//Remove them from recipients to prevent double message
					e.getRecipients().remove(p);
					
					found = true;
					break;
				}
			}
			if(found) { break; }
		}
	}
}
