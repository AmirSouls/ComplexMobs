package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class LeftSlash {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			double distance = knight.target.getLocation().distance(knight.main.getLocation());
			double moveMult = 0;
			if (distance > 2) moveMult = .12;
			knight.DirectionAndMovementTimed(distance * moveMult, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 0, 605, true);
			
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(755))) {
				if (distance < 4 && knight.stamina > 0 && Math.random() < .7) {
					ResetTimers.reset(knight);
					knight.activeAction = "RightSlash";
					knight.stamina = knight.stamina - 35;
					knight.staminaUseTimer = Instant.now();
					knight.attackAreaPts = null;
					
					//Re-enable all sounds
					knight.enableAllSounds();
				}
				else {
					ResetTimers.reset(knight);
					knight.isAttacking = false;
					
					//Re-enable all sounds
					knight.enableAllSounds();
				}
			}
			
			
			//Pelvis
			knight.pelvisPosition = new Vector(0,0.5,0);
			//Set position based on current time
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(150))) {
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(600))) {
				knight.pelvisPosition = new Vector(0, .5 + (.001 * (knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(1000))) {
				knight.pelvisPosition = new Vector(0, -.1, 0);
			}
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, -80, 0, 500, 605);
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, 0, 0, 0, 505);
			
			//Chest and knight.head
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 0, 0, 0, 14, 14, 0, 0, 355);
			knight.legacyAnimate(knight.chest, 14, 14, 0, 14, -80, 0, 350, 505);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			HeadZeroAnimation.animate(knight.head, knight);	
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, 10, 0, 0, 30, 0, -90, 0, 505);
			knight.legacyAnimate(knight.cape, 30, 0, -90, 100, -80, 0, 550, 655);
			knight.legacyAnimate(knight.cape, 100, -80, 0, 10, -70, 0, 650, 1000);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 0, 0, 20, 0, 180, 110, 0, 155);
			knight.legacyAnimate(knight.rightElbow, 0, 180, 110, 0, 180, 135, 150, 305);
			knight.legacyAnimate(knight.rightElbow, 0, 180, 135, 0, -70, 105, 300, 405);
			knight.legacyAnimate(knight.rightElbow, 0, -70, 105, -90, -45, 90, 400, 505);
			knight.legacyAnimate(knight.rightElbow, -90, -45, 90, -80, -80, 80, 500, 605);
			knight.legacyAnimate(knight.rightElbow, -80, -80, 80, 0, -80, 0, 600, 655);
		
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, 0, 0, -20, 0, 0, -20, 0, 1000);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, 0, 0, 20, -20, 180, 145, 0, 155);
			knight.legacyAnimate(knight.rightArm, -20, 180, 145, -20, 180, 210, 150, 305);
			knight.legacyAnimate(knight.rightArm, -20, 180, 210, -20, 180, 185, 300, 405);
			knight.legacyAnimate(knight.rightArm,-20, 180, 185, -135, 0, 0, 400, 505);
			knight.legacyAnimate(knight.rightArm, -135, 0, 0, -50, 20, -80, 500, 605);
			knight.legacyAnimate(knight.rightArm, -50, 20, -80, 0, -30, -70, 600, 655);
			
			knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftHand, 0, 0, -20, 0, 0, -20, 0, 1000);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, 0, 20, -20, 180, 145, 0, 155);
			knight.legacyAnimate(knight.rightHand, -20, 180, 145, 0, 180, 210, 150, 305);
			knight.legacyAnimate(knight.rightHand, -20, 180, 210, -20, 180, 185, 300, 405);
			knight.legacyAnimate(knight.rightHand, -20, 180, 185, -90, 0, -90, 400, 505);
			knight.legacyAnimate(knight.rightHand, -90, 0, -90, -50, 20, -80, 500, 605);
			knight.legacyAnimate(knight.rightHand, -50, 20, -80, 0, -30, -70, 600, 655);
			
			knight.swordPosition = knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 0, 0, 0, -70, 60, 70, 0, 155);
			knight.legacyAnimate(knight.sword, -70, 60, 70, 15, -95, 0, 150, 305);
			knight.legacyAnimate(knight.sword, 15, -95, 0, 185, 30, -10, 300, 355);
			knight.legacyAnimate(knight.sword, 185, 30, -10, -90, 0, -40, 400, 455);
			knight.legacyAnimate(knight.sword, -90, 0, -40, -20, 15, -55, 500, 605);;
			knight.legacyAnimate(knight.sword, -20, 15, -55, 100, -30, -70, 600, 655);;
			
			knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 10, 50, 0, 10, 50, 0, 0, 1000);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, 0, 0, 0, 30, -45, -30, 0, 155);
			knight.legacyAnimate(knight.leftThigh, 30, -45, -30, 50, -45, -50, 150, 355);
			knight.legacyAnimate(knight.leftThigh, 50, -45, -50, -75, -110, 0, 350, 605);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, 0, 0, 0, -45, 30, 0, 0, 305);
			knight.legacyAnimate(knight.rightThigh, -45, 30, 0, -75, -15, 0, 300, 605);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 0, 0, 0, 40, -45, -40, 0, 155);
			knight.legacyAnimate(knight.leftCalf, 40, -45, -40, 60, -10, 0, 150, 355);
			knight.legacyAnimate(knight.leftCalf, 60, -10, 0, 10, -110, 0, 350, 605);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 0, 0, 0, 15, 60, 0, 0, 205);
			knight.legacyAnimate(knight.rightCalf, 15, 60, 0, 15, 0, 5, 200, 605);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, -45, 0, 30, 0, 0, 0, 305);
			knight.legacyAnimate(knight.leftFoot, 0, 30, 0, 0, -90, 0, 300, 605);
		
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 0, 0, 0, 0, 0, 0, 1000);
			
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
			knight.DamageArea(knight.sword, 2, .5, knight.animationTimer.get(knight.sword), 495, 655, knight.swordPosition, knight.yaw, true, 12, .7, .2);
			
		} catch (NullPointerException event) {}
	}
}