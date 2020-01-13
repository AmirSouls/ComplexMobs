package complexMobs.mob.lothicKnight.action;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.Part;

public class LeftSlash extends Action {
	
	public LeftSlash() {
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
				mob.setAction("right_slash");
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
		if (getTick() == 8) main.getWorld().playSound(main.getLocation(), "lothricknight.slash", 1, 1);
		if (getTick() == 6) main.getWorld().playSound(main.getLocation(), "lothricknight.grunt", 1, 1);
		if (getTick() == 11) main.getWorld().playSound(main.getLocation(), "lothricknight.walk", 1, 1);
	}
	
	protected void move() {
		if (8 > getTick()) getMob().move(.2, 20, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
		if (getTick() >= 8 && getTick() <= 12) mob.attackFrameSword(9, mob.getMain().getLocation().getDirection().multiply(.4).setY(.2));
		if (getTick() > 12) mob.getSwordWeapon().setHitPoints(null);
		
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		
		part.animationFrame(0, getTick(), 14, -80, 0);
		part.animationFrame(1, getTick(), 14, -65, 0);
		part.animationFrame(2, getTick(), 14, -50, 0);
		part.animationFrame(3, getTick(), 14, -30, 0);
		part.animationFrame(4, getTick(), 14, -15, 0);
		part.animationFrame(5, getTick(), 14, 0, 0);
		part.animationFrame(6, getTick(), 14, 14, 0);
		//--
		part.animationFrame(7, getTick(), 14,-20, 0);
		part.animationFrame(8, getTick(), 14, -55, 0);
		part.animationFrame(9, getTick(), 14, -80, 0);
		
		if (getTick() == 0) part.setOffset(new Vector(0,.3,0));
		if (getTick() == 8) part.setOffset(new Vector(0,.1,0));	
		if (getTick() == 9) part.setOffset(new Vector(0,.07,0));	
		if (getTick() == 10) part.setOffset(new Vector(0,-.04,0));	
		if (getTick() == 11) part.setOffset(new Vector(0,-.07,0));	
		if (getTick() == 12) part.setOffset(new Vector(0,-.12,0));		
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		
		part.animationFrame(0, getTick(), 14, -80, 0);
		part.animationFrame(1, getTick(), 14, -65, 0);
		part.animationFrame(2, getTick(), 14, -50, 0);
		part.animationFrame(3, getTick(), 14, -30, 0);
		part.animationFrame(4, getTick(), 14, -15, 0);
		part.animationFrame(5, getTick(), 14, 0, 0);
		part.animationFrame(6, getTick(), 14, 14, 0);
		//--
		part.animationFrame(7, getTick(), 14,-20, 0);
		part.animationFrame(8, getTick(), 14, -55, 0);
		part.animationFrame(9, getTick(), 14, -80, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		
		part.animationFrame(0, getTick(), 10, 0, 0);
		part.animationFrame(1, getTick(), 14, 0, -20);
		part.animationFrame(2, getTick(), 17, 0, -40);
		part.animationFrame(3, getTick(), 23, 0, -60);
		part.animationFrame(4, getTick(), 26, 0, -75);
		part.animationFrame(5, getTick(), 30, 0, -90);
		//--
		part.animationFrame(6, getTick(), 70, -40, -45);
		part.animationFrame(7, getTick(), 100, -80, 0);
		//--
		part.animationFrame(8, getTick(), 100, -80, 0);
		part.animationFrame(9, getTick(), 90, -79, 0);
		part.animationFrame(10, getTick(), 80, -78, 0);
		part.animationFrame(11, getTick(), 70, -78, 0);
		part.animationFrame(12, getTick(), 60, -77, 0);
		part.animationFrame(13, getTick(), 50, -76, 0);
		part.animationFrame(14, getTick(), 40, -75, 0);
		part.animationFrame(15, getTick(), 30, -74, 0);
		part.animationFrame(16, getTick(), 20, -73, 0);
		part.animationFrame(17, getTick(), 15, -72, 0);
		part.animationFrame(18, getTick(), 10, -71, 0);
		part.animationFrame(19, getTick(), 10, -70, 0);

	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		part.animationFrame(0, getTick(), 10, 10, 0);
		part.animationFrame(1, getTick(), 10, 10, 0);
		part.animationFrame(2, getTick(), 10, 10, 0);
		part.animationFrame(3, getTick(), 10, 10, 0);
		part.animationFrame(4, getTick(), 10, 10, 0);
		part.animationFrame(5, getTick(), 10, 10, 0);
		part.animationFrame(6, getTick(), 10, 9, 0);
		part.animationFrame(7, getTick(), 10, 8, 0);
		part.animationFrame(8, getTick(), 10, 7, 0);
		part.animationFrame(9, getTick(), 14, 5, 0);
		part.animationFrame(10, getTick(), 18, 2, 0);
		part.animationFrame(11, getTick(), 22, 0, 0);
		part.animationFrame(12, getTick(), 25, 0, 0);
		part.animationFrame(13, getTick(), 25, 0, 0);
		part.animationFrame(14, getTick(), 25, 0, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		
		part.animationFrame(0, getTick(), 0, 0, 0);
	}

	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		part.animation(0, 0, -20);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		part.animation(0, 0, -20);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		part.animation(10, 50, 0);
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		
		part.animationFrame(0, getTick(), 0, 0, 20);
		part.animationFrame(1, getTick(), 0, 90, 60);
		part.animationFrame(2, getTick(), 0, 180, 110);
		//--
		part.animationFrame(3, getTick(), 0, 180, 115);
		part.animationFrame(4, getTick(), 0, 180, 125);
		part.animationFrame(5, getTick(), 0, 180, 135);
		//--
		part.animationFrame(6, getTick(), 0, 45, 120);
		part.animationFrame(8, getTick(), 0, -70, 105);		
		//--
		part.animationFrame(9, getTick(), -45, -55, 95);
		part.animationFrame(10, getTick(), -90, -45, 90);	
		//--
		part.animationFrame(11, getTick(), -85, -65, 85);
		part.animationFrame(12, getTick(), -80, -80, 80);
		//--
		part.animationFrame(13, getTick(), -0, -80, 0);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		
		part.animationFrame(0, getTick(), 0, 0, 20);
		part.animationFrame(1, getTick(), -10, 90, 65);
		part.animationFrame(2, getTick(), -20, 180, 145);
		//--
		part.animationFrame(3, getTick(), -20, 180, 165);
		part.animationFrame(4, getTick(), -20, 180, 195);
		part.animationFrame(5, getTick(), -20, 180, 210);
		//--
		part.animationFrame(6, getTick(), -20, 180, 195);
		part.animationFrame(7, getTick(), -20, 180, 185);
		//--
		part.animationFrame(8, getTick(), -75, 90, 90);
		part.animationFrame(9, getTick(), -135, 0, 0);
		//--
		part.animationFrame(10, getTick(), -90, 10, -40);
		part.animationFrame(11, getTick(), -50, 20, -80);
		//--
		part.animationFrame(12, getTick(), 0, -30, -70);
		
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		
		part.animationFrame(0, getTick(), 0, 0, 20);
		part.animationFrame(1, getTick(), -10, 90, 80);
		part.animationFrame(2, getTick(), -20, 180, 145);
		//--
		part.animationFrame(3, getTick(), -20, 180, 145);
		part.animationFrame(4, getTick(), -10, 180, 180);
		part.animationFrame(5, getTick(), 0, 180, 210);
		//--
		part.animationFrame(6, getTick(), -10, 180, 200);
		part.animationFrame(7, getTick(), -20, 180, 185);
		//--
		part.animationFrame(8, getTick(), -55, 90, 45);
		part.animationFrame(9, getTick(), -90, 0, -90);
		//--
		part.animationFrame(8, getTick(), -70, 10, -85);
		part.animationFrame(9, getTick(), -50, 20, -80);
		//--
		part.animationFrame(10, getTick(), -25, -5, -75);
		part.animationFrame(11, getTick(), 0, -30, -70);
		
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		
		part.animationFrame(0, getTick(), -70, 40, 0);
		part.animationFrame(1, getTick(), -70, 40, 0);
		part.animationFrame(2, getTick(), -100, 60, 0);
		part.animationFrame(3, getTick(), -100, 60, 0);
		part.animationFrame(4, getTick(), -135, 50, 0);
		part.animationFrame(5, getTick(), -160, 60, 0);
		part.animationFrame(6, getTick(), -200, 70, 0);
		part.animationFrame(7, getTick(), -215, 50, 0);
		//--
		part.animationFrame(8, getTick(), -60, 60, 0);
		part.animationFrame(9, getTick(), -10, 40, 0);
		part.animationFrame(10, getTick(), 0, 10, 0);
		part.animationFrame(11, getTick(), 10, -60, 0);
		//--
		part.animationFrame(12, getTick(), 20, -80, 0);
		part.animationFrame(13, getTick(), 20, -90, 0);
		part.animationFrame(14, getTick(), 20, -100, 0);
		part.animationFrame(15, getTick(), 20, -110, 0);
		part.animationFrame(16, getTick(), 20, -120, 0);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 15, -25, 0);
		part.animationFrame(2, getTick(), 30, -45, 0);
		//--
		part.animationFrame(3, getTick(), 35, -45, -10);
		part.animationFrame(4, getTick(), 40, -45, -25);
		part.animationFrame(5, getTick(), 45, -45, -40);
		part.animationFrame(6, getTick(), 50, -45, -50);
		//--
		part.animationFrame(7, getTick(), 20, -60, -40);
		part.animationFrame(8, getTick(), -10, -75, -30);
		part.animationFrame(9, getTick(), -40, -90, -15);
		part.animationFrame(10, getTick(), -65, -100, -5);
		part.animationFrame(11, getTick(), -75, -110, 0);
		
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 20, -25, -20);
		part.animationFrame(2, getTick(), 40, -45, -40);
		//--
		part.animationFrame(3, getTick(), 45, -35, -30);
		part.animationFrame(4, getTick(), 50, -25, -15);
		part.animationFrame(5, getTick(), 60, -10, 0);
		//--
		part.animationFrame(6, getTick(), 60, -30, 0);
		part.animationFrame(7, getTick(), 45, -50, 0);
		part.animationFrame(8, getTick(), 35, -70, 0);
		part.animationFrame(9, getTick(), 20, -90, 0);
		part.animationFrame(10, getTick(), 10, -110, 0);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animation(0, 0, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), -5, 10, 0);
		part.animationFrame(2, getTick(), -15, 15, 0);
		part.animationFrame(3, getTick(), -25, 20, 0);
		part.animationFrame(4, getTick(), -35, 25, 0);
		part.animationFrame(5, getTick(), -45, 30, 0);
		//--
		part.animationFrame(6, getTick(), -45, 30, 0);
		part.animationFrame(7, getTick(), -50, 25, 0);
		part.animationFrame(8, getTick(), -55, 15, 0);
		part.animationFrame(9, getTick(), -60, 5, 0);
		part.animationFrame(10, getTick(), -65, -5, 0);
		part.animationFrame(11, getTick(), -75, -15, 0);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		part.animationFrame(0, getTick(), 0, 0, 0);
		part.animationFrame(1, getTick(), 6, 20, 0);
		part.animationFrame(2, getTick(), 12, 40, 0);
		part.animationFrame(4, getTick(), 15, 60, 0);
		//--
		part.animationFrame(5, getTick(), 15, 50, 1);
		part.animationFrame(6, getTick(), 15, 40, 2);
		part.animationFrame(7, getTick(), 15, 30, 3);
		part.animationFrame(8, getTick(), 15, 25, 4);
		part.animationFrame(9, getTick(), 15, 20, 5);
		part.animationFrame(10, getTick(), 15, 10, 5);
		part.animationFrame(11, getTick(), 15, 90, 5);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animation(0, 0, 0);
	}
}
