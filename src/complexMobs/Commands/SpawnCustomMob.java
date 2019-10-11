package complexMobs.Commands;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import complexMobs.LothricKnight.Methods.Spawning;

public class SpawnCustomMob implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//List of mobs
		Collection<String> mobList = new ArrayList<>();
		mobList.add("LothricKnight");
		
		if(command.getName().equalsIgnoreCase("spawncustommob")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "Usage: /spawncustommob <Custom Mob ID>");
			}
			else if (args.length == 1) {
				if (args[0].contentEquals("LothricKnight")) {
					Location location = null;
					if (sender instanceof Player) {
						Player player = (Player) sender;
						location = player.getLocation();
					}
					else {
						location = new Location(Bukkit.getWorlds().get(0), 0, 99, 0);
					}
					Spawning.spawn(location);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Spawned " + ChatColor.LIGHT_PURPLE + args[0]);
				}
				else {
					String mobListString = ChatColor.LIGHT_PURPLE + "";
					for (String mobString : mobList) mobListString += mobString + "\n"; 
					sender.sendMessage(ChatColor.RED + "Invalid mob ID, try one of these:\n" + mobListString);
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /spawncustommob <Custom Mob ID>");
			}
		}
		return true;
	}
}
