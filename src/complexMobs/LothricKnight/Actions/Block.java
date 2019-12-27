package complexMobs.LothricKnight.Actions;

import org.bukkit.util.Vector;

import complexMobs.Mobs.LothricKnight;


public class Block {
	
	//For when attacked while the knight's shield is up
	public static void animate(LothricKnight knight) {
		try {
			knight.restoreVectors();
			
			//Pelvis
			knight.pelvisPosition = new Vector(0,0.4,0);
			knight.pelvisPosition.rotateAroundY(-knight.yaw);
			knight.pelvis.teleport(knight.main.getLocation().add(knight.pelvisPosition));
			knight.legacyAnimate(knight.pelvis, 0, 0, 0, 0, 0, 0, 0, 5000);
			knight.chestPosition = knight.partPosition(knight.chest, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0,-.16,0.01), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.chest, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			//Legs
			knight.leftThighPosition = knight.partPosition(knight.leftThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftThigh, 0, 0, -5, 0, 0, -5, 0, 5000);
			
			knight.rightThighPosition = knight.partPosition(knight.rightThigh, knight.pelvisPosition, knight.pelvis.getHeadPose(), new Vector(-0.17,-.42,.04), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightThigh, 0, 0, 10, 0, 0, 10, 0, 5000);
			
			knight.leftCalfPosition = knight.partPosition(knight.leftCalf, knight.leftThighPosition, knight.leftThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftCalf, 0, 0, -5, 0, 0, -5, 0, 5000);
			
			knight.rightCalfPosition = knight.partPosition(knight.rightCalf, knight.rightThighPosition, knight.rightThigh.getHeadPose(), new Vector(0,-0.6,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightCalf, 0, 0, 10, 0, 0, 10, 0, 5000);
			
			knight.partPosition(knight.leftFoot, knight.leftCalfPosition, knight.leftCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftFoot, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			knight.partPosition(knight.rightFoot, knight.rightCalfPosition, knight.rightCalf.getHeadPose(), new Vector(0,-0.585,-0.035), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightFoot, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			//Arms
			knight.leftElbowPosition = knight.partPosition(knight.leftElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(0.34,0.8,-.03), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftElbow, -10, -10, 5, -10, -10, 5, 0, 5000);
			
			knight.rightElbowPosition = knight.partPosition(knight.rightElbow, knight.chestPosition, knight.chest.getHeadPose(), new Vector(-0.34,0.9,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightElbow, 20, 0, 5, 20, 0, 5, 0, 5000);
			
			knight.leftArmPosition = knight.partPosition(knight.leftArm, knight.leftElbowPosition, knight.leftElbow.getHeadPose(), new Vector(0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.leftArm, -60, 0, 40, -60, 0, 40, 0, 5000);
			
			knight.rightArmPosition = knight.partPosition(knight.rightArm, knight.rightElbowPosition, knight.rightElbow.getHeadPose(), new Vector(-0.05,-0.51,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightArm, 10, 0, 20, 10, 0, 20, 0, 5000);
			
			knight.leftHandPosition = knight.partPosition(knight.leftHand, knight.leftArmPosition, knight.leftArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.pelvis, 100, 100, 0, 100, 100, 0, 0, 5000);
			
			knight.rightHandPosition = knight.partPosition(knight.rightHand, knight.rightArmPosition, knight.rightArm.getHeadPose(), new Vector(0,-.4,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.rightHand, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			knight.partPosition(knight.sword, knight.rightHandPosition, knight.rightHand.getHeadPose(), new Vector(.05,-.3,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.sword, 0, 0, 0, 0, 0, 0, 0, 5000);
			
			knight.partPosition(knight.shield, knight.leftHandPosition, knight.leftHand.getHeadPose(), new Vector(0,-.5,0), knight.main.getLocation(), knight.yaw);
			knight.legacyAnimate(knight.shield, 100, 0, 0, 100, 0, 0, 0, 5000);
			
			
		} catch (NullPointerException event) {}
	}
}