package lothricKnights.Methods;

import java.time.Instant;

import org.bukkit.entity.ArmorStand;

import lothricKnights.Actions.Running;
import lothricKnights.Actions.Standing;
import lothricKnights.Actions.WalkingForward;
import lothricKnights.Actions.WalkingSide;
import lothricKnights.Main.LothricKnights;

public class PassiveAction {
	public static void select(ArmorStand main, String newPassiveAction, double distance3D) {
		//Shield down boolean
		if (!LothricKnights.shieldUp.containsKey(main)) LothricKnights.shieldUp.put(main, true);
		boolean shieldUp = LothricKnights.shieldUp.get(main);
		
		//check change timer
		if (Instant.now().isAfter(LothricKnights.changeTimer.get(main).plusMillis(3000))) {
			//Shield Boolean
			shieldUp = true;
			if (distance3D > 6 && Math.random() > .5) shieldUp = false;
			LothricKnights.shieldUp.put(main, shieldUp);
			
			//Put passive action and change timer
			LothricKnights.passiveAction.put(main, newPassiveAction);
			LothricKnights.changeTimer.put(main, Instant.now().plusMillis((long) (Math.random() * 3000)));
			
			//Reset animation timers
			ResetTimers.resetTimers(main);
		}
		
		//Animate part with current passive action
		if (LothricKnights.passiveAction.get(main).contentEquals("WalkingForward")) WalkingForward.animate(main, shieldUp);
		if (LothricKnights.passiveAction.get(main).contentEquals("WalkingSide")) WalkingSide.animate(main, shieldUp);
		if (LothricKnights.passiveAction.get(main).contentEquals("Standing")) Standing.standing(main, shieldUp);
		if (LothricKnights.passiveAction.get(main).contentEquals("Running")) Running.animate(main);
	}
}
