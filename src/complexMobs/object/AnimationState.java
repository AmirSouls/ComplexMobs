package complexMobs.object;

import org.bukkit.util.Vector;

public class AnimationState {

	private Vector state;
	
	private int startTick;
	
	public Vector getState() {
		return this.state;
	}
	
	public int getStart() {
		return this.startTick;
	}
	
	public AnimationState(Vector state, int startTick) {
		this.state = state;
		this.startTick = startTick;
	}
	
}
