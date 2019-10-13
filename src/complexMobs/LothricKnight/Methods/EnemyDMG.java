package complexMobs.LothricKnight.Methods;

import org.bukkit.Bukkit;
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

import complexMobs.Methods.PlaySound;
import complexMobs.Mobs.LothricKnight;

public class EnemyDMG {
	public static void normal(LothricKnight knight, Vector swordHB, double damage, double knockHorz, double knockVert, int slowLvl, int slowTicks) {
		for (Entity entity : knight.main.getNearbyEntities(10, 10, 10)) {
			BoundingBox hitBox = entity.getBoundingBox();
			if (hitBox.contains(knight.main.getLocation().add(swordHB).toVector()) && entity.getType().isAlive() && entity.getType() != EntityType.ARMOR_STAND) {
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
					knight.main.getWorld().spawnParticle(Particle.BLOCK_DUST, knight.main.getLocation().add(swordHB), 200, 0, 0, 0, 1, Material.REDSTONE_WIRE.createBlockData());
					
					//Sound fx
					PlaySound.normal("lothricknight.playerhurt", knight.main.getLocation(), 2, 1, 1);
					
					livingEntity.damage(damage);
					livingEntity.setVelocity(knight.main.getLocation().getDirection().multiply(knockHorz).setY(knockVert));
					
					//Slow down opponent
					if (slowLvl > 0) {
						livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowTicks, slowLvl));
					}
				}
			}
		}
	}

	public static void normal(LothricKnight mob, Vector inbShieldHB, double damage, double knockHorz, double knockVert) {
		normal(mob, inbShieldHB, damage, knockHorz, knockVert, 0, 0);
	}
}
