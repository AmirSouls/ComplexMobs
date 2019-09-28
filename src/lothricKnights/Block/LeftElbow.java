package lothricKnights.Block;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.GoBtwn;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class LeftElbow {
	public static void animate(ArmorStand part, ArmorStand host) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			GoBtwn.combined(part, combAngle, 15, 15, 0, 0, -10, -10, 30.0);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 15,0,-10 go to 15,0,-10
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(210))) {
					CombinedAnimation.animate(part, combAngle, 10, 0, 0);
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(410))) {
					CombinedAnimation.animate(part, combAngle, -12, 0, 0);
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
