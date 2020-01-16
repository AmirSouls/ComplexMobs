package complexMobs.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.Vector;

import complexMobs.complexMob.ComplexMob;
import complexMobs.mob.LothricKnight;

public class SummonComplexMob  implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("summoncomplexmob")) {
			if (args.length == 6) {
				try {
					if (args[0].contentEquals("LothricKnight") ) {
						
						Vector post = new Vector();
						post.setX(Integer.parseInt(args[1]));
						post.setY(Integer.parseInt(args[2]));
						post.setZ(Integer.parseInt(args[3]));
						World world = Bukkit.getWorld(args[4]);
						Location spawnLoc = post.toLocation(world);
						
						ComplexMob complexMob = new LothricKnight(post, Integer.parseInt(args[5]));

						complexMob.build(spawnLoc);
						complexMob.run();
					}
					else {
						sender.sendMessage(ChatColor.RED + "Invalid Argument");
					}
				} catch (Exception e) { e.printStackTrace(); }
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /summoncomplexmob <ComplexMobID> [x y z world]");
			}
		}
		return true;
	}
}
