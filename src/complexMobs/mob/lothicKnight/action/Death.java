package complexMobs.mob.lothicKnight.action;

import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.object.Action;
import complexMobs.object.AnimationState;
import complexMobs.object.Part;

public class Death extends Action {
	
	public Death() {
		setReturnTick(140);
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
			for (Part part : getMob().getParts().values()) {
				part.getArmorStand().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, part.getArmorStand().getLocation().add(0,1,0), 20, 0, 0, 0, 0.1, null, true);
			}
			getMob().remove();
			return -1;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 0) main.getWorld().playSound(main.getLocation(), "lothricknight.death", 1, 1);
		if (getTick() == 45) main.getWorld().playSound(main.getLocation(), "lothricknight.deathknee", 1, 1);
		if (getTick() == 70) main.getWorld().playSound(main.getLocation(), "lothricknight.deathland", 1, 1);
	}
	
	protected void move() {
		//getMob().move(0, 0, 0);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		
		part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, -20, 0, 0);
		AnimationState state = part.animationLargeFrame(44, 60, getTick(), 0, 0, -20, 50, 0, 0);
		part.animationLargeFrame(state, 78, getTick(), 90, 0, 0);
		
		if (getTick() == 0) part.setOffset(new Vector(0,.23,0));
		if (getTick() == 45) part.setOffset(new Vector(0,-.2,0));
		if (getTick() == 70) part.setOffset(new Vector(0,-1.15,0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 20, 0, 0);
		state = part.animationLargeFrame(state, 7, getTick(), -20, 0, 0);
		state = part.animationLargeFrame(state, 10, getTick(), -20, -15, 0);
		state = part.animationLargeFrame(state, 22, getTick(), -10, 0, 0);
		state = part.animationLargeFrame(state, 28, getTick(),-10, 0, 0);
		state = part.animationLargeFrame(state, 38, getTick(), 0, 0, 0);
		state = part.animationLargeFrame(state, 65, getTick(), 40, 0, 0);
		state = part.animationLargeFrame(state, 72, getTick(), 100, 0, 0);
		state = part.animationLargeFrame(state, 78, getTick(), 80, 0, 0);
		state = part.animationLargeFrame(state, 84, getTick(), 75, 0, 0);
		state = part.animationLargeFrame(state, 98, getTick(), 100, 0, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 20, 0, 0, 20, 0, 60);
		part.animationLargeFrame(state, 19, getTick(), 20, 0, 0);
		state = part.animationLargeFrame(49, 66, getTick(), 20, 0, 0, 70, 0, 0);
		part.animationLargeFrame(state, 73, getTick(), 85, 0, 0);
		part.animationLargeFrame(state, 79, getTick(), 110, 0, 0);
		part.animationLargeFrame(state, 85, getTick(), 110, 0, 0);
		part.animationLargeFrame(state, 89, getTick(), 100, 0, 0);
		part.animationLargeFrame(state, 99, getTick(), 90, 0, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 20, 0, 0, 20, 0, 0);
		state = part.animationLargeFrame(state, 7, getTick(), -60, 0, 0);
		state = part.animationLargeFrame(state, 11, getTick(), 5, 0, 0);
		state = part.animationLargeFrame(state, 22, getTick(), -55, -20, 0);
		state = part.animationLargeFrame(state, 33, getTick(), -30, -10, 0);
		state = part.animationLargeFrame(state, 33, getTick(), -30, -10, 0);
		state = part.animationLargeFrame(state, 44, getTick(), -20, -5, 0);
		state = part.animationLargeFrame(state, 50, getTick(), 30, 0, 0);
		state = part.animationLargeFrame(state, 54, getTick(), 0, -5, 0);
		state = part.animationLargeFrame(state, 60, getTick(), 30, -10, 0);
		state = part.animationLargeFrame(state, 70, getTick(), 60, 0, -15);
		state = part.animationLargeFrame(state, 77, getTick(), 85, 0, -10);
		state = part.animationLargeFrame(state, 83, getTick(), 70, 0, -10);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0,  20, 0, -30);
		state = part.animationLargeFrame(state, 7, getTick(), 30, 0, -60);
		state = part.animationLargeFrame(state, 10, getTick(), 20, 0, -25);
		state = part.animationLargeFrame(state, 22, getTick(), 40, 0, -35);
		state = part.animationLargeFrame(state, 33, getTick(), 0, 0, -20);
		part.animationLargeFrame(state, 43, getTick(), 0, 0, -30);
		state = part.animationLargeFrame(54, 70, getTick(), 0, 0, -30,  0, 0, -10);
		state = part.animationLargeFrame(state, 77, getTick(), 40, 0, -20);
		state = part.animationLargeFrame(state, 83, getTick(), 85, 45, 0);
		state = part.animationLargeFrame(state, 89, getTick(), 100, 45, 0);
		state = part.animationLargeFrame(state, 95, getTick(), 110, 45, 0);
		state = part.animationLargeFrame(state, 99, getTick(), 100, 45, 0);
		state = part.animationLargeFrame(state, 105, getTick(), 85, 45, 0);
	}

	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, -10, 0, -30);
		state = part.animationLargeFrame(state, 7, getTick(), 30, 0, -60);
		state = part.animationLargeFrame(state, 10, getTick(), -10, 0, -60);
		state = part.animationLargeFrame(state, 22, getTick(), -20, 0, -60);
		state = part.animationLargeFrame(state, 33, getTick(), -20, 0, -25);
		part.animationLargeFrame(state, 43, getTick(), -20, 0, -55);
		state = part.animationLargeFrame(54, 70, getTick(), -20, 0, -55,  -20, 0, -15);
		state = part.animationLargeFrame(state, 77, getTick(), 40, 0, -20);
		state = part.animationLargeFrame(state, 83, getTick(), 90, 60, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 0, 0, -30);
		state = part.animationLargeFrame(state, 7, getTick(), 30, 0, -50);
		state = part.animationLargeFrame(state, 10, getTick(), -10, 0, -60);
		state = part.animationLargeFrame(state, 22, getTick(), -30, 0, -60);
		state = part.animationLargeFrame(state, 33, getTick(), -20, 0, -25);
		part.animationLargeFrame(state, 43, getTick(), -20, 0, -10);
		state = part.animationLargeFrame(54, 70, getTick(), 0, 0, -30,  40, 0, -10);
		state = part.animationLargeFrame(state, 77, getTick(), 40, 0, -20);
		state = part.animationLargeFrame(state, 83, getTick(), -70, 0, -10);
		state = part.animationLargeFrame(state, 89, getTick(), -100, 0, -10);
		state = part.animationLargeFrame(state, 95, getTick(), -110, 0, -10);
		state = part.animationLargeFrame(state, 99, getTick(), -100, 0, -10);
		state = part.animationLargeFrame(state, 105, getTick(), -70, 0, -10);

	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		
		getMob().getParts().get("shield").setParent(getMob().getParts().get("left_hand"));
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 20, -30, 0, 20, -30);
		state = part.animationLargeFrame(state, 7, getTick(), 0, 20, -30);
		state = part.animationLargeFrame(state, 10, getTick(), 0, -15, -50);
		state = part.animationLargeFrame(state, 22, getTick(), -30, -45, -40);
		state = part.animationLargeFrame(state, 33, getTick(), 0, -30, -25);
		state = part.animationLargeFrame(state, 43, getTick(), 0, 25, -15);
		state = part.animationLargeFrame(state, 70, getTick(), 10, 15, 0);
		state = part.animationLargeFrame(state, 77, getTick(), 0, 15, -15);
		state = part.animationLargeFrame(state, 83, getTick(), -40, 0, -75);
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		
		part.animationLargeFrame(0, 0, getTick(), 0, 0, 0, 0, 0, 0);
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 0, 30, 0);
		state = part.animationLargeFrame(state, 7, getTick(), 30, 0, 30);
		state = part.animationLargeFrame(state, 10, getTick(), 30, 0, 40);
		state = part.animationLargeFrame(state, 22, getTick(), 20, 0, 20);
		state = part.animationLargeFrame(state, 33, getTick(), 20, 0, 10);
		part.animationLargeFrame(state, 43, getTick(), 10, 15, 0);
		state = part.animationLargeFrame(54, 70, getTick(), 10, 15, 0, 40, 0, 30);
		state = part.animationLargeFrame(state, 77, getTick(), 80, 0, 30);
		state = part.animationLargeFrame(state, 83, getTick(), 110, 0, 3);
		state = part.animationLargeFrame(state, 89, getTick(), 120, 0, 30);
		state = part.animationLargeFrame(state, 95, getTick(), 110, 0, 30);
		state = part.animationLargeFrame(state, 99, getTick(), 100, 45, 0);
		state = part.animationLargeFrame(state, 105, getTick(), 80, 0, 30);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, -20, 0, 15);
		state = part.animationLargeFrame(state, 7, getTick(), -20, 0, 50);
		state = part.animationLargeFrame(state, 10, getTick(), -20, 0, 40);
		state = part.animationLargeFrame(state, 22, getTick(), -20, 0, 50);
		state = part.animationLargeFrame(state, 33, getTick(), -10, 0, 60);
		part.animationLargeFrame(state, 43, getTick(), -20, 0, 35);
		state = part.animationLargeFrame(54, 70, getTick(), -20, 0, 35, -20, 0, 25);
		state = part.animationLargeFrame(state, 77, getTick(), -20, 0, 15);
		state = part.animationLargeFrame(state, 83, getTick(), 40, 0, 20);
		state = part.animationLargeFrame(state, 89, getTick(), 40, 0, 20);
		state = part.animationLargeFrame(state, 95, getTick(), 0, 20, 100);
		state = part.animationLargeFrame(state, 99, getTick(), 0, 20, 110);
		state = part.animationLargeFrame(state, 105, getTick(), 0, 20, 70);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 15, 0, 0);
		state = part.animationLargeFrame(state, 7, getTick(), -20, 0, 60);
		state = part.animationLargeFrame(state, 10, getTick(), -20, 0, 40);
		state = part.animationLargeFrame(state, 22, getTick(), -20, 0, 50);
		state = part.animationLargeFrame(state, 33, getTick(), -10, 0, 60);
		part.animationLargeFrame(state, 43, getTick(), -20, 0, 35);
		state = part.animationLargeFrame(54, 70, getTick(), -20, 0, 35, -20, 0, 25);
		state = part.animationLargeFrame(state, 77, getTick(), 40, 0, 45);
		state = part.animationLargeFrame(state, 83, getTick(), 0, 20, 90);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 10, 10, 0);
		state = part.animationLargeFrame(state, 7, getTick(), 20, 5, 0);
		state = part.animationLargeFrame(state, 10, getTick(), 45, 45, 0);
		state = part.animationLargeFrame(state, 22, getTick(), 5, 0, 0);
		part.animationLargeFrame(state, 33, getTick(), 5, 10, 0);
		state = part.animationLargeFrame(70, 77, getTick(), 5, 10, 0, -40, 0, -90);
		state = part.animationLargeFrame(state, 91, getTick(), -40, 0, -80);
		state = part.animationLargeFrame(state, 95, getTick(), -40, 0, -90);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, -15, -15, -10);
		state = part.animationLargeFrame(state, 7, getTick(), -25, -15, -10);
		state = part.animationLargeFrame(state, 10, getTick(), -20, -15, 0);
		state = part.animationLargeFrame(state, 22, getTick(), -25, -15, -10);
		state = part.animationLargeFrame(state, 28, getTick(), -15, -15, -10);
		state = part.animationLargeFrame(state, 38, getTick(), -25, -15, -10);
		state = part.animationLargeFrame(state, 65, getTick(), -10, -15, -10);
		state = part.animationLargeFrame(state, 72, getTick(), -10, -15, -5);
		state = part.animationLargeFrame(state, 78, getTick(), 120, 15, 0);
		state = part.animationLargeFrame(state, 84, getTick(), 140, 15, 0);
		state = part.animationLargeFrame(state, 98, getTick(), 90, 5, 0);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 15, 5, 0);
		state = part.animationLargeFrame(state, 7, getTick(), 15, 5, 0);
		state = part.animationLargeFrame(state, 10, getTick(), 25, 10, 0);
		state = part.animationLargeFrame(state, 22, getTick(), 15, 5, 0);
		state = part.animationLargeFrame(state, 28, getTick(), 25, 10, 0);
		state = part.animationLargeFrame(state, 38, getTick(), 15, 5, 0);
		state = part.animationLargeFrame(state, 65, getTick(), 90, -10, 0);
		state = part.animationLargeFrame(state, 72, getTick(), 120, 0, -10);
		state = part.animationLargeFrame(state, 78, getTick(), 130, 0, -10);
		state = part.animationLargeFrame(state, 84, getTick(), 120, 0, -10);
		state = part.animationLargeFrame(state, 98, getTick(), 80, 0, -10);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		
		part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 0, -20, 0);
		part.animationLargeFrame(33, 43, getTick(), 0, -20, 0, 135, -10, 0);
		part.animationLargeFrame(70, 77, getTick(), 135, -10, 0, 220, 0, -15);
		part.animationLargeFrame(89, 95, getTick(), 220, 0, -15, 130, 0, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 20, 0, 0);
		state = part.animationLargeFrame(state, 7, getTick(), -20, 0, 0);
		state = part.animationLargeFrame(state, 10, getTick(), -20, -15, 0);
		state = part.animationLargeFrame(state, 22, getTick(), -10, 0, 0);
		state = part.animationLargeFrame(state, 28, getTick(),-10, 0, 0);
		state = part.animationLargeFrame(state, 38, getTick(), 0, 0, 0);
		state = part.animationLargeFrame(state, 65, getTick(), 40, 0, 0);
		state = part.animationLargeFrame(state, 72, getTick(), 100, 0, 0);
		state = part.animationLargeFrame(state, 78, getTick(), 80, 0, 0);
		state = part.animationLargeFrame(state, 84, getTick(), 75, 0, 0);
		state = part.animationLargeFrame(state, 98, getTick(), 100, 0, 0);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 15, 5, 0);
		state = part.animationLargeFrame(state, 7, getTick(), 15, 5, 0);
		state = part.animationLargeFrame(state, 10, getTick(), 25, 10, 0);
		state = part.animationLargeFrame(state, 22, getTick(), 15, 5, 0);
		state = part.animationLargeFrame(state, 28, getTick(), 25, 10, 0);
		state = part.animationLargeFrame(state, 38, getTick(), 15, 5, 0);
		state = part.animationLargeFrame(state, 65, getTick(), 90, -10, 0);
		state = part.animationLargeFrame(state, 72, getTick(), 120, 0, -10);
		state = part.animationLargeFrame(state, 78, getTick(), 130, 0, -10);
		state = part.animationLargeFrame(state, 84, getTick(), 120, 0, -10);
		state = part.animationLargeFrame(state, 98, getTick(), 80, 0, -10);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		
		part.animationLargeFrame(0, 3, getTick(), 0, 0, 0, 0, 30, 0);
		part.animationLargeFrame(33, 43, getTick(), 0, 30, 0, 135, 10, 0);
		part.animationLargeFrame(70, 77, getTick(), 135, 10, 0, 180, 0, 0);
		part.animationLargeFrame(77, 103, getTick(), 180, 0, 0, 145, 0, 0);
	}
}
