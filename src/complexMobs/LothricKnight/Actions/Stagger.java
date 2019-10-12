package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.FromTo;
import complexMobs.LothricKnight.Methods.GoTo;
import complexMobs.LothricKnight.Methods.PartPositioning;
import complexMobs.LothricKnight.SpecialAnimations.PelvisZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class Stagger {
	
	public static void animate(LothricKnight knight) {
		try {
			
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
			Vector leftHandPosition = new Vector(0,0,0);
			
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
				if (Instant.now().isAfter(knight.animationTimer.get(pelvis).plusMillis(1000))) {
					knight.main.teleport(newDirection.add(vector.multiply(-0.05)));
				}
			}
				{
					pelvisPosition.rotateAroundY(-yaw);
					pelvis.teleport(knight.main.getLocation().add(pelvisPosition));
					PelvisZeroAnimation.animate(pelvis, 10);
				}
				
				//Chest, head and cape
				chestPosition = PartPositioning.position(chest, pelvisPosition, pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), yaw);
				FromTo.animate(chest, 0, 0, 0, 20, 20, 0, 0, 305);
				
				PartPositioning.position(head, chestPosition, chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), yaw);
				FromTo.animate(head, 0, 0, 0, 0, 80, 20, 0, 305);
				
				PartPositioning.position(cape, chestPosition, chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), yaw);
				FromTo.animate(cape, 0, 0, 0, 95, -75, 0, 0, 305);
				
				//Arms
				leftElbowPosition = PartPositioning.position(leftElbow, chestPosition, chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), yaw);
				FromTo.animate(leftElbow, 0, 0, 0, 60, 0, -40, 0, 305);
				
				rightElbowPosition = PartPositioning.position(rightElbow, chestPosition, chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), yaw);
				FromTo.animate(rightElbow, 0, 0, 0, 0, 80, 20, 0, 305);
				
				leftArmPosition = PartPositioning.position(leftArm, leftElbowPosition, leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), yaw);
				FromTo.animate(leftArm, 0, 0, 0, 40, 0, -40, 0, 305);
				
				rightArmPosition = PartPositioning.position(rightArm, rightElbowPosition, rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), yaw);
				FromTo.animate(rightArm, 0, 0, 0, 0, 0, 0, 0, 305);
				
				leftHandPosition = PartPositioning.position(leftHand, leftArmPosition, leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				FromTo.animate(leftHand, 0, 0, 0, 40, 0, -40, 0, 305);
				
				rightHandPosition = PartPositioning.position(rightHand, rightArmPosition, rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), yaw);
				GoTo.animate(rightHand, rightHand.getHeadPose(), 0, 0, 0);
				
				PartPositioning.position(sword, rightHandPosition, rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), yaw);
			
				PartPositioning.position(shield, leftHandPosition, leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), yaw);
				
				//Legs
				leftThighPosition = PartPositioning.position(leftThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), yaw);
				FromTo.animate(leftThigh, 0, 0, 0, -40, 5, -5, 0, 305);
				
				rightThighPosition = PartPositioning.position(rightThigh, pelvisPosition, pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), yaw);
				FromTo.animate(rightThigh, 0, 0, 0, -15, 20, 0, 0, 305);
				
				leftCalfPosition = PartPositioning.position(leftCalf, leftThighPosition, leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
				FromTo.animate(leftCalf, 0, 0, 0, 5, 5, -10, 0, 305);
				
				rightCalfPosition = PartPositioning.position(rightCalf, rightThighPosition, rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), yaw);
				FromTo.animate(rightCalf, 0, 0, 0, 45, 20, 20, 0, 305);
				
				PartPositioning.position(leftFoot, leftCalfPosition, leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
				FromTo.animate(leftFoot, 0, 0, 0, 45, 20, 20, 0, 305);
			
				PartPositioning.position(rightFoot, rightCalfPosition, rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), yaw);
				FromTo.animate(rightFoot, 0, 0, 0, 0, -25, 0, 0, 305);
				
		} catch (NullPointerException event) {}
	}
}