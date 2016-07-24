package me.jacobcrofts.simplestructureloader.commands;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import me.jacobcrofts.simplestructureloader.api.StructureAPI;
import me.jacobcrofts.simplestructureloader.util.Selection;

public class CmdPasteStructure implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may perform this command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (args.length != 1) {
			player.sendMessage(ChatColor.RED + "Wrong number of arguments.");
			return false;
		}
		
		if (!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
			return true;
		}
		
		if (!(player.getGameMode() != GameMode.CREATIVE)) {
			player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
			return true;
		}
		
		try {
			Selection selection = new Selection(StructureAPI.readFromFile("plugins/structures/" + args[0] + ".json"));
			StructureAPI.placeStructure(selection, player.getLocation());
		} catch (IOException | ParseException e) {
			player.sendMessage(ChatColor.RED + "Internal exception.");
			e.printStackTrace();
		}

		return true;
		
	}

}
