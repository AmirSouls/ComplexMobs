package complexMobs.Mobs;

import java.time.Instant;
import java.util.Collection;

import org.bukkit.entity.ArmorStand;

import complexMobs.Main.ComplexMob;

public class LothricKnight extends ComplexMob {

	public LothricKnight(ArmorStand inpMain, Collection<ArmorStand> inpParts, Collection<String> inpSounds) {
		super(inpMain, 100, 14, inpParts, inpSounds);
	}
	
	//Blocking
	public Instant blockTimer;
	public boolean shieldUp;
}
