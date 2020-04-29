package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.AnimationState;
import complexMobs.object.Part;

public class ShieldBash extends Action<LothricKnight> {
	
	public ShieldBash() {
		setReturnTick(50);
	}
	
	protected int actions() {
		playSound();
		move();
		attackFrame();
		pelvis();
		chest();
		cape();
		head();
		leftElbow();
		leftArm();
		leftHand();
		shield();
		rightElbow();
		rightArm();
		rightHand();
		sword();
		leftThigh();
		leftCalf();
		leftFoot();
		rightThigh();
		rightCalf();
		rightFoot();
		if (getTick() >= getReturnTick()) {
			return -1;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 16) main.getWorld().playSound(main.getLocation(), "lothricknight.shieldbash", 1, 1);
	}
	
	protected void move() {
		double distance = ((LothricKnight) getMob()).getBrain().getLocation().distance(((LothricKnight) getMob()).getTarget().getLocation());
		Location difference = ((LothricKnight) getMob()).getBrain().getLocation().subtract(((LothricKnight) getMob()).getTarget().getLocation());
		Vector direction = difference.toVector().divide(new Vector(distance, distance, distance));
		float yaw = (float) (Math.atan2(direction.getX(), direction.getZ())*57.29);
		if (yaw > 0) yaw -= 360;
		yaw *= -1;
		yaw += 180;
		((CraftEntity) (((LothricKnight) getMob()).getBrain())).getHandle().setHeadRotation(yaw);
		if (getTick() <= 17) getMob().move(.25, 20, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
		if (getTick() >= 15 && getTick() <= 25) mob.attackFrameShield(7, mob.getMain().getLocation().getDirection().multiply(.6).setY(.2), false);
		if (getTick() > 25) mob.getShield().setHitPoints(null);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		AnimationState state = part.animationLargeFrame(0, 13, getTick(), -10, -20, 0, -20, -20, 0);
		state = part.animationLargeFrame(state, 15, getTick(), 0, 80, 0);
		if (getTick() == 0) part.setOffset(new Vector(0, .7, 0));
		if (getTick() == 12) part.setOffset(new Vector(0, .04, 0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");	
		AnimationState state = part.animationLargeFrame(0, 13, getTick(), -10, -20, 0, -20, -20, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -20, -20, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 30, 90, 0);
		state = part.animationLargeFrame(20, 45, getTick(), 30, 90, 0, 0, 20, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		AnimationState state = part.animationLargeFrame(0, 10, getTick(), 10, 0, 0, 30, 40, -90);
		state = part.animationLargeFrame(state, 30, getTick(), 10, 70, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), -10, 0, 0, 0, -10, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -20, -5, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 30, 5, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 20, -5, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, -20, 10, 0, -70);
		state = part.animationLargeFrame(state, 13, getTick(), 40, 0, -110);
		state = part.animationLargeFrame(state, 15, getTick(), 30, 0, 0);
		state = part.animationLargeFrame(state, 20, getTick(), -30, 0, 0);
		state = part.animationLargeFrame(state, 25, getTick(), 0, 0, 5);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, -10, -20, 0, 180);
		state = part.animationLargeFrame(state, 13, getTick(), 10, 0, 180);
		state = part.animationLargeFrame(state, 15, getTick(), -110, 0, 180);
		
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 10, -20, 0, 130);
		state = part.animationLargeFrame(state, 13, getTick(), 12, 0, 150);
		state = part.animationLargeFrame(state, 15, getTick(), -60, 0, 180);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, -110, 80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -140, 100, 0);
		state = part.animationLargeFrame(state, 15, getTick(), -180, 90, 0);
		
		if (getTick() == 0)  {
			getMob().getParts().get("shield").setParent(getMob().getParts().get("left_hand"));
			getMob().getParts().get("shield").setOffset(new Vector(0,-.4,0));
		}
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 15, -10, 0, 15);
		state = part.animationLargeFrame(state, 13, getTick(), 90, -30, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 75, 0, 0);
		state = part.animationLargeFrame(20, 40, getTick(), 75, 0, 0, 20, 0, 0);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 10, -15, 0, 10);
		state = part.animationLargeFrame(state, 13, getTick(), 50, -40, 0);
		state = part.animationLargeFrame(20, 40, getTick(), 50, -40, 0, 20, -20, 0);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 10, -15, 0, 10);
		state = part.animationLargeFrame(state, 13, getTick(), 50, -40, 0);
		state = part.animationLargeFrame(20, 40, getTick(), 50, -40, 0, 20, -20, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		getMob().getParts().get("sword").setOffset(new Vector(0,-.25,0));
		AnimationState state = part.animationLargeFrame(0, 13, getTick(), 80, 20, 70, 0, -20, 60);
		state = part.animationLargeFrame(state, 20, getTick(), 80, 20, 70);
		state = part.animationLargeFrame(21, 40, getTick(), 80, 20, 70, 40, 20, 30);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, -10, -20, -20);
		state = part.animationLargeFrame(state, 10, getTick(), -40, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), -70, 10, 0);
		
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, 30, -20, -10);
		state = part.animationLargeFrame(state, 10, getTick(), 30, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), -5, 10, 0);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, 0, -20, 0);
		state = part.animationLargeFrame(state, 10, getTick(), 30, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 3, 15, -10);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, -20, -10, 10);
		state = part.animationLargeFrame(state, 10, getTick(), -5, 20, 10);
		state = part.animationLargeFrame(state, 17, getTick(), 60, -20, 10);
		state = part.animationLargeFrame(20, 40, getTick(), 60, -20, 1, -20, -10, 15);
		
	
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");		
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, 20, 20, 5);
		state = part.animationLargeFrame(state, 10, getTick(), 5, 20, 10);
		state = part.animationLargeFrame(state, 17, getTick(), 30, -20, 10);
		state = part.animationLargeFrame(20, 40, getTick(), 30, -20, 10, 10, -10, 10);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");	
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, 0, 10, 0);
		state = part.animationLargeFrame(state, 16, getTick(), 20, 80, 0);
		state = part.animationLargeFrame(20, 40, getTick(), 20, 80, 0, 0, 0, 0);
	}
}
