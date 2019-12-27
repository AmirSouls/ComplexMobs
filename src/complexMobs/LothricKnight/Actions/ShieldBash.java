package complexMobs.LothricKnight.Actions;

import java.time.Instant;

import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.ResetTimers;
import complexMobs.LothricKnight.SpecialAnimations.HeadZeroAnimation;
import complexMobs.Mobs.LothricKnight;

public class ShieldBash {
	
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Direction handling
			double distance3D = knight.target.getLocation().distance(knight.main.getLocation());
			
			knight.DirectionAndMovementTimed(distance3D * 0.14, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 0, 705, true);
			if (distance3D > 3) knight.DirectionAndMovementTimed(0.6, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 700, 755, true);
			else knight.DirectionAndMovementTimed(0, 0, knight.animationTimer.get(knight.pelvis), knight.animationTimer.get(knight.pelvis), 705, 805, true);
			if (Instant.now().isAfter(knight.animationTimer.get(knight.pelvis).plusMillis(1200))) {
				ResetTimers.reset(knight);
				knight.isAttacking = false;
					
				//Re-enable all sounds
				knight.enableAllSounds();
			}
			
			//Pelvis
			knight.pelvisPosition = new Vector(0,0.5,0);
			
			//Set position based on current time
			if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(150))) {
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(600))) {
				knight.pelvisPosition = new Vector(0, .5 + (.001 * (knight.animationTimer.get(knight.pelvis).toEpochMilli() - Instant.now().toEpochMilli())), 0);
			}
			else if (Instant.now().isBefore(knight.animationTimer.get(knight.pelvis).plusMillis(1500))) {
				knight.pelvisPosition = new Vector(0, -.1, 0);
			}
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, -10, -20, 0, -20, -20, 0, 0, 655);
			knight.legacyAnimate(knight.pelvis, -20, -20, 0, 0, 80, 0, 655, 755);
			
			//Chest and head
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, -10, -20, 0, -20, -20, 0, 0, 655);
			knight.legacyAnimate(knight.chest, -20, -20, 0, 20, 80, 0, 655, 755);
			
			knight.partPosition(knight.head, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.8,0), knight.main.getLocation(), knight.yaw);
			HeadZeroAnimation.animate(knight.head, knight);	
			
			knight.partPosition(knight.cape, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0,0.9,-0.2), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.cape, 10, 0, 0, 30, 0, -90, 0, 505);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftElbow, 0, 0, -20, 10, 0, -70, 0, 305);
			knight.legacyAnimate(knight.leftElbow, 10, 0, -70, 40, 0, -110, 305, 655);
			knight.legacyAnimate(knight.leftElbow, 40, 0, -110, 30, 0, 0, 655, 755);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 0, 0, 15, -10, 0, 15, 0, 305);
			knight.legacyAnimate(knight.rightElbow, -10, 0, 15, 75, 0, 0, 305, 655);
		
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, 0, 0, -10, -20, 0, 130, 0, 305);
			knight.legacyAnimate(knight.leftArm, -20, 0, 130, 10, 0, 150, 305, 655);
			knight.legacyAnimate(knight.leftArm, 10, 0, 150, -110, 0, 180, 655, 755);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, 0, 0, 10, -15, 0, 10, 0, 305);
			knight.legacyAnimate(knight.rightArm, -15, 0, 10, 50, -40, 0, 305, 655);
			
			knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftHand, 0, 0, -10, -20, 0, 130, 0, 305);
			knight.legacyAnimate(knight.leftHand, -20, 0, 130, 10, 0, 150, 305, 655);
			knight.legacyAnimate(knight.leftHand, 10, 0, 150, -60, 0, 180, 655, 755);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, 0, 10, -15, 0, 10, 0, 305);
			knight.legacyAnimate(knight.rightHand, -15, 0, 10, 50, -40, 0, 305, 655);
			
			knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 80, 20, 70, 0, -20, 60, 0, 655);
			knight.legacyAnimate(knight.sword, 0, -20, 60, 80, 20, 70, 655, 1000);
			
			knight.shieldPosition = knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 0, 0, 0, -110, 80, 0, 0, 305);
			knight.legacyAnimate(knight.shield, -110, 80, 0, -140, 100, 0, 305, 650);
			knight.legacyAnimate(knight.shield, -140, 100, 0, -170, 80, 0, 655, 755);
			
			//Legs TODO Really need to do this
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, 0, 0, 0, 30, -45, -30, 0, 155);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, 0, 0, 0, -45, 30, 0, 0, 305);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 0, 0, 0, 40, -45, -40, 0, 155);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 0, 0, 0, 15, 60, 0, 0, 205);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, -45, 0, 30, 0, 0, 0, 305);
		
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 0, 0, 0, 0, 0, 0, 1000);
			
			//Sounds
			if (Instant.now().isAfter(knight.animationTimer.get(knight.sword).plusMillis(650)) && Instant.now().isBefore(knight.animationTimer.get(knight.sword).plusMillis(805))) {
				if (knight.soundIsOn("lothricknight.shieldbash")) {
					//Shield bash sound
					knight.playSound("lothricknight.slash", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.shieldbash");
				}
				
				if (knight.soundIsOn("lothricknight.walk")) {
					//Step sound
					knight.playSound("lothricknight.walk", knight.main.getLocation(), 2, 1, 1);
					knight.soundOff("lothricknight.walk");
				}
			}
			
			//Attack frames: Appears as 9 in 60fps, but is really 3 in game tick speed.
			knight.DamageAreaShield(knight, knight.shield, 1, knight.animationTimer.get(knight.shield), 600, 805, knight.shieldPosition, knight.yaw, false, 9, .8, .2);
			
		} catch (NullPointerException event) {}
	}
}