package fr.gearing.chessUHC;

import org.bukkit.entity.Player;

import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.role.Role;

public class Players{
	
	Player p;
    Role role;
	String CouleurCase;
	Equipe equipe; 
	PlayerList listPlayer; 
	int NBpas;
	
	public Players(Player p) {
		this.p = p;
		int alea = Timer.aleaCouleur();
		if(alea == 0) {
			CouleurCase = "noire";
		}else if(alea == 1) {
			CouleurCase = "blanc";
		}
	}
	public Players(Player p,String CouleurCase, Role role, PlayerList listPlayer, Equipe equipe) {
		this.p = p;
		this.role = role;
		this.CouleurCase = CouleurCase;
		this.listPlayer = listPlayer;
		this.equipe = equipe;
		int alea = Timer.aleaCouleur();
		if(alea == 0) {
			CouleurCase = "noire";
		}else if(alea == 1) {
			CouleurCase = "blanc";
		}
	}
	
	public Player getPlayer() {
		return p;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		//System.out.println("ajout du role " + role.getNom() + " a " + p.getName());
		this.role = role;
	}
	public String getCouleurCase(){
		return CouleurCase;
	}
	public void setCouleurCase(String couleur) {
		if(!role.getNom().equals("Fou") && ChessUHC.Timer().getJour() > 2) {
			p.sendMessage("§9La couleur pour se tour est : §e" + couleur);
			CouleurCase = couleur;
		}
	}
	public String getTeams() {
		return equipe.getNom();
	}
	public Equipe getEquipeList() {
		return equipe;
	}
	public void setTeams(Equipe team) {
		equipe = team;
	}

	public PlayerList getPlayerList() {
		return listPlayer;
	}
	public void setPlayerList(PlayerList team) {
		listPlayer = team;
	}
	public void showRole() {
		if(role.getNom() != null) {
			p.sendMessage("§2§l=========================================");
			p.sendMessage("§4"+ "vous etes : §2" + role.getNom());
			p.sendMessage("§4"+ role.roleInfo());
			p.sendMessage("§2§l=========================================");
		}
	}
	public void showTeam() {
		if(equipe.getNom() != null) {
			p.sendMessage("§l"+ "vous etes dans l'equipe : §2" + equipe.getNom());
		}		
	}
	
	public void rename(String name) {
		if(ChessUHC.viewTeams) {
			p.getPlayer().setPlayerListName(name);
			p.getPlayer().setDisplayName(name);
		}
	}
	
	public boolean equals(Object other) {
		System.out.println("equals Player");
		if(other == null) {
			return false;
		}else if(other instanceof Players) {
			Players autrePerso = (Players) other;
			System.out.println("equals");
			return this.p.getPlayer().getUniqueId().equals(autrePerso.getPlayer().getUniqueId());
		}else if(other instanceof Player) {
			Player autrePerso = (Player) other;
			System.out.println("equals Player");
			return this.p.getPlayer().getUniqueId().equals(autrePerso.getUniqueId());
		}else {
			System.out.println("bas non");
			return false;
		}
	}	
}
