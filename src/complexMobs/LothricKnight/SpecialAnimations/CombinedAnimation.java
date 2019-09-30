package complexMobs.LothricKnight.SpecialAnimations;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class CombinedAnimation {

	public static void animate(ArmorStand part, EulerAngle combAngle, double dx, double dy, double dz) {
		double rd = 57.2957;
		
		combAngle = new EulerAngle(
				combAngle.getX() + dx / rd
				,
				combAngle.getY() + dy / rd
				,
				combAngle.getZ() + dz / rd
				);
		part.setHeadPose(combAngle);
	}
}
