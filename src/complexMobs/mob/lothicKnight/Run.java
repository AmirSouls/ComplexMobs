package complexMobs.mob.lothicKnight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import complexMobs.mob.LothricKnight;
import complexMobs.mob.lothicKnight.action.Idle;
import complexMobs.mob.lothicKnight.action.Running;
import complexMobs.mob.lothicKnight.action.Walking;
import complexMobs.object.Part;

public class Run {

	private int changeTick = 0;
	
	private int tick = 0;
	
	private boolean forceChange = false;
	
	private LothricKnight lothricKnight;
	
	private JavaPlugin plugin;
	
	public void run(LothricKnight lothricKnight, JavaPlugin plugin) {
		this.lothricKnight = lothricKnight;
		this.plugin = plugin;
		
		new BukkitRunnable() {
			public void run() {
				
				calculateStamina();
				
				calculatePoise();
				
				otherTasks();
				
				targeting();
				
				if (lothricKnight.getAction() == null) lothricKnight.setAction("idle"); //Default animation
				
				if (lothricKnight.getTarget() != null) { //Target attacking logic
					
					double distance = lothricKnight.getMain().getLocation().distance(lothricKnight.getTarget().getLocation());
					
					if (distance < 2 && !lothricKnight.getAction().contentEquals("idle")) forceChange = true;
					if (distance > 3 && lothricKnight.getAction().contentEquals("idle")) forceChange = true;
					
					if ((changeTick > 100 && Math.random() < .02) || forceChange) {
						forceChange = false;
						List<String> actions = new ArrayList<>(); //Actions to choose from
						if (!(distance < 2)) {
							actions.add("running");
							actions.add("walking");
						}
						else actions.add("idle");
						Collections.shuffle(actions);
						lothricKnight.setAction(actions.get(0));
						lothricKnight.setShieldIsUp(false);
						if (Math.random() < .4) lothricKnight.setShieldIsUp(true);
						changeTick = 0;
					}
					
				}
				else { //Non-attacking logic
					
					if (changeTick > 200 && Math.random() < .01 || forceChange) { //Mininum 200 ticks before changing, random tick after that
						forceChange = false;
						List<String> actions = new ArrayList<>(); actions.add("idle"); actions.add("walking"); //Actions to choose from
						Collections.shuffle(actions);
						lothricKnight.setAction(actions.get(0));
						changeTick = 0;
					}
					
				}
				
				//Execute action
				switch(lothricKnight.getAction()) {
				
				case "idle":
					tick = new Idle().run(lothricKnight, tick);
					break;
				case "walking":
					tick = new Walking().run(lothricKnight, tick);
					break;
				case "running":
					tick = new Running().run(lothricKnight, tick);
					break;
					
				}
				changeTick++;
				
				//Position parts
				for (Part part : lothricKnight.getParts().values()) { part.position(); }
				for (Part part : lothricKnight.getParts().values()) { part.resetPosition(); }
				
			}
		}.runTaskTimer(plugin, 0, 0);
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
		
		List<Entity> nearbyEntities = lothricKnight.getMain().getNearbyEntities(10, 10, 10);
		if (nearbyEntities.isEmpty()) return;
		Player nearestPlayer = null;
		double nearestPlayerDistance = 1000;
		
		for (Entity entity : lothricKnight.getMain().getNearbyEntities(10, 10, 10)) {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				if (nearestPlayer == null) nearestPlayer = player;
				double playerDistance = player.getLocation().distance(lothricKnight.getMain().getLocation());
				if (playerDistance < nearestPlayerDistance && Math.random() > .3) {
					 nearestPlayer = player;
					 nearestPlayerDistance = playerDistance;
				}
			}
		}
		if (nearestPlayer != null) lothricKnight.setTarget(nearestPlayer);
		
	}
	
	private void calculateStamina() {
		
		lothricKnight.setStamina(lothricKnight.getStamina() + 1); //Restore 1 stamina point per tick
		if (lothricKnight.getStamina() > 100) lothricKnight.setStamina(100); //Going over, keep it at 100
		if (lothricKnight.getStamina() < 0) lothricKnight.setStamina(0); //Going under, keep it at 0
		
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
		Monster targeter = lothricKnight.getTargeter();
		double yaw = targeter.getLocation().getYaw();
		Location newLocation = lothricKnight.getMain().getLocation().toVector().toLocation(targeter.getWorld()); //Teleport without changing direction?
		newLocation.setYaw((float) yaw);
		targeter.setFireTicks(-1);
		//lothricKnight.getTargeter().teleport(newLocation);
		//((CraftEntity) targeter).getHandle().setPosition(newLocation.getX(), newLocation.getY(), newLocation.getZ());
		
		//Set targeter's target
		targeter.setTarget(lothricKnight.getTarget());
	}
}
