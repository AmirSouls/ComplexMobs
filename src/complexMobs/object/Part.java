package complexMobs.object;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Part {

	private ArmorStand part;
	
	private ArmorStand main;
	
	private Vector partOffset;
	
	private Vector partPosition;
	
	private EulerAngle headPose;
	
	public Part(ArmorStand part, ArmorStand main, Vector partOffset) {
		this.part = part;
		this.main = main;
		this.partOffset = partOffset;
		this.partPosition = part.getLocation().toVector();
	}
	
	public ArmorStand getArmorStand() {
		return this.part;
	}
	
	public Vector getPosition() {
		return this.partPosition;
	}
	
	public void setPosition(Vector partPosition) {
		this.partPosition = partPosition;
	}
	
	public EulerAngle getHeadPose() {
		return this.headPose;
	}
	
	public void setHeadPose(EulerAngle headPose) {
		this.headPose = headPose;
	}

	public void position() {
		Vector partOffset = this.partOffset.clone();
		Vector partPosition = new Vector();
		partPosition.add(partOffset);
		
		part.teleport(main.getLocation().clone().add(partPosition));
		
		this.partPosition = partPosition;
	}
}
