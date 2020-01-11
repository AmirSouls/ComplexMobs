package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.Build;
import complexMobs.mob.lothicKnight.Move;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.SoulsWeapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	/**
	* Creates a LothricKnight object
	*/
	public LothricKnight(Vector post) {
		super("LothricKnight", "Lothric Knight", 100, 30, post, new SoulsWeapon(2, 0, 0, new Vector()), new SoulsWeapon(2, 1, 0, new Vector()));
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
	
	@Override
	public void move(double vectorScalar, double angleTurn, double angleOffset) {
		new Move().run(this, vectorScalar, angleTurn, angleOffset);
	}
}
