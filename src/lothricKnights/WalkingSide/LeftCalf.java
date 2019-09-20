package lothricKnights.WalkingSide;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class LeftCalf {
	public static void animate(ArmorStand part, ArmorStand host) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Start at 10,0,-10 go to -10,15,10
			if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1000))) {
			}
			//Right
			else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1750))) {
				CombinedAnimation.animate(part, combAngle, -1.3333, 1, 1.3333);
			}
			//Pause
			else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1900))) {
			}
			//Left
			else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2750))) {
				CombinedAnimation.animate(part, combAngle, 1.3333, -1, -1.3333);
			}
			//Pause
			else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
			}
			//Reset
			else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
				double rd = 57.2957;
				LothricKnights.animationTimer.put(part, Instant.now().plusMillis(-1000));
				combAngle = new EulerAngle(
						10 / rd,
						0 / rd,
						-10 / rd
						);
				part.setHeadPose(combAngle);
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
