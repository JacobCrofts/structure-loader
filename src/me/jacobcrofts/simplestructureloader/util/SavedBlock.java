package me.jacobcrofts.simplestructureloader.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class SavedBlock {

	private final Material type;
	private final byte data;
	private final int relativeX;
	private final int relativeY;
	private final int relativeZ;
	
	@SuppressWarnings("deprecation")
	public SavedBlock(Block block) {
		this.type = block.getType();
		this.data = block.getData();
		Location location = block.getLocation();
		this.relativeX = location.getBlockX();
		this.relativeY = location.getBlockY();
		this.relativeZ = location.getBlockZ();
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
	
}
