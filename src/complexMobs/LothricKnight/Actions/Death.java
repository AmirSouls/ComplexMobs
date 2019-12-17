package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.Methods.DirectionAndMovement;
import complexMobs.Methods.LegacyAnimate;
import complexMobs.Methods.PartPositioning;
import complexMobs.Methods.PlaySound;
import complexMobs.Methods.ToggleSound;
import complexMobs.Mobs.LothricKnight;

public class Death {
	
	public static void animate(LothricKnight knight) {
		try {
			if (knight.changeTimer == null) {
				knight.changeTimer = Instant.now();
			}
			
			//Position all armorStands
			
			//Initialize parts
			ArmorStand pelvis = null;
			ArmorStand chest = null;
			ArmorStand head = null;
			ArmorStand sword = null;
			ArmorStand shield = null;
			ArmorStand leftFoot = null;
			ArmorStand rightFoot = null;
			ArmorStand leftCalf = null;
			ArmorStand rightCalf = null;
			ArmorStand leftThigh = null;
			ArmorStand rightThigh = null;
			ArmorStand leftElbow = null;
			ArmorStand rightElbow = null;
			ArmorStand leftArm = null;
			ArmorStand rightArm = null;
			ArmorStand leftHand = null;
			ArmorStand rightHand = null;
			ArmorStand cape = null;
			
			double yaw = knight.main.getLocation().getYaw() / 57.2957795;
			
			//Initialize positions and offsets
			Vector pelvisPosition = new Vector(0,0,0);
			Vector chestPosition = new Vector(0,0,0);
			Vector leftCalfPosition = new Vector(0,0,0);
			Vector rightCalfPosition = new Vector(0,0,0);
			Vector leftThighPosition = new Vector(0,0,0);
			Vector rightThighPosition = new Vector(0,0,0);
			Vector leftElbowPosition = new Vector(0,0,0);
			Vector rightElbowPosition = new Vector(0,0,0);
			Vector leftArmPosition = new Vector(0,0,0);
			Vector rightArmPosition = new Vector(0,0,0);
			Vector leftHandPosition = new Vector(0,0,0);
			Vector rightHandPosition = new Vector(0,0,0);
			
			
			for (ArmorStand part : knight.parts) {
				if (knight.partId.containsKey(part)) {
					String partId = knight.partId.get(part);
	
					if (partId.contentEquals("pelvis")) pelvis = part;
					if (partId.contentEquals("chest")) chest = part;
					if (partId.contentEquals("head")) head = part;
					if (partId.contentEquals("cape")) cape = part;
					if (partId.contentEquals("sword")) sword = part;
					if (partId.contentEquals("shield")) shield = part;
					if (partId.contentEquals("leftFoot")) leftFoot = part;
					if (partId.contentEquals("rightFoot")) rightFoot = part;
					if (partId.contentEquals("leftCalf")) leftCalf = part;
					if (partId.contentEquals("rightCalf")) rightCalf = part;
					if (partId.contentEquals("leftThigh")) leftThigh = part;
					if (partId.contentEquals("rightThigh")) rightThigh = part;
					if (partId.contentEquals("leftElbow")) leftElbow = part;
					if (partId.contentEquals("rightElbow")) rightElbow = part;
					if (partId.contentEquals("leftArm")) leftArm = part;
					if (partId.contentEquals("rightArm")) rightArm = part;
					if (partId.contentEquals("leftHand")) leftHand = part;
					if (partId.contentEquals("rightHand")) rightHand = part;
				}
			}
			
			//Direction handling: Gravity only
			knight.DirectionAndMovement(0, 0, false);

			if (pelvis != null) {
				//Default
				pelvisPosition = new Vector(0,0.23,0);
				//Set position based on current time
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(1705))) {
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(2205))) {
					pelvisPosition = new Vector(0, .23 + (.00086 * (knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli() + 1705)), 0);
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(3005))) {
					pelvisPosition = new Vector(0, -.2, 0);
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(3555))) {
					pelvisPosition = new Vector(0, -.2 + (.00072 * (knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli() + 3005)), 0);
				}
				else {
					pelvisPosition = new Vector(0, -1.15, 0);
				}
				
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				
				knight.legacyAnimate(pelvis, 0, 0, 0, -20, 0, 0, 0, 155);
				knight.legacyAnimate(pelvis, -20, 0, 0, 50, 0, 0, 2205, 3005);
				knight.legacyAnimate(pelvis, 50, 0, 0, 90, 0, 0, 3005, 3905);
				
				//Sounds
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(105)) && knight.soundIsOn("lothricknight.death")) {
					knight.playSound("lothricknight.death", knight.main.getLocation(), 3, 1, 1);
					knight.soundOff("lothricknight.death");
				}
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(2600)) && Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(2755)) && knight.soundIsOn("lothricknight.deathknee")) {
					knight.playSound("lothricknight.deathknee", knight.main.getLocation(), 3, 1, 1);
					knight.soundOff("lothricknight.deathknee");
				}
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(3900)) && Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(3955)) && knight.soundIsOn("lothricknight.deathland")) {
					knight.playSound("lothricknight.deathland", knight.main.getLocation(), 3, 1, 1);
					knight.soundOff("lothricknight.deathland");
				}
			}
			
			//Mills: 155 to 355 to 555 to 1155 to 1705 to 2205 to 2555 to 3055 to 3555 to 3905
			//Words: nod up to nod down to nod up to slowly start falling to knees to falling to shake around to tilting forward to face palming to bounce to land
			
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			knight.legacyAnimate(chest, 0, 0, 0, 20, 0, 0, 0, 155);
			knight.legacyAnimate(chest, 20, 0, 0, -20, 0, 0, 155, 355);
			knight.legacyAnimate(chest, -20, 0, 0, -20, -15, 0, 355, 555);
			knight.legacyAnimate(chest, -20, -15, 0, -10, 0, 0, 555, 1155);
			knight.legacyAnimate(chest, -10, 0, 0, -10, 0, 0, 1155, 1705);
			knight.legacyAnimate(chest, -10, 0, 0, 0, 0, 0, 1705, 2205);
			knight.legacyAnimate(chest, 0, 0, 0, 40, 0, 0, 2205, 3555);
			knight.legacyAnimate(chest, 40, 0, 0, 100, 0, 0, 3555, 3905);
			knight.legacyAnimate(chest, 100, 0, 0, 80, 0, 0, 3905, 4205);
			knight.legacyAnimate(chest, 80, 0, 0, 75, 0, 0, 4205, 4505);
			knight.legacyAnimate(chest, 75, 0, 0, 100, 0, 0, 4505, 5205);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(head, 20, 0, 0, 20, 0, 0, 0, 155);
			knight.legacyAnimate(head, 20, 0, 0, -60, 0, 0, 155, 355);
			knight.legacyAnimate(head, -60, 0, 0, 5, 0, 0, 355, 555);
			knight.legacyAnimate(head, 5, 0, 0, -55, -20, 0, 555, 1155);
			knight.legacyAnimate(head, -55, -20, 0, -30, -10, 0, 1155, 1705);
			knight.legacyAnimate(head, -30, -10, 0, -20, -5, 0, 1705, 2205);
			knight.legacyAnimate(head, -20, -5, 0, 30, 0, 0, 2205, 2555);
			//Extra two head bumps
			knight.legacyAnimate(head, 30, 0, 0, 0, -5, 0, 2555, 2755);
			knight.legacyAnimate(head, 0, -5, 0, 30, -10, 0, 2755, 3055);
			//
			knight.legacyAnimate(head, 30, -10, 0, 60, 0, -15, 3055, 3555);
			knight.legacyAnimate(head, 60, 0, -15, 85, 0, -10, 3555, 3905);
			knight.legacyAnimate(head, 60, 0, -15, 70, 0, -10, 3905, 4205);
			knight.legacyAnimate(head, 60, 0, -15, 70, 0, -10, 5205, 4205);
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			knight.legacyAnimate(cape, 20, 0, 0, 20, 0, 60, 0, 155);
			knight.legacyAnimate(cape, 20, 0, 60, 20, 0, 0, 155, 1005);
			knight.legacyAnimate(cape, 20, 0, 0, 40, 0, 0, 2700, 3555);
			knight.legacyAnimate(cape, 40, 0, 0, 85, 0, 0, 3555, 3905);
			knight.legacyAnimate(cape, 85, 0, 0, 100, 0, 0, 3905, 4205);
			knight.legacyAnimate(cape, 100, 0, 0, 110, 0, 0, 4205, 4505);
			knight.legacyAnimate(cape, 110, 0, 0, 100, 0, 0, 4505, 4705);
			knight.legacyAnimate(cape, 100, 0, 0, 90, 0, 0, 4705, 5205);
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftThigh, 0, 0, 0, -15, -15, -10, 0, 155);
			knight.legacyAnimate(leftThigh, -15, -15, -10, -25, -15, -10, 155, 355);
			knight.legacyAnimate(leftThigh, -25, -15, -10, -15, -15, -10, 355, 555);
			knight.legacyAnimate(leftThigh, -15, -15, -10, -25, -15, -10, 555, 1155);
			knight.legacyAnimate(leftThigh, -25, -15, -10, -15, -15, -10, 1155, 1705);
			knight.legacyAnimate(leftThigh, -15, -15, -10, -10, -15, -10, 1705, 2205);
			knight.legacyAnimate(leftThigh, -10, -15, -10, -10, -15, -5, 2205, 3555);
			knight.legacyAnimate(leftThigh, -15, -15, -5, 120, 15, 0, 3555, 3905);
			knight.legacyAnimate(leftThigh, 120, 15, 0, 140, 15, 0, 3905, 4205);
			knight.legacyAnimate(leftThigh, 140, 15, 0, 90, 5, 0, 4205, 5205);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightThigh, 0, 0, 0, -15, 25, 10, 0, 155);
			knight.legacyAnimate(rightThigh, -15, 25, 10, -25, 25, 10, 155, 355);
			knight.legacyAnimate(rightThigh, -25, 25, 10, -15, 25, 10, 355, 555);
			knight.legacyAnimate(rightThigh, -15, 25, 10, -25, 25, 10, 555, 1155);
			knight.legacyAnimate(rightThigh, -25, 25, 10, -15, 25, 10, 1155, 1705);
			knight.legacyAnimate(rightThigh, -15, 25, 10, -15, 25, -10, 1705, 2205);
			knight.legacyAnimate(rightThigh, -15, 25, -0, 20, 5, 10, 2205, 3555);
			knight.legacyAnimate(rightThigh, -15, 5, 5, 100, -10, 0, 3555, 3905);
			knight.legacyAnimate(rightThigh, 100, -10, 0, 120, -20, 0, 3905, 4205);
			knight.legacyAnimate(rightThigh, 120, -20, 0, 110, -15, 0, 4205, 4505);
			knight.legacyAnimate(rightThigh, 110, -15, 0, 85, -15, 0, 4705, 5205);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftCalf, 0, 0, 0, 15, -5, 0, 0, 155);
			knight.legacyAnimate(leftCalf, 15, -5, -10, 25, -5, -10, 155, 355);
			knight.legacyAnimate(leftCalf, 25, -5, -10, -5, -5, -10, 355, 555);
			knight.legacyAnimate(leftCalf, -5, -5, -10, 25, -5, -10, 555, 1155);
			knight.legacyAnimate(leftCalf, 25, -5, -10, 15, -5, -10, 1155, 1705);
			knight.legacyAnimate(leftCalf, -15, -5, -10, 90, 10, 0, 1705, 2205);
			knight.legacyAnimate(leftCalf, 90, 10, 0, 160, 0, -20, 3555, 3905);
			knight.legacyAnimate(leftCalf, 90, 10, 0, 160, 0, -20, 3555, 3905);
			knight.legacyAnimate(leftCalf, 160, 0, -20, 170, 0, -20, 3905, 4205);
			knight.legacyAnimate(leftCalf, 170, 0, -20, 160, 0, -20, 4205, 4505);
			knight.legacyAnimate(leftCalf, 160, 0, -20, 70, 0, -20, 4505, 5205);
			
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightCalf, 0, 0, 0, 15, 5, 0, 0, 155);
			knight.legacyAnimate(rightCalf, 15, 5, 0, 25, 10, 0, 155, 355);
			knight.legacyAnimate(rightCalf, 25, 10, 0, 15, 5, 0, 355, 555);
			knight.legacyAnimate(rightCalf, 15, 25, 10, 25, 10, 0, 555, 1155);
			knight.legacyAnimate(rightCalf, 25, 10, 0, 15, 5, 0, 1155, 1705);
			knight.legacyAnimate(rightCalf, 15, 5, 0, 90, -10, 0, 1705, 2205);
			knight.legacyAnimate(rightCalf, 90, -10, 0, 120, 0, -10, 3555, 3905);
			knight.legacyAnimate(rightCalf, 120, 0, -10, 130, 0, -10, 3905, 4205);
			knight.legacyAnimate(rightCalf, 130, 0, -10, 120, 0, -10, 4405, 4605);
			knight.legacyAnimate(rightCalf, 120, 0, -10, 80, 0, -10, 4705, 5205);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, 0, 0, 0, -20, 0, 0, 155);
			knight.legacyAnimate(leftFoot, 0, -20, 0, 135, -10, 0, 1705, 2205);
			knight.legacyAnimate(leftFoot, 135, -10, 0, 220, 0, -15, 3555, 3905);
			knight.legacyAnimate(leftFoot, 220, 0, -15, 130, 0, 0, 4505, 4805);
			
			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightFoot, 0, 0, 0, 0, 30, 0, 0, 155);
			knight.legacyAnimate(rightFoot, 0, 30, 0, 135, 10, 0, 1705, 2205);
			knight.legacyAnimate(rightFoot, 135, 10, 0, 180, 0, 0, 3555, 3905);
			knight.legacyAnimate(rightFoot, 180, 10, 0, 145, 0, 0, 4705, 5205);
			
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftElbow, 0, 0, 0, 20, 0, -30, 0, 155);
			knight.legacyAnimate(leftElbow, 20, 0, -30, 30, 0, -60, 155, 355);
			knight.legacyAnimate(leftElbow, 30, 0, -60, 20, 0, -25, 355, 555);
			knight.legacyAnimate(leftElbow, 20, 0, -25, 40, 0, -35, 555, 1155);
			knight.legacyAnimate(leftElbow, 40, 0, -35, 0, 0, -20, 1155, 1705);
			knight.legacyAnimate(leftElbow, 0, 0, -20, 0, 0, -30, 1705, 2205);
			knight.legacyAnimate(leftElbow, 0, 0, -30, 0, 0, -10, 2205, 2555);
			knight.legacyAnimate(leftElbow, 0, 0, -10, 40, 0, -20, 2705, 3555);
			knight.legacyAnimate(leftElbow, 40, 0, -20, 85, 45, 0, 3555, 3905);
			knight.legacyAnimate(leftElbow, 85, 45, 0, 100, 45, 0, 3905, 4205);
			knight.legacyAnimate(leftElbow, 100, 45, 0, 110, 45, 0, 4205, 4505);
			knight.legacyAnimate(leftElbow, 110, 45, 0, 100, 45, 0, 4505, 4705);
			knight.legacyAnimate(leftElbow, 100, 45, 0, 85, 45, 0, 4705, 5005);

			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, 0, 0, 0, 30, 0, 30, 0, 155);
			knight.legacyAnimate(rightElbow, 30, 0, 30, 30, 0, 40, 155, 355);
			knight.legacyAnimate(rightElbow, 30, 0, 40, 20, 0, 20, 355, 555);
			knight.legacyAnimate(rightElbow, 20, 0, 20, 20, 0, 10, 555, 1155);
			knight.legacyAnimate(rightElbow, 20, 0, 10, 10, 15, 0, 1155, 1705);
			//
			knight.legacyAnimate(rightElbow, 10, 15, 0, 40, 0, 30, 2705, 3555);
			knight.legacyAnimate(rightElbow, 40, 0, 30, 80, 0, 30, 3555, 3905);
			knight.legacyAnimate(rightElbow, 80, 0, 30, 110, 0, 30, 3905, 4205);
			knight.legacyAnimate(rightElbow, 110, 0, 30, 120, 0, 30, 4205, 4505);
			knight.legacyAnimate(rightElbow, 120, 0, 30, 110, 0, 30, 4605, 4905);
			knight.legacyAnimate(rightElbow, 110, 0, 30, 80, 0, 30, 4905, 5205);
			
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftArm, 0, 0, 0, -10, 0, -30, 0, 155);
			knight.legacyAnimate(leftArm, -10, 0, -30, 30, 0, -60, 155, 355);
			knight.legacyAnimate(leftArm, 30, 0, -60, -10, 0, -60, 355, 555);
			knight.legacyAnimate(leftArm, -10, 0, -60, -20, 0, -60, 555, 1155);
			knight.legacyAnimate(leftArm, -20, 0, -60, -20, 0, -25, 1155, 1705);
			//
			knight.legacyAnimate(leftArm, -20, 0, -25, -20, 0, -55, 1705, 2205);
			//
			knight.legacyAnimate(leftArm, -20, 0, -55, -20, 0, -15, 2205, 2705);
			knight.legacyAnimate(leftArm, -20, 0, -15, 40, 0, -20, 2705, 3555);
			knight.legacyAnimate(leftArm, 40, 0, -20, 90, 60, 0, 3555, 3905);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightArm, 0, 0, 0, -20, 0, 15, 0, 155);
			knight.legacyAnimate(rightArm, -20, 0, 15, -20, 0, 50, 155, 355);
			knight.legacyAnimate(rightArm, -20, 0, 15, -20, 0, 40, 355, 555);
			knight.legacyAnimate(rightArm, -20, 0, 40, -20, 0, 50, 555, 1155);
			knight.legacyAnimate(rightArm, -20, 0, 50, -10, 0, 60, 1155, 1705);
			knight.legacyAnimate(rightArm, -10, 0, 60, -20, 0, 35, 1705, 2205);
			knight.legacyAnimate(rightArm, -20, 0, 35, -20, 0, 25, 2205, 2555);
			knight.legacyAnimate(rightArm, -20, 0, 25, -20, 0, 15, 2555, 2705);
			knight.legacyAnimate(rightArm, -20, 0, 15, 40, 0, 20, 2705, 3555);
			knight.legacyAnimate(rightArm, 40, 0, 45, 0, 20, 90, 3555, 3905);
			knight.legacyAnimate(rightArm, 0, 20, 80, 0, 20, 100, 3905, 4205);
			knight.legacyAnimate(rightArm, 0, 20, 100, 0, 20, 110, 4205, 4405);
			knight.legacyAnimate(rightArm, 0, 20, 110, 0, 20, 100, 4505, 4605);
			knight.legacyAnimate(rightArm, 0, 20, 100, 0, 20, 70, 4605, 4805);
			
			leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftHand, 0, 0, 0, 0, 0, -30, 0, 155);
			knight.legacyAnimate(leftHand, 0, 0, -30, 30, 0, -50, 155, 355);
			knight.legacyAnimate(leftHand, 30, 0, -50, -10, 0, -60, 155, 355);
			knight.legacyAnimate(leftHand, -10, 0, -60, -30, 0, -60, 555, 1155);
			knight.legacyAnimate(leftHand, -30, 0, -60, -20, 0, -25, 1155, 1705);
			knight.legacyAnimate(leftHand, -20, 0, -25, -20, 0, -10, 1705, 2705);
			knight.legacyAnimate(leftHand, -20, 0, -10, 40, 0, -10, 2705, 3555);
			knight.legacyAnimate(leftHand, -40, 0, -10, -70, 0, -10, 2705, 3555);
			knight.legacyAnimate(leftHand, -90, 0, -10, -100, 0, -10, 3905, 4205);
			knight.legacyAnimate(leftHand, -100, 0, -10, -110, 0, -10, 4205, 4505);
			knight.legacyAnimate(leftHand, -110, 0, -10, -100, 0, -10, 4505, 4805);
			knight.legacyAnimate(leftHand, -100, 0, -10, -70, 0, -10, 4805, 4905);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightHand, 0, 0, 0, 15, 0, 0, 0, 155);
			knight.legacyAnimate(rightHand, 15, 0, 0, -20, 0, 60, 155, 355);
			knight.legacyAnimate(rightHand, -20, 0, 60, -20, 0, 40, 355, 555);
			knight.legacyAnimate(rightHand, -20, 0, 40, -20, 0, 50, 555, 1155);
			knight.legacyAnimate(rightHand, -20, 0, 50, -10, 0, 60, 1155, 1705);
			knight.legacyAnimate(rightHand, -10, 0, 60, -20, 0, 35, 1705, 2205);
			knight.legacyAnimate(rightHand, -20, 0, 35, -20, 0, 25, 2555, 2705);
			knight.legacyAnimate(rightHand, -20, 0, 25, 40, 0, 45, 2705, 3555);
			knight.legacyAnimate(rightHand, 40, 0, 45, 0, 20, 90, 3555, 3905);
			
			knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(sword, 0, 0, 0, 10, 10, 0, 0, 155);
			knight.legacyAnimate(sword, 10, 10, 0, 20, 5, 0, 155, 355);
			knight.legacyAnimate(sword, 20, 5, 0, 45, 45, 0, 355, 555);
			knight.legacyAnimate(sword, 45, 45, 0, 5, 0, 0, 555, 1155);
			knight.legacyAnimate(sword, 5, 0, 0, 5, 10, 0, 1155, 1705);
			knight.legacyAnimate(sword, 5, 10, 0, -40, 0, -90, 3555, 3905);
			knight.legacyAnimate(sword, -40, 0, -90, -40, 0, -80, 4005, 4505);
			knight.legacyAnimate(sword, -40, 0, -80, -40, 0, -90, 4505, 4705);
			
			knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(shield, 0, 20, -30, 0, 20, -30, 0, 155);
			knight.legacyAnimate(shield, 0, 20, -30, 0, 20, -30, 155, 355);
			knight.legacyAnimate(shield, 0, 20, -30, 0, -15, -50, 355, 555);
			knight.legacyAnimate(shield, 0, -15, -50, -30, -45, -40, 555, 1155);
			knight.legacyAnimate(shield, -30, -45, -40, 0, -30, -25, 1155, 1705);
			knight.legacyAnimate(shield, 0, -30, -25, 0, 25, -15, 1705, 2705);
			knight.legacyAnimate(shield, 0, 25, -15, 0, 15, -15, 2705, 3555);
			knight.legacyAnimate(shield, 0, 15, -15, -40, 0, -75, 3555, 3905);
			
		} catch (NullPointerException event) {}
	}
}