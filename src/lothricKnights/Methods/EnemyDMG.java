package lothricKnights.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class EnemyDMG {
	public static void normal(ArmorStand main, Vector swordHB, double damage, double knockHorz, double knockVert) {
		for (Entity entity : main.getNearbyEntities(10, 10, 10)) {
			BoundingBox hitBox = entity.getBoundingBox();
			if (hitBox.contains(main.getLocation().add(swordHB).toVector()) && entity.getType().isAlive() && entity.getType() != EntityType.ARMOR_STAND) {
				//Blood particle
				main.getWorld().spawnParticle(Particle.BLOCK_DUST, main.getLocation().add(swordHB), 5, 0, 0, 0, 1, Material.REDSTONE_WIRE.createBlockData());
				
				//Dmg entity
				LivingEntity livingEntity = (LivingEntity) entity;
				//Armor Formula: TODO Implement real more compelx formula
				damage = damage - (damage * (livingEntity.getAttribute(Attribute.GENERIC_ARMOR).getValue()) / 25);
				
				if (livingEntity.getNoDamageTicks() == 0 || livingEntity.getLastDamage() + 1 < damage) {
					//Sound fx
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "" +
							"playsound minecraft:lothricknight.playerhurt master @a " +
							main.getLocation().getX() + 
							" " +
							main.getLocation().getY() +
							" " + main.getLocation().getZ() + " 1 1"
							);
					
					livingEntity.damage(damage);
					livingEntity.setVelocity(main.getLocation().getDirection().multiply(knockHorz).setY(knockVert));
				}
			}
		}
	}
}
