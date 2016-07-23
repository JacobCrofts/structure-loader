package me.jacobcrofts.simplestructureloader;

import org.bukkit.plugin.java.JavaPlugin;

import me.jacobcrofts.simplestructureloader.listeners.StructureListener;
import me.jacobcrofts.simplestructureloader.listeners.TrafficListener;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;

public class SimpleStructureLoader extends JavaPlugin {
	
	private SelectionManager manager;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new StructureListener(manager), this);
		this.getServer().getPluginManager().registerEvents(new TrafficListener(manager), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
