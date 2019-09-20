package lothricKnights.Actions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.SpecialAnimations.HeadZeroAnimation;
import lothricKnights.SpecialAnimations.PelvisZeroAnimation;
import lothricKnights.Standing.*;


public class Standing {
	
	public static void standing(ArmorStand main) {
		try {
			if (!LothricKnights.changeTimer.containsKey(main)) {
				LothricKnights.changeTimer.put(main, Instant.now());
			}
			
			Collection<String> armorStands = new ArrayList<>();
			armorStands.add("pelvis");
			armorStands.add("chest");
			armorStands.add("head");
			armorStands.add("sword");
			armorStands.add("shield");
			armorStands.add("left foot");
			armorStands.add("right foot");
			armorStands.add("left calf");
			armorStands.add("right calf");
			armorStands.add("left thigh");
			armorStands.add("right thigh");
			armorStands.add("left elbow");
			armorStands.add("right elbow");
			armorStands.add("left arm");
			armorStands.add("right arm");
			armorStands.add("left hand");
			armorStands.add("right hand");
			armorStands.add("cape");
			
			//Position all armorStands
			
			//Intialize parts
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
			
			double yaw = main.getLocation().getYaw() / 57.2957795;
			
			//Intialize positions and offsets
			Vector pelvisPosition = new Vector(0,0,0);
			Vector chestPosition = new Vector(0,0,0);
			Vector chestOffset = new Vector(0,0,0);
			Vector headPosition = new Vector(0,0,0);
			Vector swordOffset = new Vector(0,0,0);
			Vector swordPosition = new Vector(0,0,0);
			Vector shieldOffset = new Vector(0,0,0);
			Vector shieldPosition = new Vector(0,0,0);
			Vector leftFootPosition = new Vector(0,0,0);
			Vector leftFootOffset = new Vector(0,0,0);
			Vector rightFootPosition = new Vector(0,0,0);
			Vector rightFootOffset = new Vector(0,0,0);
			Vector leftCalfPosition = new Vector(0,0,0);
			Vector leftCalfOffset = new Vector(0,0,0);
			Vector rightCalfPosition = new Vector(0,0,0);
			Vector rightCalfOffset = new Vector(0,0,0);
			Vector leftThighPosition = new Vector(0,0,0);
			Vector leftThighOffset = new Vector(0,0,0);
			Vector rightThighPosition = new Vector(0,0,0);
			Vector rightThighOffset = new Vector(0,0,0);
			Vector leftElbowPosition = new Vector(0,0,0);
			Vector leftElbowOffset = new Vector(0,0,0);
			Vector rightElbowPosition = new Vector(0,0,0);
			Vector rightElbowOffset = new Vector(0,0,0);
			Vector leftArmPosition = new Vector(0,0,0);
			Vector leftArmOffset = new Vector(0,0,0);
			Vector rightArmPosition = new Vector(0,0,0);
			Vector rightArmOffset = new Vector(0,0,0);
			Vector leftHandPosition = new Vector(0,0,0);
			Vector leftHandOffset = new Vector(0,0,0);
			Vector rightHandPosition = new Vector(0,0,0);
			Vector rightHandOffset = new Vector(0,0,0);
			Vector capePosition = new Vector(0,0,0);	
			Vector capeOffset = new Vector(0,0,0);	
			
			
			for (ArmorStand part : LothricKnights.partList.get(main)) {
				if (LothricKnights.partId.containsKey(part)) {
					String partId = LothricKnights.partId.get(part);
	
					if (partId.contentEquals("pelvis")) pelvis = part;
					if (partId.contentEquals("chest")) chest = part;
					if (partId.contentEquals("head")) head = part;
					if (partId.contentEquals("sword")) sword = part;
					if (partId.contentEquals("shield")) shield = part;
					if (partId.contentEquals("left foot")) leftFoot = part;
					if (partId.contentEquals("right foot")) rightFoot = part;
					if (partId.contentEquals("left calf")) leftCalf = part;
					if (partId.contentEquals("right calf")) rightCalf = part;
					if (partId.contentEquals("left thigh")) leftThigh = part;
					if (partId.contentEquals("right thigh")) rightThigh = part;
					if (partId.contentEquals("left elbow")) leftElbow = part;
					if (partId.contentEquals("right elbow")) rightElbow = part;
					if (partId.contentEquals("left arm")) leftArm = part;
					if (partId.contentEquals("right arm")) rightArm = part;
					if (partId.contentEquals("left hand")) leftHand = part;
					if (partId.contentEquals("right hand")) rightHand = part;
					if (partId.contentEquals("cape")) cape = part;
				}
			}
			
			//Direction handling
			{
				LivingEntity target = LothricKnights.mobTarget.get(main);
				
				double distance = target.getLocation().distance(main.getLocation());
				Location difference = target.getLocation().subtract(main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance,distance,distance));
				Location newDirection = main.getLocation().setDirection(vector);
				newDirection.setY(101);
				main.teleport(newDirection.add(newDirection.getDirection().multiply(0)));
			}
			
			
			if (pelvis != null) {
				pelvisPosition = new Vector(0,0.4,0);
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			if (chest != null) {
				chestPosition = new Vector(pelvisPosition.getX(),pelvisPosition.getY(),pelvisPosition.getZ());
				final EulerAngle angle = pelvis.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				chestOffset = new Vector(0,-.16,0.01);
				chestOffset.rotateAroundX(angleX / 2);
				chestOffset.rotateAroundY(-angleY / 2);
				chestOffset.rotateAroundZ(-angleZ / 2);	
				chestPosition.add(chestOffset);
				chestPosition.rotateAroundY(-yaw);
				chestPosition.add(new Vector(0,0,0));
				chest.teleport(main.getLocation().add(chestPosition));
				
				Chest.animate(chest);
			}
			
	
			if (leftThigh != null) {
				leftThighPosition = new Vector(pelvisPosition.getX(),pelvisPosition.getY(),pelvisPosition.getZ());
				final EulerAngle angle = pelvis.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftThighOffset = new Vector(0.17,-.42,.04);
				leftThighOffset.rotateAroundX(angleX);
				leftThighOffset.rotateAroundY(-angleY);
				leftThighOffset.rotateAroundZ(-angleZ);
				leftThighOffset.rotateAroundY(-yaw);
				leftThighPosition.add(leftThighOffset.multiply(1));
				
				leftThigh.teleport(main.getLocation().add(leftThighPosition));
				
				OutOfBounds.combined(leftThigh, leftThigh.getHeadPose(), 0, 0, 10);
			}
			
			if (rightThigh != null) {
				rightThighPosition = new Vector(pelvisPosition.getX(),pelvisPosition.getY(),pelvisPosition.getZ());
				final EulerAngle angle = pelvis.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightThighOffset = new Vector(-0.17,-.42,.04);
				rightThighOffset.rotateAroundX(angleX);
				rightThighOffset.rotateAroundY(-angleY);
				rightThighOffset.rotateAroundZ(-angleZ);
				rightThighOffset.rotateAroundY(-yaw);
				rightThighPosition.add(rightThighOffset.multiply(1));
				
				rightThigh.teleport(main.getLocation().add(rightThighPosition));
				
				OutOfBounds.combined(rightThigh, rightThigh.getHeadPose(), 0, 0, 10);
			}
			
			if (leftCalf != null) {
				leftCalfPosition = new Vector(leftThighPosition.getX(),leftThighPosition.getY(),leftThighPosition.getZ());
				final EulerAngle angle = leftThigh.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftCalfOffset = new Vector(0,-0.6,0);
				leftCalfOffset.rotateAroundX(angleX / 2);
				leftCalfOffset.rotateAroundY(-angleY / 2);
				leftCalfOffset.rotateAroundZ(-angleZ / 2);
				leftCalfOffset.rotateAroundY(-yaw);
				leftCalfPosition.add(leftCalfOffset.multiply(1));
				
				leftCalf.teleport(main.getLocation().add(leftCalfPosition));
				
				OutOfBounds.combined(leftCalf, leftCalf.getHeadPose(), 0, 0, 10);
			}
			
			if (rightCalf != null) {
				rightCalfPosition = new Vector(rightThighPosition.getX(),rightThighPosition.getY(),rightThighPosition.getZ());
				final EulerAngle angle = rightThigh.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightCalfOffset = new Vector(0,-0.6,0);
				rightCalfOffset.rotateAroundX(angleX / 2);
				rightCalfOffset.rotateAroundY(-angleY / 2);
				rightCalfOffset.rotateAroundZ(-angleZ / 2);
				rightCalfOffset.rotateAroundY(-yaw);
				rightCalfPosition.add(rightCalfOffset.multiply(1));
				
				rightCalf.teleport(main.getLocation().add(rightCalfPosition));
				
				OutOfBounds.combined(rightCalf, rightCalf.getHeadPose(), 0, 0, 10);
			}
			
			if (leftFoot != null) {
				leftFootPosition = new Vector(leftCalfPosition.getX(),leftCalfPosition.getY(),leftCalfPosition.getZ());
				final EulerAngle angle = leftCalf.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftFootOffset = new Vector(0,-0.585,-0.035);
				leftFootOffset.rotateAroundX(angleX / 2);
				leftFootOffset.rotateAroundY(-angleY / 2);
				leftFootOffset.rotateAroundZ(-angleZ / 2);
				leftFootOffset.rotateAroundY(-yaw);
				leftFootPosition.add(leftFootOffset.multiply(1));
				
				leftFoot.teleport(main.getLocation().add(leftFootPosition));
				
				OutOfBounds.combined(leftFoot, leftFoot.getHeadPose(), 0, 0, 10);
			}
			
			if (rightFoot != null) {
				rightFootPosition = new Vector(rightCalfPosition.getX(),rightCalfPosition.getY(),rightCalfPosition.getZ());
				final EulerAngle angle = rightCalf.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightFootOffset = new Vector(0,-0.585,-0.035);
				rightFootOffset.rotateAroundX(angleX / 2);
				rightFootOffset.rotateAroundY(-angleY / 2);
				rightFootOffset.rotateAroundZ(-angleZ / 2);
				rightFootOffset.rotateAroundY(-yaw);
				rightFootPosition.add(rightFootOffset.multiply(1));
				
				rightFoot.teleport(main.getLocation().add(rightFootPosition));
				
				OutOfBounds.combined(rightFoot, rightFoot.getHeadPose(), 0, 0, 10);
			}
			
			
			if (leftElbow != null) {
				leftElbowPosition = new Vector(chestPosition.getX(),chestPosition.getY(),chestPosition.getZ());
				final EulerAngle angle = chest.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftElbowOffset = new Vector(0.34,0.8,-.03);
				leftElbowOffset.rotateAroundX(angleX);
				leftElbowOffset.rotateAroundY(-angleY);
				leftElbowOffset.rotateAroundZ(-angleZ);
				leftElbowOffset.rotateAroundY(-yaw);
				leftElbowPosition.add(leftElbowOffset.multiply(1));
				
				leftElbow.teleport(main.getLocation().add(leftElbowPosition));
				
				OutOfBounds.combined(leftElbow, leftElbow.getHeadPose(), 0, 0, 10);
			}
			
			if (rightElbow != null) {
				rightElbowPosition = new Vector(chestPosition.getX(),chestPosition.getY(),chestPosition.getZ());
				final EulerAngle angle = chest.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightElbowOffset = new Vector(-0.34,0.9,0);
				rightElbowOffset.rotateAroundX(angleX);
				rightElbowOffset.rotateAroundY(-angleY);
				rightElbowOffset.rotateAroundZ(-angleZ);
				rightElbowOffset.rotateAroundY(-yaw);
				rightElbowPosition.add(rightElbowOffset.multiply(1));
				
				rightElbow.teleport(main.getLocation().add(rightElbowPosition));
				
				OutOfBounds.combined(rightElbow, rightElbow.getHeadPose(), 0, 0, 10);
			}
			
			if (leftArm != null) {
				leftArmPosition = new Vector(leftElbowPosition.getX(),leftElbowPosition.getY(),leftElbowPosition.getZ());
				final EulerAngle angle = leftElbow.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftArmOffset = new Vector(0.05,-0.51,0);
				leftArmOffset.rotateAroundX(angleX / 2);
				leftArmOffset.rotateAroundY(-angleY / 2);
				leftArmOffset.rotateAroundZ(-angleZ / 2);
				leftArmOffset.rotateAroundY(-yaw);
				leftArmPosition.add(leftArmOffset.multiply(1));
				
				leftArm.teleport(main.getLocation().add(leftArmPosition));
				
				OutOfBounds.combined(leftArm, leftArm.getHeadPose(), 0, 0, 10);
			}
			
			if (rightArm != null) {
				rightArmPosition = new Vector(rightElbowPosition.getX(),rightElbowPosition.getY(),rightElbowPosition.getZ());
				final EulerAngle angle = rightElbow.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightArmOffset = new Vector(-0.05,-0.51,0);
				rightArmOffset.rotateAroundX(angleX / 2);
				rightArmOffset.rotateAroundY(-angleY / 2);
				rightArmOffset.rotateAroundZ(-angleZ / 2);
				rightArmOffset.rotateAroundY(-yaw);
				rightArmPosition.add(rightArmOffset.multiply(1));
				
				rightArm.teleport(main.getLocation().add(rightArmPosition));
				
				OutOfBounds.combined(rightArm, rightArm.getHeadPose(), 0, 0, 10);
			}
			
			if (leftHand != null) {
				leftHandPosition = new Vector(leftArmPosition.getX(),leftArmPosition.getY(),leftArmPosition.getZ());
				final EulerAngle angle = leftArm.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftHandOffset = new Vector(0,-.4,0);
				leftHandOffset.rotateAroundX(angleX / 2);
				leftHandOffset.rotateAroundY(-angleY / 2);
				leftHandOffset.rotateAroundZ(-angleZ / 2);
				leftHandOffset.rotateAroundY(-yaw);
				leftHandPosition.add(leftHandOffset.multiply(1));
				
				leftHand.teleport(main.getLocation().add(leftHandPosition));
				
				OutOfBounds.combined(leftHand, leftHand.getHeadPose(), 0, 0, 10);
			}
			
			if (rightHand != null) {
				rightHandPosition = new Vector(rightArmPosition.getX(),rightArmPosition.getY(),rightArmPosition.getZ());
				final EulerAngle angle = rightArm.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightHandOffset = new Vector(0,-.4,0);
				rightHandOffset.rotateAroundX(angleX / 2);
				rightHandOffset.rotateAroundY(-angleY / 2);
				rightHandOffset.rotateAroundZ(-angleZ / 2);
				rightHandOffset.rotateAroundY(-yaw);
				rightHandPosition.add(rightHandOffset.multiply(1));
				
				rightHand.teleport(main.getLocation().add(rightHandPosition));
				
				OutOfBounds.combined(rightHand, rightHand.getHeadPose(), 0, 0, 10);
			}
			
			if (sword != null) {
				swordPosition = new Vector(rightHandPosition.getX(),rightHandPosition.getY(),rightHandPosition.getZ());
				final EulerAngle angle = rightHand.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				swordOffset = new Vector(0.05,-.3,0);
				swordOffset.rotateAroundX(angleX / 2);
				swordOffset.rotateAroundY(-angleY / 2);
				swordOffset.rotateAroundZ(-angleZ / 2);
				swordOffset.rotateAroundY(-yaw);
				swordPosition.add(swordOffset.multiply(1));
				
				sword.teleport(main.getLocation().add(swordPosition));
				
				OutOfBounds.combined(sword, sword.getHeadPose(), 0, 0, 10);
			}
			
			if (shield != null) {
				shieldPosition = new Vector(leftHandPosition.getX(),leftHandPosition.getY(),leftHandPosition.getZ());
				final EulerAngle angle = leftHand.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				shieldOffset = new Vector(0,-0.5,0);
				shieldOffset.rotateAroundX(angleX / 2);
				shieldOffset.rotateAroundY(-angleY / 2);
				shieldOffset.rotateAroundZ(-angleZ / 2);
				shieldOffset.rotateAroundY(-yaw);
				shieldPosition.add(shieldOffset.multiply(1));
				
				shield.teleport(main.getLocation().add(shieldPosition));
				
				OutOfBounds.combined(shield, shield.getHeadPose(), 0, 0, 10);
			}
			
			if (cape != null) {
				capePosition = new Vector(chestPosition.getX(),chestPosition.getY(),chestPosition.getZ());
				final EulerAngle angle = chest.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				capeOffset = new Vector(0,0,-0.2);
				capeOffset.rotateAroundX(angleX);
				capeOffset.rotateAroundY(-angleY);
				capeOffset.rotateAroundZ(-angleZ);
				capeOffset.rotateAroundY(-yaw);
				capePosition.add(capeOffset.multiply(1));
				
				cape.teleport(main.getLocation().add(capePosition));
				
				OutOfBounds.combined(cape, cape.getHeadPose(), 0, 0, 10);
			}
			
			if (head != null) {
				headPosition = chestPosition;
				final EulerAngle angle = chest.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				Vector headOffset = new Vector(0,0.8,0);
				headOffset.rotateAroundX(angleX);
				headOffset.rotateAroundY(-angleY);
				headOffset.rotateAroundZ(-angleZ);	
				headOffset.rotateAroundY(-yaw);
				headPosition.add(headOffset);
				
				head.teleport(main.getLocation().add(headPosition));
				
				//Special head animation
				HeadZeroAnimation.animate(head, main);
			}	
			
		} catch (NullPointerException event) {}
	}
}