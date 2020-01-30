package complexMobs.template;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import complexMobs.complexMob.ComplexMob;
import complexMobs.main.ComplexMobs;
import complexMobs.object.Part;

public abstract class LivingComplexMob implements ComplexMob {
	
	private String action;
	private String ID;
	private String name;
	private ArmorStand main;
	private Monster brain;
	private double health;
	private double maxHealth;
	private Map<String, Part> parts = new LinkedHashMap<>();
	private boolean isRemoved = false;
	private boolean isDead = false;
	
	/**
	* Creates a LivingComplexMob object, This implements the ComplexMob interface
	* @param ID The ID of this ComplexMob
	* @param name The display name of this ComplexMob
	* @param maxHealth The max health of this LivingComplexMob
	*/
	protected LivingComplexMob(String ID, String name, double maxHealth) {
		this.ID = ID;
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArmorStand getMain() {
		return this.main;
	}
	
	public void setMain(ArmorStand main) {
		this.main = main;
	}
	
	public Monster getBrain() {
		return this.brain;
	}
	
	public void setBrain(Monster brain) {
		this.brain = brain;
	}
	
	public Map<String, Part> getParts() {
		return this.parts;
	}
	
	public void setParts(Map<String, Part> parts) {
		this.parts = parts;
	}
	
	public boolean isRemoved() {
		return this.isRemoved;
	}
	
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
	public void remove() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity.hasMetadata("complex_mob")) {
					for (MetadataValue metadata : entity.getMetadata("complex_mob")) {
						if (metadata.value().equals(this)) entity.remove();
					}
				}
			}
		}
		
		this.isRemoved = true;
		this.isDead = true;
	}
	
	/**
	* Gets the current action of this LivingComplexMob
	* @return The current action of this LivingComplexMob
	*/
	public String getAction() {
		return this.action;
	}
	
	/**
	* Sets the action of this LivingComplexMob
	* @param action The new action of this LivingComplexMob
	*/
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	* Gets the current health of this LivingComplexMob
	* @return The current health of this LivingComplexMob
	*/
	public double getHealth() {
		return this.health;
	}
	
	/**
	* Sets the health of this LivingComplexMob
	* @param health The new health of this LivingComplexMob
	*/
	public void setHealth(double health) {
		this.health = health;
	}
	
	/**
	* Gets the max health of this LivingComplexMob
	* @return The max health of this LivingComplexMob
	*/
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	/**
	* Checks if this LivingComplexMob is dead
	* @return True if this LivingComplexMob is dead
	*/
	public boolean isDead() {
		return this.isDead;
	}
	
	/**
	* Checks if this LivingComplexMob is dead
	* @param Set if this LivingComplexMob is dead
	*/
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	* Kills this LivingComplexMob naturally by setting it's health to zero
	*/
	public void kill() {
		this.health = 0;
	}
	
	/**
	* Revives this LivingComplexMob from the dead state, this does not work if the LivingComplexMob has been removed
	*/
	public void revive() {
		this.setHealth(this.maxHealth);
		this.isDead = false;
	}
	
	public void build(Location spawnLoc) {
		ComplexMobs.getComplexMobs().add(this);
		ArmorStand main = (ArmorStand) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.ARMOR_STAND);
		main.setSilent(true);
		main.setVisible(false);
		main.setGravity(false);
		main.setMetadata("complex_mob", new FixedMetadataValue(JavaPlugin.getPlugin(ComplexMobs.class), this));
		setMain(main);
		Zombie targeter = (Zombie) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.ZOMBIE);
		targeter.setRemoveWhenFarAway(false);
		targeter.setSilent(true);
		targeter.setInvulnerable(true);
		targeter.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000000, 0));
		targeter.setBaby(false);
		targeter.setMetadata("complex_mob", new FixedMetadataValue(JavaPlugin.getPlugin(ComplexMobs.class), this));
		setBrain(targeter);
	}
	
	/**
	* Moves this LivingComplexMob
	* @param vectorScalar Meters per tick to move
	* @param angleTurn Amount in degrees to turn towards its target
	* @param angleOffset Angle offset in degrees to move, for example 90 moves right
	*/
	public abstract void move(double vectorScalar, double angleTurn, double angleOffset);
}


