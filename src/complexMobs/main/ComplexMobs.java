package complexMobs.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import complexMobs.command.RemoveAllComplexMobs;
import complexMobs.command.SummonComplexMob;
import complexMobs.complexMob.ComplexMob;
import complexMobs.event.LivingComplexMobListener;

public class ComplexMobs extends JavaPlugin {

	private static List<ComplexMob> complexMobs = new ArrayList<>();
	
	public static List<ComplexMob> getComplexMobs() {
		return complexMobs;
	}
	
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nStarting Lothric Knights\n\n");
		this.getCommand("summoncomplexmob").setExecutor(new SummonComplexMob());
		this.getCommand("removeallcomplexmobs").setExecutor(new RemoveAllComplexMobs());
		this.getServer().getPluginManager().registerEvents(new LivingComplexMobListener(), this);
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nStopping Lothric Knights\n\n");
	}
	
}
