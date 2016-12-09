package fr.morpion.ns.server;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.morpion.ns.game.MorpionGame;

public interface IMorpionServer {

	public JavaPlugin getPlugin();
	public String getPrefix();
	public String getName();
	public String getVersion();
	
	public Set<Entry<String, MorpionGame>> getMorpionGame();
	public Collection<MorpionGame> getMorpions();
	public boolean hasMorpionId(String id);
	public MorpionGame getMorpion(String id);
	public void removeMorpion(String id);
	public void newMorpionGame(String id, Player one, Player two);
	
	public Set<Entry<Player, Player>> getInvitation();
	public void addInvitePlayer(Player sender, Player player);
	public void removeInvite(Player player);
	public boolean hasPlayer(Player player);
	public Player getPlayerValues(Player player);
}
