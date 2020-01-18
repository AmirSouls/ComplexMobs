package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.AttackSword;
import complexMobs.mob.lothicKnight.Build;
import complexMobs.mob.lothicKnight.Move;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.Weapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	private int nation;
	
	private Player victim;
	
	/**
	* Creates a LothricKnight object
	*/
	public LothricKnight(Vector post, int nation) {
		super("LothricKnight", "Lothric Knight", 1000, 30, 30, post, new Weapon(), new Weapon());
		this.nation = nation;
	}
	
	/**
	* Gets the nation that this LothricKnight serves
	* @return the nation id of the nation that this LothricKnight serves
	*/
	public int getNation() {
		return this.nation;
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
	* @param the victim to be riposted by this LothricKnight
	*/
	public void setVictim(Player victim) {
		this.victim = victim;
	}
	
	public void attackFrameSword(double damage, Vector knockBack, boolean particles) {
		new AttackSword().run(this, damage, knockBack, particles);
	}

	public void attackFrameShield() {
		
	}

	public void run() {
		Run run= new Run();
		run.run(this, ComplexMobs.getProvidingPlugin(ComplexMobs.class));
	}

	public void build(Location spawnLocation) {
		Build build = new Build();
		build.run(this, spawnLocation);
	}
	
	@Override
	public void move(double vectorScalar, double angleTurn, double angleOffset) {
		new Move().run(this, vectorScalar, angleTurn, angleOffset);
	}
	
}
