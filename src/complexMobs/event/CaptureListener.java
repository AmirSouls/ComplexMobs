package complexMobs.event;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import complexMobs.complexMob.ComplexMob;
import complexMobs.mob.LothricKnight;
import net.etheria.nations.modules.capture.event.capture.CaptureEndEvent;
import net.etheria.nations.modules.capture.event.capture.CaptureStartEvent;
import net.etheria.nations.modules.capture.nexus.Nexus;
import net.etheria.nations.util.LocationUtil;

public class CaptureListener implements Listener {

	public static Map<Nexus, ComplexMob> nexusKnight = new HashMap<>();
	
	@EventHandler
	public void onCaptureStart(CaptureStartEvent e) {
	
		int team = e.getNexus().getComponent().getNation().getId();
		Location spawnLoc = e.getNexus().getLocation();
		
		ComplexMob complexMob = new LothricKnight(spawnLoc.toVector(), team);
		
		spawnLoc = LocationUtil.randomNearbyLocation(4, spawnLoc);
		
		complexMob.build(spawnLoc);
		complexMob.run();
		
		nexusKnight.put(e.getNexus(), complexMob);
	}
	
	@EventHandler
	public void onCaptureEnd(CaptureEndEvent e) {
		
		nexusKnight.get(e.getNexus()).remove();
		nexusKnight.remove(e.getNexus());
	}
}
