package lothricKnights.Actions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OffsetTimers;
import lothricKnights.Methods.ResetTimers;

public class AI {
	
	public static void ai(ArmorStand main) {
		//Collision
		try {
			for (Entity entity : main.getNearbyEntities(.1, .3, .1)) {
				double distance = entity.getLocation().distance(main.getLocation());
				Location difference = entity.getLocation().subtract(main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance,distance,distance));
				vector.multiply(.1);
				entity.setVelocity(entity.getVelocity().add(vector.setY(0)));
			}
		} catch (IllegalArgumentException e) {}
		
		//Refill stamina
		if (LothricKnights.stamina.containsKey(main)) {
			double stamina = LothricKnights.stamina.get(main);
			if (LothricKnights.staminaUse.containsKey(main)) {
				if (Instant.now().isAfter(LothricKnights.staminaUse.get(main).plusMillis(1500))) {
					LothricKnights.staminaUse.remove(main);
				}
			}
			else {
				if (stamina < 0) stamina = 0;
				stamina += 3;
				if (stamina > 100) stamina = 100;
				LothricKnights.stamina.put(main, stamina);
			}
		}
		
		//Refill poise
		if (LothricKnights.mobPoise.containsKey(main)) {
			float poise = LothricKnights.mobPoise.get(main);
			float maxPoise = 9;
			float lowPoiseModifier = 0;
			if (poise < maxPoise / 3) lowPoiseModifier = (float) 0.15;
			poise = (float) (poise + .2 - lowPoiseModifier);
			if (poise > maxPoise) poise = (float) (poise - .8);
			LothricKnights.mobPoise.put(main, poise);
		}
		
