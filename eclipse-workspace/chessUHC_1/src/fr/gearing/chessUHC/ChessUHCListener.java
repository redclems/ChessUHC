package fr.gearing.chessUHC;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.gearing.chessUHC.commands.ChessUHC;
import fr.gearing.chessUHC.role.Role;
import fr.gearing.chessUHC.title.CustomScoreboardManager;
import fr.gearing.chessUHC.title.Title;
import fr.gearing.chessUHC.title.TitleDirection;

@SuppressWarnings("deprecation")
public class ChessUHCListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		if(ChessUHC.current == ChessGame.LOBBY) {
			if(!ChessUHC.listPlayer.inList(player)) {
				player.setHealth(20D);
				player.setFoodLevel(20);
				player.getInventory().clear();
				player.setGameMode(GameMode.ADVENTURE);
				ChessUHC.listPlayer.addPlayer(player, false, false);
				Bukkit.broadcastMessage("§e"+ player.getName() + " §2viens de rejoindre la parties");
				player.teleport(new Location(Bukkit.getWorld("world"), ChessUHC.x, ChessUHC.y, ChessUHC.z));
			}else {
				ChessUHC.listPlayer.popPlayer(player, false, false);
				Bukkit.broadcastMessage("§e" + player.getName() + " §2est revenus dans la game");
				ChessUHC.listPlayer.addPlayer(player, false, false);
			}
		}else if(ChessUHC.listPlayer.inList(player)){
			
			Bukkit.broadcastMessage("§e" + player.getName() + " §2est revenus dans la game");
			player.setGameMode(GameMode.SURVIVAL);
		}else {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage("§4Vous etes mit en spectateur");
		}
		
		event.setJoinMessage("");
		
	}
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		
		if(ChessUHC.current != ChessGame.GAME){
			event.setCancelled(true);
		}else if((ChessUHC.Timer().getMinute() < 2 && ChessUHC.Timer().getJour() == 1)) {
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
			}
		}else {		
			if(event.getEntity() instanceof Player){
			
				Player player = (Player)event.getEntity();
				Player wasKill = null;
				if(player.getLastDamageCause() instanceof Player) {
					wasKill = (Player) player.getLastDamageCause();
				}
				if(player.getHealth() <= 8) {
					Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(player));
					if(p.getRole().equals("Rois")){
						Bukkit.broadcastMessage("§4le Roi §e"+ p.getTeams()  +" §4est en échec");
						for(Players play : p.getEquipeList().listPlayer()) {
							play.getPlayer().sendMessage("§4votre roi ce trouve : §e" + TitleDirection.position(p.getPlayer()));
						}
					}
				}
				if(player.getHealth() <= event.getDamage()){
					event.setDamage(0);
					respawn(player, wasKill, "PVE");
					popPlayerList(player);
				}
			}
		}
		
	}
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
	    Player p = event.getPlayer();
	    ItemStack item = null;
	    for(Players p1 : ChessUHC.listPlayer.listPlayer()) {
	    	if(p1.getPlayer().equals(p)) {
	    		if(p1.getRole() != null) {
	    			item = p1.getRole().getitem();
	    		}
	    	}
	    }
	    if(p.getItemInHand().equals(item)) {
	    	p.sendMessage("action deux enregistrer");
	    }
	    


	}
    @EventHandler
    public void onTestEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && ChessUHC.pvpOff){
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }
	
	@EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
    	Player p = event.getPlayer();
    	if(ChessUHC.Timer().getJour() > 1 && ChessUHC.chatOff) {
    		p.sendMessage("§4vous n'avez pas le droit de parler dans le chat ");
    		event.setCancelled(true);
    	}
    }
	public void respawn(Player player, Player playerWasKill, String mort) {
		player.setHealth(20D);
		player.setFoodLevel(20);
		PlayerInventory inv = player.getInventory();
		Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(player));
		Role role = p.getRole();

		for(ItemStack item : inv.getContents()) {
			if(item != null && item.getTypeId() != role.getitem().getTypeId()) {
				ChessUHC.world.dropItem(player.getLocation(), item);
			}
		}
		player.getInventory().clear();
	
		player.setGameMode(GameMode.SPECTATOR);
		if(ChessUHC.DeathMessage) {
			if(ChessUHC.Timer().getJour() > 1) {
				if(playerWasKill != null) {
					deathMessage(player, playerWasKill);
				}else {
					deathMessage(player, mort);
				}
			}else {
				deathMessageNoRole(player, mort);
			}
		}
	}
	
	private void popPlayerList(Player player) {
		if(ChessUHC.listPlayer.inList(player)) {
			int indiceP = ChessUHC.listPlayer.indicePlayer(player);
			ChessUHC.listPlayer.popPlayer(player, false, false);
			if(ChessUHC.EquipeBlanc.inList(indiceP)) {
				ChessUHC.EquipeBlanc.popPlayer(indiceP);
			}else if(ChessUHC.EquipeNoir.inList(indiceP)) {
				ChessUHC.EquipeNoir.popPlayer(indiceP);
			}
			

			String Vainqueur = ChekWin();
			if(!Vainqueur.equals("")) {
				Win(Vainqueur);
			}
		}else {
			System.out.println("le joueur n'est pas dans la liste");
		}
	}
	
	public String ChekWin() {
		CustomScoreboardManager.CustomScoreboardDisplay(ChessUHC.Timer());
		if(ChessUHC.EquipeBlanc.nbPlayer() == 0 && ChessUHC.EquipeNoir.nbPlayer() == 0) return "egaliter";
		if(ChessUHC.EquipeBlanc.nbPlayer() == 0)return ChessUHC.EquipeBlanc.getNom();
		if(ChessUHC.EquipeNoir.nbPlayer() == 0)return ChessUHC.EquipeNoir.getNom();
		return "";
	}
	
	public void Win(String equipeWin) {
		ChessUHC.current = ChessGame.FINISH;
		for(Player player : Bukkit.getOnlinePlayers()) {
			Title.sendTitle(player.getPlayer(), "La partie est fini", "vainqueur :" + equipeWin, 25);
			player.setGameMode(GameMode.CREATIVE);
		}
		Bukkit.broadcastMessage("l'equipe " + equipeWin + " a gagner");
	}
	public void deathMessageNoRole(Player pl, String raison) {
		Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(pl));
		Bukkit.broadcastMessage("§2§l=========================================");
		Bukkit.broadcastMessage("§4"+ "Le joueur : §2" + pl.getName() + "§4 de lequipe : §2" + p.getTeams());;
		Bukkit.broadcastMessage("§4"+ "il mort de : §2" + raison);
		Bukkit.broadcastMessage("§2§l=========================================");
	}
	public void deathMessage(Player pl, String raison) {
		Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(pl));
		Role role = p.getRole();
		Bukkit.broadcastMessage("§2§l=========================================");
		Bukkit.broadcastMessage("§4"+ "Le joueur : §2" + pl.getName() + "§4 de lequipe : §2" + p.getTeams());
		Bukkit.broadcastMessage("§4"+ "il etait : §2" + role.getNom());
		Bukkit.broadcastMessage("§4"+ "il mort de : §2" + raison);
		role.roleInfoDeath();
		Bukkit.broadcastMessage("§2§l=========================================");
	}
	public void deathMessage(Player pl, Player murder) {
		Players p = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(pl));
		Role role = p.getRole();
		Players pMurder = ChessUHC.listPlayer.getPlayer(ChessUHC.listPlayer.indicePlayer(pl));
		Role roleMurder = pMurder.getRole();
		Bukkit.broadcastMessage("§2§l=========================================");
		Bukkit.broadcastMessage("§4"+ "§2"+ roleMurder.getNom() +"§4prend" + role.getNom() + "en" + TitleDirection.position(p.getPlayer()));
		Bukkit.broadcastMessage("§4"+ "c'etait le joueur : §2" + pl.getName() + "§4 de lequipe : §2" + p.getTeams());
		role.roleInfoDeath();
		Bukkit.broadcastMessage("§2§l=========================================");
	}
	
}
