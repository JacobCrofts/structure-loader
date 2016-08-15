package me.jacobcrofts.simplestructureloader.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import me.jacobcrofts.simplestructureloader.SimpleStructureLoader;

public class Selection {
	
	private Location leftClickLocation;
	private Location rightClickLocation;
	private List<SavedBlock> savedBlocks;
	private Location center;
	
	public Selection() {
		this.savedBlocks = new ArrayList<SavedBlock>();
	}
	
	@SuppressWarnings("unchecked")
	public Selection(World world, JSONObject object) {
		this();
		JSONArray blocks = (JSONArray) object.get("blocks");
		Iterator<JSONObject> iterator = blocks.iterator();
		while (iterator.hasNext()) {
			this.savedBlocks.add(new SavedBlock(iterator.next()));
		}
		this.center = new Location(world, (long) object.get("center-x"), (long) object.get("center-y"), (long) object.get("center-z"));
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
	
	public Location getCenter() {
		return this.center;
	}
	
	public void setCenter(Location newCenter) {
		this.center = newCenter;
	}
	
	public void saveCurrentSelection() {
		List<SavedBlock> blocks = new ArrayList<SavedBlock>();
		
		if (leftClickLocation != null && rightClickLocation != null) {
			
			int xMin = Math.min(leftClickLocation.getBlockX(), rightClickLocation.getBlockX());
			int xMax = Math.max(leftClickLocation.getBlockX(), rightClickLocation.getBlockX());
			int yMin = Math.min(leftClickLocation.getBlockY(), rightClickLocation.getBlockY());
			int yMax = Math.max(leftClickLocation.getBlockY(), rightClickLocation.getBlockY());
			int zMin = Math.min(leftClickLocation.getBlockZ(), rightClickLocation.getBlockZ());
			int zMax = Math.max(leftClickLocation.getBlockZ(), rightClickLocation.getBlockZ());
			
			World world = leftClickLocation.getWorld();
//			Location baseBlock = new Location(world, xMin, yMin, zMin);
			
			for (int x = xMin; x <= xMax; x++) {
				for (int y = yMin; y <= yMax; y++) {
					for (int z = zMin; z <= zMax; z++) {
						Location l = new Location(world, x, y, z);
						blocks.add(new SavedBlock(center, l.getBlock()));
					}
				}
			}
			
		}
		
		this.savedBlocks = blocks;
	}
	
	// Saves only the blocks that are somewhere above blocks that match baseType and baseData.
	// Use non-natural blocks (like stained glass) as your base material or build your structure in a superflat world.
	// If ignoreBaseBlocks is true, they will not appear anywhere in your structure.
	public void saveCurrentSelectionOnlyAbove(Material baseType, byte baseData, boolean ignoreBaseBlocks) {
		List<SavedBlock> blocks = new ArrayList<SavedBlock>();
		
		if (leftClickLocation != null && rightClickLocation != null) {
			
			int xMin = Math.min(leftClickLocation.getBlockX(), rightClickLocation.getBlockX());
			int xMax = Math.max(leftClickLocation.getBlockX(), rightClickLocation.getBlockX());
			int yMin = Math.min(leftClickLocation.getBlockY(), rightClickLocation.getBlockY());
			int yMax = Math.max(leftClickLocation.getBlockY(), rightClickLocation.getBlockY());
			int zMin = Math.min(leftClickLocation.getBlockZ(), rightClickLocation.getBlockZ());
			int zMax = Math.max(leftClickLocation.getBlockZ(), rightClickLocation.getBlockZ());
			
			World world = leftClickLocation.getWorld();
//			Location baseBlock = new Location(world, xMin, yMin, zMin);
			
			for (int x = xMin; x <= xMax; x++) {
				for (int y = yMin; y <= yMax; y++) {
					for (int z = zMin; z <= zMax; z++) {
						Location l = new Location(world, x, y, z);
						Block b = l.getBlock();
						if (SimpleStructureLoader.API.isPartOfShape(b, baseType, baseData)) {
							blocks.add(new SavedBlock(center, b));
						}
					}
				}
			}
			
			if (ignoreBaseBlocks) {
				Iterator<SavedBlock> it = blocks.iterator();
				while (it.hasNext()) {
					SavedBlock savedBlock = it.next();
					if (savedBlock.getType() == baseType && savedBlock.getData() == baseData) {
						it.remove();
					}
				}
			}
			
		}
		
		this.savedBlocks = blocks;
	}
	
	public List<SavedBlock> getSavedBlocks() {
		return this.savedBlocks;
	}
	
	@SuppressWarnings({ "unchecked" })
	public JSONObject toJSON() {
		JSONObject object = new JSONObject();
		JSONArray blockDataArray = new JSONArray();
		
		for (SavedBlock block : this.savedBlocks) {
			blockDataArray.add(block.toJSON());
		}
		
		object.put("blocks", blockDataArray);
		object.put("center-x", this.center.getBlockX());
		object.put("center-y", this.center.getBlockY());
		object.put("center-z", this.center.getBlockZ());
		
		return object;
	}

}
