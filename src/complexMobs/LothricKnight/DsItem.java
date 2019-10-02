package complexMobs.LothricKnight;

import java.time.Instant;

import org.bukkit.Particle;
import org.bukkit.entity.Item;

import complexMobs.Main.ComplexMobs;

public class DsItem {
	public static void particles(Item item) {
		if (Instant.now().isAfter(ComplexMobs.dsItem.get(item).plusMillis(4000))) {
			item.getWorld().spawnParticle(Particle.END_ROD, item.getLocation().add(0,.5,0), 10, .2, .2, .2, 0);
		}
	}
}
