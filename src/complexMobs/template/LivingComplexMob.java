package complexMobs.template;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.entity.ArmorStand;

import complexMobs.complexMob.ComplexMob;
import complexMobs.object.Part;

public abstract class LivingComplexMob implements ComplexMob {
	
	private String action;
	
	private String ID;
	
	private String name;
	
	private ArmorStand main;
	
	private double health;
	
	private double maxHealth;
	
	private Map<String, Part> parts = new LinkedHashMap<>();
	
	private boolean isRemoved = false;
	
	private boolean isDead = false;
	
	/**
	* Creates a LivingComplexMob object, This implements the ComplexMob interface
	* @param ID The ID of this ComplexMob
	* @param name The display name of this ComplexMob
	* @param maxHealth The max health of this LivingComplexMob
	*/
	protected LivingComplexMob(String ID, String name, double maxHealth) {
		this.ID = ID;
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
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
	
	public Map<String, Part> getParts() {
		return this.parts;
	}
	
	public void setParts(Map<String, Part> parts) {
		this.parts = parts;
	}
	
	public boolean isRemoved() {
		return this.isRemoved;
	}
	
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
	public abstract void remove();
	
	/**
	* Gets the current action of this LivingComplexMob
	* @return The current action of this LivingComplexMob
	*/
	public String getAction() {
		return this.action;
	}
	
	/**
	* Sets the action of this LivingComplexMob
	* @param action The new action of this LivingComplexMob
	*/
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	* Gets the current health of this LivingComplexMob
	* @return The current health of this LivingComplexMob
	*/
	public double getHealth() {
		return this.health;
	}
	
	/**
	* Sets the health of this LivingComplexMob
	* @param health The new health of this LivingComplexMob
	*/
	public void setHealth(double health) {
		this.health = health;
	}
	
	/**
	* Gets the max health of this LivingComplexMob
	* @return The max health of this LivingComplexMob
	*/
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	/**
	* Checks if this LivingComplexMob is dead
	* @return True if this LivingComplexMob is dead
	*/
	public boolean isDead() {
		return this.isDead;
	}
	
	/**
	* Checks if this LivingComplexMob is dead
	* @param Set if this LivingComplexMob is dead
	*/
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	* Kills this LivingComplexMob naturally by setting it's health to zero
	*/
	public void kill() {
		this.health = 0;
	}
	
	/**
	* Revives this LivingComplexMob from the dead state, this does not work if the LivingComplexMob has been removed
	*/
	public void revive() {
		this.setHealth(this.maxHealth);
		this.isDead = false;
	}
	
	/**
	* Moves this LivingComplexMob
	* @param vectorScalar Meters per tick to move
	* @param angleTurn Amount in degrees to turn towards its target
	* @param angleOffset Angle offset in degrees to move, for example 90 moves right
	*/
	public abstract void move(double vectorScalar, double angleTurn, double angleOffset);
	
}


