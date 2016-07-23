package me.jacobcrofts.simplestructureloader.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.jacobcrofts.simplestructureloader.util.Selection;

public class SelectionManager {
	
	private Map<Player, Selection> selections;
	
	public SelectionManager() {
		this.selections = new HashMap<Player, Selection>();
	}
	
	public Selection getSelection(Player player) {
		return this.selections.get(player);
	}
	
	public void register(Player player) {
		this.selections.put(player, new Selection());
	}
	
	public void unregister(Player player) {
		this.selections.remove(player);
	}

}
