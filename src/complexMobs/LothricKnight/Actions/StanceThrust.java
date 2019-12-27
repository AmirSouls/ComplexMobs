package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Mobs.LothricKnight;

public class StanceThrust {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			double distance = knight.target.getLocation().distance(knight.main.getLocation());
			//Charge up frames
			knight.DirectionAndMovementTimed(distance * .014, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 0, 355, true);

			//Attack frames with aim
			knight.DirectionAndMovementTimed(distance * .3, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 355, 455, true);

			//Attack frames without aim
			knight.DirectionAndMovementTimed(distance * .3, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 455, 605, false);
			
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(1600))) {
				ResetTimers.resetOffset(knight, -55);
				knight.isAttacking = false;
				
				//Re-enable all sounds
				knight.enableAllSounds();
			}
			
			//Sounds
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(350)) && Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(455))) {
				
				//Grunt sound
				if (knight.soundIsOn("lothricknight.grunt")) {
					knight.playSound("lothricknight.grunt", knight.main.getLocation(), 3, 1, 1);
					knight.soundOff("lothricknight.grunt");
				}
				
				//Stance attack sound fx
				if (knight.soundIsOn("lothricknight.stanceattack")) {
					knight.playSound("lothricknight.stanceattack", knight.main.getLocation(), 3, 1, 1);
					knight.soundOff("lothricknight.stanceattack");
				}
			}
			
			//Pelvis
			knight.pelvisPosition = new Vector(0,.2,0);
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, -5, 55, 10, 30, 50, 0, 0, 355);
			knight.legacyAnimate(knight.pelvis, 30, 50, 0, 0, -90, 0, 355, 605);
			
			//Chest, head, cape and shield on back
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 50, 50, 40, 75, 35, 30, 0, 355);
			knight.legacyAnimate(knight.chest, 75, 35, 30, 0, -90, 0, 355, 605);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.head, 30, 0, 0, 60, 10, 0, 0, 355);
			knight.legacyAnimate(knight.head, 60, 10, 0, 30, -20, 0, 355, 605);
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, -35, 70, -30, 90, 35, 30, 0, 355);
			knight.legacyAnimate(knight.cape, 90, 35, 30, 0, -50, 30, 355, 605);
			
			knight.partPosition(knight.shield, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-.4,.9,-.1), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 180, -30, -0, 180, 40, -70, 0, 355);
			knight.legacyAnimate(knight.shield, 180, 40, -70, 150, 180, 30, 355, 605);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftElbow, -90, 70, 0, 0, 100, 40, 0, 355);
			knight.legacyAnimate(knight.leftElbow, 0, 100, 40, 90, -50, -90, 355, 605);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, -145, 160, 0, 125, 0, -20, 0, 355);
			knight.legacyAnimate(knight.rightElbow, 125, 0, -20, -90, 0, 0, 355, 605);
		
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, -130, 160, 0, 90, -60, 50, 0, 355);
			knight.legacyAnimate(knight.leftArm, 90, -60, 50, 20, 0, 0, 355, 605);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, -40, 0, 45, -20, 0, 70, 0, 355);
			knight.legacyAnimate(knight.rightArm, 20, 0, 70, -90, -10, 0, 355, 605);
			
			knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftHand, 120, -40, 0, 90, -60, 50, 0, 355);
			knight.legacyAnimate(knight.leftHand, 90, -60, 50, 0, 0, 0, 355, 605);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, 0, 45, 20, 0, 70, 0, 355);
			knight.legacyAnimate(knight.rightHand, 20, 0, 70, -90, 0, 0, 355, 605);
			
			knight.swordPosition = knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 7, -15, 60, 30, -15, 60, 0, 355);
			knight.legacyAnimate(knight.sword, 30, -15, 60, 0, 0, 0, 355, 605);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, -10, 0, 0, 30, 40, 0, 0, 355);
			knight.legacyAnimate(knight.leftThigh, 30, 40, 0, -30, -100, 30, 355, 605);

			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, -25, 110, -60, 50, 0, 0, 0, 355);
			knight.legacyAnimate(knight.rightThigh, 50, 0, 0, 15, -70, 0, 355, 605);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 10, -10, -15, 75, 30, 0, 0, 355);
			knight.legacyAnimate(knight.leftCalf, 75, 30, 0, -30, -150, 0, 355, 605);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 90, 60, 90, 75, 50, 0, 0, 355);
			knight.legacyAnimate(knight.rightCalf, 0, 0, 0, 30, -55, 0, 355, 605);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 10, 0, 50, 30, 0, 0, 355);
			knight.legacyAnimate(knight.leftFoot, 50, 30, 0, 0, -90, 0, 355, 605);
		
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightFoot, 0, 70, 0, 75, 50, 10, 0, 355);
			knight.legacyAnimate(knight.rightFoot, 75, 50, 10, 0, -45, 0, 355, 605);
			
			knight.DamageArea(knight.sword, 2, .5, knight.animationTimer.get(knight.sword), 350, 655, knight.swordPosition, knight.yaw, true, 22, .7, .4);
			
		} catch (NullPointerException event) {}
	}
}