package me.jacobcrofts.simplestructureloader.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Selection {
	
	private Location left;
	private Location right;
	private List<SavedBlock> savedBlocks;
	
	public Selection() {
		this.savedBlocks = new ArrayList<SavedBlock>();
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
	
	public void save() {
		List<SavedBlock> blocks = new ArrayList<SavedBlock>();
		
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
					blocks.add(new SavedBlock(l.getBlock()));
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
