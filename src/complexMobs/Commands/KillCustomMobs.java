package complexMobs.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;

public class KillCustomMobs implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("killcustommobs")) {
			if (args.length < 1) {
				for (World world : Bukkit.getWorlds()) {
					for (ArmorStand armorStand : world.getEntitiesByClass(ArmorStand.class)) {
						if (armorStand.getScoreboardTags().contains("complexMobPart")) armorStand.remove();
					}
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /killcustommobs");
			}
		}
		return true;
	}
}
