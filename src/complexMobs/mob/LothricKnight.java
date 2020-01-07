package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import complexMobs.mob.lothicKnight.Build;
import complexMobs.object.ChildPart;
import complexMobs.object.Part;
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

	public void move() {

	}

	public void run() {
		
	}

	public void build(Location spawnLocation) {
		Build build = new Build();
		build.run(this, spawnLocation);
	}
}
