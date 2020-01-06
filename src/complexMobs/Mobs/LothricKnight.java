package complexMobs.mobs;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.object.SoulsWeapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	public LothricKnight() {
		super("LothricKnight", "Lothric Knight", 100, 30, new SoulsWeapon(2, 0, 0, new Vector(0,0,0)), new SoulsWeapon(2, 1, 0, new Vector(0,0,0)));
	}

	public void attackFrameSword() {

	}

	public void attackFrameShield() {

	}

	public void move() {

	}

	public void thinkThread() {

	}

	public void build(Location spawnLocation) {
		
	}
}
