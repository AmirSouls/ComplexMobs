package complexMobs.LothricKnight.Methods;

import complexMobs.Mobs.LothricKnight;

public class OffsetTimers {
	public static void offset(LothricKnight knight, int offsetMillis) {
		
		knight.animationTimer.put(knight.pelvis, knight.animationTimer.get(knight.pelvis ).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.chest, knight.animationTimer.get(knight.chest).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.head, knight.animationTimer.get(knight.head).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.cape, knight.animationTimer.get(knight.cape).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.sword , knight.animationTimer.get(knight.sword).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.shield, knight.animationTimer.get(knight.shield ).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.leftFoot , knight.animationTimer.get(knight.leftFoot).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.rightFoot, knight.animationTimer.get(knight.rightFoot).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.leftCalf, knight.animationTimer.get(knight.leftCalf).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.rightCalf, knight.animationTimer.get(knight.rightCalf).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.leftThigh, knight.animationTimer.get(knight.leftThigh).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.rightThigh, knight.animationTimer.get(knight.rightThigh).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.leftElbow, knight.animationTimer.get(knight.leftElbow).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.rightElbow, knight.animationTimer.get(knight.rightElbow).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.leftArm, knight.animationTimer.get(knight.leftArm).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.rightArm, knight.animationTimer.get(knight.rightArm).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.leftHand, knight.animationTimer.get(knight.leftHand).plusMillis(offsetMillis));
		knight.animationTimer.put(knight.rightHand, knight.animationTimer.get(knight.rightHand).plusMillis(offsetMillis));
	}
}
