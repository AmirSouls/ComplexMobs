package lothricKnights.Events;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.ResetTimers;

public class Damage implements Listener {
	//Knight Dmg
	//
	@EventHandler
	public void knightHurt(EntityDamageByEntityEvent event) {
		if (event.getCause() == DamageCause.ENTITY_ATTACK || event.getCause() == DamageCause.PROJECTILE || event.getCause() == DamageCause.ENTITY_EXPLOSION || event.getCause() == DamageCause.BLOCK_EXPLOSION) {
			if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
				if (LothricKnights.partHost.containsKey(event.getEntity())) {
					if (LothricKnights.shieldUp.containsKey(LothricKnights.partHost.get(event.getEntity()))) {
						ArmorStand main = LothricKnights.partHost.get(event.getEntity());
						if (LothricKnights.shieldUp.get(main)) {
							//Shield up
							if (!LothricKnights.blocked.containsKey(main) && event.getFinalDamage() > 3.5) {
								LothricKnights.blocked.put(main, Instant.now());
								ResetTimers.resetTimers(main);
								
								//Shield hit sound
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
										"playsound minecraft:lothricknight.shieldhit master @a " +
										main.getLocation().getX() + 
										" " +
										main.getLocation().getY() +
										" " + main.getLocation().getZ() + " 0.5 1"
										);
							}
						}
						//Shield down
						else {		
							//Initialize health
							float maxHealth = 10000;
							if (!LothricKnights.mobHealth.containsKey(main)) {
								LothricKnights.mobHealth.put(main, (float) maxHealth);
							}
							
							//Initialize poise
							float poiseModifier = 0;
							float maxPoise = 9 * (1 + poiseModifier);
							if (!LothricKnights.mobPoise.containsKey(main)) {
								LothricKnights.mobPoise.put(main, (float) maxPoise);
							}
							
							//Get current health
							float health = LothricKnights.mobHealth.get(main);
							
							//Get current poise and immediatly apply dmg to see if their stance was broken
							float poise = LothricKnights.mobPoise.get(main);
							if (event.getFinalDamage() > 3.5 && !LothricKnights.hyperArmor.get(main)) {
								
								poise = (float) (poise - event.getFinalDamage());
							}
							
							if (event.getFinalDamage() > health) {
								//Dead
								LothricKnights.mobHealth.put(main, (float) 0.0);
							}
							else {
								//Damaged
								if (!LothricKnights.stunned.containsKey(main) && poise <= 0 && poise > -30 && event.getFinalDamage() > 3.5 && !LothricKnights.hyperArmor.get(main)) {
									LothricKnights.stunned.put(main, Instant.now());
									LothricKnights.isAttacking.put(main, false);
									LothricKnights.hyperArmor.put(main, false);
									ResetTimers.resetTimers(main);
									
									//Higher poise
									LothricKnights.mobPoise.put(main, (float) maxPoise);
								}
								//Gone over chain threshold
								else if (poise < -1.5 * maxPoise) {
									LothricKnights.mobPoise.put(main, maxPoise * 2);
								}
								//Not staggered
								else {
									LothricKnights.mobPoise.put(main, poise);
								}
								
								//Deal dmg
								LothricKnights.mobHealth.put(main, (float) (health - event.getFinalDamage()));
								
								//Particles
								main.getWorld().spawnParticle(Particle.BLOCK_DUST, main.getLocation().add(0,2,0), 100, 0.1, 0.3, 0.1, 0, Material.REDSTONE_WIRE.createBlockData());
								
								//Hurt sound
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
										"playsound minecraft:lothricknight.hurt master @a " +
										main.getLocation().getX() + 
										" " +
										main.getLocation().getY() +
										" " + main.getLocation().getZ() + " 0.5 1"
										);
							}
							event.setDamage(0);
						}
					}
				}
			}
		}
	}	
}
