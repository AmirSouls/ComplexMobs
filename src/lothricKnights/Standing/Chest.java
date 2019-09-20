package lothricKnights.Standing;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class Chest {
	public static void animate(ArmorStand part) {
		//Breathing animation
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, -5, 0, 9, -5, 0, 9, -5, 0, 9);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//In
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2500))) {
					CombinedAnimation.animate(part, combAngle, -.0999, 0.0, 0.0);
				}
				//Out
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(5000))) {
					CombinedAnimation.animate(part, combAngle, .0999, 0.0, 0.0);
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(5000))) {
					LothricKnights.animationTimer.put(part, Instant.now());
					combAngle = new EulerAngle(
							0,
							0,
							0
							);
					part.setHeadPose(combAngle);
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
