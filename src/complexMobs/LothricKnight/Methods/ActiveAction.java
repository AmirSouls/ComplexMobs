package complexMobs.LothricKnight.Methods;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import complexMobs.Mobs.LothricKnight;

public class ActiveAction {
	public static void select(LothricKnight knight, double distance3D) {
		//Default to 35 stamina use
		double staminaUseAmount = 35;
		
		//Initialize action list
		Collection<String> actions = new ArrayList<>();
		
		//Close range
		if (distance3D > 2 && distance3D < 5 && knight.stamina > 34)  {
			actions.add("LeftSlash"); 
			if (Math.random() < 0.4) actions.add("RightSlash");
		}
		//Medium range
		else if (distance3D > 5 && distance3D < 15 && knight.stamina > 34) {
			if (Math.random() < .008) {
				actions.add("Stance"); 
				staminaUseAmount = 100;
			}
		}
		
		if (!actions.isEmpty()) {
			Collections.shuffle((List<?>) actions);
			knight.isAttacking = true;
			knight.attackAreaPts = null;
			knight.staminaUseTimer = Instant.now();
			knight.changeTimer = Instant.now();
			String action = (String) actions.toArray()[0];
			
			//Put selected action
			knight.activeAction = action;
			knight.stamina = knight.stamina - staminaUseAmount;
			
			//Reset animation timers
			ResetTimers.reset(knight);
		}
	}
}
