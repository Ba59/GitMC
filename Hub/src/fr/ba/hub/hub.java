package fr.ba.hub;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.ba.bdd.CmdCoins;
import fr.ba.bdd.CmdCredits;
import fr.ba.bdd.SqlConnection;
import fr.ba.scoreboard.CustomScoreboardManager;
import fr.ba.scoreboard.ScoreboardRunnable;

public class hub extends JavaPlugin {
	
	public SqlConnection sql;
	public HashMap<Player, CustomScoreboardManager> sb = new HashMap<>();
	
	String nom = new String("");
	
	public static hub instance;
	public static hub getInstance() {
		return instance;
	}
	
	public void onEnable() {
		getCommand("coins").setExecutor(new CmdCoins(sql));
		getCommand("credits").setExecutor(new CmdCredits(sql));
		instance = this;
		new ScoreboardRunnable().runTaskTimer(this, 0L, 20L);
		new fr.ba.events.EventsManager (this).registerEvents();
		sql = new SqlConnection("jdbc:mysql://","localhost","elonia","root","");
		sql.connection();
	
	}
	
	
	public void onDisable() {
			sql.disconnect();
	}

	
}
