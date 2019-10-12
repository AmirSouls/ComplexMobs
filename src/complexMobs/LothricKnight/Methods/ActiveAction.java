package complexMobs.LothricKnight.Methods;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.ArmorStand;

import complexMobs.Main.ComplexMob;
import complexMobs.Main.ComplexMobs;
import complexMobs.Methods.ToggleSound;
import complexMobs.Mobs.LothricKnight;

public class ActiveAction {
	public static void select(LothricKnight knight, double distance3D) {
		
		//Hold for ally
		for (ArmorStand armorStand : knight.main.getWorld().getEntitiesByClass(ArmorStand.class)) {
			if (ComplexMobs.isMain.containsKey(armorStand)) {
				double distance = armorStand.getLocation().distance(knight.main.getLocation());
				if (distance < 5) {
					ComplexMob mob = ComplexMobs.partMob.get(armorStand);
					if (mob.isAttacking) {
						return;
					}
				}
			}
		}
		
		//Default to 35 stamina use
		double staminaUseAmount = 35;
		
		//Initialize action list
		Collection<String> actions = new ArrayList<>();
		
		//Really close range
		if (distance3D < 4 && Math.random() < 0.4) {
			actions.add("ShieldBash"); 
		}
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
			
			//Re-enable all sounds
			ToggleSound.enableAllSounds(knight);
		}
	}
}
