package lothricKnights.WalkingForward;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class LeftCalf {
	public static void animate(ArmorStand part) {
		//Breathing animation
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, -15, 60, 10, 0, 0, 10, 0, 0, 10);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at -15,0,0 go to 60,0,0
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1000))) {
				}
				//Backward
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1600))) {
					CombinedAnimation.animate(part, combAngle, 6.25, 0.0, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1800))) {
				}
				//Forward
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2100))) {
					CombinedAnimation.animate(part, combAngle, -3.125, 0.0, 0.0);
				}
				//Forward
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2400))) {
					CombinedAnimation.animate(part, combAngle, -9.375, 0.0, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2600))) {
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(2600))) {
					double rd = 57.2957;
					LothricKnights.animationTimer.put(part, Instant.now().plusMillis(-1000));
					combAngle = new EulerAngle(
							-15 / rd,
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
