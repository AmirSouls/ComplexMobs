package complexMobs.Main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import complexMobs.LothricKnight.Events.Damage;
import complexMobs.LothricKnight.Events.Spawning;
import complexMobs.LothricKnight.Methods.AI;
import complexMobs.Mobs.LothricKnight;

public class ComplexMobs extends JavaPlugin implements Listener {

	public static Map<ArmorStand, ComplexMob> partMob = new HashMap<>();
	public static Map<ArmorStand, Boolean> isMain = new HashMap<>();
	
	//Start tasks
	//
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new Spawning(), this);
		tasks();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nStarting Lothric Knights\n\n");
		
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			//Set Gamemode
			if (player.getGameMode().equals(GameMode.SPECTATOR)) {
				player.setGameMode(GameMode.SURVIVAL);
			}
			
			//Remove invul if has it
			player.setInvulnerable(false);
		}
	}
	
	//Stop tasks
	//
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "\n\nDisabling Lothric Knights...\n\n");
		
		//Stop all sounds
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			
			for (Entity entity : player.getWorld().getEntities()) {
				if (entity.getType() == EntityType.ARMOR_STAND) {
					if (entity.getScoreboardTags().contains("lothricknight")) {
						entity.remove();
					}
				}
			}
		}
	}
	
	
	private void tasks() {
		new BukkitRunnable() {
			public void run() {
				try {
					for (Player player : Bukkit.getOnlinePlayers()) {
						for (ArmorStand armorStand : player.getWorld().getEntitiesByClass(ArmorStand.class)) {
							if (isMain.containsKey(armorStand)) {
								if (partMob.containsKey(armorStand)) {
									AI.process((LothricKnight) partMob.get(armorStand));
								}
							}
						}
					}
				} catch (NullPointerException e) {}
			}
		}.runTaskTimer(this, 0, 0);
	}
}
