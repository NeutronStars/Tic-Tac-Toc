package fr.morpion.ns.bukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import fr.morpion.ns.Morpion;

public final class InventoryCloseListener implements Listener {

	@EventHandler
	private void inventoryClose(InventoryCloseEvent ice){
		if(!Morpion.getServer().hasMorpionId(ice.getInventory().getTitle())) return;
		Morpion.forfaitGame((Player)ice.getPlayer());
	}
}
