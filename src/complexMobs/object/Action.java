package complexMobs.object;

import complexMobs.template.LivingComplexMob;

public abstract class Action {
	
	private int tick;
	
	private LivingComplexMob livingComplexMob;
	
	public int getTick() {
		return this.tick;
	}
	
	public LivingComplexMob getMob() {
		return this.livingComplexMob;
	}
	
	public void run(LivingComplexMob livingComplexMob, int tick) {
		this.tick = tick;
		this.livingComplexMob = livingComplexMob;
		actions();
	}
	
	protected abstract void actions();
    
}
