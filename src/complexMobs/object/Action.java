package complexMobs.object;

import complexMobs.template.LivingComplexMob;

public abstract class Action {
	
	private int tick;
	
	private int returnTick = 10;
	
	private LivingComplexMob livingComplexMob;
	
	/**
	* Gets the tick of this Action
	* @return The current tick of this Action
	*/
	public int getTick() {
		return this.tick;
	}
	
	/**
	* Gets the return tick of this Action, which indicates when the action should terminate
	* @return The return tick of this Action
	*/
	protected int getReturnTick() {
		return this.returnTick;
	}
	
	/**
	* Sets the return tick of this Action, which indicates when the action should terminate
	* @param returnTick The new return tick of this Action
	*/
	protected void setReturnTick(int returnTick) {
		this.returnTick = returnTick;
	}
	
	/**
	* Gets LivingComplexMob of this Action
	* @return The LivingComplexMob of this Action
	*/
	public LivingComplexMob getMob() {
		return this.livingComplexMob;
	}
	
	/**
	* Executes the action and increments or resets the tick
	* @return The new tick of the action
	*/
	public int run(LivingComplexMob livingComplexMob, int tick) {
		this.tick = tick;
		this.livingComplexMob = livingComplexMob;
		return actions();
	}
	
	protected abstract int actions();
    
}
