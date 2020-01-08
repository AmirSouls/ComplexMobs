package complexMobs.object;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Part {

	private ArmorStand part;
	
	private ArmorStand main;
	
	private Vector partOffset;
	
	private Vector partPosition;
	
	private EulerAngle headPose = new EulerAngle(0,0,0);
	
	public Part(ArmorStand part, ArmorStand main, Vector partOffset) {
		this.part = part;
		this.main = main;
		this.partOffset = partOffset;
		this.partPosition = new Vector();
	}
	
	public ArmorStand getMain() {
		return this.main;
	}
	
	public ArmorStand getArmorStand() {
		return this.part;
	}
	
	public Vector getPosition() {
		return this.partPosition;
	}
	
	public void setPosition(Vector partPosition) {
		part.teleport(main.getLocation().clone().add(partPosition));
		this.partPosition = partPosition;
	}
	
	public void resetPosition() {
		this.partPosition = new Vector();
	}
	
	public Vector getOffset() {
		return this.partOffset;
	}
	
	public EulerAngle getHeadPose() {
		return this.headPose;
	}
	
	private void setHeadPose(EulerAngle headPose) {
		this.headPose = headPose;
		this.part.setHeadPose(headPose);
	}

	public void animationFrame(int frame, int tick, double x, double y, double z) {
		if (tick == frame) {
			double rd = 57.2958;
			
			EulerAngle newPose = new EulerAngle(
					x / rd
					,
					y / rd
					,
					z / rd
			);
			
			setHeadPose(newPose);
		}
	}
	
	public void animation(double x, double y, double z) {
		double rd = 57.2958;
		
		EulerAngle newPose = new EulerAngle(
				x / rd
				,
				y / rd
				,
				z / rd
		);
		
		setHeadPose(newPose);
	}
	
	public void position() {
		Vector partOffset = this.partOffset.clone();
		Vector partPosition = new Vector();
		partPosition.add(partOffset);
		
		setPosition(partPosition);
	}
}
