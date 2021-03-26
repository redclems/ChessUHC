package fr.gearing.chessUHC.role;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.PlayerList;
import fr.gearing.chessUHC.Players;
import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.commands.mob;

public class Cavalier extends Role{

	public Cavalier(String nom, Equipe list, Players pl) {
		super(nom, list, pl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void passif() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actif() {
		this.actifUtiliser = true;
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 1));
		pl.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
		
	}

	@Override
	public void effet() {
		//none
	}

	@Override
	public void update() {
		this.actifUtiliser = false;
		
	}

	@Override
	public void mort() {
		mob.spawnZombie(pl.getPlayer().getLocation());
	}

	@Override
	public void effetTeams() {
		//nulll
		
	}

	@Override
	public PotionEffect effetDeath() {
		// null
		return null;
	}

	@Override
	public String roleInfoDeath() {
		
		return null;
	}

	@Override
	public String roleInfo() {
		String res = "";
		res += "les effet :";
		res += "vous connaissez :";
		res += "capacité Saut d'obstacle: vous obtenez Jump Boost 2 et speed 1 et changement de couleur de case. ";
		res += "Passif si vous etes a 20block d'une tour du camp adversse vous obtenez Force 1";
		return res;
	}

	@Override
	public void give() {
		pl.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
		
	}


}
