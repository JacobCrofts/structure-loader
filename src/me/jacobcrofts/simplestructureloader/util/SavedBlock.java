package me.jacobcrofts.simplestructureloader.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.json.simple.JSONObject;

public class SavedBlock {

	private final Material type;
	private final byte data;
	private final int relativeX;
	private final int relativeY;
	private final int relativeZ;
	
	@SuppressWarnings("deprecation")
	public SavedBlock(Location baseLocation, Block block) {
		this.type = block.getType();
		this.data = block.getData();
		Location relativeLocation = block.getLocation().subtract(baseLocation);
		this.relativeX = relativeLocation.getBlockX();
		this.relativeY = relativeLocation.getBlockY();
		this.relativeZ = relativeLocation.getBlockZ();
	}
	
	public Material getType() {
		return this.type;
	}
	
	public byte getData() {
		return this.data;
	}
	
	public int getRelativeX() {
		return this.relativeX;
	}
	
	public int getRelativeY() {
		return this.relativeY;
	}
	
	public int getRelativeZ() {
		return this.relativeZ;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("type", this.type);
		json.put("data", this.data);
		json.put("relative-x", this.relativeX);
		json.put("relative-y", this.relativeY);
		json.put("relative-z", this.relativeZ);
		return json;
	}
	
}
