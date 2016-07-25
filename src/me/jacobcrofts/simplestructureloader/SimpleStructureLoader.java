package me.jacobcrofts.simplestructureloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import me.jacobcrofts.simplestructureloader.commands.*;
import me.jacobcrofts.simplestructureloader.listeners.*;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;
import me.jacobcrofts.simplestructureloader.util.SavedBlock;
import me.jacobcrofts.simplestructureloader.util.Selection;

public final class SimpleStructureLoader extends JavaPlugin {
	
	private static SimpleStructureLoader thisInstance;
	
	public static SimpleStructureLoader thisInstance() {
		return thisInstance;
	}
	
	private SelectionManager selectionManager;
	
	public SelectionManager getSelectionManager() {
		return this.selectionManager;
	}
	
	@Override
	public void onEnable() {
		thisInstance = this;
		this.getServer().getPluginManager().registerEvents(new TrafficListener(), this);
		this.getServer().getPluginManager().registerEvents(new SelectionListener(), this);
		
		this.getCommand("save").setExecutor(new CmdSaveStructure());
		this.getCommand("paste").setExecutor(new CmdPasteStructure());
		this.selectionManager = new SelectionManager();
	}
	
	@Override
	public void onDisable() {
		
		thisInstance = null;
	}
	
	public static class API {
		
		private API() {}		
		
		public static void writeToFile(String filename, Selection selection) throws IOException {
			
			File structures = new File("plugins/structures");
			if (!(structures.exists())) {			
				structures.mkdirs();
			}
			
			FileWriter writer = new FileWriter("plugins/structures/" + filename + ".json");
			writer.write(selection.toJSON().toJSONString());
			writer.close();
			
		}
		
		public static JSONArray readFromFile(String path) throws FileNotFoundException, IOException, ParseException {
			return (JSONArray) new JSONParser().parse(new FileReader(path));
		}
		
		@SuppressWarnings("deprecation")
		public static void placeStructure(String fileName, Location baseLocation) {
			Selection selection;
			try {
				selection = new Selection(readFromFile("plugins/structures/" + fileName + ".json"));
				List<SavedBlock> doLater = new ArrayList<SavedBlock>();
				
				for (SavedBlock savedBlock : selection.getSavedBlocks()) {
					if (savedBlock.isAttachable()) {
						doLater.add(0, savedBlock);
					} else {
						Block realBlock = baseLocation.clone().add(savedBlock.getRelativeX(), savedBlock.getRelativeY(), savedBlock.getRelativeZ()).getBlock();
						realBlock.setType(savedBlock.getType());
						realBlock.setData(savedBlock.getData());
					}
				}
				
				for (SavedBlock savedBlock : doLater) {
					Block realBlock = baseLocation.clone().add(savedBlock.getRelativeX(), savedBlock.getRelativeY(), savedBlock.getRelativeZ()).getBlock();
					Block below = realBlock.getRelative(BlockFace.DOWN);
					
					Material belowType = below.getType();
					byte belowData = below.getData();
					
					if (!below.getType().isSolid()) {
						below.setType(Material.STONE);
					}
					
					realBlock.setType(savedBlock.getType());
					realBlock.setData(savedBlock.getData());
					
					below.setType(belowType);
					below.setData(belowData);
				}
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		
	}

}
