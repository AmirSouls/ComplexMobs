package complexMobs.LothricKnight.SpecialAnimations;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.Mobs.LothricKnight;

public class HeadZeroAnimation {
	public static void animate(ArmorStand armorStand, LothricKnight knight) {
		LivingEntity target = knight.target;
		Location targetLocation = target.getLocation();
		targetLocation.add(0,1.80,0);
		Location headLocation = knight.main.getLocation();
		headLocation.add(0,3,0);
		
		double distance3D = targetLocation.distance(headLocation);
		Vector difference = targetLocation.subtract(headLocation).toVector();
		Vector direction = difference.divide(new Vector(distance3D, distance3D, distance3D));
		
		EulerAngle angle = armorStand.getHeadPose();
		{
			angle = new EulerAngle(
					-Math.asin(direction.getY())
					,
					0
					,
					0
					);
			armorStand.setHeadPose(angle);
		}
	}
}