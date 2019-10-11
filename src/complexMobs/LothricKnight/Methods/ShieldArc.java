package complexMobs.LothricKnight.Methods;

public class ShieldArc {
	public static boolean didBlock(double mobYaw, double attackerYaw, double shieldArc) {
		
		double shieldStart = mobYaw - .5 * shieldArc;
		double shieldStop = mobYaw + .5 * shieldArc;
		
		if (attackerYaw > 180) attackerYaw = attackerYaw - 180;
		else attackerYaw = attackerYaw + 180;
		
		if (shieldStart < attackerYaw && shieldStop > attackerYaw) {
			return true;
		}
		else {
			return false;
		}
	}
}
