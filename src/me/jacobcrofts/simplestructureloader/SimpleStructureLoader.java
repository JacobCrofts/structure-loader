package me.jacobcrofts.simplestructureloader;

import org.bukkit.plugin.java.JavaPlugin;

import me.jacobcrofts.simplestructureloader.listeners.StructureListener;

public class SimpleStructureLoader extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new StructureListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
