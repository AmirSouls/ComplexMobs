package lothricKnights.WalkingSide;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class RightThigh {
	public static void animate(ArmorStand part, ArmorStand host) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, -30, 20, 30, 30, 55, 30, 0, 0, 30);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1000))) {
				}
				//Right
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1750))) {
					CombinedAnimation.animate(part, combAngle, 3.3332, 1.6665, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1900))) {
				}
				//Left
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2750))) {
					CombinedAnimation.animate(part, combAngle, -3.3332, -1.6665, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
					double rd = 57.2957;
					LothricKnights.animationTimer.put(part, Instant.now().plusMillis(-1000));
					combAngle = new EulerAngle(
							-20 / rd,
							30 / rd,
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
