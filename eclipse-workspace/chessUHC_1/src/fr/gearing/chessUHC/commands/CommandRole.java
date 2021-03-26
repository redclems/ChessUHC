package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gearing.chessUHC.Players;
import fr.gearing.chessUHC.role.Cavalier;
import fr.gearing.chessUHC.role.Dame;
import fr.gearing.chessUHC.role.Fou;
import fr.gearing.chessUHC.role.Pion;
import fr.gearing.chessUHC.role.Rois;
import fr.gearing.chessUHC.role.Role;
import fr.gearing.chessUHC.role.Tour;

public class CommandRole implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		if(ChessUHC.Timer().getJour() > 1) {
			Player p = (Player) sender;
			Players pl = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(p));
			
			if(arg.length == 1) {
				if(arg[0].equals("item")) {
					pl.getRole().giveItem(p);
				}else {
					if(!sender.isOp()) {
						sender.sendMessage("§4la comande est: uhcrole item");
					}else {
						sender.sendMessage("§4la comande est: uhcrole item|add|pop role [personne]");
					}
				}
			}else if(arg.length == 2) {
				
				if(arg[0].equals("add")){
					if(arg[1].equals("Cavalier")) {
						pl.setRole(new Cavalier(arg[1], pl.getEquipeList(), pl));
						sender.sendMessage("§4le role a etait ajouter");
					}else if(arg[1].equals("Dame")){
						pl.setRole(new Dame(arg[1], pl.getEquipeList(), pl));
						sender.sendMessage("§4le role a etait ajouter");
					}else if(arg[1].equals("Fou")){
						pl.setRole(new Fou(arg[1], pl.getEquipeList(), pl));
						sender.sendMessage("§4le role a etait ajouter");
					}else if(arg[1].equals("Pion")){
						pl.setRole(new Pion(arg[1], pl.getEquipeList(), pl));
						sender.sendMessage("§4le role a etait ajouter");
					}else if(arg[1].equals("Rois")){
						pl.setRole(new Rois(arg[1], pl.getEquipeList(), pl));
						sender.sendMessage("§4le role a etait ajouter");
					}else if(arg[1].equals("Tour")){
						pl.setRole(new Tour(arg[1], pl.getEquipeList(), pl));
						sender.sendMessage("§4le role a etait ajouter");
					}else {
						sender.sendMessage("§4le role n'a pas etait ajouter");
					}
					
				}else if(arg[0].equals("pop")){
					pl.setRole(null);
					sender.sendMessage("§4le role a etait suprimer");
				}else {
					sender.sendMessage("§4la comande est: uhcrole add|pop role [personne]");
				}
			}else if(arg.length == 3) {
				p = Bukkit.getPlayer(arg[2]);
				Players pl1 = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(p));
				if(arg[0].equals("add")){
					if(arg[1].equals("Cavalier")) {
						pl1.setRole(new Cavalier(arg[1], pl.getEquipeList(), pl));
					}else if(arg[1].equals("Dame")){
						pl1.setRole(new Dame(arg[1], pl.getEquipeList(), pl));
					}else if(arg[1].equals("Fou")){
						pl1.setRole(new Fou(arg[1], pl.getEquipeList(), pl));
					}else if(arg[1].equals("Pion")){
						pl1.setRole(new Pion(arg[1], pl.getEquipeList(), pl));
					}else if(arg[1].equals("Rois")){
						pl1.setRole(new Rois(arg[1], pl.getEquipeList(), pl));
					}else if(arg[1].equals("Tour")){
						pl1.setRole(new Tour(arg[1], pl.getEquipeList(), pl));
					}
					sender.sendMessage("§4le role a etait ajouter");
				}else if(arg[0].equals("pop")){
					pl1.setRole(null);
					sender.sendMessage("§4le role a etait suprimer");
				}else {
					sender.sendMessage("§4la comande est: uhcrole add|pop role [personne]");
				}
			}else {
				if(ChessUHC.listPlayer.inList((Player) sender)) {
					int indice = ChessUHC.listPlayer.indicePlayer((Player)sender);
					Role role = ChessUHC.listPlayer.getPlayer(indice).getRole();
					Bukkit.broadcastMessage("§2§l=========================================");
					Bukkit.broadcastMessage("§4"+ "vous etes : §2" + role.getNom());
					Bukkit.broadcastMessage("§4"+ role.roleInfo());
					Bukkit.broadcastMessage("§2§l=========================================");
				}else {
					sender.sendMessage("§4Vous etes pas dans la liste des joueurs");
				}
			}
		}else {
			sender.sendMessage("§4Vous n'avez pas de role");
		}
		return false;
	}
 }

