package me.jacobcrofts.simplestructureloader.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import me.jacobcrofts.simplestructureloader.util.Selection;

public final class StructureAPI {
	
	private StructureAPI() {}
	
	public static void writeToFile(String path, Selection selection) throws IOException {
		FileWriter writer = new FileWriter(path);
		writer.write(selection.toJSON().toJSONString());
		writer.close();
	}
	
	public static JSONArray readFromFile(String path) throws FileNotFoundException, IOException, ParseException {
		return (JSONArray) new JSONParser().parse(new FileReader(path));
	}
	
}
