package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.GoBtwn;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.LothricKnight.SpecialAnimations.PelvisZeroAnimation;
import complexMobs.Mobs.LothricKnight;


public class WalkingBackward {
	
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
			knight.DirectionAndMovement(-.1, 0, true);
			
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				//Up
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(300))) {
					pelvisPosition = pelvisPosition.add(new Vector(0,.04,0));	
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(355))) {		
				}
				//Step sound
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(405))) {		
					if (knight.soundIsOn("lothricknight.walk")) knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
				}
				//Pause and re-enable sound
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(500))) {		
					knight.soundOn("lothricknight.walk");
				}
				//Down
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(805))) {
					pelvisPosition = pelvisPosition.add(new Vector(0,-.04,0));	
				}
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(1000))) {	
				}
				//Reset
				else if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(1000))) {
					knight.animationTimer.put(pelvis, Instant.now());
					pelvisPosition = new Vector(0,0.5,0);
				}
				
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
			GoBtwn.animate(cape, cape.getHeadPose(), 15, 15, 0, 0, 0, 0, 30.0);
	
			//Arms, shield up or down boolean.
			if (knight.shieldUp) {
				leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftElbow, leftElbow.getHeadPose(), 15, 15, 0, 0, -10, -10, 5);
				
				rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightElbow, rightElbow.getHeadPose(), 10, 10, 0, 0, 45, 45, 5);
				
				leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftArm, leftArm.getHeadPose(), -60, -60, 0, 0, 40, 40, 5);
				
				rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightArm, rightArm.getHeadPose(), 0, 0, 0, 0, 40, 40, 5);
				
				leftHandPosition = knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(leftHand, leftHand.getHeadPose(), 180, 180, 100, 100, 0, 0, 30.0);
				
				rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(rightHand, rightHand.getHeadPose(), 0, 0, 30.0);
				
				knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(sword, sword.getHeadPose(), 0, 0, 30.0);
				
				knight.partPosition(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
				GoBtwn.animate(shield, shield.getHeadPose(), 180, 180, 100, 100, 0, 0, 110);
			}
			else {
				//Arms
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
			knight.legacyAnimate(leftThigh, -15,0,0, 30,0,0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(leftThigh, 30, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(leftThigh, 1600);
			
			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightThigh, 30, 0, 0, -15,0,0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(rightThigh, -15,0,0, 30, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(rightThigh, 1600);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftCalf, -15, 0, 0, 60, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(leftCalf, 60, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(leftCalf, 1600);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightCalf, 60, 0, 0, -15, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(rightCalf, -15, 0, 0, 60, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(rightCalf, 1600);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(leftFoot, -15, 0, 0, 60, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(leftFoot, 60, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(leftFoot, 1600);

			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			//Right
			knight.legacyAnimate(rightFoot, 60, 0, 0, -15, 0, 0, 0, 605);
			//Pause
			//Left
			knight.legacyAnimate(rightFoot, -15, 0, 0, 60, 0, 0, 800, 1405);
			//Pause
			//Reset
			knight.reset(rightFoot, 1600);
			
		} catch (NullPointerException event) {}
	}
}