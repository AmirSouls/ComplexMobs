package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class ShieldBash {
	
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
				
				knight.DirectionAndMovementTimed(distance * 0.14, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 0, 705, true);
				if (distance > 3) knight.DirectionAndMovementTimed(0.6, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 700, 755, true);
				else knight.DirectionAndMovementTimed(0, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 705, 805, true);
				
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(1200))) {
					ResetTimers.reset(knight);
					knight.isAttacking = false;
						
					//Re-enable all sounds
					knight.enableAllSounds();
				}
			}
			
			//Pelvis
			//Default position to this
			pelvisPosition = new Vector(0,0.5,0);
			
			//Set position based on current time
			if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(150))) {
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(600))) {
				pelvisPosition = new Vector(0, .5 + (.001 * (knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(1500))) {
				pelvisPosition = new Vector(0, -.1, 0);
			}
			pelvisPosition.rotateAroundY(-yaw);
			pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
			knight.legacyAnimate(pelvis, -10, -20, 0, -20, -20, 0, 0, 655);
			knight.legacyAnimate(pelvis, -20, -20, 0, 0, 80, 0, 655, 755);
			
			//Chest and head
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			knight.legacyAnimate(chest, -10, -20, 0, -20, -20, 0, 0, 655);
			knight.legacyAnimate(chest, -20, -20, 0, 20, 80, 0, 655, 755);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, knight);	
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			knight.legacyAnimate(cape, 10, 0, 0, 30, 0, -90, 0, 505);
			
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftElbow, 0, 0, -20, 10, 0, -70, 0, 305);
			knight.legacyAnimate(leftElbow, 10, 0, -70, 40, 0, -110, 305, 655);
			knight.legacyAnimate(leftElbow, 40, 0, -110, 30, 0, 0, 655, 755);
			
			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, 0, 0, 15, -10, 0, 15, 0, 305);
			knight.legacyAnimate(rightElbow, -10, 0, 15, 75, 0, 0, 305, 655);
		
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftArm, 0, 0, -10, -20, 0, 130, 0, 305);
			knight.legacyAnimate(leftArm, -20, 0, 130, 10, 0, 150, 305, 655);
			knight.legacyAnimate(leftArm, 10, 0, 150, -110, 0, 180, 655, 755);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightArm, 0, 0, 10, -15, 0, 10, 0, 305);
			knight.legacyAnimate(rightArm, -15, 0, 10, 50, -40, 0, 305, 655);
			
			leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftHand, 0, 0, -10, -20, 0, 130, 0, 305);
			knight.legacyAnimate(leftHand, -20, 0, 130, 10, 0, 150, 305, 655);
			knight.legacyAnimate(leftHand, 10, 0, 150, -60, 0, 180, 655, 755);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightHand, 0, 0, 10, -15, 0, 10, 0, 305);
			knight.legacyAnimate(rightHand, -15, 0, 10, 50, -40, 0, 305, 655);
			
			knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(sword, 80, 20, 70, 0, -20, 60, 0, 655);
			knight.legacyAnimate(sword, 0, -20, 60, 80, 20, 70, 655, 1000);
			
			Vector shieldPosition = knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(shield, 0, 0, 0, -110, 80, 0, 0, 305);
			knight.legacyAnimate(shield, -110, 80, 0, -140, 100, 0, 305, 650);
			knight.legacyAnimate(shield, -140, 100, 0, -170, 80, 0, 655, 755);
			
			//Legs TODO Really need to do this
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftThigh, 0, 0, 0, 30, -45, -30, 0, 155);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightThigh, 0, 0, 0, -45, 30, 0, 0, 305);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftCalf, 0, 0, 0, 40, -45, -40, 0, 155);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightCalf, 0, 0, 0, 15, 60, 0, 0, 205);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, -45, 0, 30, 0, 0, 0, 305);
		
			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			//Sounds
			if (Instant.now().isAfter(knight.animationTimer.get(sword).plusMillis(650)) && Instant.now().isBefore(knight.animationTimer.get(sword).plusMillis(805))) {
				if (knight.soundIsOn("lothricknight.shieldbash")) {
					//Shield bash sound
					knight.playSound("lothricknight.slash", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.shieldbash");
				}
				
				if (knight.soundIsOn("lothricknight.walk")) {
					//Step sound
					knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.walk");
				}
			}
			
			//Attack frames: Appears as 9 in 60fps, but is really 3 in game tick speed.
			knight.DamageAreaShield(knight, shield, 1, knight.animationTimer.get(shield), 600, 805, shieldPosition, yaw, false, 9, .8, .2);
			
		} catch (NullPointerException event) {}
	}
}