package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Mobs.LothricKnight;

public class StanceThrust {
	
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

				//Charge up frames
				knight.DirectionAndMovementTimed(distance * .014, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 0, 355, true);

				//Attack frames with aim
				knight.DirectionAndMovementTimed(distance * .3, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 355, 455, true);

				//Attack frames without aim
				knight.DirectionAndMovementTimed(distance * .3, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 455, 605, false);
				
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(1600))) {
					ResetTimers.resetOffset(knight, -55);
					knight.isAttacking = false;
					
					//Re-enable all sounds
					knight.enableAllSounds();
				}
				
				//Sounds
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(350)) && Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(455))) {
					
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
			}
			
			if (pelvis != null) {
				pelvisPosition = new Vector(0,.2,0);
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				knight.legacyAnimate(pelvis, -5, 55, 10, 30, 50, 0, 0, 355);
				knight.legacyAnimate(pelvis, 30, 50, 0, 0, -90, 0, 355, 605);
			}
			
			//Chest, head, cape and shield on back
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			knight.legacyAnimate(chest, 50, 50, 40, 75, 35, 30, 0, 355);
			knight.legacyAnimate(chest, 75, 35, 30, 0, -90, 0, 355, 605);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(head, 30, 0, 0, 60, 10, 0, 0, 355);
			knight.legacyAnimate(head, 60, 10, 0, 30, -20, 0, 355, 605);
			
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			knight.legacyAnimate(cape, -35, 70, -30, 90, 35, 30, 0, 355);
			knight.legacyAnimate(cape, 90, 35, 30, 0, -50, 30, 355, 605);
			
			knight.partPosition(shield, chestPosition, chest.getHeadPose(), new Vector(-.4,.9,-.1), knight.main.getLocation(), yaw);
			knight.legacyAnimate(shield, 180, -30, -0, 180, 40, -70, 0, 355);
			knight.legacyAnimate(shield, 180, 40, -70, 150, 180, 30, 355, 605);
			
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftElbow, -90, 70, 0, 0, 100, 40, 0, 355);
			knight.legacyAnimate(leftElbow, 0, 100, 40, 90, -50, -90, 355, 605);
			
			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, -145, 160, 0, 125, 0, -20, 0, 355);
			knight.legacyAnimate(rightElbow, 125, 0, -20, -90, 0, 0, 355, 605);
		
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftArm, -130, 160, 0, 90, -60, 50, 0, 355);
			knight.legacyAnimate(leftArm, 90, -60, 50, 20, 0, 0, 355, 605);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightArm, -40, 0, 45, -20, 0, 70, 0, 355);
			knight.legacyAnimate(rightArm, 20, 0, 70, -90, -10, 0, 355, 605);
			
			knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftHand, 120, -40, 0, 90, -60, 50, 0, 355);
			knight.legacyAnimate(leftHand, 90, -60, 50, 0, 0, 0, 355, 605);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightHand, 0, 0, 45, 20, 0, 70, 0, 355);
			knight.legacyAnimate(rightHand, 20, 0, 70, -90, 0, 0, 355, 605);
			
			Vector swordPosition = knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(sword, 7, -15, 60, 30, -15, 60, 0, 355);
			knight.legacyAnimate(sword, 30, -15, 60, 0, 0, 0, 355, 605);
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftThigh, -10, 0, 0, 30, 40, 0, 0, 355);
			knight.legacyAnimate(leftThigh, 30, 40, 0, -30, -100, 30, 355, 605);

			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightThigh, -25, 110, -60, 50, 0, 0, 0, 355);
			knight.legacyAnimate(rightThigh, 50, 0, 0, 15, -70, 0, 355, 605);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftCalf, 10, -10, -15, 75, 30, 0, 0, 355);
			knight.legacyAnimate(leftCalf, 75, 30, 0, -30, -150, 0, 355, 605);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightCalf, 90, 60, 90, 75, 50, 0, 0, 355);
			knight.legacyAnimate(rightCalf, 0, 0, 0, 30, -55, 0, 355, 605);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, 10, 0, 50, 30, 0, 0, 355);
			knight.legacyAnimate(leftFoot, 50, 30, 0, 0, -90, 0, 355, 605);
		
			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightFoot, 0, 70, 0, 75, 50, 10, 0, 355);
			knight.legacyAnimate(rightFoot, 75, 50, 10, 0, -45, 0, 355, 605);
			
			knight.DamageArea(sword, 2, .5, knight.animationTimer.get(sword), 350, 655, swordPosition, yaw, true, 22, .7, .4);
			
		} catch (NullPointerException event) {}
	}
}