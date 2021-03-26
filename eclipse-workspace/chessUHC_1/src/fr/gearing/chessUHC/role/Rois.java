package fr.gearing.chessUHC.role;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.Players;


public class Rois extends Role {

	

	public Rois(String nom, Equipe list, Players pl) {
		super(nom, list, pl);
	}

	@Override
	public String roleInfoDeath() {
		return "§4toute l'equipe " + list.getNom() + " perd ces effets." + "Ils obtiennent l'effet Weakness 1" ;
	}

	@Override
	public PotionEffect effetDeath() {
		return new PotionEffect(PotionEffectType.WEAKNESS, 40, 2);
		
	}

	@Override
	public void mort() {
		Bukkit.broadcastMessage("§4Echec et Mat");
		list.setRoi(false);
		
	}

	@Override
	public void passif() {
		//fait dans le damage listener
		
	}

	@Override
	public void actif() {
		this.actifUtiliser = true;
		/*
		List<Players> tour = list.listPlayerHaveRole("Tour");
		
		if(tour.size() < 0) {
			pl.getPlayer().sendMessage("§4Il n'y a plus de Tour pour vous aider");
		}
		@SuppressWarnings("unused")
		Utility util = new Utility(pl.getPlayer(), list.getPlayer((int) (Math.random() * (tour.size() - 0))).getPlayer());
		*/
	}

	@Override
	public void effetTeams() {
		//none
		
	}

	@Override
	public String roleInfo() {
		String res = "";
		res += "les effet :";
		res += "/nvous connaissez :";
		res += "/ncapacité Roque: vous pouvez téléporter une Tour a vous";
		res += "/nsi vous mourez votre equipe recevras Weakness 1";
		return res;
	}

	@Override
	public void effet() {
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 2));
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 40, 2));
	}

	@Override
	public void update() {
		//none
		
	}

	@Override
	public void give() {
		// TODO Auto-generated method stub
		
	}

}
