package me.jacobcrofts.simplestructureloader;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import me.jacobcrofts.simplestructureloader.commands.CmdSave;
import me.jacobcrofts.simplestructureloader.listeners.StructureListener;
import me.jacobcrofts.simplestructureloader.listeners.TrafficListener;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;

public class SimpleStructureLoader extends JavaPlugin {
	
	private SelectionManager manager;
	
	@Override
	public void onEnable() {
		
		File structures = new File("structures");
		if (!(structures.exists())) {			
			structures.mkdirs();
		}		
		this.manager = new SelectionManager();
		
		this.getCommand("save").setExecutor(new CmdSave(manager));
		
		this.getServer().getPluginManager().registerEvents(new StructureListener(manager), this);
		this.getServer().getPluginManager().registerEvents(new TrafficListener(manager), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
