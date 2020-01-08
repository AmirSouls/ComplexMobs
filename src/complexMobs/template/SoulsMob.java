package complexMobs.template;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public abstract class SoulsMob extends LivingComplexMob {

	private LivingEntity target;
	
	private double stamina = 100;
	
	private double maxStamina = 100;
	
	private double poise;
	
	private double maxPoise;
	
	protected SoulsMob(String ID, String name, double maxHealth, double maxPoise) {
		super(ID, name, maxHealth);
		this.maxPoise = maxPoise;
		this.poise = maxPoise;
	}
	
	public LivingEntity getTarget() {
		return this.target;
	}
	
	public void setTarget(LivingEntity target) {
		this.target = target;
	}
	
	public double getStamina() {
		return this.stamina;
	}
	
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	
	public double getMaxStamina() {
		return this.maxStamina;
	}
	
	public double getPoise() {
		return this.poise;
	}
	
	public void setPoise(double poise) {
		this.poise = poise;
	}
	
	public double getMaxPoise() {
		return this.maxPoise;
	}
	
	public void move(double vectorScalar, double angleTurn) {
		
		
		double distance = getTarget().getLocation().distance(getMain().getLocation());
		Vector difference = getTarget().getLocation().toVector().subtract(getMain().getLocation().toVector());
		Vector direction = difference.divide(new Vector(distance, distance, distance));
		
		
		double targetYaw = Math.atan2(direction.getX(), direction.getZ()) * 57.29;
		double mobYaw = getMain().getLocation().getYaw();
		double newAngle;
		if (Math.pow(targetYaw + (mobYaw-angleTurn),2) > Math.pow(targetYaw + (mobYaw+angleTurn),2)) {
			newAngle = mobYaw + angleTurn;
			if (newAngle < targetYaw) newAngle = targetYaw;
		}
		else {
			newAngle = mobYaw - angleTurn;
			if (newAngle > targetYaw) newAngle = targetYaw;
		}
		
		Vector moveVector = new Vector(0,0,1);
		moveVector.multiply(vectorScalar);
		moveVector.rotateAroundY(newAngle / 57.29);
		Location newLocation = getMain().getLocation().add(moveVector);
		newLocation.setDirection(direction);
		getMain().teleport(newLocation);
	}
}
