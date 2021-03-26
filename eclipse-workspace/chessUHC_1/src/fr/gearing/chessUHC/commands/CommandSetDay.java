package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.gearing.chessUHC.ChessGame;
import fr.gearing.chessUHC.Timer;


public class CommandSetDay implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		
		if(ChessUHC.current == ChessGame.GAME) {
			if(arg.length > 0) {
				Timer time = ChessUHC.Timer();
				time.setJour(Integer.parseInt(arg[0]));
				Bukkit.broadcastMessage("§2Nous somme maintenant le jour " + arg[0]);
				if(arg.length > 1) {
					time.setMinute(Integer.parseInt(arg[1]));
					ChessUHC.world.setTime(1200*Integer.parseInt(arg[1]));
				}else {
					time.setMinute(0);
					ChessUHC.world.setTime(0);
				}
				time.setSeconde(0);
			}else {
				sender.sendMessage("§4"+ChessUHC.Timer().getJour()+"J time : " +ChessUHC.Timer().getMinute()+":"+ChessUHC.Timer().getSeconde());
				sender.sendMessage("§4 la commande est: uhcsetday numero du jour, minute");
			}
		}else {
			sender.sendMessage("Vous n'etes pas en mode game");
		}
		return false;
	}

}
