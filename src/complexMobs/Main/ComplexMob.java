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
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import complexMobs.Methods.DirectionAndMovement;
import complexMobs.Methods.EnemyDMG;
import complexMobs.Methods.LegacyAnimate;
import complexMobs.Methods.PartPositioning;
import complexMobs.Methods.PlaySound;
import complexMobs.Methods.ToggleSound;

public abstract class ComplexMob {
	
	//Variables--
	//Main
	public final ArmorStand main;

	//Target
	public LivingEntity target;
	
	//Maps
	public Map<ArmorStand, ArmorStand> partHost = new HashMap<>();
	public Map<ArmorStand, String> partId = new HashMap<>();
	public Map<Item, Boolean> dsItem = new HashMap<>();
	public Map<Entity, Instant> entityAttackMobCooldown = new HashMap<>();
	public Map<String, Boolean> played = new HashMap<>();
	
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
	public Collection<String> soundList;
	
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
	
	
	//Constructor--
	protected ComplexMob(ArmorStand inpMain, double inpMaxHealth, double inpMaxPoise, Collection<ArmorStand> inpParts, Collection<String> inpSounds) {
		//Input variables
		main = inpMain;
		
		maxHealth = inpMaxHealth;
		health = maxHealth;
		
		maxPoise = inpMaxPoise;
		poise = maxPoise;
		
		parts = inpParts;
		
		soundList = inpSounds;
	}
	
	
	//Methods--
	//Direction and movement
	public void DirectionAndMovement(double speed, double rotation, boolean aim) {
		final DirectionAndMovement method = new DirectionAndMovement();
		method.normal(this, speed, rotation, aim);
	}
	public void DirectionAndMovementTimed(double speed, double rotation, Instant timer, Instant timer2, int startMilli, int endMilli, boolean aim) {
		final DirectionAndMovement method = new DirectionAndMovement();
		method.timed(this, speed, 0, timer, timer2, startMilli, endMilli, aim);
	}
	
	//Sound toggling
	public boolean soundIsOn(String soundID) {
		final ToggleSound method = new ToggleSound();
		return method.isOn(this, soundID);
	}
	public void soundOn(String soundID) {
		final ToggleSound method = new ToggleSound();
		method.on(this, soundID);
	}
	public void soundOff(String soundID) {
		final ToggleSound method = new ToggleSound();
		method.off(this, soundID);
	}
	public void enableAllSounds() {
		final ToggleSound method = new ToggleSound();
		method.enableAllSounds(this);
	}
	//Sound playing
	public void playSound(String soundID, Location coords, double volume) {
		final PlaySound method = new PlaySound();
		method.normal(soundID, coords, volume);
	}
	public void playSound(String soundID, Location coords, double volume, double pitch) {
		final PlaySound method = new PlaySound();
		method.normal(soundID, coords, volume, pitch);
	}
	public void playSound(String soundID, Location coords, double volume, double pitch, double minVolume) {
		final PlaySound method = new PlaySound();
		method.normal(soundID, coords, volume, pitch, minVolume);
	}
	//Legacy Animation
	public void legacyAnimate(ArmorStand part, double fX, double fY, double fZ, double tX, double tY, double tZ, int startTime, int endTime) {
		final LegacyAnimate method = new LegacyAnimate();
		method.animate(part, fX, fY, fZ, tX, tY, tZ, startTime, endTime);
	}
	public void reset(ArmorStand part, int endTime) {
		final LegacyAnimate method = new LegacyAnimate();
		method.reset(part, endTime);
	}
	//Part positioning
	public Vector partPosition(ArmorStand part, Vector parentPosition, EulerAngle parentPose, Vector partOffset, Location hostPosition, double yaw) {
		final PartPositioning method = new PartPositioning();
		return method.position(part, parentPosition, parentPose, partOffset, hostPosition, yaw);
	}
	//Enemy Damage
	public void enemyDamage(Vector swordHB, double damage, double knockHorz, double knockVert, int slowLvl, int slowTicks) {
		final EnemyDMG method = new EnemyDMG();
		method.normal(this, swordHB, damage, knockHorz, knockVert, slowLvl, slowTicks);
	}
}
	
	
