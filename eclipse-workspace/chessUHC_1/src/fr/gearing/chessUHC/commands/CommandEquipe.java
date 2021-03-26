package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gearing.chessUHC.Players;

public class CommandEquipe implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		
		if(arg.length == 0) {
			equipeNoir(sender);
			equipeBlanc(sender);
		}else if(arg.length == 1) {
			if(arg[0].equals("noire"))equipeNoir(sender);
			if(arg[0].equals("blanc"))equipeBlanc(sender);

		}else if(arg.length >= 2) {
			if(arg[0].equals("noire") || arg[0].equals("blanc")) {
				if(arg[1].equals("add")){
					if(arg.length == 3) {
						Players p = new Players(Bukkit.getPlayer(arg[2]));
						if(p.getPlayer() == null) {
							sender.sendMessage("§4le joueur " + arg[2] + " n'a pas etait trouver");
						}else {
							addPlayer(sender, p, arg[0]);
						}
					}else if(arg.length == 2) {
						Player pl= (Player) sender;
						Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(pl));
						addPlayer(sender, p, arg[0]);

					}else {
						sender.sendMessage("§4la commande est /uhcteams (noire|blanc) add|pop (pseudo)");
					}
				}else if(arg[1].equals("pop")){

					if(arg.length == 3) {
						Players p = new Players(Bukkit.getPlayer(arg[2]));
						if(p.getPlayer() == null) {
							sender.sendMessage("§4le joueur " + arg[2] + " n'a pas etait trouver");
						}else {
							popPlayer(sender, p, arg[0]);
						}
					}else if(arg.length == 2) {
						Player pl= (Player) sender;
						Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(pl));
						popPlayer(sender, p, arg[0]);

					}else {
						sender.sendMessage("§4la commande est /uhcteams (noire|blanc) add|pop (pseudo)");
					}
				}else {
					sender.sendMessage("§4l'option " + arg[1] + "n'existe pas");
				}
			}else {
				sender.sendMessage("§4l'equipe " + arg[0] + "n'existe pas");
			}
		}else {
			sender.sendMessage("§4la commande est /uhcteams (noire|blanc) add|pop (pseudo)");
		}
		
		return false;
	}
	
	private void popPlayer(CommandSender sender, Players p, String equipe) {
		if(equipe.equals("noire")) {
			if(ChessUHC.EquipeNoir.inList(ChessUHC.listPlayer.indicePlayer(p))) {
				ChessUHC.EquipeNoir.popPlayer(ChessUHC.listPlayer.indicePlayer(p));
				p.rename(p.getPlayer().getName());
				sender.sendMessage("§ele joueur " + p.getPlayer().getName() + " a bien etait suprimer de l'equipe " + equipe);
			}else {
				sender.sendMessage("§4le joueur " + p.getPlayer().getName() + " n'ait pas dans l'equipe " + equipe);
			}
			
		}else if(equipe.equals("blanc")) {
			if(ChessUHC.EquipeBlanc.inList(ChessUHC.listPlayer.indicePlayer(p))) {
				ChessUHC.EquipeBlanc.popPlayer(ChessUHC.listPlayer.indicePlayer(p));
				p.rename(p.getPlayer().getName());
				sender.sendMessage("§ele joueur " + p.getPlayer().getName() + " a bien etait suprimer de l'equipe " + equipe);
			}else {
				sender.sendMessage("§4le joueur " + p.getPlayer().getName() + " n'ait pas dans l'equipe " + equipe);
			}
		}
	}
	private void addPlayer(CommandSender sender, Players p, String equipe) {
		if(equipe.equals("noire")) {
			if(!ChessUHC.EquipeNoir.inList(ChessUHC.listPlayer.indicePlayer(p))) {
				if(ChessUHC.EquipeBlanc.inList(ChessUHC.listPlayer.indicePlayer(p))) {
					popPlayer(sender, p, "blanc");
				}
				ChessUHC.EquipeNoir.addPlayer(ChessUHC.listPlayer.indicePlayer(p));
				p.rename(ChessUHC.EquipeNoir.getCouleur()+p.getPlayer().getName()+"§f");
				sender.sendMessage("§ele joueur " + p.getPlayer().getName() + " a bien etait ajouter a l'equipe " + equipe);
			}else {
				sender.sendMessage("§4le joueur " + p.getPlayer().getName() + " ait dejas dans l'equipe " + equipe);
			}
			
		}else if(equipe.equals("blanc")) {
			if(!ChessUHC.EquipeBlanc.inList(ChessUHC.listPlayer.indicePlayer(p))) {
				if(ChessUHC.EquipeNoir.inList(ChessUHC.listPlayer.indicePlayer(p))) {
					popPlayer(sender, p, "noire");
				}
				ChessUHC.EquipeBlanc.addPlayer(ChessUHC.listPlayer.indicePlayer(p));
				p.rename(ChessUHC.EquipeBlanc.getCouleur()+p.getPlayer().getName()+"§f");
				sender.sendMessage("§ele joueur " + p.getPlayer().getName() + " a bien etait ajouter a l'equipe " + equipe);
			}else {
				sender.sendMessage("§4le joueur " + p.getPlayer().getName() + " ait dejas dans l'equipe " + equipe);
			}
		}
	}
	
	private void equipeNoir(CommandSender sender) {
		if(ChessUHC.EquipeNoir != null) {
			sender.sendMessage("§2§l=========================================");
			sender.sendMessage("§ail y a §e" + ChessUHC.EquipeNoir.nbPlayer() + " §ajoueur" + "dans l'equipe noire");
			sender.sendMessage("§ala liste :");
			sender.sendMessage(" ");
			sender.sendMessage("§e" + ChessUHC.EquipeNoir.toString());
			sender.sendMessage("§2§l=========================================");
		}else {
			sender.sendMessage("§ala liste des Noire est vide");
		}
	}
	
	private void equipeBlanc(CommandSender sender) {
		if(ChessUHC.EquipeBlanc != null) {
			sender.sendMessage("§2§l=========================================");
			sender.sendMessage("§ail y a §e" + ChessUHC.EquipeBlanc.nbPlayer() + " §ajoueur" + "dans l'equipe blanc");
			sender.sendMessage("§ala liste :");
			sender.sendMessage(" ");
			sender.sendMessage("§e" + ChessUHC.EquipeBlanc.toString());
			sender.sendMessage("§2§l=========================================");
		}else {
			sender.sendMessage("§ala liste des Blanc est vide");
		}
	}

}
