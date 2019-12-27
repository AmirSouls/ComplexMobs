package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.Mobs.LothricKnight;

public class Stance {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			knight.DirectionAndMovement(0, 0, true);
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(55))) {
				//Stop sound
				if (knight.soundIsOn("lothricknight.walk")) {
					knight.playSound("lothricknight.walk", knight.main.getLocation(), 3, 1, 1);
					knight.soundOff("lothricknight.walk");
				}
			}
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(600))) {
				double distance3D = knight.target.getLocation().distance(knight.main.getLocation());
				if (distance3D > 6) {
					//Thrust attack!
					knight.activeAction = "StanceThrust";
					ResetTimers.reset(knight);
				}
				else {
					ResetTimers.reset(knight);
					knight.isAttacking = false;
				}
			}
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(5000))) {
				ResetTimers.reset(knight);
			}
			
			
			//Pelvis
			knight.pelvisPosition = new Vector(0,0.5,0);
			//Set position based on current time
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(300))) {
				knight.pelvisPosition = new Vector(0, .5 + (.001 * (knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
			}
			else if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(300))) {
				knight.pelvisPosition = new Vector(0, .2, 0);
			}
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, -5, 55, 10, 0, 305);
			
			
			//Chest, head, cape and shield on back
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 0, 0, 0, 50, 50, 40, 0, 305);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.head, 0, 0, 0, 30, 0, 0, 0, 305);
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, 0, 0, 0, -35, 70, -30, 0, 305);
			
			knight.partPosition(knight.shield, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-.4,.9,-.1), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 0, 0, 0, 180, -30, -30, 0, 305);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftElbow, 0, 0, 0, -90, 70, 0, 0, 305);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 0, 0, 0, -145, 160, 0, 0, 305);
		
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, 0, 0, 0, -130, 160, 0, 0, 305);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, 0, 0, 0, -40, 0, 45, 0, 305);
			
			knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftHand, 0, 0, 0, 120, -40, 0, 0, 305);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, 0, 0, 0, 0, 45, 0, 305);
			
			knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 0, 0, 0, 7, -15, 60, 0, 305);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, 0, 0, 0, -70, 20, 0, 0, 205);
			knight.legacyAnimate(knight.leftThigh, 0, 0, 0, -45, -10, 0, 205, 305);

			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, 0, 0, 0, -25, 110, 0, 0, 305);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 0, 0, 0, 10, 20, -5, 0, 205);
			knight.legacyAnimate(knight.leftCalf, 10, 20, -5, 10, -10, -15, 205, 305);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 0, 0, 0, 90, 60, 90, 0, 305);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 10, 0, 0, 10, 0, 0, 305);
		
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightFoot, 0, 70, 0, 0, 70, 0, 0, 300);
		
		} catch (NullPointerException event) {}
	}
}