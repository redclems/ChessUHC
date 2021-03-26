package fr.gearing.chessUHC.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandStop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		
		Bukkit.broadcastMessage("§4la game s'arrete");
		World world = Bukkit.getWorld("world");//world non de la map
		world.setGameRuleValue("doDaylightCycle", "false");
		ChessUHC.stop = true;

		
		return false;
	}

}
