package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.GoBtwn;
import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.LothricKnight.SpecialAnimations.PelvisZeroAnimation;
import complexMobs.Mobs.LothricKnight;


public class Running {
	
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
			knight.DirectionAndMovement(.28, 0, true);
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				
				double deltaTime = knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli();
				double heightModif = -.05;
				
				//Down
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(105))) {
					pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
				}
				//Up
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(205))) {
					deltaTime += 100;
					pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
				}
				//Sound
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(255))) {
					if (knight.soundIsOn("lothricknight.run")) knight.playSound("lothricknight.run", knight.main.getLocation(), 3, 1, 1);
				}
				//Pause and re-enable sounds
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(305))) {
					knight.soundOn("lothricknight.run");
				}
				//Down
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(405))) {
					deltaTime += 300;
					pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
				}
				//Down
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(505))) {
					deltaTime += 400;
					pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
				}
				//Sound
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(555))) {
					if (knight.soundIsOn("lothricknight.run")) knight.playSound("lothricknight.run", knight.main.getLocation(), 3, 1, 1);
				}
				//Pause and re-enable sounds
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(605))) {
					knight.soundOn("lothricknight.run");
				}
				//Reset
				else if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(605))) {
					ResetTimers.reset(knight);
				}
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				
				//Animation
				//Special pelvis animation
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			//Chest, head and cape
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(chest, 30, -5, 0, 30, 5, 0, 0, 205);
			//Left
			knight.legacyAnimate(chest, 30, 5, 0, 30, -5, 0, 305, 505);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, knight);
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			GoBtwn.animate(cape, cape.getHeadPose(), 15, 15, 0, 0, 0, 0, 10);
	
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftElbow, 40, 0, -30, 10, 0, -10, 0, 205);
			//Left
			knight.legacyAnimate(leftElbow, 10, 0, -10, 40, 0, -30, 305, 505);
			
			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightElbow, 30, 0, 30, 55, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(rightElbow, 55, 0, 45, 30, 0, 30, 305, 505);
			
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftArm, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			knight.legacyAnimate(leftArm, -30, 30, 0, 0, 0, -10, 305, 505);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightArm, 0, 0, 10, 55, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(rightArm, 55, 0, 45, 0, 0, 10, 305, 505);
			
			leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftHand, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			knight.legacyAnimate(leftHand, -30, 30, 0, 0, 0, -10, 305, 505);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightHand, 0, 0, 0, 45, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(rightHand, 45, 0, 45, 0, 0, 0, 305, 505);
			
			knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(sword, 50, 0, 35, 70, 0, 45, 0, 205);
			//Left
			knight.legacyAnimate(sword, 70, 0, 45, 50, 0, 35, 305, 505);
			
			knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(shield, 0, 30, -5, -25, 65, 0, 0, 205);
			//Left
			knight.legacyAnimate(shield, -25, 65, 0, 0, 30, -5, 305, 505);
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftThigh, -60, 0, 0, 45, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(leftThigh, 45, 0, 0, -60, 0, 0, 305, 505);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightThigh, 45, 0, 0, -60, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(rightThigh, -60, 0, 0, 45, 0, 0, 305, 505);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftCalf, -10, 0, 0, 100, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(leftCalf, 100, 0, 0, -10, 0, 0, 305, 505);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightCalf, 100, 0, 0, -10, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(rightCalf, -10, 0, 0, 100, 0, 0, 305, 505);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftFoot, 0, 0, 0, 50, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(leftFoot, 50, 0, 0, 0, 0, 0, 305, 505);

			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightFoot, 50, 0, 0, 0, 0, 0, 0, 205);
			//Left
			knight.legacyAnimate(rightFoot, 0, 0, 0, 50, 0, 0, 305, 505);
			
		} catch (NullPointerException event) {}
	}
}