package fr.ba.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.ba.bdd.SqlConnection;
import fr.ba.grades.Rank;

public class EventsJoueur implements Listener {

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onAchieve(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onTalk(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String m = e.getMessage();
		Rank rank = SqlConnection.getRank(p);
		if(rank.getPower() == 1) {
			e.setFormat(rank.getName()+""+p.getName()+ "  §l» §r"+rank.getTag()+m);
		}else {
		e.setFormat(rank.getName()+" "+p.getName()+ "  §l» §r"+rank.getTag()+m);
	}}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.setCancelled(true);
	}
	

}
