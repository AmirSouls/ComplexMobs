package complexMobs.LothricKnight.Methods;

import java.time.Instant;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Actions.Block;
import complexMobs.LothricKnight.Actions.Death;
import complexMobs.LothricKnight.Actions.LeftSlash;
import complexMobs.LothricKnight.Actions.RightSlash;
import complexMobs.LothricKnight.Actions.ShieldBash;
import complexMobs.LothricKnight.Actions.Stagger;
import complexMobs.LothricKnight.Actions.Stance;
import complexMobs.LothricKnight.Actions.StanceThrust;
import complexMobs.LothricKnight.Actions.Standing;
import complexMobs.Main.ComplexMobs;
import complexMobs.Mobs.LothricKnight;

public class AI {
	
	public void process(LothricKnight knight) {
		//Collision
		try {
			knight.restoreVectors();
			
			for (Entity entity : knight.main.getNearbyEntities(.125, .3, .125)) {
				double distance = entity.getLocation().distance(knight.main.getLocation());
				Location difference = entity.getLocation().subtract(knight.main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance,distance,distance));
				vector.multiply(.05);
				entity.setVelocity(entity.getVelocity().add(vector.setY(0)));
				
				if (ComplexMobs.isMain.containsKey(entity)) {
					entity.teleport(entity.getLocation().add(vector.multiply(5).setY(0)));
				}
			}
		} catch (IllegalArgumentException e) {}
		
		//Update yaw
		knight.yaw = knight.main.getLocation().getYaw() / 57.2957795;
		
		//Refill stamina
		if (knight.staminaUseTimer != null) {
			if (Instant.now().isAfter(knight.staminaUseTimer.plusMillis(1500))) {
				knight.staminaUseTimer = null;
			}
		}
		else {
			if (knight.stamina < 0) knight.stamina = 0;
			knight.stamina += 3;
			if (knight.stamina > 100) knight.stamina = 100;
		}	
		
		//Refill poise
		
		//Low poise modifier
		double lowPoiseModifier = 0;
		if (knight.poise < knight.maxPoise / 3) lowPoiseModifier = 0.15;
		//If less than max, increase, if max or slightly more, set to max, if more enough, decrease
		if (knight.poise < knight.maxPoise) knight.poise = (knight.poise + .2 - lowPoiseModifier);
		if (knight.poise >= knight.maxPoise && knight.poise < knight.maxPoise + 1);
		else if (knight.poise > knight.maxPoise + 1) knight.poise = knight.poise - .8;
		
		
		//Action logic
		//See if the knight blocked an attack, got staggered, or is dead.
		boolean outOfStamina = false;
		boolean blocked = false;
		boolean staggered = false;
		if (knight.stamina <= 0) outOfStamina = true;
		if (knight.blockTimer != null) blocked = true;
		if (knight.staggerTimer != null) staggered = true;
		if (knight.dead) {
			Death.animate(knight);
		}
		else if (blocked) {
			if (Instant.now().isBefore(knight.blockTimer.plusMillis(605))) {
				Block.animate(knight);
			}
			else if (Instant.now().isAfter(knight.blockTimer.plusMillis(605))) {
				OffsetTimers.offset(knight, -600);
				knight.blockTimer = null;
			}
		}
		else if (staggered) {
			if (Instant.now().isBefore(knight.staggerTimer.plusMillis(605))) {
				Stagger.animate(knight);
			}
			else if (Instant.now().isAfter(knight.staggerTimer.plusMillis(605))) {
				OffsetTimers.offset(knight, -600);
				knight.staggerTimer = null;
			}
		}
		else {
			//Check if currently not attacking
			if (!knight.isAttacking) {
				//Why isn't it?
				if (knight.target == null) {
					//Standing idle animation
					Standing.animate(knight);
					
					LivingEntity target = null;
					double targetDistance = 100000000;
					for (LivingEntity livingEntity : knight.main.getWorld().getEntitiesByClass(LivingEntity.class)) {
						if (livingEntity.getType() != EntityType.ARMOR_STAND && targetDistance > livingEntity.getLocation().distance(knight.main.getLocation()) && livingEntity.getLocation().distance(knight.main.getLocation()) < 30) {
							targetDistance = livingEntity.getLocation().distance(knight.main.getLocation());
							target = livingEntity;
						}
					}
					if (target != null) {
						knight.target = target;
					}
				}
				else {
					//Has alive target or its dead target
					if (knight.target.isDead()) {
						//Target is dead, stop targeting it
						knight.target = null;
					}
					else {
						//Check if player is spectator
						boolean targetRemoved = false;
						if (knight.target.getType() == EntityType.PLAYER) {
							Player player = (Player) knight.target;
							if (GameMode.SPECTATOR == player.getGameMode()) {
								//Target went into spectator mode, stop targeting them.
								knight.target = null;
								targetRemoved = true;
							}
							if (!player.isOnline()) {
								//Target left game, stop targetting.
								knight.target = null;
								targetRemoved = true;
							}
						}
						if (!targetRemoved && !knight.dead && !blocked && !staggered) {
							//Target it alive, knight just isn't attacking right now
							if (knight.passiveAction != null) {
								LivingEntity target = knight.target;
								double distance3D = target.getLocation().distance(knight.main.getLocation());
							
								//Stamina chance modifier squared
								double staminaModifier = knight.stamina / 100;
									staminaModifier = staminaModifier * staminaModifier;
									
								//Decide if it wants to attempt an attack
								if (Math.random() < 0.7 * staminaModifier && !outOfStamina) {
									knight.ActiveActionSelect(distance3D);
								}
								
							}
							else {
								//They don't even have a passive action! Lets make one
								knight.passiveAction = "Standing";
								knight.changeTimer = Instant.now().plusMillis((long) (Math.random() * 3000));
								
								//Reset animation timers
								ResetTimers.resetOffset(knight, -1000);
							}
							
							//Now for the actual mechanics of passive actions
							if (knight.passiveAction != null) {
								LivingEntity target = knight.target;
								double distance3D = target.getLocation().distance(knight.main.getLocation());
								
								//Action select and Execution
								knight.PassiveActionSelect(distance3D);
							}	
						}
					}
				}
			}
			else {
				//Knight is attacking! Lets direct it to its animation and mechanics:
				if (knight.activeAction != null) {

					if (knight.activeAction == "LeftSlash") {
						LeftSlash.animate(knight);
					}
					else if (knight.activeAction == "RightSlash") {
						RightSlash.animate(knight);
					}
					else if (knight.activeAction == "Stance") {
						Stance.animate(knight);
					}
					else if (knight.activeAction == "StanceThrust") {
						StanceThrust.animate(knight);
					}
					else if (knight.activeAction == "ShieldBash") {
						ShieldBash.animate(knight);
					}
				}
			}
		}
	}	
}