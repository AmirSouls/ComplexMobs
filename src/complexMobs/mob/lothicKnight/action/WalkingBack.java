package complexMobs.mob.lothicKnight.action;

public class WalkingBack extends Walking {
	
	public WalkingBack() {
		setReturnTick(31);
	}
	
	@Override
	protected void move() {
		getMob().move(-.1, 20, 0);
	}
}
