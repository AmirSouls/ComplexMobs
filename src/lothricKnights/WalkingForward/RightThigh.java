package lothricKnights.WalkingForward;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class RightThigh {
	public static void animate(ArmorStand part) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, -15, 30, 10, 0, 0, 10, 0, 0, 10);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at 30,0,0 go to -15,0,0
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1000))) {
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1200))) {
				}
				//Backward
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1800))) {
					CombinedAnimation.animate(part, combAngle, -3.75, 0.0, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2000))) {
				}
				//Forward
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2600))) {
					CombinedAnimation.animate(part, combAngle, 3.75, 0.0, 0.0);
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(2600))) {
					double rd = 57.2957;
					LothricKnights.animationTimer.put(part, Instant.now().plusMillis(-1000));
					combAngle = new EulerAngle(
							30 / rd,
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
