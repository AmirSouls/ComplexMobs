package complexMobs.LothricKnight.Methods;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class GoBtwn {
	
	//Set constraints for all 3 combined angles of a part
	public static boolean animate(ArmorStand part, EulerAngle angle, double lowLimX, double upLimX, double lowLimY, double upLimY, double lowLimZ, double upLimZ, double spd) {
		boolean outOfBounds = false;
		double rd = 57.3957;
		
		//convert lims and spds to rd
		lowLimX = lowLimX / rd;
		lowLimY = lowLimY / rd;
		lowLimZ = lowLimZ / rd;
		upLimX = upLimX / rd;
		upLimY = upLimY / rd;
		upLimZ = upLimZ / rd;
		spd = spd / rd;
		
		//X
		if (angle.getX() > upLimX) {
			if (angle.getX() < upLimX + spd) {
				angle = new EulerAngle(
						upLimX ,
						angle.getY(),
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
			else {
				angle = new EulerAngle(
						angle.getX() - spd ,
						angle.getY(),
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
		}
		else if (angle.getX() < lowLimX ) {
			if (angle.getX() > (lowLimX - spd) ) {
				angle = new EulerAngle(
						lowLimX ,
						angle.getY(),
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
			else {
				angle = new EulerAngle(
						angle.getX() + spd ,
						angle.getY(),
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
		}
		//Y
		if (angle.getY() > upLimY ) {
			if (angle.getY() < (upLimY + spd) ) {
				angle = new EulerAngle(
						angle.getX()
						,
						upLimY 
						,
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
			else {
				angle = new EulerAngle(
						angle.getX()
						,
						angle.getY() - spd 
						,
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
		}
		else if (angle.getY() < lowLimY ) {
			if (angle.getY() > (lowLimY - spd) ) {
				angle = new EulerAngle(
						angle.getX()
						,
						lowLimY 
						,
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
			else {
				angle = new EulerAngle(
						angle.getX()
						,
						angle.getY() + spd 
						,
						angle.getZ()
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
		}
		//Z
		if (angle.getZ() > upLimZ ) {
			if (angle.getZ() < (upLimZ + spd) ) {
				angle = new EulerAngle(
						angle.getX()
						,
						angle.getY()
						,
						upLimZ 
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
			else {
				angle = new EulerAngle(
						angle.getX()
						,
						angle.getY()
						,
						angle.getZ() - spd 
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
		}
		else if (angle.getZ() < lowLimZ ) {
			if (angle.getZ() > (lowLimZ - spd) ) {
				angle = new EulerAngle(
						angle.getX()
						,
						angle.getY()
						,
						lowLimZ 
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
			else {
				angle = new EulerAngle(
						angle.getX()
						,
						angle.getY()
						,
						angle.getZ() + spd 
						);
				part.setHeadPose(angle);
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	//Simple 1 for all 3 method for combined
	public static boolean animate(ArmorStand part, EulerAngle angle, double lowLim, double upLim, double spd) {
		return animate(part, angle, lowLim, upLim, lowLim, upLim, lowLim, upLim, spd);
	}
}
