package complexMobs.Main;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class ComplexMob {
	//Main
	public final ArmorStand main;

	//Target
	public LivingEntity target;
	
	//Maps
	public Map<ArmorStand, ArmorStand> partHost = new HashMap<>();
	public Map<ArmorStand, String> partId = new HashMap<>();
	public Map<Item, Boolean> dsItem = new HashMap<>();
	public Map<Entity, Instant> entityAttackMobCooldown = new HashMap<>();
	
	//Doubles
	public final double maxPoise;
	public double poise;
	public double stamina = 100;
	public final double maxHealth;
	public double health;
	
	//Integers
	public int action;
	
	//String
	public String passiveAction;
	public String activeAction;
	public String killer;
	
	//Collections
	public Collection<ArmorStand> parts;
	public Collection<Vector> attackAreaPts;
	
	//Timers
	public Instant staggerTimer;
	public Map<ArmorStand, Instant> animationTimer = new HashMap<>();
	public Instant stageTimer;
	public Instant changeTimer;
	public Instant staminaUseTimer;
	
	//Locations/Vectors
	public Location direction;
	
	//Booleans
	public boolean isAttacking = false;
	public boolean hyperArmor = false;
	public boolean dead = false;
	
	//part IDs
	public Collection<String> partIds;
	
	protected ComplexMob(ArmorStand inpMain, double inpMaxHealth, double inpMaxPoise, Collection<ArmorStand> inpParts) {
		//Input variables
		main = inpMain;
		
		maxHealth = inpMaxHealth;
		health = maxHealth;
		
		maxPoise = inpMaxPoise;
		poise = maxPoise;
		
		parts = inpParts;
	}
}
