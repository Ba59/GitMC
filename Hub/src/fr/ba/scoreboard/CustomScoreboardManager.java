package fr.ba.scoreboard;

import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.ba.bdd.SqlConnection;
import fr.ba.grades.Rank;
import fr.ba.hub.hub;

public class CustomScoreboardManager implements ScoreboardManager{

	private Scoreboard scoreboard;
	public Objective objective;
	public String name = "test.scoreboard";
	public Player player;
	
	public CustomScoreboardManager(Player p) {
		this.player = p;
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		if(hub.getInstance().sb.containsKey(p)) { return;	}
		
		this.name = "sb."+ new Random().nextInt(99999);
		this.objective = scoreboard.registerNewObjective(name,"dummy");
		objective.setDisplayName(ChatColor.GOLD+"§lElonia");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		hub.getInstance().sb.put(p, this);

		
		
		
	
}

	@Override
	public Scoreboard getMainScoreboard() {
		// TODO Auto-generated method stub
		return scoreboard;
	} 

	@Override
	public Scoreboard getNewScoreboard() {
		// TODO Auto-generated method stub
		return null;
	}

	public void refresh() {
		for(String ligne : scoreboard.getEntries()) {
		    if(ligne.contains("EloniaCrédits")){
                scoreboard.resetScores(ligne);
        		objective.getScore(ChatColor.GRAY+"EloniaCrédits : "+ChatColor.AQUA+SqlConnection.getBalanceCREDITS(player)).setScore(6);
		    }
		    
		    if(ligne.contains("EloniaCoins")){
                scoreboard.resetScores(ligne);
        		objective.getScore(ChatColor.GRAY+"EloniaCoins : "+ChatColor.YELLOW+SqlConnection.getBalance(player)).setScore(5);
		    }
		    
		    if(ligne.contains("Joueurs")){
                scoreboard.resetScores(ligne);
        		objective.getScore(ChatColor.GRAY+"Joueurs : "+ChatColor.LIGHT_PURPLE+Bukkit.getOnlinePlayers().size()+"/300").setScore(3);
		    }
		    if(ligne.contains("Grade")){
                scoreboard.resetScores(ligne);
        		Rank rank = SqlConnection.getRank(player);
        		if(rank.getPower() == 1) {
        		objective.getScore(ChatColor.GRAY+"Grade : §dJoueur").setScore(7);
		    }else{		objective.getScore(ChatColor.GRAY+"Grade : "+rank.getName()).setScore(7);
		    	}}
		    }
		
	}
	
	public void sendLine() {
		objective.getScore(ChatColor.GOLD+"§a ").setScore(9);
		objective.getScore(ChatColor.GRAY+"Pseudo : "+ChatColor.GREEN+player.getName()).setScore(8);
		Rank rank = SqlConnection.getRank(player);
		if(rank.getPower() == 1) {
		objective.getScore(ChatColor.GRAY+"Grade : §dJoueur").setScore(7);
    }else{		objective.getScore(ChatColor.GRAY+"Grade : "+rank.getName()).setScore(7);
    	}
		objective.getScore(ChatColor.GRAY+"EloniaCrédits : "+ChatColor.AQUA+SqlConnection.getBalanceCREDITS(player)).setScore(6);
		objective.getScore(ChatColor.GRAY+"EloniaCoins : "+ChatColor.YELLOW+SqlConnection.getBalance(player)).setScore(5);
		objective.getScore(ChatColor.GOLD+"§e ").setScore(4);
		objective.getScore(ChatColor.GRAY+"Joueurs : "+ChatColor.LIGHT_PURPLE+Bukkit.getOnlinePlayers().size()+"/300").setScore(3);
		objective.getScore(ChatColor.GRAY+"Lobby : "+ChatColor.LIGHT_PURPLE+"#1").setScore(2);
		objective.getScore(ChatColor.GOLD+"§l ").setScore(1);
		objective.getScore(ChatColor.GOLD+"      play.elonia.fr").setScore(0);
	}
	
	public void setScoreboard() {
		player.setScoreboard(scoreboard);
	}
}
