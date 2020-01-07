package complexMobs.object;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class ChildPart extends Part {

	private ArmorStand part;
	
	private Part parent;
	
	private ArmorStand main;
	
	private Vector partOffset;
	
	public ChildPart(ArmorStand part, ArmorStand main, Vector partOffset, Part parent) {
		super(part, main, partOffset);
		this.parent = parent;
	}
	
	@Override
	public void position() {
		Vector partOffset = this.partOffset.clone();
		Vector partPosition = parent.getPosition();
		final double yaw = main.getLocation().getYaw();
		final EulerAngle angle = parent.getArmorStand().getHeadPose();
		final double angleX = angle.getX();
		final double angleY = angle.getY();
		final double angleZ = angle.getZ();
		partOffset.rotateAroundX(angleX);
		partOffset.rotateAroundY(-angleY);
		partOffset.rotateAroundZ(-angleZ);
		partOffset.rotateAroundY(-yaw);
		partPosition.add(partOffset);
		
		part.teleport(main.getLocation().clone().add(partPosition));
		
		this.setPosition(partPosition);
	}
}
