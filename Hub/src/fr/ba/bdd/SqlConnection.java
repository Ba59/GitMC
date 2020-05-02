package fr.ba.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.ba.grades.Rank;

public class SqlConnection {
	
	private static Connection connection;
	private String urlbase,host,database,user,pass;

	
	public SqlConnection( String urlbase, String host, String database, String user, String pass) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
	}

	public void connection(){
		if(!isConnected()){
			try {
				connection = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
				System.out.println("connected ok");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect(){
		if(isConnected()){
			try {
				connection.close();
				System.out.println("connected off");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * INSERT
	 * UPDATE
	 * DELETE
	 * SELECT
	 * 
	 * PREPARER ?,?
	 * REMPLACER LES ? PAR DES VALEURS
	 * EXECUTE
	 * 
	 */
	
	public boolean isConnected(){
		return connection != null;
	}
	public void createAccount(Player player){
		if(!hasAccount(player)){
			//INSERT
			
			try {
				PreparedStatement q = connection.prepareStatement("INSERT INTO joueurs(uuid,coins,credits,grade,pseudo) VALUES (?,?,?,?,?)");
				q.setString(1, player.getUniqueId().toString());
				q.setInt(2, 100);
				q.setInt(3, 20);
				q.setInt(4, Rank.JOUEUR.getPower());
				q.setString(5,player.getName());
				q.execute();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public boolean hasAccount(Player player){
		//SELECT
		
		try {
			PreparedStatement q = connection.prepareStatement("SELECT uuid FROM joueurs WHERE uuid = ?");
			q.setString(1, player.getUniqueId().toString());
			ResultSet resultat = q.executeQuery();
			boolean hasAccount = resultat.next();
			q.close();
			return hasAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static int getBalance(Player player){
		//SELECT
		
		try {
			PreparedStatement q = connection.prepareStatement("SELECT coins FROM joueurs WHERE uuid = ?");
			q.setString(1, player.getUniqueId().toString());

			int balance = 0;
			ResultSet rs = q.executeQuery();
			
			while(rs.next()){
				balance = rs.getInt("coins");
			}
			
			q.close();
			
			return balance;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static int getBalanceCREDITS(Player player){
		//SELECT
		
		try {
			PreparedStatement q = connection.prepareStatement("SELECT credits FROM joueurs WHERE uuid = ?");
			q.setString(1, player.getUniqueId().toString());

			int balance = 0;
			ResultSet rs = q.executeQuery();
			
			while(rs.next()){
				balance = rs.getInt("credits");
			}
			
			q.close();
			
			return balance;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static void addMoney(Player player,int amount){
		//UPDATE
		
		int balance = getBalance(player);
		int newbalance = balance + amount;
		
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE joueurs SET coins = ? WHERE uuid = ?");
			rs.setInt(1, newbalance);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void removeMoney(Player player,int amount){
		//UPDATE
		
		int balance = getBalance(player);
		int newbalance = balance - amount;
		
		if(newbalance <= 0){
			return;
		}
		
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE joueurs SET coins = ? WHERE uuid = ?");
			rs.setInt(1, newbalance);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addCredits(Player player,int amount){
		//UPDATE
		
		int balance = getBalanceCREDITS(player);
		int newbalance = balance + amount;
		
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE joueurs SET credits = ? WHERE uuid = ?");
			rs.setInt(1, newbalance);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void removeCredits(Player player,int amount){
		//UPDATE
		
		int balance = getBalanceCREDITS(player);
		int newbalance = balance - amount;
		
		if(newbalance <= 0){
			return;
		}
		
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE joueurs SET credits = ? WHERE uuid = ?");
			rs.setInt(1, newbalance);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setRank(Player player, Rank rank) {
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE joueurs SET grade = ? WHERE uuid = ?");
			rs.setInt(1, rank.getPower());
			rs.setString(2, player.getUniqueId().toString()	);
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Rank getRank(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT grade FROM joueurs WHERE uuid = ?");
			q.setString(1, p.getUniqueId().toString());

			int power = 0;
			ResultSet rs = q.executeQuery();
			
			while(rs.next()){
				power = rs.getInt("grade");
			}
			
			q.close();
			
			return Rank.powerToRank(power);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Rank.JOUEUR;
	}
}





































/* package fr.ba.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
	
public class SQLConnection {
	
	private Connection connection;
	private String urlbase,host,database,user,pass;

	public SQLConnection(String urlbase, String host, String database, String user, String pass) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
		// TODO Auto-generated constructor stub
	}


	public void connection() {
		if(!isConnected()) {
		try {
			DriverManager.getConnection(urlbase+host+"/"+database,user,pass);
			System.out.println("Base de données de joueurs connectée !");
		} catch (SQLException e) {
			e.printStackTrace();
		}}
	}
	
	public void disconnect() {
		if(isConnected()) {
		try {
			connection.close();
			System.out.println("Base de données de joueurs déconnectée !");
		} catch (SQLException e) {
			e.printStackTrace();
		}}	
	}
	
	public boolean isConnected() {
		return connection != null;
	}
	
	public void createAccount(Player p) {
		if(!hasAccount(p)){
			
			try {
				PreparedStatement query = connection.prepareStatement("INSERT INTO joueurs(uuid,coins,credits,grade) VALUES(?,?,?)");
				query.setString(1, p.getUniqueId().toString());
				query.setInt(2, 100);
				query.setInt(3, 20);
				query.setString(4, "joueur");
				query.execute();
				query.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//INSERT
		}

	}

	public Boolean hasAccount(Player p) {
		//SELECT
		
		try {
			PreparedStatement query = connection.prepareStatement("SELECT uuid FROM joueurs WHERE uuid = ?");
			query.setString(1, p.getUniqueId().toString());
			ResultSet resultat = query.executeQuery();
			boolean hasAccount = resultat.next();
			query.close();
			return hasAccount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public int getBalance(Player p) {
		//SELECT
		return 0;	}
	
	public void addMoney(Player p, int amount) {
		//UPDATE
	}
	
	public void removeMoney(Player p, int amount) {
		//UPDATE
	}
}
*/