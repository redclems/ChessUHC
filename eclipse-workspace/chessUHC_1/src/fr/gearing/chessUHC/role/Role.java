package fr.gearing.chessUHC.role;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.Players;
import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.commands.Item;

public abstract class Role {
	
	protected String nom;
	protected String info;
	protected String infoDeath;
	protected Equipe list;
	protected Players pl;
	protected int temp = 0;
	protected ItemStack item;
	protected boolean actifUtiliser = false;
	
	public Role(String nom, Equipe list, Players pl) {
		this.nom = nom;
		this.list = list;
		this.pl = pl;
		this.pl.setRole(this);
		System.out.println("le joueur "+pl.getPlayer().getName()+ " a reussus le role euhhh");

	}
	
	protected String nomRole;
	
	public abstract void give();
	
	public abstract void passif();
	
	public abstract void actif();
	
	public abstract void effet();
	
	public abstract void update();
	
	public abstract void mort();
	
	public abstract void effetTeams();
	
	public abstract PotionEffect effetDeath();
	
	public abstract String roleInfoDeath();
	
	public abstract String roleInfo();
	
	public String getNom() {
		return nom;
	}
	public void giveItem(Player pl) {
		this.item = Item.ItemRole(this.pl);
		if(!pl.getInventory().contains(item)) {
			pl.getInventory().addItem(item);
		}else {
			pl.sendMessage("§4tu as dejas l'item");
		}
	}
	public void load() {
		this.item = Item.ItemRole(this.pl);
		this.giveItem(pl.getPlayer());
		this.give();
	}
	public ItemStack getitem() {
		return this.item;
	}


}
