package lothricKnights.Methods;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class OutOfBounds {
	
	//Set constraints for all 3 combined angles of a part
	public static boolean combined(ArmorStand part, EulerAngle combinedAngle, double lowLimX, double upLimX, double spdX, double lowLimY, double upLimY, double spdY, double lowLimZ, double upLimZ, double spdZ) {
		boolean outOfBounds = false;
		double rd = 57.3957;
		
		//convert lims and spds to rd
		lowLimX = lowLimX / rd;
		lowLimY = lowLimY / rd;
		lowLimZ = lowLimZ / rd;
		upLimX = upLimX / rd;
		upLimY = upLimY / rd;
		upLimZ = upLimZ / rd;
		spdX = spdX / rd;
		spdY = spdY / rd;
		spdZ = spdZ / rd;
		
		//X
		if (combinedAngle.getX() > upLimX) {
			if (combinedAngle.getX() < upLimX + spdX) {
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
						combinedAngle.getX() - spdX ,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getX() < lowLimX ) {
			if (combinedAngle.getX() > (lowLimX - spdX) ) {
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
						combinedAngle.getX() + spdX ,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		//Y
		if (combinedAngle.getY() > upLimY ) {
			if (combinedAngle.getY() < (upLimY + spdY) ) {
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
						combinedAngle.getY() - spdY 
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getY() < lowLimY ) {
			if (combinedAngle.getY() > (lowLimY - spdY) ) {
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
						combinedAngle.getY() + spdY 
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		//Z
		if (combinedAngle.getZ() > upLimZ ) {
			if (combinedAngle.getZ() < (upLimZ + spdZ) ) {
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
						combinedAngle.getZ() - spdZ 
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getZ() < lowLimZ ) {
			if (combinedAngle.getZ() > (lowLimZ - spdZ) ) {
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
						combinedAngle.getZ() + spdZ 
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	//Simple 1 for all 3 method for combined
	public static boolean combined(ArmorStand part, EulerAngle combinedAngle, double lowLim, double upLim, double spd) {
		return combined(part, combinedAngle, lowLim, upLim, spd, lowLim, upLim, spd, lowLim, upLim, spd);
	}
}
