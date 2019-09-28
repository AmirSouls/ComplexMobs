package lothricKnights.Methods;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class GoBtwn {
	
	//Set constraints for all 3 combined angles of a part
	public static boolean combined(ArmorStand part, EulerAngle combinedAngle, double lowLimX, double upLimX, double lowLimY, double upLimY, double lowLimZ, double upLimZ, double spd) {
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
		if (combinedAngle.getX() > upLimX) {
			if (combinedAngle.getX() < upLimX + spd) {
				combinedAngle = new EulerAngle(
						upLimX ,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX() - spd ,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getX() < lowLimX ) {
			if (combinedAngle.getX() > (lowLimX - spd) ) {
				combinedAngle = new EulerAngle(
						lowLimX ,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX() + spd ,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		//Y
		if (combinedAngle.getY() > upLimY ) {
			if (combinedAngle.getY() < (upLimY + spd) ) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						upLimY 
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY() - spd 
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getY() < lowLimY ) {
			if (combinedAngle.getY() > (lowLimY - spd) ) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						lowLimY 
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY() + spd 
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		//Z
		if (combinedAngle.getZ() > upLimZ ) {
			if (combinedAngle.getZ() < (upLimZ + spd) ) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY()
						,
						upLimZ 
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY()
						,
						combinedAngle.getZ() - spd 
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getZ() < lowLimZ ) {
			if (combinedAngle.getZ() > (lowLimZ - spd) ) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY()
						,
						lowLimZ 
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY()
						,
						combinedAngle.getZ() + spd 
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	//Simple 1 for all 3 method for combined
	public static boolean combined(ArmorStand part, EulerAngle combinedAngle, double lowLim, double upLim, double spd) {
		return combined(part, combinedAngle, lowLim, upLim, lowLim, upLim, lowLim, upLim, spd);
	}
}
