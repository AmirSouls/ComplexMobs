package complexMobs.template;

import complexMobs.object.SoulsWeapon;

public abstract class SoulsKnight extends SoulsMob {

	private boolean shieldUp = false;
	
	private SoulsWeapon sword;
	
	private SoulsWeapon shield;
	
	protected SoulsKnight(String ID, String name, double maxHealth, double maxPoise, SoulsWeapon sword, SoulsWeapon shield) {
		super(ID, name, maxHealth, maxPoise);
		this.sword = sword;
		this.shield = shield;
	}
	
	public void setSword(SoulsWeapon sword) {
		this.sword = sword;
	}
	
	public SoulsWeapon getSword() {
		return this.sword;
	}
	
	public void setShield(SoulsWeapon shield) {
		this.shield = shield;
	}
	
	public SoulsWeapon getShield() {
		return this.shield;
	}
	
	public void setShieldIsUp(boolean shieldUp) {
		this.shieldUp = shieldUp;
	}
	
	public boolean isShieldUp() {
		return this.shieldUp;
	}
	
	public abstract void attackFrameSword();
	
	public abstract void attackFrameShield();
	
}
