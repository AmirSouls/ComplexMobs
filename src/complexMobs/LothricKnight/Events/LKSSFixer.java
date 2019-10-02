package complexMobs.LothricKnight.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class LKSSFixer implements Listener {
	
	@EventHandler
	public void use(PlayerItemDamageEvent event) {
		event.setCancelled(true);
	}
}
