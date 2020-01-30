package complexMobs.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class IsWallBetween {

	public static boolean check(Entity entity1, Entity entity2) {
		boolean isBlock = false;
		
		Location loc = entity1.getLocation();
		double distance = Math.sqrt(Math.pow(loc.getX() - entity2.getLocation().getX(), 2) + Math.pow(loc.getZ() - entity2.getLocation().getZ(), 2));
		double differenceX = loc.toVector().getX() - entity2.getLocation().toVector().getX();
		double differenceZ = loc.toVector().getZ() - entity2.getLocation().toVector().getZ();
		

		loc.add(0,1,0);
		for (int blockI = (int) distance; blockI > 0; blockI--) {
			Location blockLoc = loc.clone().subtract(new Vector(differenceX*(blockI/distance),0,differenceZ*(blockI/distance)));
			if (!blockLoc.getBlock().isPassable()) {
				isBlock = true;
			}
		}
		loc.add(0,1,0);
		for (int blockI = (int) distance; blockI > 0; blockI--) {
			Location blockLoc = loc.clone().subtract(new Vector(differenceX*(blockI/distance),0,differenceZ*(blockI/distance)));
			if (!blockLoc.getBlock().isPassable()) {
				isBlock = true;
			}
		}
		
		return isBlock;
	}
}
