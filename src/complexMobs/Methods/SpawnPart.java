package complexMobs.Methods;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import complexMobs.Main.ComplexMob;
import complexMobs.Main.ComplexMobs;

public class SpawnPart {
	public static ArmorStand normal(ArmorStand main, Location location, Material item, int itemDmg, String partId, ComplexMob mob) {
		ArmorStand part = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		part.addScoreboardTag("complexMobPart");
		part.setInvulnerable(false);
		part.setVisible(false);
		part.setGravity(false);
		part.setCollidable(false);
		part.setSilent(true);
		ItemStack head = new ItemStack(item, 1);
		head.setDurability((short) itemDmg);
		part.setHelmet(head);
		mob.partHost.put(part, main);
		mob.partId.put(part, partId);
		ComplexMobs.partMob.put(part, mob);
	
		return part;
	}
	
	public static ArmorStand main(ArmorStand part) {
		part.addScoreboardTag("complexMobPart");
		part.setInvulnerable(false);
		part.setVisible(false);
		part.setGravity(false);
		part.setCollidable(false);
		part.setSilent(true);
	
		return part;
	}
}
