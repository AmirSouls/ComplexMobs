package complexMobs.template;

import java.util.List;

import org.bukkit.entity.ArmorStand;

import complexMobs.complexMob.ComplexMob;
import complexMobs.object.Animation;

public abstract class LivingComplexMob implements ComplexMob {
	
	private Animation currentAnimation;
	
	private String ID;
	
	private String name;
	
	private ArmorStand main;
	
	private double health;
	
	private double maxHealth;
	
	private List<ArmorStand> parts;
	
	private boolean isRemoved = false;
	
	private boolean isDead = false;
	
	protected LivingComplexMob(String ID, String name, double maxHealth) {
		this.ID = ID;
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	public Animation getCurrentAnimation() {
		return this.currentAnimation;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public ArmorStand getMain() {
		return this.main;
	}
	
	public void setMain(ArmorStand main) {
		this.main = main;
	}
	
	public List<ArmorStand> getParts() {
		return this.parts;
	}
	
	public void setParts(List<ArmorStand> parts) {
		this.parts = parts;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	public boolean isRemoved() {
		return this.isRemoved;
	}
	
	public void remove() {
		for (ArmorStand stand : this.parts) {
			stand.remove();
		}
		isRemoved = true;
		isDead = true;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	public void kill() {
		this.health = 0;
	}
	
	public void revive() {
		this.setHealth(this.maxHealth);
		this.isDead = false;
	}
	
	public abstract void move();
	
	public void animate(Animation animation) {
		animation.thread();
		this.currentAnimation = animation;
	}
	
	public void cancelAnimation() {
		this.currentAnimation.terminate();
		this.currentAnimation = null;
	}
}


