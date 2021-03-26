package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.gearing.chessUHC.ChessGame;


public class CommandStart implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		
		if(ChessUHC.current == ChessGame.LOBBY) {
			if(ChessUHC.listPlayer.nbPlayer() < 20) {
				sender.sendMessage("§4pas asser de joueur, il manque §e" + (20 - ChessUHC.listPlayer.nbPlayer()) + "§4 joueur");
			}//else{
				Bukkit.broadcastMessage("§athe Game starting in 10 second");
				ChessUHC.current = ChessGame.PREGAME;
			//}
		}else {
			ChessUHC.stop = false;
			World world = Bukkit.getWorld("world");//world non de la map
			world.setGameRuleValue("doDaylightCycle", "true");
			Bukkit.broadcastMessage("§4la game redemare");
		}
		return false;
	}

}
