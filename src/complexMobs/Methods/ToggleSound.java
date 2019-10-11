package complexMobs.Methods;

import complexMobs.Main.ComplexMob;

public class ToggleSound {
	
	public static void off(ComplexMob mob, String soundID) {
		mob.played.put(soundID, true);
	}
	
	public static void on(ComplexMob mob, String soundID) {
		if (mob.played.containsKey(soundID)) {
			mob.played.remove(soundID);
		}
	}
	
	public static boolean isOn(ComplexMob mob, String soundID) {
		if (mob.played.containsKey(soundID)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static void enableAllSounds(ComplexMob mob) {
		for (String soundID : mob.soundList) {
			if (mob.played.containsKey(soundID)) {
				mob.played.remove(soundID);
			}
		}
	}
}
