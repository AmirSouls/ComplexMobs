package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.Part;

public class Stance extends Action<LothricKnight> {
	
	public Stance() {
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
		
		if (getTick() >= getReturnTick() - 10) {
			LothricKnight mob = getMob();
			
			if (mob.getTarget().getLocation().distance(mob.getMain().getLocation()) < 5) {
				mob.setStamina(mob.getStamina() - 25);
				mob.setStaminaUseTick(mob.getStaminaUseTickMax());
				mob.setAction("right_slash");
				getMob().getParts().get("shield").setParent(getMob().getParts().get("left_hand"));
				getMob().getParts().get("shield").setOffset(new Vector(0,-.5,0));
				return 0;
			}
			else {
				mob.setStamina(mob.getStamina() - 60);
				mob.setStaminaUseTick(mob.getStaminaUseTickMax());
				mob.setAction("stance_thrust");
				return 0;
			}
		}
		
		if (getTick() >= getReturnTick()) {
			return -1;
		}
		
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 5) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
	}
	
	protected void move() {
		double distance = getMob().getBrain().getLocation().distance(getMob().getTarget().getLocation());
		Location difference = getMob().getBrain().getLocation().subtract(getMob().getTarget().getLocation());
		Vector direction = difference.toVector().divide(new Vector(distance, distance, distance));
		float yaw = (float) (Math.atan2(direction.getX(), direction.getZ())*57.29);
		if (yaw > 0) yaw -= 360;
		yaw *= -1;
		yaw += 180;
		((CraftEntity) getMob().getBrain()).getHandle().setHeadRotation(yaw);
		getMob().move(0, 20, 0);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -1, 10, 2);
		part.animationFrame(2, getTick(), -2, 20, 4);
		part.animationFrame(3, getTick(), -3, 30, 6);
		part.animationFrame(4, getTick(), -4, 40, 8);
		part.animationFrame(5, getTick(), -5, 55, 10);
		part.setOffset(new Vector(0,.2,0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 10, 10, 5);
		part.animationFrame(2, getTick(), 20, 20, 10);
		part.animationFrame(3, getTick(), 30, 30, 20);
		part.animationFrame(4, getTick(), 40, 40, 30);
		part.animationFrame(5, getTick(), 50, 50, 40);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -10, 15, -10);
		part.animationFrame(2, getTick(), -20, 30, -15);
		part.animationFrame(3, getTick(), -25, 45, -20);
		part.animationFrame(4, getTick(), -30, 60, -25);
		part.animationFrame(5, getTick(), -35, 70, -30);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 5, 0, 0);
		part.animationFrame(2, getTick(), 10, 0, 0);
		part.animationFrame(3, getTick(), 15, 0, 0);
		part.animationFrame(4, getTick(), 20, 0, 0);
		part.animationFrame(5, getTick(), 30, 0, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -10, 15, 0);
		part.animationFrame(2, getTick(), -30, 30, 0);
		part.animationFrame(3, getTick(), -50, 45, 0);
		part.animationFrame(4, getTick(), -70, 60, -0);
		part.animationFrame(5, getTick(), -90, 70, 0);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -20, 40, 0);
		part.animationFrame(2, getTick(), -40, 70, 0);
		part.animationFrame(3, getTick(), -70, 100, 0);
		part.animationFrame(4, getTick(), -100, 130, 0);
		part.animationFrame(5, getTick(), -130, 160, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 25, -10, 0);
		part.animationFrame(2, getTick(), 50, -20, 0);
		part.animationFrame(3, getTick(), 75, -25, 0);
		part.animationFrame(4, getTick(), 100, -35, 0);
		part.animationFrame(5, getTick(), 120, -40, 0);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 20, -10, -10);
		part.animationFrame(2, getTick(), 60, -15, -15);
		part.animationFrame(3, getTick(), 100, -20, -20);
		part.animationFrame(4, getTick(), 140, -25, -25);
		part.animationFrame(5, getTick(), 180, -30, -30);
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -20, 40, 0);
		part.animationFrame(2, getTick(), -60, 70, 0);
		part.animationFrame(3, getTick(), -100, 100, 0);
		part.animationFrame(4, getTick(), -140, 130, 0);
		part.animationFrame(5, getTick(), -145, 160, 0);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -5, 0, 10);
		part.animationFrame(2, getTick(), -10, 0, 20);
		part.animationFrame(3, getTick(), -20, 0, 25);
		part.animationFrame(4, getTick(), -30, 0, 35);
		part.animationFrame(5, getTick(), -40, 0, 45);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 0, 10, 0);
		part.animationFrame(2, getTick(), 0, 20, 0);
		part.animationFrame(3, getTick(), 0, 25, 0);
		part.animationFrame(4, getTick(), 0, 35, 0);
		part.animationFrame(5, getTick(), 0, 45, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(),- 1, -3, 15);
		part.animationFrame(2, getTick(), -3, -6, 30);
		part.animationFrame(3, getTick(), -4, -9, 40);
		part.animationFrame(4, getTick(), -5, -12, 50);
		part.animationFrame(5, getTick(), -7, -15, 60);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -25, 6, 0);
		part.animationFrame(2, getTick(), -50, 12, 0);
		part.animationFrame(3, getTick(), -70, 20, 0);
		//--
		part.animationFrame(4, getTick(), -55, 5, 0);
		part.animationFrame(5, getTick(), -45, -10, 0);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 3, 10, -1);
		part.animationFrame(2, getTick(), 7, 15, -3);
		part.animationFrame(3, getTick(), 10, 20, -5);
		//--
		part.animationFrame(4, getTick(), 10, 5, -10);
		part.animationFrame(5, getTick(), 10, -10, -15);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animation(0, 10, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -5, 20, 0);
		part.animationFrame(2, getTick(), -10, 40, 0);
		part.animationFrame(3, getTick(), -15, 60, 0);
		part.animationFrame(4, getTick(), -20, 80, 0);
		part.animationFrame(5, getTick(), -25, 110, 0);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 10, 10, 10);
		part.animationFrame(2, getTick(), 30, 20, 20);
		part.animationFrame(3, getTick(), 50, 30, 30);
		part.animationFrame(4, getTick(), 70, 45, 45);
		part.animationFrame(5, getTick(), 90, 60, 90);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animation(0, 70, 0);
	}
}
