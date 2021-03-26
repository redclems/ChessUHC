package fr.gearing.chessUHC.role;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.PlayerList;
import fr.gearing.chessUHC.Players;

public class Dame extends Role{

	public Dame(String nom, Equipe list, Players pl) {
		super(nom, list, pl);
	}

	@Override
	public void passif() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actif() {
		this.actifUtiliser = true;
		this.temp = 60;
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 2));
		pl.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	}

	@Override
	public void effet() {
		if(temp == 0) {
			pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 2));
		}else {
			temp--;
		}
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 1));
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 40, 1));
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
	}

	@Override
	public void mort() {
		//none
		
	}

	@Override
	public void effetTeams() {
		//none
		
	}

	@Override
	public PotionEffect effetDeath() {
		//none
		return null;
	}

	@Override
	public String roleInfoDeath() {
		//none
		return null;
	}

	@Override
	public String roleInfo() {
		String res = "";
		res += "les effet :";
		res += "vous connaissez :";
		res += "capacité Sortie Magistral: vous obtenez speed 2 mais perder l'effet force pendant 1mn";
		return res;
	}

	@Override
	public void update() {
		this.actifUtiliser = false;
		
	}

	@Override
	public void give() {
		// TODO Auto-generated method stub
		
	}


}
