package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.Part;

public abstract class LothricKnightAction extends Action {
	protected void actions() {
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
	}
	
	protected void playSound() {
	
	}
	
	protected void move() {
		getMob().move(0, 20);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(0, 0, 0);
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
		Location targetEyes = ((LothricKnight) getMob()).getTarget().getEyeLocation();
		Location headEyes = part.getArmorStand().getEyeLocation();
		double distance = headEyes.distance(targetEyes);
		Vector difference = targetEyes.toVector().subtract(headEyes.toVector());
		Vector direction = difference.divide(new Vector(distance, distance, distance));
		part.animation(-Math.asin(direction.getY()), 0, 0);
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
