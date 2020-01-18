package complexMobs.mob.lothicKnight.action;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.AnimationState;
import complexMobs.object.ChildPart;
import complexMobs.object.Part;

public class Grab extends Action {
	
	public Grab() {
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
		if (getMob().getAction().contentEquals("riposte")) {
			return 0;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 14) main.getWorld().playSound(main.getLocation(), "lothricknight.shieldbash", 1, 1);
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
		
		if (getTick() <= 17) getMob().move(.25, 20, 0);
	}
	
	protected void attackFrame() {
		LothricKnight mob = (LothricKnight) getMob();
		
		if (getTick() == 14) {
			List<Entity> grabbableEntities = mob.getParts().get("left_hand").getArmorStand().getNearbyEntities(.4, .5, .4);
			grabbableEntities.addAll(mob.getParts().get("left_arm").getArmorStand().getNearbyEntities(.4, .5, .4));
			for (Entity entity : grabbableEntities) {
				if (entity instanceof Player) {
					Player p = (Player) entity;
					//!(lothricKnight.getNation() == Nations.getNation(player).getId())
					if (true) {
						p.damage(.01);
						p.playSound(p.getLocation(), "lothricknight.playerhurt", 1, 1);
						p.getWorld().spawnParticle(Particle.END_ROD, mob.getParts().get("left_hand").getArmorStand().getLocation().add(0,1.6,0), 1, 0, 0, 0, 0);
						
						mob.setVictim(p);
						mob.setAction("riposte");
						break;
					}
				}
			}
		}
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 0, -5, -80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -5, -60, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 60, 40, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 30, -10, 0);
		
		if (getTick() == 0) part.setOffset(new Vector(0,.2,0));
		if (getTick() == 17) part.setOffset(new Vector(0,.04,0));
		
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 0, -5, -80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -15, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 70, 50, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 30, -10, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 5, 0, 0, 5, -80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), 25, -50, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 35, 40, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), -10, 0, 0, 0, -10, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -20, -5, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 30, 5, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 20, -5, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, -40, 110, 10, 0);
		state = part.animationLargeFrame(state, 12, getTick(), 110, 10, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -70, 50, -90);
		state = part.animationLargeFrame(state, 14, getTick(), -70, 50, -90);
		state = part.animationLargeFrame(state, 40, getTick(), 20, 0, -50);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, -30, 110, 80, 0);
		state = part.animationLargeFrame(state, 12, getTick(), 110, 80, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -110, 50, -90);
		state = part.animationLargeFrame(state, 14, getTick(), -110, 50, -90);
		state = part.animationLargeFrame(state, 40, getTick(), 5, 0, -40);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, -20, 0, -40, -100);
		state = part.animationLargeFrame(state, 12, getTick(), 0, -40, -120);
		state = part.animationLargeFrame(state, 13, getTick(), -110, 20, -90);
		state = part.animationLargeFrame(state, 14, getTick(), -110, 20, -90);
		state = part.animationLargeFrame(state, 40, getTick(), 5, 0, -60);
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 100, -105, 60, 100, -105, 60);
		state = part.animationLargeFrame(state, 13, getTick(), 75, -145, 95);
		
		if (getTick() == 0)  {
			((ChildPart) getMob().getParts().get("shield")).setParent(getMob().getParts().get("chest"));
			getMob().getParts().get("shield").setOffset(new Vector(-.4,.9,-.1));
		}
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 30, -10, -80, 0);
		state = part.animationLargeFrame(state, 14, getTick(), 90, -30, 0);
		state = part.animationLargeFrame(state, 17, getTick(), 90, -30, 0);
		state = part.animationLargeFrame(state, 30, getTick(), 10, 0, 30);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 25, -30, -90, -45);
		state = part.animationLargeFrame(state, 14, getTick(), 90, -110, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 0, 0, 10);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 0, 0, 5, -30, -90, -45);
		state = part.animationLargeFrame(state, 14, getTick(), 90, -260, -260);
		state = part.animationLargeFrame(state, 40, getTick(), 90, -240, -240);
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword");
		getMob().getParts().get("sword").setOffset(new Vector(0,-.25,0));
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 45, -30, 0, -110, 0, 40);
		state = part.animationLargeFrame(state, 14, getTick(), 40, 50, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 15, 90, 0);
		
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		
		AnimationState state = part.animationLargeFrame(0, 3, getTick(), -30, -50, 0, -30, -50, 0);
		state = part.animationLargeFrame(state, 7, getTick(), -110, -10, 0);
		state = part.animationLargeFrame(state, 13, getTick(), -30, 10, 0);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		
		part.animationFrame(0, getTick(), 10, -10, -15);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		
		part.animationFrame(0, getTick(), 0, 10, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		
		AnimationState state = part.animationLargeFrame(0, 12, getTick(), 30, 30, 30, 30, 30, 30);
		state = part.animationLargeFrame(state, 13, getTick(), 0, 0, 10);
		state = part.animationLargeFrame(state, 14, getTick(), 0, 0, 10);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");
		
		AnimationState state = part.animationLargeFrame(0, 7, getTick(), 100, 40, 90, 100, 40, 100);
		state = part.animationLargeFrame(state, 13, getTick(), 100, 35, 100);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");
		
		part.animationFrame(0, getTick(), 0, 70, 0);
	}
}
