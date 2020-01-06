package complexMobs.template;

public abstract class SoulsMob extends LivingComplexMob {

	private double stamina = 100;
	
	private double maxStamina = 100;
	
	private double poise;
	
	private double maxPoise;
	
	protected SoulsMob(String ID, String name, double maxHealth, double maxPoise) {
		super(ID, name, maxHealth);
		this.maxPoise = maxPoise;
		this.poise = maxPoise;
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
	
	public abstract void move();
}
