package complexMobs.object;

import java.util.List;

import org.bukkit.util.Vector;

public class Weapon {
	
	private List<Vector> hitPoints;
	
	public void setHitPoints(List<Vector> hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	public List<Vector> getHitPoints() {
		return hitPoints;
	}

}
