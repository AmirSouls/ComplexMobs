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
import lothricKnights.Methods.OutOfBounds;
import lothricKnights.Methods.PartPositioning;
import lothricKnights.SpecialAnimations.HeadZeroAnimation;
import lothricKnights.SpecialAnimations.PelvisZeroAnimation;

public class WalkingSide {
	
	public static void animate(ArmorStand main, boolean shieldUp) {
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
			Vector swordPosition = new Vector(0,0,0);
			
			
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
				
				double walkingDirection = -90 / 57.2934;
				vector.rotateAroundAxis(new Vector(0,1,0), walkingDirection);

				vector.setY(0);
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {
					main.teleport(newDirection.add(vector.multiply(0.07)));
				}
			}
			
			
			if (pelvis != null) {
				pelvisPosition = new Vector(0,0.4,0);
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				
				//Animation
				//Special pelvis animation
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			//Chest, head and cape
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			OutOfBounds.combined(chest, chest.getHeadPose(), 10, 10, 10, 15, 15, 10, 0, 0, 0);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, main);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			OutOfBounds.combined(cape, cape.getHeadPose(), 20, 20, 10, 35, 35, 10, 0, 0, 10);
			
			//Arms
			if (shieldUp) {
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
				OutOfBounds.combined(leftElbow, leftElbow.getHeadPose(), 15, 15, 5, 0, 0, 5, -10, -10, 5);
				
				rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
				OutOfBounds.combined(rightElbow, rightElbow.getHeadPose(), 10, 10, 5, 0, 0, 5, 45, 45, 5);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
				OutOfBounds.combined(leftArm, leftArm.getHeadPose(), -60, -60, 5, 0, 0, 5, 40, 40, 5);
				
				rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
				OutOfBounds.combined(rightArm, rightArm.getHeadPose(), 0, 0, 5, 0, 0, 5, 40, 40, 5);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				OutOfBounds.combined(leftHand, leftHand.getHeadPose(), 180, 180, 10, 100, 100, 10, 0, 0, 10);
				
				rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				OutOfBounds.combined(rightHand, rightHand.getHeadPose(), 0, 0, 10);
				
				swordPosition = PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
				OutOfBounds.combined(sword, sword.getHeadPose(), 0, 0, 10);
				
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
				OutOfBounds.combined(shield, shield.getHeadPose(), 180, 180, 40, 100, 100, 40, 0, 0, 40);
			}
			else {
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
				OutOfBounds.combined(leftElbow, leftElbow.getHeadPose(), 0, 0, 5, 0, 0, 5, -20, -20, 5);
				
				rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
				OutOfBounds.combined(rightElbow, rightElbow.getHeadPose(), 30, 30, 5, 0, 0, 5, 30, 30, 5);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
				OutOfBounds.combined(leftArm, leftArm.getHeadPose(), 0, 0, 5, 0, 0, 5, 10, 10, 5);
				
				rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
				OutOfBounds.combined(rightArm, rightArm.getHeadPose(), 20, 20, 5, 0, 0, 5, 40, 40, 5);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				OutOfBounds.combined(leftHand, leftHand.getHeadPose(), -10, -10, 10, 10, 10, 10, 5, 5, 10);
				
				rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				OutOfBounds.combined(rightHand, rightHand.getHeadPose(), 20, 20, 5, 0, 0, 5, 40, 40, 5);
				
				PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
				OutOfBounds.combined(sword, sword.getHeadPose(), 40, 40, 10, 20, 20, 10, 20, 20, 10);
				
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
				OutOfBounds.combined(shield, shield.getHeadPose(), -15, -15, 20, 10, 10, 20, 10, 10, 20);
			}
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftThigh, -30, -20, 0, -50, 30, 0, 0, 755);
			//Pause
			//Left
			Animate.fromTo(leftThigh, -50, 30, 0, -30, -20, 0, 900, 1655);
			//Pause
			//Reset
			Animate.reset(leftThigh, 1800);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightThigh, -30, 30, 0, 20, 55, 0, 0, 755);
			//Pause
			//Left
			Animate.fromTo(rightThigh, 20, 55, 0, -30, 30, 0, 900, 1655);
			//Pause
			//Reset
			Animate.reset(rightThigh, 1800);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftCalf, 10, 0, -10, -10, 15, 10, 0, 755);
			//Pause
			//Left
			Animate.fromTo(leftCalf, -10, 15, 10, 10, 0, -10, 900, 1655);
			//Pause
			//Reset
			Animate.reset(leftCalf, 1800);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightCalf, 5, 0, 10, 50, 80, 0, 0, 755);
			//Pause
			//Left
			Animate.fromTo(rightCalf, 50, 80, 0, 5, 0, 10, 900, 1655);
			//Pause
			//Reset
			Animate.reset(rightCalf, 1800);
			
			//Step sound
			if (Instant.now().isAfter(LothricKnights.animationTimer.get(rightCalf).plusMillis(755)) && Instant.now().isBefore(LothricKnights.animationTimer.get(rightCalf).plusMillis(805))) {		
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
						"playsound minecraft:lothricknight.walk master @a " +
						main.getLocation().getX() + 
						" " +
						main.getLocation().getY() +
						" " + main.getLocation().getZ() + " 0.5 1"
						);
			}
			//Step sound
			else if (Instant.now().isAfter(LothricKnights.animationTimer.get(rightCalf).plusMillis(1655)) && Instant.now().isBefore(LothricKnights.animationTimer.get(rightCalf).plusMillis(1705))) {		
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
						"playsound minecraft:lothricknight.walk master @a " +
						main.getLocation().getX() + 
						" " +
						main.getLocation().getY() +
						" " + main.getLocation().getZ() + " 0.5 1"
						);
			}
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			OutOfBounds.combined(leftFoot, leftFoot.getHeadPose(), 0, 0, 10);

			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			OutOfBounds.combined(rightFoot, rightFoot.getHeadPose(), 0, 0, 10);
			
		} catch (NullPointerException event) {}
	}
}