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
	public static void process(ComplexMob mob, ArmorStand weapon, double weaponLength, Instant weaponTimer, int startMilli, int endMilli, Vector weaponPosition, double yaw, boolean particles, double damage, double knockHorz, double knockVert) {
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
						
			//10 pts between hilt and tip
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
}
