package fr.ba.hub;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

public class Title {

	public static void sendTitle(Player p, String title, String subtitle, int ticks) {
		
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent chatsubTitle = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
		
		PacketPlayOutTitle titre = new PacketPlayOutTitle(EnumTitleAction.TITLE,chatTitle);
		PacketPlayOutTitle soustitre = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,chatsubTitle);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(titre);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(soustitre);
		sendTime(p, ticks);
		
	}
	
	private static void sendTime(Player p, int ticks) {
		
		PacketPlayOutTitle pa = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(pa);
	}
	
	public static void sendABar(Player p, String message) {
		IChatBaseComponent msg = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat ab = new PacketPlayOutChat(msg,(byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(ab);
	}
	
}



