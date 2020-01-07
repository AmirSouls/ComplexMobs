package complexMobs.mob.lothicKnight;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import complexMobs.object.ChildPart;
import complexMobs.object.Part;

public class Build {

	public void run(LothricKnight lothricKnight, Location spawnLocation) {
		World world = spawnLocation.getWorld();
		ArmorStand main = (ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND);
		lothricKnight.getParts().put(
				"pelvis", 
				new Part((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main, 
				new Vector()));
		
		lothricKnight.getParts().put(
				"chest", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("pelvis")));
		
		lothricKnight.getParts().put(
				"head", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("chest")));
		
		lothricKnight.getParts().put(
				"cape", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("chest")));
		
		lothricKnight.getParts().put(
				"left_elbow", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("chest")));
		
		lothricKnight.getParts().put(
				"left_arm", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("left_elbow")));
		
		lothricKnight.getParts().put(
				"left_hand", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("left_arm")));
		
		lothricKnight.getParts().put(
				"shield", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("left_hand")));
		
		lothricKnight.getParts().put(
				"right_elbow", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("chest")));
		
		lothricKnight.getParts().put(
				"right_arm", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("right_elbow")));
		
		lothricKnight.getParts().put(
				"right_hand", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("right_arm")));
		
		lothricKnight.getParts().put(
				"sword", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("right_hand")));
		
		lothricKnight.getParts().put(
				"left_thigh", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("pelvis")));
		
		lothricKnight.getParts().put(
				"left_calf", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("left_thigh")));
		
		lothricKnight.getParts().put(
				"left_foot", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("left_calf")));
		
		lothricKnight.getParts().put(
				"right_thigh", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("pelvis")));
		
		lothricKnight.getParts().put(
				"right_calf", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("right_thigh")));
		
		lothricKnight.getParts().put(
				"right_foot", 
				new ChildPart((ArmorStand) world.spawnEntity(spawnLocation, EntityType.ARMOR_STAND), 
				main,
				new Vector(), 
				lothricKnight.getParts().get("right_calf")));
		
		for (String key : lothricKnight.getParts().keySet()) {
			ItemStack partModel = new ItemStack(Material.DIAMOND_HOE);
			switch (key) {
			case "pelvis":
				partModel.setDurability((short) 1);
				break;
			case "chest":
				partModel.setDurability((short) 2);
				break;
			case "cape":
				partModel.setDurability((short) 15);
				break;
			case "head":
				partModel.setDurability((short) 3);
				break;
			case "left_elbow":
				partModel.setDurability((short) 9);
				break;
			case "left_arm":
				partModel.setDurability((short) 11);
				break;
			case "left_hand":
				partModel.setDurability((short) 13);
				break;
			case "shield":
				partModel.setDurability((short) 5);
				break;
			case "right_elbow":
				partModel.setDurability((short) 10);
				break;
			case "right_arm":
				partModel.setDurability((short) 12);
				break;
			case "right_hand":
				partModel.setDurability((short) 14);
				break;
			case "sword":
				partModel.setDurability((short) 4);
				break;
			case "left_thigh":
				partModel.setDurability((short) 8);
				break;
			case "left_calf":
				partModel.setDurability((short) 7);
				break;
			case "left_foot":
				partModel.setDurability((short) 6);
				break;
			case "right_thigh":
				partModel.setDurability((short) 8);
				break;
			case "right_calf":
				partModel.setDurability((short) 7);
				break;
			case "right_foot":
				partModel.setDurability((short) 6);
				break;
			}
			lothricKnight.getParts().get(key).getArmorStand().setHelmet(partModel);
		}
	}
}
