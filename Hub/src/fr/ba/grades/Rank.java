package fr.ba.grades;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;

public enum Rank {
	
	JOUEUR(1,"§7",ChatColor.GRAY),
	MINIVIP(11,"§rMini-VIP",ChatColor.WHITE),
	VIP(12,"§aVIP",ChatColor.WHITE),
	MVP(13,"§bMVP",ChatColor.WHITE),
	AMI(14,ChatColor.BLUE+"Ami",ChatColor.WHITE),
	GRAPH(21,"§dGraphiste",ChatColor.WHITE),
	DEV(22,"§3Développeur",ChatColor.WHITE),
	GUIDE(31,"§eGuide",ChatColor.WHITE),
	MODO(32,"§9Modérateur",ChatColor.WHITE),
	ADMIN(33,"§aAdmin",ChatColor.WHITE),
	FONDATEUR(34,"§cFondateur",ChatColor.WHITE);

	
	private int power;
	private String displayname;
	private ChatColor colortag;
	public static Map<Integer, Rank> grade = new HashMap<>();
	
	
	
	Rank(int power, String displayname, ChatColor tag){
		this.power = power;
		this.displayname = displayname;
		this.colortag = tag;
	}
	
	
	static {
		for(Rank r : Rank.values()) {
			grade.put(r.getPower(), r);
		}
	}
	
	public int getPower() {
		return power;
	}
	
	public String getName() {
		return displayname;
		
	}
	
	public ChatColor getTag() {
		return colortag;
		
	}

	public static Rank powerToRank(int power) {
		return  grade.get(power);
	}
	
}
