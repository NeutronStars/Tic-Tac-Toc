package fr.morpion.ns.morpion;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class PlayerMorpion {

	private final Player player;
	private final TokenMorpion token;
	
	public PlayerMorpion(Player player, Material token, byte data) {
		this.player = player;
		this.token = new TokenMorpion(token, data, this);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public TokenMorpion getToken() {
		return token;
	}
}
