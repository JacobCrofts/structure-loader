package me.jacobcrofts.simplestructureloader.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import me.jacobcrofts.simplestructureloader.util.SavedBlock;
import me.jacobcrofts.simplestructureloader.util.Selection;

public final class StructureAPI {
	
	private StructureAPI() {}
	
	public static void writeToFile(String filename, Selection selection) throws IOException {
		FileWriter writer = new FileWriter("structures/" + filename + ".json");
		writer.write(selection.toJSON().toJSONString());
		writer.close();
	}
	
	public static JSONArray readFromFile(String path) throws FileNotFoundException, IOException, ParseException {
		return (JSONArray) new JSONParser().parse(new FileReader(path));
	}
	
	@SuppressWarnings("deprecation")
	public static void placeStructure(Selection selection, Location baseLocation) {
		for (SavedBlock block : selection.getSavedBlocks()) {
			Block realBlock = baseLocation.add(block.getRelativeX(), block.getRelativeY(), block.getRelativeZ()).getBlock();
			realBlock.setType(block.getType());
			realBlock.setData(block.getData());
		}
	}
	
}
