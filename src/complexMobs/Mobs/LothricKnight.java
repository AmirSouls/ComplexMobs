package complexMobs.Mobs;

import java.time.Instant;
import java.util.Collection;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.Main.ComplexMob;

public class LothricKnight extends ComplexMob {

	//Constructor--
	public LothricKnight(ArmorStand inpMain, Collection<ArmorStand> inpParts, Collection<String> inpSounds) {
		super(inpMain, 100, 14, inpParts, inpSounds);
	}
	
	
	//Variables--
	//Blocking
	public Instant blockTimer;
	public boolean shieldUp;
	
	
	//Methods--
	//Active action
	public void ActiveActionSelect(double distance3D) {
		final complexMobs.LothricKnight.Methods.ActiveAction method = new complexMobs.LothricKnight.Methods.ActiveAction();
		method.select(this, distance3D);
	}
	//Passive Action
		public void PassiveActionSelect(double distance3D) {
			final complexMobs.LothricKnight.Methods.PassiveAction method = new complexMobs.LothricKnight.Methods.PassiveAction();
			method.select(this, distance3D);
		}
	//AI
	public void AI() {
		final complexMobs.LothricKnight.Methods.AI method = new complexMobs.LothricKnight.Methods.AI();
		method.process(this);
	}
	//Damage Area
	public void DamageArea(ArmorStand weapon, double weaponLength, double backRatio, Instant weaponTimer, int startMilli, int endMilli, Vector weaponPosition, double yaw, boolean particles, double damage, double knockHorz, double knockVert) {
		final complexMobs.LothricKnight.Methods.DamageArea method = new complexMobs.LothricKnight.Methods.DamageArea();
		method.normal(this, weapon, weaponLength, backRatio, weaponTimer, startMilli, endMilli, weaponPosition, yaw, particles, damage, knockHorz, knockVert);
	}	
	public void DamageAreaShield(LothricKnight knight, ArmorStand weapon, double shieldLength, Instant weaponTimer, int startMilli, int endMilli, Vector shieldPosition, double yaw, boolean particles, double damage, double knockHorz, double knockVert) {
		final complexMobs.LothricKnight.Methods.DamageArea method = new complexMobs.LothricKnight.Methods.DamageArea();
		method.shield(this, weapon, shieldLength, weaponTimer, startMilli, endMilli, shieldPosition, yaw, particles, damage, knockHorz, knockVert);
	}
}
