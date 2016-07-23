package me.jacobcrofts.simplestructureloader.commands;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class InGameCommandExecutor implements CommandExecutor {

	@Override
	public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command.");
		}
		
		Player player = (Player) sender;
		
		if (!this.checkArgumentLength(args)) {
			player.sendMessage(ChatColor.RED + "Wrong number of arguments.");
			return false;
		}
		
		return onCommand(player, args);
	}
	
	@CommandInputHandler
	public abstract boolean onCommand(Player player, String[] args);
	
	private final boolean checkArgumentLength(String[] args) {
		Method method = null;
		
		try {
			method = this.getClass().getDeclaredMethod("onCommand", Player.class, String[].class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		CommandInputHandler inputHandler = (CommandInputHandler) method.getAnnotation(CommandInputHandler.class);
		if (inputHandler.minArgs() > args.length || inputHandler.maxArgs() < args.length) {
			return false;
		}
		
		return true;
	}

}
