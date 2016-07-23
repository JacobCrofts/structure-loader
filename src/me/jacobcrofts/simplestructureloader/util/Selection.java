package me.jacobcrofts.simplestructureloader.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Selection {
	
	private Location leftClickLocation;
	private Location rightClickLocation;
	private List<SavedBlock> savedBlocks;
	
	public Selection() {
		this.savedBlocks = new ArrayList<SavedBlock>();
	}
	
	public Location getLeftClickLocation() {
		return this.leftClickLocation;
	}
	
	public Location getRightClickLocation() {
		return this.rightClickLocation;
	}
	
	public void setLeftClickLocation(Location location) {
		this.leftClickLocation = location;
	}
	
	public void setRightClickLocation(Location location) {
		this.rightClickLocation = location;
	}
	
	public void save() {
		List<SavedBlock> blocks = new ArrayList<SavedBlock>();
		
		int xMin = Math.min(leftClickLocation.getBlockX(), rightClickLocation.getBlockX());
		int xMax = Math.max(leftClickLocation.getBlockX(), rightClickLocation.getBlockX());
		int yMin = Math.min(leftClickLocation.getBlockY(), rightClickLocation.getBlockY());
		int yMax = Math.max(leftClickLocation.getBlockY(), rightClickLocation.getBlockY());
		int zMin = Math.min(leftClickLocation.getBlockZ(), rightClickLocation.getBlockZ());
		int zMax = Math.max(leftClickLocation.getBlockZ(), rightClickLocation.getBlockZ());
		
		World world = leftClickLocation.getWorld();
		Location baseBlock = new Location(world, xMin, yMin, zMin);
		
		for (int x = xMin; x <= xMax; x++) {
			for (int y = yMin; y <= yMax; y++) {
				for (int z = zMin; z <= zMax; z++) {
					Location l = new Location(world, x, y, z);
					blocks.add(new SavedBlock(baseBlock, l.getBlock()));
				}
			}
		}
		
		this.savedBlocks = blocks;
	}
	
	public List<SavedBlock> getSavedBlocks() {
		return this.savedBlocks;
	}
	
	@SuppressWarnings({ "unchecked" })
	public JSONArray toJSON() {
		JSONArray blockDataArray = new JSONArray();
		
		List<SavedBlock> blocks = this.getSavedBlocks();
		
		for (SavedBlock block : blocks) {
			JSONObject blockJSON = new JSONObject();
			blockJSON.put("x", block.getRelativeX());
			blockJSON.put("y", block.getRelativeY());
			blockJSON.put("z", block.getRelativeZ());
			blockJSON.put("type", block.getType().toString());
			blockJSON.put("data", block.getData());
			blockDataArray.add(blockJSON);
		}
		
		return blockDataArray;
	}
	
	public void saveFromJSON(JSONObject json) {
		// TODO
	}

}
