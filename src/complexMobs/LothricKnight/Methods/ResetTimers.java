package complexMobs.LothricKnight.Methods;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.entity.ArmorStand;

import complexMobs.Mobs.LothricKnight;

public class ResetTimers {
	public static void reset(LothricKnight knight) {
		Collection<String> armorStandIds = new ArrayList<>();
		armorStandIds.add("pelvis");
		armorStandIds.add("chest");
		armorStandIds.add("head");
		armorStandIds.add("sword");
		armorStandIds.add("shield");
		armorStandIds.add("leftFoot");
		armorStandIds.add("rightFoot");
		armorStandIds.add("leftCalf");
		armorStandIds.add("rightCalf");
		armorStandIds.add("leftThigh");
		armorStandIds.add("rightThigh");
		armorStandIds.add("leftElbow");
		armorStandIds.add("rightElbow");
		armorStandIds.add("leftArm");
		armorStandIds.add("rightArm");
		armorStandIds.add("leftHand");
		armorStandIds.add("rightHand");
		
		Collection<ArmorStand> armorStands = knight.parts;
		
		for (ArmorStand part : armorStands) {
			knight.animationTimer.put(part, Instant.now());
		}
	}
	
	public static void resetOffset(LothricKnight knight, int plusMillis) {
		Collection<String> armorStandIds = new ArrayList<>();
		armorStandIds.add("pelvis");
		armorStandIds.add("chest");
		armorStandIds.add("head");
		armorStandIds.add("sword");
		armorStandIds.add("shield");
		armorStandIds.add("leftFoot");
		armorStandIds.add("rightFoot");
		armorStandIds.add("leftCalf");
		armorStandIds.add("rightCalf");
		armorStandIds.add("leftThigh");
		armorStandIds.add("rightThigh");
		armorStandIds.add("leftElbow");
		armorStandIds.add("rightElbow");
		armorStandIds.add("leftArm");
		armorStandIds.add("rightArm");
		armorStandIds.add("leftHand");
		armorStandIds.add("rightHand");
		
		Collection<ArmorStand> armorStands = knight.parts;
		
		for (ArmorStand part : armorStands) {
			knight.animationTimer.put(part, Instant.now().plusMillis(plusMillis));
		}
	}
}
