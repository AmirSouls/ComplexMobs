package complexMobs.mob.lothicKnight.action;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Part;

public class Sidestepping extends PassiveAction {
	
	public Sidestepping() {
		setReturnTick(37);
	}
	
	@Override
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 14) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
		if (getTick() == 33) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
	}
	
	@Override
	protected void move() {
		getMob().move(.1, 20, -80);
	}
	
	@Override
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(20, 0, 0);
		part.setOffset(new Vector(0,.35,0));
	}
	
	@Override
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		part.animationFrame(0, getTick(), 10, 0, 0);
	}

	@Override
	protected void cape() {
		Part part = getMob().getParts().get("cape");
		part.animation(15, 0, 0);
	}

	//@Override
	//protected void head() {}
	
	@Override
	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		if (((LothricKnight) getMob()).isShieldUp()) part.animation(15, 0, -20);
		else part.animation(-10, -10, 5);
	}

	@Override
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		if (((LothricKnight) getMob()).isShieldUp()) part.animation(-70, 30, 0);
		else part.animation(0, 10, 10);
		
	}

	@Override
	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		if (((LothricKnight) getMob()).isShieldUp()) part.animation(-150, 30, 0);
		else part.animation(10, 5, 5);
	}

	@Override
	protected void shield() {
		Part part = getMob().getParts().get("shield");
		if (((LothricKnight) getMob()).isShieldUp()) part.animation(180, 90, 0);
		else part.animation(10, 10, 10);
	}
	
	@Override
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		if (((LothricKnight) getMob()).getTarget() != null) part.animation(50, 0, 40);
		else part.animation(20, 0, 5);
	}
	
	@Override
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		if (((LothricKnight) getMob()).getTarget() != null) part.animation(0, 0, 10);
		else part.animation(10, 0, 20);
	}

	@Override
	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		if (((LothricKnight) getMob()).getTarget() != null) part.animation(0, 20, 50);
		else part.animation(0, 0, 20);
	}

	@Override
	protected void sword() {
		Part part = getMob().getParts().get("sword");
		if (((LothricKnight) getMob()).getTarget() != null) part.animation(40, 70, 0);
		else part.animation(50, 0, 20);
	}
	
	@Override
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animationFrame(0, getTick(), -30, -20, 0);
		part.animationFrame(1, getTick(), -31, -17, 0);
		part.animationFrame(2, getTick(), -32, -14, 0);
		part.animationFrame(3, getTick(), -34, -10, 0);
		part.animationFrame(4, getTick(), -35, -7, 0);
		part.animationFrame(5, getTick(), -36, -4, 0);
		part.animationFrame(6, getTick(), -38, 0, 0);
		part.animationFrame(7, getTick(), -39, 3, 0);
		part.animationFrame(8, getTick(), -41, 7, 0);
		part.animationFrame(9, getTick(), -42, 10, 0);
		part.animationFrame(10, getTick(), -44, 14, 0);
		part.animationFrame(11, getTick(), -45, 18, 0);
		part.animationFrame(12, getTick(), -47, 21, 0);
		part.animationFrame(13, getTick(), -48, 25, 0);
		part.animationFrame(14, getTick(), -50, 30, 0);
		//
		//
		//
		//
		part.animationFrame(19, getTick(), -50, 30, 0);
		part.animationFrame(20, getTick(), -48, 25, 0);
		part.animationFrame(21, getTick(), -47, 21, 0);
		part.animationFrame(22, getTick(), -45, 18, 0);
		part.animationFrame(23, getTick(), -44, 14, 0);
		part.animationFrame(24, getTick(), -42, 10, 0);
		part.animationFrame(25, getTick(), -41, 7, 0);
		part.animationFrame(26, getTick(), -39, 3, 0);
		part.animationFrame(27, getTick(), -38, 0, 0);
		part.animationFrame(28, getTick(), -36, -4, 0);
		part.animationFrame(29, getTick(), -35, -7, 0);
		part.animationFrame(30, getTick(), -34, -10, 0);
		part.animationFrame(31, getTick(), -32, -14, 0);
		part.animationFrame(32, getTick(), -31, -17, 0);
		part.animationFrame(33, getTick(), -30, -20, 0);
		//
		//
		//
		//
	}

	@Override
	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationFrame(0, getTick(), 10, 0, -10);
		part.animationFrame(1, getTick(), 9, 1, -8);
		part.animationFrame(2, getTick(), 7, 2, -7);
		part.animationFrame(3, getTick(), 6, 3, -5);
		part.animationFrame(4, getTick(), 5, 4, -3);
		part.animationFrame(5, getTick(), 4, 5, -2);
		part.animationFrame(6, getTick(), 2, 6, 0);
		part.animationFrame(7, getTick(), 1, 7, 1);
		part.animationFrame(8, getTick(), 0, 8, 2);
		part.animationFrame(9, getTick(), -2, 9, 4);
		part.animationFrame(10, getTick(), -3, 10, 5);
		part.animationFrame(11, getTick(), -5, 11, 6);
		part.animationFrame(12, getTick(), -7, 13, 7);
		part.animationFrame(13, getTick(), -8, 14, 9);
		part.animationFrame(14, getTick(), -10, 15, 10);
		//
		//
		//
		//
		part.animationFrame(19, getTick(), -10, 15, 10);
		part.animationFrame(20, getTick(), -8, 14, 9);
		part.animationFrame(21, getTick(), -7, 13, 7);
		part.animationFrame(22, getTick(), -5, 12, 6);
		part.animationFrame(23, getTick(), -3, 11, 5);
		part.animationFrame(24, getTick(), -2, 10, 4);
		part.animationFrame(25, getTick(), 0, 9, 2);
		part.animationFrame(26, getTick(), 1, 8, 1);
		part.animationFrame(27, getTick(), 2, 7, 0);
		part.animationFrame(28, getTick(), 4, 6, -2);
		part.animationFrame(29, getTick(), 5, 5, -3);
		part.animationFrame(30, getTick(), 6, 4, -5);
		part.animationFrame(31, getTick(), 7, 3, -7);
		part.animationFrame(32, getTick(), 9, 1, -8);
		part.animationFrame(33, getTick(), 10, 0, -10);
		//
		//
		//
		//
	}

	@Override
	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animation(0, 0, 0);
	}

	@Override
	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationFrame(0, getTick(), -30, 30, 0);
		part.animationFrame(1, getTick(), -25, 32, 0);
		part.animationFrame(2, getTick(), -20, 34, 0);
		part.animationFrame(3, getTick(), -15, 36, 0);
		part.animationFrame(4, getTick(), -10, 39, 0);
		part.animationFrame(5, getTick(), -5, 41, 0);
		part.animationFrame(6, getTick(), 0, 43, 0);
		part.animationFrame(7, getTick(), 5, 46, 0);
		part.animationFrame(8, getTick(), 10, 48, 0);
		part.animationFrame(9, getTick(), 12, 51, 0);
		part.animationFrame(10, getTick(), 16, 52, 0);
		part.animationFrame(11, getTick(), 20, 55, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), 20, 55, 0);
		part.animationFrame(17, getTick(), 16, 55, 0);
		part.animationFrame(18, getTick(), 12, 52, 0);
		part.animationFrame(19, getTick(), 10, 51, 0);
		part.animationFrame(20, getTick(), 5, 48, 0);
		part.animationFrame(21, getTick(), 0, 46, 0);
		part.animationFrame(22, getTick(), -5, 43, 0);
		part.animationFrame(23, getTick(), -10, 41, 0);
		part.animationFrame(24, getTick(), -15, 39, 0);
		part.animationFrame(25, getTick(), -20, 36, 0);
		part.animationFrame(26, getTick(), -25, 32, 0);
		part.animationFrame(27, getTick(), -30, 30, 0);
		//
		//
		//
		//
	}

	@Override
	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animationFrame(0, getTick(), 5, 0, 10);
		part.animationFrame(1, getTick(), 8, 6, 9);
		part.animationFrame(2, getTick(), 12, 12, 8.5);
		part.animationFrame(3, getTick(), 15, 17, 8);
		part.animationFrame(4, getTick(), 20, 23, 7);
		part.animationFrame(5, getTick(), 24, 29, 6.5);
		part.animationFrame(6, getTick(), 28, 34, 6);
		part.animationFrame(7, getTick(), 32, 40, 5.5);
		part.animationFrame(8, getTick(), 35, 46, 5);
		part.animationFrame(9, getTick(), 36, 51, 4);
		part.animationFrame(10, getTick(), 39, 57, 3.5);
		part.animationFrame(11, getTick(), 42, 62, 3);
		part.animationFrame(12, getTick(), 45, 68, 2);
		part.animationFrame(13, getTick(), 47, 74, 1);
		part.animationFrame(14, getTick(), 50, 80, 0);
		//
		//
		//
		//
		part.animationFrame(19, getTick(), 50, 80, 0);
		part.animationFrame(20, getTick(), 47, 74, 1);
		part.animationFrame(21, getTick(), 45, 68, 2);
		part.animationFrame(22, getTick(), 42, 62, 3);
		part.animationFrame(23, getTick(), 39, 57, 3.5);
		part.animationFrame(24, getTick(), 36, 51, 4);
		part.animationFrame(25, getTick(), 35, 46, 5);
		part.animationFrame(26, getTick(), 32, 34, 5.5);
		part.animationFrame(27, getTick(), 28, 29, 6);
		part.animationFrame(28, getTick(), 24, 23, 6.5);
		part.animationFrame(29, getTick(), 20, 17, 7);
		part.animationFrame(30, getTick(), 15, 12, 8);
		part.animationFrame(31, getTick(), 12, 6, 8.5);
		part.animationFrame(32, getTick(), 8, 3, 9);
		part.animationFrame(33, getTick(), 5, 0, 10);
		//
		//
		//
		//
	}

	@Override
	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animation(0, 0, 0);
	}
}
