package fr.gearing.chessUHC;

import fr.gearing.chessUHC.commands.ChessUHC;

public class UpdateJour {
	
	public static void update(String day) {
		if(day.equals("nuit")) {
			for(Players player : ChessUHC.EquipeNoir.listPlayer()){
				player.getRole().effet();
			}
		}
		if(day.equals("jour")) {
			for(Players player : ChessUHC.EquipeBlanc.listPlayer()){
				player.getRole().effet();
			}
		}
	}

}
