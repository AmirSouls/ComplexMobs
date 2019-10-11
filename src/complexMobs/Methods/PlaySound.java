package complexMobs.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PlaySound {
	
	public static void normal(String soundID, Location coords, double volume, double pitch, double minVolume) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"playsound minecraft:"
				+
				soundID
				+
				" master @a "
				+
				coords.getX()
				+ 
				" "
				+
				coords.getY()
				+
				" "
				+
				coords.getZ()
				+
				" "
				+
				volume
				+
				" "
				+
				pitch
				+
				" "
				+
				minVolume
				);
	}
	
	//Without minimum volume
	public static void normal(String soundID, Location coords, double volume, double pitch) {
		normal(soundID, coords, volume, pitch, 0);
	}
	
	//Only volume
	public static void normal(String soundID, Location coords, double volume) {
		normal(soundID, coords, volume, 1, 0);
	}
}
