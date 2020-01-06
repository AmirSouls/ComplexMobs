package complexMobs.object;

import org.bukkit.util.Vector;

public class SoulsWeapon {
	
	public SoulsWeapon(double length, double width, double depth, Vector offset) {
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.offset = offset;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	public double getDepth() {
		return this.depth;
	}
	
	public void setOffset(Vector offset) {
		this.offset = offset;
	}
	
	public Vector getVector() {
		return this.offset;
	}
	
	private double length;
	
	private double width;
	
	private double depth;
	
	private Vector offset;
}
