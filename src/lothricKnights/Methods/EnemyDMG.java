package lothricKnights.Methods;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class EnemyDMG {
	public static void normal(ArmorStand main, Vector swordHB) {
		for (Entity entity : main.getNearbyEntities(10, 10, 10)) {
			BoundingBox hitBox = entity.getBoundingBox();
			if (hitBox.contains(main.getLocation().add(swordHB).toVector()) && entity.getType().isAlive() && entity.getType() != EntityType.ARMOR_STAND) {
				//Blood particle
				main.getWorld().spawnParticle(Particle.BLOCK_DUST, main.getLocation().add(swordHB), 5, 0, 0, 0, 1, Material.REDSTONE_WIRE.createBlockData());
				
				//Dmg entity
				LivingEntity livingEntity = (LivingEntity) entity;
				//TODO: Implement armor formula so this isnt too op
				livingEntity.damage(15);
				livingEntity.setVelocity(main.getLocation().getDirection().multiply(0.4).setY(0.2));
			}
		}
	}
}
