package fr.gearing.chessUHC;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.role.Cavalier;
import fr.gearing.chessUHC.role.Dame;
import fr.gearing.chessUHC.role.Fou;
import fr.gearing.chessUHC.role.Pion;
import fr.gearing.chessUHC.role.Rois;
import fr.gearing.chessUHC.role.Role;
import fr.gearing.chessUHC.role.Tour;

public class GameRun {
	
	ChessUHC main;
	Timer time;

	
	public GameRun(ChessUHC main, Timer time) {
		this.time = time;
		this.main = main;
		ChessUHC.current = ChessGame.GAME;
		World world = Bukkit.getWorld("world");//world non de la map
		world.setSpawnLocation(main.getConfig().getInt("world.spawnX"), main.getConfig().getInt("world.spawnY"), main.getConfig().getInt("world.spawnZ"));
		WorldBorder wb = world.getWorldBorder();//set the border at position
		wb.setCenter(main.getConfig().getInt("world.spawnX"), main.getConfig().getInt("world.spawnZ"));
		wb.setSize(main.getConfig().getInt("world.sizeMap"));
		world.setTime(0);
		
		equipeAlea();
		teleportPlayer();
		rolePlayer();
	}
	
	private void equipeAlea() {
		System.out.println("Creation des equipes");
		
		int nbJoueur = ChessUHC.listPlayer.nbPlayer();
		if(nbJoueur > 1) {
			for(int p = 0; p < nbJoueur/2;p++) {
				int valAlea = (int) (Math.random() * (nbJoueur - 0));
				System.out.println("Noire : " + valAlea);
				ChessUHC.EquipeNoir.addPlayer(valAlea);
			}
		}
		if(nbJoueur > 0) {
			for(int p = 0; p < nbJoueur;p++) {
				if(!ChessUHC.EquipeNoir.inList(p)) {
					System.out.println("Blanc : " + p);
					ChessUHC.EquipeBlanc.addPlayer(p);
				}	
			}	
		}
	}
	
	private void teleportPlayer() {
		System.out.println("Joueur teleporter");
		int size = main.getConfig().getInt("world.sizeMap");
		int nbJoueur = ChessUHC.listPlayer.nbPlayer();
		int espace = (size*4)/ (nbJoueur+6);
		int xspawn = main.getConfig().getInt("world.spawnX");
		int zspawn = main.getConfig().getInt("world.spawnZ");
		
		int x1 = (xspawn+(size/2))-espace;
		int x2 = (xspawn-(size/2))+espace;
		int z1 = (zspawn-(size/2))+espace;
		int z2 = (zspawn+(size/2))-espace;
		int x = x1;
		int z = z1;
		int zone = 1;
		for(Players p : ChessUHC.listPlayer.listPlayer()) {
			p.getPlayer().setGameMode(GameMode.SURVIVAL);
			p.getPlayer().teleport(new Location(Bukkit.getWorld("world"), x, 201, z));
			p.getPlayer().getInventory().clear();

			if(zone == 1) {
				if(z < z2) {
					z = z + espace;
				}else {
					zone++;
				}
			}
			if(zone == 2) {
				if(x > x2 ) {
					x = x - espace;
				}else {
					zone++;
				}
			}
			if(zone == 3) {
				if(z > z1) {
					z = z - espace;
				}else {
					zone++;
				}
			}
			if(zone == 4) {
				if(x < x1 ) {
					x = x + espace;
				}else {
					zone++;
				}
			}
			
		}
		
	}
	
	private void rolePlayer() {
		List<Equipe> equipeNom = Arrays.asList(ChessUHC.EquipeNoir, ChessUHC.EquipeBlanc);
		System.out.println("role Aleatoire");
		
		for(int i = 0; i < equipeNom.size(); i++) {
			int NBequipe = equipeNom.get(i).nbPlayer();

		
			int valAlea = playerAlea(NBequipe);
			System.out.println("A bas ma non");
			if(valAlea != -1) {
				NBequipe--;
				Players p = ChessUHC.listPlayer.getPlayer(valAlea);
				new Rois("Rois", p.getEquipeList(), p);
				System.out.println("A bas si");
			}
		
			valAlea = playerAlea(NBequipe);
			if(valAlea != -1) {
				NBequipe--;
				Players p = ChessUHC.listPlayer.getPlayer(valAlea);
				new Dame("Dame", p.getEquipeList(), p);
			}
		
			for(int i1 = 0; i1 < 2;i1++) {
				valAlea = playerAlea(NBequipe);
				if(valAlea != -1) {
					NBequipe--;
					Players p = ChessUHC.listPlayer.getPlayer(valAlea);
					new Fou("Fou", p.getEquipeList(), p);
				}
			}
			for(int i1 = 0; i1 < 2;i1++) {
				valAlea = playerAlea(NBequipe);
				NBequipe--;
				if(valAlea != -1) {
					Players p = ChessUHC.listPlayer.getPlayer(valAlea);
					new Cavalier("Cavalier", p.getEquipeList(), p);
				}
			}
			for(int i1 = 0; i1 < 2;i1++) {
				valAlea = playerAlea(NBequipe);
				NBequipe--;
				if(valAlea != -1) {
					Players p = ChessUHC.listPlayer.getPlayer(valAlea);
					new Tour("Tour", p.getEquipeList(), p);
				}
			}
			for(int i1 = 0; i1 < NBequipe;i1++) {
				valAlea = playerAlea(NBequipe);
				NBequipe--;
				if(valAlea != -1) {
					Players p = ChessUHC.listPlayer.getPlayer(valAlea);
					new Pion("Pion", p.getEquipeList(), p);

				}
			}
		}
	}
	
	private int playerAlea(int nbMax) {
		if(nbMax>0)return (int) (Math.random() * (nbMax - 0));
		return -1;
	}
	
	

}
