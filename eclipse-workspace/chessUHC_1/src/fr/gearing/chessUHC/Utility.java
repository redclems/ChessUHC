package fr.gearing.chessUHC;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Utility extends BukkitRunnable{
	
	private int second = 0;
	private boolean chrono = false;
	
	private int action;
	
	private Player telepoter;
	private Player cible;
	
	public Utility(Player player1, Player player2) {
		this.action = 1;
		this.cible = player1;
		this.telepoter = player2;
		second = 10;
		chrono = true;
		telepoter.sendMessage("§4Vous allez rejoindre votre roi");
	}

	@Override
	public void run() {
		if(chrono) {
			second--;
			if(second == 0) {
				chrono=false;
				action();
			}
		}
		
	}

	public boolean isChrono() {
		return chrono;
	}

	public void setChrono(boolean chrono) {
		this.chrono = chrono;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	public void action() {
		if(action == 1) {
			telepoter.teleport(cible.getLocation());
		}
	}

}
