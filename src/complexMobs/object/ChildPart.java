package complexMobs.object;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class ChildPart extends Part {
	
	private Part parent;
	
	public ChildPart(ArmorStand part, ArmorStand main, Vector partOffset, Part parent) {
		super(part, main, partOffset);
		this.parent = parent;
	}
	
	public void setParent(Part parent) {
		this.parent = parent;
	}
	
	@Override
	public void position() {
		Vector partOffset = getOffset().clone();
		Vector partPosition = parent.getPosition().clone();
		double yaw = getMain().getLocation().getYaw() / 57.29;
		EulerAngle angle = parent.getHeadPose();
		double angleX = angle.getX();
		double angleY = angle.getY();
		double angleZ = angle.getZ();
		partOffset.rotateAroundX(angleX);
		partOffset.rotateAroundY(-angleY);
		partOffset.rotateAroundZ(-angleZ);
		partOffset.rotateAroundY(-yaw);
		partPosition.add(partOffset);
		
		this.setPosition(partPosition);
	}
}
