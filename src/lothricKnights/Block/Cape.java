package lothricKnights.Block;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class Cape {
	public static void animate(ArmorStand part, ArmorStand host) {
		//Breathing animation
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Host angle
			EulerAngle hostAngle = host.getHeadPose();
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			//Relative angle
			EulerAngle relAngle = new EulerAngle(hostAngle.getX() - combAngle.getX(), hostAngle.getY() - combAngle.getY(), hostAngle.getZ() - combAngle.getZ());
			
			if (false) {
			}
			else {
				//Start at 0,0,0 go 95,-75,0
				//Flinch back
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(600))) {
					if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(310))) {
						CombinedAnimation.animate(part, combAngle, ((int) (Math.random() * 2) + 3),  ((int) (Math.random() * 10 - 5)), 0);
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
