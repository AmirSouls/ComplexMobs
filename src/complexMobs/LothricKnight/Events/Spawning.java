package complexMobs.LothricKnight.Events;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import complexMobs.Main.ComplexMobs;
import complexMobs.Methods.SpawnPart;
import complexMobs.Mobs.LothricKnight;

public class Spawning implements Listener {
	
	@EventHandler
	public void spawnKnight(EntitySpawnEvent event) {
		if (event.getEntity().getType() == EntityType.ZOMBIE) {
			if (!(Math.random() > 1)) {
				//Replace zombie with Lothric knight
				event.setCancelled(true);
				Entity entity = event.getEntity();
				
				//Spawn body parts
				
				//Main
				ArmorStand main = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				Collection<ArmorStand> partCollection = new ArrayList<>();
				partCollection = SpawnPart.main(entity, main, partCollection);
				
				//Create LothricKnight Variable
				LothricKnight knight = new LothricKnight(main, partCollection);
				
				//Main variables
				knight.partHost.put(main, main);
				ComplexMobs.isMain.put(main, true);
				ComplexMobs.partMob.put(main, knight);
				
				//Model Item
				Material modelItem = Material.DIAMOND_HOE;
				
				//Chest, head, pelvis and cape
				partCollection = SpawnPart.normal(main, entity, modelItem, 1, "pelvis", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 2, "chest", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 3, "head", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 15, "cape", knight, partCollection);
				//Weapons
				partCollection = SpawnPart.normal(main, entity, modelItem, 4, "sword", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 5, "shield", knight, partCollection);
				
				//Legs
				partCollection = SpawnPart.normal(main, entity, modelItem, 6, "leftFoot", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 6, "rightFoot", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 7, "leftCalf", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 7, "rightCalf", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 8, "leftThigh", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 8, "rightThigh", knight, partCollection);
				
				//Arms
				partCollection = SpawnPart.normal(main, entity, modelItem, 9, "leftElbow", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 10, "rightElbow", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 11, "leftArm", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 12, "rightArm", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 13, "leftHand", knight, partCollection);
				partCollection = SpawnPart.normal(main, entity, modelItem, 14, "rightHand", knight, partCollection);
				
				//Complete collection
				knight.parts = partCollection;
				
				//Id collection
				Collection<String> armorStandIds = new ArrayList<>();
				armorStandIds.add("pelvis");
				armorStandIds.add("chest");
				armorStandIds.add("head");
				armorStandIds.add("cape");
				armorStandIds.add("sword");
				armorStandIds.add("shield");
				armorStandIds.add("leftFoot");
				armorStandIds.add("rightFoot");
				armorStandIds.add("leftCalf");
				armorStandIds.add("rightCalf");
				armorStandIds.add("leftThigh");
				armorStandIds.add("rightThigh");
				armorStandIds.add("leftElbow");
				armorStandIds.add("rightElbow");
				armorStandIds.add("leftArm");
				armorStandIds.add("rightArm");
				armorStandIds.add("leftHand");
				armorStandIds.add("rightHand");
				
				knight.partIds = armorStandIds;
			}
		}
	}
}
