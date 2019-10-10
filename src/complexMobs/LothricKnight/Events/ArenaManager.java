package complexMobs.LothricKnight.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
		inv.setItem(0, new ItemStack(Material.IRON_SWORD));
		inv.setItem(1, new ItemStack(Material.GOLDEN_APPLE, 10));
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		player.getEquipment().setArmorContents(armor);
	}
}
