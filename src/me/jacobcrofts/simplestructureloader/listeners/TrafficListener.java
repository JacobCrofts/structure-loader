package me.jacobcrofts.simplestructureloader.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jacobcrofts.simplestructureloader.managers.SelectionManager;

public class TrafficListener implements Listener {
	
	private final SelectionManager manager;
	
	public TrafficListener(SelectionManager manager) {
		this.manager = manager;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.manager.register(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent event) {
		this.manager.unregister(event.getPlayer());
	}

}
