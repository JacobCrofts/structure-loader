package me.jacobcrofts.simplestructureloader.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import me.jacobcrofts.simplestructureloader.api.StructureAPI;
import me.jacobcrofts.simplestructureloader.util.Selection;

public class CmdTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {			
			Selection selection;
			try {
				selection = new Selection(StructureAPI.readFromFile("plugins/structures/" + args[0] + ".json"));
				StructureAPI.placeStructure(selection, ((Player) sender).getLocation());
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}

}
