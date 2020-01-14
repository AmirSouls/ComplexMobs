package complexMobs.mob.lothicKnight.action;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Part;

public class Walking extends LothricKnightPassiveAction {
	
	public Walking() {
		setReturnTick(31);
	}
	
	@Override
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 11) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
		if (getTick() == 27) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
	}
	
	@Override
	protected void move() {
		getMob().move(.1, 20, 0);
	}
	
	@Override
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(20, 0, 0);
		part.setOffset(new Vector(0,.4,0));
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
		part.animationFrame(0, getTick(), -15, 0, 0);
		part.animationFrame(1, getTick(), -11, 0, 0);
		part.animationFrame(2, getTick(), -7, 0, 0);
		part.animationFrame(3, getTick(), -3, 0, 0);
		part.animationFrame(4, getTick(), 1, 0, 0);
		part.animationFrame(5, getTick(), 5, 0, 0);
		part.animationFrame(6, getTick(), 9, 0, 0);
		part.animationFrame(7, getTick(), 13, 0, 0);
		part.animationFrame(8, getTick(), 17, 0, 0);
		part.animationFrame(9, getTick(), 21, 0, 0);
		part.animationFrame(10, getTick(), 25, 0, 0);
		part.animationFrame(11, getTick(), 30, 0, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), 30, 0, 0);
		part.animationFrame(17, getTick(), 25, 0, 0);
		part.animationFrame(18, getTick(), 21, 0, 0);
		part.animationFrame(19, getTick(), 17, 0, 0);
		part.animationFrame(20, getTick(), 13, 0, 0);
		part.animationFrame(21, getTick(), 9, 0, 0);
		part.animationFrame(22, getTick(), 5, 0, 0);
		part.animationFrame(23, getTick(), 1, 0, 0);
		part.animationFrame(24, getTick(), -3, 0, 0);
		part.animationFrame(25, getTick(), -7, 0, 0);
		part.animationFrame(26, getTick(), -11, 0, 0);
		part.animationFrame(27, getTick(), -15, 0, 0);
		//
		//
		//
		//
	}

	@Override
	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationFrame(0, getTick(), -15, 0, 0);
		part.animationFrame(1, getTick(), -8, 0, 0);
		part.animationFrame(2, getTick(), -1, 0, 0);
		part.animationFrame(3, getTick(), 6, 0, 0);
		part.animationFrame(4, getTick(), 13, 0, 0);
		part.animationFrame(5, getTick(), 20, 0, 0);
		part.animationFrame(6, getTick(), 27, 0, 0);
		part.animationFrame(7, getTick(), 34, 0, 0);
		part.animationFrame(8, getTick(), 41, 0, 0);
		part.animationFrame(9, getTick(), 48, 0, 0);
		part.animationFrame(10, getTick(), 55, 0, 0);
		part.animationFrame(11, getTick(), 60, 0, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), 60, 0, 0);
		part.animationFrame(17, getTick(), 55, 0, 0);
		part.animationFrame(18, getTick(), 48, 0, 0);
		part.animationFrame(19, getTick(), 41, 0, 0);
		part.animationFrame(20, getTick(), 34, 0, 0);
		part.animationFrame(21, getTick(), 27, 0, 0);
		part.animationFrame(22, getTick(), 20, 0, 0);
		part.animationFrame(23, getTick(), 13, 0, 0);
		part.animationFrame(24, getTick(), 6, 0, 0);
		part.animationFrame(25, getTick(), -1, 0, 0);
		part.animationFrame(26, getTick(), -8, 0, 0);
		part.animationFrame(27, getTick(), -15, 0, 0);
		//
		//
		//
		//
	}

	@Override
	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animationFrame(0, getTick(), -15, 0, 0);
		part.animationFrame(1, getTick(), -8, 0, 0);
		part.animationFrame(2, getTick(), -1, 0, 0);
		part.animationFrame(3, getTick(), 6, 0, 0);
		part.animationFrame(4, getTick(), 13, 0, 0);
		part.animationFrame(5, getTick(), 20, 0, 0);
		part.animationFrame(6, getTick(), 27, 0, 0);
		part.animationFrame(7, getTick(), 34, 0, 0);
		part.animationFrame(8, getTick(), 41, 0, 0);
		part.animationFrame(9, getTick(), 48, 0, 0);
		part.animationFrame(10, getTick(), 55, 0, 0);
		part.animationFrame(11, getTick(), 60, 0, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), 60, 0, 0);
		part.animationFrame(17, getTick(), 55, 0, 0);
		part.animationFrame(18, getTick(), 48, 0, 0);
		part.animationFrame(19, getTick(), 41, 0, 0);
		part.animationFrame(20, getTick(), 34, 0, 0);
		part.animationFrame(21, getTick(), 27, 0, 0);
		part.animationFrame(22, getTick(), 20, 0, 0);
		part.animationFrame(23, getTick(), 13, 0, 0);
		part.animationFrame(24, getTick(), 6, 0, 0);
		part.animationFrame(25, getTick(), -1, 0, 0);
		part.animationFrame(26, getTick(), -8, 0, 0);
		part.animationFrame(27, getTick(), -15, 0, 0);
		//
		//
		//
		//
	}

	@Override
	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationFrame(0, getTick(), 30, 0, 0);
		part.animationFrame(1, getTick(), 25, 0, 0);
		part.animationFrame(2, getTick(), 21, 0, 0);
		part.animationFrame(3, getTick(), 17, 0, 0);
		part.animationFrame(4, getTick(), 13, 0, 0);
		part.animationFrame(5, getTick(), 9, 0, 0);
		part.animationFrame(6, getTick(), 5, 0, 0);
		part.animationFrame(7, getTick(), 1, 0, 0);
		part.animationFrame(8, getTick(), -3, 0, 0);
		part.animationFrame(9, getTick(), -7, 0, 0);
		part.animationFrame(10, getTick(), -11, 0, 0);
		part.animationFrame(11, getTick(), -15, 0, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), -15, 0, 0);
		part.animationFrame(17, getTick(), -11, 0, 0);
		part.animationFrame(18, getTick(), -7, 0, 0);
		part.animationFrame(19, getTick(), -3, 0, 0);
		part.animationFrame(20, getTick(), 1, 0, 0);
		part.animationFrame(21, getTick(), 5, 0, 0);
		part.animationFrame(22, getTick(), 9, 0, 0);
		part.animationFrame(23, getTick(), 13, 0, 0);
		part.animationFrame(24, getTick(), 17, 0, 0);
		part.animationFrame(25, getTick(), 21, 0, 0);
		part.animationFrame(26, getTick(), 25, 0, 0);
		part.animationFrame(27, getTick(), 30, 0, 0);
		//
		//
		//
		//
	}

	@Override
	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animationFrame(0, getTick(), 60, 0, 0);
		part.animationFrame(2, getTick(), 55, 0, 0);
		part.animationFrame(3, getTick(), 48, 0, 0);
		part.animationFrame(4, getTick(), 41, 0, 0);
		part.animationFrame(5, getTick(), 34, 0, 0);
		part.animationFrame(6, getTick(), 27, 0, 0);
		part.animationFrame(7, getTick(), 20, 0, 0);
		part.animationFrame(8, getTick(), 13, 0, 0);
		part.animationFrame(9, getTick(), 6, 0, 0);
		part.animationFrame(10, getTick(), -1, 0, 0);
		part.animationFrame(11, getTick(), -8, 0, 0);
		part.animationFrame(12, getTick(), -15, 0, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), -15, 0, 0);
		part.animationFrame(17, getTick(), -8, 0, 0);
		part.animationFrame(18, getTick(), -1, 0, 0);
		part.animationFrame(19, getTick(), 6, 0, 0);
		part.animationFrame(20, getTick(), 13, 0, 0);
		part.animationFrame(21, getTick(), 20, 0, 0);
		part.animationFrame(22, getTick(), 27, 0, 0);
		part.animationFrame(23, getTick(), 34, 0, 0);
		part.animationFrame(24, getTick(), 41, 0, 0);
		part.animationFrame(25, getTick(), 48, 0, 0);
		part.animationFrame(26, getTick(), 55, 0, 0);
		part.animationFrame(27, getTick(), 60, 0, 0);
		//
		//
		//
		//
	}

	@Override
	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animationFrame(0, getTick(), 60, 0, 0);
		part.animationFrame(2, getTick(), 55, 0, 0);
		part.animationFrame(3, getTick(), 48, 0, 0);
		part.animationFrame(4, getTick(), 41, 0, 0);
		part.animationFrame(5, getTick(), 34, 0, 0);
		part.animationFrame(6, getTick(), 27, 0, 0);
		part.animationFrame(7, getTick(), 20, 0, 0);
		part.animationFrame(8, getTick(), 13, 0, 0);
		part.animationFrame(9, getTick(), 6, 0, 0);
		part.animationFrame(10, getTick(), -1, 0, 0);
		part.animationFrame(11, getTick(), -8, 0, 0);
		part.animationFrame(12, getTick(), -15, 0, 0);
		//
		//
		//
		//
		part.animationFrame(16, getTick(), -15, 0, 0);
		part.animationFrame(17, getTick(), -8, 0, 0);
		part.animationFrame(18, getTick(), -1, 0, 0);
		part.animationFrame(19, getTick(), 6, 0, 0);
		part.animationFrame(20, getTick(), 13, 0, 0);
		part.animationFrame(21, getTick(), 20, 0, 0);
		part.animationFrame(22, getTick(), 27, 0, 0);
		part.animationFrame(23, getTick(), 34, 0, 0);
		part.animationFrame(24, getTick(), 41, 0, 0);
		part.animationFrame(25, getTick(), 48, 0, 0);
		part.animationFrame(26, getTick(), 55, 0, 0);
		part.animationFrame(27, getTick(), 60, 0, 0);
		//
		//
		//
		//
	}
}
