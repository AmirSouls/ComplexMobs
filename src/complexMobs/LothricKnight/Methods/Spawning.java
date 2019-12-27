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
		main = SpawnPart.main(main);
		
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
		LothricKnight knight = new LothricKnight(main, soundList);
		
		//Main variables
		knight.partHost.put(main, main);
		ComplexMobs.isMain.put(main, true);
		ComplexMobs.partMob.put(main, knight);
		
		//Model Item
		Material modelItem = Material.DIAMOND_HOE;
		
		//Chest, head, pelvis and cape
		knight.pelvis = SpawnPart.normal(main, location, modelItem, 1, "pelvis", knight);
		knight.chest = SpawnPart.normal(main, location, modelItem, 2, "chest", knight);
		knight.head = SpawnPart.normal(main, location, modelItem, 3, "head", knight);
		knight.cape = SpawnPart.normal(main, location, modelItem, 15, "cape", knight);
		//Weapons
		knight.sword = SpawnPart.normal(main, location, modelItem, 4, "sword", knight);
		knight.shield = SpawnPart.normal(main, location, modelItem, 5, "shield", knight);
		
		//Legs
		knight.leftFoot = SpawnPart.normal(main, location, modelItem, 6, "leftFoot", knight);
		knight.rightFoot = SpawnPart.normal(main, location, modelItem, 6, "rightFoot", knight);
		knight.leftCalf= SpawnPart.normal(main, location, modelItem, 7, "leftCalf", knight);
		knight.rightCalf = SpawnPart.normal(main, location, modelItem, 7, "rightCalf", knight);
		knight.leftThigh = SpawnPart.normal(main, location, modelItem, 8, "leftThigh", knight);
		knight.rightThigh = SpawnPart.normal(main, location, modelItem, 8, "rightThigh", knight);
		
		//Arms
		knight.leftElbow = SpawnPart.normal(main, location, modelItem, 9, "leftElbow", knight);
		knight.rightElbow = SpawnPart.normal(main, location, modelItem, 10, "rightElbow", knight);
		knight.leftArm = SpawnPart.normal(main, location, modelItem, 11, "leftArm", knight);
		knight.rightArm = SpawnPart.normal(main, location, modelItem, 12, "rightArm", knight);
		knight.leftHand = SpawnPart.normal(main, location, modelItem, 13, "leftHand", knight);
		knight.rightHand = SpawnPart.normal(main, location, modelItem, 14, "rightHand", knight);
		
	}
}
