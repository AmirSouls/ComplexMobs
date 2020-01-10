package complexMobs.template;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

public abstract class SoulsMob extends LivingComplexMob {

	private LivingEntity target;
	
	private Monster targeter;
	
	private double stamina = 100;
	
	private double poise;
	
	private double maxPoise;
	
	/**
	* Creates a SoulsMob object, This extends the LivingComplexMob type with more values of and methods to fit the category
	* @param ID The ID of this ComplexMob
	* @param name The display name of this ComplexMob
	* @param maxHealth The max health of this LivingComplexMob
	* @param maxPoise The max poise of this SoulsMob
	*/
	protected SoulsMob(String ID, String name, double maxHealth, double maxPoise) {
		super(ID, name, maxHealth);
		this.maxPoise = maxPoise;
		this.poise = maxPoise;
	}
	
	/**
	* Gets the targeter LivingEntity of this SoulsMob
	* @return The target LivingEntity of this SoulsMob
	*/
	public Monster getTargeter() {
		return this.targeter;
	}
	
	/**
	* Sets the targeter Monster of this SoulsMob
	* @param The new target LivingEntity of this SoulsMob
	*/
	public void setTargeter(Monster targeter) {
		this.targeter = targeter;
	}
	
	/**
	* Gets the target LivingEntity of this SoulsMob
	* @return The target LivingEntity of this SoulsMob
	*/
	public LivingEntity getTarget() {
		return this.target;
	}
	
	/**
	* Sets the target LivingEntity of this SoulsMob
	* @param The new target LivingEntity of this SoulsMob
	*/
	public void setTarget(LivingEntity target) {
		this.target = target;
		this.targeter.setTarget(target);
	}
	
	/**
	* Gets the current stamina of this SoulsMob
	* @return The current stamina of this SoulsMob
	*/
	public double getStamina() {
		return this.stamina;
	}
	
	/**
	Sets the current stamina of this SoulsMob
	* @param stamina The new stamina of this SoulsMob
	*/
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	
	/**
	* Gets the current poise of this SoulsMob 
	@return The current poise of this SoulsMob
	*/
	public double getPoise() {
		return this.poise;
	}
	
	/**
	* Sets the current poise of this SoulsMob
	* @param The new poise of this SoulsMob
	*/
	public void setPoise(double poise) {
		this.poise = poise;
	}
	
	/**
	* Gets the max poise of this SoulsMob
	* @return The max poise of this SoulsMob
	*/
	public double getMaxPoise() {
		return this.maxPoise;
	}
}
