package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.DamageArea;
import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Methods.DirectionAndMovement;
import complexMobs.Methods.LegacyAnimate;
import complexMobs.Methods.PartPositioning;
import complexMobs.Methods.PlaySound;
import complexMobs.Methods.ToggleSound;
import complexMobs.Mobs.LothricKnight;

public class RightSlash {
	
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
				
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(805))) {
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
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.29,0);
				
				//Set position based on current time
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(305))) {
				}
				else {
					pelvisPosition = new Vector(0, .29 + (.0001 * (knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
				}
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				knight.legacyAnimate(pelvis, 0, 0, 0, 5, -45, 0, 0, 305);
				knight.legacyAnimate(pelvis, 5, -45, 0, -5, 40, 0, 305, 505);
			}
			
			//18,526 to 18,538
			
			//Chest and head
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			knight.legacyAnimate(chest, 14, -80, 0, 20, -45, 0, 0, 305);
			knight.legacyAnimate(chest, 20, -45, 0, 55, -20, 0, 305, 505);
			knight.legacyAnimate(chest, 55, -20, 0, 55, 45, 0, 505, 655);
			knight.legacyAnimate(chest, 55, 45, 0, 55, 85, 0, 655, 805);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(head, 0, 0, 0, 20, -20, 0, 0, 305);
			knight.legacyAnimate(head, 20, -20, 0, 30, 0, 0, 305, 505);
			knight.legacyAnimate(head, 30, 0, 0, 10, 10, 0, 505, 655);
			knight.legacyAnimate(head, 10, 10, 0, 20, 20, 0, 655, 805);
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			knight.legacyAnimate(cape, 10, -70, 0, -90, -30, 110, 0, 305);
			knight.legacyAnimate(cape, -90, -30, 110, 85, -15, 0, 305, 505);
			knight.legacyAnimate(cape, 85, -15, 0, 110, 30, 90, 505, 655);
			knight.legacyAnimate(cape, 110, 30, 90, 30, 0, 0, 655, 3000);
			
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftElbow, 0, 0, 0, 40, -55, 0, 0, 305);
			knight.legacyAnimate(leftElbow, 40, -55, 0, 40, -40, 0, 305, 505);
			knight.legacyAnimate(leftElbow, 40, -40, 0, 0, 90, -20, 505, 805);
			
			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, 0, -80, 0, -50, -100, 0, 0, 305);
			knight.legacyAnimate(rightElbow, -50, -100, 0, -50, -80, 0, 305, 505);
			knight.legacyAnimate(rightElbow, -50, -80, 0, -50, -30, 0, 505, 555);
			knight.legacyAnimate(rightElbow, -50, -30, 0, -50, 5, 0, 555, 605);
			knight.legacyAnimate(rightElbow, -50, 5, 0, -50, 70, 0, 605, 655);
			knight.legacyAnimate(rightElbow, -50, 70, 0, -40, 170, 0, 655, 805);
			
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftArm, 0, 0, -20, -30, -55, 0, 0, 305);
			knight.legacyAnimate(leftArm, -30, -55, 0, -100, -55, 0, 305, 505);
			knight.legacyAnimate(leftArm, -100, -55, 0, 0, 0, 0, 505, 805);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightArm, 0, -30, -70, -70, -130, 0, 0, 305);
			knight.legacyAnimate(rightArm, -70, -130, 0, -80, -70, 0, 305, 505);
			knight.legacyAnimate(rightArm, -80, -70, 0, -80, -30, 0, 505, 555);
			knight.legacyAnimate(rightArm, -80, -30, 0, -80, 5, 0, 555, 605);
			knight.legacyAnimate(rightArm, -80, 5, 0, -60, 70, 0, 605, 655);
			knight.legacyAnimate(rightArm, -60, 70, 0, -50, 165, 0, 655, 805);
			
			leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftHand, 0, 0, -20, 0, 55, 0, 0, 305);
			knight.legacyAnimate(leftHand, 0, 55, 0, 0, 90, 0, 505, 805);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightHand, 0, -30, -70, -80, -170, 0, 0, 305);
			knight.legacyAnimate(rightHand, -80, -170, 0, -80, -80, 0, 305, 505);
			knight.legacyAnimate(rightHand, -80, -80, 0, -80, -20, 0, 505, 555);
			knight.legacyAnimate(rightHand, -80, -20, 0, -80, 5, 0, 555, 605);
			knight.legacyAnimate(rightHand, -80, 5, 0, -60, 70, 0, 605, 655);
			knight.legacyAnimate(rightHand, -60, 70, 0, -50, 160, 0, 655, 805);

			Vector swordPosition = knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(sword, 100, -30, -70, 10, -220, 0, 0, 305);
			knight.legacyAnimate(sword, 10, -220, 0, 0, -130, 0, 305, 505);
			knight.legacyAnimate(sword, 0, -130, 0, 0, -85, 0, 505, 555);
			knight.legacyAnimate(sword, 0, -85, 0, 0, -30, 0, 555, 605);
			knight.legacyAnimate(sword, 0, -30, 0, 30, 40, 0, 605, 655);
			knight.legacyAnimate(sword, 30, 40, 0, 30, 85, 0, 655, 805);
			
			
			knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(shield, 10, 50, 0, 0, 15, 0, 0, 305);
			knight.legacyAnimate(shield, 0, 15, 0, 0, 40, 10, 305, 505);
			knight.legacyAnimate(shield, 0, 40, 10, 0, 90, 10, 605, 805);
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftThigh, -50, -75, -110, -40, -45, 0, 0, 305);
			knight.legacyAnimate(leftThigh, -40, -45, 0, -50, -10, 0, 305, 505);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightThigh, -75, -15, 0, -10, 35, 0, 0, 305);
			knight.legacyAnimate(rightThigh, -10, 35, 0, 45, -75, 0, 305, 505);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftCalf, 10, -110, 0, 15, -45, 0, 0, 305);
			knight.legacyAnimate(leftCalf, 15, -45, 0, -40, -10, 0, 305, 505);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightCalf, 15, 0, 5, 20, 40, 0, 0, 305);
			knight.legacyAnimate(rightCalf, 20, 40, 0, 15, 80, -10, 305, 505);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, -90, 0, 0, -25, 0, 0, 305);
			knight.legacyAnimate(leftFoot, 0, -25, 0, 0, 15, 0, 305, 505);
		
			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightFoot, 0, 0, 0, 0, 0, 30, 0, 305);
			knight.legacyAnimate(rightFoot, 0, 30, 0, 20, 80, 0, 305, 505);
			
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
			knight.DamageArea(sword, 2, .5, knight.animationTimer.get(sword), 500, 655, swordPosition, yaw, true, 12, .7, .2);
		
		} catch (NullPointerException event) {}
	}
}