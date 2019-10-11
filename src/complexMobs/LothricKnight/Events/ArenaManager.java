package complexMobs.LothricKnight.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import complexMobs.Main.ComplexMobs;

public class ArenaManager implements Listener {
	
	//This is just for testing on the server test server world.
	
	//Tasks
	public static void checkEmpty(World world) {
		if (world.getPlayers().isEmpty()) {
			//No players online, remove all knights
			for (ArmorStand armorStand : world.getEntitiesByClass(ArmorStand.class)) {
				if (ComplexMobs.partMob.containsKey(armorStand)) {
					armorStand.remove();
				}
			}
		}
	}
	
	//Events
	@EventHandler
	public void players(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.teleport(new Location(player.getWorld(), 23.5, 106, 0.5));
		player.getInventory().clear();
		Inventory inv = player.getInventory();
		
		//Pre 1.9 sword
		ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		swordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		swordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("W", 6, Operation.ADD_NUMBER));
		swordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("K", 10000, Operation.ADD_NUMBER));
		sword.setItemMeta(swordMeta);
		inv.setItem(0, sword);
		
		inv.setItem(1, new ItemStack(Material.GOLDEN_APPLE, 10));
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		player.getEquipment().setArmorContents(armor);
	}
}
