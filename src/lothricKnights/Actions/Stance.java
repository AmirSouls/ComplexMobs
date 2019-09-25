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
import lothricKnights.Methods.PartPositioning;
import lothricKnights.Methods.ResetTimers;

public class Stance {
	
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
			
			//Get yaw
			double yaw = main.getLocation().getYaw() / 57.2957795;
			
			//Initialize positions
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
				
				double distance3D = target.getLocation().distance(main.getLocation());
				Location difference = target.getLocation().subtract(main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance3D,distance3D,distance3D));

				Location newDirection = main.getLocation().setDirection(vector);

				vector.setY(0);

				main.teleport(newDirection.add(vector.multiply(0)));
			
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(55))) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.walk master @a " +
							main.getLocation().getX() + 
							" " +
							main.getLocation().getY() +
							" " + main.getLocation().getZ() + " 1 1"
							);
				}
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(600))) {
					if (distance3D > 6) {
						//Thrust attack!
						LothricKnights.activeAction.put(main, "StanceThrust");
						ResetTimers.resetTimers(main);
					}
					else {
						ResetTimers.resetTimers(main);
						LothricKnights.isAttacking.put(main, false);
					}
				}
				
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(5000))) {
					ResetTimers.resetTimers(main);
				}
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(300))) {
					pelvisPosition = new Vector(0, .5 + (.001 * (LothricKnights.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
				}
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(300))) {
					pelvisPosition = new Vector(0, .2, 0);
				}
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				Animate.fromTo(pelvis, 0, 0, 0, -5, 55, 10, 0, 305);
			}
			
			//Chest, head, cape and shield on back
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			Animate.fromTo(chest, 0, 0, 0, 50, 50, 40, 0, 305);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			Animate.fromTo(head, 0, 0, 0, 30, 0, 0, 0, 305);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			Animate.fromTo(cape, 0, 0, 0, -35, 70, -30, 0, 305);
			
			PartPositioning.position(shield, chestPosition, chest.getHeadPose(), new Vector(-.4,.9,-.1), main.getLocation(), yaw);
			Animate.fromTo(shield, 0, 0, 0, 180, -30, -30, 0, 305);
			
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
			Animate.fromTo(leftElbow, 0, 0, 0, -90, 70, 0, 0, 305);
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
			Animate.fromTo(rightElbow, 0, 0, 0, -145, 160, 0, 0, 305);
		
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(leftArm, 0, 0, 0, -130, 160, 0, 0, 305);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(rightArm, 0, 0, 0, -40, 0, 45, 0, 305);
			
			PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(leftHand, 0, 0, 0, 120, -40, 0, 0, 305);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(rightHand, 0, 0, 0, 0, 0, 45, 0, 305);
			
			PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
			Animate.fromTo(sword, 0, 0, 0, 7, -15, 60, 0, 305);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(leftThigh, 0, 0, 0, -70, 20, 0, 0, 205);
			Animate.fromTo(leftThigh, 0, 0, 0, -45, -10, 0, 205, 305);

			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(rightThigh, 0, 0, 0, -25, 110, 0, 0, 305);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(leftCalf, 0, 0, 0, 10, 20, -5, 0, 205);
			Animate.fromTo(leftCalf, 10, 20, -5, 10, -10, -15, 205, 305);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(rightCalf, 0, 0, 0, 90, 60, 90, 0, 305);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(leftFoot, 0, 10, 0, 0, 10, 0, 0, 305);
		
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(rightFoot, 0, 70, 0, 0, 70, 0, 0, 300);
		
		} catch (NullPointerException event) {}
	}
}