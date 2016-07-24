package me.jacobcrofts.simplestructureloader;

import org.bukkit.plugin.java.JavaPlugin;

import me.jacobcrofts.simplestructureloader.commands.*;
import me.jacobcrofts.simplestructureloader.listeners.*;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;

public class SimpleStructureLoader extends JavaPlugin {
	
	private static SimpleStructureLoader thisInstance;
	
	public static SimpleStructureLoader thisInstance() {
		return thisInstance;
	}
	
	private SelectionManager selectionManager;
	
	public SelectionManager getSelectionManager() {
		return this.selectionManager;
	}
	
	@Override
	public void onEnable() {
		thisInstance = this;
		this.getServer().getPluginManager().registerEvents(new TrafficListener(), this);
		this.getServer().getPluginManager().registerEvents(new SelectionListener(), this);
		
		this.getCommand("save").setExecutor(new CmdSaveStructure());
		this.selectionManager = new SelectionManager();
	}
	
	@Override
	public void onDisable() {
		
		thisInstance = null;
	}

}
