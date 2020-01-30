package complexMobs.mob.lothicKnight.action;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.AnimationState;
import complexMobs.object.Part;

public class Riposte extends Action {
	
	public Riposte() {
		setReturnTick(140);
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
		if (getTick() == 60) main.getWorld().playSound(main.getLocation(), "lothricknight.grunt", 1, 1);
		if (getTick() == 65) main.getWorld().playSound(main.getLocation(), "lothricknight.playerhurt", 1, 1);
		if (getTick() == 65) main.getWorld().spawnParticle(Particle.BLOCK_CRACK, ((LothricKnight) getMob()).getVictim().getLocation().add(0,1,0), 400, .1, .2, .1, .5, Material.REDSTONE_WIRE.createBlockData(), true);
		if (getTick() == 85) main.getWorld().playSound(main.getLocation(), "lothricknight.grunt", 1, 1);
	}
	
	protected void move() {
		getMob().move(0, 0, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
		Player victim = mob.getVictim();
		if (victim == null && getTick() < 90) {
			mob.setAction("idle");
			return;
		}
		else if (victim.isDead() && getTick() < 90) {
			mob.setAction("idle");
			return;
		}
		Part part;
		Vector weaponMid = new Vector(0,0,0);
		if (getTick() < 64) part = mob.getParts().get("left_hand");
		else {
			part = mob.getParts().get("sword");
			weaponMid = new Vector(0,0,.9);
		}
		EulerAngle weaponAngle = part.getHeadPose();
		weaponMid.rotateAroundX(weaponAngle.getX());
		weaponMid.rotateAroundY(-weaponAngle.getY());
		weaponMid.rotateAroundZ(-weaponAngle.getZ());
		weaponMid.rotateAroundAxis(new Vector(0,1,0), part.getArmorStand().getLocation().getYaw() / -57.29);
		Vector victimPosition = part.getArmorStand().getLocation().add(weaponMid).add(0,.4,0).toVector();
		double x = victimPosition.getX();
		double y = victimPosition.getY();
		double z = victimPosition.getZ();
		victim.setFallDistance(0);
		if (getTick() < 90)((CraftEntity) victim).getHandle().setPosition(x, y, z);
		if (getTick() < 90) victim.setVelocity(new Vector(.15-Math.random()*.3, .15-Math.random()*.3, .15-Math.random()*.3));
		else if (getTick() == 90) {
			victim.setVelocity(mob.getMain().getLocation().getDirection().multiply(4).rotateAroundY(-80 / 57.29).setY(-1));
		}
		else if (getTick() < 110) {
			victim.getWorld().spawnParticle(Particle.BLOCK_CRACK, ((LothricKnight) getMob()).getVictim().getLocation().add(0,1,0), 4, 0, 0, 0, .5, Material.REDSTONE_WIRE.createBlockData(), true);
		}
		
		if (getTick() == 100) {
			victim.damage(10);
		}
		
		if (getTick() == 64) {
			victim.setHealth(victim.getHealth() * .25);
			victim.damage(.00001);
		}
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 60, 40, 0, 60, 40, 0);
		part.animationLargeFrame(state, 20, getTick(), 10, 20, 0);
		state = part.animationLargeFrame(60, 65, getTick(), 10, 20, 0, -10, -30, 0);
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 50, 50, 0, 50, 50, 0);
		part.animationLargeFrame(state, 20, getTick(), 10, 20, 0);
		part.animationLargeFrame(60, 65, getTick(), 10, 20, 0, -10, -30, 0);
		state = part.animationLargeFrame(85, 90, getTick(), -10, -30, 0, -10, 30, 0);
		part.animationLargeFrame(110, 140, getTick(), -10, 30, 0, 0, -15, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 45, 40, 0, 45, 40, 0);
		state = part.animationLargeFrame(state, 20, getTick(), 15, -30, 0);
		part.animationLargeFrame(85, 90, getTick(), -120, -20, 0, 80, 30, 0);
		part.animationLargeFrame(90, 130, getTick(), 80, 30, 0, 15, -15, 18);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 30, 5, 0, 30, 5, 0);
		state = part.animationLargeFrame(state, 20, getTick(), 35, -30, 0);
		state = part.animationLargeFrame(state, 30, getTick(), -30, -90, 0);
		part.animationLargeFrame(85, 95, getTick(), -40, -90, 0, -10, 90, 0);
		part.animationLargeFrame(110, 140, getTick(), -10, 90, 0, 0, 10, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), -70, 50, -90, -70, 50, -90);
		state = part.animationLargeFrame(state, 20, getTick(), 20, 0, 0);
		part.animationLargeFrame(state, 30, getTick(), -140, -90, 0);
		part.animationLargeFrame(60, 64, getTick(), -140, -90, 0, -120, -60, 0);
		state = part.animationLargeFrame(85, 90, getTick(), -120, -60, 0, -80, 50, 0);
		part.animationLargeFrame(110, 140, getTick(), -80, 50, 0, 20, 0, -30);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), -110, 50, -90, -110, 50, -90);
		part.animationLargeFrame(state, 30, getTick(), -140, -90, 0);
		state = part.animationLargeFrame(60, 64, getTick(), -140, -90, 0, -120, 20, 0);
		state = part.animationLargeFrame(85, 90, getTick(), -120, -20, 0, -80, 80, 0);
		part.animationLargeFrame(110, 140, getTick(), -80, 80, 0, 0, 0, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), -110, 20, -90, -110, 20, -90);
		state = part.animationLargeFrame(state, 30, getTick(), -140, -90, 0);
		part.animationLargeFrame(60, 64, getTick(), -140, -90, 0, -20, 70, 0);
		state = part.animationLargeFrame(85, 90, getTick(), -20, 70, 0, -80, 100, 0);
		part.animationLargeFrame(state, 140, getTick(), 0, 0, 0);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		part.animationLargeFrame(0, 4, getTick(), 15, -115, 95, 15, -115, 95);
		part.animationLargeFrame(120, 140, getTick(), 15, -115, 95, 10, 10, 10);
		if (getTick() == 120)  {
			getMob().getParts().get("shield").setParent(getMob().getParts().get("left_hand"));
			getMob().getParts().get("shield").setOffset(new Vector(0,-.5,0));
		}
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 90, -30, 0, 90, -30, 0);
		part.animationLargeFrame(state, 20, getTick(), 20, 40, 0);
		part.animationLargeFrame(20, 40, getTick(), 20, 50, 0, 10, -10, 90);
		part.animationLargeFrame(60, 65, getTick(), 10, -10, 90, -160, 0, 80);
		state = part.animationLargeFrame(85, 90, getTick(), -160, 0, 80, -20, 0, 30);
		part.animationLargeFrame(120, 140, getTick(), -20, 0, 30, 20, 0, 20);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 90, -110, 0, 90, -110, 0);
		state = part.animationLargeFrame(state, 20, getTick(), 20, 0, 20);
		part.animationLargeFrame(20, 40, getTick(), 20, 0, 20, -10, -10, 60);
		state = part.animationLargeFrame(60, 65, getTick(), -10, -10, 60, -220, 0, 80);
		state = part.animationLargeFrame(85, 90, getTick(), -220, 0, 80, -40, 0, 60);
		part.animationLargeFrame(120, 140, getTick(), -40, 0, 60, 0, 0, 0);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		part.animationLargeFrame(0, 4, getTick(), 90, -260, -260, 90, -260, -260);
		part.animationLargeFrame(20, 40, getTick(), -40, 0, 0, -40, 0, 0);
		part.animationLargeFrame(60, 65, getTick(), -10, -10, 60, -220, 0, 80);
		part.animationLargeFrame(85, 90, getTick(), -220, 0, 80, -90, -90, 180);
		part.animationLargeFrame(120, 140, getTick(), -90, -90, 180, 0, 0, 0);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		part.animationLargeFrame(0, 4, getTick(), 40, 50, 0, 40, 50, 0);
		part.animationLargeFrame(20, 40, getTick(), 40, 50, 0, -40, -85, 0);
		part.animationLargeFrame(60, 65, getTick(), -40, -85, 0, -40, -95, 0);
		part.animationLargeFrame(85, 90, getTick(), -40, -95, 0, 0, 90, 0);
		part.animationLargeFrame(120, 140, getTick(), 0, 90, 0, 50, 0, 20);
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		part.animationLargeFrame(0, 4, getTick(), -30, 10, 0, -30, 10, 0);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		part.animationLargeFrame(0, 4, getTick(), 10, -10, -15, 10, -10, -15);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		part.animationLargeFrame(0, 4, getTick(), 0, 10, 0, 0, 10, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		part.animationLargeFrame(0, 4, getTick(), 0, 0, 10, 0, 0, 10);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		AnimationState state = part.animationLargeFrame(0, 4, getTick(), 100, 35, 100, 100, 35, 100);
		state = part.animationLargeFrame(state, 20, getTick(), 25, 0, 0);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		part.animationLargeFrame(0, 4, getTick(), 0, 70, 0, 0, 70, 0);
	}
}
