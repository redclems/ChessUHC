package fr.gearing.chessUHC.role;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.PlayerList;
import fr.gearing.chessUHC.Players;

public class Fou extends Role{

	public Fou(String nom, Equipe list, Players pl) {
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
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1));
		
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
		return "§ele Fou est mort " + pl.getPlayer().getLocation().toString();
	}

	@Override
	public String roleInfo() {
		String res = "";
		res += "les effet :";
		res += "vous connaissez :";
		res += " COULEUR CIBLÉE : Vous infliger 1,5* plus de dégâts au joueurs ayant la même couleur de case que vous et 1,5* moins de dégâts au joueurs ayant l’autre couleur de case. ";
		res += " FOU DU ROI : Lors de votre mort, les joueurs encore en vie connaîtront la position exact du décès et le nom de la personne qui vous a tuer.";
		return res;
	}

}
