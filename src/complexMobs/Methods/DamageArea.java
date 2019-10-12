package complexMobs.Methods;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.LothricKnight.Methods.EnemyDMG;
import complexMobs.Main.ComplexMob;
import complexMobs.Mobs.LothricKnight;

public class DamageArea {
	
	//Legacy
	public static void legacy(ComplexMob mob, ArmorStand weapon, double weaponLength, Instant weaponTimer, int startMilli, int endMilli, Vector weaponPosition, double yaw, boolean particles, double damage, double knockHorz, double knockVert) {
		if (Instant.now().isAfter(weaponTimer.plusMillis(startMilli)) && Instant.now().isBefore(weaponTimer.plusMillis(endMilli))) {

			//HyperArmor
			mob.hyperArmor = true;
			
			//Sword hit point list
			Collection<Vector> newAttackAreaPtsList = new ArrayList<>();
		
			//Sword hilt offset from weapon stand
			Vector weaponHilt = weaponPosition.clone();
			weaponHilt.setY(weaponHilt.getY() + 1.5);
			
			//Sword tip offset from weapon hilt
			Vector weaponTip = new Vector(0,0,weaponLength);
			final EulerAngle weaponAngle = weapon.getHeadPose();
			weaponTip.rotateAroundX(weaponAngle.getX());
			weaponTip.rotateAroundY(-weaponAngle.getY());
			weaponTip.rotateAroundZ(-weaponAngle.getZ());
			weaponTip.rotateAroundAxis(new Vector(0,1,0), -yaw);
						
			//10 points between hilt and tip
			for (int m = 0; !(m > 10); m++) {
				Vector weaponHB = weaponHilt.clone();
				weaponHB.add(weaponTip.clone().multiply(m * 0.1));	
				newAttackAreaPtsList.add(weaponHB);
				
				//Hit box points between last slash frame
				if (mob.attackAreaPts != null) {
					Collection<Vector> slashPtsList = mob.attackAreaPts;
					Vector opposPt = (Vector) slashPtsList.toArray()[m];
					//Remove everything from the pt but its offset from the weapon tip
					opposPt.subtract(weaponHilt.clone().add(weaponTip.clone().multiply(m * 0.1)));
					
					//Calculate and use the points in between
					for (int b = 0; !(b > 10); b++) {
						//Add curve offset at right angle to line
						Vector inbSwordHB = weaponHB.clone();
						inbSwordHB.add(opposPt.clone().multiply(b * 0.1));	
						
						//Effects and damage
						if (particles) mob.main.getWorld().spawnParticle(Particle.END_ROD, mob.main.getLocation().add(inbSwordHB), 0);
						EnemyDMG.normal((LothricKnight) mob, inbSwordHB, damage, knockHorz, knockVert);
					}
				}
			}
			
			mob.attackAreaPts = newAttackAreaPtsList;
		}
		else {
			mob.hyperArmor = false;
		}
	}
	
	

