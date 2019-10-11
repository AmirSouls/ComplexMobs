package complexMobs.LothricKnight;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.DirectionAndMovement;
import complexMobs.LothricKnight.Methods.FromTo;
import complexMobs.LothricKnight.Methods.PartPositioning;
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
			DirectionAndMovement.normal(knight, 0, 0, false);

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
				
				FromTo.animate(pelvis, 0, 0, 0, -20, 0, 0, 0, 155);
				FromTo.animate(pelvis, -20, 0, 0, 50, 0, 0, 2205, 3005);
				FromTo.animate(pelvis, 50, 0, 0, 90, 0, 0, 3005, 3905);
				
				//Sounds
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(55)) && ToggleSound.isOn(knight, "lothricknight.death")) {
					PlaySound.normal("lothricknight.death", knight.main.getLocation(), 3, 1, 1);
					ToggleSound.off(knight, "lothricknight.death");
				}
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(2700)) && Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(2755)) && ToggleSound.isOn(knight, "lothricknight.deathknee")) {
					PlaySound.normal("lothricknight.deathknee", knight.main.getLocation(), 3, 1, 1);
					ToggleSound.off(knight, "lothricknight.deathknee");
				}
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(3900)) && Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(3955)) && ToggleSound.isOn(knight, "lothricknight.deathland")) {
					PlaySound.normal("lothricknight.deathland", knight.main.getLocation(), 3, 1, 1);
					ToggleSound.off(knight, "lothricknight.deathland");
				}
			}
			
			//Mills: 155 to 355 to 555 to 1155 to 1705 to 2205 to 2555 to 3055 to 3555 to 3905
			//Words: nod up to nod down to nod up to slowly start falling to knees to falling to shake around to tilting forward to face palming to bounce to land
			
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			FromTo.animate(chest, 0, 0, 0, 20, 0, 0, 0, 155);
			FromTo.animate(chest, 20, 0, 0, -20, 0, 0, 155, 355);
			FromTo.animate(chest, -20, 0, 0, -20, -15, 0, 355, 555);
			FromTo.animate(chest, -20, -15, 0, -10, 0, 0, 555, 1155);
			FromTo.animate(chest, -10, 0, 0, -10, 0, 0, 1155, 1705);
			FromTo.animate(chest, -10, 0, 0, 0, 0, 0, 1705, 2205);
			FromTo.animate(chest, 0, 0, 0, 40, 0, 0, 2205, 3555);
			FromTo.animate(chest, 40, 0, 0, 100, 0, 0, 3555, 3905);
			FromTo.animate(chest, 100, 0, 0, 80, 0, 0, 3905, 4205);
			FromTo.animate(chest, 80, 0, 0, 75, 0, 0, 4205, 4505);
			FromTo.animate(chest, 75, 0, 0, 100, 0, 0, 4505, 5205);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			FromTo.animate(head, 20, 0, 0, 20, 0, 0, 0, 155);
			FromTo.animate(head, 20, 0, 0, -60, 0, 0, 155, 355);
			FromTo.animate(head, -60, 0, 0, 5, 0, 0, 355, 555);
			FromTo.animate(head, 5, 0, 0, -55, -20, 0, 555, 1155);
			FromTo.animate(head, -55, -20, 0, -30, -10, 0, 1155, 1705);
			FromTo.animate(head, -30, -10, 0, -20, -5, 0, 1705, 2205);
			FromTo.animate(head, -20, -5, 0, 30, 0, 0, 2205, 2555);
			//Extra two head bumps
			FromTo.animate(head, 30, 0, 0, 0, -5, 0, 2555, 2755);
			FromTo.animate(head, 0, -5, 0, 30, -10, 0, 2755, 3055);
			//
			FromTo.animate(head, 30, -10, 0, 60, 0, -15, 3055, 3555);
			FromTo.animate(head, 60, 0, -15, 85, 0, -10, 3555, 3905);
			FromTo.animate(head, 60, 0, -15, 70, 0, -10, 3905, 4205);
			FromTo.animate(head, 60, 0, -15, 70, 0, -10, 5205, 4205);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			FromTo.animate(cape, 20, 0, 0, 20, 0, 60, 0, 155);
			FromTo.animate(cape, 20, 0, 60, 20, 0, 0, 155, 1005);
			FromTo.animate(cape, 20, 0, 0, 40, 0, 0, 2700, 3555);
			FromTo.animate(cape, 40, 0, 0, 85, 0, 0, 3555, 3905);
			FromTo.animate(cape, 85, 0, 0, 100, 0, 0, 3905, 4205);
			FromTo.animate(cape, 100, 0, 0, 110, 0, 0, 4205, 4505);
			FromTo.animate(cape, 110, 0, 0, 100, 0, 0, 4505, 4705);
			FromTo.animate(cape, 100, 0, 0, 90, 0, 0, 4705, 5205);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			FromTo.animate(leftThigh, 0, 0, 0, -15, -15, -10, 0, 155);
			FromTo.animate(leftThigh, -15, -15, -10, -25, -15, -10, 155, 355);
			FromTo.animate(leftThigh, -25, -15, -10, -15, -15, -10, 355, 555);
			FromTo.animate(leftThigh, -15, -15, -10, -25, -15, -10, 555, 1155);
			FromTo.animate(leftThigh, -25, -15, -10, -15, -15, -10, 1155, 1705);
			FromTo.animate(leftThigh, -15, -15, -10, -10, -15, -10, 1705, 2205);
			FromTo.animate(leftThigh, -10, -15, -10, -10, -15, -5, 2205, 3555);
			FromTo.animate(leftThigh, -15, -15, -5, 120, 15, 0, 3555, 3905);
			FromTo.animate(leftThigh, 120, 15, 0, 140, 15, 0, 3905, 4205);
			FromTo.animate(leftThigh, 140, 15, 0, 90, 5, 0, 4205, 5205);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			FromTo.animate(rightThigh, 0, 0, 0, -15, 25, 10, 0, 155);
			FromTo.animate(rightThigh, -15, 25, 10, -25, 25, 10, 155, 355);
			FromTo.animate(rightThigh, -25, 25, 10, -15, 25, 10, 355, 555);
			FromTo.animate(rightThigh, -15, 25, 10, -25, 25, 10, 555, 1155);
			FromTo.animate(rightThigh, -25, 25, 10, -15, 25, 10, 1155, 1705);
			FromTo.animate(rightThigh, -15, 25, 10, -15, 25, -10, 1705, 2205);
			FromTo.animate(rightThigh, -15, 25, -0, 20, 5, 10, 2205, 3555);
			FromTo.animate(rightThigh, -15, 5, 5, 100, -10, 0, 3555, 3905);
			FromTo.animate(rightThigh, 100, -10, 0, 120, -20, 0, 3905, 4205);
			FromTo.animate(rightThigh, 120, -20, 0, 110, -15, 0, 4205, 4505);
			FromTo.animate(rightThigh, 110, -15, 0, 85, -15, 0, 4705, 5205);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			FromTo.animate(leftCalf, 0, 0, 0, 15, -5, 0, 0, 155);
			FromTo.animate(leftCalf, 15, -5, -10, 25, -5, -10, 155, 355);
			FromTo.animate(leftCalf, 25, -5, -10, -5, -5, -10, 355, 555);
			FromTo.animate(leftCalf, -5, -5, -10, 25, -5, -10, 555, 1155);
			FromTo.animate(leftCalf, 25, -5, -10, 15, -5, -10, 1155, 1705);
			FromTo.animate(leftCalf, -15, -5, -10, 90, 10, 0, 1705, 2205);
			FromTo.animate(leftCalf, 90, 10, 0, 160, 0, -20, 3555, 3905);
			FromTo.animate(leftCalf, 90, 10, 0, 160, 0, -20, 3555, 3905);
			FromTo.animate(leftCalf, 160, 0, -20, 170, 0, -20, 3905, 4205);
			FromTo.animate(leftCalf, 170, 0, -20, 160, 0, -20, 4205, 4505);
			FromTo.animate(leftCalf, 160, 0, -20, 70, 0, -20, 4505, 5205);
			
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightCalf, 0, 0, 0, 15, 5, 0, 0, 155);
			FromTo.animate(rightCalf, 15, 5, 0, 25, 10, 0, 155, 355);
			FromTo.animate(rightCalf, 25, 10, 0, 15, 5, 0, 355, 555);
			FromTo.animate(rightCalf, 15, 25, 10, 25, 10, 0, 555, 1155);
			FromTo.animate(rightCalf, 25, 10, 0, 15, 5, 0, 1155, 1705);
			FromTo.animate(rightCalf, 15, 5, 0, 90, -10, 0, 1705, 2205);
			FromTo.animate(rightCalf, 90, -10, 0, 120, 0, -10, 3555, 3905);
			FromTo.animate(rightCalf, 120, 0, -10, 130, 0, -10, 3905, 4205);
			FromTo.animate(rightCalf, 130, 0, -10, 120, 0, -10, 4405, 4605);
			FromTo.animate(rightCalf, 120, 0, -10, 80, 0, -10, 4705, 5205);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			FromTo.animate(leftFoot, 0, 0, 0, 0, -20, 0, 0, 155);
			FromTo.animate(leftFoot, 0, -20, 0, 135, -10, 0, 1705, 2205);
			FromTo.animate(leftFoot, 135, -10, 0, 220, 0, -15, 3555, 3905);
			FromTo.animate(leftFoot, 220, 0, -15, 130, 0, 0, 4505, 4805);
			
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			FromTo.animate(rightFoot, 0, 0, 0, 0, 30, 0, 0, 155);
			FromTo.animate(rightFoot, 0, 30, 0, 135, 10, 0, 1705, 2205);
			FromTo.animate(rightFoot, 135, 10, 0, 180, 0, 0, 3555, 3905);
			FromTo.animate(rightFoot, 180, 10, 0, 145, 0, 0, 4705, 5205);
			
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			FromTo.animate(leftElbow, 0, 0, 0, 20, 0, -30, 0, 155);
			FromTo.animate(leftElbow, 20, 0, -30, 30, 0, -60, 155, 355);
			FromTo.animate(leftElbow, 30, 0, -60, 20, 0, -25, 355, 555);
			FromTo.animate(leftElbow, 20, 0, -25, 40, 0, -35, 555, 1155);
			FromTo.animate(leftElbow, 40, 0, -35, 0, 0, -20, 1155, 1705);
			FromTo.animate(leftElbow, 0, 0, -20, 0, 0, -30, 1705, 2205);
			FromTo.animate(leftElbow, 0, 0, -30, 0, 0, -10, 2205, 2555);
			FromTo.animate(leftElbow, 0, 0, -10, 40, 0, -20, 2705, 3555);
			FromTo.animate(leftElbow, 40, 0, -20, 85, 45, 0, 3555, 3905);
			FromTo.animate(leftElbow, 85, 45, 0, 100, 45, 0, 3905, 4205);
			FromTo.animate(leftElbow, 100, 45, 0, 110, 45, 0, 4205, 4505);
			FromTo.animate(leftElbow, 110, 45, 0, 100, 45, 0, 4505, 4705);
			FromTo.animate(leftElbow, 100, 45, 0, 85, 45, 0, 4705, 5005);

			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightElbow, 0, 0, 0, 30, 0, 30, 0, 155);
			FromTo.animate(rightElbow, 30, 0, 30, 30, 0, 40, 155, 355);
			FromTo.animate(rightElbow, 30, 0, 40, 20, 0, 20, 355, 555);
			FromTo.animate(rightElbow, 20, 0, 20, 20, 0, 10, 555, 1155);
			FromTo.animate(rightElbow, 20, 0, 10, 10, 15, 0, 1155, 1705);
			//
			FromTo.animate(rightElbow, 10, 15, 0, 40, 0, 30, 2705, 3555);
			FromTo.animate(rightElbow, 40, 0, 30, 80, 0, 30, 3555, 3905);
			FromTo.animate(rightElbow, 80, 0, 30, 110, 0, 30, 3905, 4205);
			FromTo.animate(rightElbow, 110, 0, 30, 120, 0, 30, 4205, 4505);
			FromTo.animate(rightElbow, 120, 0, 30, 110, 0, 30, 4605, 4905);
			FromTo.animate(rightElbow, 110, 0, 30, 80, 0, 30, 4905, 5205);
			
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			FromTo.animate(leftArm, 0, 0, 0, -10, 0, -30, 0, 155);
			FromTo.animate(leftArm, -10, 0, -30, 30, 0, -60, 155, 355);
			FromTo.animate(leftArm, 30, 0, -60, -10, 0, -60, 355, 555);
			FromTo.animate(leftArm, -10, 0, -60, -20, 0, -60, 555, 1155);
			FromTo.animate(leftArm, -20, 0, -60, -20, 0, -25, 1155, 1705);
			//
			FromTo.animate(leftArm, -20, 0, -25, -20, 0, -55, 1705, 2205);
			//
			FromTo.animate(leftArm, -20, 0, -55, -20, 0, -15, 2205, 2705);
			FromTo.animate(leftArm, -20, 0, -15, 40, 0, -20, 2705, 3555);
			FromTo.animate(leftArm, 40, 0, -20, 90, 60, 0, 3555, 3905);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightArm, 0, 0, 0, -20, 0, 15, 0, 155);
			FromTo.animate(rightArm, -20, 0, 15, -20, 0, 50, 155, 355);
			FromTo.animate(rightArm, -20, 0, 15, -20, 0, 40, 355, 555);
			FromTo.animate(rightArm, -20, 0, 40, -20, 0, 50, 555, 1155);
			FromTo.animate(rightArm, -20, 0, 50, -10, 0, 60, 1155, 1705);
			FromTo.animate(rightArm, -10, 0, 60, -20, 0, 35, 1705, 2205);
			FromTo.animate(rightArm, -20, 0, 35, -20, 0, 25, 2205, 2555);
			FromTo.animate(rightArm, -20, 0, 25, -20, 0, 15, 2555, 2705);
			FromTo.animate(rightArm, -20, 0, 15, 40, 0, 20, 2705, 3555);
			FromTo.animate(rightArm, 40, 0, 45, 0, 20, 90, 3555, 3905);
			FromTo.animate(rightArm, 0, 20, 80, 0, 20, 100, 3905, 4205);
			FromTo.animate(rightArm, 0, 20, 100, 0, 20, 110, 4205, 4405);
			FromTo.animate(rightArm, 0, 20, 110, 0, 20, 100, 4505, 4605);
			FromTo.animate(rightArm, 0, 20, 100, 0, 20, 70, 4605, 4805);
			
			leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			FromTo.animate(leftHand, 0, 0, 0, 0, 0, -30, 0, 155);
			FromTo.animate(leftHand, 0, 0, -30, 30, 0, -50, 155, 355);
			FromTo.animate(leftHand, 30, 0, -50, -10, 0, -60, 155, 355);
			FromTo.animate(leftHand, -10, 0, -60, -30, 0, -60, 555, 1155);
			FromTo.animate(leftHand, -30, 0, -60, -20, 0, -25, 1155, 1705);
			FromTo.animate(leftHand, -20, 0, -25, -20, 0, -10, 1705, 2705);
			FromTo.animate(leftHand, -20, 0, -10, 40, 0, -10, 2705, 3555);
			FromTo.animate(leftHand, -40, 0, -10, -70, 0, -10, 2705, 3555);
			FromTo.animate(leftHand, -90, 0, -10, -100, 0, -10, 3905, 4205);
			FromTo.animate(leftHand, -100, 0, -10, -110, 0, -10, 4205, 4505);
			FromTo.animate(leftHand, -110, 0, -10, -100, 0, -10, 4505, 4805);
			FromTo.animate(leftHand, -100, 0, -10, -70, 0, -10, 4805, 4905);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightHand, 0, 0, 0, 15, 0, 0, 0, 155);
			FromTo.animate(rightHand, 15, 0, 0, -20, 0, 60, 155, 355);
			FromTo.animate(rightHand, -20, 0, 60, -20, 0, 40, 355, 555);
			FromTo.animate(rightHand, -20, 0, 40, -20, 0, 50, 555, 1155);
			FromTo.animate(rightHand, -20, 0, 50, -10, 0, 60, 1155, 1705);
			FromTo.animate(rightHand, -10, 0, 60, -20, 0, 35, 1705, 2205);
			FromTo.animate(rightHand, -20, 0, 35, -20, 0, 25, 2555, 2705);
			FromTo.animate(rightHand, -20, 0, 25, 40, 0, 45, 2705, 3555);
			FromTo.animate(rightHand, 40, 0, 45, 0, 20, 90, 3555, 3905);
			
			PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			FromTo.animate(sword, 0, 0, 0, 10, 10, 0, 0, 155);
			FromTo.animate(sword, 10, 10, 0, 20, 5, 0, 155, 355);
			FromTo.animate(sword, 20, 5, 0, 45, 45, 0, 355, 555);
			FromTo.animate(sword, 45, 45, 0, 5, 0, 0, 555, 1155);
			FromTo.animate(sword, 5, 0, 0, 5, 10, 0, 1155, 1705);
			FromTo.animate(sword, 5, 10, 0, -40, 0, -90, 3555, 3905);
			FromTo.animate(sword, -40, 0, -90, -40, 0, -80, 4005, 4505);
			FromTo.animate(sword, -40, 0, -80, -40, 0, -90, 4505, 4705);
			
			PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			FromTo.animate(shield, 0, 20, -30, 0, 20, -30, 0, 155);
			FromTo.animate(shield, 0, 20, -30, 0, 20, -30, 155, 355);
			FromTo.animate(shield, 0, 20, -30, 0, -15, -50, 355, 555);
			FromTo.animate(shield, 0, -15, -50, -30, -45, -40, 555, 1155);
			FromTo.animate(shield, -30, -45, -40, 0, -30, -25, 1155, 1705);
			FromTo.animate(shield, 0, -30, -25, 0, 25, -15, 1705, 2705);
			FromTo.animate(shield, 0, 25, -15, 0, 15, -15, 2705, 3555);
			FromTo.animate(shield, 0, 15, -15, -40, 0, -75, 3555, 3905);
			
		} catch (NullPointerException event) {}
	}
}