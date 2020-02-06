package complexMobs.mob.lothicKnight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftMonster;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.mob.lothicKnight.action.Backstep;
import complexMobs.mob.lothicKnight.action.Death;
import complexMobs.mob.lothicKnight.action.Grab;
import complexMobs.mob.lothicKnight.action.Idle;
import complexMobs.mob.lothicKnight.action.LeftSlash;
import complexMobs.mob.lothicKnight.action.RightSlash;
import complexMobs.mob.lothicKnight.action.Riposte;
import complexMobs.mob.lothicKnight.action.Running;
import complexMobs.mob.lothicKnight.action.Sidestepping;
import complexMobs.mob.lothicKnight.action.Stance;
import complexMobs.mob.lothicKnight.action.StanceThrust;
import complexMobs.mob.lothicKnight.action.Walking;
import complexMobs.mob.lothicKnight.action.WalkingBack;
import complexMobs.object.Part;
import complexMobs.util.IsWallBetween;

public class Run {

	private int changeTick = 0;
	private int tick = 0;
	private boolean forceChange = false;
	private boolean attacking = false;
	private Wither bossBar;
	private LothricKnight lothricKnight;
	
	public void run(LothricKnight lothricKnight, JavaPlugin plugin) {
		this.lothricKnight = lothricKnight;
		
		new BukkitRunnable() {
			public void run() {
				
				if (lothricKnight.isRemoved()) {
					bossBar.remove();
					this.cancel();
					return;
				}
				
				calculateHealth();
				calculateStamina();
				calculatePoise();
				otherTasks();
				targeting();
				
				if (lothricKnight.getAction() == null) lothricKnight.setAction("idle"); //Default animation
				
				if (lothricKnight.getTarget() != null) { //Target attacking logic
					attackingLogic();
				}
				else { //Non-attacking logic
					nonAttackingLogic();
				}
				
				//Check for attack action end
				if (tick == -1) {
					attacking = false;
					tick = 0;
					changeTick = 99;
					lothricKnight.setAction(null);
					return;
				}
				
				//Execute action
				switch(lothricKnight.getAction()) {
				
				case "idle":
					tick = new Idle().run(lothricKnight, tick);
					break;
				case "walking":
					tick = new Walking().run(lothricKnight, tick);
					break;
				case "walking_back":
					tick = new WalkingBack().run(lothricKnight, tick);
					if (!lothricKnight.getMain().getLocation().add(lothricKnight.getMain().getLocation().getDirection().multiply(-1)).getBlock().isPassable()) {
						forceChange = true;
					}
					break;
				case "sidestepping":
					tick = new Sidestepping().run(lothricKnight, tick);
					if (!lothricKnight.getMain().getLocation().add(lothricKnight.getMain().getLocation().getDirection().rotateAroundY(-80)).getBlock().isPassable()) {
						forceChange = true;
					}
					break;
				case "running":
					tick = new Running().run(lothricKnight, tick);
					break;
				//Attacks and non-looping movements
				case "right_slash":
					attacking = true;
					tick = new RightSlash().run(lothricKnight, tick);
					break;
				case "left_slash":
					attacking = true;
					tick = new LeftSlash().run(lothricKnight, tick);
					break;
				case "backstep":
					attacking = true;
					tick = new Backstep().run(lothricKnight, tick);
					break;
				case "grab":
					attacking = true;
					tick = new Grab().run(lothricKnight, tick);
					break;
				case "riposte":
					attacking = true;
					tick = new Riposte().run(lothricKnight, tick);
					break;
				case "stance":
					lothricKnight.getParts().get("shield").setParent(lothricKnight.getParts().get("chest"));
					lothricKnight.getParts().get("shield").setOffset(new Vector(-.4,.9,-.1));
					attacking = true;
					tick = new Stance().run(lothricKnight, tick);
					break;
				case "stance_thrust":
					attacking = true;
					tick = new StanceThrust().run(lothricKnight, tick);
					break;
				case "death":
					tick = new Death().run(lothricKnight, tick);
					break;
				}
				changeTick++;
				
				//Position parts
				for (Part part : lothricKnight.getParts().values()) { part.position(); }
				for (Part part : lothricKnight.getParts().values()) { part.resetPosition(); }
				
			}
		}.runTaskTimer(plugin, 0, 0);
	}
	
	
	private void attackingLogic() {
		
		if (lothricKnight.isDead()) return;
		
		Location loc = lothricKnight.getMain().getLocation();
		
		double distance = loc.distance(lothricKnight.getTarget().getLocation());
		if (distance < 2 && !lothricKnight.getAction().contentEquals("idle")) forceChange = true;
		if (distance > 3 && lothricKnight.getAction().contentEquals("idle")) forceChange = true;
		
		//Passive actions
		if (((changeTick > 100 && Math.random() < .02) || forceChange) && !attacking) {
			forceChange = false;
			List<String> actions = new ArrayList<>(); //Actions to choose from
			if (!(distance < 2)) {
				
				actions.add("walking");
				
				if (!IsWallBetween.check(lothricKnight.getTarget(), lothricKnight.getMain())) {
					actions.add("walking_back");
					actions.add("sidestepping");
					actions.add("running");
					if (distance > 12) actions.add("running"); actions.add("running");
				}
			}
			else actions.add("idle");
			
			//Select action
			Collections.shuffle(actions);
			lothricKnight.setAction(actions.get(0));
			changeTick = 0;
			tick = 0;
			
			//Shield
			lothricKnight.getParts().get("shield").setParent(lothricKnight.getParts().get("left_hand"));
			lothricKnight.getParts().get("shield").setOffset(new Vector(0,-.5,0));
			lothricKnight.setShieldIsUp(false);
			if (Math.random() < .4 && !lothricKnight.getAction().contentEquals("running")) lothricKnight.setShieldIsUp(true);
		}
		
		if (!attacking) {
			//Attack actions
			List<String> actions = new ArrayList<>(); //Actions to choose from
			if (distance < 4 && lothricKnight.getStamina() > 25) {
				actions.add("right_slash");
				actions.add("left_slash");
				if (Math.random() < .2) actions.add("backstep");
				actions.add("grab");
				lothricKnight.setStamina(lothricKnight.getStamina() - 25);
				lothricKnight.setStaminaUseTick(lothricKnight.getStaminaUseTickMax());
			}
			else if (distance < 4 && Math.random() < .03) {
				actions.add("backstep");
				lothricKnight.setStamina(lothricKnight.getStamina() - 10);
				lothricKnight.setStaminaUseTick(lothricKnight.getStaminaUseTickMax());
			}
			else if (Math.random() < .004) {
				actions.add("stance");
			}
			
			if (!actions.isEmpty()) {
				Collections.shuffle(actions);
				lothricKnight.setAction(actions.get(0));
				lothricKnight.setShieldIsUp(false);
				changeTick = 0;
				tick = 0;
			}
		}
	}

	
	private void nonAttackingLogic() {
		
		if (lothricKnight.isDead()) return;
		
		attacking = false;
		
		//Shield
		lothricKnight.getParts().get("shield").setParent(lothricKnight.getParts().get("left_hand"));
		lothricKnight.getParts().get("shield").setOffset(new Vector(0,-.5,0));
		
		//Pathfind selection
		if (changeTick % 50 == 0) {
			Location loc = lothricKnight.getMain().getLocation();
			if (loc.distance(lothricKnight.getPost().toLocation(loc.getWorld())) < 5) {
				((CraftMonster) lothricKnight.getBrain()).getHandle().setGoalTarget(null);
				lothricKnight.setAction("idle");
				lothricKnight.setShieldIsUp(false);
			}
			else {
				((CraftMonster) lothricKnight.getBrain()).getHandle().getNavigation().a(lothricKnight.getPost().getX(), lothricKnight.getPost().getY(), lothricKnight.getPost().getZ());
				lothricKnight.setAction("walking");
			}
		}
		
		//Action selection
		if (changeTick > 200 && Math.random() < .01 || forceChange) { //Minimum 200 ticks before changing, random tick after that
			
			forceChange = false;
			List<String> actions = new ArrayList<>(); actions.add("walking"); actions.add("walking"); //Actions to choose from
			Collections.shuffle(actions);
			lothricKnight.setAction(actions.get(0));
			changeTick = 0;
		}
	}
	
	
	private void targeting() {
		if (lothricKnight.getTarget() != null) {
			if (lothricKnight.getTarget().isDead() || lothricKnight.getTarget().getLocation().distance(lothricKnight.getMain().getLocation()) > 20 || Math.random() < .01) {
				forceChange = true;
				lothricKnight.setTarget(null); 
			}
		}
		
		if (lothricKnight.getTarget() == null) {
			findNewTarget(); //Find new target
		}
	}
	
	
	private void findNewTarget() {
		
		List<Entity> nearbyEntities = lothricKnight.getMain().getNearbyEntities(20, 20, 20);
		if (nearbyEntities.isEmpty()) return;
		Player nearestPlayer = null;
		double nearestPlayerDistance = 1000;
		
		for (Entity entity : nearbyEntities) {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				if ((player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE)) {
					if (nearestPlayer == null) nearestPlayer = player;
					double playerDistance = player.getLocation().distance(lothricKnight.getMain().getLocation());
					if (playerDistance < nearestPlayerDistance && Math.random() > .3 && !IsWallBetween.check(player, entity)) {
						 nearestPlayer = player;
						 nearestPlayerDistance = playerDistance;
					}
				}
			}
		}
		if (nearestPlayer != null) {
			lothricKnight.setTarget(nearestPlayer);
		}
	}
	
	private void calculateHealth() {
		
		if (lothricKnight.getInvulTick() > 0) lothricKnight.setInvulTick(lothricKnight.getInvulTick() - 1);;
		
		if (lothricKnight.getHealth() <= 0) {
			if (!lothricKnight.isDead()) tick = 0;
			lothricKnight.setDead(true);
 			lothricKnight.setAction("death");
		}
		else if (lothricKnight.getHealth() > lothricKnight.getMaxHealth()) {
			lothricKnight.setHealth(lothricKnight.getMaxHealth());
		}
		else {
			lothricKnight.setHealth(lothricKnight.getHealth() + .15);
		}
		
		if (bossBar != null) {
			bossBar.setHealth(Math.max(0, Math.min(lothricKnight.getHealth(), lothricKnight.getMaxHealth())));
		}
	}
	
	private void calculateStamina() {
		
		if (lothricKnight.getStaminaUseTick() == 0) {
			lothricKnight.setStamina(lothricKnight.getStamina() + 3); //Restore 1 stamina point per tick
		}
		
		if (lothricKnight.getStamina() > 100) lothricKnight.setStamina(100); //Going over, keep it at 100
		if (lothricKnight.getStamina() < 0) lothricKnight.setStamina(0); //Going under, keep it at 0
		
		if (lothricKnight.getStaminaUseTick() > 0) lothricKnight.setStaminaUseTick(lothricKnight.getStaminaUseTick() - 1);
	}
	
	
	private void calculatePoise() {
		
		lothricKnight.setPoise(lothricKnight.getPoise() + Math.sqrt(Math.sqrt(lothricKnight.getPoise()))/10); //Add exponentionally less poise for current amount
		if (lothricKnight.getPoise() > lothricKnight.getMaxPoise()) lothricKnight.setPoise(lothricKnight.getPoise()); //Going over, keep it at max
		if (lothricKnight.getPoise() < 0) { //Stagger logic
			
			lothricKnight.setPoise(lothricKnight.getMaxPoise());
		}
	}
	
	private void otherTasks() {
		
		//Targeter teleport
		Monster targeter = lothricKnight.getBrain();
		double yaw = targeter.getLocation().getYaw();
		Location newLocation = lothricKnight.getMain().getLocation().toVector().toLocation(targeter.getWorld()); //Teleport without changing direction?
		newLocation.setYaw((float) yaw);
		targeter.setFireTicks(-1);
		((CraftEntity) targeter).getHandle().setPosition(newLocation.getX(), newLocation.getY(), newLocation.getZ());
		
		//Player collision
		for (Entity entity : lothricKnight.getMain().getNearbyEntities(.125, .3, .125)) {
			if (entity.getType().equals(EntityType.PLAYER)) {
				double distance = entity.getLocation().distance(lothricKnight.getMain().getLocation());
				Location difference = entity.getLocation().subtract(lothricKnight.getMain().getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance,distance,distance));
				vector.multiply(.05);
				try { entity.setVelocity(entity.getVelocity().add(vector.setY(0))); } catch (Exception e) {}
			}
		}
		
		//Too far from home
		if (lothricKnight.getMain().getLocation().distance(lothricKnight.getPost().toLocation(lothricKnight.getMain().getWorld())) > 100) {
			lothricKnight.getMain().teleport(lothricKnight.getPost().toLocation(lothricKnight.getMain().getWorld()));
			lothricKnight.setHealth(lothricKnight.getHealth() + 50);
		}
		
		//Clear targeter's equipment if has any
		lothricKnight.getBrain().getEquipment().clear();
		
		if (bossBar == null) {
			bossBar = (Wither) lothricKnight.getMain().getWorld().spawnEntity(lothricKnight.getMain().getLocation(), EntityType.WITHER);
			bossBar.setSilent(true);
			bossBar.setInvulnerable(true);
			bossBar.setGravity(false);
			bossBar.setCustomName("Knight");
			bossBar.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(lothricKnight.getMaxHealth());
			bossBar.setHealth(bossBar.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
		}
		else {
			Location loc = lothricKnight.getMain().getLocation().clone();
			loc.setY(-5);
			bossBar.teleport(loc);
		}
	}
}
