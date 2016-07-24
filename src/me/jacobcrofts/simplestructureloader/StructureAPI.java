package me.jacobcrofts.simplestructureloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class StructureAPI {
	
	private StructureAPI() {}
	
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
	public static void placeStructure(Selection selection, Location baseLocation) {
		for (SavedBlock savedBlock : selection.getSavedBlocks()) {
			Block realBlock = baseLocation.clone().add(savedBlock.getRelativeX(), savedBlock.getRelativeY(), savedBlock.getRelativeZ()).getBlock();
			realBlock.setType(savedBlock.getType());
			realBlock.setData(savedBlock.getData());
		}
	}
	
}
