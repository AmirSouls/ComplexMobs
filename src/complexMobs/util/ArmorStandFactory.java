package complexMobs.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import complexMobs.complexMob.ComplexMob;
import complexMobs.main.ComplexMobs;

public class ArmorStandFactory {
	
	public static ArmorStand createArmorStand(ComplexMob complexMob, Material item, int damage) {
		Location location = complexMob.getMain().getLocation();
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setSilent(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setInvulnerable(true);
        armorStand.setMetadata("complex_mob", new FixedMetadataValue(JavaPlugin.getPlugin(ComplexMobs.class), complexMob));
        ItemStack headItem = new ItemStack(item);
        headItem.setDurability((short) damage);
        armorStand.setHelmet(headItem);
        return armorStand;
    }
}
