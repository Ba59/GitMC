package fr.ba.grades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Tablist extends JavaPlugin implements Listener{

	private static Tablist instance;
	
	

	public void onEnable() {
		instance = this;
		onTab();
	}
	

	public void onDisable() {

	}
	
	public static Tablist getInstance() {
		return instance;
	}
	
	List<Player> joueur = new ArrayList<>();
	List<Player> pote = new ArrayList<>();
	
	
	public void onTab() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Tablist.getInstance(), new Runnable() {
			
			Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
			Team team = null;
			
			public void run(){
				for(Player pls : Bukkit.getOnlinePlayers()){
				Player p = pls.getPlayer();
				if(p.isOp()) {
					p.setPlayerListName(ChatColor.RED+ "[Administrateur] : "+p.getName());
					
					
				}else if(p.hasPermission("gen.modo")) {
					if(board.getTeam(p.getName()) == null) {
						team = board.registerNewTeam(p.getName());
					}else {
						team = board.getTeam(p.getName());
						team.addPlayer(p);
						
						}
					p.setPlayerListName(ChatColor.GREEN+"[Modérateur] : " + p.getName());
					
				}else if(p.hasPermission("gen.pote")) {
					if(board.getTeam(p.getName()) == null) {
						team = board.registerNewTeam(p.getName());
					}else {
						team = board.getTeam(p.getName());
						team.addPlayer(p);
						
						}
					p.setPlayerListName(ChatColor.GOLD+"[Pote] : " + p.getName());
					
				
					
					
				}else {
						if(board.getTeam(p.getName()) == null) {
							team = board.registerNewTeam(p.getName());
						}else {
							team = board.getTeam(p.getName());
							team.addPlayer(p);
							
						}
						p.setPlayerListName(ChatColor.BLUE + "[Joueur] : " +p.getName());
					}
			}}
		}, 0, 3);

	}}
	

