package lothricKnights.Actions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import lothricKnights.Main.LothricKnights;
import lothricKnights.Methods.Animate;
import lothricKnights.Methods.EnemyDMG;
import lothricKnights.Methods.PartPositioning;
import lothricKnights.Methods.ResetTimers;
import lothricKnights.SpecialAnimations.HeadZeroAnimation;

public class LeftSlash {
	
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

				vector.setY(0);
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(350))) {
					main.teleport(newDirection.add(vector.multiply(0.15 * distance)));
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(600))) {
					Vector direction = main.getLocation().getDirection();
					direction.setY(0);
					main.teleport(main.getLocation().add(direction.multiply(0)));
				}
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {
					ResetTimers.resetTimers(main);
					LothricKnights.isAttacking.put(main, false);
				}
				
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.5,0);
				
				//Set position based on current time
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(150))) {
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(600))) {
					pelvisPosition = new Vector(0, .5 + (.001 * (LothricKnights.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {
					pelvisPosition = new Vector(0, -.1, 0);
				}
				//Reset
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {
					LothricKnights.animationTimer.put(pelvis, Instant.now());
					pelvisPosition = new Vector(0,0.5,0);
				}
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				Animate.fromTo(pelvis, 0, 0, 0, 0, -80, 0, 500, 605);
				Animate.fromTo(pelvis, 0, 0, 0, 0, 0, 0, 0, 505);
			}
			
			//Chest and head: TODO: Animate the head looking down from slash attack, find a way to make a cool special cape thing
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			Animate.fromTo(chest, 0, 0, 0, 14, 14, 0, 0, 355);
			Animate.fromTo(chest, 14, 14, 0, 14, -80, 0, 350, 505);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			HeadZeroAnimation.animate(head, main);	
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			Animate.fromTo(cape, 10, 0, 0, 30, 0, -90, 0, 505);
			Animate.fromTo(cape, 30, 0, -90, 100, -80, 0, 550, 655);
			Animate.fromTo(cape, 100, -80, 0, 10, -70, 0, 650, 1000);
			
			//Arms: TODO: Basically done, just need to check left side
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
			Animate.fromTo(rightElbow, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
			Animate.fromTo(rightElbow, 0, 0, 20, 0, 180, 110, 0, 155);
			Animate.fromTo(rightElbow, 0, 180, 110, 0, 180, 135, 150, 305);
			Animate.fromTo(rightElbow, 0, 180, 135, 0, -70, 105, 300, 405);
			Animate.fromTo(rightElbow, 0, -70, 105, -90, -45, 90, 400, 505);
			Animate.fromTo(rightElbow, -90, -45, 90, -80, -80, 80, 500, 605);
			Animate.fromTo(rightElbow, -80, -80, 80, 0, -80, 0, 600, 655);
		
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(leftArm, 0, 0, -20, 0, 0, -20, 0, 1000);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(rightArm, 0, 0, 20, -20, 180, 145, 0, 155);
			Animate.fromTo(rightArm, -20, 180, 145, -20, 180, 210, 150, 305);
			Animate.fromTo(rightArm, -20, 180, 210, -20, 180, 185, 300, 405);
			Animate.fromTo(rightArm,-20, 180, 185, -135, 0, 0, 400, 505);
			Animate.fromTo(rightArm, -135, 0, 0, -50, 20, -80, 500, 605);
			Animate.fromTo(rightArm, -50, 20, -80, 0, -30, -70, 600, 655);
			
			leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(leftHand, 0, 0, -20, 0, 0, -20, 0, 1000);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(rightHand, 0, 0, 20, -20, 180, 145, 0, 155);
			Animate.fromTo(rightHand, -20, 180, 145, 0, 180, 210, 150, 305);
			Animate.fromTo(rightHand, -20, 180, 210, -20, 180, 185, 300, 405);
			Animate.fromTo(rightHand, -20, 180, 185, -90, 0, -90, 400, 505);
			Animate.fromTo(rightHand, -90, 0, -90, -50, 20, -80, 500, 605);
			Animate.fromTo(rightHand, -50, 20, -80, 0, -30, -70, 600, 655);
			
			swordPosition = PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
			Animate.fromTo(sword, 0, 0, 0, -70, 60, 70, 0, 155);
			Animate.fromTo(sword, -70, 60, 70, 15, -95, 0, 150, 305);
			Animate.fromTo(sword, 15, -95, 0, 185, 30, -10, 300, 355);
			Animate.fromTo(sword, 185, 30, -10, -90, 0, -40, 400, 455);
			Animate.fromTo(sword, -90, 0, -40, -20, 15, -55, 500, 605);;
			Animate.fromTo(sword, -20, 15, -55, 100, -30, -70, 600, 655);;
			
			PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
			Animate.fromTo(shield, 10, 50, 0, 10, 50, 0, 0, 1000);
			
			//Legs: Finished :)
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(leftThigh, 0, 0, 0, 30, -45, -30, 0, 155);
			Animate.fromTo(leftThigh, 30, -45, -30, 50, -45, -50, 150, 355);
			Animate.fromTo(leftThigh, 50, -45, -50, -75, -110, 0, 350, 605);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(rightThigh, 0, 0, 0, -45, 30, 0, 0, 305);
			Animate.fromTo(rightThigh, -45, 30, 0, -75, -15, 0, 300, 605);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(leftCalf, 0, 0, 0, 40, -45, -40, 0, 155);
			Animate.fromTo(leftCalf, 40, -45, -40, 60, -10, 0, 150, 355);
			Animate.fromTo(leftCalf, 60, -10, 0, 10, -110, 0, 350, 605);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(rightCalf, 0, 0, 0, 15, 60, 0, 0, 205);
			Animate.fromTo(rightCalf, 15, 60, 0, 15, 0, 5, 200, 605);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(leftFoot, 0, -45, 0, 30, 0, 0, 0, 305);
			Animate.fromTo(leftFoot, 0, 30, 0, 0, -90, 0, 300, 605);
		
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(leftFoot, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			//Slash start sound
			if (Instant.now().isAfter(LothricKnights.animationTimer.get(sword).plusMillis(500)) && Instant.now().isBefore(LothricKnights.animationTimer.get(sword).plusMillis(555))) {
				//Slash sound
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
					"playsound minecraft:lothricknight.slash master @a " +
					main.getLocation().getX() + 
					" " +
					main.getLocation().getY() +
					" " + main.getLocation().getZ() + " 0.5 1"
					);
				//Grunt sound
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
						"playsound minecraft:lothricknight.grunt master @a " +
						main.getLocation().getX() + 
						" " +
						main.getLocation().getY() +
						" " + main.getLocation().getZ() + " 0.5 1"
						);
				//Step sound
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
						"playsound minecraft:lothricknight.walk master @a " +
						main.getLocation().getX() + 
						" " +
						main.getLocation().getY() +
						" " + main.getLocation().getZ() + " 0.5 1"
						);
			}
			
			//Attack frames: Appears as 9 in 60fps, but is really 3 in game tick speed.
			if (Instant.now().isAfter(LothricKnights.animationTimer.get(sword).plusMillis(500)) && Instant.now().isBefore(LothricKnights.animationTimer.get(sword).plusMillis(655))) {
				//Enable hyperarmor
				LothricKnights.hyperArmor.put(main, true);
				
				//Sword hit point list
				Collection<Vector> newSlashPtsList = new ArrayList<>();
			
				//Sword hilt offset from sword stand
				Vector swordHilt = new Vector(swordPosition.getX(), swordPosition.getY() + 1.5, swordPosition.getZ());
				main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(swordHilt), 0);
				
				//Sword tip offset from sword hilt
				Vector swordTip = new Vector(0,0,2);
				final EulerAngle swordAngle = sword.getHeadPose();
				swordTip.rotateAroundX(swordAngle.getX());
				swordTip.rotateAroundY(-swordAngle.getY());
				swordTip.rotateAroundZ(-swordAngle.getZ());
				swordTip.rotateAroundAxis(new Vector(0,1,0), -yaw);
				
				//10 points between hilt and tip for the blade
				for (int m = 0; m < 11; m++) {
					Vector swordHB = swordHilt.clone();
					swordHB.add(swordTip.clone().multiply(m * 0.1));	
					newSlashPtsList.add(swordHB);
					
					//Hit box points between last slash frame
					if (LothricKnights.slashPts.containsKey(main)) {
						Collection<Vector> slashPtsList = LothricKnights.slashPts.get(main);
						Vector opposPt = (Vector) slashPtsList.toArray()[m];
						//Remove everything from the pt but its offset from the sword tip
						opposPt.subtract(swordHilt.clone().add(swordTip.clone().multiply(m * 0.1)));
						//Calculate and use the points in between
						for (int b = 1; b < 10; b++) {
							//Add curve offset at right angle to line
							Vector inbSwordHB = swordHB.clone();
							inbSwordHB.add(opposPt.clone().multiply(b * 0.1));	
							
							//Effects and dmg
							main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(inbSwordHB), 0);
							EnemyDMG.normal(main, inbSwordHB, 9, .4, .2);
						}
					}
					
					//Effects and dmg
					main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(swordHB), 0);
					EnemyDMG.normal(main, swordHB, 9, .4, .2);
				}
				
				LothricKnights.slashPts.put(main, newSlashPtsList);
			}
			else {
				LothricKnights.hyperArmor.put(main, false);
			}
		} catch (NullPointerException event) {}
	}
}