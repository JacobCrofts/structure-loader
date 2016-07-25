package me.jacobcrofts.simplestructureloader.commands;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jacobcrofts.simplestructureloader.SimpleStructureLoader;
import me.jacobcrofts.simplestructureloader.util.Selection;

public class CmdSaveSpecialStructure implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may perform this command.");
			return true;
		}
				
		Player player = (Player) sender;
		Material baseMaterial = null;
		Byte baseData = null;
		Boolean ignoreBaseBlocks = null;
		
		if (!(player.isOp())) {
			player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
			return true;
		}
		
		if (player.getGameMode() != GameMode.CREATIVE) {
			player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
			return true;
		}
		
		if (args.length != 4) {
			player.sendMessage(ChatColor.RED + "Wrong number of arguments.");
			return false;
		}
		
		baseMaterial = Material.getMaterial(args[1].toUpperCase());
		
		if (baseMaterial == null) {
			player.sendMessage("No such Material '" + args[1] + "'.");
			return false;
		}
		
		try {
			baseData = Byte.parseByte(args[2]);
		} catch (NumberFormatException e) {
			player.sendMessage(ChatColor.RED + "Wrong command format.");
			return false;
		}
		
		ignoreBaseBlocks = Boolean.parseBoolean(args[3]);
		
		Selection selection = SimpleStructureLoader.thisInstance().getSelectionManager().getSelection(player);
		selection.saveCurrentSelectionOnlyAbove(baseMaterial, baseData, ignoreBaseBlocks);
		
		try {
			SimpleStructureLoader.API.writeToFile(args[0], selection);
		} catch (IOException e) {
			e.printStackTrace();
			player.sendMessage(ChatColor.RED + "Something went wrong whe we tried to write your structure to a file.");
		}
		
		player.sendMessage(ChatColor.DARK_PURPLE + "Successfully saved your structure in the directory 'plugins/structures/" + args[0] + ".json'.");
		
		return true;
		
	}

}
