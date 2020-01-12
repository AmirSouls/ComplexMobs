package complexMobs.template;

import org.bukkit.util.Vector;

import complexMobs.object.SoulsWeapon;

public abstract class SoulsKnight extends SoulsMob {

	private boolean shieldUp = false;
	
	private SoulsWeapon sword;
	
	private SoulsWeapon shield;
	
	/**
	* Creates a SoulsKnight object, This extends the SoulsMob type with more values of and methods to fit the category
	* @param ID The ID of this ComplexMob
	* @param name The display name of this ComplexMob
	* @param maxHealth The max health of this LivingComplexMob
	* @param maxPoise The max poise of this SoulsMob
	* @param sword The sword object of this SoulsKnight
	* @param shield The shield object of this SoulsKnight
	*/
	protected SoulsKnight(String ID, String name, double maxHealth, double maxPoise, int staminaUseTickMax, Vector post, SoulsWeapon sword, SoulsWeapon shield) {
		super(ID, name, maxHealth, maxPoise, staminaUseTickMax, post);
		this.sword = sword;
		this.shield = shield;
		this.shieldUp = false;
	}
	
	/**
	* Gets the sword object of this SoulsKnight
	* @return The sword object of this SoulsKnight
	*/
	public SoulsWeapon getSwordWeapon() {
		return this.sword;
	}
	
	/**
	* Sets the sword object of this SoulsKnight
	* @param sword The new sword object of this SoulsKnight
	*/
	public void setSwordWeapon(SoulsWeapon sword) {
		this.sword = sword;
	}
	
	/**
	* Gets the shield object of this SoulsKnight
	* @return The shield object of this SoulsKnight
	*/
	public SoulsWeapon getShield() {
		return this.shield;
	}
	
	/**
	* Sets the shield object of this SoulsKnight
	* @param shield The new sword object of this SoulsKnight
	*/
	public void setShield(SoulsWeapon shield) {
		this.shield = shield;
	}
	
	/**
	* Sets if this SoulsKnight is blocking with its shield or not
	* @param shieldUp The shieldUp boolean of this SoulsKnight
	*/
	public void setShieldIsUp(boolean shieldUp) {
		this.shieldUp = shieldUp;
	}
	
	/**
	* Gets if this SoulsKnight is blocking
	* @return True if this SoulsKnight is blocking
	*/
	public boolean isShieldUp() {
		return this.shieldUp;
	}
	
	/**
	* Deals damage in the area of this SoulsKnight's sword
	*/
	public abstract void attackFrameSword();
	
	/**
	* Deals damage in the area of this SoulsKnight's shield
	*/
	public abstract void attackFrameShield();
	
}
