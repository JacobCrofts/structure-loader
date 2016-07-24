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
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.getGameMode() == GameMode.CREATIVE && player.isOp()) {				
				Selection selection;
				try {
					selection = new Selection(StructureAPI.readFromFile("plugins/structures/" + args[0] + ".json"));
					StructureAPI.placeStructure(selection, player.getLocation());
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
			}
			return true;
		} else {
			sender.sendMessage("Only players may perform this command.");
		}
		
		return false;
	}

}
