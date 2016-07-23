package me.jacobcrofts.simplestructureloader.commands;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import me.jacobcrofts.simplestructureloader.api.StructureAPI;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;
import me.jacobcrofts.simplestructureloader.util.Selection;

public class CmdSave extends InGameCommandExecutor {
	
	private SelectionManager manager;
	
	public CmdSave(SelectionManager manager) {
		this.manager = manager;
	}

	@Override
	@CommandInputHandler(minArgs = 1, maxArgs = 1)
	public boolean onCommand(Player player, String[] args) {
		
		if (player.getGameMode() != GameMode.CREATIVE) {
			player.sendMessage(ChatColor.RED + "You must be in creative mode to execute this command.");
			return true;
		}
		
		if (!(player.isOp() || player.hasPermission("simple-structures.ALL"))) {
			player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
			return true;
		}
		
		Selection selection = this.manager.getSelection(player);
		selection.saveCurrentSelection();
		
		try {
			StructureAPI.writeToFile(args[0], selection);
		} catch (IOException e) {
			e.printStackTrace();
			player.sendMessage(ChatColor.RED + "Something went wrong whe we tried to write your structure to a file.");
		}
		
		return true;
		
	}

}
