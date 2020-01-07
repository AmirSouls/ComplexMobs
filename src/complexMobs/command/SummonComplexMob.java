package complexMobs.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import complexMobs.complexMob.ComplexMob;
import complexMobs.mob.LothricKnight;

public class SummonComplexMob  implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("summoncomplexmob")) {
			if (args.length < 1) {
				if (sender instanceof Player) {
					ComplexMob complexMob = new LothricKnight();
					complexMob.build(((Player) sender).getLocation());
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /summoncomplexmob");
			}
		}
		return true;
	}
}
