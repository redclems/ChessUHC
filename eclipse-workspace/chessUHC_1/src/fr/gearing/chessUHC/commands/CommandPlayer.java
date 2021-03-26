package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandPlayer implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		
		if(arg.length == 0) {
			sender.sendMessage("§2§l=========================================");
			sender.sendMessage("§ail y a §e" + ChessUHC.listPlayer.nbPlayer() + " §ajoueur");
			sender.sendMessage("§ala liste :");
			sender.sendMessage(" ");
			sender.sendMessage("§e" + ChessUHC.listPlayer.toString());
			sender.sendMessage("§2§l=========================================");
			
		}else if(arg[0].equals("add")) {
			if(arg.length == 2) {
				Player p = Bukkit.getPlayer(arg[1]);
				if(p == null) {
					sender.sendMessage("§4le joueur " + arg[1] + " n'a pas etait trouver");
				}else {
					addPlayer(sender, p);
				}
			}else if(arg.length == 1) {
				addPlayer(sender, (Player) sender);
			}else {
				sender.sendMessage("§4la commande est /uhcplayerlist add|pop (pseudo)");
			}
		}else if(arg[0].equals("pop")) {
			if(arg.length == 2) {
				Player p = Bukkit.getPlayer(arg[1]);
				if(p == null) {
					sender.sendMessage("§4le joueur " + arg[1] + " n'a pas etait trouver");
				}else {
					popPlayer(sender, p);
				}
			}else if(arg.length == 1){
				popPlayer(sender, (Player) sender);
			}else {
				sender.sendMessage("§4la commande est /uhcplayerlist add|pop (pseudo)");
			}
		}else {
			sender.sendMessage("§4la commande est /uhcplayerlist add|pop (pseudo)");
		}
		
		return false;
	}
	
	private void popPlayer(CommandSender sender, Player p) {
		if(ChessUHC.specPlayer.inList(p.getPlayer())) {
			if(ChessUHC.listPlayer.inList(p.getPlayer())) {
				ChessUHC.listPlayer.popPlayer(p, false, false);
				p.setGameMode(GameMode.SPECTATOR);
			}
			ChessUHC.specPlayer.addPlayer(p, false, false);
			sender.sendMessage("§ele joueur " + p.getPlayer().getName() + " a bien quitter la partie" );
			p.sendMessage("Vous etes en spectateur");
		}else {
			sender.sendMessage("§4le joueur " + p.getPlayer().getName() + " est pas dans la partie ");
		}	
	}
	private void addPlayer(CommandSender sender, Player p) {
		if(!ChessUHC.listPlayer.inList(p.getPlayer())) {
			if(ChessUHC.specPlayer.inList(p.getPlayer())) {
				ChessUHC.specPlayer.popPlayer(p, false, false);
				p.setGameMode(GameMode.SPECTATOR);
			}
			ChessUHC.listPlayer.addPlayer(p, false, false);
			sender.sendMessage("§ele joueur " + p.getPlayer().getName() + " a bien rejoint la partie " );
			p.sendMessage("Vous etes maintenant dans la partie");
		}else {
			sender.sendMessage("§4le joueur " + p.getPlayer().getName() + " est dejas dans la partie ");
		}	
	}
}
