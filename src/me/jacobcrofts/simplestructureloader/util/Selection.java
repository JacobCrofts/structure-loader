package me.jacobcrofts.simplestructureloader.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Selection {
	
	private Location left;
	private Location right;
	private List<Block> blocks;
	
	public Selection() {
		this.blocks = new ArrayList<Block>();
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
		List<Block> blocks = new ArrayList<Block>();
		
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
					blocks.add(l.getBlock());
				}
			}
		}
		
		this.blocks = blocks;
	}
	
	public List<Block> getBlocks() {
		return this.blocks;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public JSONArray toJSON() {
		JSONArray blockDataArray = new JSONArray();
		
		List<Block> blocks = this.getBlocks();
		
		for (Block block : blocks) {
			JSONObject blockJSON = new JSONObject();
			blockJSON.put("x", block.getLocation().getBlockX());
			blockJSON.put("y", block.getLocation().getBlockY());
			blockJSON.put("z", block.getLocation().getBlockZ());
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
