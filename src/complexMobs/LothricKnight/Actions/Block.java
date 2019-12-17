package complexMobs.LothricKnight.Actions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.LothricKnight.SpecialAnimations.PelvisZeroAnimation;
import complexMobs.Mobs.LothricKnight;


public class Block {
	
	//For when attacked while the knight's shield is up
	public static void animate(LothricKnight knight) {
		try {
			
			Collection<String> armorStands = new ArrayList<>();
			armorStands.add("pelvis");
			armorStands.add("chest");
			armorStands.add("head");
			armorStands.add("cape");
			armorStands.add("sword");
			armorStands.add("shield");
			armorStands.add("leftFoot");
			armorStands.add("rightFoot");
			armorStands.add("leftCalf");
			armorStands.add("rightCalf");
			armorStands.add("leftThigh");
			armorStands.add("rightThigh");
			armorStands.add("leftElbow");
			armorStands.add("rightElbow");
			armorStands.add("leftArm");
			armorStands.add("rightArm");
			armorStands.add("leftHand");
			armorStands.add("rightHand");
			
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
			Vector headOffset = new Vector(0,0,0);
			
			
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
				Location difference = target.getLocation().subtract(knight.main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance,distance,distance));

				Location newDirection = knight.main.getLocation().setDirection(vector);

				vector.setY(0);
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(50))) {
					knight.main.teleport(newDirection.add(vector.multiply(-0.05)));
				}
				else {
					knight.main.teleport(newDirection.add(vector.multiply(0)));
				}
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.4,0);
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				
				//Animation
				//Special pelvis animation
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			if (chest != null) {
				chestPosition = new Vector(pelvisPosition.getX(),pelvisPosition.getY(),pelvisPosition.getZ());
				final EulerAngle angle = pelvis.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				chestOffset = new Vector(0,-.16,0.01);
				chestOffset.rotateAroundX(angleX);
				chestOffset.rotateAroundY(-angleY);
				chestOffset.rotateAroundZ(-angleZ);	
				chestPosition.add(chestOffset);
				chestPosition.rotateAroundY(-yaw);
				chestPosition.add(new Vector(0,0,0));
				chest.teleport(knight.main.getLocation().add(chestPosition));
			}
			
			if (head != null) {
				headPosition = new Vector(chestPosition.getX(),chestPosition.getY(),chestPosition.getZ());
				final EulerAngle angle = chest.getHeadPose();
				headOffset = new Vector(0,0.8,0);
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				headOffset.rotateAroundX(angleX);
				headOffset.rotateAroundY(-angleY);
				headOffset.rotateAroundZ(-angleZ);	
				headOffset.rotateAroundY(-yaw);
				headPosition.add(headOffset);
				
				head.teleport(knight.main.getLocation().add(headPosition));
				//Special head animation
				HeadZeroAnimation.animate(head, knight);
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
				
				leftThigh.teleport(knight.main.getLocation().add(leftThighPosition));
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
				
				rightThigh.teleport(knight.main.getLocation().add(rightThighPosition));
			}
			
			if (leftCalf != null) {
				leftCalfPosition = new Vector(leftThighPosition.getX(),leftThighPosition.getY(),leftThighPosition.getZ());
				final EulerAngle angle = leftThigh.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftCalfOffset = new Vector(0,-0.6,0);
				leftCalfOffset.rotateAroundX(angleX);
				leftCalfOffset.rotateAroundY(-angleY);
				leftCalfOffset.rotateAroundZ(-angleZ);
				leftCalfOffset.rotateAroundY(-yaw);
				leftCalfPosition.add(leftCalfOffset.multiply(1));
				
				leftCalf.teleport(knight.main.getLocation().add(leftCalfPosition));
			}
			
			if (rightCalf != null) {
				rightCalfPosition = new Vector(rightThighPosition.getX(),rightThighPosition.getY(),rightThighPosition.getZ());
				final EulerAngle angle = rightThigh.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightCalfOffset = new Vector(0,-0.6,0);
				rightCalfOffset.rotateAroundX(angleX);
				rightCalfOffset.rotateAroundY(-angleY);
				rightCalfOffset.rotateAroundZ(-angleZ);
				rightCalfOffset.rotateAroundY(-yaw);
				rightCalfPosition.add(rightCalfOffset.multiply(1));
				
				rightCalf.teleport(knight.main.getLocation().add(rightCalfPosition));
			}
			
			if (leftFoot != null) {
				leftFootPosition = new Vector(leftCalfPosition.getX(),leftCalfPosition.getY(),leftCalfPosition.getZ());
				final EulerAngle angle = leftCalf.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftFootOffset = new Vector(0,-0.585,-0.035);
				leftFootOffset.rotateAroundX(angleX);
				leftFootOffset.rotateAroundY(-angleY);
				leftFootOffset.rotateAroundZ(-angleZ);
				leftFootOffset.rotateAroundY(-yaw);
				leftFootPosition.add(leftFootOffset.multiply(1));
				
				leftFoot.teleport(knight.main.getLocation().add(leftFootPosition));
			}
			
			if (rightFoot != null) {
				rightFootPosition = new Vector(rightCalfPosition.getX(),rightCalfPosition.getY(),rightCalfPosition.getZ());
				final EulerAngle angle = rightCalf.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightFootOffset = new Vector(0,-0.585,-0.035);
				rightFootOffset.rotateAroundX(angleX);
				rightFootOffset.rotateAroundY(-angleY);
				rightFootOffset.rotateAroundZ(-angleZ);
				rightFootOffset.rotateAroundY(-yaw);
				rightFootPosition.add(rightFootOffset.multiply(1));
				
				rightFoot.teleport(knight.main.getLocation().add(rightFootPosition));
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
				
				leftElbow.teleport(knight.main.getLocation().add(leftElbowPosition));
				
				knight.legacyAnimate(leftElbow, 15,0,-10, -10, -10, -10, 0, 205);
				knight.legacyAnimate(leftElbow, -10, -10, -10, 15, 0, -10, 205, 605);
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
				
				rightElbow.teleport(knight.main.getLocation().add(rightElbowPosition));
			}
			
			if (leftArm != null) {
				leftArmPosition = new Vector(leftElbowPosition.getX(),leftElbowPosition.getY(),leftElbowPosition.getZ());
				final EulerAngle angle = leftElbow.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftArmOffset = new Vector(0.05,-0.51,0);
				leftArmOffset.rotateAroundX(angleX);
				leftArmOffset.rotateAroundY(-angleY);
				leftArmOffset.rotateAroundZ(-angleZ);
				leftArmOffset.rotateAroundY(-yaw);
				leftArmPosition.add(leftArmOffset.multiply(1));
				
				leftArm.teleport(knight.main.getLocation().add(leftArmPosition));
			}
			
			if (rightArm != null) {
				rightArmPosition = new Vector(rightElbowPosition.getX(),rightElbowPosition.getY(),rightElbowPosition.getZ());
				final EulerAngle angle = rightElbow.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightArmOffset = new Vector(-0.05,-0.51,0);
				rightArmOffset.rotateAroundX(angleX);
				rightArmOffset.rotateAroundY(-angleY);
				rightArmOffset.rotateAroundZ(-angleZ);
				rightArmOffset.rotateAroundY(-yaw);
				rightArmPosition.add(rightArmOffset.multiply(1));
				
				rightArm.teleport(knight.main.getLocation().add(rightArmPosition));
			}
			
			if (leftHand != null) {
				leftHandPosition = new Vector(leftArmPosition.getX(),leftArmPosition.getY(),leftArmPosition.getZ());
				final EulerAngle angle = leftArm.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				leftHandOffset = new Vector(0,-.4,0);
				leftHandOffset.rotateAroundX(angleX);
				leftHandOffset.rotateAroundY(-angleY);
				leftHandOffset.rotateAroundZ(-angleZ);
				leftHandOffset.rotateAroundY(-yaw);
				leftHandPosition.add(leftHandOffset.multiply(1));
				
				leftHand.teleport(knight.main.getLocation().add(leftHandPosition));
			}
			
			if (rightHand != null) {
				rightHandPosition = new Vector(rightArmPosition.getX(),rightArmPosition.getY(),rightArmPosition.getZ());
				final EulerAngle angle = rightArm.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				rightHandOffset = new Vector(0,-.4,0);
				rightHandOffset.rotateAroundX(angleX);
				rightHandOffset.rotateAroundY(-angleY);
				rightHandOffset.rotateAroundZ(-angleZ);
				rightHandOffset.rotateAroundY(-yaw);
				rightHandPosition.add(rightHandOffset.multiply(1));
				
				rightHand.teleport(knight.main.getLocation().add(rightHandPosition));
			}
			
			if (sword != null) {
				swordPosition = new Vector(rightHandPosition.getX(),rightHandPosition.getY(),rightHandPosition.getZ());
				final EulerAngle angle = rightHand.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				swordOffset = new Vector(0.05,-.3,0);
				swordOffset.rotateAroundX(angleX);
				swordOffset.rotateAroundY(-angleY);
				swordOffset.rotateAroundZ(-angleZ);
				swordOffset.rotateAroundY(-yaw);
				swordPosition.add(swordOffset.multiply(1));
				
				sword.teleport(knight.main.getLocation().add(swordPosition));
			}
			
			if (shield != null) {
				shieldPosition = new Vector(leftHandPosition.getX(),leftHandPosition.getY(),leftHandPosition.getZ());
				final EulerAngle angle = leftHand.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				shieldOffset = new Vector(0,-0.5,0);
				shieldOffset.rotateAroundX(angleX);
				shieldOffset.rotateAroundY(-angleY);
				shieldOffset.rotateAroundZ(-angleZ);
				shieldOffset.rotateAroundY(-yaw);
				shieldPosition.add(shieldOffset.multiply(1));
				
				shield.teleport(knight.main.getLocation().add(shieldPosition));
			}
			
			if (cape != null) {
				capePosition = new Vector(chestPosition.getX(),chestPosition.getY(),chestPosition.getZ());
				final EulerAngle angle = chest.getHeadPose();
				final double angleX = angle.getX();
				final double angleY = angle.getY();
				final double angleZ = angle.getZ();
				capeOffset = new Vector(0,0.9,-0.2);
				capeOffset.rotateAroundX(angleX);
				capeOffset.rotateAroundY(-angleY);
				capeOffset.rotateAroundZ(-angleZ);
				capeOffset.rotateAroundY(-yaw);
				capePosition.add(capeOffset.multiply(1));
				
				//Animation
				//Special cape animation
				cape.teleport(knight.main.getLocation().add(capePosition));
				
				knight.legacyAnimate(cape, 0, 0, 0, 70, 0, 40, 0, 105);
				knight.legacyAnimate(cape, 70, 0, 40, 0, 0, 0, 105, 1005);
			}
			
			
		} catch (NullPointerException event) {}
	}
}