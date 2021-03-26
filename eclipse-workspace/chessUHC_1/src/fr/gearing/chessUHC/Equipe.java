package fr.gearing.chessUHC;

import java.util.ArrayList;
import java.util.List;

import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.role.Role;

public class Equipe{
	protected List<Integer> listPlayer;
	protected String nom;
	protected String Couleur;
	protected boolean roi;
	

	public Equipe(String nom, String couleur) {
		listPlayer = new ArrayList<>();
		this.nom = nom;
		this.Couleur = couleur;
	}
	
	public String getNom() {
		return nom;
	}
	public String getCouleur() {
		return Couleur;
	}
	public boolean getRoi() {
		return roi;
	}
	public void setRoi(boolean val) {
		roi = val;
	}

	public void addPlayer(int indiceP) {
		listPlayer.add(indiceP);
		Players player = ChessUHC.listPlayer.getPlayer(indiceP);
		player.setTeams(this);
		player.rename(ChessUHC.EquipeBlanc.getCouleur()+player.getPlayer().getName()+"§f");
		//System.out.println("Le joueur " + player.getPlayer().getName() + " a etait ajouter dans l'equipe " + this.nom);
	}
	public void popPlayer(int indiceP) {
		for(int i = 0; i < listPlayer.size(); i++) {
			if(listPlayer.get(i) == indiceP)listPlayer.remove(i);
		}
	}

	public Players getPlayer(int i) {
		return ChessUHC.listPlayer.getPlayer(listPlayer.get(i));
	}
	
	public Role getRolePlayer(int i) {
		return ChessUHC.listPlayer.getPlayer(i).getRole();
	}
	
	public int NBRolePlayer(String role) {
		int val = 0;
		for(int i : listPlayer) {
			if(ChessUHC.listPlayer.getPlayer(i).getRole().getNom().equals(role)) {
				val++;
			}
		}
		return val;
	}
		
	public String getCouleurCase(int i) {
		return ChessUHC.listPlayer.getPlayer(i).getCouleurCase();
	}
	
	public int nbPlayer() {
		return listPlayer.size();
	}
	public boolean inList(int indiceP) {
		for(int i : listPlayer) {
			if(i == indiceP) {
				return true;
			}
		}
		return false;
	}
		
	@Override
	public String toString() {
		String joueur = "";
		for(int i : listPlayer) {
			joueur = joueur + ChessUHC.listPlayer.getPlayer(i).getPlayer().getName()+ ", "; 
		}
		return joueur;
	}
	public List<Players> listPlayer() {
		List<Players> res = new ArrayList<>();
		for(int i = 0; i < listPlayer.size(); i++) {
			res.add(ChessUHC.listPlayer.getPlayer(listPlayer.get(i)));
		}
		return res;
	}


}

