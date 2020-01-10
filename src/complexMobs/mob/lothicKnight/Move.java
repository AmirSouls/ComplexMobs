package complexMobs.mob.lothicKnight;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;
import net.md_5.bungee.api.ChatColor;

public class Move {

	public void run(LothricKnight lothricKnight, double vectorScalar, double angleTurn, double angleOffset) {
		Vector targetDirection = lothricKnight.getTargeter().getLocation().getDirection();
		double targetYaw = Math.atan2(targetDirection.getX(), targetDirection.getZ()) * 57.29; //Yaw that the mob needs to turn to
		
		double mobYaw = lothricKnight.getMain().getLocation().getYaw(); //Current yaw of the mob
		
		double targetCompareYaw = targetYaw;
		//if (targetCompareYaw > 0) targetCompareYaw -= 360;
		//targetCompareYaw *= -1;
		
		Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + targetCompareYaw);
		Bukkit.broadcastMessage("" + mobYaw);
		
		double deltaYaw = targetCompareYaw - mobYaw;
		double newYaw = mobYaw;
		
		
		if (deltaYaw > 0 && deltaYaw < 180 || deltaYaw < -180) {
			newYaw += angleTurn;
			if (newYaw > targetCompareYaw) newYaw = targetCompareYaw;
		}
		else if (deltaYaw < 0 || deltaYaw >= 180) {
			newYaw -= angleTurn;
			if (newYaw < targetCompareYaw) newYaw = targetCompareYaw;
		}
		
		Vector moveVector = lothricKnight.getMain().getLocation().getDirection().rotateAroundY(angleOffset).multiply(vectorScalar);
		Location newLocation = lothricKnight.getMain().getLocation();
			
		if (newLocation.clone().add(0,-1,0).getBlock().isPassable()) {
			newLocation = newLocation.clone().add(0,-1,0).getBlock().getLocation();
		}
		else {
			
			newLocation.setY(newLocation.clone().add(0,-1,0).getBlockY() + 1);
			
			newLocation.add(moveVector);
			if (
					!newLocation.clone().getBlock().isPassable() && 
					newLocation.clone().add(0,1,0).getBlock().isPassable() && 
					newLocation.clone().add(0,2,0).getBlock().isPassable() &&
					newLocation.clone().add(0,3,0).getBlock().isPassable() &&
					newLocation.clone().add(0,4,0).getBlock().isPassable()) {
				newLocation = newLocation.clone().add(0,1,0);
			}
			else if (
					!newLocation.clone().getBlock().isPassable() || 
					!newLocation.clone().add(0,1,0).getBlock().isPassable() || 
					!newLocation.clone().add(0,2,0).getBlock().isPassable() ||
					!newLocation.clone().add(0,3,0).getBlock().isPassable()) {
				newLocation.subtract(moveVector);
				newLocation.add(moveVector.clone().setX(0));
				if (
						!newLocation.clone().getBlock().isPassable() || 
						!newLocation.clone().add(0,1,0).getBlock().isPassable() || 
						!newLocation.clone().add(0,2,0).getBlock().isPassable() ||
						!newLocation.clone().add(0,3,0).getBlock().isPassable()) {
					newLocation.subtract(moveVector.clone().setX(0));
				}
				newLocation.add(moveVector.clone().setZ(0));
				if (
						!newLocation.clone().getBlock().isPassable() || 
						!newLocation.clone().add(0,1,0).getBlock().isPassable() || 
						!newLocation.clone().add(0,2,0).getBlock().isPassable() ||
						!newLocation.clone().add(0,3,0).getBlock().isPassable()) {
					newLocation.subtract(moveVector.clone().setZ(0));
				}
			}
		}
		
		//if (newYaw > 0) newYaw -= 360;
		//newYaw *= -1;
		newLocation.setDirection(new Vector(0,0,1).rotateAroundY(newYaw / 57.29));
		//lothricKnight.getMain().teleport(newLocation.clone());
		net.minecraft.server.v1_13_R2.Entity nmsMain = ((CraftEntity) lothricKnight.getMain()).getHandle();
		nmsMain.setPositionRotation(newLocation.getX(), newLocation.getY(), newLocation.getZ(), (float) newYaw, 0f);
		
	}
}
