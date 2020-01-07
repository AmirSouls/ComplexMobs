package complexMobs.main;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import complexMobs.command.SummonComplexMob;

public class ComplexMobs extends JavaPlugin {

	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nStarting Lothric Knights\n\n");
		this.getCommand("summoncomplexmob").setExecutor(new SummonComplexMob());
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nStopping Lothric Knights\n\n");
	}
	
}
