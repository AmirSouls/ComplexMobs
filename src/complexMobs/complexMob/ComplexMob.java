package complexMobs.complexMob;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import complexMobs.object.Part;

public interface ComplexMob {
	
	String getID();
	
	String getName();
	
	void setName(String name);
	
	ArmorStand getMain();
	
	void setMain(ArmorStand main);
	
	Map<String, Part> getParts();
	
	void setParts(Map<String, Part> parts);
	
	void run();
	
	void build(Location spawnLocation);
	
}
