package lothricKnights.Features;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import lothricKnights.Main.LothricKnights;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerTasks implements Listener {

	//
	//Player Events
	//

	//Estus Drink
	@EventHandler
	public void drinkEstus(PlayerInteractEvent event) {
		if (event.getItem().getType() == Material.POTION) {
			Player player = event.getPlayer();

			if (!LothricKnights.estusTimer.containsKey(player)) {
				LothricKnights.estusTimer.put(player, Instant.now());
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "Drinking Estus..."));
			}
			event.setCancelled(true);
		}
	}

	//Player Damage
	@EventHandler
	public void playerDamage(EntityDamageEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();

			// Cancel fall dmg
			if (event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}

			// Stop estus if drinking + counter
			if (LothricKnights.estusTimer.containsKey(player)) {
				LothricKnights.estusTimer.remove(player);

				// event.setDamage(event.getDamage() * 2);;
			}

		}
	}

	//
	// Player Tasks
	//
	public static void playerTasks() {
		new BukkitRunnable() {
			public void run() {
				try {
					for (Player player : Bukkit.getOnlinePlayers()) {
						// Estus Flask Task
						if (LothricKnights.estusTimer.containsKey(player)) {
							Instant now = Instant.now();
							Vector standStill = new Vector(0, -1, 0);

							if (now.isBefore(LothricKnights.estusTimer.get(player).plusMillis(450))) {
								player.setVelocity(standStill);
							} else if (now.isBefore(LothricKnights.estusTimer.get(player).plusMillis(500))) {
								player.setVelocity(standStill);
								// Play sound
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"" + "playsound minecraft:other.estus master @a " + standStill.getX() + " "
												+ standStill.getY() + " " + standStill.getZ() + " 1 1 1");

								// Particles
								player.spawnParticle(Particle.FLAME, player.getLocation().add(0, 1.7, 0), 199, .5, .5,
										.5, .005);
								player.getPlayer().spawnParticle(Particle.LAVA, player.getLocation().add(0, 1.7, 0), 30,
										.3, .3, .3, 0);

								// Remove item
								for (ItemStack item : player.getInventory().getContents()) {
									if (item.getType() == Material.POTION) {
										if (item.getAmount() > 1) {
											item.setAmount(item.getAmount() - 1);
										} else {
											player.getInventory().clear(1);
										}
									}
								}
							} else if (now.isBefore(LothricKnights.estusTimer.get(player).plusMillis(1000))) {
								player.setVelocity(standStill);
							} else if (now.isBefore(LothricKnights.estusTimer.get(player).plusMillis(2000))) {
								player.setVelocity(standStill);

								// Heal
								double health = player.getHealth();
								if (health + 0.4 > 20) {
									player.setHealth(20);
								} else {
									player.setHealth(health + 0.4);
								}
							} else {
								LothricKnights.estusTimer.remove(player);
							}
						}
					}
				} catch (NullPointerException e) {
				}
			}
		}.runTaskTimer(LothricKnights.getPlugin(LothricKnights.class), 0, 0);
	}
}
