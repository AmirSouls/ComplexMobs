package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.Build;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.SoulsWeapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	public LothricKnight() {
		super("LothricKnight", "Lothric Knight", 100, 30, new SoulsWeapon(2, 0, 0, new Vector()), new SoulsWeapon(2, 1, 0, new Vector()));
	}
	
	public void attackFrameSword() {
		
	}

	public void attackFrameShield() {
		
	}

	public void run() {
		Run run= new Run();
		run.run(this, ComplexMobs.getProvidingPlugin(ComplexMobs.class));
	}

	public void build(Location spawnLocation) {
		Build build = new Build();
		build.run(this, spawnLocation);
	}
	
	@Override
	public void move(double vectorScalar, double angleTurn) {
		double distance = getTarget().getLocation().distance(getMain().getLocation());
		Vector difference = getTarget().getLocation().toVector().subtract(getMain().getLocation().toVector());
		Vector direction = difference.divide(new Vector(distance, distance, distance));
		
		double targetYaw = Math.atan2(direction.getX(), direction.getZ()) * 57.29;
		double mobYaw = getMain().getLocation().getYaw();
		if (mobYaw > 180) mobYaw -= 360;
		mobYaw *= -1;
		
		double deltaYaw = targetYaw - mobYaw;
		double newYaw = mobYaw;
		
		if (deltaYaw > 0) {
			newYaw += angleTurn;
			if (newYaw > targetYaw) newYaw = targetYaw;
		}
		else if (deltaYaw < 0) {
			newYaw -= angleTurn;
			if (newYaw < targetYaw) newYaw = targetYaw;
		}
		
		Vector moveVector = getMain().getLocation().getDirection().multiply(vectorScalar);
		Location newLocation = getMain().getLocation();
			
		if (newLocation.clone().add(0,-1,0).getBlock().isPassable()) {
			newLocation = newLocation.clone().add(0,-1,0).getBlock().getLocation();
		}
		else {
			newLocation.add(moveVector);
			if (
					!newLocation.clone().getBlock().isPassable() && 
					newLocation.clone().add(0,1,0).getBlock().isPassable() && 
					newLocation.clone().add(0,2,0).getBlock().isPassable() &&
					newLocation.clone().add(0,3,0).getBlock().isPassable() &&
					newLocation.clone().add(0,4,0).getBlock().isPassable()) {
				newLocation = newLocation.clone().add(0,1,0);
			}
			else if (
					!newLocation.getBlock().isPassable() || 
					!newLocation.clone().add(0,1,0).getBlock().isPassable() || 
					!newLocation.clone().add(0,2,0).getBlock().isPassable() ||
					!newLocation.clone().add(0,3,0).getBlock().isPassable()) {
				newLocation.subtract(moveVector);
			}
			
		}

		newLocation.setDirection(new Vector(0,0,1).rotateAroundY(newYaw / 57.29));
		getMain().teleport(newLocation);
	}
}
