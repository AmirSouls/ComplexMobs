package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.Part;

public abstract class PassiveAction extends Action<LothricKnight> {
	
	protected int actions() {
		playSound();
		move();
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
		if (getTick() >= getReturnTick()) return 0;
		return getTick()+1;
	}
	
	protected abstract void playSound();
	
	protected void move() {
		getMob().move(0, 20, 0);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(0, 0, 0);
		part.setOffset(new Vector(0,.5,0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		part.animation(0, 0, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		part.animation(0, 0, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		if (getMob().getTarget() != null) {
			
			Location targetEyes = ((LothricKnight) getMob()).getTarget().getEyeLocation();
			Location headEyes = part.getArmorStand().getEyeLocation();
			double distance = headEyes.distance(targetEyes);
			Vector difference = targetEyes.toVector().subtract(headEyes.toVector());
			Vector direction = difference.divide(new Vector(distance, distance, distance));
			double targetYaw = Math.atan2(direction.getX(), direction.getZ()) * 57.29;
			double mobYaw = getMob().getMain().getLocation().getYaw();
			if (mobYaw > 180) mobYaw -= 360;
			mobYaw *= -1;
			if (Math.abs(mobYaw - targetYaw) > 70) part.animation(Math.min(-Math.asin(direction.getY())*57.29, 50), Math.min(Math.max(-70, mobYaw - targetYaw), 70), 0);
			else part.animation(Math.min(-Math.asin(direction.getY())*57.29, 50), mobYaw - targetYaw, 0);
		}
		else {
			part.animation(0, 0, 0);
		}
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		part.animation(0, 0, 0);
	}

	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		part.animation(0, 0, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		part.animation(0, 0, 0);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		part.animation(0, 0, 0);
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		part.animation(0, 0, 0);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		part.animation(0, 0, 0);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		part.animation(0, 0, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animation(0, 0, 0);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animation(0, 0, 0);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animation(0, 0, 0);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animation(0, 0, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animation(0, 0, 0);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animation(0, 0, 0);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animation(0, 0, 0);
	}
}
