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

public class StanceThrust {
	
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
				//Charge up frames
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(355))) {
					main.teleport(newDirection.add(vector.multiply(.014 * distance)));
				}
				//Attack frames with aim
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(455))) {
					main.teleport(newDirection.add(vector.multiply(.3 * distance)));
				}
				//Attack frames without aim
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(605))) {
					Vector direction = main.getLocation().getDirection();
					direction.setY(0);
					main.teleport(main.getLocation().add(direction.multiply(.3 * distance)));
				}
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(1600))) {
					ResetTimers.resetTimers(main, -55);
					LothricKnights.isAttacking.put(main, false);
				}
				
				//Sound fx
				if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(5)) && Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(55))) {
				//Grunt sound
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
						"playsound minecraft:lothricknight.grunt master @a " +
						main.getLocation().getX() + 
						" " +
						main.getLocation().getY() +
						" " + main.getLocation().getZ() + " 1 1"
						);
				//Stance attack sound fx
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
						"playsound minecraft:lothricknight.stanceattack master @a " +
						main.getLocation().getX() + 
						" " +
						main.getLocation().getY() +
						" " + main.getLocation().getZ() + " 1 1"
						);
				}
				
			}
			
			if (pelvis != null) {
				pelvisPosition = new Vector(0,.2,0);
				
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				Animate.fromTo(pelvis, -5, 55, 10, 30, 50, 0, 0, 355);
				Animate.fromTo(pelvis, 30, 50, 0, 0, -90, 0, 355, 605);
			}
			
			//Chest, head, cape and shield on back
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			Animate.fromTo(chest, 50, 50, 40, 75, 35, 30, 0, 355);
			Animate.fromTo(chest, 75, 35, 30, 0, -90, 0, 355, 605);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			Animate.fromTo(head, 30, 0, 0, 60, 10, 0, 0, 355);
			Animate.fromTo(head, 60, 10, 0, 30, -20, 0, 355, 605);
			
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			Animate.fromTo(cape, -35, 70, -30, 90, 35, 30, 0, 355);
			Animate.fromTo(cape, 90, 35, 30, 0, -50, 30, 355, 605);
			
			PartPositioning.position(shield, chestPosition, chest.getHeadPose(), new Vector(-.4,.9,-.1), main.getLocation(), yaw);
			Animate.fromTo(shield, 180, -30, -0, 180, 40, -70, 0, 355);
			Animate.fromTo(shield, 180, 40, -70, 150, 180, 30, 355, 605);
			
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
			Animate.fromTo(leftElbow, -90, 70, 0, 0, 100, 40, 0, 355);
			Animate.fromTo(leftElbow, 0, 100, 40, 90, -50, -90, 355, 605);
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
			Animate.fromTo(rightElbow, -145, 160, 0, 125, 0, -20, 0, 355);
			Animate.fromTo(rightElbow, 125, 0, -20, -90, 0, 0, 355, 605);
		
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(leftArm, -130, 160, 0, 90, -60, 50, 0, 355);
			Animate.fromTo(leftArm, 90, -60, 50, 20, 0, 0, 355, 605);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(rightArm, -40, 0, 45, -20, 0, 70, 0, 355);
			Animate.fromTo(rightArm, 20, 0, 70, -90, -10, 0, 355, 605);
			
			PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(leftHand, 120, -40, 0, 90, -60, 50, 0, 355);
			Animate.fromTo(leftHand, 90, -60, 50, 0, 0, 0, 355, 605);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(rightHand, 0, 0, 45, 20, 0, 70, 0, 355);
			Animate.fromTo(rightArm, 20, 0, 70, -90, 0, 0, 355, 605);
			
			swordPosition = PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
			Animate.fromTo(sword, 7, -15, 60, 30, -15, 60, 0, 355);
			Animate.fromTo(sword, 30, -15, 60, 0, 0, 0, 355, 605);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(leftThigh, -10, 0, 0, 30, 40, 0, 0, 355);
			Animate.fromTo(leftThigh, 30, 40, 0, -30, -100, 30, 355, 605);

			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(rightThigh, -25, 110, -60, 50, 0, 0, 0, 355);
			Animate.fromTo(rightThigh, 50, 0, 0, 15, -70, 0, 355, 605);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(leftCalf, 10, -10, -15, 75, 30, 0, 0, 355);
			Animate.fromTo(leftCalf, 75, 30, 0, -30, -150, 0, 355, 605);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(rightCalf, 90, 60, 90, 75, 50, 0, 0, 355);
			Animate.fromTo(rightCalf, 0, 0, 0, 30, -55, 0, 355, 605);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(leftFoot, 0, 10, 0, 50, 30, 0, 0, 355);
			Animate.fromTo(leftFoot, 50, 30, 0, 0, -90, 0, 355, 605);
		
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(rightFoot, 0, 70, 0, 75, 50, 10, 0, 355);
			Animate.fromTo(rightFoot, 75, 50, 10, 0, -45, 0, 355, 605);
		
			
			//Stance Thrust:
			//21 Charge up frames, 7 ingame frames, 350 millis
			//16 Attack frames, 5 ingame frames, 250 millis
			//70 Recovery Frames, 23 ingame frames, 1150 millis
			
			//Enable Hyperarmor
			if (Instant.now().isBefore(LothricKnights.animationTimer.get(sword).plusMillis(650))) {
				LothricKnights.hyperArmor.put(main, true);
			}
			else {
				LothricKnights.hyperArmor.put(main, false);
			}
			
			//Attack frames: Appears as 9 in 60fps, but is really 3 in game tick speed.
			if (Instant.now().isAfter(LothricKnights.animationTimer.get(sword).plusMillis(350)) && Instant.now().isBefore(LothricKnights.animationTimer.get(sword).plusMillis(655))) {

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
							EnemyDMG.normal(main, inbSwordHB, 22, .7, .4);
						}
					}
					
					//Effects and dmg
					main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(swordHB), 0);
					EnemyDMG.normal(main, swordHB, 22, .7, .4);
				}
				
				LothricKnights.slashPts.put(main, newSlashPtsList);
			}
			
		} catch (NullPointerException event) {}
	}
}