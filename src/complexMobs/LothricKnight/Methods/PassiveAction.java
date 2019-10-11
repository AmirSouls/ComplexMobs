package complexMobs.LothricKnight.Methods;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import complexMobs.LothricKnight.Running;
import complexMobs.LothricKnight.Standing;
import complexMobs.LothricKnight.WalkingBackward;
import complexMobs.LothricKnight.WalkingForward;
import complexMobs.LothricKnight.WalkingSide;
import complexMobs.Methods.ToggleSound;
import complexMobs.Mobs.LothricKnight;

public class PassiveAction {
	public static void select(LothricKnight knight, double distance3D) {
		
		//Action selection
		Collection<String> actions = new ArrayList<>();
		if (distance3D < 4) actions.add("WalkingBackward"); 
		if (distance3D > 4 && distance3D < 10) actions.add("WalkingSide"); 
		if (distance3D > 6 && distance3D < 12) actions.add("WalkingForward");
		if (distance3D > 6) actions.add("Running");
		if (!actions.isEmpty()) Collections.shuffle((List<?>) actions);
		String newPassiveAction = (String) actions.toArray()[0];
		
		//Shield down boolean
		knight.shieldUp = true;
		
		final Instant changeTimer = knight.changeTimer;
		
		//"Too close, walk backwards" override
		if (distance3D < 3 && !knight.passiveAction.contentEquals("WalkingBackward")) {
			knight.passiveAction = "WalkingBackward";
			knight.changeTimer = Instant.now().plusMillis((long) (Math.random() * 3000));
			
			//Reset animation timers
			ResetTimers.reset(knight);
			
			//Re-enable all sounds
			ToggleSound.enableAllSounds(knight);
		}
		//Check change timer
		else if (Instant.now().isAfter(changeTimer.plusMillis(3000))) {
			//Shield Boolean
			knight.shieldUp = true;
			if (distance3D > 6 && Math.random() > .5) knight.shieldUp = false;
			
			//Put passive action and change timer
			knight.passiveAction = newPassiveAction;
			knight.changeTimer = Instant.now().plusMillis((long) (Math.random() * 3000));
			
			//Reset animation timers
			ResetTimers.reset(knight);
			
			//Re-enable all sounds
			ToggleSound.enableAllSounds(knight);
		}
		
		//Animate part with current passive action
		if (knight.passiveAction.contentEquals("WalkingForward")) WalkingForward.animate(knight, knight.shieldUp);
		if (knight.passiveAction.contentEquals("WalkingBackward")) WalkingBackward.animate(knight, true);
		if (knight.passiveAction.contentEquals("WalkingSide")) WalkingSide.animate(knight, knight.shieldUp);
		if (knight.passiveAction.contentEquals("Standing")) Standing.animate(knight, knight.shieldUp);
		if (knight.passiveAction.contentEquals("Running")) {
			knight.shieldUp = false;
			Running.animate(knight);
	    }
	}
}
