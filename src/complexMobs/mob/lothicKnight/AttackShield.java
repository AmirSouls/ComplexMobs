package complexMobs.mob.lothicKnight;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.Part;

public class AttackShield {

	public void run(LothricKnight lothricKnight, double damage, Vector knockBack, boolean particles) {
		Part shieldPart = lothricKnight.getParts().get("shield");
		ArmorStand main = lothricKnight.getMain();
		Vector weaponHilt = shieldPart.getArmorStand().getLocation().toVector().add(new Vector(0,1.3,0));
		Vector weaponTip = new Vector(0,1,0);
		final EulerAngle weaponAngle = shieldPart.getHeadPose();
		weaponTip.rotateAroundX(weaponAngle.getX());
		weaponTip.rotateAroundY(-weaponAngle.getY());
		weaponTip.rotateAroundZ(-weaponAngle.getZ());
		weaponTip.rotateAroundAxis(new Vector(0,1,0), shieldPart.getArmorStand().getLocation().getYaw() / -57.29);
		weaponHilt = weaponHilt.subtract(weaponTip.clone().multiply(.4));
		weaponTip.multiply(1.4);
		List<Vector> newHitPList = new ArrayList<>();
		
		for (int points = 0; !(points > 10); points++) {
			Vector hitP = weaponHilt.clone();
			hitP.add(weaponTip.clone().multiply(points * .1));	
			newHitPList.add(hitP);
			
			if (lothricKnight.getShield().getHitPoints() != null) {
				if (lothricKnight.getShield().getHitPoints().size() > points) {
					
					Vector opposingP = lothricKnight.getShield().getHitPoints().get(points);
					
					for (int pointsBetween = 0; !(pointsBetween > 10); pointsBetween++) {
						Vector betweenP = hitP.clone();
						betweenP.add(opposingP.clone().subtract(betweenP).multiply(pointsBetween * .1));	
						Location betweenPLoc = betweenP.toLocation(main.getWorld());
						if (particles) main.getWorld().spawnParticle(Particle.END_ROD, betweenPLoc, 0);
						
						for (Entity entity : main.getNearbyEntities(10, 10, 10)) {
							if (entity == lothricKnight.getBrain() || entity.getType().equals(EntityType.ARMOR_STAND)) continue;
							
							if (entity instanceof LivingEntity) {
								LivingEntity livingEntity = (LivingEntity)entity;
								
								if (livingEntity.getBoundingBox().contains(betweenPLoc.toVector())) {
									livingEntity.damage(damage, lothricKnight.getMain());
									entity.setVelocity(knockBack);
								}
							}
						}
					}
				}
			}
		}
		
		lothricKnight.getShield().setHitPoints(newHitPList);
	}
	
	private double damageCalculation(double damage, Player player) {
		
		double armorPts = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
		double toughnessPts = .3 * player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
		if (armorPts > 12) damage += damage * Math.min(1 - (armorPts - damage/(2 + toughnessPts / 4)) / 25, 1);
		if (player.isBlocking()) damage *= .2;
		return damage;
	}
}
