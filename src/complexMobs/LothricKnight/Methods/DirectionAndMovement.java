package complexMobs.LothricKnight.Methods;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.Main.ComplexMob;
import complexMobs.Mobs.LothricKnight;

public class DirectionAndMovement {
	public static void normal(ComplexMob mob, double speed, double rotation, boolean aim) {
		try {
			
			//Initiate direction vector
			Vector directionVector;
			
			//Check if movement is aimed
			if (aim) {
				LivingEntity target = mob.target;
			
				double distance = target.getLocation().distance(mob.main.getLocation());
				Location difference = target.getLocation().subtract(mob.main.getLocation());
				directionVector = difference.toVector().divide(new Vector(distance,distance,distance));
			}
			else {
				directionVector = mob.main.getLocation().getDirection();
			}
			
			//Set mob direction, and what the new location would be
			Location newLocation = mob.main.getLocation().setDirection(directionVector);
			directionVector.setY(0);
			newLocation.add(directionVector.multiply(speed));
			
			//Fall loop
			if (canGoThrough(mob.main.getLocation().add(0,-1,0)) || mob.main.getLocation().getY()%1 != 0) {
				Vector coords = mob.main.getLocation().toVector();
				for (int y = mob.main.getLocation().getBlockY(); y > -1; y--) {
					coords.setY(y);
					if (!canGoThrough(coords.toLocation(mob.main.getLocation().getWorld()))) {
						mob.main.teleport(coords.toLocation(mob.main.getLocation().getWorld()).add(0,1,0));
						y = -1;
					}
					else if (y == 0) {
						mob.main.teleport(mob.main.getLocation().add(0,-1000,0));
					}
				}
			}
			//Normal and vertical movement
			else {
				//Can move normally
				if (canGoThrough(newLocation)) {
					mob.main.teleport(newLocation);
				}
				//1 Block climb
				else if (canGoThrough(newLocation.clone().add(0,1,0)) && canGoThrough(newLocation.clone().add(0,2,0)) && canGoThrough(newLocation.clone().add(0,3,0))) {
					mob.main.teleport(newLocation.add(0,1,0));
				}
				//2 Block climb
				else if (canGoThrough(newLocation.clone().add(0,2,0)) && canGoThrough(newLocation.clone().add(0,3,0)) && canGoThrough(newLocation.clone().add(0,4,0))) {
					mob.main.teleport(newLocation.add(0,2,0));
				}
				//Can't move anywhere forward, change action
				else {
					if (mob instanceof LothricKnight) {
						LothricKnight knight = (LothricKnight) mob;
						knight.shieldUp = true;
					}
					if (Math.random() > .5) {
						mob.passiveAction = "WalkingBackward";
					}
					else {
						mob.passiveAction = "WalkingSide";
					}
				}
			}
			
		} catch (IllegalArgumentException e) {}
	}
	
	//Simplification
	public static void normal(ComplexMob mob, double speed, double rotation) {
		normal(mob, speed, rotation, true);
	}
	
	//Timed
	public static void timed(ComplexMob mob, double speed, double rotation, Instant timer, Instant timer2, int startMilli, int endMilli, boolean aim) {
		if (Instant.now().isAfter(timer.plusMillis(startMilli)) && Instant.now().isBefore(timer2.plusMillis(endMilli))) {
			normal(mob, speed, rotation, aim);
		}
	}
	
	//Timed, Assume aim
	public static void timed(ComplexMob mob, double speed, double rotation, Instant timer, Instant timer2, int startMilli, int endMilli) {
		timed(mob, speed, rotation, timer, timer2, startMilli, endMilli, true);
	}
	
	//Can go through check
	protected static boolean canGoThrough(Location location) {
		if (location.getBlock().isEmpty() || location.getBlock().isPassable() || location.getBlock().isLiquid()) {
			return true;
		}
		else {
			return false;
		}
	}
}
