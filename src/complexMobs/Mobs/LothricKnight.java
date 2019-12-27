package complexMobs.Mobs;

import java.time.Instant;
import java.util.Collection;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import complexMobs.Main.ComplexMob;

public class LothricKnight extends ComplexMob {

	//Constructor--
	public LothricKnight(ArmorStand inpMain, Collection<String> inpSounds) {
		super(inpMain, 100, 14, inpSounds);
	}
	
	//Variables--
	public Instant blockTimer;
	public boolean shieldUp;
	public double yaw = this.main.getLocation().getYaw() / 57.2957795;
	
	//Stands
	public ArmorStand pelvis = null;
	public ArmorStand chest = null;
	public ArmorStand head = null;
	public ArmorStand cape = null;
	public ArmorStand sword = null;
	public ArmorStand shield = null;
	public ArmorStand leftFoot = null;
	public ArmorStand rightFoot = null;
	public ArmorStand leftCalf = null;
	public ArmorStand rightCalf = null;
	public ArmorStand leftThigh = null;
	public ArmorStand rightThigh = null;
	public ArmorStand leftElbow = null;
	public ArmorStand rightElbow = null;
	public ArmorStand leftArm = null;
	public ArmorStand rightArm = null;
	public ArmorStand leftHand = null;
	public ArmorStand rightHand = null;
	
	//Stand vectors
	public Vector pelvisPosition = new Vector(0,0,0);
	public Vector chestPosition = new Vector(0,0,0);
	public Vector headPosition = new Vector(0,0,0);
	public Vector swordPosition = new Vector(0,0,0);
	public Vector shieldPosition = new Vector(0,0,0);
	public Vector leftFootPosition = new Vector(0,0,0);
	public Vector rightFootPosition = new Vector(0,0,0);
	public Vector leftCalfPosition = new Vector(0,0,0);
	public Vector rightCalfPosition = new Vector(0,0,0);
	public Vector leftThighPosition = new Vector(0,0,0);
	public Vector rightThighPosition = new Vector(0,0,0);
	public Vector leftElbowPosition = new Vector(0,0,0);
	public Vector rightElbowPosition = new Vector(0,0,0);
	public Vector leftArmPosition = new Vector(0,0,0);
	public Vector rightArmPosition = new Vector(0,0,0);
	public Vector leftHandPosition = new Vector(0,0,0);
	public Vector rightHandPosition = new Vector(0,0,0);
	public Vector capePosition = new Vector(0,0,0);

	//Restore vectors
	public void restoreVectors() {
		pelvisPosition = new Vector(0,0,0);
		chestPosition = new Vector(0,0,0);
		headPosition = new Vector(0,0,0);
		swordPosition = new Vector(0,0,0);
		shieldPosition = new Vector(0,0,0);
		leftFootPosition = new Vector(0,0,0);
		rightFootPosition = new Vector(0,0,0);
		leftCalfPosition = new Vector(0,0,0);
		rightCalfPosition = new Vector(0,0,0);
		leftThighPosition = new Vector(0,0,0);
		rightThighPosition = new Vector(0,0,0);
		leftElbowPosition = new Vector(0,0,0);
		rightElbowPosition = new Vector(0,0,0);
		leftArmPosition = new Vector(0,0,0);
		rightArmPosition = new Vector(0,0,0);
		leftHandPosition = new Vector(0,0,0);
		rightHandPosition = new Vector(0,0,0);
		capePosition = new Vector(0,0,0);
	}
	
	
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
