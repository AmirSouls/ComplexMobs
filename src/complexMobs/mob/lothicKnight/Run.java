package complexMobs.mob.lothicKnight;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import complexMobs.mob.LothricKnight;
import complexMobs.mob.lothicKnight.action.Running;
import complexMobs.object.Part;

public class Run {

	private int tick = 0;
	
	public void run(LothricKnight lothricKnight, JavaPlugin plugin) {
		new BukkitRunnable() {
			public void run() {
				if (lothricKnight.getTarget() == null) {
					for (Entity entity : lothricKnight.getMain().getNearbyEntities(10, 10, 10)) {
						if (entity instanceof Player) {
							Player player = (Player) entity;
							lothricKnight.setTarget(player);
						}
					}
				}
				else {
					tick++;
					if (tick > 9) tick = 1;
					new Running().run(lothricKnight, tick);
					for (Part part : lothricKnight.getParts().values()) { part.position(); }
					for (Part part : lothricKnight.getParts().values()) { part.resetPosition(); }
				}
			}
		}.runTaskTimer(plugin, 0, 0);
	}
}
