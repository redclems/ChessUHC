package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBorder implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		
		if(arg.length == 0) {
			sender.sendMessage("la bordure bouge :" + ChessUHC.borderMouve);
		}else if(arg[0].equals("start")){
			Bukkit.broadcastMessage("§2La bordure se deplace, ATTENTION !!!");
			ChessUHC.borderMouve = true;
		}else if(arg[0].equals("stop")){
			if(ChessUHC.borderMouve) {
				sender.sendMessage("§2la bordure s'arrete");
				ChessUHC.borderMouve = false;
			}else {
				sender.sendMessage("§2la bordure n'est pas en mouvement");
			}
		}else {
			sender.sendMessage("§2La commande est uhcborder start|stop");
		}
		
		return false;
	}

}
