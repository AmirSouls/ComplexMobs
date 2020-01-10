package complexMobs.mob.lothicKnight.action;

import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Part;

public class Idle extends LothricKnightPassiveAction {
	
	public Idle() {
		setReturnTick(32);
	}
	
	//@Override
	protected void playSound() {
	}
	
	@Override
	protected void move() {
		getMob().move(0, 10, 0);
	}
	
	@Override
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(0, 0, 0);
		part.setOffset(new Vector(0,.4,0));
	}
	
	@Override
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		part.animationFrame(0, getTick(), -1, 0, 0);
		part.animationFrame(1, getTick(), -.8, 0, 0);
		part.animationFrame(2, getTick(), -.6, 0, 0);
		part.animationFrame(3, getTick(), -.4, 0, 0);
		part.animationFrame(4, getTick(), -.2, 0, 0);
		part.animationFrame(5, getTick(), -.1, 0, 0);
		part.animationFrame(6, getTick(), 0, 0, 0);
		part.animationFrame(7, getTick(), .5, 0, 0);
		part.animationFrame(8, getTick(), 1, 0, 0);
		part.animationFrame(9, getTick(), 1.5, 0, 0);
		part.animationFrame(10, getTick(), 2, 0, 0);
		part.animationFrame(11, getTick(), 2.5, 0, 0);
		part.animationFrame(12, getTick(), 3, 0, 0);
		part.animationFrame(13, getTick(), 3.5, 0, 0);
		part.animationFrame(14, getTick(), 3.7, 0, 0);
		part.animationFrame(15, getTick(), 3.9, 0, 0);
		part.animationFrame(16, getTick(), 4.1, 0, 0);
		part.animationFrame(17, getTick(), 3.9, 0, 0);
		part.animationFrame(18, getTick(), 3.7, 0, 0);
		part.animationFrame(19, getTick(), 3.5, 0, 0);
		part.animationFrame(20, getTick(), 3, 0, 0);
		part.animationFrame(21, getTick(), 2.5, 0, 0);
		part.animationFrame(22, getTick(), 2, 0, 0);
		part.animationFrame(23, getTick(), 1.5, 0, 0);
		part.animationFrame(24, getTick(), 1, 0, 0);
		part.animationFrame(25, getTick(), .5, 0, 0);
		part.animationFrame(26, getTick(), 0, 0, 0);
		part.animationFrame(27, getTick(), -.1, 0, 0);
		part.animationFrame(28, getTick(), -.2, 0, 0);
		part.animationFrame(29, getTick(), -.4, 0, 0);
		part.animationFrame(30, getTick(), -.6, 0, 0);
		part.animationFrame(31, getTick(), -.8, 0, 0);
		part.animationFrame(32, getTick(), -1, 0, 0);
	}

	@Override
	protected void cape() {
		Part part = getMob().getParts().get("cape");
		part.animation(5, 0, 0);
	}

	//@Override
	//protected void head() {}
	
	@Override
	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		if (!((LothricKnight) getMob()).isShieldUp()) part.animation(15, 0, -20);
		else part.animation(-10, -10, 5);
	}

	@Override
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		if (!((LothricKnight) getMob()).isShieldUp()) part.animation(-70, 30, 0);
		else part.animation(0, 10, 10);
	}

	@Override
	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		if (!((LothricKnight) getMob()).isShieldUp()) part.animation(-150, 30, 0);
		else part.animation(10, 5, 5);
	}

	@Override
	protected void shield() {
		Part part = getMob().getParts().get("shield");
		if (!((LothricKnight) getMob()).isShieldUp()) part.animation(180, 90, 0);
		else part.animation(10, 10, 10);
	}
	
	@Override
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		part.animation(0, 10, 20);
	}
	
	@Override
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		part.animation(10, 0, 20);
	}

	@Override
	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		part.animation(0, 0, 20);
	}

	@Override
	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animation(50, 0, 20);
	}
	
	@Override
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animation(0, 0, -5);
	}

	@Override
	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animation(0, 0, -5);
	}

	@Override
	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animation(0, 0, 0);
	}

	@Override
	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animation(0, 0, 5);
	}

	@Override
	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animation(0, 0, 5);
	}

	@Override
	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animation(0, 0, 0);
	}
}
