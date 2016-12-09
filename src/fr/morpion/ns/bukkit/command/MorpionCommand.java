package fr.morpion.ns.bukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.morpion.ns.Morpion;

public final class MorpionCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if(!(s instanceof Player)) return true;
		
		if(a.length < 1) return helpCommand(s);
		if(a[0].equalsIgnoreCase("send") || a[0].equalsIgnoreCase("-s")) return send(s, a);
		if(a[0].equalsIgnoreCase("accept") || a[0].equalsIgnoreCase("-a")) return accept(s, a);
		if(a[0].equalsIgnoreCase("decline") || a[0].equalsIgnoreCase("-d")) return decline(s, a);
		if(a[0].equalsIgnoreCase("remove") || a[0].equalsIgnoreCase("-r")) return remove(s);
		return helpCommand(s);
	}
	
	private boolean helpCommand(CommandSender s){
		s.sendMessage("§f§l§m=========§r§4[§8§lHELP §9§oTIC-TAC-TOC§r§4]§f§l§m=========");
		s.sendMessage("§6§l§nListe des commandes : ");
		s.sendMessage(" ");
		s.sendMessage("             §8§l>> §r§6/tictactoc");
		s.sendMessage("             §8§l>> §r§6/ttt");
		s.sendMessage("             §8§l>> §r§6/morpion");
		s.sendMessage("§f§l§m-------------------------------");
		s.sendMessage("§6/ttt §asend §5<Player>");
		s.sendMessage("§6/ttt §aaccept §5<Player>");
		s.sendMessage("§6/ttt §adecline §5<Player>");
		s.sendMessage("§6/ttt §aremove");
		s.sendMessage("§f§l§o=========§r§4[§8§lHELP §9§oTIC-TAC-TOC§r§4]§f§l§o=========");
		return true;
	}
	
	private boolean send(CommandSender s, String[] a){
		if(a.length < 2) return helpCommand(s);
		if(Bukkit.getPlayerExact(a[1]) == null) return notPlayer(s);
		if(Bukkit.getPlayerExact(a[1]).equals((Player)s)) return soloSend(s);
		if(Morpion.getServer().hasPlayer((Player)s)){
			if(Bukkit.getPlayerExact(a[1]).equals(Morpion.getServer().getPlayerValues((Player)s))){
				s.sendMessage(Morpion.getPrefix()+"§4Vous avez déjà invité ce joueur.");
				return true;
			}
			removeInvite(Bukkit.getPlayerExact(a[1]));
		}
		Morpion.getServer().addInvitePlayer((Player)s, Bukkit.getPlayerExact(a[1]));
		s.sendMessage(Morpion.getPrefix()+"§2§lL'invitation a bien été envoyé.");
		Bukkit.getPlayerExact(a[1]).sendMessage(Morpion.getPrefix()+"§5Vous avez reçu une invitation pour jouer au morpion. Faites §6/ttt help §5pour plus d'infos.");
		return true;
	}
	
	private boolean soloSend(CommandSender s){
		s.sendMessage(Morpion.getPrefix()+"§4Vous ne pouvez pas vous envoyer d'invitation.");
		return true;
	}
	
	private boolean notPlayer(CommandSender s){
		s.sendMessage(Morpion.getPrefix()+"§4§lLe joueur n'a pas été trouvé.");
		return true;
	}
	
	private boolean removeInvite(Player player){
		player.sendMessage(Morpion.getPrefix()+"§3§lVotre invitation à été supprimé.");
		return true;
	}
	
	private boolean dontInvite(CommandSender s){
		s.sendMessage(Morpion.getPrefix()+"§4§lVous n'avez pas d'invitation.");
		return true;
	}
	
	private boolean decline(CommandSender s, String[] a){
		boolean r = isRefused(s, a);
		if(r) return true;
		Morpion.getServer().removeInvite(Bukkit.getPlayerExact(a[1]));
		return declineInvite(s);
	}
	
	private boolean declineInvite(CommandSender s){
		s.sendMessage(Morpion.getPrefix()+"§2L'invitation a été annulé.");
		return true;
	}
	
	private boolean remove(CommandSender s){
		if(!Morpion.getServer().hasPlayer((Player)s)) return dontInvite(s);
		removeInvite(Morpion.getServer().getPlayerValues((Player)s));
		Morpion.getServer().removeInvite((Player)s);
		return declineInvite(s);
	}
	
	private boolean accept(CommandSender s, String[] a){
		boolean r = isRefused(s, a);
		if(r) return true;
		boolean start = false;
		String title = "";
		
		do{
			title = "§8Morpion §4[§9#"+Morpion.getRandom(20000)+"§4]";
			start = !Morpion.getServer().hasMorpionId(title);
		}while(!start);
		
		Morpion.getServer().newMorpionGame(title, Bukkit.getPlayerExact(a[1]), (Player)s);
		
		return true;
	}
	
	private boolean isRefused(CommandSender s, String[] a){
		if(a.length < 2) return helpCommand(s);
		if(Bukkit.getPlayerExact(a[1]) == null) return notPlayer(s);
		if(!Morpion.getServer().hasPlayer(Bukkit.getPlayerExact(a[1]))
				|| !Morpion.getServer().getPlayerValues(Bukkit.getPlayerExact(a[1])).equals((Player)s)){
			return dontInvite(s);
		}
		removeInvite(Bukkit.getPlayerExact(a[1]));
		return false;
	}
}
