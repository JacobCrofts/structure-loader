package me.jacobcrofts.simplestructureloader.util;

import org.bukkit.Location;

public class Selection {
	
	private Location left;
	private Location right;
	
	public Selection() {
		
	}
	
	public Location getLeftClickLocation() {
		return this.left;
	}
	
	public Location getRightClickLocation() {
		return this.right;
	}
	
	public void setLeftClickLocation(Location location) {
		this.left = location;
	}
	
	public void setRightClickLocation(Location location) {
		this.right = location;
	}

}
