package complexMobs.complexMob;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public interface ComplexMob {
	
	String getID();
	
	String getName();
	
	void setName(String name);
	
	ArmorStand getMain();
	
	void setMain(ArmorStand main);
	
	List<ArmorStand> getParts();
	
	void setParts(List<ArmorStand> parts);
	
	void run();
	
	void build(Location spawnLocation);
	
}
