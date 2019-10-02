package complexMobs.Methods;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import complexMobs.Main.ComplexMob;
import complexMobs.Main.ComplexMobs;

public class SpawnPart {
	public static Collection<ArmorStand> normal(ArmorStand main, Entity entity, Material item, int itemDmg, String partId, ComplexMob mob, Collection<ArmorStand> partCollection) {
		ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
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
		partCollection.add(part);
	
		return partCollection;
	}
	
	public static Collection<ArmorStand> main(Entity entity, ArmorStand part, Collection<ArmorStand> partCollection) {
		part.addScoreboardTag("complexMobPart");
		part.setInvulnerable(false);
		part.setVisible(false);
		part.setGravity(false);
		part.setCollidable(false);
		part.setSilent(true);
		partCollection.add(part);
	
		return partCollection;
	}
}
