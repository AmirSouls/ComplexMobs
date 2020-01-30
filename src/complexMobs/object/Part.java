package complexMobs.object;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.complexMob.ComplexMob;
import complexMobs.util.ArmorStandFactory;

public class Part {

	private Part parent;
	private ArmorStand part;
	private ArmorStand main;
	private Vector partOffset;
	private Vector partPosition;
	private EulerAngle headPose = new EulerAngle(0,0,0);
	
	public Part(int damage, ComplexMob complexMob, Vector partOffset) {
		this.main = complexMob.getMain();
		this.part = ArmorStandFactory.createArmorStand(complexMob, Material.DIAMOND_HOE, damage);
		this.partOffset = partOffset;
		this.partPosition = new Vector();
	}
	
	public Part(int damage, ComplexMob complexMob, Vector partOffset, Part parent) {
		this.main = complexMob.getMain();
		this.part = ArmorStandFactory.createArmorStand(complexMob, Material.DIAMOND_HOE, damage);
		this.partOffset = partOffset;
		this.partPosition = new Vector();
		this.parent = parent;
	}
	
	public void setParent(Part parent) {
		this.parent = parent;
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
	
	public void setOffset(Vector partOffset) {
		this.partOffset = partOffset;
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
	
	public AnimationState animationLargeFrame(int frame, int frame2, int tick, double x, double y, double z, double x2, double y2, double z2) {
		
		if (tick >= frame && tick < frame2) {
			double frameProgress = ((double) tick - frame) / ((double) frame2 - frame);
			double xD = x2 - x;
			double yD = y2 - y;
			double zD = z2 - z;
			animation(x + xD*frameProgress, y + yD*frameProgress, z + zD*frameProgress);
		}
		
		return new AnimationState(new Vector(x2, y2, z2), frame2);
	}
	
	public AnimationState animationLargeFrame(AnimationState animationState, int frame2, int tick, double x2, double y2, double z2) {
		
		int frame = animationState.getStart();
		double x = animationState.getState().getX();
		double y = animationState.getState().getY();
		double z = animationState.getState().getZ();
		
		if (tick >= frame && tick < frame2) {
			double frameProgress = ((double) tick - frame) / ((double) frame2 - frame);
			double xD = x2 - x;
			double yD = y2 - y;
			double zD = z2 - z;
			animation(x + xD*frameProgress, y + yD*frameProgress, z + zD*frameProgress);
		}
		
		return new AnimationState(new Vector(x2, y2, z2), frame2);
	}
	
	public void position() {
		Vector partOffset = getOffset().clone();
        Vector partPosition = new Vector();

        if (parent != null) {
            partPosition = parent.getPosition().clone();
            double yaw = getMain().getLocation().getYaw() / 57.29;
            EulerAngle angle = parent.getHeadPose();
            double angleX = angle.getX();
            double angleY = angle.getY();
            double angleZ = angle.getZ();
            partOffset.rotateAroundX(angleX);
            partOffset.rotateAroundY(-angleY);
            partOffset.rotateAroundZ(-angleZ);
            partOffset.rotateAroundY(-yaw);
        }

        partPosition.add(partOffset);
        this.setPosition(partPosition);
	}
}
