package complexMobs.event;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import complexMobs.complexMob.ComplexMob;
import complexMobs.mob.LothricKnight;
import net.etheria.nations.modules.capture.event.capture.CaptureEndEvent;
import net.etheria.nations.modules.capture.event.capture.CaptureStartEvent;
import net.etheria.nations.modules.capture.nexus.Nexus;
import net.etheria.nations.modules.capture.nexus.NexusType;
import net.etheria.nations.util.LocationUtil;

public class CaptureListener implements Listener {

	public static Map<Nexus, ComplexMob> nexusKnight = new HashMap<>();
	
	@EventHandler
	public void onCaptureStart(CaptureStartEvent e) {
	
		if (!e.isCancelled()) {
			int team = e.getNexus().getComponent().getNation().getId();
			if (team == -1) return;
			if (e.getNexus().getType().equals(NexusType.OUTPOST)) return;
			Location spawnLoc = e.getNexus().getLocation();
			
			ComplexMob complexMob = new LothricKnight(spawnLoc.toVector(), team);
			
			spawnLoc = LocationUtil.randomNearbyLocation(4, spawnLoc);
			
			complexMob.build(spawnLoc);
			complexMob.run();
			e.getAttacker().getBukkitPlayer().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, spawnLoc.clone().add(0,2,0), 0, .3, 1, .3, 30, null, true);
			e.getAttacker().getBukkitPlayer().getWorld().playSound(spawnLoc, Sound.ENTITY_BLAZE_DEATH, 3, 0);
			
			nexusKnight.put(e.getNexus(), complexMob);
		}
	}
	
	@EventHandler
	public void onCaptureEnd(CaptureEndEvent e) {
		
		if (!e.isCancelled()) {
			
			if (!nexusKnight.get(e.getNexus()).isRemoved() && !((LothricKnight) nexusKnight.get(e.getNexus())).isDead()) {
				e.getAttacker().getBukkitPlayer().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, nexusKnight.get(e.getNexus()).getMain().getLocation().add(0,2,0), 0, .3, 1, .3, 30, null, true);
				e.getAttacker().getBukkitPlayer().getWorld().playSound(nexusKnight.get(e.getNexus()).getMain().getLocation().add(0,2,0), Sound.ENTITY_PLAYER_LEVELUP, 3, .5f);
			}
			
			nexusKnight.get(e.getNexus()).remove();
			nexusKnight.remove(e.getNexus());
		}
	}
}
