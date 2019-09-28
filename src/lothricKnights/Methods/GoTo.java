package lothricKnights.Methods;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class GoTo {
	
	//Set constraints for all 3 combined angles of a part
	public static void angle(ArmorStand part, EulerAngle angle, double x, double y, double z) {
		double rd = 57.3957;
		
		//convert lims and spds to rd
		x = x / rd;
		y = y / rd;
		z = z / rd;
		
		angle = new EulerAngle(
				x,
				y,
				z
				);
		part.setHeadPose(angle);
	}
}
