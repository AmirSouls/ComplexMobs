package complexMobs.Mobs;

import java.time.Instant;
import java.util.Collection;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.Main.ComplexMob;

public class LothricKnight extends ComplexMob {

	public LothricKnight(ArmorStand inpMain, Collection<ArmorStand> inpParts) {
		super(inpMain, 100, 14, inpParts);
	}
	
	//Blocking
	public Instant blockTimer;
	public boolean shieldUp;
}
