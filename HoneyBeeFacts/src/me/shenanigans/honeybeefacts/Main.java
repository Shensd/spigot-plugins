package me.shenanigans.honeybeefacts;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private String[] facts;
	private int lines = 53;
	
	@Override
	public void onEnable() {
		StringWriter writer = new StringWriter();
		String fact = "";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("facts.txt");
		try {
			IOUtils.copy(in, writer);
			fact = writer.toString();
			
			writer.close();
			in.close();
		} catch(Exception e) { e.printStackTrace(); }
		facts = fact.split("\n");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getLabel().equalsIgnoreCase("beefacts")) {
				
				Random rand = new Random();
				String fact = facts[rand.nextInt(lines)];
				
				player.sendMessage(ChatColor.YELLOW + "Did you know?");
				player.sendMessage(fact.substring(0, fact.length() - 1)); //Substring to remove trailing invalid character
				
			}
			return true;
		}
		return false;
	}
}
