package me.shenanigans.stats;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override 
	public void onEnable() {
		getCommand("stats").setExecutor(new StatCommand(this));
		
		this.getConfig().getDefaults();
		
		//Descriptions for player stats
		this.getConfig().set("statNames.wolvesTamed", "Wolves Tamed");
		this.getConfig().set("statNames.deaths", "Deaths");
		this.getConfig().set("statNames.dirtBroken", "Dirt Blocks Broken");
		this.getConfig().set("statNames.stoneBroken", "Stone Blocks Broken");
		this.getConfig().set("statNames.diamondsMined", "Diamond Ore Mined");
		this.getConfig().set("statNames.goldenCarrotsEaten", "Golden Carrots Eaten");
		
		//Set stat groups
		this.getConfig().set("statGroups.blocks", new String[]{"dirtBroken", "stoneBroken", "diamondsMined"});
		
		this.getConfig().set("statGroups.player", new String[]{"wolvesTamed", "deaths", "goldenCarrotsEaten"});
		
		//Add event listener
		this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
		
		this.saveConfig();
	}
}
