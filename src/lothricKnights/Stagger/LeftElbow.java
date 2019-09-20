package lothricKnights.Stagger;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class LeftElbow {
	public static void animate(ArmorStand part, ArmorStand host) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, 0, 60, 30, 0, 0, 30, -40, 0, 30);
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 0,0,0 go to 60,0,-40
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(310))) {
					CombinedAnimation.animate(part, combAngle, 12, 0, -8);
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(610))) {
					CombinedAnimation.animate(part, combAngle, -3, 0, 2);
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
