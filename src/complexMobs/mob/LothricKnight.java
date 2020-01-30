package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.AttackSword;
import complexMobs.mob.lothicKnight.Move;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.Part;
import complexMobs.object.Weapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	private Player victim;
	
	/**
	* Creates a LothricKnight object
	*/
	public LothricKnight(Vector post) {
		super("LothricKnight", "Lothric Knight", 500, 30, 30, post, new Weapon(), new Weapon());
	}
	
	/**
	* Gets the nation that this LothricKnight serves
	* @return the nation id of the nation that this LothricKnight serves
	*/
	public int getNation() {
		return 0;
	}
	
	/**
	* Gets the victim of a riposte being executed by this LothricKnight
	* @return the victim of a riposte being executed by this LothricKnight
	*/
	public Player getVictim() {
		return this.victim;
	}
	
	/**
	* Sets the victim of a riposte being executed by this LothricKnight
	* @param victim the victim to be riposted by this LothricKnight
	*/
	public void setVictim(Player victim) {
		this.victim = victim;
	}
	
	/**
	* Deals damage in the area of the sword
	* @param damage the damage to be dealt
	* @param the velocity vector to apply to damaged players
	* @param particles show particle effects in the weapon's arc
	*/
	public void attackFrameSword(double damage, Vector knockBack, boolean particles) {
		new AttackSword().run(this, damage, knockBack, particles);
	}

	/**
	* Deals damage in the area of the shield
	* @param damage the damage to be dealt
	* @param the velocity vector to apply to damaged players
	* @param particles show particle effects in the weapon's arc
	*/
	public void attackFrameShield() {
		
	}

	public void run() {
		Run run= new Run();
		run.run(this, ComplexMobs.getProvidingPlugin(ComplexMobs.class));
	}

	public void build(Location spawnLocation) {
		super.build(spawnLocation);
		
		getParts().put("pelvis", new Part(1, this, new Vector(0,.5,0)));
        getParts().put("chest", new Part(2, this, new Vector(0,-.16,.01), getParts().get("pelvis")));
        getParts().put("head", new Part(3, this, new Vector(0,.8,0), getParts().get("chest")));
        getParts().put("cape", new Part(15, this, new Vector(0,.9,-.2), getParts().get("chest")));
        getParts().put("left_elbow", new Part(9, this, new Vector(.34,.8,-.03), getParts().get("chest")));
        getParts().put("left_arm", new Part(11, this, new Vector(.05,-0.51,0), getParts().get("left_elbow")));
        getParts().put("left_hand", new Part(13, this, new Vector(0,-.4,0), getParts().get("left_arm")));
        getParts().put("shield", new Part(5, this, new Vector(0,-.5,0), getParts().get("left_hand")));
        getParts().put("right_elbow", new Part(10, this, new Vector(-.34,.9,0), getParts().get("chest")));
        getParts().put("right_arm", new Part(12, this, new Vector(-.05,-0.51,0), getParts().get("right_elbow")));
        getParts().put("right_hand", new Part(14, this, new Vector(0,-.4,0), getParts().get("right_arm")));
        getParts().put("sword", new Part(4, this, new Vector(0,-.25,0), getParts().get("right_hand")));
        getParts().put("left_thigh", new Part(8, this, new Vector(.17,-.42,.04), getParts().get("pelvis")));
        getParts().put("left_calf", new Part(7, this, new Vector(0,-0.6,0), getParts().get("left_thigh")));
        getParts().put("left_foot", new Part(6, this, new Vector(0,-.585,-.035), getParts().get("left_calf")));
        getParts().put("right_thigh", new Part(8, this, new Vector(-.17,-.42,.04), getParts().get("pelvis")));
        getParts().put("right_calf", new Part(7, this, new Vector(0,-0.6,0), getParts().get("right_thigh")));
        getParts().put("right_foot", new Part(6, this, new Vector(0,-.585,-.035), getParts().get("right_calf")));
	}
	
	@Override
	public void move(double vectorScalar, double angleTurn, double angleOffset) {
		new Move().run(this, vectorScalar, angleTurn, angleOffset);
	}
	
}
