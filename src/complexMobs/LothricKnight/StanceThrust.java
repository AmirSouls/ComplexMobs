package complexMobs.LothricKnight;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.DirectionAndMovement;
import complexMobs.LothricKnight.Methods.FromTo;
import complexMobs.LothricKnight.Methods.PartPositioning;
import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Methods.DamageArea;
import complexMobs.Methods.PlaySound;
import complexMobs.Methods.ToggleSound;
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
				DirectionAndMovement.timed(knight, distance * .014, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 0, 355);

				//Attack frames with aim
				DirectionAndMovement.timed(knight, distance * .3, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 355, 455);

				//Attack frames without aim
				DirectionAndMovement.timed(knight, distance * .3, 0, knight.animationTimer.get(pelvis), knight.animationTimer.get(pelvis), 455, 605, false);
				
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(1600))) {
					ResetTimers.resetOffset(knight, -55);
					knight.isAttacking = false;
					
					//Re-enable all sounds
					ToggleSound.enableAllSounds(knight);
				}
				
				//Sounds
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(0)) && Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(55))) {
					
					//Grunt sound
					if (ToggleSound.isOn(knight, "lothricknight.grunt")) {
						PlaySound.normal("lothricknight.grunt", knight.main.getLocation(), 3, 1, 1);
						ToggleSound.off(knight, "lothricknight.grunt");
					}
					
					//Stance attack sound fx
					if (ToggleSound.isOn(knight, "lothricknight.stanceattack")) {
						PlaySound.normal("lothricknight.stanceattack", knight.main.getLocation(), 3, 1, 1);
						ToggleSound.off(knight, "lothricknight.stanceattack");
					}
				}
			}
			
			if (pelvis != null) {
				pelvisPosition = new Vector(0,.2,0);
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
				FromTo.animate(pelvis, -5, 55, 10, 30, 50, 0, 0, 355);
				FromTo.animate(pelvis, 30, 50, 0, 0, -90, 0, 355, 605);
			}
			
			//Chest, head, cape and shield on back
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			FromTo.animate(chest, 50, 50, 40, 75, 35, 30, 0, 355);
			FromTo.animate(chest, 75, 35, 30, 0, -90, 0, 355, 605);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			FromTo.animate(head, 30, 0, 0, 60, 10, 0, 0, 355);
			FromTo.animate(head, 60, 10, 0, 30, -20, 0, 355, 605);
			
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			FromTo.animate(cape, -35, 70, -30, 90, 35, 30, 0, 355);
			FromTo.animate(cape, 90, 35, 30, 0, -50, 30, 355, 605);
			
			PartPositioning.position(shield, chestPosition, chest.getHeadPose(), new Vector(-.4,.9,-.1), knight.main.getLocation(), yaw);
			FromTo.animate(shield, 180, -30, -0, 180, 40, -70, 0, 355);
			FromTo.animate(shield, 180, 40, -70, 150, 180, 30, 355, 605);
			
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			FromTo.animate(leftElbow, -90, 70, 0, 0, 100, 40, 0, 355);
			FromTo.animate(leftElbow, 0, 100, 40, 90, -50, -90, 355, 605);
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightElbow, -145, 160, 0, 125, 0, -20, 0, 355);
			FromTo.animate(rightElbow, 125, 0, -20, -90, 0, 0, 355, 605);
		
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			FromTo.animate(leftArm, -130, 160, 0, 90, -60, 50, 0, 355);
			FromTo.animate(leftArm, 90, -60, 50, 20, 0, 0, 355, 605);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightArm, -40, 0, 45, -20, 0, 70, 0, 355);
			FromTo.animate(rightArm, 20, 0, 70, -90, -10, 0, 355, 605);
			
			PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			FromTo.animate(leftHand, 120, -40, 0, 90, -60, 50, 0, 355);
			FromTo.animate(leftHand, 90, -60, 50, 0, 0, 0, 355, 605);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightHand, 0, 0, 45, 20, 0, 70, 0, 355);
			FromTo.animate(rightHand, 20, 0, 70, -90, 0, 0, 355, 605);
			
			Vector swordPosition = PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			FromTo.animate(sword, 7, -15, 60, 30, -15, 60, 0, 355);
			FromTo.animate(sword, 30, -15, 60, 0, 0, 0, 355, 605);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			FromTo.animate(leftThigh, -10, 0, 0, 30, 40, 0, 0, 355);
			FromTo.animate(leftThigh, 30, 40, 0, -30, -100, 30, 355, 605);

			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			FromTo.animate(rightThigh, -25, 110, -60, 50, 0, 0, 0, 355);
			FromTo.animate(rightThigh, 50, 0, 0, 15, -70, 0, 355, 605);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			FromTo.animate(leftCalf, 10, -10, -15, 75, 30, 0, 0, 355);
			FromTo.animate(leftCalf, 75, 30, 0, -30, -150, 0, 355, 605);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			FromTo.animate(rightCalf, 90, 60, 90, 75, 50, 0, 0, 355);
			FromTo.animate(rightCalf, 0, 0, 0, 30, -55, 0, 355, 605);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			FromTo.animate(leftFoot, 0, 10, 0, 50, 30, 0, 0, 355);
			FromTo.animate(leftFoot, 50, 30, 0, 0, -90, 0, 355, 605);
		
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			FromTo.animate(rightFoot, 0, 70, 0, 75, 50, 10, 0, 355);
			FromTo.animate(rightFoot, 75, 50, 10, 0, -45, 0, 355, 605);
			
			DamageArea.process(knight, sword, 2, knight.animationTimer.get(sword), 350, 655, swordPosition, yaw, true, 22, .7, .4);
			
		} catch (NullPointerException event) {}
	}
}