package complexMobs.Methods;

import complexMobs.Main.ComplexMob;

public class ToggleSound {
	
	public void off(ComplexMob mob, String soundID) {
		mob.played.put(soundID, true);
	}
	
	public void on(ComplexMob mob, String soundID) {
		if (mob.played.containsKey(soundID)) {
			mob.played.remove(soundID);
		}
	}
	
	public boolean isOn(ComplexMob mob, String soundID) {
		if (mob.played.containsKey(soundID)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void enableAllSounds(ComplexMob mob) {
		for (String soundID : mob.soundList) {
			if (mob.played.containsKey(soundID)) {
				mob.played.remove(soundID);
			}
		}
	}
}
