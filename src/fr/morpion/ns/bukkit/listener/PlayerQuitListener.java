package fr.morpion.ns.bukkit.listener;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.morpion.ns.Morpion;

public final class PlayerQuitListener implements Listener {

	@EventHandler
	private void playerQuit(PlayerQuitEvent pqe){
		boolean b = true;
		
		do{
			
			if(Morpion.getServer().getMorpions().isEmpty()) b = false;
			for(Entry<Player, Player> invitation : Morpion.getServer().getInvitation()){
				b = messageQuit(invitation.getKey().equals(pqe.getPlayer()) ? invitation.getValue() : invitation.getValue().equals(pqe.getPlayer()) ? invitation.getKey() : null);
				if(b){
					Morpion.getServer().removeInvite(invitation.getKey());
					continue;
				}
			}
		}while(b);
		
		Morpion.forfaitGame(pqe.getPlayer());
	}
	
	private boolean messageQuit(Player player){
		if(player != null) player.sendMessage(Morpion.getPrefix()+"§4L'invitation a été supprimé du à la déconnexion du joueur.");
		return player != null;
	}
}
