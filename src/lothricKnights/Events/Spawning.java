package lothricKnights.Events;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import lothricKnights.Main.LothricKnights;

public class Spawning implements Listener {
	
	@EventHandler
	public void spawnKnight(EntitySpawnEvent event) {
		if (event.getEntity().getType() == EntityType.ZOMBIE) {
			if (!(Math.random() > 1)) {
				//Replace zombie with lothric knight
				event.setCancelled(true);
				Entity entity = event.getEntity();
				
				//Spawn body parts
				//
				
				//Main
				ArmorStand main = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				LothricKnights.isMain.put(main, true);
				main.setSilent(true);
				main.setCollidable(false);
				main.setInvulnerable(true);
				main.setVisible(false);
				main.setGravity(false);
				Collection<ArmorStand> partCollection = new ArrayList<>();
				partCollection.add(main);
				LothricKnights.partList.put(main, partCollection);
				LothricKnights.partHost.put(main, main);
				LothricKnights.hyperArmor.put(main, false);
				LothricKnights.stamina.put(main, 100.0);
				
				//Pelvis
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 1);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "pelvis");}
				
				
				//Chest
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 2);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "chest");}
				
				//Head; player tracker
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 3);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "head");}
				
				//Sword
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 4);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "sword");}
				
				//Shield
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 5);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "shield");}
				
				//Left Foot
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 6);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "left foot");}
				
				//Right Foot
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 6);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "right foot");}
				
				//Left Calf
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 7);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "left calf");}
				
				//Right Calf
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 7);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "right calf");}
				
				//Left Thigh
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 8);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "left thigh");}
				
				//Right Thigh
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 8);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "right thigh");}
				
				//Left Elbow
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 9);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "left elbow");}
				
				//Right Elbow
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 10);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "right elbow");}
				
				//Left Arm
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 11);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "left arm");}
				
				//Right Arm
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 12);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "right arm");}
				
				//Left Hand
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 13);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "left hand");}
				
				//Right Hand
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 14);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "right hand");}			
				
				//Cape
				{ArmorStand part = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
				part.setInvulnerable(false);
				part.setVisible(false);
				part.setGravity(false);
				part.setMarker(false);
				part.setCollidable(false);
				part.setSilent(true);
				ItemStack head = new ItemStack(Material.DIAMOND_HOE, 1);
				head.setDurability((short) 15);
				part.setHelmet(head);
				partCollection.add(part);
				LothricKnights.partHost.put(part, main);
				LothricKnights.partList.put(part, partCollection);
				LothricKnights.partId.put(part, "cape");}		
				
			}
		}
	}
}
