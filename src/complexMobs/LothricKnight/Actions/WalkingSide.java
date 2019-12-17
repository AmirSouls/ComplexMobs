package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.GoBtwn;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.LothricKnight.SpecialAnimations.PelvisZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class WalkingSide {
	
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
				knight.DirectionAndMovement(.06, -90, true);
			}
			
			
			if (pelvis != null) {
				pelvisPosition = new Vector(0,0.4,0);
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				
				//Animation
				//Special pelvis animation
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			//Chest, head and cape
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			GoBtwn.animate(chest, chest.getHeadPose(), 10, 10, 15, 15, 0, 0, 30.0);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, knight);
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			GoBtwn.animate(cape, cape.getHeadPose(), 20, 20, 35, 35, 0, 0, 30.0);
			
			//Arms
			if (knight.shieldUp) {
				leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftElbow, leftElbow.getHeadPose(), 15, 15, 0, 0, -10, -10, 110.0);
				
				rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightElbow, rightElbow.getHeadPose(), 10, 10, 0, 0, 45, 45, 30.0);
				
				leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftArm, leftArm.getHeadPose(), -60, -60, 0, 0, 40, 40, 110.0);
				
				rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightArm, rightArm.getHeadPose(), 0, 0, 0, 0, 40, 40, 30.0);
				
				leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftHand, leftHand.getHeadPose(), 180, 180, 100, 100, 0, 0, 110.0);
				
				rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightHand, rightHand.getHeadPose(), 0, 0, 10);
				
				knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(sword, sword.getHeadPose(), 0, 0, 10);
				
				knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(shield, shield.getHeadPose(), 180, 180, 100, 100, 0, 0, 110);
			}
			else {
				leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftElbow, leftElbow.getHeadPose(), 0, 0, 0, 0, -20, -20, 30.0);
				
				rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightElbow, rightElbow.getHeadPose(), 30, 30, 0, 0, 30, 30, 30.0);
				
				leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftArm, leftArm.getHeadPose(), 0, 0, 0, 0, 10, 10, 30.0);
				
				rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightArm, rightArm.getHeadPose(), 20, 20, 0, 0, 40, 40, 30.0);
				
				leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftHand, leftHand.getHeadPose(), -10, -10, 10, 10, 5, 5, 30.0);
				
				rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightHand, rightHand.getHeadPose(), 20, 20, 0, 0, 40, 40, 30.0);
				
				knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(sword, sword.getHeadPose(), 40, 40, 20, 20, 20, 20, 30.0);
				
				knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(shield, shield.getHeadPose(), -15, -15, 10, 10, 10, 10, 30.0);
			}
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftThigh, -30, -20, 0, -50, 30, 0, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(leftThigh, -50, 30, 0, -30, -20, 0, 900, 1655);
			//Pause
			//Reset
			knight.reset(leftThigh, 1800);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightThigh, -30, 30, 0, 20, 55, 0, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(rightThigh, 20, 55, 0, -30, 30, 0, 900, 1655);
			//Pause
			//Reset
			knight.reset(rightThigh, 1800);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftCalf, 10, 0, -10, -10, 15, 10, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(leftCalf, -10, 15, 10, 10, 0, -10, 900, 1655);
			//Pause
			//Reset
			knight.reset(leftCalf, 1800);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightCalf, 5, 0, 10, 50, 80, 0, 0, 755);
			//Pause
			//Left
			knight.legacyAnimate(rightCalf, 50, 80, 0, 5, 0, 10, 900, 1655);
			//Pause
			//Reset
			knight.reset(rightCalf, 1800);
			
			//Step sound
			if (Instant.now().isAfter(knight.animationTimer.get(rightCalf).plusMillis(755)) && Instant.now().isBefore(knight.animationTimer.get(rightCalf).plusMillis(805))) {		
				if (knight.soundIsOn("lothricknight.walk")) knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
			}
			//Re-enable sound
			else if (Instant.now().isAfter(knight.animationTimer.get(rightCalf).plusMillis(805)) && Instant.now().isBefore(knight.animationTimer.get(rightCalf).plusMillis(1655))) {	
				knight.soundOn("lothricknight.walk");
			}
			//Step sound
			else if (Instant.now().isAfter(knight.animationTimer.get(rightCalf).plusMillis(1655)) && Instant.now().isBefore(knight.animationTimer.get(rightCalf).plusMillis(1705))) {		
				if (knight.soundIsOn("lothricknight.walk")) knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
			}
			//Re-enable sound
			else if (Instant.now().isAfter(knight.animationTimer.get(rightCalf).plusMillis(1705))) {	
				knight.soundOn("lothricknight.walk");
			}
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			GoBtwn.animate(leftFoot, leftFoot.getHeadPose(), 0, 0, 30.0);

			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			GoBtwn.animate(rightFoot, rightFoot.getHeadPose(), 0, 0, 30.0);
			
		} catch (NullPointerException event) {}
	}
}