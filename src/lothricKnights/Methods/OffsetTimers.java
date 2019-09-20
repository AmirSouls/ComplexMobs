package lothricKnights.Methods;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.entity.ArmorStand;

import lothricKnights.Main.LothricKnights;

public class OffsetTimers {
	public static void offset(ArmorStand main, int offsetMillis) {
		Collection<String> armorStandIds = new ArrayList<>();
		armorStandIds.add("pelvis");
		armorStandIds.add("chest");
		armorStandIds.add("head");
		armorStandIds.add("sword");
		armorStandIds.add("shield");
		armorStandIds.add("left foot");
		armorStandIds.add("right foot");
		armorStandIds.add("left calf");
		armorStandIds.add("right calf");
		armorStandIds.add("left thigh");
		armorStandIds.add("right thigh");
		armorStandIds.add("left elbow");
		armorStandIds.add("right elbow");
		armorStandIds.add("left arm");
		armorStandIds.add("right arm");
		armorStandIds.add("left hand");
		armorStandIds.add("right hand");
		
		Collection<ArmorStand> armorStands = LothricKnights.partList.get(main);
		
		for (ArmorStand part : armorStands) {
			LothricKnights.animationTimer.put(part, LothricKnights.animationTimer.get(part).plusMillis(offsetMillis));
		}
	}
}
