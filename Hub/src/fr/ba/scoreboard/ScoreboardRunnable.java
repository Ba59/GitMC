package fr.ba.scoreboard;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.ba.hub.hub;

public class ScoreboardRunnable extends BukkitRunnable{

	@Override
	public void run() {
		for(Entry<Player, CustomScoreboardManager> scoreboard : hub.getInstance().sb.entrySet()) {
			CustomScoreboardManager board = scoreboard.getValue();
			board.refresh();
		}
		
	}
	
	

}
