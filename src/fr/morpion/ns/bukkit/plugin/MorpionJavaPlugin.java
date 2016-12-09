package fr.morpion.ns.bukkit.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.morpion.ns.Morpion;
import fr.morpion.ns.server.MorpionServer;

public class MorpionJavaPlugin extends JavaPlugin{

	@Override
	public void onEnable() {
		Morpion.setServer(new MorpionServer(this));
	}
}
