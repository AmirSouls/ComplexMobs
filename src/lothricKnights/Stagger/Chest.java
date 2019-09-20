package lothricKnights.Stagger;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class Chest {
	public static void animate(ArmorStand part) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, 0, 20, 30, 0, 20, 30, 0, 0, 30);
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 0,0,0 go to 20,20,0
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(310))) {
					CombinedAnimation.animate(part, combAngle, 4, 2, 0);
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(610))) {
					CombinedAnimation.animate(part, combAngle, -1, -.5, 0);
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
