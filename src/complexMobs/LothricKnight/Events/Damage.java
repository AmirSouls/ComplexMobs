package complexMobs.LothricKnight.Events;

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

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Main.ComplexMob;
import complexMobs.Main.ComplexMobs;
import complexMobs.Mobs.LothricKnight;

public class Damage implements Listener {
	//Knight Dmg
	//
	@EventHandler
	public void knightHurt(EntityDamageByEntityEvent event) {
		if (true || event.getCause() == DamageCause.ENTITY_ATTACK || event.getCause() == DamageCause.PROJECTILE || event.getCause() == DamageCause.ENTITY_EXPLOSION || event.getCause() == DamageCause.BLOCK_EXPLOSION) {
			if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
				if (ComplexMobs.partMob.containsKey(event.getEntity())) {
					ComplexMob mob = ComplexMobs.partMob.get(event.getEntity());
					boolean noShield = true;
					
					if (mob instanceof LothricKnight) {
						LothricKnight knight = (LothricKnight) mob;
						if (knight.shieldUp && !knight.isAttacking) {
							noShield = false;
							//Shield up
							if (knight.blockTimer == null) {
								knight.blockTimer = Instant.now();
								ResetTimers.reset(knight);
								
								//Shield hit sound
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
										"playsound minecraft:lothricknight.shieldhit master @a " +
										knight.main.getLocation().getX() + 
										" " +
										knight.main.getLocation().getY() +
										" " + knight.main.getLocation().getZ() + " 0.5 1"
										);
							}
						}
					
					}
					//Shield down or there is no shield at all
					if (noShield) {		
						//Health
						double health = mob.health;
						
						//Poise
						double poise = mob.poise;
	
						if (event.getFinalDamage() > health && event.getFinalDamage() > 2) {
							//Dead
							mob.health = 0;							
							mob.main.remove();
							for (ArmorStand part : mob.parts) {
								part.remove();
							}
						}
						else {
							if (event.getFinalDamage() > 2) {
								//Deal damage
								mob.health = health - event.getFinalDamage();
								
								//Particles
								mob.main.getWorld().spawnParticle(Particle.BLOCK_DUST, mob.main.getLocation().add(0,2,0), 100, 0.1, 0.3, 0.1, 0, Material.REDSTONE_WIRE.createBlockData());
								
								//Hurt sound
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
										"playsound minecraft:lothricknight.hurt master @a " +
										mob.main.getLocation().getX() + 
										" " +
										mob.main.getLocation().getY() +
										" " + mob.main.getLocation().getZ() + " 0.5 1"
										);
								
								//Lower poise
								if (!mob.hyperArmor) {
									poise = poise - event.getFinalDamage();
								}
							}
							
							//Stagger
							if (mob.staggerTimer == null && mob.poise <= 0 && mob.poise > mob.maxPoise * -1.5 && event.getFinalDamage() > 3.5 && !mob.hyperArmor) {
								mob.staggerTimer = Instant.now();
								mob.isAttacking = false;
								if (mob instanceof LothricKnight) {
									ResetTimers.reset((LothricKnight) mob);
								}
								mob.changeTimer = Instant.now().minusMillis(10000);
								
								//Higher poise
								mob.poise = mob.maxPoise;
							}
							//Gone over chain threshold
							else if (mob.poise < -1.5 * mob.maxPoise) {
								mob.poise = mob.maxPoise * 1.5;
							}
						}
						event.setDamage(0);
					}
				}
			}
		}
	}	
}
