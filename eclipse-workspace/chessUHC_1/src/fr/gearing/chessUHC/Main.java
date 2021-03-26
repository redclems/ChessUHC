package fr.gearing.chessUHC;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static PlayerList listPlayer;
	public static PlayerList specPlayer;
	public static ChessGame current;
	public static Equipe EquipeNoir;
	public static Equipe EquipeBlanc; 
	public static boolean borderMouve = false;
	public static boolean stop = false;
	public static Timer task;
	public static boolean viewTeams;
	public static boolean coView;
	public static boolean chatOff;
	public static boolean pvpOff = true;
	public static boolean DeathMessage;
	
	public static double x;
	public static double y;
	public static double z;
	public static int tailleCase;
	public static int groupeTaille;
	public static World world;

	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		System.out.println("le plugin chessUHC is Enable");
		current = ChessGame.LOBBY;
		getServer().getPluginManager().registerEvents(new ChessUHCListener(), this);
		listPlayer = new PlayerList("listPlayer");
		specPlayer = new PlayerList("listSpec");
		EquipeNoir = new Equipe("noire", "§f[§8noire§f]§8");
		EquipeBlanc = new Equipe("blanc", "§f[§fblanc§f]§f");
		
		getCommand("uhcstart").setExecutor(new CommandStart());
		getCommand("uhcstop").setExecutor(new CommandStop());
		getCommand("info").setExecutor(new CommandInfo());
		getCommand("uhcplayerList").setExecutor(new CommandPlayer());
		getCommand("uhcteams").setExecutor(new CommandEquipe());
		getCommand("uhcborder").setExecutor(new CommandBorder());
		getCommand("uhcrole").setExecutor(new CommandRole());
		getCommand("uhcsetday").setExecutor(new CommandSetDay());
		getCommand("uhcgroupetaille").setExecutor(new CommandGroupeTaille());
		
		viewTeams = this.getConfig().getBoolean("gameplay.teamView");
		coView = this.getConfig().getBoolean("gameplay.coordoneeView");
		chatOff = this.getConfig().getBoolean("gameplay.chatOff");
		x = this.getConfig().getInt("world.spawnX");
		y = this.getConfig().getInt("world.spawnY");
		z = this.getConfig().getInt("world.spawnZ");
		tailleCase = this.getConfig().getInt("plateau.tailleCase");
		groupeTaille = this.getConfig().getInt("gameplay.groupTaille");
		DeathMessage = this.getConfig().getBoolean("gameplay.DeathMessage");

		CreateSpawns.spawn();
		task = new Timer(this);
		task.runTaskTimer(this, 0, 20);//timer
		
		world = Bukkit.getWorld("world");//world non de la map
		WorldBorder wb = world.getWorldBorder();//set the border at position
		WorlBorderMouv mouvBorder = new WorlBorderMouv(world, wb, this);
		mouvBorder.runTaskTimer(this, 0, 20); ///mouv the border
		world.setGameRuleValue("reducedDebugInfo", ""+coView);
		world.setGameRuleValue("naturalRegeneration", ""+this.getConfig().getBoolean("gameplay.naturalRegeneration"));

	}
	@Override
	public void onDisable() {
		System.out.println("le plugin chessUHC is Disable");
		
	}
	
	public static Timer Timer() {
		return task;
	}
	public static void setPVP(boolean pvp) {
		pvpOff = pvp;
		Bukkit.broadcastMessage("§4Le PVP est activer");
	}
	
	

}
