package fr.ba.bdd;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCredits implements CommandExecutor {

	

	public CmdCredits(SqlConnection sql) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player)sender;
			
			//money --> donner son argent
			if(args.length == 0){
				int balance = SqlConnection.getBalanceCREDITS(p);
				p.sendMessage("§7Tu as §a" + balance+" §7crédits.");
			}
			
			//money add 50 gravenilvec
			
			if(args.length >= 1){
				
				//money add
				if(args[0].equalsIgnoreCase("add")){
					
					if(args.length == 1 || args.length == 2){
						p.sendMessage(ChatColor.RED+"La commande est /credits <add>/<remove> [joueur] [montant].");
					}
					
					if(args.length == 3){
						if(p.isOp()||SqlConnection.getRank(p).getPower() == 34 || SqlConnection.getRank(p).getPower() == 33) {
						Player cible = Bukkit.getPlayer(args[1]);
						if(cible != null){	
							int montant = Integer.valueOf(args[2]);
							SqlConnection.addCredits(cible, montant);
							cible.sendMessage(ChatColor.GRAY+"Tu as reçu "+ ChatColor.GREEN+ montant+ChatColor.GRAY+" crédits de la part de " +ChatColor.GREEN+ p.getName()+ChatColor.GRAY+".");
							p.sendMessage(ChatColor.GRAY+"Tu as ajouté "+ ChatColor.GREEN+ montant+ChatColor.GRAY+" crédits à " +ChatColor.GREEN+ p.getName()+ChatColor.GRAY+".");
						}}else { p.sendMessage(ChatColor.RED+"Tu n'as pas accès à cette commande.");}
						
						
					}
					
				}
				
				//money remove
				if(args[0].equalsIgnoreCase("remove")){
					
					if(args.length == 1 || args.length == 2){
						p.sendMessage(ChatColor.RED+"La commande est /credits <add>/<remove> [joueur] [montant].");
					}
					
					if(args.length == 3){
						if(p.isOp()||SqlConnection.getRank(p).getPower() == 34 || SqlConnection.getRank(p).getPower() == 33) {
						Player cible = Bukkit.getPlayer(args[1]);
						if(cible != null){	
							int montant = Integer.valueOf(args[2]);
							SqlConnection.removeCredits(cible, montant);
							cible.sendMessage(ChatColor.GREEN+ p.getName()+ChatColor.GRAY+" t'as retiré "+ ChatColor.GREEN+ montant+ChatColor.GRAY+" crédits" +ChatColor.GRAY+".");
							p.sendMessage(ChatColor.GRAY+"Tu as retiré "+ ChatColor.GREEN+ montant+ChatColor.GRAY+" crédits à " +ChatColor.GREEN+ p.getName()+ChatColor.GRAY+".");
						}}else { p.sendMessage(ChatColor.RED+"Tu n'as pas accès à cette commande.");}
						
						
					}
					
					
				}
				
			}
			
			//money remove gravenilvec 50
			
		}
		
		return false;
	}

}




















/* 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCoins implements CommandExecutor {

	private SqlConnection sql;
	
	public CmdCoins(SqlConnection sql) {
		this.sql = sql;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0 ) {
				int balance = sql.getBalance(p);
				p.sendMessage(ChatColor.GREEN+"Tu possèdes "+balance+" EloniaCoins");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			}
			
			if (args.length >=1 || args.length == 2) {
				if(args[0].equalsIgnoreCase("add")) {
					if(args.length == 1) {
						p.sendMessage(ChatColor.RED+"La commande est /money <add>/<remove> [montant] [joueur].");
					}
					if (args.length == 3) {
						if(p.isOp()) {
							
						
						Player cible = Bukkit.getPlayer(args[2]);
						if(cible!=null) {
							int montant = Integer.valueOf(args[1]);
							sql.addMoney(cible, montant);
							p.sendMessage(ChatColor.GRAY+"Tu as envoyé "+ChatColor.GREEN+montant+ChatColor.GRAY+" coins à "+ChatColor.GREEN+cible.getName()+ChatColor.GRAY+".");
						}}else {p.sendMessage(ChatColor.RED+"Tu n'as pas accès à cette commande.");
					}
				}
				
				if(args[0].equalsIgnoreCase("remove")) {
					if(args.length == 1 || args.length == 2) {
						p.sendMessage(ChatColor.RED+"La commande est /money <add>/<remove> [montant] [joueur].");
					}
					if (args.length == 3) {
						if(p.isOp()) {
					}
						Player cible = Bukkit.getPlayer(args[2]);
						if(cible!=null) {
							int montant = Integer.valueOf(args[1]);
							sql.removeMoney(cible, montant);
							p.sendMessage(ChatColor.GRAY+"Tu as retiré "+ChatColor.GREEN+montant+ChatColor.GRAY+" coins à "+ChatColor.GREEN+cible.getName()+ChatColor.GRAY+".");
						}}else {p.sendMessage(ChatColor.RED+"Tu n'as pas accès à cette commande.");
					
						
			}
		}}}
}
		return false;
	
	
}}
 */