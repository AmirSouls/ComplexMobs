package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.Part;

public class Backstep extends Action {
	
	public Backstep() {
		setReturnTick(30);
	}
	
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
		if (getTick() >= getReturnTick()) {
			return -1;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 0) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
		if (getTick() == 12) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
	}
	
	protected void move() {
		if (1 < getTick() && 15 > getTick()) getMob().move(-.8, 0, 0);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(0, 35, 0);
		
		if (getTick() == 0) part.setOffset(new Vector(0,.4,0));
		if (getTick() == 8) part.setOffset(new Vector(0,-.02,0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		
		part.animationFrame(0, getTick(), 40, 30, 0);
		
		part.animationFrame(4, getTick(), 20, 30, 0);
		
		part.animationFrame(8, getTick(), 30, 30, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		
		part.animationFrame(0, getTick(), 10, 0, 0);
		
		part.animationFrame(4, getTick(), 50, 0, 0);
		
		part.animationFrame(8, getTick(), 20, 0, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		if (((LothricKnight) getMob()).getTarget() != null) {
			
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
		
		part.animationFrame(0, getTick(), 30, 60, 0);	
		
		part.animationFrame(4, getTick(), 45, 60, 0);	
		
		part.animationFrame(8, getTick(), 30, 60, 0);	
	}

	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		part.animationFrame(0, getTick(), -10, 40, 0);

	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		part.animationFrame(0, getTick(), -10, 40, 0);

	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		
		getMob().getParts().get("shield").setParent(getMob().getParts().get("left_hand"));
		
		part.animationFrame(0, getTick(), 5, 50, 0);

	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		
		part.animationFrame(0, getTick(), 25, -45, 0);
		
		part.animationFrame(4, getTick(), 35, -45, 0);
		
		part.animationFrame(8, getTick(), 25, -45, 0);

	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		
		part.animationFrame(0, getTick(), -5, -45, 0);
		
		part.animationFrame(4, getTick(), -15, -45, 0);
		
		part.animationFrame(8, getTick(), -5, -45, 0);

	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		
		part.animationFrame(0, getTick(), -90, 10, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animationFrame(0, getTick(), -60, 0, -30);

	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		
		part.animationFrame(0, getTick(), -70, 10, 0);
		
		part.animationFrame(4, getTick(), -60, 10, 0);
		
		part.animationFrame(8, getTick(), -70, 10, 0);

	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		
		part.animationFrame(0, getTick(), -55, -70, 0);
		
		part.animationFrame(4, getTick(), -45, -70, 0);
		
		part.animationFrame(8, getTick(), -30, -70, 0);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		
		part.animationFrame(0, getTick(), 0, 0, 0);
		
		part.animationFrame(0, getTick(), 20, 0, 0);
		
		part.animationFrame(0, getTick(), 0, 0, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		
		part.animationFrame(0, getTick(), -70, 70, 0);
		
		part.animationFrame(4, getTick(), -50, 70, 0);
		
		part.animationFrame(8, getTick(), -60, 70, 0);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		
		part.animationFrame(0, getTick(), 35, 70, 0);

		part.animationFrame(4, getTick(), 15, 70, 0);
		
		part.animationFrame(8, getTick(), 10, 70, 0);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		
		part.animationFrame(0, getTick(), 0, 0, 0);
		
		part.animationFrame(4, getTick(), 30, 90, 0);
		
		part.animationFrame(8, getTick(), 0, 90, 0);
	}
}
