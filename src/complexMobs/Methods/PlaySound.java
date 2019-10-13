package complexMobs.Methods;

import org.bukkit.Location;

public class PlaySound {
	
	public static void normal(String soundID, Location coords, double volume, double pitch, double minVolume) {
		coords.getWorld().playSound(coords, soundID, (float) volume, (float) pitch);
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
