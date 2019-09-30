package complexMobs.LothricKnight.Methods;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.Main.ComplexMob;

public class DirectionAndMovement {
	public static void normal(ComplexMob mob, double speed, double rotation, boolean aim) {
		Vector directionVector;
		
		if (aim) {
			LivingEntity target = mob.target;
		
			double distance = target.getLocation().distance(mob.main.getLocation());
			Location difference = target.getLocation().subtract(mob.main.getLocation());
			directionVector = difference.toVector().divide(new Vector(distance,distance,distance));
		}
		else {
			directionVector = mob.main.getLocation().getDirection();
		}
		
		Location newLocation = mob.main.getLocation().setDirection(directionVector);
		directionVector.setY(0);
		
		if (rotation != 0) {
			double walkingDirection = (rotation + .0001) / 57.2934;
			directionVector.rotateAroundAxis(new Vector(0,1,0), walkingDirection);
		}
		
		if (!canGoThrough(mob.main.getLocation().add(0,-.01,0))) {
			if (canGoThrough(newLocation)) {
				mob.main.teleport(newLocation.add(directionVector.multiply(speed)));
			}
			else if (canGoThrough(newLocation.clone().add(0,1,0))) {
				mob.main.teleport(newLocation.add(directionVector.multiply(speed)).add(0,1,0));
			}
			else {
				mob.main.teleport(newLocation.add(directionVector.multiply(0)));
			}
		}
		else {
			if (!canGoThrough(mob.main.getLocation().add(0,-1,0))) {
				newLocation.setY(mob.main.getLocation().add(0,-1,0).getBlock().getY() + 1);
				mob.main.teleport(newLocation);
			}
			else {
				newLocation.add(0,-1,0);
				mob.main.teleport(newLocation);
			}
		}
	}
	
	public static void normal(ComplexMob mob, double speed, double rotation) {
		normal(mob, speed, rotation, true);
	}
	
	public static void timed(ComplexMob mob, double speed, double rotation, Instant timer, Instant timer2, int startMilli, int endMilli, boolean aim) {
		if (Instant.now().isAfter(timer.plusMillis(startMilli)) && Instant.now().isBefore(timer2.plusMillis(endMilli))) {
			normal(mob, speed, rotation, aim);
		}
	}
	
	public static void timed(ComplexMob mob, double speed, double rotation, Instant timer, Instant timer2, int startMilli, int endMilli) {
		timed(mob, speed, rotation, timer, timer2, startMilli, endMilli, true);
	}
	
	protected static boolean canGoThrough(Location location) {
		if (location.getBlock().isEmpty() || location.getBlock().isPassable() || location.getBlock().isLiquid()) {
			return true;
		}
		else {
			return false;
		}
	}
}
