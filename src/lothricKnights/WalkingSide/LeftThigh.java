package lothricKnights.WalkingSide;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class LeftThigh {
	public static void animate(ArmorStand part, ArmorStand host) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, -50, -30, 30, -20, 30, 30, 0, 0, 30);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				//Start at -30,-20,0 go to -50,30,0
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1000))) {
				}
				//Right
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1750))) {
					CombinedAnimation.animate(part, combAngle, -1.3333, 3.3333, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1900))) {
				}
				//Left
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2750))) {
					CombinedAnimation.animate(part, combAngle, 1.3333, -3.3333, 0.0);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
					double rd = 57.2957;
					LothricKnights.animationTimer.put(part, Instant.now().plusMillis(-1000));
					combAngle = new EulerAngle(
							-30 / rd,
							-20 / rd,
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
