package me.jacobcrofts.simplestructureloader.commands;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jacobcrofts.simplestructureloader.SimpleStructureLoader;
import me.jacobcrofts.simplestructureloader.api.StructureAPI;
import me.jacobcrofts.simplestructureloader.util.Selection;

public class CmdSaveStructure implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.getGameMode() != GameMode.CREATIVE) {
				player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
				return true;
			}
			
			if (!(player.isOp() || player.hasPermission("simple-structures.ALL"))) {
				player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
				return true;
			}
			
			Selection selection = SimpleStructureLoader.thisInstance().getSelectionManager().getSelection(player);
			selection.saveCurrentSelection();
			
			try {
				StructureAPI.writeToFile(args[0], selection);
			} catch (IOException e) {
				e.printStackTrace();
				player.sendMessage(ChatColor.RED + "Something went wrong whe we tried to write your structure to a file.");
			}
			
			player.sendMessage(ChatColor.DARK_PURPLE + "Successfully saved your structure in the directory 'plugins/structures/" + args[0] + ".json'.");
		
			return true;
			
		} else {
			sender.sendMessage("Only players may execute this command.");
		}
		
		
		return false;
	}

}
