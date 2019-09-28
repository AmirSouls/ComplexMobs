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
import lothricKnights.SpecialAnimations.HeadZeroAnimation;
import lothricKnights.SpecialAnimations.PelvisZeroAnimation;


public class WalkingForward {
	
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
					main.teleport(newDirection.add(vector.multiply(0.1)));
				}
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				//Up
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(300))) {
					pelvisPosition = pelvisPosition.add(new Vector(0,.04,0));	
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(355))) {		
				}
				//Step sound
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(405))) {		
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.walk master @a " +
							main.getLocation().getX() + 
							" " +
							main.getLocation().getY() +
							" " + main.getLocation().getZ() + " 0.5 1"
							);
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(500))) {		
				}
				//Down
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(805))) {
					pelvisPosition = pelvisPosition.add(new Vector(0,-.04,0));	
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {	
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {
					LothricKnights.animationTimer.put(pelvis, Instant.now());
					pelvisPosition = new Vector(0,0.5,0);
				}
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				
				//Animation
				//Special pelvis animation
				PelvisZeroAnimation.animate(pelvis, 10);
			}
			
			//Chest, head and cape
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			GoBtwn.combined(chest, chest.getHeadPose(), 10, 10, 15, 15, 0, 0, 30.0);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, main);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			GoBtwn.combined(cape, cape.getHeadPose(), 15, 15, 0, 0, 0, 0, 30.0);
	
			//Arms, shield up or down boolean.
			if (shieldUp) {
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
				GoBtwn.combined(leftElbow, leftElbow.getHeadPose(), 15, 15, 0, 0, -10, -10, 5);
				
				rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
				GoBtwn.combined(rightElbow, rightElbow.getHeadPose(), 10, 10, 0, 0, 45, 45, 5);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
				GoBtwn.combined(leftArm, leftArm.getHeadPose(), -60, -60, 0, 0, 40, 40, 5);
				
				rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
				GoBtwn.combined(rightArm, rightArm.getHeadPose(), 0, 0, 0, 0, 40, 40, 5);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				GoBtwn.combined(leftHand, leftHand.getHeadPose(), 180, 180, 100, 100, 0, 0, 30.0);
				
				rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				GoBtwn.combined(rightHand, rightHand.getHeadPose(), 0, 0, 30.0);
				
				PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
				GoBtwn.combined(sword, sword.getHeadPose(), 0, 0, 30.0);
				
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
				GoBtwn.combined(shield, shield.getHeadPose(), 180, 180, 100, 100, 0, 0, 110);
			}
			else {
				//Arms
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
				GoBtwn.combined(leftElbow, leftElbow.getHeadPose(), 0, 0, 0, 0, -20, -20, 30.0);
				
				rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
				GoBtwn.combined(rightElbow, rightElbow.getHeadPose(), 30, 30, 0, 0, 30, 30, 30.0);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
				GoBtwn.combined(leftArm, leftArm.getHeadPose(), 0, 0, 0, 0, 10, 10, 30.0);
				
				rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
				GoBtwn.combined(rightArm, rightArm.getHeadPose(), 20, 20, 0, 0, 40, 40, 30.0);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				GoBtwn.combined(leftHand, leftHand.getHeadPose(), -10, -10, 10, 10, 5, 5, 30.0);
				
				rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
				GoBtwn.combined(rightHand, rightHand.getHeadPose(), 20, 20, 0, 0, 40, 40, 30.0);
				
				PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
				GoBtwn.combined(sword, sword.getHeadPose(), 40, 40, 20, 20, 20, 20, 30.0);
				
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
				GoBtwn.combined(shield, shield.getHeadPose(), -15, -15, 10, 10, 10, 10, 30.0);
			}
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftThigh, -15,0,0, 30,0,0, 0, 605);
			//Pause
			//Left
			Animate.fromTo(leftThigh, 30, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			Animate.reset(leftThigh, 1600);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightThigh, 30, 0, 0, -15,0,0, 0, 605);
			//Pause
			//Left
			Animate.fromTo(rightThigh, -15,0,0, 30, 0, 0, 800, 1405);
			//Pause
			//Reset
			Animate.reset(rightThigh, 1600);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftCalf, -15, 0, 0, 60, 0, 0, 0, 605);
			//Pause
			//Left
			Animate.fromTo(leftCalf, 60, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			Animate.reset(leftCalf, 1600);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightCalf, 60, 0, 0, -15, 0, 0, 0, 605);
			//Pause
			//Left
			Animate.fromTo(rightCalf, -15, 0, 0, 60, 0, 0, 800, 1405);
			//Pause
			//Reset
			Animate.reset(rightCalf, 1600);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			//Right
			Animate.fromTo(leftFoot, -15, 0, 0, 60, 0, 0, 0, 605);
			//Pause
			//Left
			Animate.fromTo(leftFoot, 60, 0, 0, -15, 0, 0, 800, 1405);
			//Pause
			//Reset
			Animate.reset(leftFoot, 1600);

			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			//Right
			Animate.fromTo(rightFoot, 60, 0, 0, -15, 0, 0, 0, 605);
			//Pause
			//Left
			Animate.fromTo(rightFoot, -15, 0, 0, 60, 0, 0, 800, 1405);
			//Pause
			//Reset
			Animate.reset(rightFoot, 1600);
			
		} catch (NullPointerException event) {}
	}
}