package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandGroupeTaille implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		if(arg.length == 0) {
			sender.sendMessage("§4the comande is : /info <nb>");
		}else if(arg.length >= 1){
			ChessUHC.groupeTaille = Integer.parseInt(arg[0]);
			Bukkit.broadcastMessage("§4§lLes groupe de joueur sont limité a " + arg[0]);

		}
		
		return false;
	}

}
