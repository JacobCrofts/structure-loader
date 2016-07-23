package me.jacobcrofts.simplestructureloader.api;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import me.jacobcrofts.simplestructureloader.util.Selection;

public final class StructureAPI {
	
	private StructureAPI() {}
	
	public static void writeToFile(String path, Selection selection) throws IOException {
		FileWriter writer = new FileWriter(path);
		writer.write(selection.toJSON().toJSONString());
		writer.close();
	}
	
//	playerData = (JSONObject) new JSONParser().parse(new FileReader(f));

}
