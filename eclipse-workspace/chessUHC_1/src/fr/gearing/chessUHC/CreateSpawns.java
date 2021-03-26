package fr.gearing.chessUHC;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import fr.gearing.chessUHC.commands.ChessUHC;



public class CreateSpawns {
	
	public static void spawn(){
		World world = Bukkit.getWorld("world");
		world.setSpawnLocation((int) ChessUHC.x, (int) ChessUHC.y, (int) ChessUHC.z);
		WorldBorder wb = world.getWorldBorder();
		wb.setCenter(ChessUHC.x, ChessUHC.z);
		wb.setSize(2400);
	}

}
