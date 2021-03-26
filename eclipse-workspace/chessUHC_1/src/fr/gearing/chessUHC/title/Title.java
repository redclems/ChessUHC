package fr.gearing.chessUHC.title;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Title {
	
	public static void sendTitle(Player player, String title, String subTitle, int ticks){
		

		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent chatsubTitle = ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
		
		PacketPlayOutTitle titre = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle soustitre = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatsubTitle);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(titre);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(soustitre);
		
		sendTime(player, ticks);
		
		
	}
	
	private static void sendTime(Player player, int ticks){
		
		PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
		
	}
	
	public static void sendActionBar(Player player, String message){
		IChatBaseComponent actionBar = ChatSerializer.a("{\": \"" + message + "\"}");
		PacketPlayOutChat ab = new PacketPlayOutChat(actionBar, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(ab);
		
	}
}
