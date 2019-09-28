package lothricKnights.Actions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import lothricKnights.Methods.PassiveAction;

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
			float maxPoise = 14;
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
								if (distance3D < 5 && distance3D > 2 && LothricKnights.stamina.get(main) > 34) {
									//Stamina chance modifier sqrd
									double staminaModifier = LothricKnights.stamina.get(main) / 100;
									staminaModifier = staminaModifier * staminaModifier;
									//Decide if it wants to attempt an attack
									if (Math.random() < 0.7 * staminaModifier) {
										LothricKnights.isAttacking.put(main, true);
										Collection<String> actions = new ArrayList<>();
										//actions.add("RightSlashShield"); 
										actions.add("LeftSlash"); 
										if (Math.random() > 0.6) actions.add("RightSlash");
										//actions.add("Stance"); 
										//actions.add("OverheadSlash"); 
										if (!actions.isEmpty()) Collections.shuffle((List<?>) actions);
										String action = (String) actions.toArray()[0];
										
										//Direction means where the slash is going to not from
										
										if (action.contentEquals("RightSlash")) {
											LothricKnights.activeAction.put(main, action);
											LothricKnights.changeTimer.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
										}
										else if (action.contentEquals("LeftSlash")) {
											LothricKnights.activeAction.put(main, action);
											LothricKnights.changeTimer.put(main, Instant.now());
											LothricKnights.stamina.put(main, LothricKnights.stamina.get(main) - 35);
											LothricKnights.staminaUse.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
										}
										else if (action.contentEquals("ShieldBash")) {
											LothricKnights.activeAction.put(main, action);
											LothricKnights.changeTimer.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
										}
										else if (action.contentEquals("Stance")) {
											LothricKnights.activeAction.put(main, "Stance");
											LothricKnights.changeTimer.put(main, Instant.now());
											LothricKnights.stamina.put(main, LothricKnights.stamina.get(main) - 100);
											LothricKnights.staminaUse.put(main, Instant.now());
											
											//Reset animation timers
											ResetTimers.resetTimers(main);
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
								else if (distance3D > 6 && distance3D < 15 && !outOfStamina) {
									//Only 10% chance of this happening every second
									if (Math.random() < 0.0075) {
										//Stance and stab
										LothricKnights.isAttacking.put(main, true);
										LothricKnights.activeAction.put(main, "Stance");
										LothricKnights.changeTimer.put(main, Instant.now());
										LothricKnights.stamina.put(main, LothricKnights.stamina.get(main) - 100);
										LothricKnights.staminaUse.put(main, Instant.now());
										
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
								
								//Action selection
								Collection<String> actions = new ArrayList<>();
								//actions.add("Standing");
								if (distance3D < 10) actions.add("WalkingSide"); 
								if (distance3D > 6 && distance3D < 12) actions.add("WalkingForward");
								if (distance3D > 6) actions.add("Running");
								if (!actions.isEmpty()) Collections.shuffle((List<?>) actions);
								
								//Action select and Execution
								PassiveAction.select(main, (String) actions.toArray()[0], distance3D);
							}	
						}
					}
				}
			}
			else {
				//Knight is attacking! Lets direct it to its animation and mechanics:
				if (LothricKnights.activeAction.containsKey(main)) {
					if (LothricKnights.activeAction.get(main) == "LeftSlash") {
						LeftSlash.animate(main);
					}
					else if (LothricKnights.activeAction.get(main) == "RightSlash") {
						RightSlash.animate(main);
					}
					else if (LothricKnights.activeAction.get(main) == "Stance") {
						Stance.animate(main);
					}
					else if (LothricKnights.activeAction.get(main) == "StanceThrust") {
						StanceThrust.animate(main);
					}
				}
			}
		}
	}	
}