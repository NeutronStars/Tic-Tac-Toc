package fr.morpion.ns.bukkit.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.morpion.ns.Morpion;
import fr.morpion.ns.game.MorpionGame;
import fr.morpion.ns.morpion.Grid.WinType;

public final class InventoryClickListener implements Listener {

	@EventHandler
	private void inventoryClick(InventoryClickEvent ice){
		if(ice.getClickedInventory() == null) return;
		if(!Morpion.getServer().hasMorpionId(ice.getClickedInventory().getTitle())) return;
		ice.setCancelled(true);
		
		MorpionGame morpion = Morpion.getServer().getMorpion(ice.getClickedInventory().getTitle());
		
		if(!morpion.getCurrentPlayer().getPlayer().equals((Player)ice.getWhoClicked())){
			ice.getWhoClicked().sendMessage(Morpion.getPrefix()+"§4Ce n'est pas à votre tour.");
			return;
		}
		
		int id = ice.getSlot() < 6 && ice.getSlot() > 2 ? ice.getSlot() - 3 : ice.getSlot() < 15 && ice.getSlot() > 11 ? ice.getSlot() - 9 : ice.getSlot() < 24 && ice.getSlot() > 20 ? ice.getSlot() - 15 : -1;
		
		if(id == -1) return;
		
		Bukkit.broadcastMessage(id+"");
		
		if(morpion.getGrid().hasToken(id)){
			ice.getWhoClicked().sendMessage(Morpion.getPrefix()+"§4Le slot n'est pas vide.");
			return;
		}
		
		morpion.setToken(ice.getSlot(), id, morpion.getCurrentPlayer().getToken());
		
		if(morpion.getGrid().hasWin(morpion.getCurrentPlayer().getToken())){
			if(morpion.getGrid().getWinType().equals(WinType.PLAYER)) Bukkit.broadcastMessage(Morpion.getPrefix()+"§6"+morpion.getWinnerName()+" §2a remporté une partie de morpion contre §6"+morpion.getLooserName()+"§2.");
			else Bukkit.broadcastMessage(Morpion.getPrefix()+"§6"+morpion.getWinnerName()+" §5& §6"+morpion.getLooserName()+" §5ont fait un null au morpion.");
			
			Morpion.getServer().removeMorpion(ice.getClickedInventory().getTitle());
			morpion.getPlayerOne().getPlayer().closeInventory();
			morpion.getPlayerTwo().getPlayer().closeInventory();
			
			return;
		}
		
		morpion.swapPlayer();
		
		morpion.getPlayerOne().getPlayer().sendMessage(Morpion.getPrefix()+"§2C'est au tour de "+morpion.getWinnerName());
		morpion.getPlayerTwo().getPlayer().sendMessage(Morpion.getPrefix()+"§2C'est au tour de "+morpion.getWinnerName());
		
	}
}
