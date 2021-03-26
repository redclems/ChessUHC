package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandInfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		if(arg.length == 0) {
			sender.sendMessage("§4the comande is : /info <message>");
		}else if(arg.length >= 1){
			StringBuilder bc = new StringBuilder();
			for(String part : arg) {
				bc.append(part + " ");
			}
			Bukkit.broadcastMessage("§2§l=========================================");
			Bukkit.broadcastMessage("§4"+ bc);
			Bukkit.broadcastMessage("§2§l=========================================");
		}
		
		return false;
	}

}
