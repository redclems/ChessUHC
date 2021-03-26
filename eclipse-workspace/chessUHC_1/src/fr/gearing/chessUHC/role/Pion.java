package fr.gearing.chessUHC.role;

import org.bukkit.potion.PotionEffect;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.PlayerList;
import fr.gearing.chessUHC.Players;

public class Pion extends Role{

	public Pion(String nom, Equipe list, Players pl) {
		super(nom, list, pl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void give() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passif() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actif() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effetTeams() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PotionEffect effetDeath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roleInfoDeath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roleInfo() {
		String res = "";
		res += "les effet :";
		res += "vous connaissez :";
		res += "PROMOTION : Après avoir marcher sur un certain nombre de block, vous allez pouvoir être promu en pièce majeur répliqué. Dès que vous êtes promu, vous ne pourrez plus revenir en arrière et garderez cette promotion définitivement. Plus vous marchez, plus vous aurez accès à des promotions plus intéressante.";
		res += "AVANCÉE SOUDAINE :\n"+ " - Une fois dans la partie, vous pouvez dash de 5 blocs devant vous. Ne peut plus être utiliser après l’activation du Passif.\n";
		return res;
	}

}
