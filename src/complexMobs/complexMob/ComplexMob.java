package complexMobs.complexMob;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import complexMobs.object.Part;

public interface ComplexMob {
	
	/**
	* Gets type ID of this ComplexMob
	* @return The ID of this ComplexMob
	*/
	String getID();
	
	/**
	* Gets the name of this ComplexMob
	* @return The name of this ComplexMob
	*/
	String getName();
	
	/**
	* Sets display name of this ComplexMob
	* @param The name to set of this ComplexMob
	*/
	void setName(String name);
	
	/**
	* Sets the main ArmorStand of this ComplexMob
	* @return The main ArmorStand of this ComplexMob
	*/
	ArmorStand getMain();
	
	/**
	* Sets the main ArmorStand of this ComplexMob
	* @param main The new main ArmorStand of this ComplexMob
	*/
	void setMain(ArmorStand main);
	
	/**
	* Gets the part map of this ComplexMob
	* @return The part map of this mob
	*/
	Map<String, Part> getParts();
	
	/**
	* Sets the part map of this ComplexMob
	* @param parts The new part map
	*/
	void setParts(Map<String, Part> parts);
	
	/**
	* Starts the logic BukkitRunnable of the ComplexMob
	*/
	void run();
	
	/**
	* Physically constructs the ComplexMob in game
	* @param spawnLocation The spawn location of the ComplexMob
	*/
	void build(Location spawnLocation);
	
	/**
	* Checks if this ComplexMob has been removed
	* @return True if the ComplexMob has been removed
	*/
	boolean isRemoved();
	
	/**
	* Marks if this ComplexMob is removed
	*/
	void setRemoved(boolean removed);
	
	/**
	* Removes this ComplexMob from the world
	*/
	void remove();
	
}
