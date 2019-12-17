package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.DamageArea;
import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Methods.DirectionAndMovement;
import complexMobs.Methods.LegacyAnimate;
import complexMobs.Methods.PartPositioning;
import complexMobs.Methods.PlaySound;
import complexMobs.Methods.ToggleSound;
import complexMobs.Mobs.LothricKnight;

public class LeftSlash {
	
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
					if (partId.contentEquals("cape")) cape = part;
				}
			}
			
			//Direction handling
			{
				LivingEntity target = knight.target;
				double distance = target.getLocation().distance(knight.main.getLocation());
				
				double moveMult = 0;
				if (distance > 2) moveMult = .12;
				knight.DirectionAndMovementTimed(distance * moveMult, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 0, 605, true);
				
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(755))) {
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
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(150))) {
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(600))) {
					pelvisPosition = new Vector(0, .5 + (.001 * (knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(1000))) {
					pelvisPosition = new Vector(0, -.1, 0);
				}
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				knight.legacyAnimate(pelvis, 0, 0, 0, 0, -80, 0, 500, 605);
				knight.legacyAnimate(pelvis, 0, 0, 0, 0, 0, 0, 0, 505);
			}
			
			//Chest and head
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			knight.legacyAnimate(chest, 0, 0, 0, 14, 14, 0, 0, 355);
			knight.legacyAnimate(chest, 14, 14, 0, 14, -80, 0, 350, 505);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, knight);	
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			knight.legacyAnimate(cape, 10, 0, 0, 30, 0, -90, 0, 505);
			knight.legacyAnimate(cape, 30, 0, -90, 100, -80, 0, 550, 655);
			knight.legacyAnimate(cape, 100, -80, 0, 10, -70, 0, 650, 1000);
			
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, 0, 0, 20, 0, 180, 110, 0, 155);
			knight.legacyAnimate(rightElbow, 0, 180, 110, 0, 180, 135, 150, 305);
			knight.legacyAnimate(rightElbow, 0, 180, 135, 0, -70, 105, 300, 405);
			knight.legacyAnimate(rightElbow, 0, -70, 105, -90, -45, 90, 400, 505);
			knight.legacyAnimate(rightElbow, -90, -45, 90, -80, -80, 80, 500, 605);
			knight.legacyAnimate(rightElbow, -80, -80, 80, 0, -80, 0, 600, 655);
		
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftArm, 0, 0, -20, 0, 0, -20, 0, 1000);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightArm, 0, 0, 20, -20, 180, 145, 0, 155);
			knight.legacyAnimate(rightArm, -20, 180, 145, -20, 180, 210, 150, 305);
			knight.legacyAnimate(rightArm, -20, 180, 210, -20, 180, 185, 300, 405);
			knight.legacyAnimate(rightArm,-20, 180, 185, -135, 0, 0, 400, 505);
			knight.legacyAnimate(rightArm, -135, 0, 0, -50, 20, -80, 500, 605);
			knight.legacyAnimate(rightArm, -50, 20, -80, 0, -30, -70, 600, 655);
			
			leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftHand, 0, 0, -20, 0, 0, -20, 0, 1000);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightHand, 0, 0, 20, -20, 180, 145, 0, 155);
			knight.legacyAnimate(rightHand, -20, 180, 145, 0, 180, 210, 150, 305);
			knight.legacyAnimate(rightHand, -20, 180, 210, -20, 180, 185, 300, 405);
			knight.legacyAnimate(rightHand, -20, 180, 185, -90, 0, -90, 400, 505);
			knight.legacyAnimate(rightHand, -90, 0, -90, -50, 20, -80, 500, 605);
			knight.legacyAnimate(rightHand, -50, 20, -80, 0, -30, -70, 600, 655);
			
			Vector swordPosition = knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(sword, 0, 0, 0, -70, 60, 70, 0, 155);
			knight.legacyAnimate(sword, -70, 60, 70, 15, -95, 0, 150, 305);
			knight.legacyAnimate(sword, 15, -95, 0, 185, 30, -10, 300, 355);
			knight.legacyAnimate(sword, 185, 30, -10, -90, 0, -40, 400, 455);
			knight.legacyAnimate(sword, -90, 0, -40, -20, 15, -55, 500, 605);;
			knight.legacyAnimate(sword, -20, 15, -55, 100, -30, -70, 600, 655);;
			
			knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(shield, 10, 50, 0, 10, 50, 0, 0, 1000);
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftThigh, 0, 0, 0, 30, -45, -30, 0, 155);
			knight.legacyAnimate(leftThigh, 30, -45, -30, 50, -45, -50, 150, 355);
			knight.legacyAnimate(leftThigh, 50, -45, -50, -75, -110, 0, 350, 605);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightThigh, 0, 0, 0, -45, 30, 0, 0, 305);
			knight.legacyAnimate(rightThigh, -45, 30, 0, -75, -15, 0, 300, 605);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftCalf, 0, 0, 0, 40, -45, -40, 0, 155);
			knight.legacyAnimate(leftCalf, 40, -45, -40, 60, -10, 0, 150, 355);
			knight.legacyAnimate(leftCalf, 60, -10, 0, 10, -110, 0, 350, 605);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightCalf, 0, 0, 0, 15, 60, 0, 0, 205);
			knight.legacyAnimate(rightCalf, 15, 60, 0, 15, 0, 5, 200, 605);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, -45, 0, 30, 0, 0, 0, 305);
			knight.legacyAnimate(leftFoot, 0, 30, 0, 0, -90, 0, 300, 605);
		
			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			//Sounds
			if (Instant.now().isAfter(knight.animationTimer.get(sword).plusMillis(500)) && Instant.now().isBefore(knight.animationTimer.get(sword).plusMillis(555))) {
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
			knight.DamageArea(sword, 2, .5, knight.animationTimer.get(sword), 495, 655, swordPosition, yaw, true, 12, .7, .2);
			
		} catch (NullPointerException event) {}
	}
}