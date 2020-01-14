package complexMobs.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.template.LivingComplexMob;

public class LivingComplexMobListener implements Listener {

	@EventHandler
	public void armorStandInteract(PlayerArmorStandManipulateEvent e) {
		if (e.getRightClicked().hasMetadata("complex_mob")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void targeterAttack(EntityDamageByEntityEvent e) {
		
		if (e.getDamager().hasMetadata("complex_mob")) e.setCancelled(true);
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {

		if (e.getEntity().hasMetadata("complex_mob")) {
			LivingComplexMob mob = (LivingComplexMob) e.getEntity().getMetadata("complex_mob").get(0).value();
			
			if (e.getDamager() instanceof Arrow) {
				e.setDamage(e.getDamage()*.4);
			}
			
			if (mob instanceof LothricKnight) {
				if (onLothricKnightHit(e, (LothricKnight) mob)) return;
			}
			
			mob.setHealth(mob.getHealth() - e.getDamage());
			mob.getMain().getWorld().playSound(e.getEntity().getLocation(), "lothricknight.hurt", (float) e.getDamage() / 6f, 1);
			mob.getMain().getWorld().spawnParticle(Particle.BLOCK_DUST, e.getEntity().getLocation().add(0,1.3,0), 5, 0, 0, 0, .1, Material.REDSTONE_WIRE.createBlockData(), false);
		}
	}
	
	private boolean onLothricKnightHit(EntityDamageByEntityEvent e, LothricKnight lothricKnight) {
		
		if (lothricKnight.isShieldUp()) {
			Location dmgLoc = e.getDamager().getLocation();
			Location mobLoc = lothricKnight.getMain().getLocation();
			dmgLoc.setY(0);
			mobLoc.setY(0);
			Vector directionVec = dmgLoc.toVector().subtract(mobLoc.toVector());
			
			double damageDirectionYaw = Math.atan2(directionVec.getX(), directionVec.getZ()) * 57.29;
			if (damageDirectionYaw > 0) damageDirectionYaw -= 360;
			damageDirectionYaw *= -1;
			
			double mobYaw = lothricKnight.getMain().getLocation().getYaw();
			
			double shieldArc = 160;
			double shieldStart = mobYaw - shieldArc*.5;
			double shieldStop = mobYaw + shieldArc*.5;
			
			if (shieldStart < damageDirectionYaw && shieldStop > damageDirectionYaw) {
				
				lothricKnight.getMain().getWorld().playSound(e.getEntity().getLocation(), "lothricknight.shieldhit", 1, 1);
				
				return true;
			}
		}
		
		if (lothricKnight.getInvulTick() > 0) return true;
		
		if (lothricKnight.getTarget() == null) return true;
		
		lothricKnight.setInvulTick(10);
		
		return false;
	}
}
