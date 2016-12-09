package fr.morpion.ns.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.morpion.ns.Morpion;
import fr.morpion.ns.morpion.Grid;
import fr.morpion.ns.morpion.PlayerMorpion;
import fr.morpion.ns.morpion.TokenMorpion;

public final class MorpionGame {

	private final PlayerMorpion pOne, pTwo;
	private PlayerMorpion pCurrent;
	private final Grid grid = new Grid();
	private final Inventory inventory;
	
	public MorpionGame(String title, PlayerMorpion pOne, PlayerMorpion pTwo) {
		inventory = Bukkit.createInventory(null, 27, title);
		
		for(int i = 0; i < 27; i++){
			if(i != 3 && i != 4 && i != 5 && i != 12 && i != 13 && i != 14 && i != 21 && i != 22 && i != 23)
				inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)Morpion.getRandom(16)));
		}
		
		this.pOne = pOne;
		this.pTwo = pTwo;
		pCurrent = pOne;
		pOne.getPlayer().openInventory(getInventory());
		pTwo.getPlayer().openInventory(getInventory());
		pCurrent.getPlayer().sendMessage(Morpion.getPrefix()+"§2C'est vous qui commencez.");
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public PlayerMorpion getPlayerOne() {
		return pOne;
	}
	
	public PlayerMorpion getPlayerTwo() {
		return pTwo;
	}
	
	public PlayerMorpion getCurrentPlayer() {
		return pCurrent;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setToken(int slot, int id, TokenMorpion token){
		inventory.setItem(slot, token.getTokenItem());
		getGrid().setToken(id, token);
	}
	
	public String getWinnerName(){
		return pCurrent.getPlayer().getName();
	}
	
	public String getLooserName(){
		return pCurrent.equals(pOne) ? pTwo.getPlayer().getName() : pOne.getPlayer().getName();
	}
	
	public void swapPlayer(){
		if(pCurrent.equals(pOne)) pCurrent = pTwo;
		else pCurrent = pOne;
	}
}
