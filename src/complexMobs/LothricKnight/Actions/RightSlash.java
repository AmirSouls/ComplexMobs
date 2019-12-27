package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Mobs.LothricKnight;

public class RightSlash {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			double distance = knight.target.getLocation().distance(knight.main.getLocation());
			double moveMult = 0;
			if (distance > 2) moveMult = .12;
			knight.DirectionAndMovementTimed(distance * moveMult, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 0, 605, true);
			
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(805))) {
				if (distance < 4 && knight.stamina > 0 && Math.random() < .7) {
					ResetTimers.reset(knight);
					knight.activeAction = "LeftSlash";
					knight.stamina = knight.stamina - 35;
					knight.staminaUseTimer = Instant.now();
					knight.attackAreaPts = null;
					
					//Re-enable all sounds
					knight.enableAllSounds();
				}
				else {
					ResetTimers.reset(knight);
					knight.isAttacking = false;
					knight.attackAreaPts = null;
					
					//Re-enable all sounds
					knight.enableAllSounds();
				}
			}
			
			//Default to this
			knight.pelvisPosition = new Vector(0,0.29,0);
			
			//Set position based on current time
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(305))) {
			}
			else {
				knight.pelvisPosition = new Vector(0, .29 + (.0001 * (knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
			}
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 5, -45, 0, 0, 305);
			knight.legacyAnimate(knight.pelvis, 5, -45, 0, -5, 40, 0, 305, 505);
			
			
			//Chest and head
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 14, -80, 0, 20, -45, 0, 0, 305);
			knight.legacyAnimate(knight.chest, 20, -45, 0, 55, -20, 0, 305, 505);
			knight.legacyAnimate(knight.chest, 55, -20, 0, 55, 45, 0, 505, 655);
			knight.legacyAnimate(knight.chest, 55, 45, 0, 55, 85, 0, 655, 805);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.head, 0, 0, 0, 20, -20, 0, 0, 305);
			knight.legacyAnimate(knight.head, 20, -20, 0, 30, 0, 0, 305, 505);
			knight.legacyAnimate(knight.head, 30, 0, 0, 10, 10, 0, 505, 655);
			knight.legacyAnimate(knight.head, 10, 10, 0, 20, 20, 0, 655, 805);
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, 10, -70, 0, -90, -30, 110, 0, 305);
			knight.legacyAnimate(knight.cape, -90, -30, 110, 85, -15, 0, 305, 505);
			knight.legacyAnimate(knight.cape, 85, -15, 0, 110, 30, 90, 505, 655);
			knight.legacyAnimate(knight.cape, 110, 30, 90, 30, 0, 0, 655, 3000);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftElbow, 0, 0, 0, 40, -55, 0, 0, 305);
			knight.legacyAnimate(knight.leftElbow, 40, -55, 0, 40, -40, 0, 305, 505);
			knight.legacyAnimate(knight.leftElbow, 40, -40, 0, 0, 90, -20, 505, 805);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 0, -80, 0, -50, -100, 0, 0, 305);
			knight.legacyAnimate(knight.rightElbow, -50, -100, 0, -50, -80, 0, 305, 505);
			knight.legacyAnimate(knight.rightElbow, -50, -80, 0, -50, -30, 0, 505, 555);
			knight.legacyAnimate(knight.rightElbow, -50, -30, 0, -50, 5, 0, 555, 605);
			knight.legacyAnimate(knight.rightElbow, -50, 5, 0, -50, 70, 0, 605, 655);
			knight.legacyAnimate(knight.rightElbow, -50, 70, 0, -40, 170, 0, 655, 805);
			
			knight.rightElbowPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, 0, 0, -20, -30, -55, 0, 0, 305);
			knight.legacyAnimate(knight.leftArm, -30, -55, 0, -100, -55, 0, 305, 505);
			knight.legacyAnimate(knight.leftArm, -100, -55, 0, 0, 0, 0, 505, 805);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, 0, -30, -70, -70, -130, 0, 0, 305);
			knight.legacyAnimate(knight.rightArm, -70, -130, 0, -80, -70, 0, 305, 505);
			knight.legacyAnimate(knight.rightArm, -80, -70, 0, -80, -30, 0, 505, 555);
			knight.legacyAnimate(knight.rightArm, -80, -30, 0, -80, 5, 0, 555, 605);
			knight.legacyAnimate(knight.rightArm, -80, 5, 0, -60, 70, 0, 605, 655);
			knight.legacyAnimate(knight.rightArm, -60, 70, 0, -50, 165, 0, 655, 805);
			
			knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.rightElbowPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftHand, 0, 0, -20, 0, 55, 0, 0, 305);
			knight.legacyAnimate(knight.leftHand, 0, 55, 0, 0, 90, 0, 505, 805);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, -30, -70, -80, -170, 0, 0, 305);
			knight.legacyAnimate(knight.rightHand, -80, -170, 0, -80, -80, 0, 305, 505);
			knight.legacyAnimate(knight.rightHand, -80, -80, 0, -80, -20, 0, 505, 555);
			knight.legacyAnimate(knight.rightHand, -80, -20, 0, -80, 5, 0, 555, 605);
			knight.legacyAnimate(knight.rightHand, -80, 5, 0, -60, 70, 0, 605, 655);
			knight.legacyAnimate(knight.rightHand, -60, 70, 0, -50, 160, 0, 655, 805);

			Vector swordPosition = knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 100, -30, -70, 10, -220, 0, 0, 305);
			knight.legacyAnimate(knight.sword, 10, -220, 0, 0, -130, 0, 305, 505);
			knight.legacyAnimate(knight.sword, 0, -130, 0, 0, -85, 0, 505, 555);
			knight.legacyAnimate(knight.sword, 0, -85, 0, 0, -30, 0, 555, 605);
			knight.legacyAnimate(knight.sword, 0, -30, 0, 30, 40, 0, 605, 655);
			knight.legacyAnimate(knight.sword, 30, 40, 0, 30, 85, 0, 655, 805);
			
			
			knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 10, 50, 0, 0, 15, 0, 0, 305);
			knight.legacyAnimate(knight.shield, 0, 15, 0, 0, 40, 10, 305, 505);
			knight.legacyAnimate(knight.shield, 0, 40, 10, 0, 90, 10, 605, 805);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, -50, -75, -110, -40, -45, 0, 0, 305);
			knight.legacyAnimate(knight.leftThigh, -40, -45, 0, -50, -10, 0, 305, 505);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, -75, -15, 0, -10, 35, 0, 0, 305);
			knight.legacyAnimate(knight.rightThigh, -10, 35, 0, 45, -75, 0, 305, 505);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 10, -110, 0, 15, -45, 0, 0, 305);
			knight.legacyAnimate(knight.leftCalf, 15, -45, 0, -40, -10, 0, 305, 505);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 15, 0, 5, 20, 40, 0, 0, 305);
			knight.legacyAnimate(knight.rightCalf, 20, 40, 0, 15, 80, -10, 305, 505);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, -90, 0, 0, -25, 0, 0, 305);
			knight.legacyAnimate(knight.leftFoot, 0, -25, 0, 0, 15, 0, 305, 505);
		
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightFoot, 0, 0, 0, 0, 0, 30, 0, 305);
			knight.legacyAnimate(knight.rightFoot, 0, 30, 0, 20, 80, 0, 305, 505);
			
			//Sounds
			if (Instant.now().isAfter(knight.animationTimer.get(knight.sword).plusMillis(500)) && Instant.now().isBefore(knight.animationTimer.get(knight.sword).plusMillis(555))) {
				if (knight.soundIsOn("lothricknight.slash")) {
					//Slash sound
					knight.playSound("lothricknight.slash", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.slash");
				}
				
				if (knight.soundIsOn("lothricknight.grunt")) {
					//Grunt sound
					knight.playSound("lothricknight.grunt", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.grunt");
				}
				
				if (knight.soundIsOn("lothricknight.walk")) {
					//Step sound
					knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.walk");
				}
			}
			
			//Attack frames: Appears as 9 in 60fps, but is really 3 in game tick speed.
			knight.DamageArea(knight.sword, 2, .5, knight.animationTimer.get(knight.sword), 500, 655, swordPosition, knight.yaw, true, 12, .7, .2);
		
		} catch (NullPointerException event) {}
	}
}