	//Normal
	public static void normal(ComplexMob mob, ArmorStand weapon, double weaponLength, double backRatio, Instant weaponTimer, int startMilli, int endMilli, Vector weaponPosition, double yaw, boolean particles, double damage, double knockHorz, double knockVert) {
		if (Instant.now().isAfter(weaponTimer.plusMillis(startMilli)) && Instant.now().isBefore(weaponTimer.plusMillis(endMilli))) {

			//HyperArmor
			mob.hyperArmor = true;
			
			//Sword hit point list
			Collection<Vector> newAttackAreaPtsList = new ArrayList<>();
		
			//Sword hilt offset from weapon stand
			Vector weaponHilt = weaponPosition.clone();
			weaponHilt.setY(weaponHilt.getY() + 1.5);
			
			//Sword tip offset from weapon hilt
			Vector weaponTip = new Vector(0,0,weaponLength);
			final EulerAngle weaponAngle = weapon.getHeadPose();
			weaponTip.rotateAroundX(weaponAngle.getX());
			weaponTip.rotateAroundY(-weaponAngle.getY());
			weaponTip.rotateAroundZ(-weaponAngle.getZ());
			weaponTip.rotateAroundAxis(new Vector(0,1,0), -yaw);
			
			//10 points between hilt and tip
			for (int ptsA = 0; !(ptsA > 10); ptsA++) {
				Vector weaponHB = weaponHilt.clone();
				weaponHB.add(weaponTip.clone().multiply(ptsA * 0.1));	
				newAttackAreaPtsList.add(weaponHB);
				
				//Hit box points between last slash frame
				if (mob.attackAreaPts != null) {
					Collection<Vector> slashPtsList = mob.attackAreaPts;
					Vector opposPt = (Vector) slashPtsList.toArray()[ptsA];
					//Remove everything from the point but its offset from the weapon tip
					opposPt.subtract(weaponHilt.clone().add(weaponTip.clone().multiply(ptsA * 0.1)));
					
					//Use 10 points on a line in between the each of the previous points and its corresponding new point
					for (int ptsIBW = 0; !(ptsIBW > 10); ptsIBW++) {
						Vector inbSwordHB = weaponHB.clone();
						inbSwordHB.add(opposPt.clone().multiply(ptsIBW * 0.1));	
						
						//Effects and damage
						if (particles) mob.main.getWorld().spawnParticle(Particle.END_ROD, mob.main.getLocation().add(inbSwordHB), 0);
						EnemyDMG.normal((LothricKnight) mob, inbSwordHB, damage, knockHorz, knockVert);
					}
				}
			}
			
			mob.attackAreaPts = newAttackAreaPtsList;
		}
		else {
			mob.hyperArmor = false;
		}
	}
	
	//Shield
	public static void shield(ComplexMob mob, ArmorStand weapon, double shieldLength, Instant weaponTimer, int startMilli, int endMilli, Vector shieldPosition, double yaw, boolean particles, double damage, double knockHorz, double knockVert) {
		if (Instant.now().isAfter(weaponTimer.plusMillis(startMilli)) && Instant.now().isBefore(weaponTimer.plusMillis(endMilli))) {

			//HyperArmor
			mob.hyperArmor = true;
			
			//Sword hit point list
			Collection<Vector> newAttackAreaPtsList = new ArrayList<>();
		
			//Sword hilt offset from weapon stand
			Vector shieldHandle = shieldPosition.clone();
			shieldHandle.setY(shieldHandle.getY() + 1.5);
			
			//Sword tip offset from weapon hilt
			Vector shieldTip = new Vector(0,shieldLength,0);
			final EulerAngle weaponAngle = weapon.getHeadPose();
			shieldTip.rotateAroundX(weaponAngle.getX());
			shieldTip.rotateAroundY(-weaponAngle.getY());
			shieldTip.rotateAroundZ(-weaponAngle.getZ());
			shieldTip.rotateAroundAxis(new Vector(0,1,0), -yaw);
			
			//10 points between hilt and tip
			for (int ptsA = 0; !(ptsA > 10); ptsA++) {
				Vector weaponHB = shieldHandle.clone();
				weaponHB.add(shieldTip.clone().multiply(ptsA * 0.1));	
				newAttackAreaPtsList.add(weaponHB);
				
				//Hit box points between last slash frame
				if (mob.attackAreaPts != null) {
					Collection<Vector> slashPtsList = mob.attackAreaPts;
					Vector opposPt = (Vector) slashPtsList.toArray()[ptsA];
					//Remove everything from the point but its offset from the weapon tip
					opposPt.subtract(shieldHandle.clone().add(shieldTip.clone().multiply(ptsA * 0.1)));
					
					//Use 10 points on a line in between the each of the previous points and its corresponding new point
					for (int ptsIBW = 0; !(ptsIBW > 10); ptsIBW++) {
						Vector inbShieldHB = weaponHB.clone();
						inbShieldHB.add(opposPt.clone().multiply(ptsIBW * 0.1));	
						
						//Effects and damage
						if (particles) mob.main.getWorld().spawnParticle(Particle.END_ROD, mob.main.getLocation().add(inbShieldHB), 0);
						EnemyDMG.normal((LothricKnight) mob, inbShieldHB, damage, knockHorz, knockVert, 1, 50);
					}
				}
			}
			
			mob.attackAreaPts = newAttackAreaPtsList;
		}
		else {
			mob.hyperArmor = false;
		}
	}
}
