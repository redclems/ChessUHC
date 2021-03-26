package fr.gearing.chessUHC.title;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.gearing.chessUHC.commands.ChessUHC;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;


public class TitleDirection {
	public static String position(Player p) {
		Location locP = p.getLocation();
		char lettre = ' ';
		char numero = ' ';
		int x = locP.getBlockX();
		int z = locP.getBlockZ();
		if(x<ChessUHC.x+ChessUHC.tailleCase*4 && x >= ChessUHC.x+ChessUHC.tailleCase*3) lettre = 'a';
		else if(x<ChessUHC.x+ChessUHC.tailleCase*3 && x >= ChessUHC.x+ChessUHC.tailleCase*2) lettre = 'b';
		else if(x<ChessUHC.x+ChessUHC.tailleCase*2 && x >= ChessUHC.x+ChessUHC.tailleCase*1) lettre = 'c';
		else if(x<ChessUHC.x+ChessUHC.tailleCase*1 && x >= ChessUHC.x-ChessUHC.tailleCase*0) lettre = 'd';
		else if(x<ChessUHC.x-ChessUHC.tailleCase*0 && x >= ChessUHC.x-ChessUHC.tailleCase*1) lettre = 'e';
		else if(x<ChessUHC.x-ChessUHC.tailleCase*1 && x >= ChessUHC.x-ChessUHC.tailleCase*2) lettre = 'f';
		else if(x<ChessUHC.x-ChessUHC.tailleCase*2 && x >= ChessUHC.x-ChessUHC.tailleCase*3) lettre = 'g';
		else if(x<ChessUHC.x-ChessUHC.tailleCase*3 && x >= ChessUHC.x-ChessUHC.tailleCase*4) lettre = 'h';

		
		if(z<ChessUHC.z+ChessUHC.tailleCase*4 && z >= ChessUHC.z+ChessUHC.tailleCase*3) numero = '1';
		else if(z<ChessUHC.z+ChessUHC.tailleCase*3 && z >= ChessUHC.z+ChessUHC.tailleCase*2) numero = '2';
		else if(z<ChessUHC.z+ChessUHC.tailleCase*2 && z >= ChessUHC.z+ChessUHC.tailleCase*1) numero = '3';
		else if(z<ChessUHC.z+ChessUHC.tailleCase*1 && z >= ChessUHC.z-ChessUHC.tailleCase*0) numero = '4';
		else if(z<ChessUHC.z-ChessUHC.tailleCase*0 && z >= ChessUHC.z-ChessUHC.tailleCase*1) numero = '5';
		else if(z<ChessUHC.z-ChessUHC.tailleCase*1 && z >= ChessUHC.z-ChessUHC.tailleCase*2) numero = '6';
		else if(z<ChessUHC.z-ChessUHC.tailleCase*2 && z >= ChessUHC.z-ChessUHC.tailleCase*3) numero = '7';
		else if(z<ChessUHC.z-ChessUHC.tailleCase*3 && z >= ChessUHC.z-ChessUHC.tailleCase*4) numero = '8';
		
		if(lettre != ' ' && numero != ' ')return ""+lettre + numero;
		return "";
	}
	public static void directionDisplay(Player p) {
		p.setCompassTarget(new Location(ChessUHC.world, ChessUHC.x, ChessUHC.y, ChessUHC.z));

		String position = position(p);
		if(position != "")sendMessage(p, "§2position : §e" + position);
		else sendMessage(p, "§2position : §een dehors");
	}
	public static void sendMessage(Player p, String message) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message.replace("&", "§") + "\"}"), (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
		  
	public static void broadcastMessage(String message) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message.replace("&", "§") + "\"}"), (byte) 2);
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}
}