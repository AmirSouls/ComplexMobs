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

public class RightSlash {
	
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
				
				double distance3D = target.getLocation().distance(main.getLocation());
				Location difference = target.getLocation().subtract(main.getLocation());
				Vector vector = difference.toVector().divide(new Vector(distance3D,distance3D,distance3D));

				Location newDirection = main.getLocation().setDirection(vector);

				vector.setY(0);
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(505))) {
					main.teleport(newDirection.add(vector.multiply(0.15 * distance3D)));
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(605))) {
					Vector direction = main.getLocation().getDirection();
					direction.setY(0);
					main.teleport(main.getLocation().add(direction.multiply(0)));
				}
				else if (Instant.now().isAfter(LothricKnights.animationTimer.get(pelvis).plusMillis(1000))) {
					if (distance3D < 4 && LothricKnights.stamina.get(main) > 0 && Math.random() < .7) {
						ResetTimers.resetTimers(main);
						LothricKnights.activeAction.put(main, "LeftSlash");
						LothricKnights.stamina.put(main, LothricKnights.stamina.get(main) - 35);
						LothricKnights.staminaUse.put(main, Instant.now());
					}
					else {
						ResetTimers.resetTimers(main);
						LothricKnights.isAttacking.put(main, false);
					}
				}
			}
			
			if (pelvis != null) {
				//Default to this
				pelvisPosition = new Vector(0,0.29,0);
				
				//Set position based on current time
				if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(305))) {
				}
				else if (Instant.now().isBefore(LothricKnights.animationTimer.get(pelvis).plusMillis(505))) {
					pelvisPosition = new Vector(0, .29 + (.0001 * (LothricKnights.animationTimer.get(pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
				}
				pelvisPosition.rotateAroundY(-yaw);
				pelvis.teleport(main.getLocation().add(pelvisPosition));
				Animate.fromTo(pelvis, 0, 0, 0, 5, -45, 0, 0, 305);
				Animate.fromTo(pelvis, 5, -45, 0, -5, 40, 0, 305, 505);
			}
			
			//18,526 to 18,538
			
			//Chest and head
			chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), main.getLocation(), yaw);
			Animate.fromTo(chest, 14, -80, 0, 20, -45, 0, 0, 305);
			Animate.fromTo(chest, 20, -45, 0, 55, -20, 0, 305, 505);
			Animate.fromTo(chest, 55, -20, 0, 55, 45, 0, 505, 655);
			Animate.fromTo(chest, 55, 45, 0, 55, 85, 0, 655, 805);
			
			PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), main.getLocation(), yaw);
			Animate.fromTo(head, 0, 0, 0, 20, -20, 0, 0, 305);
			Animate.fromTo(head, 20, -20, 0, 30, 0, 0, 305, 505);
			Animate.fromTo(head, 30, 0, 0, 10, 10, 0, 505, 655);
			Animate.fromTo(head, 10, 10, 0, 20, 20, 0, 655, 805);
			
			PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), main.getLocation(), yaw);
			Animate.fromTo(cape, 10, -70, 0, -90, -30, 110, 0, 305);
			Animate.fromTo(cape, -90, -30, 110, 85, -15, 0, 305, 505);
			Animate.fromTo(cape, 85, -15, 0, 110, 30, 90, 505, 655);
			Animate.fromTo(cape, 110, 30, 90, 30, 0, 0, 655, 3000);
			
			//Arms
			leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), main.getLocation(), yaw);
			Animate.fromTo(leftElbow, 0, 0, 0, 40, -55, 0, 0, 305);
			Animate.fromTo(leftElbow, 40, -55, 0, 40, -40, 0, 305, 505);
			Animate.fromTo(leftElbow, 40, -40, 0, 0, 90, -20, 505, 805);
			
			rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), main.getLocation(), yaw);
			Animate.fromTo(rightElbow, 0, -80, 0, -50, -100, 0, 0, 305);
			Animate.fromTo(rightElbow, -50, -100, 0, -50, -80, 0, 305, 505);
			Animate.fromTo(rightElbow, -50, -80, 0, -50, -30, 0, 505, 555);
			Animate.fromTo(rightElbow, -50, -30, 0, -50, 5, 0, 555, 605);
			Animate.fromTo(rightElbow, -50, 5, 0, -50, 70, 0, 605, 655);
			Animate.fromTo(rightElbow, -50, 70, 0, -40, 170, 0, 655, 805);
			
			leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(leftArm, 0, 0, -20, -30, -55, 0, 0, 305);
			Animate.fromTo(leftArm, -30, -55, 0, -100, -55, 0, 305, 505);
			Animate.fromTo(leftArm, -100, -55, 0, 0, 0, 0, 505, 805);
			
			rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), main.getLocation(), yaw);
			Animate.fromTo(rightArm, 0, -30, -70, -70, -130, 0, 0, 305);
			Animate.fromTo(rightArm, -70, -130, 0, -80, -70, 0, 305, 505);
			Animate.fromTo(rightArm, -80, -70, 0, -80, -30, 0, 505, 555);
			Animate.fromTo(rightArm, -80, -30, 0, -80, 5, 0, 555, 605);
			Animate.fromTo(rightArm, -80, 5, 0, -60, 70, 0, 605, 655);
			Animate.fromTo(rightArm, -60, 70, 0, -50, 165, 0, 655, 805);
			
			leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(leftHand, 0, 0, -20, 0, 55, 0, 0, 305);
			Animate.fromTo(leftHand, 0, 55, 0, 0, 90, 0, 505, 805);
			
			rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), main.getLocation(), yaw);
			Animate.fromTo(rightHand, 0, -30, -70, -80, -170, 0, 0, 305);
			Animate.fromTo(rightHand, -80, -170, 0, -80, -80, 0, 305, 505);
			Animate.fromTo(rightHand, -80, -80, 0, -80, -20, 0, 505, 555);
			Animate.fromTo(rightHand, -80, -20, 0, -80, 5, 0, 555, 605);
			Animate.fromTo(rightHand, -80, 5, 0, -60, 70, 0, 605, 655);
			Animate.fromTo(rightHand, -60, 70, 0, -50, 160, 0, 655, 805);

			swordPosition = PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), main.getLocation(), yaw);
			Animate.fromTo(sword, 100, -30, -70, 10, -220, 0, 0, 305);
			Animate.fromTo(sword, 10, -220, 0, 0, -130, 0, 305, 505);
			Animate.fromTo(sword, 0, -130, 0, 0, -85, 0, 505, 555);
			Animate.fromTo(sword, 0, -85, 0, 0, -30, 0, 555, 605);
			Animate.fromTo(sword, 0, -30, 0, 30, 40, 0, 605, 655);
			Animate.fromTo(sword, 30, 40, 0, 30, 85, 0, 655, 805);
			
			
			PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), main.getLocation(), yaw);
			Animate.fromTo(shield, 10, 50, 0, 0, 15, 0, 0, 305);
			Animate.fromTo(shield, 0, 15, 0, 0, 40, 10, 305, 505);
			Animate.fromTo(shield, 0, 40, 10, 0, 90, 10, 605, 805);
			
			//Legs
			leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(leftThigh, -50, -75, -110, -40, -45, 0, 0, 305);
			Animate.fromTo(leftThigh, -40, -45, 0, -50, -10, 0, 305, 505);
			
			rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), main.getLocation(), yaw);
			Animate.fromTo(rightThigh, -75, -15, 0, -10, 35, 0, 0, 305);
			Animate.fromTo(rightThigh, -10, 35, 0, 45, -75, 0, 305, 505);
			
			leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(leftCalf, 10, -110, 0, 15, -45, 0, 0, 305);
			Animate.fromTo(leftCalf, 15, -45, 0, -40, -10, 0, 305, 505);
			
			rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), main.getLocation(), yaw);
			Animate.fromTo(rightCalf, 15, 0, 5, 20, 40, 0, 0, 305);
			Animate.fromTo(rightCalf, 20, 40, 0, 15, 80, -10, 305, 505);
			
			PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(leftFoot, 0, -90, 0, 0, -25, 0, 0, 305);
			Animate.fromTo(leftFoot, 0, -25, 0, 0, 15, 0, 305, 505);
		
			PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), main.getLocation(), yaw);
			Animate.fromTo(rightFoot, 0, 0, 0, 0, 0, 30, 0, 305);
			Animate.fromTo(rightFoot, 0, 30, 0, 20, 80, 0, 305, 505);
			
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
			if (Instant.now().isAfter(LothricKnights.animationTimer.get(sword).plusMillis(500)) && Instant.now().isBefore(LothricKnights.animationTimer.get(sword).plusMillis(705))) {
				//Enable hyperarmor
				LothricKnights.hyperArmor.put(main, true);
				
				//Sword hit point list
				Collection<Vector> newSlashPtsList = new ArrayList<>();
			
				//Sword hilt offset from sword stand
				Vector swordHilt = new Vector(swordPosition.getX(), swordPosition.getY() + 1.5, swordPosition.getZ());
				//main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(swordHilt), 0);
				
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
							//main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(inbSwordHB), 0);
							EnemyDMG.normal(main, inbSwordHB, 9, .4, .2);
						}
					}
					
					//Effects and dmg
					//main.getWorld().spawnParticle(Particle.END_ROD, main.getLocation().add(swordHB), 0);
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