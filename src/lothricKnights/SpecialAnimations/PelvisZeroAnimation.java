package lothricKnights.SpecialAnimations;

import org.bukkit.entity.ArmorStand;

import lothricKnights.Methods.GoBtwn;

public class PelvisZeroAnimation {
	public static void animate(ArmorStand part, float spd) {
		//Keeps pelvis at 0,0,0
		
		GoBtwn.combined(part, part.getHeadPose(), 0, 0, spd);
	}
}