package lothricKnights.Stagger;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class LeftCalf {
	public static void animate(ArmorStand part) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, 0, 5, 30, 0, 5, 30, -10, 0, 30);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 0,0,0 go to 5,5,-10
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(310))) {
					CombinedAnimation.animate(part, combAngle, 1, 1, -2);
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(610))) {
					CombinedAnimation.animate(part, combAngle, -.25, -.25, 2);
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
