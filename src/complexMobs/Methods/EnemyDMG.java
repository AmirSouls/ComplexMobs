package complexMobs.Methods;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import complexMobs.Main.ComplexMob;

public class EnemyDMG {
	public void normal(ComplexMob mob, Vector swordHB, double damage, double knockHorz, double knockVert, int slowLvl, int slowTicks) {
		for (Entity entity : mob.main.getNearbyEntities(10, 10, 10)) {
			BoundingBox hitBox = entity.getBoundingBox();
			if (hitBox.contains(mob.main.getLocation().add(swordHB).toVector()) && entity.getType().isAlive() && entity.getType() != EntityType.ARMOR_STAND) {
				//Damage entity
				LivingEntity livingEntity = (LivingEntity) entity;
				
				//Creative check
				boolean creative = false;
				if (livingEntity.getType() == EntityType.PLAYER) {
					Player player = (Player) livingEntity;
					if (player.getGameMode() == GameMode.CREATIVE) creative = true;
				}
				
				//Armor Formula
				double defensePoints = livingEntity.getAttribute(Attribute.GENERIC_ARMOR).getValue();
				double toughness = livingEntity.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
				defensePoints -= (damage / (2 + toughness / 4));
				damage *= (1 - (defensePoints / 25));
				
				if ((livingEntity.getNoDamageTicks() == 0 || livingEntity.getLastDamage() + 1 < damage) && !creative) {
					//Blood particle
					mob.main.getWorld().spawnParticle(Particle.BLOCK_DUST, mob.main.getLocation().add(swordHB), 200, 0, 0, 0, 1, Material.REDSTONE_WIRE.createBlockData());
					
					//Sound fx
					mob.playSound("lothricmob.playerhurt", mob.main.getLocation(), 2, 1, 1);
					
					livingEntity.damage(damage);
					livingEntity.setVelocity(mob.main.getLocation().getDirection().multiply(knockHorz).setY(knockVert));
					
					//Slow down opponent
					if (slowLvl > 0) {
						livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowTicks, slowLvl));
					}
				}
			}
		}
	}

	public void normal(ComplexMob mob, Vector inbShieldHB, double damage, double knockHorz, double knockVert) {
		normal(mob, inbShieldHB, damage, knockHorz, knockVert, 0, 0);
	}
}
