package me.jacobcrofts.simplestructureloader.util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

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
	
	public Map<Location, Block> getLocationsAndBlocks() {
		Map<Location, Block> blockData = new HashMap<Location, Block>();
		
		int xMin = Math.min(left.getBlockX(), right.getBlockX());
		int xMax = Math.max(left.getBlockX(), right.getBlockX());
		int yMin = Math.min(left.getBlockY(), right.getBlockY());
		int yMax = Math.max(left.getBlockY(), right.getBlockY());
		int zMin = Math.min(left.getBlockZ(), right.getBlockZ());
		int zMax = Math.max(left.getBlockZ(), right.getBlockZ());
		
		World world = left.getWorld();
		
		for (int x = xMin; x <= xMax; x++) {
			for (int y = yMin; y <= yMax; y++) {
				for (int z = zMin; z <= zMax; z++) {
					Location l = new Location(world, x, y, z);
					blockData.put(l, l.getBlock());
				}
			}
		}
		
		return blockData;
	}

}
