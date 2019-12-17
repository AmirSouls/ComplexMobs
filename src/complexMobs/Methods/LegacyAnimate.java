package complexMobs.Methods;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import complexMobs.Main.ComplexMob;
import complexMobs.Main.ComplexMobs;

public class LegacyAnimate {
	public void animate(ArmorStand part, double fX, double fY, double fZ, double tX, double tY, double tZ, int startTime, int endTime) {
		ComplexMob mob = ComplexMobs.partMob.get(part);
		
		if (Instant.now().isAfter(mob.animationTimer.get(part).plusMillis(startTime)) && Instant.now().isBefore(mob.animationTimer.get(part).plusMillis(endTime))) {
			
			double period = Instant.now().toEpochMilli() - mob.animationTimer.get(part).plusMillis(startTime).toEpochMilli();
			double progress = period / (endTime - startTime);
			
			double dX = tX - fX;
			double dY = tY - fY;
			double dZ = tZ - fZ;
			
			double rd = 57.2958;
			
			EulerAngle newPose = new EulerAngle(
					(fX + dX * progress) / rd
					,
					(fY + dY * progress) / rd
					,
					(fZ + dZ * progress) / rd
			);
			part.setHeadPose(newPose);
		}
	}
	
	public void reset(ArmorStand part, int endTime) {
		ComplexMob mob = (ComplexMob) ComplexMobs.partMob.get(part);
		
		if (Instant.now().isAfter(mob.animationTimer.get(part).plusMillis(endTime))) {
			mob.animationTimer.put(part, Instant.now());
		}
	}
}
