package complexMobs.LothricKnight.Methods;

import java.time.Instant;

import complexMobs.LothricKnight.Running;
import complexMobs.LothricKnight.Standing;
import complexMobs.LothricKnight.WalkingForward;
import complexMobs.LothricKnight.WalkingSide;
import complexMobs.Mobs.LothricKnight;

public class PassiveAction {
	public static void select(LothricKnight knight, String newPassiveAction, double distance3D) {
		//Shield down boolean
		knight.shieldUp = true;
		
		final Instant changeTimer = knight.changeTimer;
		//check change timer
		if (Instant.now().isAfter(changeTimer.plusMillis(3000))) {
			//Shield Boolean
			knight.shieldUp = true;
			if (distance3D > 6 && Math.random() > .5) knight.shieldUp = false;
			
			//Put passive action and change timer
			knight.passiveAction = newPassiveAction;
			knight.changeTimer = Instant.now().plusMillis((long) (Math.random() * 3000));
			
			//Reset animation timers
			ResetTimers.reset(knight);
		}
		
		//Animate part with current passive action
		if (knight.passiveAction.contentEquals("WalkingForward")) WalkingForward.animate(knight, knight.shieldUp);
		if (knight.passiveAction.contentEquals("WalkingSide")) WalkingSide.animate(knight, knight.shieldUp);
		if (knight.passiveAction.contentEquals("Standing")) Standing.animate(knight, knight.shieldUp);
		if (knight.passiveAction.contentEquals("Running")) {
			knight.shieldUp = false;
			Running.animate(knight);
	    }
	}
}
