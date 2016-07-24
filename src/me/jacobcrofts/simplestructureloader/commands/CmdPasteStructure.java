package me.jacobcrofts.simplestructureloader.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jacobcrofts.simplestructureloader.SimpleStructureLoader;

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
		
		if (player.getGameMode() != GameMode.CREATIVE) {
			player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
			return true;
		}
		
		SimpleStructureLoader.API.placeStructure(args[0], player.getLocation());

		return true;
		
	}

}
