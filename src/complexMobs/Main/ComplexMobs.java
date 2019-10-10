package complexMobs.Main;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import complexMobs.Commands.KillCustomMobs;
import complexMobs.Commands.SpawnCustomMob;
import complexMobs.LothricKnight.DsItem;
import complexMobs.LothricKnight.Events.ArenaManager;
import complexMobs.LothricKnight.Events.Damage;
import complexMobs.LothricKnight.Events.LKSSFixer;
import complexMobs.LothricKnight.Methods.AI;
import complexMobs.Mobs.LothricKnight;

public class ComplexMobs extends JavaPlugin implements Listener {

	public static Map<ArmorStand, ComplexMob> partMob = new HashMap<>();
	public static Map<ArmorStand, Boolean> isMain = new HashMap<>();
	public static Map<Item, Instant> dsItem = new HashMap<>();
	
	//Start tasks
	//
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nStarting Lothric Knights\n\n");
		
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new LKSSFixer(), this);
		getServer().getPluginManager().registerEvents(new ArenaManager(), this);
		this.getCommand("spawncustommob").setExecutor(new SpawnCustomMob());
		this.getCommand("killcustommobs").setExecutor(new KillCustomMobs());
		tasks();
	}
	
	//Stop tasks
	//
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "\n\nDisabling Lothric Knights...\n\n");
		
		//Stop all sounds
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			
			for (Entity entity : player.getWorld().getEntities()) {
				if (entity.getType() == EntityType.ARMOR_STAND) {
					if (entity.getScoreboardTags().contains("complexMobPart")) {
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
						for (Item item : player.getWorld().getEntitiesByClass(Item.class)) {
							if (dsItem.containsKey(item)) DsItem.particles(item);
						}
					}
					for (World world : Bukkit.getWorlds()) {
						for (ArmorStand armorStand : world.getEntitiesByClass(ArmorStand.class)) {
							ArenaManager.checkEmpty(world);
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
