package fr.ba.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.ba.hub.hub;

public class EventsManager {

	public hub pl;
	
	
	
	public EventsManager(hub hub) {
		this.pl = hub;
	}

	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinHub(), pl);
		pm.registerEvents(new EventsJoueur(), pl);
		
	}

}
