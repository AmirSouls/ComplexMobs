package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class Running {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			knight.DirectionAndMovement(.28, 0, true);
			
			//Default pelvis to
			knight.pelvisPosition = new Vector(0,0.5,0);
			//Set position based on current time
			double deltaTime = knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli();
			double heightModif = -.05;
			//Down
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(105))) {
				knight.pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
			}
			//Up
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(205))) {
				deltaTime += 100;
				knight.pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
			}
			//Sound
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(255))) {
				if (knight.soundIsOn("lothricknight.run")) knight.playSound("lothricknight.run", knight.main.getLocation(), 3, 1, 1);
			}
			//Pause and re-enable sounds
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(305))) {
				knight.soundOn("lothricknight.run");
			}
			//Down
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(405))) {
				deltaTime += 300;
				knight.pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
			}
			//Down
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(505))) {
				deltaTime += 400;
				knight.pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
			}
			//Sound
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(555))) {
				if (knight.soundIsOn("lothricknight.run")) knight.playSound("lothricknight.run", knight.main.getLocation(), 3, 1, 1);
			}
			//Pause and re-enable sounds
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(605))) {
				knight.soundOn("lothricknight.run");
			}
			//Reset
			else if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(605))) {
				ResetTimers.reset(knight);
			}
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			
			//Chest, knight.head and cape
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.chest, 30, -5, 0, 30, 5, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.chest, 30, 5, 0, 30, -5, 0, 305, 505);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			HeadZeroAnimation.animate(knight.head, knight);
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, 0, 0, 0, 5000);
	
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftElbow, 40, 0, -30, 10, 0, -10, 0, 205);
			//Left
			knight.legacyAnimate(knight.leftElbow, 10, 0, -10, 40, 0, -30, 305, 505);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightElbow, 30, 0, 30, 55, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(knight.rightElbow, 55, 0, 45, 30, 0, 30, 305, 505);
			
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftArm, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.leftArm, -30, 30, 0, 0, 0, -10, 305, 505);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightArm, 0, 0, 10, 55, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(knight.rightArm, 55, 0, 45, 0, 0, 10, 305, 505);
			
			knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftHand, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.leftHand, -30, 30, 0, 0, 0, -10, 305, 505);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightHand, 0, 0, 0, 45, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(knight.rightHand, 45, 0, 45, 0, 0, 0, 305, 505);
			
			knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.sword, 50, 0, 35, 70, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(knight.sword, 70, 0, 45, 50, 0, 35, 305, 505);
			
			knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.shield, 0, 30, -5, -25, 65, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.shield, -25, 65, 0, 0, 30, -5, 305, 505);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftThigh, -60, 0, 0, 45, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.leftThigh, 45, 0, 0, -60, 0, 0, 305, 505);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightThigh, 45, 0, 0, -60, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.rightThigh, -60, 0, 0, 45, 0, 0, 305, 505);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftCalf, -10, 0, 0, 100, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.leftCalf, 100, 0, 0, -10, 0, 0, 305, 505);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightCalf, 100, 0, 0, -10, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.rightCalf, -10, 0, 0, 100, 0, 0, 305, 505);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.leftFoot, 0, 0, 0, 50, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.leftFoot, 50, 0, 0, 0, 0, 0, 305, 505);

			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			//Right
			knight.legacyAnimate(knight.rightFoot, 50, 0, 0, 0, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(knight.rightFoot, 0, 0, 0, 50, 0, 0, 305, 505);
			
		} catch (NullPointerException event) {}
	}
}