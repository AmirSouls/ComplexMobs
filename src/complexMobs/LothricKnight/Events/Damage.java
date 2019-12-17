package complexMobs.LothricKnight.Events;

import java.time.Instant;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.Methods.ShieldArc;
import complexMobs.Main.ComplexMob;
import complexMobs.Main.ComplexMobs;
import complexMobs.Mobs.LothricKnight;

public class Damage implements Listener {
	//Mob receiving damage
	//
	@EventHandler
	public void knightHurt(EntityDamageByEntityEvent event) {
		if (event.getCause() == DamageCause.ENTITY_ATTACK) {
			if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
				if (ComplexMobs.partMob.containsKey(event.getEntity())) {
					ComplexMob mob = ComplexMobs.partMob.get(event.getEntity());
					//See if hurt timer is over for attacker
					if (mob.entityAttackMobCooldown.containsKey(event.getDamager())) {
						if (Instant.now().isAfter(mob.entityAttackMobCooldown.get(event.getDamager()).plusMillis(500))) mob.entityAttackMobCooldown.remove(event.getDamager());
					}
					
					//Check if mob is dead, or if the attacker currently has a cooldown on hitting the mob
					if (!mob.dead && !mob.entityAttackMobCooldown.containsKey(event.getDamager())) {
						//Set hurt cool down for attacking entity
						mob.entityAttackMobCooldown.put(event.getDamager(), Instant.now());
						
						//Default to no shield
						boolean noShield = true;
						
						if (mob instanceof LothricKnight) {
							LothricKnight knight = (LothricKnight) mob;
							if (knight.shieldUp && !knight.isAttacking && ShieldArc.didBlock(knight.main.getLocation().getYaw(), event.getDamager().getLocation().getYaw(), 200)) {
								noShield = false;
								//Shield up
								if (knight.blockTimer == null) {
									knight.blockTimer = Instant.now();
									ResetTimers.reset(knight);
									
									//Shield hit sound
									knight.playSound("lothricknight.shieldhit", mob.main.getLocation(), 2, 1, 1);
									
									//Push back attacker
									event.getDamager().setVelocity(event.getDamager().getLocation().getDirection().multiply(-.3).setY(.1));
								}
							}
						
						}
						//Shield down or there is no shield at all
						if (noShield) {		
							
							if (event.getFinalDamage() > mob.health) {
								//Dead
								mob.health = 0;		
								mob.dead = true;
								ResetTimers.reset((LothricKnight) mob);
								
								//Killer display
								mob.killer = event.getDamager().getName();
								ArmorStand display = (ArmorStand) mob.main.getWorld().spawnEntity(mob.main.getLocation(), EntityType.ARMOR_STAND);
								display.setVisible(false);
								display.setGravity(false);
								display.setCustomName(mob.killer);
								display.setCustomNameVisible(true);
								display.addScoreboardTag("mobkiller");
								
								//Drop sword
								ItemStack straightSword = new ItemStack(Material.DIAMOND_HOE, 1);
								ItemMeta straightSwordMeta = straightSword.getItemMeta();
								straightSwordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
								straightSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
								straightSwordMeta.setDisplayName(ChatColor.RESET + "Lothric Knight Straight Sword");
								straightSwordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("W", 8, Operation.ADD_NUMBER));
								straightSwordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("K", 10000, Operation.ADD_NUMBER));
								straightSword.setItemMeta(straightSwordMeta);
								straightSword.setDurability((short) 4);
								Item item = mob.main.getWorld().dropItem(mob.main.getLocation(), straightSword);
								item.setPickupDelay(100);
								ComplexMobs.dsItem.put(item, Instant.now());
								
								//Particles
								mob.main.getWorld().spawnParticle(Particle.BLOCK_DUST, mob.main.getLocation().add(0,2,0), 400, 0.1, 0.3, 0.3, 0, Material.REDSTONE_WIRE.createBlockData());
								
								//Hurt sound
								mob.playSound("lothricknight.hurt", mob.main.getLocation(), 2, 1, 1);
							}
							else {
								//Deal damage
								mob.health = mob.health - event.getFinalDamage();
								
								//Particles
								mob.main.getWorld().spawnParticle(Particle.BLOCK_DUST, mob.main.getLocation().add(0,2,0), 100, 0.1, 0.3, 0.1, 0, Material.REDSTONE_WIRE.createBlockData());
								
								//Hurt sound
								mob.playSound("lothricknight.hurt", mob.main.getLocation(), 2, 1, 1);
								
								//Lower poise
								if (!mob.hyperArmor) {
									mob.poise = mob.poise - event.getFinalDamage();
								}
								
								//Stagger
								if (mob.staggerTimer == null && mob.poise <= 0 && mob.poise > mob.maxPoise * -1.5 && !mob.hyperArmor) {
									mob.staggerTimer = Instant.now();
									mob.isAttacking = false;
									if (mob instanceof LothricKnight) {
										ResetTimers.reset((LothricKnight) mob);
									}
									mob.changeTimer = Instant.now().minusMillis(10000);
									
									//Higher poise
									mob.poise = mob.maxPoise;
								}
								//Gone over chain hit threshold
								else if (mob.poise < -1.5 * mob.maxPoise) {
									mob.poise = mob.maxPoise * 1.5;
								}
								
								//Make the knight target the entity that attacked it
								mob.target = (LivingEntity) event.getDamager();
							}
							event.setDamage(0);
						}
					}
				}
			}
		}
	}	
}
