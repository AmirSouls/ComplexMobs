package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.DirectionAndMovement;
import complexMobs.LothricKnight.Methods.GoTo;
import complexMobs.LothricKnight.Methods.PartPositioning;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.LothricKnight.SpecialAnimations.PelvisZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class Standing {
	
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
			if (knight.target == null) {
			}
			else {
				DirectionAndMovement.normal(knight, 0, 0, true);
			}
			
			
			pelvisPosition = new Vector(0,0.4,0);
			pelvisPosition.rotateAroundY(-yaw);
			pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				
			PelvisZeroAnimation.animate(pelvis, 10);
			
			
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			GoTo.animate(chest, chest.getHeadPose(), 0, 0, 0);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			GoTo.animate(leftThigh, leftThigh.getHeadPose(), 0, 0, -5);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			GoTo.animate(rightThigh, rightThigh.getHeadPose(), 0, 0, 10);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			GoTo.animate(leftCalf, leftCalf.getHeadPose(), 0, 0, -5);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			GoTo.animate(rightCalf, rightCalf.getHeadPose(), 0, 0, 10);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			GoTo.animate(leftFoot, leftFoot.getHeadPose(), 0, 0, 0);
			
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			GoTo.animate(rightFoot, rightFoot.getHeadPose(), 0, 0, 0);
			
			//Arms
			if (knight.shieldUp) {
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
				GoTo.animate(leftElbow, leftElbow.getHeadPose(), 15, 0, -10);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoTo.animate(leftArm, leftArm.getHeadPose(), -60, 0, 40);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoTo.animate(leftHand, leftHand.getHeadPose(), 180, 100, 0);
				
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
				GoTo.animate(shield, shield.getHeadPose(), 180, 100, 0);
			}
			else {
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
				GoTo.animate(leftElbow, leftElbow.getHeadPose(), 0, 0, -15);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
				GoTo.animate(leftArm, leftArm.getHeadPose(), 0, 0, -5);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoTo.animate(leftHand, leftHand.getHeadPose(), 0, 30, -10);
				
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
				GoTo.animate(shield, shield.getHeadPose(), 0, 30, -10);
			}
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			GoTo.animate(rightElbow, rightElbow.getHeadPose(), 20, 0, 5);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			GoTo.animate(rightArm, rightArm.getHeadPose(), 10, 0, 20);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			GoTo.animate(rightHand, rightHand.getHeadPose(), 10, 30, 0);
			
			PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			GoTo.animate(sword, sword.getHeadPose(), 10, 30, 0);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			GoTo.animate(cape, cape.getHeadPose(), 0, 0, 0);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, knight);	
			
		} catch (NullPointerException event) {}
	}
}