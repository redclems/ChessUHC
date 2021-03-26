package fr.gearing.chessUHC.role;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.PlayerList;
import fr.gearing.chessUHC.Players;

public class Tour extends Role{

	public Tour(String nom, Equipe list, Players pl) {
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
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 2));
		
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
		res += "MAT DE L’ESCALIER : Si vous vous trouvez à moins de 20 blocks de l’autre Tour ou d’un Pion promu Tour, vous obtiendrez l’effet Résistance 1.";
		return res;
	}

	


}
