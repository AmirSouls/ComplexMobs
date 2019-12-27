package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class WalkingSide {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			knight.DirectionAndMovement(.06, -90, true);
			
			//Pelvis
			knight.pelvisPosition = new Vector(0,0.4,0);
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			//Chest, head and cape
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			HeadZeroAnimation.animate(knight.head, knight);
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			//Arms
			if (knight.shieldUp) {
				knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.leftElbow, -10, -10, 5, -10, -10, 5, 0, 5000);
				
				knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.rightElbow, 20, 0, 5, 20, 0, 5, 0, 5000);
				
				knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.leftArm, -60, 0, 40, -60, 0, 40, 0, 5000);
				
				knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.rightArm, 10, 0, 20, 10, 0, 20, 0, 5000);
				
				knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.pelvis, 100, 100, 0, 100, 100, 0, 0, 5000);
				
				knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.rightHand, 0, 0, 0, 0, 0, 0, 0, 5000);
				
				knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.sword, 0, 0, 0, 0, 0, 0, 0, 5000);
				
				knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.shield, 100, 0, 0, 100, 0, 0, 0, 5000);
			}
			else {
				knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.leftElbow, 0, -20, -20, 0, -20, -20, 0, 5000);
				
				knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.rightElbow, 30, 30, 0, 30, 30, 0, 0, 5000);
				
				knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.leftArm, 0, 10, 10, 0, 10, 10, 0, 5000);
				
				knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.rightArm, 0, 0, 40, 0, 0, 40, 0, 5000);
				
				knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.leftHand, 10, 5, 5, 5, 10, 5, 0, 5000);
				
				knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.rightHand, 0, 40, 40, 0, 40, 40, 0, 5000);
				
				knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.sword, 20, 20, 20, 20, 20, 20, 0, 5000);
				
				knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
				knight.legacyAnimate(knight.shield, 10, 10, 10, 10, 10, 10, 0, 5000);
			}
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftThigh, -30, -20, 0, -50, 30, 0, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(knight.leftThigh, -50, 30, 0, -30, -20, 0, 900, 1655);
			//Pause
			//Reset
			knight.reset(knight.leftThigh, 1800);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightThigh, -30, 30, 0, 20, 55, 0, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(knight.rightThigh, 20, 55, 0, -30, 30, 0, 900, 1655);
			//Pause
			//Reset
			knight.reset(knight.rightThigh, 1800);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftCalf, 10, 0, -10, -10, 15, 10, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(knight.leftCalf, -10, 15, 10, 10, 0, -10, 900, 1655);
			//Pause
			//Reset
			knight.reset(knight.leftCalf, 1800);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightCalf, 5, 0, 10, 50, 80, 0, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(knight.rightCalf, 50, 80, 0, 5, 0, 10, 900, 1655);
			//Pause
			//Reset
			knight.reset(knight.rightCalf, 1800);
			
			//Step sound
			if (Instant.now().isAfter(knight.animationTimer.get(knight.rightCalf).plusMillis(755)) && Instant.now().isBefore(knight.animationTimer.get(knight.rightCalf).plusMillis(805))) {		
				if (knight.soundIsOn("lothricknight.walk")) knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
			}
			//Re-enable sound
			else if (Instant.now().isAfter(knight.animationTimer.get(knight.rightCalf).plusMillis(805)) && Instant.now().isBefore(knight.animationTimer.get(knight.rightCalf).plusMillis(1655))) {	
				knight.soundOn("lothricknight.walk");
			}
			//Step sound
			else if (Instant.now().isAfter(knight.animationTimer.get(knight.rightCalf).plusMillis(1655)) && Instant.now().isBefore(knight.animationTimer.get(knight.rightCalf).plusMillis(1705))) {		
				if (knight.soundIsOn("lothricknight.walk")) knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
			}
			//Re-enable sound
			else if (Instant.now().isAfter(knight.animationTimer.get(knight.rightCalf).plusMillis(1705))) {	
				knight.soundOn("lothricknight.walk");
			}
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightFoot, 0, 0, 0, 0, 0, 0, 0, 5000);
			
		} catch (NullPointerException event) {}
	}
}