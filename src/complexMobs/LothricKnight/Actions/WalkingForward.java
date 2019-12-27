package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class WalkingForward {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			knight.DirectionAndMovement(.1, 0, true);
			
			if (knight.pelvis != null) {
				//Default to this
				knight.pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				//Up
				if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(300))) {
					knight.pelvisPosition = knight.pelvisPosition.add(new Vector(0,.04,0));	
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(355))) {		
				}
				//Step sound
				else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(405))) {		
					if (knight.soundIsOn("lothricknight.walk")) knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
				}
				//Pause and re-enable sound
				else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(500))) {		
					knight.soundOn("lothricknight.walk");
				}
				//Down
				else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(805))) {
					knight.pelvisPosition = knight.pelvisPosition.add(new Vector(0,-.04,0));	
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(1000))) {	
				}
				//Reset
				else if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(1000))) {
					knight.animationTimer.put(knight.pelvis, Instant.now());
					knight.pelvisPosition = new Vector(0,0.5,0);
				}
				
				knight.pelvisPosition.rotateAroundY(-knight.yaw);
				knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
				
				//Animation
				//Special knight.pelvis animation
				knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, 0, 0, 0, 5000);
			}
			
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
			knight.legacyAnimate(knight.leftThigh, -15,0,0, 30,0,0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(knight.leftThigh, 30, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(knight.leftThigh, 1600);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightThigh, 30, 0, 0, -15,0,0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(knight.rightThigh, -15,0,0, 30, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(knight.rightThigh, 1600);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftCalf, -15, 0, 0, 60, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(knight.leftCalf, 60, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(knight.leftCalf, 1600);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightCalf, 60, 0, 0, -15, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(knight.rightCalf, -15, 0, 0, 60, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(knight.rightCalf, 1600);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftFoot, -15, 0, 0, 60, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(knight.leftFoot, 60, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(knight.leftFoot, 1600);

			knight.partPosition(knight.rightFoot, knight.rightCalfPosition,knight. rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightFoot, 60, 0, 0, -15, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(knight.rightFoot, -15, 0, 0, 60, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(knight.rightFoot, 1600);
			
		} catch (NullPointerException event) {}
	}
}