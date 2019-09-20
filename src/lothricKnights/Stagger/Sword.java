package lothricKnights.Stagger;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class Sword {
	public static void animate(ArmorStand part) {
		//Breathing animation
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, 0, 0, 30, 0, 80, 30, -25, 0, 30);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 0,0,0 go 0,80,-25
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(600))) {
					if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(310))) {
						CombinedAnimation.animate(part, combAngle, 0, 16, -5);
					}
					else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(610))) {
						CombinedAnimation.animate(part, combAngle, 0, -4, 1.25);
					}
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
