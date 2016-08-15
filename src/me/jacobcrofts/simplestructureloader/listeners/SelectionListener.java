package me.jacobcrofts.simplestructureloader.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.jacobcrofts.simplestructureloader.SimpleStructureLoader;
import me.jacobcrofts.simplestructureloader.managers.SelectionManager;

public class SelectionListener implements Listener {
	
	@EventHandler
	public void onPlayerMakeSelection(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
				
		if (player.getGameMode() == GameMode.CREATIVE && player.isOp()) {
			
			Material heldMaterial = player.getInventory().getItemInMainHand().getType();
			SelectionManager manager = SimpleStructureLoader.thisInstance().getSelectionManager();
			
			if (heldMaterial == Material.GOLD_AXE) {
				
				if (action == Action.RIGHT_CLICK_BLOCK) {
					if (event.getHand() == EquipmentSlot.HAND) {						
						Location location = event.getClickedBlock().getLocation();
						manager.getSelection(player).setRightClickLocation(location);
						player.sendMessage(ChatColor.DARK_PURPLE + "Right-click selection: [" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]");
					}
				}
				
				if (action == Action.LEFT_CLICK_BLOCK) {
					Location location = event.getClickedBlock().getLocation();
					manager.getSelection(player).setLeftClickLocation(location);
					player.sendMessage(ChatColor.DARK_PURPLE + "Left-click selection: [" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]");
					event.setCancelled(true);
				}
				
			} else if (heldMaterial == Material.GOLD_SPADE) {
				
				if (action == Action.RIGHT_CLICK_BLOCK && event.getHand() == EquipmentSlot.HAND || action == Action.LEFT_CLICK_BLOCK) {
					Location location = event.getClickedBlock().getLocation();
					manager.getSelection(player).setCenter(location);
					player.sendMessage(ChatColor.DARK_PURPLE + "Selection centered at: [" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]");
					event.setCancelled(true);
				}
				
			}
			
		}
	}

}
