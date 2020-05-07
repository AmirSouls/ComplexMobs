package complexMobs.mob.lothicKnight.action;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Action;
import complexMobs.object.AnimationState;
import complexMobs.object.Part;

public class Force extends Action<LothricKnight> {
	
	public Force() {
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
			getMob().getParts().get("sword").setParent(getMob().getParts().get("right_hand"));
			getMob().getParts().get("sword").setOffset(new Vector(0,-.25,0));
			return -1;
		}
		return getTick()+1;
	}
	
	protected void playSound() {
		ArmorStand main = getMob().getMain();
		if (getTick() == 8) main.getWorld().playSound(main.getLocation(), "lothricknight.forcecharge", 1, 1);
		else if (getTick() == 22) {
			main.getWorld().playSound(main.getLocation(), "lothricknight.forceboom", 1, 1);
		}
	}
	
	protected void move() {

	}
	
	protected void attackFrame() {
		ArmorStand main = getMob().getMain();
		if (getTick() != 22) return;
		double particleCount = 40;
		main.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, main.getLocation().add(0,2,0), 700, .4, .4, .4, 1);
		
		//TODO Make this look cleaner
		for (int i = 0; i < particleCount; i++) { //Particle sphere loop
			
			for (int j = 0; j < particleCount; j++) {
				main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(
						Math.sin(i / particleCount * Math.PI * 2) * Math.sin(j / particleCount * Math.PI) * 4,
						(Math.cos(j / particleCount * Math.PI + Math.PI) / 2 + .5) * 7 - 1.5,
						Math.cos(i / particleCount * Math.PI * 2) * Math.sin(j / particleCount * Math.PI) * 4), 1, 0, 0, 0, .05);
			}
		}
		
		for (Entity entity : main.getNearbyEntities(4, 4, 4)) { //Damage nearby players loop
			if (!entity.getType().equals(EntityType.PLAYER)) continue;
			Location entityLoc = entity.getLocation();
			Location loc = main.getLocation();
			double xVec = (entityLoc.getX() - loc.getX()) / entityLoc.distance(loc);
			double yVec = (entityLoc.getY() - loc.getY()) / entityLoc.distance(loc);
			double zVec = (entityLoc.getZ() - loc.getZ()) / entityLoc.distance(loc);
			entity.setVelocity(new Vector(xVec, yVec, zVec));
			Player player = (Player) entity;
			player.damage(7);
		}
	}
	
	protected void pelvis() {
		Part part = getMob().getParts().get("pelvis");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, -10, 0, 0);
		if (getTick() == 0) part.setOffset(new Vector(0, .3, 0));
	}
	
	protected void chest() {
		Part part = getMob().getParts().get("chest");	
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 10, 0, 0);
		state = part.animationLargeFrame(state, 20, getTick(), 20, 0, 0);
		state = part.animationLargeFrame(20, 25, getTick(), 20, 0, 0, -20, 0, 0);
		state = part.animationLargeFrame(state, 40, getTick(), 0, 0, 0);
	}

	protected void cape() {
		Part part = getMob().getParts().get("cape");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), -15, 0, 0, -15, 0, 0);
		state = part.animationLargeFrame(state, 15, getTick(), 0, 0, 0);
	}

	protected void head() {
		Part part = getMob().getParts().get("head");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 0, 0, 0);
	}

	protected void leftElbow() {
		Part part = getMob().getParts().get("left_elbow");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 0, 0, -40);
		state = part.animationLargeFrame(state, 15, getTick(), -40, -20, 5);
		state = part.animationLargeFrame(20, 25, getTick(), -40, -20, 5, 30, 0, -30);
	}
		
	protected void leftArm() {
		Part part = getMob().getParts().get("left_arm");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, -45, -45, 0);
		state = part.animationLargeFrame(state, 15, getTick(), -50, 0, 145);
		state = part.animationLargeFrame(20, 25, getTick(), -50, 0, 145, -100, 10, 0);
	}

	protected void leftHand() {
		Part part = getMob().getParts().get("left_hand");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, -45, -45, 0);
		state = part.animationLargeFrame(state, 15, getTick(), 0, 90, 135);
		state = part.animationLargeFrame(20, 25, getTick(), 0, 90, 135, -130, 60, 0);
		
		if (getTick() <= 15) {
			part.getArmorStand().getWorld().spawnParticle(Particle.END_ROD, part.getArmorStand().getLocation().clone().add(0,1.6,0), 1, 0, 0, 0, 0);
		}
	}

	protected void shield() {
		Part part = getMob().getParts().get("shield");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 210, -90, -30, 210, -90, -30);
		getMob().getParts().get("shield").setParent(getMob().getParts().get("chest"));
		if (getTick() < 8) getMob().getParts().get("shield").setOffset(new Vector(0,1,-.2));
		else if (getTick() == 8) getMob().getParts().get("shield").setOffset(new Vector(0,1,-.4));
	}
	
	protected void rightElbow() {
		Part part = getMob().getParts().get("right_elbow");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 0, 0, 40);
		state = part.animationLargeFrame(state, 15, getTick(), -40, 10, 5);
		state = part.animationLargeFrame(20, 25, getTick(), -40, 10, 5, 30, 0, 30);
	}
	
	protected void rightArm() {
		Part part = getMob().getParts().get("right_arm");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, -45, 45, 0);
		state = part.animationLargeFrame(state, 15, getTick(), -5, 0, -135);
		state = part.animationLargeFrame(20, 25, getTick(), -50, 0, 145, -130, -10, 0);
	}

	protected void rightHand() {
		Part part = getMob().getParts().get("right_hand");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, -45, 45, 0);
		state = part.animationLargeFrame(state, 15, getTick(), 0, -90, -135);
		state = part.animationLargeFrame(20, 25, getTick(), 0, -90, -135, -100, -10, 0);
		
		if (getTick() <= 15) {
			part.getArmorStand().getWorld().spawnParticle(Particle.END_ROD, part.getArmorStand().getLocation().clone().add(0,1.6,0), 1, 0, 0, 0, 0);
		}
	}

	protected void sword() {
		Part part = getMob().getParts().get("sword"); 
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 90, 110, -30, 90, 110, -30);
		
		if (getTick() == 0)  {
			getMob().getParts().get("sword").setParent(getMob().getParts().get("chest"));
			getMob().getParts().get("sword").setOffset(new Vector(-.5,1.2,-.3));
		}
	}
	
	protected void leftThigh() {
		Part part = getMob().getParts().get("left_thigh");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, -10, -10, -20);
	}

	protected void leftCalf() {
		Part part = getMob().getParts().get("left_calf");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 10, -10, -5);
	}

	protected void leftFoot() {
		Part part = getMob().getParts().get("left_foot");
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 0, 0, 0);
	}

	protected void rightThigh() {
		Part part = getMob().getParts().get("right_thigh");
		AnimationState state = part.animationLargeFrame(0, 6, getTick(), 0, 0, 0, -10, 10, 20);
	}

	protected void rightCalf() {
		Part part = getMob().getParts().get("right_calf");		
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 10, 10, 5);
	}

	protected void rightFoot() {
		Part part = getMob().getParts().get("right_foot");	
		AnimationState state = part.animationLargeFrame(0, 8, getTick(), 0, 0, 0, 0, 0, 0);
	}
}
