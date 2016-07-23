package me.jacobcrofts.simplestructureloader.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.jacobcrofts.simplestructureloader.managers.SelectionManager;

public class StructureListener implements Listener {
	
	private final SelectionManager manager;
	
	public StructureListener(SelectionManager manager) {
		this.manager = manager;
	}
	
	@EventHandler(priority= EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		// player can only use this plugin while in creative mode
		if (player.getGameMode() != GameMode.CREATIVE) {
			return;
		}
		
		// only an OP or an explicitly permissioned player can use this plugin
		if (!(player.isOp() || player.hasPermission("simple-structures.ALL"))) {
			return;
		}
		
		
		
	}

}
