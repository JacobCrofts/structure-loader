package me.jacobcrofts.simplestructureloader.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jacobcrofts.simplestructureloader.SimpleStructureLoader;

public class TrafficListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		SimpleStructureLoader.thisInstance().getSelectionManager().register(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		SimpleStructureLoader.thisInstance().getSelectionManager().unregister(event.getPlayer());
	}

}
