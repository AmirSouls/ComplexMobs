package complexMobs.LothricKnight.Methods;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import complexMobs.Main.ComplexMobs;
import complexMobs.Methods.SpawnPart;
import complexMobs.Mobs.LothricKnight;

public class Spawning {
	public static void spawn(Location location) {
		//Spawn body parts
		
		//Main
		ArmorStand main = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		Collection<ArmorStand> partCollection = new ArrayList<>();
		partCollection = SpawnPart.main(main, partCollection);
		
		//Sound list
		Collection<String> soundList = new ArrayList<>();
		soundList.add("lothricknight.shieldhit");
		soundList.add("lothricknight.stanceattack");
		soundList.add("lothricknight.deathland");
		soundList.add("lothricknight.deathknee");
		soundList.add("lothricknight.death");
		soundList.add("lothricknight.run");
		soundList.add("lothricknight.walk");
		soundList.add("lothricknight.slash");
		soundList.add("lothricknight.grunt");
		soundList.add("lothricknight.shieldbash");
		soundList.add("lothricknight.playerhurt");
		soundList.add("lothricknight.slam");
		
		//Create LothricKnight Variable
		LothricKnight knight = new LothricKnight(main, partCollection, soundList);
		
		//Main variables
		knight.partHost.put(main, main);
		ComplexMobs.isMain.put(main, true);
		ComplexMobs.partMob.put(main, knight);
		
		//Model Item
		Material modelItem = Material.DIAMOND_HOE;
		
		//Chest, head, pelvis and cape
		partCollection = SpawnPart.normal(main, location, modelItem, 1, "pelvis", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 2, "chest", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 3, "head", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 15, "cape", knight, partCollection);
		//Weapons
		partCollection = SpawnPart.normal(main, location, modelItem, 4, "sword", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 5, "shield", knight, partCollection);
		
		//Legs
		partCollection = SpawnPart.normal(main, location, modelItem, 6, "leftFoot", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 6, "rightFoot", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 7, "leftCalf", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 7, "rightCalf", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 8, "leftThigh", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 8, "rightThigh", knight, partCollection);
		
		//Arms
		partCollection = SpawnPart.normal(main, location, modelItem, 9, "leftElbow", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 10, "rightElbow", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 11, "leftArm", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 12, "rightArm", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 13, "leftHand", knight, partCollection);
		partCollection = SpawnPart.normal(main, location, modelItem, 14, "rightHand", knight, partCollection);
		
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
