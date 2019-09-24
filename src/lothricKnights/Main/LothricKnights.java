package lothricKnights.Main;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import lothricKnights.Actions.AI;
import lothricKnights.Events.Damage;
import lothricKnights.Events.Spawning;
import lothricKnights.Features.PlayerTasks;

public class LothricKnights extends JavaPlugin implements Listener {
	
	//Maps
	//
	
	//Main
	public static Map<ArmorStand, ArmorStand> partHost = new HashMap<>();
	public static Map<ArmorStand, Boolean> isMain = new HashMap<>();
	public static Map<ArmorStand, Float> mobHealth = new HashMap<>();
	public static Map<ArmorStand, Integer> mobAction = new HashMap<>();
	public static Map<ArmorStand, Instant> mobStageTimer = new HashMap<>();
	public static Map<ArmorStand, Instant> mobDirection = new HashMap<>();
	public static Map<ArmorStand, LivingEntity> mobTarget = new HashMap<>();
	public static Map<ArmorStand, String> partId = new HashMap<>();
	public static Map<ArmorStand, Collection<ArmorStand>> partList = new HashMap<>();
	
	//Actions
	public static Map<ArmorStand, Boolean> isAttacking = new HashMap<>();
	public static Map<ArmorStand, String> passiveAction = new HashMap<>();
	public static Map<ArmorStand, String> activeAction = new HashMap<>();
	public static Map<ArmorStand, Instant> changeTimer = new HashMap<>();
	public static Map<ArmorStand, Float> ArmorStandDirection = new HashMap<>();
	public static Map<ArmorStand, Float> playerTrackerDirection = new HashMap<>();
	
	//Animations
	public static Map<ArmorStand, Instant> animationTimer = new HashMap<>();
	public static Map<ArmorStand, Location> movementCheck = new HashMap<>();
	
	//Estus
	public static Map<Player, Instant> estusTimer = new HashMap<>();
	
	//Interrupts
	public static Map<ArmorStand, Instant> stunned = new HashMap<>();
	public static Map<ArmorStand, Instant> blocked = new HashMap<>();
	public static Map<ArmorStand, Instant> dead = new HashMap<>();
	public static Map<ArmorStand, Float> mobPoise = new HashMap<>();
	public static Map<ArmorStand, Boolean> shieldUp = new HashMap<>();
	public static Map<ArmorStand, Boolean> hyperArmor = new HashMap<>();
	public static Map<ArmorStand, Double> stamina = new HashMap<>();
	public static Map<ArmorStand, Instant> staminaUse = new HashMap<>();
	
	//Slash
	public static Map<ArmorStand, Collection<Vector>> slashPts = new HashMap<>();

	//Start tasks
	//
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new PlayerTasks(), this);
		getServer().getPluginManager().registerEvents(new Spawning(), this);
		tasks();
		PlayerTasks.playerTasks();
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
								AI.ai(armorStand);
							}
						}
					}
				} catch (NullPointerException e) {}
			}
		}.runTaskTimer(this, 0, 0);
	}
}
