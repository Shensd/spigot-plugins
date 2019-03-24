package me.shenanigans.snailspam;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*

o    o     __ __
 \  /    '       `
  |/   /     __    \
(`  \ '    '    \   '
  \  \|   |   @_/   |
   \   \   \       /--/
    ` ___ ___ ___ __ '
     
 */

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(cmd.getLabel().equalsIgnoreCase("snail")) {
				
				String[] snail = {
						"o    o     __ __", 
						" \\  /    '       `",
						"  |/   /     __    \\",
						"(`  \\ '    '    \\   '",
						"  \\  \\|   |   @_/   |",
						"   \\   \\   \\       /--/",
						"    ` ___ ___ ___ __ '"};
				
				player.sendMessage(snail);
			}
			
			return true;
		}
		return false;
	}
}
