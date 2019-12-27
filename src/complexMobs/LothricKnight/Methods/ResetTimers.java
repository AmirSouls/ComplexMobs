package complexMobs.LothricKnight.Methods;

import java.time.Instant;

import complexMobs.Mobs.LothricKnight;

public class ResetTimers {
	public static void reset(LothricKnight knight) {
		knight.animationTimer.put(knight.pelvis, Instant.now()); 
		knight.animationTimer.put(knight.chest, Instant.now());
		knight.animationTimer.put(knight.head, Instant.now()); 
		knight.animationTimer.put(knight.cape, Instant.now()); 
		knight.animationTimer.put(knight.sword , Instant.now());
		knight.animationTimer.put(knight.shield, Instant.now()); 
		knight.animationTimer.put(knight.leftFoot , Instant.now());
		knight.animationTimer.put(knight.rightFoot, Instant.now());
		knight.animationTimer.put(knight.leftCalf, Instant.now()); 
		knight.animationTimer.put(knight.rightCalf, Instant.now()); 
		knight.animationTimer.put(knight.leftThigh, Instant.now());
		knight.animationTimer.put(knight.rightThigh, Instant.now());
		knight.animationTimer.put(knight.leftElbow, Instant.now());
		knight.animationTimer.put(knight.rightElbow, Instant.now()); 
		knight.animationTimer.put(knight.leftArm, Instant.now());
		knight.animationTimer.put(knight.rightArm, Instant.now()); 
		knight.animationTimer.put(knight.leftHand, Instant.now());
		knight.animationTimer.put(knight.rightHand, Instant.now());
		
	}
	
	public static void resetOffset(LothricKnight knight, int plusMillis) {
		knight.animationTimer.put(knight.pelvis, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.chest, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.head, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.cape, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.sword , Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.shield, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.leftFoot , Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.rightFoot, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.leftCalf, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.rightCalf, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.leftThigh, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.rightThigh, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.leftElbow, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.rightElbow, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.leftArm, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.rightArm, Instant.now().plusMillis(plusMillis)); 
		knight.animationTimer.put(knight.leftHand, Instant.now().plusMillis(plusMillis));
		knight.animationTimer.put(knight.rightHand, Instant.now().plusMillis(plusMillis));
	}
}
