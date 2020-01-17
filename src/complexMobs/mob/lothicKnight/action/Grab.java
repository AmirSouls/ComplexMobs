package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.AnimationState;
import complexMobs.object.ChildPart;
import complexMobs.object.Part;

public class Grab extends Action {
	
	public Grab() {
		setReturnTick(70);
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
			return 0;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 10) main.getWorld().playSound(main.getLocation(), "lothricknight.grunt", 1, 1);
	}
	
	protected void move() {
		
		double distance = ((LothricKnight) getMob()).getTargeter().getLocation().distance(((LothricKnight) getMob()).getTarget().getLocation());
		Location difference = ((LothricKnight) getMob()).getTargeter().getLocation().subtract(((LothricKnight) getMob()).getTarget().getLocation());
		Vector direction = difference.toVector().divide(new Vector(distance, distance, distance));
		
		float yaw = (float) (Math.atan2(direction.getX(), direction.getZ())*57.29);
		if (yaw > 0) yaw -= 360;
		yaw *= -1;
		yaw += 180;
		((CraftEntity) (((LothricKnight) getMob()).getTargeter())).getHandle().setHeadRotation(yaw);
		
		double moveAmount = .2;
		
		if (getTick() <= 10) getMob().move(0, 30, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 0, -5, -80, 0);
		//state = part.animationLargeFrame(state, 7, getTick(), -20, 0, 0);
		
		if (getTick() == 0) part.setOffset(new Vector(0,.2,0));
		if (getTick() == 30) part.setOffset(new Vector(0,.4,0));
		
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 0, -5, -80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -15, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 60, 40, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 25, 0, 0, 25, -80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), 25, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 45, 40, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), -10, 0, 0, 0, -10, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -20, -5, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 30, 5, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, -40, 110, 10, 0);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, -30, 110, 80, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, -20, 0, -40, -100);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 180, -80, -30, 180, -80, -30);
		
		if (getTick() == 60)  {
			((ChildPart) getMob().getParts().get("shield")).setParent(getMob().getParts().get("left_hand"));
			getMob().getParts().get("shield").setOffset(new Vector(0,-.5,0));
		}
		
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 30, -10, -80, 0);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 25, -30, -90, -45);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 5, -30, -90, -45);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 45, -30, 0, -110, 0, 40);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		
		part.animationFrame(0, getTick(), 10, 0, 0);
		
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		
		part.animationFrame(0, getTick(), 10, -10, -15);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		
		part.animationFrame(0, getTick(), 0, 10, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 10, 0, 0, 10);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		
		part.animationFrame(0, getTick(), 90, 60, 90);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		
		part.animationFrame(0, getTick(), 0, 70, 0);
	}
}
