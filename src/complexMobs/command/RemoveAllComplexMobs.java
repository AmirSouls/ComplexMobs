package complexMobs.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;

import complexMobs.complexMob.ComplexMob;

public class RemoveAllComplexMobs  implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("removeallcomplexmobs")) {
			if (args.length < 1) {
				for (World world : Bukkit.getWorlds()) {
					for (Entity entity : world.getEntities()) {
						if (entity.hasMetadata("complex_mob")) {
							for (MetadataValue metadata : entity.getMetadata("complex_mob")) {
								((ComplexMob) metadata.value()).remove();
							}
						}
					}
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /removeallcomplexmobs");
			}
		}
		return true;
	}
}
