package lothricKnights.Actions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.Animate;
import lothricKnights.Methods.GoBtwn;
import lothricKnights.Methods.PartPositioning;
import lothricKnights.Methods.ResetTimers;
import lothricKnights.SpecialAnimations.HeadZeroAnimation;
import lothricKnights.SpecialAnimations.PelvisZeroAnimation;


public class Running {
	
	public static void animate(ArmorStand main) {
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
			
			double yaw = main.getLocation().getYaw() / 57.2957795;
			
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

				vector.setY(0);
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis))) {
					main.teleport(newDirection.add(vector.multiply(0.28)));
				}
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(605))) {
					ResetTimers.resetTimers(main);
				}
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				
				double deltaTime = LothricKnights.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli();
				double heightModif = -.05;
				
				//Down
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(105))) {
					pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
				}
				//Up
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(205))) {
					deltaTime += 100;
					pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
				}
				//Sound
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(255))) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.run master @a " +
							main.getLocation().getX() + 
							" " +
							main.getLocation().getY() +
							" " + main.getLocation().getZ() + " 1 1"
							);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(305))) {
				}
				//Down
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(405))) {
					deltaTime += 300;
					pelvisPosition = new Vector(0, .5 - (heightModif * (deltaTime / 100)), 0);
				}
				//Down
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(505))) {
					deltaTime += 400;
					pelvisPosition = new Vector(0, .5 + (heightModif * (deltaTime / 100)), 0);
				}
				//Sound
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(555))) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.run master @a " +
							main.getLocation().getX() + 
							" " +
							main.getLocation().getY() +
							" " + main.getLocation().getZ() + " 1 1"
							);
				}
				//Pause
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(605))) {
				}
				//Reset
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				
				//Animation
				//Special pelvis animation
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			//Chest, head and cape
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			//Right
			Animate.fromTo(chest, 30, -5, 0, 30, 5, 0, 0, 205);
			//Left
			Animate.fromTo(chest, 30, 5, 0, 30, -5, 0, 305, 505);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, main);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			GoBtwn.combined(cape, cape.getHeadPose(), 15, 15, 0, 0, 0, 0, 10);
	
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftElbow, 40, 0, -30, 10, 0, -10, 0, 205);
			//Pause
			//Left
			Animate.fromTo(leftElbow, 10, 0, -10, 40, 0, -30, 305, 505);
			//Pause
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightElbow, 30, 0, 30, 55, 0, 45, 0, 205);
			//Left
			Animate.fromTo(rightElbow, 55, 0, 45, 30, 0, 30, 305, 505);
			
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftArm, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			Animate.fromTo(leftArm, -30, 30, 0, 0, 0, -10, 305, 505);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightArm, 0, 0, 10, 55, 0, 45, 0, 205);
			//Left
			Animate.fromTo(rightArm, 55, 0, 45, 0, 0, 10, 305, 505);
			
			leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftHand, 0, 0, -10, -30, 30, 0, 0, 205);
			//Left
			Animate.fromTo(leftHand, -30, 30, 0, 0, 0, -10, 305, 505);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightHand, 0, 0, 0, 45, 0, 45, 0, 205);
			//Left
			Animate.fromTo(rightHand, 45, 0, 45, 0, 0, 0, 305, 505);
			
			PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(sword, 50, 0, 35, 70, 0, 45, 0, 205);
			//Left
			Animate.fromTo(sword, 70, 0, 45, 50, 0, 35, 305, 505);
			
			PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(shield, 0, 30, -5, -25, 65, 0, 0, 205);
			//Left
			Animate.fromTo(shield, -25, 65, 0, 0, 30, -5, 305, 505);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftThigh, -60, 0, 0, 45, 0, 0, 0, 205);
			//Left
			Animate.fromTo(leftThigh, 45, 0, 0, -60, 0, 0, 305, 505);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightThigh, 45, 0, 0, -60, 0, 0, 0, 205);
			//Left
			Animate.fromTo(rightThigh, -60, 0, 0, 45, 0, 0, 305, 505);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftCalf, -10, 0, 0, 100, 0, 0, 0, 205);
			//Left
			Animate.fromTo(leftCalf, 100, 0, 0, -10, 0, 0, 305, 505);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightCalf, 100, 0, 0, -10, 0, 0, 0, 205);
			//Left
			Animate.fromTo(rightCalf, -10, 0, 0, 100, 0, 0, 305, 505);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftFoot, 0, 0, 0, 50, 0, 0, 0, 205);
			//Left
			Animate.fromTo(leftFoot, 50, 0, 0, 0, 0, 0, 305, 505);

			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightFoot, 50, 0, 0, 0, 0, 0, 0, 205);
			//Left
			Animate.fromTo(rightFoot, 0, 0, 0, 50, 0, 0, 305, 505);
			
		} catch (NullPointerException event) {}
	}
}