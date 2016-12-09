package fr.morpion.ns;

import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.morpion.ns.bukkit.command.MorpionCommand;
import fr.morpion.ns.bukkit.listener.InventoryClickListener;
import fr.morpion.ns.bukkit.listener.InventoryCloseListener;
import fr.morpion.ns.bukkit.listener.PlayerQuitListener;
import fr.morpion.ns.game.MorpionGame;
import fr.morpion.ns.server.IMorpionServer;

public class Morpion {

	private static IMorpionServer server;
	private final static Random random = new Random();
	
	public static void setServer(IMorpionServer server){
		if(Morpion.server != null) throw new UnsupportedOperationException("Impossible de redefinir la class MorpionServer.");
		Morpion.server = server;
	
		registerCommand(server.getPlugin(), new MorpionCommand(), "tictactoc", "ttt", "morpion");
		
		registerEvents(server.getPlugin(), new InventoryClickListener(), new InventoryCloseListener()
										 , new PlayerQuitListener());
	}
	
	public static IMorpionServer getServer() {
		return server;
	}
	
	private static void registerCommand(JavaPlugin plugin, CommandExecutor command, String... labels){
		for(String label : labels) plugin.getCommand(label).setExecutor(command);
	}
	
	private static void registerEvents(JavaPlugin plugin, Listener... listeners){
		for(Listener listener : listeners) Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	public static String getPrefix(){
		return server.getPrefix();
	}
	
	public static void forfaitGame(Player player){
		for(Entry<String, MorpionGame> game : Morpion.getServer().getMorpionGame()){
			if(game.getValue().getPlayerOne().getPlayer().equals(player) || game.getValue().getPlayerTwo().getPlayer().equals(player)){
				Player target = game.getValue().getPlayerOne().getPlayer().equals(player) ? game.getValue().getPlayerTwo().getPlayer() : game.getValue().getPlayerOne().getPlayer();
				Bukkit.broadcastMessage(Morpion.getPrefix()+"§6"+target.getName()+" §3a remporté une partie de morpion par forfait.");
				Morpion.getServer().removeMorpion(game.getKey());
				target.closeInventory();
				continue;
			}
		}
	}
	
	public static int getRandom(int index){
		return random.nextInt(index);
	}
}
