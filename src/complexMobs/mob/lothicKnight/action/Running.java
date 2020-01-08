package complexMobs.mob.lothicKnight.action;

import complexMobs.object.Part;

public class Running extends LothricKnightAction {
	
	@Override
	protected void move() {
		getMob().move(.28, 4);
	}
	
	@Override
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(20, 0, 0);
	}
	
	@Override
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		part.animationFrame(0, getTick(), 30, -5, 0);
		part.animationFrame(1, getTick(), 30, -1.66, 0);
		part.animationFrame(2, getTick(), 30, 1.66, 0);
		part.animationFrame(3, getTick(), 30, 5, 0);
		
		
		part.animationFrame(6, getTick(), 30, 5, 0);
		part.animationFrame(7, getTick(), 30, 1.66, 0);
		part.animationFrame(8, getTick(), 30, -1.66, 0);
		part.animationFrame(9, getTick(), 30, -5, 0);
	}

	@Override
	protected void cape() {
		Part part = getMob().getParts().get("cape");
		part.animationFrame(0, getTick(), 90, 0, 0);
	}

	//@Override
	protected void head() {}
	
	@Override
	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		part.animationFrame(0, getTick(), 40, 0, -30);
		part.animationFrame(1, getTick(), 30, 0, -25);
		part.animationFrame(2, getTick(), 20, 0, -15);
		part.animationFrame(3, getTick(), 10, 0, -10);
		
		
		part.animationFrame(6, getTick(), 10, 0, -10);
		part.animationFrame(7, getTick(), 20, 0, -15);
		part.animationFrame(8, getTick(), 30, 0, -25);
		part.animationFrame(9, getTick(), 40, 0, -30);
	}

	@Override
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		part.animationFrame(0, getTick(), 0, 0, -10);
		part.animationFrame(1, getTick(), -10, 10, -6);
		part.animationFrame(2, getTick(), -20, 20, -3);
		part.animationFrame(3, getTick(), -30, 30, 0);
		
		
		part.animationFrame(6, getTick(), -30, 30, 0);
		part.animationFrame(7, getTick(), -20, 20, -3);
		part.animationFrame(8, getTick(), -10, 10, -6);
		part.animationFrame(9, getTick(), 0, 0, -10);
	}

	@Override
	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		part.animationFrame(0, getTick(), -30, 0, 0);
		part.animationFrame(1, getTick(), -20, 0, -3);
		part.animationFrame(2, getTick(), -10, 0, -6);
		part.animationFrame(3, getTick(), 0, 0, -10);
		
		
		part.animationFrame(6, getTick(), 0, 0, -10);
		part.animationFrame(7, getTick(), -10, 0, -6);
		part.animationFrame(8, getTick(), -20, 0, -3);
		part.animationFrame(9, getTick(), -30, 0, 0);
	}

	@Override
	protected void shield() {
		Part part = getMob().getParts().get("shield");
		part.animationFrame(0, getTick(), 0, 30, -5);
		part.animationFrame(1, getTick(), -5, 45, -3);
		part.animationFrame(2, getTick(), -15, 55, -1);
		part.animationFrame(3, getTick(), -25, 65, 0);
		
		
		part.animationFrame(6, getTick(), -25, 65, 0);
		part.animationFrame(7, getTick(), -15, 55, -1);
		part.animationFrame(8, getTick(), -5, 45, -3);
		part.animationFrame(9, getTick(), 0, 30, -5);
	}
	
	@Override
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		part.animationFrame(0, getTick(), 30, 0, 30);
		part.animationFrame(1, getTick(), 40, 0, 35);
		part.animationFrame(2, getTick(), 45, 0, 40);
		part.animationFrame(3, getTick(), 55, 0, 45);
		
		
		part.animationFrame(6, getTick(), 55, 0, 45);
		part.animationFrame(7, getTick(), 45, 0, 40);
		part.animationFrame(8, getTick(), 40, 0, 35);
		part.animationFrame(9, getTick(), 30, 0, 30);
	}
	
	@Override
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		part.animationFrame(0, getTick(), 0, 0, 10);
		part.animationFrame(1, getTick(), 20, 0, 25);
		part.animationFrame(2, getTick(), 40, 0, 35);
		part.animationFrame(3, getTick(), 55, 0, 45);
		
		
		part.animationFrame(6, getTick(), 55, 0, 45);
		part.animationFrame(7, getTick(), 40, 0, 35);
		part.animationFrame(8, getTick(), 20, 0, 25);
		part.animationFrame(9, getTick(), 0, 0, 10);
	}

	@Override
	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 15, 0, 15);
		part.animationFrame(2, getTick(), 30, 0, 30);
		part.animationFrame(3, getTick(), 45, 0, 45);
		
		
		part.animationFrame(6, getTick(), 45, 0, 45);
		part.animationFrame(7, getTick(), 30, 0, 30);
		part.animationFrame(8, getTick(), 15, 0, 15);
		part.animationFrame(9, getTick(), 0, 0, 0);
	}

	@Override
	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animationFrame(0, getTick(), 50, 0, 35);
		part.animationFrame(1, getTick(), 55, 0, 40);
		part.animationFrame(2, getTick(), 65, 0, 40);
		part.animationFrame(3, getTick(), 70, 0, 45);
		
		
		part.animationFrame(6, getTick(), 70, 0, 45);
		part.animationFrame(7, getTick(), 65, 0, 40);
		part.animationFrame(8, getTick(), 55, 0, 40);
		part.animationFrame(9, getTick(), 50, 0, 35);
	}
	
	@Override
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animationFrame(0, getTick(), -60, 0, 0);
		part.animationFrame(1, getTick(), -10, 0, 0);
		part.animationFrame(2, getTick(), 0, 0, 0);
		part.animationFrame(3, getTick(), 45, 0, 0);
		
		
		part.animationFrame(6, getTick(), 45, 0, 0);
		part.animationFrame(7, getTick(), 0, 0, 0);
		part.animationFrame(8, getTick(), -10, 0, 0);
		part.animationFrame(9, getTick(), -60, 0, 0);
	}

	@Override
	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationFrame(0, getTick(), -10, 0, 0);
		part.animationFrame(1, getTick(), 25, 0, 0);
		part.animationFrame(2, getTick(), 65, 0, 0);
		part.animationFrame(3, getTick(), 100, 0, 0);
		
		
		part.animationFrame(6, getTick(), 100, 0, 0);
		part.animationFrame(7, getTick(), 65, 0, 0);
		part.animationFrame(8, getTick(), 25, 0, 0);
		part.animationFrame(9, getTick(), -10, 0, 0);
	}

	@Override
	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 15, 0, 0);
		part.animationFrame(2, getTick(), 35, 0, 0);
		part.animationFrame(3, getTick(), 50, 0, 0);
		
		
		part.animationFrame(6, getTick(), 50, 0, 0);
		part.animationFrame(7, getTick(), 35, 0, 0);
		part.animationFrame(8, getTick(), 15, 0, 0);
		part.animationFrame(9, getTick(), 0, 0, 0);
	}

	@Override
	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 15, 0, 0);
		part.animationFrame(2, getTick(), 35, 0, 0);
		part.animationFrame(3, getTick(), 50, 0, 0);
		
		
		part.animationFrame(6, getTick(), 50, 0, 0);
		part.animationFrame(7, getTick(), 35, 0, 0);
		part.animationFrame(8, getTick(), 15, 0, 0);
		part.animationFrame(9, getTick(), 0, 0, 0);
	}

	@Override
	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animationFrame(0, getTick(), 100, 0, 0);
		part.animationFrame(1, getTick(), 60, 0, 0);
		part.animationFrame(2, getTick(), 30, 0, 0);
		part.animationFrame(3, getTick(), -10, 0, 0);
		
		
		part.animationFrame(6, getTick(), -10, 0, 0);
		part.animationFrame(7, getTick(), 30, 0, 0);
		part.animationFrame(8, getTick(), 60, 0, 0);
		part.animationFrame(9, getTick(), 100, 0, 0);
	}

	@Override
	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animationFrame(0, getTick(), 50, 0, 0);
		part.animationFrame(1, getTick(), 35, 0, 0);
		part.animationFrame(2, getTick(), 15, 0, 0);
		part.animationFrame(3, getTick(), 0, 0, 0);
		
		
		part.animationFrame(6, getTick(), 0, 0, 0);
		part.animationFrame(7, getTick(), 15, 0, 0);
		part.animationFrame(8, getTick(), 35, 0, 0);
		part.animationFrame(9, getTick(), 50, 0, 0);
	}
}
