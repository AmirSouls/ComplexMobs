package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.ChildPart;
import complexMobs.object.Part;

public class StanceThrust extends Action {
	
	public StanceThrust() {
		setReturnTick(50);
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
		if (getTick() >= getReturnTick()) {
			return -1;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 0) main.getWorld().playSound(main.getLocation(), "lothricknight.grunt", 1, 1);
		if (getTick() == 6) main.getWorld().playSound(main.getLocation(), "lothricknight.stanceattack", 2, 1);
	}
	
	protected void move() {
		
		double distance = ((LothricKnight) getMob()).getTargeter().getLocation().distance(((LothricKnight) getMob()).getTarget().getLocation());
		Location difference = ((LothricKnight) getMob()).getTargeter().getLocation().subtract(((LothricKnight) getMob()).getTarget().getLocation());
		Vector direction = difference.toVector().divide(new Vector(distance, distance, distance));
		
		float yaw = (float) (Math.atan2(direction.getX(), direction.getZ())*57.29);
		if (yaw > 0) yaw -= 360;
		yaw *= -1;
		yaw += 180;
		((CraftEntity) (((LothricKnight) getMob()).getTargeter())).getHandle().setHeadRotation(yaw);
		
		double moveAmount = Math.min(distance / 5 + .5, 5);
		
		if (getTick() <= 6) getMob().move(0, 20, 0);
		else if (getTick() <= 9) getMob().move(moveAmount, 60, 0);
		else if (getTick() <= 11) getMob().move(moveAmount, 0, 0);
		else if (getTick() >= 30) getMob().move(0, 10, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
		if (getTick() >= 6 && getTick() <= 13) mob.attackFrameSword(22, mob.getMain().getLocation().getDirection().multiply(2).setY(.3), true);
		if (getTick() > 13) mob.getSwordWeapon().setHitPoints(null);
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		
		part.animationFrame(0, getTick(), -5, 55, 10);
		part.animationFrame(1, getTick(), 5, 54, 9);
		part.animationFrame(2, getTick(), 10, 53, 8);
		part.animationFrame(3, getTick(), 15, 53, 6);
		part.animationFrame(4, getTick(), 20, 52, 4);
		part.animationFrame(5, getTick(), 25, 51, 2);
		part.animationFrame(6, getTick(), 30, 50, 0);
		//--
		part.animationFrame(7, getTick(), 25, 35, 0);
		part.animationFrame(8, getTick(), 20, 20, 0);
		part.animationFrame(9, getTick(), 15, -10, 0);
		part.animationFrame(10, getTick(), 10, -40, 0);
		part.animationFrame(11, getTick(), 0, -90, 0);
		//--
		part.animationFrame(30, getTick(), 0, -80, 0);
		part.animationFrame(31, getTick(), 0, -70, 0);
		part.animationFrame(32, getTick(), 0, -60, 0);
		part.animationFrame(33, getTick(), 0, -50, 0);
		part.animationFrame(34, getTick(), 0, -40, 0);
		part.animationFrame(35, getTick(), 0, -35, 0);
		part.animationFrame(36, getTick(), 0, -30, 0);
		part.animationFrame(37, getTick(), 0, -25, 0);
		part.animationFrame(38, getTick(), 0, -20, 0);
		part.animationFrame(39, getTick(), 0, -10, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);		
		
		if (getTick() == 0) part.setOffset(new Vector(0,.2,0));
		if (getTick() == 30) part.setOffset(new Vector(0,.4,0));
		
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		
		part.animationFrame(0, getTick(), 50, 50, 40);
		part.animationFrame(1, getTick(), 55, 47, 38);
		part.animationFrame(2, getTick(), 60, 45, 36);
		part.animationFrame(3, getTick(), 65, 43, 34);
		part.animationFrame(4, getTick(), 70, 40, 32);
		part.animationFrame(5, getTick(), 72, 37, 31);
		part.animationFrame(6, getTick(), 75, 35, 30);
		//--
		part.animationFrame(7, getTick(), 60, 10, 25);
		part.animationFrame(8, getTick(), 45, -15, 20);
		part.animationFrame(9, getTick(), 30, -40, 15);
		part.animationFrame(10, getTick(), 15, -65, 10);
		part.animationFrame(11, getTick(), 0, -90, 0);
		//--
		part.animationFrame(30, getTick(), 0, -90, 0);
		part.animationFrame(31, getTick(), 0, -80, 0);
		part.animationFrame(32, getTick(), 0, -70, 0);
		part.animationFrame(33, getTick(), 0, -60, 0);
		part.animationFrame(34, getTick(), 0, -50, 0);
		part.animationFrame(35, getTick(), 0, -45, 0);
		part.animationFrame(36, getTick(), 0, -40, 0);
		part.animationFrame(37, getTick(), 0, -35, 0);
		part.animationFrame(38, getTick(), 0, -30, 0);
		part.animationFrame(39, getTick(), 0, -15, 0);
		part.animationFrame(40, getTick(), 0, -15, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		
		part.animationFrame(0, getTick(), -35, 70, -30);
		part.animationFrame(1, getTick(), -10, 65, -20);
		part.animationFrame(2, getTick(), 15, 60, -10);
		part.animationFrame(3, getTick(), 40, 50, 0);
		part.animationFrame(4, getTick(), 60, 45, 10);
		part.animationFrame(5, getTick(), 75, 40, 20);
		part.animationFrame(6, getTick(), 90, 35, 30);
		//--
		part.animationFrame(7, getTick(), 80, 15, 30);
		part.animationFrame(8, getTick(), 60, -5, 30);
		part.animationFrame(9, getTick(), 40, -20, 30);
		part.animationFrame(10, getTick(), 20, -40, 30);
		part.animationFrame(11, getTick(), 0, -50, 130);
		//--
		part.animationFrame(13, getTick(), 0, -48, 126);
		part.animationFrame(14, getTick(), 0, -46, 122);
		part.animationFrame(15, getTick(), 0, -44, 118);
		part.animationFrame(16, getTick(), 0, -42, 114);
		part.animationFrame(17, getTick(), 0, -40, 110);
		part.animationFrame(18, getTick(), 0, -38, 106);
		part.animationFrame(19, getTick(), 0, -36, 102);
		part.animationFrame(20, getTick(), 0, -35, 98);
		part.animationFrame(21, getTick(), 0, -34, 94);
		part.animationFrame(22, getTick(), 0, -33, 90);
		part.animationFrame(23, getTick(), 0, -32, 86);
		part.animationFrame(24, getTick(), 0, -31, 82);
		part.animationFrame(25, getTick(), 0, -30, 78);
		part.animationFrame(26, getTick(), 0, -29, 74);
		part.animationFrame(27, getTick(), 0, -27, 70);
		part.animationFrame(28, getTick(), 0, -25, 66);
		part.animationFrame(29, getTick(), 0, -23, 62);
		part.animationFrame(30, getTick(), 0, -22, 58);
		part.animationFrame(31, getTick(), 0, -21, 54);
		part.animationFrame(32, getTick(), 0, -20, 46);
		part.animationFrame(33, getTick(), 0, -19, 42);
		part.animationFrame(34, getTick(), 0, -18, 38);
		part.animationFrame(35, getTick(), 0, -17, 34);
		part.animationFrame(36, getTick(), 0, -16, 30);
		part.animationFrame(37, getTick(), 0, -14, 26);
		part.animationFrame(38, getTick(), 0, -12, 22);
		part.animationFrame(39, getTick(), 0, -10, 18);
		
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		part.animationFrame(0, getTick(), 30, 0, 0);
		part.animationFrame(1, getTick(), 35, 1, 0);
		part.animationFrame(2, getTick(), 40, 2, 0);
		part.animationFrame(3, getTick(), 45, 4, 0);
		part.animationFrame(4, getTick(), 50, 6, 0);
		part.animationFrame(5, getTick(), 55, 8, 0);
		part.animationFrame(6, getTick(), 60, 10, 0);
		//--
		part.animationFrame(7, getTick(), 55, 5, 0);
		part.animationFrame(8, getTick(), 50, 0, 0);
		part.animationFrame(9, getTick(), 45, -5, 0);
		part.animationFrame(10, getTick(), 40, -15, 0);
		part.animationFrame(11, getTick(), 30, -20, 0);
		//--
		part.animationFrame(30, getTick(), 29, -18, 0);
		part.animationFrame(31, getTick(), 28, -16, 0);
		part.animationFrame(32, getTick(), 27, -14, 0);
		part.animationFrame(33, getTick(), 26, -12, 0);
		part.animationFrame(34, getTick(), 25, -10, 0);
		part.animationFrame(35, getTick(), 24, -8, 0);
		part.animationFrame(36, getTick(), 23, -6, 0);
		part.animationFrame(37, getTick(), 22, -4, 0);
		part.animationFrame(38, getTick(), 21, -2, 0);
		part.animationFrame(39, getTick(), 20, -1, 0);
		part.animationFrame(40, getTick(), 20, 0, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		
		part.animationFrame(0, getTick(),-90, 70, 0);
		part.animationFrame(1, getTick(), -75, 75, 15);
		part.animationFrame(2, getTick(), -60, 80, 20);
		part.animationFrame(3, getTick(), -45, 85, 25);
		part.animationFrame(4, getTick(), -30, 90, 30);
		part.animationFrame(5, getTick(), -15, 95, 35);
		part.animationFrame(6, getTick(), 0, 100, 40);
		//--
		part.animationFrame(7, getTick(), 0, 60, 10);
		part.animationFrame(8, getTick(), 20, 20, -20);
		part.animationFrame(9, getTick(), 40, -20, -50);
		part.animationFrame(10, getTick(), 60, -35, -70);
		part.animationFrame(11, getTick(), 90, -50, -90);
		//--
		part.animationFrame(30, getTick(), 80, -45, -80);
		part.animationFrame(31, getTick(), 70, -40, -75);
		part.animationFrame(32, getTick(), 60, -35, -70);
		part.animationFrame(33, getTick(), 50, -30, -60);
		part.animationFrame(34, getTick(), 40, -25, -50);
		part.animationFrame(35, getTick(), 30, -20, -40);
		part.animationFrame(36, getTick(), 20, -15, -30);
		part.animationFrame(37, getTick(), 15, -10, -30);
		part.animationFrame(38, getTick(), 10, -5, -30);
		part.animationFrame(39, getTick(), 15, -3, -30);
		part.animationFrame(40, getTick(), 20, 0, -30);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		
		part.animationFrame(0, getTick(), -130, 160, 0);
		part.animationFrame(1, getTick(), -90, 110, 5);
		part.animationFrame(2, getTick(), -50, 60, 15);
		part.animationFrame(3, getTick(), -10, 10, 25);
		part.animationFrame(4, getTick(), 30, -40, 35);
		part.animationFrame(5, getTick(), 60, -50, 40);
		part.animationFrame(6, getTick(), 90, -60, 50);
		//--
		part.animationFrame(7, getTick(), 75, -50, 40);
		part.animationFrame(8, getTick(), 60, -40, 30);
		part.animationFrame(9, getTick(), 45, -30, 20);
		part.animationFrame(10, getTick(), 30, -20, 10);
		part.animationFrame(11, getTick(), 20, 0, 0);
		//--
		part.animationFrame(30, getTick(), 18, 0, 0);
		part.animationFrame(31, getTick(), 16, 0, 0);
		part.animationFrame(32, getTick(), 14, 0, 0);
		part.animationFrame(33, getTick(), 12, 0, 0);
		part.animationFrame(34, getTick(), 10, 0, 0);
		part.animationFrame(35, getTick(), 8, 0, 0);
		part.animationFrame(36, getTick(), 6, 0, 0);
		part.animationFrame(37, getTick(), 4, 0, 0);
		part.animationFrame(38, getTick(), 2, 0, 0);
		part.animationFrame(39, getTick(), 0, 0, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		
		part.animationFrame(0, getTick(), 120, -40, 0);
		part.animationFrame(1, getTick(), 115, -41, 10);
		part.animationFrame(2, getTick(), 110, -42, 15);
		part.animationFrame(3, getTick(), 105, -48, 20);
		part.animationFrame(4, getTick(), 100, -52, 25);
		part.animationFrame(5, getTick(), 95, -56, 35);
		part.animationFrame(6, getTick(), 90, -60, 50);
		//--
		part.animationFrame(7, getTick(), 50, -50, 40);
		part.animationFrame(8, getTick(), 10, -40, 30);
		part.animationFrame(9, getTick(), -30, -30, 20);
		part.animationFrame(10, getTick(), -60, -20, 10);
		part.animationFrame(11, getTick(), -90, 0, 0);
		//--
		part.animationFrame(30, getTick(), -85, 0, 0);
		part.animationFrame(31, getTick(), -80, 0, 0);
		part.animationFrame(32, getTick(), -75, 0, 0);
		part.animationFrame(33, getTick(), -70, 0, 0);
		part.animationFrame(34, getTick(), -60, 0, 0);
		part.animationFrame(35, getTick(), -50, 0, 0);
		part.animationFrame(36, getTick(), -40, 0, 0);
		part.animationFrame(37, getTick(), -30, 0, 0);
		part.animationFrame(38, getTick(), -20, 0, 0);
		part.animationFrame(39, getTick(), -10, 0, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);		
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		
		part.animationFrame(0, getTick(), 180, -30, 0);
		part.animationFrame(1, getTick(), 180, -15, -20);
		part.animationFrame(2, getTick(), 180, 0, -30);
		part.animationFrame(3, getTick(), 180, 15, -40);
		part.animationFrame(4, getTick(), 180, 30, -50);
		part.animationFrame(5, getTick(), 180, 35, -60);
		part.animationFrame(6, getTick(), 180, 40, -70);
		//--
		part.animationFrame(7, getTick(), 150, 70, -40);
		part.animationFrame(8, getTick(), 150, 100, -10);
		part.animationFrame(9, getTick(), 150, 130, 10);
		part.animationFrame(10, getTick(), 150, 160, 20);
		part.animationFrame(11, getTick(), 150, 180, 30);
		//--
		part.animationFrame(30, getTick(), 135, 160, 25);
		part.animationFrame(31, getTick(), 120, 140, 20);
		part.animationFrame(32, getTick(), 105, 120, 15);
		part.animationFrame(33, getTick(), 90, 100, 10);
		part.animationFrame(34, getTick(), 75, 80, 10);
		part.animationFrame(35, getTick(), 60, 60, 10);
		part.animationFrame(36, getTick(), 45, 45, 10);
		part.animationFrame(37, getTick(), 30, 40, 10);
		part.animationFrame(38, getTick(), 15, 35, 10);
		part.animationFrame(39, getTick(), 10, 20, 10);
		part.animationFrame(40, getTick(), 10, 10, 10);
		
		if (getTick() == 30)  {
			((ChildPart) getMob().getParts().get("shield")).setParent(getMob().getParts().get("left_hand"));
			getMob().getParts().get("shield").setOffset(new Vector(0,-.5,0));
		}
		
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		
		part.animationFrame(0, getTick(), -145, 160, 0);
		part.animationFrame(1, getTick(), -100, 130, -2);
		part.animationFrame(2, getTick(), -50, 100, -4);
		part.animationFrame(3, getTick(), 0, 70, -8);
		part.animationFrame(4, getTick(), 50, 40, -12);
		part.animationFrame(5, getTick(), 90, 10, -16);
		part.animationFrame(6, getTick(), 125, 0, -20);
		//--
		part.animationFrame(7, getTick(), 80, 0, -15);
		part.animationFrame(8, getTick(), 40, 0, -10);
		part.animationFrame(9, getTick(), 0, 0, -5);
		part.animationFrame(10, getTick(), -40, 0, -2);
		part.animationFrame(11, getTick(), -90, 0, 0);
		//--
		part.animationFrame(30, getTick(), -85, 0, 0);
		part.animationFrame(31, getTick(), -80, 0, 0);
		part.animationFrame(32, getTick(), -75, 0, 0);
		part.animationFrame(33, getTick(), -70, 0, 0);
		part.animationFrame(34, getTick(), -60, 0, 0);
		part.animationFrame(35, getTick(), -50, 0, 0);
		part.animationFrame(36, getTick(), -40, 0, 0);
		part.animationFrame(37, getTick(), -30, 0, 0);
		part.animationFrame(38, getTick(), -20, 0, 0);
		part.animationFrame(39, getTick(), -10, 0, -5);
		part.animationFrame(40, getTick(), 20, 0, 20);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		
		part.animationFrame(0, getTick(), -40, 0, 45);
		part.animationFrame(1, getTick(), -38, 0, 50);
		part.animationFrame(2, getTick(), -37, 0, 55);
		part.animationFrame(3, getTick(), -35, 0, 60);
		part.animationFrame(4, getTick(), -30, 0, 65);
		part.animationFrame(5, getTick(), -25, 0, 67);
		part.animationFrame(6, getTick(), -20, 0, 70);
		//--
		part.animationFrame(7, getTick(), -30, -2, 55);
		part.animationFrame(8, getTick(), -45, -4, 40);
		part.animationFrame(9, getTick(), -60, -6, 25);
		part.animationFrame(10, getTick(), -70, -8, 10);
		part.animationFrame(11, getTick(), -90, -10, 0);
		//--
		part.animationFrame(30, getTick(), -85, -7, 0);
		part.animationFrame(31, getTick(), -80, -3, 0);
		part.animationFrame(32, getTick(), -75, 0, 0);
		part.animationFrame(33, getTick(), -70, 0, 0);
		part.animationFrame(34, getTick(), -60, 0, 0);
		part.animationFrame(35, getTick(), -50, 0, 0);
		part.animationFrame(36, getTick(), -40, 0, 0);
		part.animationFrame(37, getTick(), -30, 0, 0);
		part.animationFrame(38, getTick(), -20, 0, 0);
		part.animationFrame(39, getTick(), -10, 0, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		
		part.animationFrame(0, getTick(), 0, 0, 45);
		part.animationFrame(1, getTick(), 2, 0, 50);
		part.animationFrame(2, getTick(), 4, 0, 55);
		part.animationFrame(3, getTick(), 8, 0, 60);
		part.animationFrame(4, getTick(), 12, 0, 65);
		part.animationFrame(5, getTick(), 16, 0, 67);
		part.animationFrame(6, getTick(), 20, 0, 70);
		//--
		part.animationFrame(7, getTick(), 0, 0, 55);
		part.animationFrame(8, getTick(), -15, 0, 40);
		part.animationFrame(9, getTick(), -40, 0, 25);
		part.animationFrame(10, getTick(), -65, 0, 10);
		part.animationFrame(11, getTick(), -90, 0, 0);
		//--
		part.animationFrame(30, getTick(), -80, 0, 0);
		part.animationFrame(31, getTick(), -70, 0, 0);
		part.animationFrame(32, getTick(), -50, 0, 0);
		part.animationFrame(33, getTick(), -30, 0, 0);
		part.animationFrame(34, getTick(), -20, 0, 0);
		part.animationFrame(35, getTick(), -15, 0, 0);
		part.animationFrame(36, getTick(), -13, 0, 0);
		part.animationFrame(37, getTick(), -10, 0, 0);
		part.animationFrame(38, getTick(), -7, 0, 0);
		part.animationFrame(39, getTick(), -3, 0, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		
		part.animationFrame(0, getTick(), 7, -15, 60);
		part.animationFrame(1, getTick(), 11, -15, 60);
		part.animationFrame(2, getTick(), 15, -15, 60);
		part.animationFrame(3, getTick(), 19, -15, 60);
		part.animationFrame(4, getTick(), 23, -15, 60);
		part.animationFrame(5, getTick(), 27, -15, 60);
		part.animationFrame(6, getTick(), 30, -15, 60);
		//--
		part.animationFrame(7, getTick(), 25, -12, 50);
		part.animationFrame(8, getTick(), 20, -8, 35);
		part.animationFrame(9, getTick(), 15, -4, 20);
		part.animationFrame(10, getTick(), 12, -2, 10);
		part.animationFrame(11, getTick(), 0, 0, 0);
		//--
		part.animationFrame(30, getTick(), 0, 0, 0);
		part.animationFrame(31, getTick(), 10, 0, 1);
		part.animationFrame(32, getTick(), 15, 0, 2);
		part.animationFrame(33, getTick(), 22, 0, 4);
		part.animationFrame(34, getTick(), 26, 0, 6);
		part.animationFrame(35, getTick(), 30, 0, 8);
		part.animationFrame(36, getTick(), 35, 0, 12);
		part.animationFrame(37, getTick(), 41, 0, 14);
		part.animationFrame(38, getTick(), 44, 0, 16);
		part.animationFrame(39, getTick(), 47, 0, 18);
		part.animationFrame(40, getTick(), 50, 0, 20);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		
		part.animationFrame(0, getTick(), 10, 0, 0);
		part.animationFrame(1, getTick(), 14, 15, 0);
		part.animationFrame(2, getTick(), 18, 20, 0);
		part.animationFrame(3, getTick(), 22, 25, 0);
		part.animationFrame(4, getTick(), 26, 30, 0);
		part.animationFrame(5, getTick(), 28, 35, 0);
		part.animationFrame(6, getTick(), 30, 40, 0);
		//--
		part.animationFrame(7, getTick(), 20, 10, 5);
		part.animationFrame(8, getTick(), 10, -20, 10);
		part.animationFrame(9, getTick(), 0, -50, 15);
		part.animationFrame(10, getTick(), -15, -80, 25);
		part.animationFrame(11, getTick(), -30, -100, 30);
		//--
		part.animationFrame(30, getTick(), -29, -90, 28);
		part.animationFrame(31, getTick(), -29, -80, 26);
		part.animationFrame(32, getTick(), -28, -70, 24);
		part.animationFrame(33, getTick(), -26, -60, 22);
		part.animationFrame(34, getTick(), -24, -50, 20);
		part.animationFrame(35, getTick(), -22, -40, 18);
		part.animationFrame(36, getTick(), -20, -30, 16);
		part.animationFrame(37, getTick(), -15, -20, 10);
		part.animationFrame(38, getTick(), -10, -15, 5);
		part.animationFrame(39, getTick(), -5, -15, -5);
		part.animationFrame(40, getTick(), 0, -10, -10);
		
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		
		part.animationFrame(0, getTick(), 10, -10, -15);
		part.animationFrame(1, getTick(), 20, -5, -12);
		part.animationFrame(2, getTick(), 30, 5, -9);
		part.animationFrame(3, getTick(), 40, 10, -6);
		part.animationFrame(4, getTick(), 50, 15, -3);
		part.animationFrame(5, getTick(), 60, 25, -1);
		part.animationFrame(6, getTick(), 75, 30, 0);
		//--
		part.animationFrame(7, getTick(), 50, 60, 0);
		part.animationFrame(8, getTick(), 25, 90, 0);
		part.animationFrame(9, getTick(), 0, 120, 0);
		part.animationFrame(10, getTick(), -20, 135, 0);
		part.animationFrame(11, getTick(), -30, -150, 0);
		//--
		part.animationFrame(30, getTick(), 0, -140, 0);
		part.animationFrame(31, getTick(), 0, -130, 0);
		part.animationFrame(32, getTick(), 0, -120, 0);
		part.animationFrame(33, getTick(), 0, -100, 0);
		part.animationFrame(34, getTick(), 0, -90, 0);
		part.animationFrame(35, getTick(), 0, -80, 0);
		part.animationFrame(36, getTick(), 0, -70, 0);
		part.animationFrame(37, getTick(), 0, -60, 0);
		part.animationFrame(38, getTick(), 0, -30, 0);
		part.animationFrame(39, getTick(), 0, -15, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		
		part.animationFrame(0, getTick(), 0, 10, 0);
		part.animationFrame(1, getTick(), 5, 10, 0);
		part.animationFrame(2, getTick(), 10, 10, 0);
		part.animationFrame(3, getTick(), 20, 15, 0);
		part.animationFrame(4, getTick(), 30, 20, 0);
		part.animationFrame(5, getTick(), 40, 25, 0);
		part.animationFrame(6, getTick(), 50, 30, 0);
		//--
		part.animationFrame(7, getTick(), 40, 15, 0);
		part.animationFrame(8, getTick(), 30, 0, 0);
		part.animationFrame(9, getTick(), 20, -30, 0);
		part.animationFrame(10, getTick(), 10, -60, 0);
		part.animationFrame(11, getTick(), 0, -90, 0);
		//--
		part.animationFrame(30, getTick(), 0, -82, 0);
		part.animationFrame(31, getTick(), 0, 74, 0);
		part.animationFrame(32, getTick(), 0, 66, 0);
		part.animationFrame(33, getTick(), 0, 58, 0);
		part.animationFrame(34, getTick(), 0, 50, 0);
		part.animationFrame(35, getTick(), 0, 42, 0);
		part.animationFrame(36, getTick(), 0, 36, 0);
		part.animationFrame(37, getTick(), 0, 28, 0);
		part.animationFrame(38, getTick(), 0, 20, 0);
		part.animationFrame(39, getTick(), 0, 10, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		
		part.animationFrame(0, getTick(), -25, 110, -60);
		part.animationFrame(1, getTick(), -10, 90, -50);
		part.animationFrame(2, getTick(), 5, 70, -40);
		part.animationFrame(3, getTick(), 20, 50, -20);
		part.animationFrame(4, getTick(), 35, 30, -10);
		part.animationFrame(5, getTick(), 40, 10, -5);
		part.animationFrame(6, getTick(), 50, 0, 0);
		//--
		part.animationFrame(7, getTick(), 50, -15, 0);
		part.animationFrame(8, getTick(), 40, -30, 0);
		part.animationFrame(9, getTick(), 30, -45, 0);
		part.animationFrame(10, getTick(), 20, -60, 0);
		part.animationFrame(11, getTick(), 15, -70, 0);
		//--
		part.animationFrame(30, getTick(), 14, -65, 0);
		part.animationFrame(31, getTick(), 13, -60, 0);
		part.animationFrame(32, getTick(), 11, -55, 0);
		part.animationFrame(33, getTick(), 10, -50, 0);
		part.animationFrame(34, getTick(), 9, -45, 0);
		part.animationFrame(35, getTick(), 8, -40, 0);
		part.animationFrame(36, getTick(), 6, -35, 0);
		part.animationFrame(37, getTick(), 5, -30, 0);
		part.animationFrame(38, getTick(), 4, -20, 0);
		part.animationFrame(39, getTick(), 2, -10, 0);
		part.animationFrame(40, getTick(), 0, 0, 5);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		
		part.animationFrame(0, getTick(), 90, 60, 90);
		part.animationFrame(1, getTick(), 88, 58, 70);
		part.animationFrame(2, getTick(), 86, 56, 60);
		part.animationFrame(3, getTick(), 84, 54, 50);
		part.animationFrame(4, getTick(), 82, 52, 40);
		part.animationFrame(5, getTick(), 80, 51, 20);
		part.animationFrame(6, getTick(), 75, 50, 0);
		//--
		part.animationFrame(7, getTick(), 60, 30, 0);
		part.animationFrame(8, getTick(), 55, 10, 0);
		part.animationFrame(9, getTick(), 50, -10, 0);
		part.animationFrame(10, getTick(), 40, -30, 0);
		part.animationFrame(11, getTick(), 30, -55, 0);
		//--
		part.animationFrame(30, getTick(), 27, -50, 0);
		part.animationFrame(31, getTick(), 24, -45, 0);
		part.animationFrame(32, getTick(), 21, -40, 0);
		part.animationFrame(33, getTick(), 18, -35, 0);
		part.animationFrame(34, getTick(), 15, -30, 0);
		part.animationFrame(35, getTick(), 12, -20, 0);
		part.animationFrame(36, getTick(), 9, -10, 0);
		part.animationFrame(37, getTick(), 6, -5, 0);
		part.animationFrame(38, getTick(), 4, -2, 0);
		part.animationFrame(39, getTick(), 2, -1, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		
		part.animationFrame(0, getTick(), 0, 70, 0);
		part.animationFrame(1, getTick(), 10, 66, 2);
		part.animationFrame(2, getTick(), 20, 62, 4);
		part.animationFrame(3, getTick(), 30, 58, 6);
		part.animationFrame(4, getTick(), 45, 54, 7);
		part.animationFrame(5, getTick(), 60, 52, 8);
		part.animationFrame(6, getTick(), 75, 50, 10);
		//--
		part.animationFrame(7, getTick(), 60, 35, 8);
		part.animationFrame(8, getTick(), 45, -5, 6);
		part.animationFrame(9, getTick(), 30, -25, 4);
		part.animationFrame(10, getTick(), 15, -30, 2);
		part.animationFrame(11, getTick(), 0, -45, 0);
		//--
		part.animationFrame(30, getTick(), 0, -40, 0);
		part.animationFrame(31, getTick(), 0, -35, 0);
		part.animationFrame(32, getTick(), 0, -32, 0);
		part.animationFrame(33, getTick(), 0, -30, 0);
		part.animationFrame(34, getTick(), 0, -25, 0);
		part.animationFrame(35, getTick(), 0, -23, 0);
		part.animationFrame(36, getTick(), 0, -20, 0);
		part.animationFrame(37, getTick(), 0, -15, 0);
		part.animationFrame(38, getTick(), 0, -10, 0);
		part.animationFrame(39, getTick(), 0, -5, 0);
		part.animationFrame(40, getTick(), 0, 0, 0);
	}
}
