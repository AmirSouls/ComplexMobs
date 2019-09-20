package lothricKnights.WalkingSide;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.CombinedAnimation;

public class RightCalf {
	public static void animate(ArmorStand part, ArmorStand host) {
		if (LothricKnights.animationTimer.containsKey(part)) {
			
			//Combined angle
			EulerAngle combAngle = part.getHeadPose();
			
			//Out of bounds combined
			OutOfBounds.combined(part, combAngle, 5, 50, 30, 0, 80, 30, 0, 10, 30);
			
			boolean debug = false;
			if (debug) {
			}
			else {
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1000))) {
				}
				//Right
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1750))) {
					CombinedAnimation.animate(part, combAngle, 3, 5.3333, -.6666);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1900))) {
				}
				//Left
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2750))) {
					CombinedAnimation.animate(part, combAngle, -3, -5.3333, .6666);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(2900))) {
					double rd = 57.2957;
					LothricKnights.animationTimer.put(part, Instant.now().plusMillis(-1000));
					combAngle = new EulerAngle(
							5 / rd
							,
							0
							,
							10 / rd
							);
					part.setHeadPose(combAngle);
					
					//Footstep sound
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.walk master @a " +
							part.getLocation().getX() + 
							" " +
							part.getLocation().getY() +
							" " + part.getLocation().getZ() + " 0.5"
							);
				}
				
				//Mid-animation footstep
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(part).plusMillis(1750)) && Instant.now().isBefore(LothricKnights.animationTimer.get(part).plusMillis(1805))) {
					//Footstep sound
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.walk master @a " +
							part.getLocation().getX() + 
							" " +
							part.getLocation().getY() +
							" " + part.getLocation().getZ() + " 0.5"
							);
				}
			}
		}
		else {
			LothricKnights.animationTimer.put(part, Instant.now());
		}
	}
}
