package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.Build;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.SoulsWeapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	public LothricKnight() {
		super("LothricKnight", "Lothric Knight", 100, 30, new SoulsWeapon(2, 0, 0, new Vector()), new SoulsWeapon(2, 1, 0, new Vector()));
	}
	
	public void attackFrameSword() {
		
	}

	public void attackFrameShield() {
		
	}

	public void run() {
		Run run= new Run();
		run.run(this, ComplexMobs.getProvidingPlugin(ComplexMobs.class));
	}

	public void build(Location spawnLocation) {
		Build build = new Build();
		build.run(this, spawnLocation);
	}
}
