package complexMobs.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import complexMobs.main.ComplexMobs;

public class RemoveAllComplexMobs  implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("removeallcomplexmobs")) {
			if (args.length < 1) {
				ComplexMobs.getComplexMobs().forEach(mob -> mob.remove());
				ComplexMobs.getComplexMobs().clear();
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /removeallcomplexmobs");
			}
		}
		return true;
	}
}
