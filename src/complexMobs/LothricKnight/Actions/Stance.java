package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Mobs.LothricKnight;

public class Stance {
	
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
				
				double distance3D = target.getLocation().distance(knight.main.getLocation());
				Location difference = target.getLocation().subtract(knight.main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance3D,distance3D,distance3D));

				Location newDirection = knight.main.getLocation().setDirection(vector);

				vector.setY(0);

				knight.main.teleport(newDirection.add(vector.multiply(0)));
			
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(55))) {
					//Stop sound
					if (knight.soundIsOn("lothricknight.walk")) {
						knight.playSound("lothricknight.walk", knight.main.getLocation(), 3, 1, 1);
						knight.soundOff("lothricknight.walk");
					}
				}
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(600))) {
					if (distance3D > 6) {
						//Thrust attack!
						knight.activeAction = "StanceThrust";
						ResetTimers.reset(knight);
					}
					else {
						ResetTimers.reset(knight);
						knight.isAttacking = false;
					}
				}
				
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(5000))) {
					ResetTimers.reset(knight);
				}
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(300))) {
					pelvisPosition = new Vector(0, .5 + (.001 * (knight.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
				}
				else if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(300))) {
					pelvisPosition = new Vector(0, .2, 0);
				}
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				knight.legacyAnimate(pelvis, 0, 0, 0, -5, 55, 10, 0, 305);
			}
			
			//Chest, head, cape and shield on back
			chestPosition = knight.partPosition(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			knight.legacyAnimate(chest, 0, 0, 0, 50, 50, 40, 0, 305);
			
			knight.partPosition(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(head, 0, 0, 0, 30, 0, 0, 0, 305);
			
			knight.partPosition(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			knight.legacyAnimate(cape, 0, 0, 0, -35, 70, -30, 0, 305);
			
			knight.partPosition(shield, chestPosition, chest.getHeadPose(), new Vector(-.4,.9,-.1), knight.main.getLocation(), yaw);
			knight.legacyAnimate(shield, 0, 0, 0, 180, -30, -30, 0, 305);
			
			//Arms
			leftElbowPosition = knight.partPosition(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftElbow, 0, 0, 0, -90, 70, 0, 0, 305);
			
			rightElbowPosition = knight.partPosition(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightElbow, 0, 0, 0, -145, 160, 0, 0, 305);
		
			leftArmPosition = knight.partPosition(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftArm, 0, 0, 0, -130, 160, 0, 0, 305);
			
			rightArmPosition = knight.partPosition(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightArm, 0, 0, 0, -40, 0, 45, 0, 305);
			
			knight.partPosition(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftHand, 0, 0, 0, 120, -40, 0, 0, 305);
			
			rightHandPosition = knight.partPosition(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightHand, 0, 0, 0, 0, 0, 45, 0, 305);
			
			knight.partPosition(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(sword, 0, 0, 0, 7, -15, 60, 0, 305);
			
			//Legs
			leftThighPosition = knight.partPosition(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftThigh, 0, 0, 0, -70, 20, 0, 0, 205);
			knight.legacyAnimate(leftThigh, 0, 0, 0, -45, -10, 0, 205, 305);

			rightThighPosition = knight.partPosition(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightThigh, 0, 0, 0, -25, 110, 0, 0, 305);
			
			leftCalfPosition = knight.partPosition(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftCalf, 0, 0, 0, 10, 20, -5, 0, 205);
			knight.legacyAnimate(leftCalf, 10, 20, -5, 10, -10, -15, 205, 305);
			
			rightCalfPosition = knight.partPosition(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightCalf, 0, 0, 0, 90, 60, 90, 0, 305);
			
			knight.partPosition(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(leftFoot, 0, 10, 0, 0, 10, 0, 0, 305);
		
			knight.partPosition(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			knight.legacyAnimate(rightFoot, 0, 70, 0, 0, 70, 0, 0, 300);
		
		} catch (NullPointerException event) {}
	}
}