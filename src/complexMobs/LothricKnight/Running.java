package complexMobs.LothricKnight;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.DirectionAndMovement;
import complexMobs.LothricKnight.Methods.FromTo;
import complexMobs.LothricKnight.Methods.GoBtwn;
import complexMobs.LothricKnight.Methods.PartPositioning;
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
			DirectionAndMovement.normal(knight, .28, 0);
			
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
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.run master @a " +
							knight.main.getLocation().getX() + 
							" " +
							knight.main.getLocation().getY() +
							" " + knight.main.getLocation().getZ() + " 3 1"
							);
				}
				//Pause
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(305))) {
				}
				//Down
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(405))) {
					deltaTime += 300;
					pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
				}
				//Down
				if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(505))) {
					deltaTime += 400;
					pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
				}
				//Sound
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(555))) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.run master @a " +
							knight.main.getLocation().getX() + 
							" " +
							knight.main.getLocation().getY() +
							" " + knight.main.getLocation().getZ() + " 3 1"
							);
				}
				//Pause
				else if (Instant.now().isBefore(knight.animationTimer.get(pelvis).plusMillis(605))) {
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
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(chest, 30, -5, 0, 30, 5, 0, 0, 205);
			//Left
			FromTo.animate(chest, 30, 5, 0, 30, -5, 0, 305, 505);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, knight);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
			GoBtwn.animate(cape, cape.getHeadPose(), 15, 15, 0, 0, 0, 0, 10);
	
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(leftElbow, 40, 0, -30, 10, 0, -10, 0, 205);
			//Left
			FromTo.animate(leftElbow, 10, 0, -10, 40, 0, -30, 305, 505);
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(rightElbow, 30, 0, 30, 55, 0, 45, 0, 205);
			//Left
			FromTo.animate(rightElbow, 55, 0, 45, 30, 0, 30, 305, 505);
			
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(leftArm, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			FromTo.animate(leftArm, -30, 30, 0, 0, 0, -10, 305, 505);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(rightArm, 0, 0, 10, 55, 0, 45, 0, 205);
			//Left
			FromTo.animate(rightArm, 55, 0, 45, 0, 0, 10, 305, 505);
			
			leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(leftHand, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			FromTo.animate(leftHand, -30, 30, 0, 0, 0, -10, 305, 505);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(rightHand, 0, 0, 0, 45, 0, 45, 0, 205);
			//Left
			FromTo.animate(rightHand, 45, 0, 45, 0, 0, 0, 305, 505);
			
			PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(sword, 50, 0, 35, 70, 0, 45, 0, 205);
			//Left
			FromTo.animate(sword, 70, 0, 45, 50, 0, 35, 305, 505);
			
			PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(shield, 0, 30, -5, -25, 65, 0, 0, 205);
			//Left
			FromTo.animate(shield, -25, 65, 0, 0, 30, -5, 305, 505);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(leftThigh, -60, 0, 0, 45, 0, 0, 0, 205);
			//Left
			FromTo.animate(leftThigh, 45, 0, 0, -60, 0, 0, 305, 505);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(rightThigh, 45, 0, 0, -60, 0, 0, 0, 205);
			//Left
			FromTo.animate(rightThigh, -60, 0, 0, 45, 0, 0, 305, 505);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(leftCalf, -10, 0, 0, 100, 0, 0, 0, 205);
			//Left
			FromTo.animate(leftCalf, 100, 0, 0, -10, 0, 0, 305, 505);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(rightCalf, 100, 0, 0, -10, 0, 0, 0, 205);
			//Left
			FromTo.animate(rightCalf, -10, 0, 0, 100, 0, 0, 305, 505);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(leftFoot, 0, 0, 0, 50, 0, 0, 0, 205);
			//Left
			FromTo.animate(leftFoot, 50, 0, 0, 0, 0, 0, 305, 505);

			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
			//Right
			FromTo.animate(rightFoot, 50, 0, 0, 0, 0, 0, 0, 205);
			//Left
			FromTo.animate(rightFoot, 0, 0, 0, 50, 0, 0, 305, 505);
			
		} catch (NullPointerException event) {}
	}
}