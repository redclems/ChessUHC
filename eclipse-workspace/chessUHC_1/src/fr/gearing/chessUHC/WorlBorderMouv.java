package fr.gearing.chessUHC;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

import fr.gearing.chessUHC.commands.ChessUHC;

public class WorlBorderMouv extends BukkitRunnable {
	
	int tailleMin;
	float NBjour;
	
	double tempMax;
	int temp;
	double worldSize;
	
	World world;
	WorldBorder wb;
	ChessUHC main;
	
	boolean stop;
	
	
	public WorlBorderMouv(World world, WorldBorder wb, ChessUHC main) {
		 this.wb = wb;
		 this.world = world;
		 
		 this.NBjour = main.getConfig().getInt("world.tenpBorder");
		 this.tailleMin = main.getConfig().getInt("world.sizeMapEnd")*2;
		 
		 this.worldSize = this.wb.getSize();
		 this.temp = 0;
		 this.tempMax = (1200*NBjour)/(worldSize - tailleMin);
		 this.stop = false;
	}

	@Override
	public void run() {
		if(ChessUHC.borderMouve) {
			if(temp >= tempMax) {
				worldSize--;
				temp = 0;
				if(worldSize > tailleMin) {
					wb.setSize(worldSize);
					stop = true;
				}
			}
			temp++;
		}
	}

}
