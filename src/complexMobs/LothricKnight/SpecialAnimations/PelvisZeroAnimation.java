package complexMobs.LothricKnight.SpecialAnimations;

import org.bukkit.entity.ArmorStand;

import complexMobs.LothricKnight.Methods.GoBtwn;

public class PelvisZeroAnimation {
	public static void animate(ArmorStand part, float spd) {
		//Keeps pelvis at 0,0,0
		
		GoBtwn.animate(part, part.getHeadPose(), 0, 0, spd);
	}
}