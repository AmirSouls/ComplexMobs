package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.Mobs.LothricKnight;

public class Death {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling: Gravity only
			knight.DirectionAndMovement(0, 0, false);
			//Default
			knight.pelvisPosition = new Vector(0,0.23,0);
			//Set position based on current time
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(1705))) {
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(2205))) {
				knight.pelvisPosition = new Vector(0, .23 + (.00086 * (knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli() + 1705)), 0);
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(3005))) {
				knight.pelvisPosition = new Vector(0, -.2, 0);
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(3555))) {
				knight.pelvisPosition = new Vector(0, -.2 + (.00072 * (knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli() + 3005)), 0);
			}
			else {
				knight.pelvisPosition = new Vector(0, -1.15, 0);
			}
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, -20, 0, 0, 0, 155);
			knight.legacyAnimate(knight.pelvis, -20, 0, 0, 50, 0, 0, 2205, 3005);
			knight.legacyAnimate(knight.pelvis, 50, 0, 0, 90, 0, 0, 3005, 3905);
			
			//Sounds
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(105)) && knight.soundIsOn("lothricknight.death")) {
				knight.playSound("lothricknight.death", knight.main.getLocation(), 3, 1, 1);
				knight.soundOff("lothricknight.death");
			}
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(2600)) && Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(2755)) && knight.soundIsOn("lothricknight.deathknee")) {
				knight.playSound("lothricknight.deathknee", knight.main.getLocation(), 3, 1, 1);
				knight.soundOff("lothricknight.deathknee");
			}
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(3900)) && Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(3955)) && knight.soundIsOn("lothricknight.deathland")) {
				knight.playSound("lothricknight.deathland", knight.main.getLocation(), 3, 1, 1);
				knight.soundOff("lothricknight.deathland");
			}
			
			//Words: nod up to nod down to nod up to slowly start falling to knees to falling to shake around to tilting forward to face palming to bounce to land
			
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 0, 0, 0, 20, 0, 0, 0, 155);
			knight.legacyAnimate(knight.chest, 20, 0, 0, -20, 0, 0, 155, 355);
			knight.legacyAnimate(knight.chest, -20, 0, 0, -20, -15, 0, 355, 555);
			knight.legacyAnimate(knight.chest, -20, -15, 0, -10, 0, 0, 555, 1155);
			knight.legacyAnimate(knight.chest, -10, 0, 0, -10, 0, 0, 1155, 1705);
			knight.legacyAnimate(knight.chest, -10, 0, 0, 0, 0, 0, 1705, 2205);
			knight.legacyAnimate(knight.chest, 0, 0, 0, 40, 0, 0, 2205, 3555);
			knight.legacyAnimate(knight.chest, 40, 0, 0, 100, 0, 0, 3555, 3905);
			knight.legacyAnimate(knight.chest, 100, 0, 0, 80, 0, 0, 3905, 4205);
			knight.legacyAnimate(knight.chest, 80, 0, 0, 75, 0, 0, 4205, 4505);
			knight.legacyAnimate(knight.chest, 75, 0, 0, 100, 0, 0, 4505, 5205);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.head, 20, 0, 0, 20, 0, 0, 0, 155);
			knight.legacyAnimate(knight.head, 20, 0, 0, -60, 0, 0, 155, 355);
			knight.legacyAnimate(knight.head, -60, 0, 0, 5, 0, 0, 355, 555);
			knight.legacyAnimate(knight.head, 5, 0, 0, -55, -20, 0, 555, 1155);
			knight.legacyAnimate(knight.head, -55, -20, 0, -30, -10, 0, 1155, 1705);
			knight.legacyAnimate(knight.head, -30, -10, 0, -20, -5, 0, 1705, 2205);
			knight.legacyAnimate(knight.head, -20, -5, 0, 30, 0, 0, 2205, 2555);
			//Extra two knight.head bumps
			knight.legacyAnimate(knight.head, 30, 0, 0, 0, -5, 0, 2555, 2755);
			knight.legacyAnimate(knight.head, 0, -5, 0, 30, -10, 0, 2755, 3055);
			//
			knight.legacyAnimate(knight.head, 30, -10, 0, 60, 0, -15, 3055, 3555);
			knight.legacyAnimate(knight.head, 60, 0, -15, 85, 0, -10, 3555, 3905);
			knight.legacyAnimate(knight.head, 60, 0, -15, 70, 0, -10, 3905, 4205);
			knight.legacyAnimate(knight.head, 60, 0, -15, 70, 0, -10, 5205, 4205);
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, 20, 0, 0, 20, 0, 60, 0, 155);
			knight.legacyAnimate(knight.cape, 20, 0, 60, 20, 0, 0, 155, 1005);
			knight.legacyAnimate(knight.cape, 20, 0, 0, 40, 0, 0, 2700, 3555);
			knight.legacyAnimate(knight.cape, 40, 0, 0, 85, 0, 0, 3555, 3905);
			knight.legacyAnimate(knight.cape, 85, 0, 0, 100, 0, 0, 3905, 4205);
			knight.legacyAnimate(knight.cape, 100, 0, 0, 110, 0, 0, 4205, 4505);
			knight.legacyAnimate(knight.cape, 110, 0, 0, 100, 0, 0, 4505, 4705);
			knight.legacyAnimate(knight.cape, 100, 0, 0, 90, 0, 0, 4705, 5205);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, 0, 0, 0, -15, -15, -10, 0, 155);
			knight.legacyAnimate(knight.leftThigh, -15, -15, -10, -25, -15, -10, 155, 355);
			knight.legacyAnimate(knight.leftThigh, -25, -15, -10, -15, -15, -10, 355, 555);
			knight.legacyAnimate(knight.leftThigh, -15, -15, -10, -25, -15, -10, 555, 1155);
			knight.legacyAnimate(knight.leftThigh, -25, -15, -10, -15, -15, -10, 1155, 1705);
			knight.legacyAnimate(knight.leftThigh, -15, -15, -10, -10, -15, -10, 1705, 2205);
			knight.legacyAnimate(knight.leftThigh, -10, -15, -10, -10, -15, -5, 2205, 3555);
			knight.legacyAnimate(knight.leftThigh, -15, -15, -5, 120, 15, 0, 3555, 3905);
			knight.legacyAnimate(knight.leftThigh, 120, 15, 0, 140, 15, 0, 3905, 4205);
			knight.legacyAnimate(knight.leftThigh, 140, 15, 0, 90, 5, 0, 4205, 5205);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, 0, 0, 0, -15, 25, 10, 0, 155);
			knight.legacyAnimate(knight.rightThigh, -15, 25, 10, -25, 25, 10, 155, 355);
			knight.legacyAnimate(knight.rightThigh, -25, 25, 10, -15, 25, 10, 355, 555);
			knight.legacyAnimate(knight.rightThigh, -15, 25, 10, -25, 25, 10, 555, 1155);
			knight.legacyAnimate(knight.rightThigh, -25, 25, 10, -15, 25, 10, 1155, 1705);
			knight.legacyAnimate(knight.rightThigh, -15, 25, 10, -15, 25, -10, 1705, 2205);
			knight.legacyAnimate(knight.rightThigh, -15, 25, -0, 20, 5, 10, 2205, 3555);
			knight.legacyAnimate(knight.rightThigh, -15, 5, 5, 100, -10, 0, 3555, 3905);
			knight.legacyAnimate(knight.rightThigh, 100, -10, 0, 120, -20, 0, 3905, 4205);
			knight.legacyAnimate(knight.rightThigh, 120, -20, 0, 110, -15, 0, 4205, 4505);
			knight.legacyAnimate(knight.rightThigh, 110, -15, 0, 85, -15, 0, 4705, 5205);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 0, 0, 0, 15, -5, 0, 0, 155);
			knight.legacyAnimate(knight.leftCalf, 15, -5, -10, 25, -5, -10, 155, 355);
			knight.legacyAnimate(knight.leftCalf, 25, -5, -10, -5, -5, -10, 355, 555);
			knight.legacyAnimate(knight.leftCalf, -5, -5, -10, 25, -5, -10, 555, 1155);
			knight.legacyAnimate(knight.leftCalf, 25, -5, -10, 15, -5, -10, 1155, 1705);
			knight.legacyAnimate(knight.leftCalf, -15, -5, -10, 90, 10, 0, 1705, 2205);
			knight.legacyAnimate(knight.leftCalf, 90, 10, 0, 160, 0, -20, 3555, 3905);
			knight.legacyAnimate(knight.leftCalf, 90, 10, 0, 160, 0, -20, 3555, 3905);
			knight.legacyAnimate(knight.leftCalf, 160, 0, -20, 170, 0, -20, 3905, 4205);
			knight.legacyAnimate(knight.leftCalf, 170, 0, -20, 160, 0, -20, 4205, 4505);
			knight.legacyAnimate(knight.leftCalf, 160, 0, -20, 70, 0, -20, 4505, 5205);
			
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 0, 0, 0, 15, 5, 0, 0, 155);
			knight.legacyAnimate(knight.rightCalf, 15, 5, 0, 25, 10, 0, 155, 355);
			knight.legacyAnimate(knight.rightCalf, 25, 10, 0, 15, 5, 0, 355, 555);
			knight.legacyAnimate(knight.rightCalf, 15, 25, 10, 25, 10, 0, 555, 1155);
			knight.legacyAnimate(knight.rightCalf, 25, 10, 0, 15, 5, 0, 1155, 1705);
			knight.legacyAnimate(knight.rightCalf, 15, 5, 0, 90, -10, 0, 1705, 2205);
			knight.legacyAnimate(knight.rightCalf, 90, -10, 0, 120, 0, -10, 3555, 3905);
			knight.legacyAnimate(knight.rightCalf, 120, 0, -10, 130, 0, -10, 3905, 4205);
			knight.legacyAnimate(knight.rightCalf, 130, 0, -10, 120, 0, -10, 4405, 4605);
			knight.legacyAnimate(knight.rightCalf, 120, 0, -10, 80, 0, -10, 4705, 5205);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 0, 0, 0, -20, 0, 0, 155);
			knight.legacyAnimate(knight.leftFoot, 0, -20, 0, 135, -10, 0, 1705, 2205);
			knight.legacyAnimate(knight.leftFoot, 135, -10, 0, 220, 0, -15, 3555, 3905);
			knight.legacyAnimate(knight.leftFoot, 220, 0, -15, 130, 0, 0, 4505, 4805);
			
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightFoot, 0, 0, 0, 0, 30, 0, 0, 155);
			knight.legacyAnimate(knight.rightFoot, 0, 30, 0, 135, 10, 0, 1705, 2205);
			knight.legacyAnimate(knight.rightFoot, 135, 10, 0, 180, 0, 0, 3555, 3905);
			knight.legacyAnimate(knight.rightFoot, 180, 10, 0, 145, 0, 0, 4705, 5205);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftElbow, 0, 0, 0, 20, 0, -30, 0, 155);
			knight.legacyAnimate(knight.leftElbow, 20, 0, -30, 30, 0, -60, 155, 355);
			knight.legacyAnimate(knight.leftElbow, 30, 0, -60, 20, 0, -25, 355, 555);
			knight.legacyAnimate(knight.leftElbow, 20, 0, -25, 40, 0, -35, 555, 1155);
			knight.legacyAnimate(knight.leftElbow, 40, 0, -35, 0, 0, -20, 1155, 1705);
			knight.legacyAnimate(knight.leftElbow, 0, 0, -20, 0, 0, -30, 1705, 2205);
			knight.legacyAnimate(knight.leftElbow, 0, 0, -30, 0, 0, -10, 2205, 2555);
			knight.legacyAnimate(knight.leftElbow, 0, 0, -10, 40, 0, -20, 2705, 3555);
			knight.legacyAnimate(knight.leftElbow, 40, 0, -20, 85, 45, 0, 3555, 3905);
			knight.legacyAnimate(knight.leftElbow, 85, 45, 0, 100, 45, 0, 3905, 4205);
			knight.legacyAnimate(knight.leftElbow, 100, 45, 0, 110, 45, 0, 4205, 4505);
			knight.legacyAnimate(knight.leftElbow, 110, 45, 0, 100, 45, 0, 4505, 4705);
			knight.legacyAnimate(knight.leftElbow, 100, 45, 0, 85, 45, 0, 4705, 5005);

			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 0, 0, 0, 30, 0, 30, 0, 155);
			knight.legacyAnimate(knight.rightElbow, 30, 0, 30, 30, 0, 40, 155, 355);
			knight.legacyAnimate(knight.rightElbow, 30, 0, 40, 20, 0, 20, 355, 555);
			knight.legacyAnimate(knight.rightElbow, 20, 0, 20, 20, 0, 10, 555, 1155);
			knight.legacyAnimate(knight.rightElbow, 20, 0, 10, 10, 15, 0, 1155, 1705);
			//
			knight.legacyAnimate(knight.rightElbow, 10, 15, 0, 40, 0, 30, 2705, 3555);
			knight.legacyAnimate(knight.rightElbow, 40, 0, 30, 80, 0, 30, 3555, 3905);
			knight.legacyAnimate(knight.rightElbow, 80, 0, 30, 110, 0, 30, 3905, 4205);
			knight.legacyAnimate(knight.rightElbow, 110, 0, 30, 120, 0, 30, 4205, 4505);
			knight.legacyAnimate(knight.rightElbow, 120, 0, 30, 110, 0, 30, 4605, 4905);
			knight.legacyAnimate(knight.rightElbow, 110, 0, 30, 80, 0, 30, 4905, 5205);
			
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, 0, 0, 0, -10, 0, -30, 0, 155);
			knight.legacyAnimate(knight.leftArm, -10, 0, -30, 30, 0, -60, 155, 355);
			knight.legacyAnimate(knight.leftArm, 30, 0, -60, -10, 0, -60, 355, 555);
			knight.legacyAnimate(knight.leftArm, -10, 0, -60, -20, 0, -60, 555, 1155);
			knight.legacyAnimate(knight.leftArm, -20, 0, -60, -20, 0, -25, 1155, 1705);
			//
			knight.legacyAnimate(knight.leftArm, -20, 0, -25, -20, 0, -55, 1705, 2205);
			//
			knight.legacyAnimate(knight.leftArm, -20, 0, -55, -20, 0, -15, 2205, 2705);
			knight.legacyAnimate(knight.leftArm, -20, 0, -15, 40, 0, -20, 2705, 3555);
			knight.legacyAnimate(knight.leftArm, 40, 0, -20, 90, 60, 0, 3555, 3905);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, 0, 0, 0, -20, 0, 15, 0, 155);
			knight.legacyAnimate(knight.rightArm, -20, 0, 15, -20, 0, 50, 155, 355);
			knight.legacyAnimate(knight.rightArm, -20, 0, 15, -20, 0, 40, 355, 555);
			knight.legacyAnimate(knight.rightArm, -20, 0, 40, -20, 0, 50, 555, 1155);
			knight.legacyAnimate(knight.rightArm, -20, 0, 50, -10, 0, 60, 1155, 1705);
			knight.legacyAnimate(knight.rightArm, -10, 0, 60, -20, 0, 35, 1705, 2205);
			knight.legacyAnimate(knight.rightArm, -20, 0, 35, -20, 0, 25, 2205, 2555);
			knight.legacyAnimate(knight.rightArm, -20, 0, 25, -20, 0, 15, 2555, 2705);
			knight.legacyAnimate(knight.rightArm, -20, 0, 15, 40, 0, 20, 2705, 3555);
			knight.legacyAnimate(knight.rightArm, 40, 0, 45, 0, 20, 90, 3555, 3905);
			knight.legacyAnimate(knight.rightArm, 0, 20, 80, 0, 20, 100, 3905, 4205);
			knight.legacyAnimate(knight.rightArm, 0, 20, 100, 0, 20, 110, 4205, 4405);
			knight.legacyAnimate(knight.rightArm, 0, 20, 110, 0, 20, 100, 4505, 4605);
			knight.legacyAnimate(knight.rightArm, 0, 20, 100, 0, 20, 70, 4605, 4805);
			
			knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftHand, 0, 0, 0, 0, 0, -30, 0, 155);
			knight.legacyAnimate(knight.leftHand, 0, 0, -30, 30, 0, -50, 155, 355);
			knight.legacyAnimate(knight.leftHand, 30, 0, -50, -10, 0, -60, 155, 355);
			knight.legacyAnimate(knight.leftHand, -10, 0, -60, -30, 0, -60, 555, 1155);
			knight.legacyAnimate(knight.leftHand, -30, 0, -60, -20, 0, -25, 1155, 1705);
			knight.legacyAnimate(knight.leftHand, -20, 0, -25, -20, 0, -10, 1705, 2705);
			knight.legacyAnimate(knight.leftHand, -20, 0, -10, 40, 0, -10, 2705, 3555);
			knight.legacyAnimate(knight.leftHand, -40, 0, -10, -70, 0, -10, 2705, 3555);
			knight.legacyAnimate(knight.leftHand, -90, 0, -10, -100, 0, -10, 3905, 4205);
			knight.legacyAnimate(knight.leftHand, -100, 0, -10, -110, 0, -10, 4205, 4505);
			knight.legacyAnimate(knight.leftHand, -110, 0, -10, -100, 0, -10, 4505, 4805);
			knight.legacyAnimate(knight.leftHand, -100, 0, -10, -70, 0, -10, 4805, 4905);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, 0, 0, 15, 0, 0, 0, 155);
			knight.legacyAnimate(knight.rightHand, 15, 0, 0, -20, 0, 60, 155, 355);
			knight.legacyAnimate(knight.rightHand, -20, 0, 60, -20, 0, 40, 355, 555);
			knight.legacyAnimate(knight.rightHand, -20, 0, 40, -20, 0, 50, 555, 1155);
			knight.legacyAnimate(knight.rightHand, -20, 0, 50, -10, 0, 60, 1155, 1705);
			knight.legacyAnimate(knight.rightHand, -10, 0, 60, -20, 0, 35, 1705, 2205);
			knight.legacyAnimate(knight.rightHand, -20, 0, 35, -20, 0, 25, 2555, 2705);
			knight.legacyAnimate(knight.rightHand, -20, 0, 25, 40, 0, 45, 2705, 3555);
			knight.legacyAnimate(knight.rightHand, 40, 0, 45, 0, 20, 90, 3555, 3905);
			
			knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 0, 0, 0, 10, 10, 0, 0, 155);
			knight.legacyAnimate(knight.sword, 10, 10, 0, 20, 5, 0, 155, 355);
			knight.legacyAnimate(knight.sword, 20, 5, 0, 45, 45, 0, 355, 555);
			knight.legacyAnimate(knight.sword, 45, 45, 0, 5, 0, 0, 555, 1155);
			knight.legacyAnimate(knight.sword, 5, 0, 0, 5, 10, 0, 1155, 1705);
			knight.legacyAnimate(knight.sword, 5, 10, 0, -40, 0, -90, 3555, 3905);
			knight.legacyAnimate(knight.sword, -40, 0, -90, -40, 0, -80, 4005, 4505);
			knight.legacyAnimate(knight.sword, -40, 0, -80, -40, 0, -90, 4505, 4705);
			
			knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 0, 20, -30, 0, 20, -30, 0, 155);
			knight.legacyAnimate(knight.shield, 0, 20, -30, 0, 20, -30, 155, 355);
			knight.legacyAnimate(knight.shield, 0, 20, -30, 0, -15, -50, 355, 555);
			knight.legacyAnimate(knight.shield, 0, -15, -50, -30, -45, -40, 555, 1155);
			knight.legacyAnimate(knight.shield, -30, -45, -40, 0, -30, -25, 1155, 1705);
			knight.legacyAnimate(knight.shield, 0, -30, -25, 0, 25, -15, 1705, 2705);
			knight.legacyAnimate(knight.shield, 0, 25, -15, 0, 15, -15, 2705, 3555);
			knight.legacyAnimate(knight.shield, 0, 15, -15, -40, 0, -75, 3555, 3905);
			
		} catch (NullPointerException event) {}
	}
}