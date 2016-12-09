package fr.morpion.ns.server;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.morpion.ns.game.MorpionGame;
import fr.morpion.ns.morpion.PlayerMorpion;

public class MorpionServer implements IMorpionServer{

	private final JavaPlugin plugin;
	private final Map<Player, Player> invites = Maps.newHashMap();
	private final Map<String, MorpionGame> morpions = Maps.newHashMap();
	private final String prefix = "§4[§9§oTIC-TAC-TOC§r§4]§r ", name = "Tic-Tac-Toc", version = "V-1.0.0";
	
	public MorpionServer(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public JavaPlugin getPlugin() {
		return plugin;
	}

	@Override
	public Set<Entry<String, MorpionGame>> getMorpionGame() {
		return morpions.entrySet();
	}
	
	@Override
	public Set<Entry<Player, Player>> getInvitation() {
		return invites.entrySet();
	}
	
	@Override
	public Collection<MorpionGame> getMorpions() {
		return Lists.newArrayList(morpions.values());
	}
	
	@Override
	public boolean hasMorpionId(String id){
		return morpions.containsKey(id);
	}
	
	@Override
	public MorpionGame getMorpion(String id) {
		return morpions.get(id);
	}
	
	@Override
	public void removeMorpion(String id) {
		morpions.remove(id);
	}
	
	@Override
	public void newMorpionGame(String id, Player one, Player two) {
		morpions.put(id, new MorpionGame(id, new PlayerMorpion(one, Material.STAINED_CLAY, (byte)11), new PlayerMorpion(two, Material.STAINED_CLAY, (byte)14)));
		removeInvite(one);
	}
	
	@Override
	public void addInvitePlayer(Player sender, Player player){
		invites.put(sender, player);
	}
	
	@Override
	public void removeInvite(Player player){
		invites.remove(player);
	}
	
	@Override
	public boolean hasPlayer(Player player){
		return invites.containsKey(player);
	}
	
	@Override
	public Player getPlayerValues(Player player){
		return invites.get(player);
	}
	
	@Override
	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getVersion() {
		return version;
	}
}
