package fr.morpion.ns.morpion;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class TokenMorpion {

	private final PlayerMorpion player;
	private final Material token;
	private final byte data;
	
	public TokenMorpion(Material token, byte data, PlayerMorpion player) {
		this.player = player;
		this.token = token;
		this.data = data;
	}
	
	public PlayerMorpion getPlayer() {
		return player;
	}
	
	public ItemStack getTokenItem(){
		return new ItemStack(token, 1, data);
	}
	
	@Override
	public String toString() {
		return token.toString();
	}
}
