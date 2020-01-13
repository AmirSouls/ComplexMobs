package complexMobs.mob.lothicKnight.action;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.Part;

public class RightSlash extends Action {
	
	public RightSlash() {
		setReturnTick(25);
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
		if (getTick() >= getReturnTick() - 7) {
			LothricKnight mob = (LothricKnight) getMob();
			if (mob.getTarget().getLocation().distance(mob.getMain().getLocation()) < 4 && mob.getStamina() > 25) {
				mob.setStamina(mob.getStamina() - 25);
				mob.setStaminaUseTick(mob.getStaminaUseTickMax());
				mob.setAction("left_slash");
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
		if (getTick() == 13) main.getWorld().playSound(main.getLocation(), "lothricknight.slash", 1, 1);
		if (getTick() == 10) main.getWorld().playSound(main.getLocation(), "lothricknight.grunt", 1, 1);
		if (getTick() == 15) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
	}
	
	protected void move() {
		double distance = getMob().getMain().getLocation().distance(((LothricKnight) getMob()).getTarget().getLocation());
		double moveAmount = Math.min(distance / 30, .15);
		
		if (13 > getTick()) getMob().move(moveAmount, 40, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
		if (getTick() >= 10 && getTick() <= 15) mob.attackFrameSword(9, mob.getMain().getLocation().getDirection().multiply(.4).setY(.2), false);
		if (getTick() > 15) mob.getSwordWeapon().setHitPoints(null);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		part.animation(0, 0, 0);
		part.setOffset(new Vector(0,0.29,0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		part.animationFrame(0, getTick(), 14, -80, 0);
		part.animationFrame(1, getTick(), 15, -75, 0);
		part.animationFrame(2, getTick(), 16, -70, 0);
		part.animationFrame(3, getTick(), 17, -65, 0);
		part.animationFrame(4, getTick(), 18, -55, 0);
		part.animationFrame(5, getTick(), 20, -45, 0);
		//--
		part.animationFrame(6, getTick(), 27, -40, 0);
		part.animationFrame(7, getTick(), 35, -35, 0);
		part.animationFrame(8, getTick(), 47, -30, 0);
		part.animationFrame(9, getTick(), 55, -20, 0);
		//--
		part.animationFrame(10, getTick(), 55, 0, 0);
		part.animationFrame(11, getTick(), 55, 10, 0);
		part.animationFrame(12, getTick(), 55, 30, 0);
		part.animationFrame(13, getTick(), 55, 45, 0);
		//--
		part.animationFrame(14, getTick(), 55, 55, 0);
		part.animationFrame(15, getTick(), 55, 65, 0);
		part.animationFrame(16, getTick(), 55, 75, 0);
		part.animationFrame(17, getTick(), 55, 85, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		part.animationFrame(0, getTick(), 10, -70, 0);
		part.animationFrame(1, getTick(), -10, -60, 25);
		part.animationFrame(2, getTick(), -30, -50, 50);
		part.animationFrame(3, getTick(), -50, -40, 75);
		part.animationFrame(4, getTick(), -70, -35, 95);
		part.animationFrame(5, getTick(), -90, -30, 110);
		//--
		part.animationFrame(6, getTick(), 27, -40, 0);
		part.animationFrame(7, getTick(), 35, -35, 0);
		part.animationFrame(8, getTick(), 47, -30, 0);
		part.animationFrame(9, getTick(), 85, -15, 0);
		//--
		part.animationFrame(10, getTick(), 90, -5, 30);
		part.animationFrame(11, getTick(), 95, 10, 50);
		part.animationFrame(12, getTick(), 105, 20, 70);
		part.animationFrame(13, getTick(), 110, 30, 90);
		//--
		part.animationFrame(14, getTick(), 108, 30, 90);
		part.animationFrame(15, getTick(), 106, 29, 88);
		part.animationFrame(16, getTick(), 104, 28, 86);
		part.animationFrame(17, getTick(), 102, 27, 84);
		part.animationFrame(18, getTick(), 100, 26, 82);
		part.animationFrame(19, getTick(), 98, 25, 80);
		part.animationFrame(20, getTick(), 96, 24, 78);
		part.animationFrame(21, getTick(), 94, 23, 76);
		part.animationFrame(22, getTick(), 92, 22, 74);
		part.animationFrame(23, getTick(), 90, 21, 72);
		part.animationFrame(24, getTick(), 88, 20, 70);
		part.animationFrame(25, getTick(), 86, 19, 68);
		part.animationFrame(26, getTick(), 84, 18, 66);
		part.animationFrame(27, getTick(), 82, 17, 64);
		part.animationFrame(28, getTick(), 80, 16, 62);
		part.animationFrame(29, getTick(), 78, 15, 60);
		part.animationFrame(30, getTick(), 76, 14, 58);
		part.animationFrame(31, getTick(), 74, 13, 56);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 4, -4, 0);
		part.animationFrame(2, getTick(), 8, -8, 0);
		part.animationFrame(3, getTick(), 12, -12, 0);
		part.animationFrame(4, getTick(), 16, -16, 0);
		part.animationFrame(5, getTick(), 20, -20, 0);
		//--
		part.animationFrame(6, getTick(), 23, -15, 0);
		part.animationFrame(7, getTick(), 25, -10, 0);
		part.animationFrame(8, getTick(), 27, -5, 0);
		part.animationFrame(9, getTick(), 30, 0, 0);
		//--
		part.animationFrame(10, getTick(), 25, 2, 0);
		part.animationFrame(11, getTick(), 20, 5, 0);
		part.animationFrame(12, getTick(), 15, 7, 0);
		part.animationFrame(13, getTick(), 10, 10, 0);
		//--
		part.animationFrame(14, getTick(), 12, 12, 0);
		part.animationFrame(15, getTick(), 15, 15, 0);
		part.animationFrame(16, getTick(), 18, 18, 0);
		part.animationFrame(17, getTick(), 20, 20, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		part.animationFrame(0, getTick(), 15, -10, 0);
		part.animationFrame(1, getTick(), 17, -15, 0);
		part.animationFrame(2, getTick(), 20, -20, 0);
		part.animationFrame(3, getTick(), 30, -30, 0);
		part.animationFrame(4, getTick(), 35, -40, 0);
		part.animationFrame(5, getTick(), 40, -55, 0);
		//--
		part.animationFrame(6, getTick(), 40, -50, 0);
		part.animationFrame(7, getTick(), 40, -45, 0);
		part.animationFrame(8, getTick(), 40, -43, 0);
		part.animationFrame(9, getTick(), 40, -40, 0);
		//--
		part.animationFrame(10, getTick(), 30, -10, -4);
		part.animationFrame(11, getTick(), 25, 0, -8);
		part.animationFrame(12, getTick(), 20, 20, -11);
		part.animationFrame(13, getTick(), 15, 40, -14);		
		part.animationFrame(14, getTick(), 10, 70, -17);
		part.animationFrame(15, getTick(), 0, 90, -20);		
	}

	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		part.animationFrame(0, getTick(), 0, 0, -20);
		part.animationFrame(1, getTick(), -10, -10, -16);
		part.animationFrame(2, getTick(), -15, -20, -12);
		part.animationFrame(3, getTick(), -20, -30, -8);
		part.animationFrame(4, getTick(), -25, -40, -4);
		part.animationFrame(5, getTick(), -30, -55, 0);
		//--
		part.animationFrame(6, getTick(), -50, -50, 0);
		part.animationFrame(7, getTick(), -60, -45, 0);
		part.animationFrame(8, getTick(), -80, -43, 0);
		part.animationFrame(9, getTick(), -100, -55, 0);
		//--
		part.animationFrame(10, getTick(), -100, -55, 0);
		part.animationFrame(11, getTick(), -80, -45, 0);
		part.animationFrame(12, getTick(), -60, -35, 0);
		part.animationFrame(13, getTick(), -40, -25, 0);		
		part.animationFrame(14, getTick(), -20, -10, 0);
		part.animationFrame(15, getTick(), 0, 0, 0);	
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		part.animationFrame(0, getTick(), 0, 0, -20);
		part.animationFrame(1, getTick(), 0, 10, -16);
		part.animationFrame(2, getTick(), 0, 20, -12);
		part.animationFrame(3, getTick(), 0, 30, -8);
		part.animationFrame(4, getTick(), 0, 40, -4);
		part.animationFrame(5, getTick(), 0, 55, 0);
		//--
		part.animationFrame(9, getTick(), 0, 55, 0);
		part.animationFrame(10, getTick(), 0, 63, 0);
		part.animationFrame(11, getTick(), 0, 71, 0);
		part.animationFrame(12, getTick(), 0, 78, 0);
		part.animationFrame(13, getTick(), 0, 86, 0);
		part.animationFrame(14, getTick(), 0, 90, 0);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		part.animationFrame(0, getTick(), 10, 50, 0);
		part.animationFrame(1, getTick(), 8, 40, 0);
		part.animationFrame(2, getTick(), 6, 30, 0);
		part.animationFrame(3, getTick(), 4, 25, 0);
		part.animationFrame(4, getTick(), 2, 20, 0);
		part.animationFrame(5, getTick(), 0, 15, 0);
		//--
		part.animationFrame(6, getTick(), 0, 15, 1);
		part.animationFrame(7, getTick(), 0, 25, 3);
		part.animationFrame(8, getTick(), 0, 35, 7);
		part.animationFrame(9, getTick(), 0, 40, 10);
		//--
		part.animationFrame(12, getTick(), 0, 40, 10);
		part.animationFrame(13, getTick(), 0, 60, 10);
		part.animationFrame(14, getTick(), 0, 75, 10);
		part.animationFrame(15, getTick(), 0, 90, 10);
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		part.animationFrame(0, getTick(), 0, -80, 0);
		part.animationFrame(1, getTick(), -10, -85, 0);
		part.animationFrame(2, getTick(), -20, -90, 0);
		part.animationFrame(3, getTick(), -30, -93, 0);
		part.animationFrame(4, getTick(), -40, -96, 0);
		part.animationFrame(5, getTick(), -50, -100, 0);
		//--
		part.animationFrame(6, getTick(), -50, -100, 0);
		part.animationFrame(7, getTick(), -50, -95, 0);
		part.animationFrame(8, getTick(), -50, -85, 0);
		part.animationFrame(9, getTick(), -50, -80, 0);
		//--
		part.animationFrame(10, getTick(), -50, -30, 0);	
		//--
		part.animationFrame(11, getTick(), -50, 5, 0);	
		//--
		part.animationFrame(12, getTick(), -50, 70, 0);	
		//--
		part.animationFrame(13, getTick(), -50, 110, 0);	
		part.animationFrame(14, getTick(), -45, 140, 0);
		part.animationFrame(15, getTick(), -40, 170, 0);	
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		part.animationFrame(0, getTick(), 0, -30, -70);
		part.animationFrame(1, getTick(), -15, -50, -55);
		part.animationFrame(2, getTick(), -30, -70, -45);
		part.animationFrame(3, getTick(), -45, -90, -30);
		part.animationFrame(4, getTick(), -60, -110, -15);
		part.animationFrame(5, getTick(), -70, -130, 0);
		//--
		part.animationFrame(6, getTick(), -72, -115, 0);
		part.animationFrame(7, getTick(), -74, -100, 0);
		part.animationFrame(8, getTick(), -76, -85, 0);
		part.animationFrame(9, getTick(), -80, -70, 0);
		//--
		part.animationFrame(10, getTick(), -80, -30, 0);	
		//--
		part.animationFrame(11, getTick(), -80, 5, 0);	
		//--
		part.animationFrame(12, getTick(), -60, 70, 0);	
		//--
		part.animationFrame(13, getTick(), -57, 100, 0);	
		part.animationFrame(14, getTick(), -53, 130, 0);
		part.animationFrame(15, getTick(), -50, 165, 0);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		part.animationFrame(0, getTick(), 0, -30, -70);
		part.animationFrame(1, getTick(), -15, -70, -60);
		part.animationFrame(2, getTick(), -30, -110, -45);
		part.animationFrame(3, getTick(), -45, -150, -30);
		part.animationFrame(4, getTick(), -60, -190, -10);
		part.animationFrame(5, getTick(), -80, -170, 0);
		//--
		part.animationFrame(6, getTick(), -80, -150, 0);
		part.animationFrame(7, getTick(), -80, -130, 0);
		part.animationFrame(8, getTick(), -80, -105, 0);
		part.animationFrame(9, getTick(), -80, -80, 0);
		//--
		part.animationFrame(10, getTick(), -80, -20, 0);	
		//--
		part.animationFrame(11, getTick(), -80, 5, 0);	
		//--
		part.animationFrame(12, getTick(), -60, 70, 0);	
		//--
		part.animationFrame(13, getTick(), -57, 100, 0);	
		part.animationFrame(14, getTick(), -53, 130, 0);
		part.animationFrame(15, getTick(), -50, 160, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animationFrame(0, getTick(), 100, -30, -70);
		part.animationFrame(1, getTick(), 80, -70, -60);
		part.animationFrame(2, getTick(), 60, -110, -45);
		part.animationFrame(3, getTick(), 40, -150, -30);
		part.animationFrame(4, getTick(), 20, -190, -10);
		part.animationFrame(5, getTick(), 10, -220, 0);
		//--
		part.animationFrame(6, getTick(), 8, -200, 0);
		part.animationFrame(7, getTick(), 6, -180, 0);
		part.animationFrame(8, getTick(), 3, -150, 0);
		part.animationFrame(9, getTick(), 0, -130, 0);
		//--
		part.animationFrame(10, getTick(), 0, -85, 0);	
		//--
		part.animationFrame(11, getTick(), 0, -30, 0);	
		//--
		part.animationFrame(12, getTick(), 30, 40, 0);	
		//--
		part.animationFrame(13, getTick(), 30, 55, 0);	
		part.animationFrame(14, getTick(), 30, 65, 0);
		part.animationFrame(15, getTick(), 30, 85, 0);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animationFrame(0, getTick(), -50, -75, -110);
		part.animationFrame(1, getTick(), -48, -65, -60);
		part.animationFrame(2, getTick(), -46, -60, -45);
		part.animationFrame(3, getTick(), -44, -55, -30);
		part.animationFrame(4, getTick(), -42, -50, -10);
		part.animationFrame(5, getTick(), -40, -45, 0);
		//--
		part.animationFrame(6, getTick(), -42, -35, 0);
		part.animationFrame(7, getTick(), -44, -25, 0);
		part.animationFrame(8, getTick(), -47, -15, 0);
		part.animationFrame(9, getTick(), -50, -10, 0);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationFrame(0, getTick(), 10, -110, 0);
		part.animationFrame(1, getTick(), 11, -95, 0);
		part.animationFrame(2, getTick(), 12, -80, 0);
		part.animationFrame(3, getTick(), 13, -65, 0);
		part.animationFrame(4, getTick(), 14, -55, 0);
		part.animationFrame(5, getTick(), 15, -45, 0);
		//--
		part.animationFrame(6, getTick(), 0, -35, 0);
		part.animationFrame(7, getTick(), -15, -26, 0);
		part.animationFrame(8, getTick(), -30, -18, 0);
		part.animationFrame(9, getTick(), -40, -10, 0);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animationFrame(0, getTick(), 0, -90, 0);
		part.animationFrame(1, getTick(), 0, 65, 0);
		part.animationFrame(2, getTick(), 0, 40, 0);
		part.animationFrame(3, getTick(), 0, 15, 0);
		part.animationFrame(4, getTick(), 0, -10, 0);
		part.animationFrame(5, getTick(), 0, -25, 0);
		//--
		part.animationFrame(6, getTick(), 0, -15, 0);
		part.animationFrame(7, getTick(), 0, -5, 0);
		part.animationFrame(8, getTick(), 0, 5, 0);
		part.animationFrame(9, getTick(), 0, 15, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationFrame(0, getTick(), -75, -15, 0);
		part.animationFrame(1, getTick(), -65, -5, 0);
		part.animationFrame(2, getTick(), -50, 5, 0);
		part.animationFrame(3, getTick(), -35, 15, 0);
		part.animationFrame(4, getTick(), -20, 25, 0);
		part.animationFrame(5, getTick(), -10, 35, 0);
		//--
		part.animationFrame(6, getTick(), 0, -35, 0);
		part.animationFrame(7, getTick(), 15, -25, 0);
		part.animationFrame(8, getTick(), 30, -15, 0);
		part.animationFrame(9, getTick(), 45, -75, 0);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animationFrame(0, getTick(), 15, 0, 5);
		part.animationFrame(1, getTick(), 16, -5, 4);
		part.animationFrame(2, getTick(), 17, 5, 3);
		part.animationFrame(3, getTick(), 18, 15, 2);
		part.animationFrame(4, getTick(), 19, 25, 1);
		part.animationFrame(5, getTick(), 20, 40, 0);
		//--
		part.animationFrame(6, getTick(), 19, 50, -2);
		part.animationFrame(7, getTick(), 18, 60, -4);
		part.animationFrame(8, getTick(), 17, 70, -7);
		part.animationFrame(9, getTick(), 15, 80, -10);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 0, 5, 0);
		part.animationFrame(2, getTick(), 0, 15, 0);
		part.animationFrame(3, getTick(), 0, 20, 0);
		part.animationFrame(4, getTick(), 0, 25, 0);
		part.animationFrame(5, getTick(), 0, 30, 0);
		//--
		part.animationFrame(6, getTick(), 5, 45, 0);
		part.animationFrame(7, getTick(), 10, 60, 0);
		part.animationFrame(8, getTick(), 15, 70, 0);
		part.animationFrame(9, getTick(), 20, 80, 0);
	}
}
