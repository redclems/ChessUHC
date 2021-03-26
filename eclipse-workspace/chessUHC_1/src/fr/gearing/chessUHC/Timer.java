package fr.gearing.chessUHC;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.title.CustomScoreboardManager;
import fr.gearing.chessUHC.title.Title;
import fr.gearing.chessUHC.title.TitleDirection;

public class Timer extends BukkitRunnable {
	
	private int second = 0;
	private int time = 0;
	private int minute = 0;
	private int jour = 1;
	
	private String day;
	
	private int tenSecond = 10;
	
	public boolean addDelais = false;
	
	ChessUHC main;
	GameRun game;
	
	public Timer(ChessUHC main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		
		if(ChessUHC.current == ChessGame.GAME) {
            
			if(second >= 60) {
				minute++;
				second = 0;
			}
			if(minute >= 20) {
				jour++;
				minute = 0;
			}
			if(jour > 1 ) {
				if(minute == 0 && second == 0) {
					day = ("jour");
					Bukkit.broadcastMessage("§2Les pieces blanc on leur effet activé");
				}
				if(minute == 10 && second == 0) {
					day = ("nuit");
					Bukkit.broadcastMessage("§2Les pieces noire on leur effet activé");
				}
				UpdateJour.update(day);
			}
			if(jour == 1 && second == 0 && minute == 2) {
				Bukkit.broadcastMessage("§2Vous etes plus invulnerable");
			}
			if(jour > 1 && second == 0 && minute == 0) {
				Bukkit.broadcastMessage("§2Nous somme le jour §e" + this.jour);
			}
			if(jour > 1 && second == 0 && minute == 0) {
				couleurCase();
			}
			if(jour == 2 && second == 0 && minute == 0) {
				ChessUHC.setPVP(false);//active le pvp
				for(Players pl : ChessUHC.listPlayer.listPlayer()) {
					pl.getRole().load();
					pl.showRole();
					pl.showTeam();
					
				}
			}
			if(jour == main.getConfig().getInt("world.borderJour") && second == 0 && minute == 0) {
				Bukkit.broadcastMessage("§2La bordure se deplace, ATTENTION !!!");
				ChessUHC.borderMouve = true;
			}
			CustomScoreboardManager.CustomScoreboardDisplay(this);
			distance();
			time++;
			if(time >= 10)time = 0;
			if(!ChessUHC.stop) {
				second++;
				if(jour > 1 ) {
					for(Players pl : ChessUHC.listPlayer.listPlayer()) {
						pl.getRole().passif();
					}
				}
			}
		}else if(ChessUHC.current == ChessGame.PREGAME) {
			if(tenSecond <= 5) {
				Bukkit.broadcastMessage("§2la partis commence dans: §e" + tenSecond + "§2s");
			}
			if(tenSecond == 0) {
				for(Players player : ChessUHC.listPlayer.listPlayer()){
					Title.sendTitle(player.getPlayer(), "La partie a commencer", "Bonne Chance", 25);
				}
				game = new GameRun(main, this);
			}
			tenSecond--;
		}
	}
	
	public int getJour() {
		return this.jour;
	}
	public int getMinute() {
		return this.minute;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSeconde() {
		return this.second;
	}
	public int getTime() {
		return this.time;
	}
	public void setSeconde(int second) {
		this.second = second;
	}
	
	public void couleurCase(){
		for(Players p : ChessUHC.listPlayer.listPlayer()) {
			String col = "";
			int alea = aleaCouleur();
			if(alea == 0) {
				col = "noire";
			}else if(alea == 1) {
				col = "blanc";
			}
			p.setCouleurCase(col);
		}
	}
	
	static int aleaCouleur() {
		return (int) (Math.random() * (2 - 0));
	}
	
	private void distance() {
		for(Player player : Bukkit.getOnlinePlayers()){
			TitleDirection.directionDisplay(player);
		}
	}
}