		//See if the knight blocked an attack or got stunned, or is dead.
		boolean outOfStamina = false;
		boolean blocked = false;
		boolean stunned = false;
		boolean dead = false;
		if (LothricKnights.stamina.get(main) <= 0) outOfStamina = true;
		if (LothricKnights.blocked.containsKey(main)) blocked = true;
		if (LothricKnights.stunned.containsKey(main)) stunned = true;
		if (LothricKnights.dead.containsKey(main)) dead = true;
		if (dead) {
		}
		else if (blocked) {
			if (Instant.now().isBefore(LothricKnights.blocked.get(main).plusMillis(600))) {
				Block.animate(main);
			}
			if (Instant.now().isAfter(LothricKnights.blocked.get(main).plusMillis(600))) {
				OffsetTimers.offset(main, -600);
				LothricKnights.blocked.remove(main);
			}
		}
		else if (stunned) {
			if (Instant.now().isBefore(LothricKnights.stunned.get(main).plusMillis(600))) {
				Stagger.animate(main);
			}
			else if (Instant.now().isAfter(LothricKnights.stunned.get(main).plusMillis(600))) {
				OffsetTimers.offset(main, -600);
				LothricKnights.stunned.remove(main);
			}
		}
		else {
			//Not attacking bool
			if (!LothricKnights.isAttacking.containsKey(main)) {
				//Default to not attacking
				LothricKnights.isAttacking.put(main, false);
			}
			//Check if currently not attacking
			else if (!LothricKnights.isAttacking.get(main)) {
				
				//Why isn't it?
				if (!LothricKnights.mobTarget.containsKey(main)) {
					LivingEntity target = null;
					float targetDistance = 100000000;
					for (LivingEntity livingEntity : main.getWorld().getEntitiesByClass(LivingEntity.class)) {
						if (livingEntity.getType() != EntityType.ARMOR_STAND && targetDistance > livingEntity.getLocation().distance(main.getLocation())) {
							target = livingEntity;
						}
					}
					if (target != null) {
						LothricKnights.mobTarget.put(main, target);
					}
				}
				else {
					//Has target or its dead,
					if (LothricKnights.mobTarget.get(main).isDead()) {
						//Target is dead, stop targeting it
						LothricKnights.mobTarget.remove(main);
					}
					else {
						//See if we can remove a possibly dead target
						boolean targetRemoved = false;
						if (LothricKnights.mobTarget.get(main).getType() == EntityType.PLAYER) {
							Player player = (Player) LothricKnights.mobTarget.get(main);
							if (GameMode.SPECTATOR == player.getGameMode()) {
								//Target is dead, stop targeting them
								LothricKnights.mobTarget.remove(main);
								targetRemoved = true;
							}
						}
						if (!targetRemoved && !dead && !blocked && !stunned) {
							//Target it alive, knight just isn't attacking right now
							//Decide if they are gonna attack
							//Make sure they arent standing still
							//							
							
							if (LothricKnights.passiveAction.containsKey(main)) {
								LivingEntity target = LothricKnights.mobTarget.get(main);
								double distance3D = target.getLocation().distance(main.getLocation());
								
								//They are within 6 metes, Close range attack
								if (distance3D < 5 && distance3D > 2 && !outOfStamina) {
									//Stamina chance modifier sqrd
									double staminaModifier = LothricKnights.stamina.get(main) / 100;
									staminaModifier = staminaModifier * staminaModifier;
									//Decide if it wants to attempt an attack
									if (Math.random() < 0.7 * staminaModifier) {
										LothricKnights.isAttacking.put(main, true);
										Collection<String> actions = new ArrayList<>();
										//actions.add("RightSlashShield"); 
										actions.add("LeftSlashShield"); 
										//actions.add("Stance"); 
										//actions.add("OverheadSlash"); 
										if (LothricKnights.passiveAction.get(main).contentEquals("WalkingSideShield") || LothricKnights.passiveAction.get(main).contentEquals("WalkingForwardShield")) {
											//actions.add("ShieldBash"); 
										}
										if (!actions.isEmpty()) Collections.shuffle((List<?>) actions);
										String action = (String) actions.toArray()[0];
										
										//Direction means where the slash is going to not from
										
										if (action.contentEquals("RightSlashShield")) {
											LothricKnights.activeAction.put(main, "RightSlashShield");
											LothricKnights.changeTimer.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
										}
										else if (action.contentEquals("LeftSlashShield")) {
											LothricKnights.activeAction.put(main, "LeftSlashShield");
											LothricKnights.changeTimer.put(main, Instant.now());
											LothricKnights.stamina.put(main, LothricKnights.stamina.get(main) - 35);
											LothricKnights.staminaUse.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
										}
										else if (action.contentEquals("ShieldBash")) {
											LothricKnights.activeAction.put(main, "ShieldBash");
											LothricKnights.changeTimer.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
										}
										else if (action.contentEquals("Stance")) {
											//Make this attack not happen 60% of the time
											if (Math.random() > 0.6) {
												LothricKnights.activeAction.put(main, "Stance");
												LothricKnights.changeTimer.put(main, Instant.now());
												
												//Reset animation timers
												ResetTimers.resetTimers(main);
											}
										}
										else if (action.contentEquals("OverheadSlash")) {
											//Make this attack not happen 60% of the time
											if (Math.random() > 0.6) {
												LothricKnights.activeAction.put(main, "OverheadSlash");
												LothricKnights.changeTimer.put(main, Instant.now());
												
												//Reset animation timers
												ResetTimers.resetTimers(main);
											}
										}
									}
								}
								//They keeping their distance at more than 6 meters, Mid range attack
								else if (distance3D < 10 && false && !outOfStamina) {
									//Only 10% chance of this happening every second
									if (Math.random() < 0.005) {
										//Stance and stab
										LothricKnights.isAttacking.put(main, true);
										LothricKnights.activeAction.put(main, "Stance");
										LothricKnights.changeTimer.put(main, Instant.now());
										
										//Reset animation timers
										ResetTimers.resetTimers(main);
									}
								}
							}
							else {
								//They don't even have a passive action! Lets make one
								LothricKnights.passiveAction.put(main, "Standing");
								LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 3000)));
								
								//Reset animation timers
								ResetTimers.resetTimers(main, -1000);
							}
							
							//Now for the actual mechanics of passive actions
							if (LothricKnights.passiveAction.containsKey(main)) {
								LivingEntity target = LothricKnights.mobTarget.get(main);
								double distance3D = target.getLocation().distance(main.getLocation());
								Collection<String> actions = new ArrayList<>();
								if (distance3D < 10) actions.add("WalkingSideShield"); 
								if (distance3D > 6 && distance3D < 10) actions.add("WalkingSide"); 
								if (distance3D > 10) actions.add("WalkingForward");
								if (distance3D > 6 && distance3D < 15) actions.add("WalkingForwardShield");
								if (!actions.isEmpty()) Collections.shuffle((List<?>) actions);
								
								//Standing still
								if (LothricKnights.passiveAction.get(main) == "Standing") {
									//shield down boolean
									LothricKnights.shieldUp.put(main, false);
									
									Standing.standing(main);
									
									//check change timer
									if (Instant.now().isAfter(LothricKnights.changeTimer.get(main).plusMillis(2000))) {
										LothricKnights.passiveAction.put(main, (String) actions.toArray()[0]);
										LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 5000)));
										
										//Reset animation timers
										ResetTimers.resetTimers(main, -1000);
									}
								}
								//Walking forward
								else if (LothricKnights.passiveAction.get(main).contentEquals("WalkingForward")) {
									//shield down boolean
									LothricKnights.shieldUp.put(main, false);
									
									//check change timer
									if (Instant.now().isAfter(LothricKnights.changeTimer.get(main).plusMillis(5000))) {
										LothricKnights.passiveAction.put(main, (String) actions.toArray()[0]);
										LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 5000)));
										
										//Reset animation timers
										ResetTimers.resetTimers(main, -1000);
									}
									WalkingForward.animate(main, true);
								}
								//Walking forward with shield
								else if (LothricKnights.passiveAction.get(main).contentEquals("WalkingForwardShield")) {
									//shield up boolean
									LothricKnights.shieldUp.put(main, true);
									
									//check change timer
									if (Instant.now().isAfter(LothricKnights.changeTimer.get(main).plusMillis(5000))) {
										LothricKnights.passiveAction.put(main, (String) actions.toArray()[0]);
										LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 5000)));
										
										//Reset animation timers
										ResetTimers.resetTimers(main, -1000);
									}
									WalkingForwardShield.animate(main, true);
								}
								//Walking sideways
								else if (LothricKnights.passiveAction.get(main).contentEquals("WalkingSide")) {
									//shield down boolean
									LothricKnights.shieldUp.put(main, false);
									
									//check change timer
									if (Instant.now().isAfter(LothricKnights.changeTimer.get(main).plusMillis(5000))) {
										LothricKnights.passiveAction.put(main, (String) actions.toArray()[0]);
										LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 5000)));
										
										//Reset animation timers
										ResetTimers.resetTimers(main, -1000);
									}
									WalkingSide.animate(main, true);
								}
								//Walking sideways with shield
								else if (LothricKnights.passiveAction.get(main).contentEquals("WalkingSideShield")) {
									//shield up boolean
									LothricKnights.shieldUp.put(main, true);
									
									//check change timer

									if (Instant.now().isAfter(LothricKnights.changeTimer.get(main).plusMillis(5000))) {
										LothricKnights.passiveAction.put(main, (String) actions.toArray()[0]);
										LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 5000)));
										
										//Reset animation timers
										ResetTimers.resetTimers(main, -1000);
									}
									WalkingSideShield.animate(main, true);
								}
							}
						}
					}
				}
			}
			else {
				//Knight is attacking! Lets direct it to its animation and mechanics:
				if (LothricKnights.activeAction.containsKey(main)) {
					if (LothricKnights.shieldUp.containsKey(main)) {
						LothricKnights.shieldUp.put(main, false);
					}
					if (LothricKnights.activeAction.get(main) == "LeftSlashShield") {
						LeftSlashShield.animate(main);
					}
				}
			}
		}
	}	
}