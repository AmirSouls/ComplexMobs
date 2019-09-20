package lothricKnights.Methods;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class OutOfBoundsTestt {
	
	//Set constraints for all 3 combined angles of a part
	public static boolean combined(ArmorStand part, EulerAngle combinedAngle, float lowLimX, float upLimX, float spdX, float lowLimY, float upLimY, float spdY, float lowLimZ, float upLimZ, float spdZ) {
		boolean outOfBounds = false;
		double rd = 57.3957;
		
		//X
		if (combinedAngle.getX() > upLimX / rd) {
			if (combinedAngle.getX() < (upLimX + spdX) / rd) {
				combinedAngle = new EulerAngle(
						upLimX / rd,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX() - spdX / rd,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getX() < lowLimX / rd) {
			if (combinedAngle.getX() > (lowLimX - spdX) / rd) {
				combinedAngle = new EulerAngle(
						lowLimX / rd,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
			else {
				combinedAngle = new EulerAngle(
						combinedAngle.getX() + spdX / rd,
						combinedAngle.getY(),
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		//Y
		if (combinedAngle.getY() > upLimY / rd) {
			if (combinedAngle.getY() < (upLimY + spdY) / rd) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						upLimY / rd
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
						combinedAngle.getY() - spdY / rd
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getY() < lowLimY / rd) {
			if (combinedAngle.getY() > (lowLimY - spdY) / rd) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						lowLimY / rd
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
						combinedAngle.getY() + spdY / rd
						,
						combinedAngle.getZ()
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		//Z
		if (combinedAngle.getZ() > upLimZ / rd) {
			if (combinedAngle.getZ() < (upLimZ + spdZ) / rd) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY()
						,
						upLimZ / rd
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
						combinedAngle.getZ() - spdZ / rd
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		else if (combinedAngle.getZ() < lowLimZ / rd) {
			if (combinedAngle.getZ() > (lowLimZ - spdZ) / rd) {
				combinedAngle = new EulerAngle(
						combinedAngle.getX()
						,
						combinedAngle.getY()
						,
						lowLimZ / rd
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
						combinedAngle.getZ() + spdZ / rd
						);
				part.setHeadPose(combinedAngle);
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	//Simple 1 for all 3 method for combined
	public static boolean combined(ArmorStand part, EulerAngle combinedAngle, float lowLim, float upLim, float spd) {
		return combined(part, combinedAngle, lowLim, upLim, spd, lowLim, upLim, spd, lowLim, upLim, spd);
	}
}
