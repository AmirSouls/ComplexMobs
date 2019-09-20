package lothricKnights.Stagger;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class Cape {
	public static void animate(ArmorStand part) {
		//Breathing animation
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 0,0,0 go 95,-75,0
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(600))) {
					if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(310))) {
						CombinedAnimation.animate(part, combAngle, ((int) (Math.random() * 7) + 8),  ((int) (Math.random() * 30 - 15)), 0);
					}
					else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(610))) {
						OutOfBounds.combined(part, combAngle, 10, 0, 3);
					}
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
