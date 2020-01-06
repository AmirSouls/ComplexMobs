package complexMobs.object;

import org.bukkit.scheduler.BukkitTask;

public abstract class Animation {
	
	private BukkitTask thread;
	
	public abstract void thread();
	
	public void terminate() {
		if (thread != null) thread.cancel();
	}

}
