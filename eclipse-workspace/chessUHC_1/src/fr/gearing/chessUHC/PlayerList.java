package fr.gearing.chessUHC;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.gearing.chessUHC.role.Role;


public class PlayerList {
	
	protected List<Players> listPlayer;
	protected String nom;
	protected String Couleur;
	protected boolean roi;
	
	public PlayerList(List<Players> equipe) {
		listPlayer = new ArrayList<>();
		for(Players pl : equipe) {
			this.addPlayer(pl, false, false);
		}
	}
	public PlayerList(String nom) {
		listPlayer = new ArrayList<>();
		this.nom = nom;
	}
	public PlayerList(String nom, String couleur) {
		listPlayer = new ArrayList<>();
		this.nom = nom;
		this.Couleur = couleur;
	}
	
	public boolean getRoi() {
		return roi;
		
	}
	public void setRoi(boolean val) {
		roi = val;
	}
	public String getNom() {
		return nom;
	}
	public String getCouleur() {
		return Couleur;
	}
	public void addPlayer(Player pl, boolean afficher, boolean afficherP) {
		if(!inList(pl)){
			this.addPlayer(new Players(pl), afficher, afficherP);
		}else {
			Players plsave = this.getPlayer(this.indicePlayer(pl));
			this.addPlayer(new Players(pl, plsave.getCouleurCase(), plsave.getRole(), plsave.getPlayerList(), plsave.getEquipeList()), afficher, afficherP);
			this.popPlayer(plsave, false, false);
		}
	}
	public void addPlayer(Players p, boolean afficherAll, boolean afficherP) {
		listPlayer.add(p);
		p.setPlayerList(this);
		if(afficherAll)Bukkit.broadcastMessage("§e"+ p.getPlayer().getName() + " §2viens de rejoindre l'equipe §e" + nom);
		if(afficherP)this.messageJoin(p);
	}
	public void popPlayer(Players p, boolean afficher, boolean afficherP) {
		List<Players> newlistPlayer = new ArrayList<>();
		for(Players pl : listPlayer) {
			if(!pl.getPlayer().equals(p.getPlayer()))newlistPlayer.add(pl);
		}
		listPlayer = newlistPlayer;
		p.setPlayerList(null);
		if(afficher) Bukkit.broadcastMessage("§e"+ p.getPlayer().getName() + " §4viens de quitter la parties" + nom);
		if(afficherP)this.messageLeft(p);
	}
	public void popPlayer(Player p, boolean afficher, boolean afficherP) {
		for(Players pl : listPlayer) {
			if(pl.getPlayer().equals(p)) {
				popPlayer(pl, afficher, afficherP);
			}
		}
	}
	public Players getPlayer(int i) {
		return listPlayer.get(i);
	}
	
	public Role getRolePlayer(int i) {
		return listPlayer.get(i).getRole();
	}
	
	public int getNBPlayerRole(String nomRole) {
		int nb = 0;
		for(Players pl : listPlayer) {
			if(pl.getRole().getNom().equals(nomRole))nb++;
		}
		return nb;
	}
	public int getNBPlayerList() {
		return listPlayer.size();
	}
	
	
	public String getCouleurCase(int i) {
		return listPlayer.get(i).getCouleurCase();
	}
	
	public int nbPlayer() {
		return listPlayer.size();
	}
	
	public void messageJoin(Players p) {
		p.getPlayer().sendMessage("§2vous etes ajouter dans l'equipe §e" + nom);

	}
	public void messageLeft(Players p) {
		p.getPlayer().sendMessage("§2vous etes ejecter de l'equipe §e" + nom);

	}
	
	public boolean inList(Player p) {
		for(Players pl : listPlayer) {
			if(pl.getPlayer().getUniqueId().equals(p.getUniqueId())) {
				System.out.println(nom);
				return true;
			}
		}
		return false;
	}
	public int indicePlayer(Player p) {
		for(int i = 0; i < listPlayer.size() ; i++) {
			if(listPlayer.get(i).getPlayer().getUniqueId().equals(p.getUniqueId())) {
				return i;
			}
		}
		return 0;
	}
	public Role RolePlayer(Players p) {
		for(int i = 0; i < listPlayer.size() ; i++) {
			if(listPlayer.get(i).getPlayer().equals(p)) {
				return p.getRole();
			}
		}
		return null;
	}
	public int indicePlayer(Players p) {
		for(int i = 0; i < listPlayer.size() ; i++) {
			if(listPlayer.get(i).getPlayer().equals(p.getPlayer())) {
				return i;
			}
		}
		return -1;
	}
		
	@Override
	public String toString() {
		String joueur = "";
		for(Players p : listPlayer) {
			joueur = joueur + p.getPlayer().getName()+ ", "; 
		}
		return joueur;
	}
	public List<Players> listPlayer() {
		return listPlayer;
	}
	public List<Players> listPlayerHaveRole(String name) {
		List<Players> res = new ArrayList<>();
		for(Players pl : listPlayer) {
			if(pl.getRole().getNom().equals(name))res.add(pl);
		}
		return res;
	}

}
