package complexMobs.mob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.Build;
import complexMobs.mob.lothicKnight.Move;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.Part;
import complexMobs.object.Weapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	/**
	* Creates a LothricKnight object
	*/
	public LothricKnight(Vector post) {
		super("LothricKnight", "Lothric Knight", 100, 30, 30, post, new Weapon(), new Weapon());
	}
	
	public void attackFrameSword(double damage, Vector knockBack) {
		
		Part swordPart = this.getParts().get("sword");
		ArmorStand main = this.getMain();
		
		Vector weaponHilt = swordPart.getArmorStand().getLocation().toVector().add(new Vector(0,1.3,0));

		Vector weaponTip = new Vector(0,0,2.5);
		final EulerAngle weaponAngle = swordPart.getHeadPose();
		weaponTip.rotateAroundX(weaponAngle.getX());
		weaponTip.rotateAroundY(-weaponAngle.getY());
		weaponTip.rotateAroundZ(-weaponAngle.getZ());
		weaponTip.rotateAroundAxis(new Vector(0,1,0), swordPart.getArmorStand().getLocation().getYaw() / -57.29);
		weaponHilt = weaponHilt.subtract(weaponTip.clone().multiply(-.2));
		
		List<Vector> newHitPList = new ArrayList<>();
		for (int points = 0; !(points > 10); points++) {
			Vector hitP = weaponHilt.clone();
			hitP.add(weaponTip.clone().multiply(points * .1));	
			newHitPList.add(hitP);
			
			if (this.getSwordWeapon().getHitPoints() != null) {
				
				if (this.getSwordWeapon().getHitPoints().size() > points) {
					
					Vector opposingP = this.getSwordWeapon().getHitPoints().get(points);
					for (int pointsBetween = 0; !(pointsBetween > 10); pointsBetween++) {
						Vector betweenP = hitP.clone();
						betweenP.add(opposingP.clone().subtract(betweenP).multiply(pointsBetween * .1));	
						Location betweenPLoc = betweenP.toLocation(main.getWorld());
						
						main.getWorld().spawnParticle(Particle.END_ROD, betweenPLoc, 0);
						
						for (Entity entity : main.getNearbyEntities(10, 10, 10)) {
							if (entity instanceof Player) {
								Player player = (Player) entity;
								if (player.getBoundingBox().contains(betweenPLoc.toVector())) {
									player.damage(damage);
									entity.setVelocity(knockBack);
								}
							}
						}
					}
				}
			}
		}
		
		this.getSwordWeapon().setHitPoints(newHitPList);
	}

	public void attackFrameShield() {
		
	}

	public void run() {
		Run run= new Run();
		run.run(this, ComplexMobs.getProvidingPlugin(ComplexMobs.class));
	}

	public void build(Location spawnLocation) {
		Build build = new Build();
		build.run(this, spawnLocation);
	}
	
	@Override
	public void move(double vectorScalar, double angleTurn, double angleOffset) {
		new Move().run(this, vectorScalar, angleTurn, angleOffset);
	}
}
