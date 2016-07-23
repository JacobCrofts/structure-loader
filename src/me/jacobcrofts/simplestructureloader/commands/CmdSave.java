package me.jacobcrofts.simplestructureloader.commands;

import java.io.IOException;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jacobcrofts.simplestructureloader.api.StructureAPI;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;
import me.jacobcrofts.simplestructureloader.util.Selection;

public class CmdSave implements CommandExecutor {
	
	private SelectionManager manager;
	
	public CmdSave(SelectionManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.getGameMode() != GameMode.CREATIVE) {
				return false;
			}
			
			if (!(player.isOp() || player.hasPermission("simple-structures.ALL"))) {
				return false;
				// TODO: custom error messages
			}
			
			Selection selection = this.manager.getSelection(player);
			selection.saveCurrentSelection();
			try {
				StructureAPI.writeToFile(args[0], selection);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
			
		}
		
		return false;
	}

}
