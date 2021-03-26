package fr.gearing.chessUHC.title;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.gearing.chessUHC.Equipe;
import fr.gearing.chessUHC.Timer;
import fr.gearing.chessUHC.commands.ChessUHC;

public class CustomScoreboardManager{
   
    public static void CustomScoreboardDisplay(Timer time){
    	for(Player pl : Bukkit.getOnlinePlayers()) {
    	   if(time.getJour() == 1) {
    		   disp(time, pl.getPlayer());
       	   }else {
       		   time.getSeconde() ;
       		   if(time.getTime()< 5)
       		   disp2(time, pl.getPlayer(), ChessUHC.EquipeBlanc);
       		   
       		   else if(time.getTime()<=10)
       		   disp2(time, pl.getPlayer(), ChessUHC.EquipeNoir);
    	   }
       }

    }
    private static void disp(Timer time, Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("§aChess§cU§9H§eC", "");
        
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(" ");
        score.setScore(15);
        Score score1 = obj.getScore("§r§2Jour : §4" + time.getJour());
        score1.setScore(14);
        Score score2 = obj.getScore("§r§2Time : §4" + time.getMinute() +"§2:§4"+ time.getSeconde() );
        score2.setScore(13);
        Score score20 = obj.getScore("§r§2groupe de : §4" + ChessUHC.groupeTaille);
        score20.setScore(12);
        Score score3 = obj.getScore("§7---------");
        score3.setScore(11);
        p.setScoreboard(board);
    }

    private static void disp2(Timer time, Player p, Equipe equipe) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("§aChess§cU§9H§eC", "");
        
        int nbJoueur =  0;
        String add = "§2";
        
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(" ");
        score.setScore(15);
        Score score1 = obj.getScore("§r§2Jour : §4" + time.getJour());
        score1.setScore(14);
        Score score2 = obj.getScore("§r§2Time : §4" + time.getMinute() +"§2:§4"+ time.getSeconde() );
        score2.setScore(13);
        Score score20 = obj.getScore("§r§2groupe de : §4"+ChessUHC.groupeTaille);
        score20.setScore(12);
        Score score3 = obj.getScore("§7---------");
        score3.setScore(11);
        
        Score score10 = obj.getScore("§7Role "+ equipe.getNom() + "§7:");
        score10.setScore(10);
        
        nbJoueur = equipe.NBRolePlayer("Rois");
        add = "§2";
        if(nbJoueur == 0)add="§m";
        Score score4 = obj.getScore("§7"+add+"Rois :" + nbJoueur);
        score4.setScore(9);
        
        nbJoueur = equipe.NBRolePlayer("Dame");
        add = "§2";
        if(nbJoueur == 0)add="§m";
        Score score5 = obj.getScore("§7"+add+"Dame :" + nbJoueur);
        score5.setScore(8);
        
        nbJoueur = equipe.NBRolePlayer("Fou");
        add = "§2";
        if(nbJoueur == 0)add="§m";
        Score score6 = obj.getScore("§7"+add+"Fou :" + nbJoueur);
        score6.setScore(7);
        
        nbJoueur = equipe.NBRolePlayer("Cavalier");
        add = "§2";
        if(nbJoueur == 0)add="§m";
        Score score7 = obj.getScore("§7"+add+"Cavalier :" + nbJoueur);
        score7.setScore(6);
        
        nbJoueur = equipe.NBRolePlayer("Tour");
        add = "§2";
        if(nbJoueur == 0)add="§m";
        Score score8 = obj.getScore("§7"+add+"Tour :" + nbJoueur);
        score8.setScore(5);
        
        nbJoueur = equipe.NBRolePlayer("Pion");
        add = "§2";
        if(nbJoueur == 0)add="§m";
        Score score9 = obj.getScore("§7"+add+"Pion :" + nbJoueur);
        score9.setScore(4);
        
        p.setScoreboard(board);
    }
    
    
    
    
 
}
 