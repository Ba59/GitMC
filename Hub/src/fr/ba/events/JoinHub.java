package fr.ba.events;



import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import fr.ba.hub.Title;
import fr.ba.hub.hub;
import fr.ba.scoreboard.CustomScoreboardManager;


public class JoinHub implements Listener {

	World world = Bukkit.getServer().getWorld("world");
	Location hub1 = new Location(world, 4, 65, -4);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		//GADGET
		
		p.teleport(hub1);
		e.setJoinMessage(ChatColor.GRAY+"["+ChatColor.GREEN+"+"+ChatColor.GRAY+"] "+p.getName());
		Title.sendTitle(p, ChatColor.GREEN+"§l* ELONIA *", ChatColor.GOLD+"Bienvenue " + p.getName(), 40);
		Title.sendABar(p, ChatColor.GREEN+"§l» §r"+ChatColor.GOLD+"Amuse toi bien "+p.getName()+ChatColor.GREEN+"§l «");
		p.setGameMode(GameMode.ADVENTURE);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
		
		//SCOREBOARD
		
		CustomScoreboardManager board = new CustomScoreboardManager(p);
		board.sendLine();
		board.setScoreboard();
		
		
		//SQL
		
		hub.getInstance().sql.createAccount(p);
	}
}
