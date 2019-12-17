package complexMobs.Methods;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class PartPositioning {
	
	public Vector position(ArmorStand part, Vector parentPosition, EulerAngle parentPose, Vector partOffset, Location hostPosition, double yaw) {
		Vector partPosition = new Vector(parentPosition.getX(), parentPosition.getY(), parentPosition.getZ());
		final EulerAngle angle = parentPose;
		final double angleX = angle.getX();
		final double angleY = angle.getY();
		final double angleZ = angle.getZ();
		partOffset.rotateAroundX(angleX);
		partOffset.rotateAroundY(-angleY);
		partOffset.rotateAroundZ(-angleZ);
		partOffset.rotateAroundY(-yaw);
		partPosition.add(partOffset);
		
		part.teleport(hostPosition.add(partPosition));
		
		return partPosition;
	}
}
