package fr.gearing.chessUHC.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class mob {
	public static void spawnZombie(Location loc) {

	      	@SuppressWarnings("deprecation")
			Zombie sk = (Zombie) ChessUHC.world.spawnCreature(loc, EntityType.ZOMBIE);

	        double health = 20;
	        String name = "Cavalier";
	        ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
	        ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
	        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
	        ItemStack legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
	        ItemStack iteminhand = new ItemStack(Material.GOLD_SWORD);
	        boolean canpickupitems = false;
	/*************************************************************/
	        sk.getEquipment().setBoots(boots);
	        sk.getEquipment().setHelmet(helmet);
	        sk.getEquipment().setChestplate(chestplate);
	        sk.getEquipment().setLeggings(legs);
	        sk.getEquipment().setItemInHand(iteminhand);
	        sk.setMaxHealth((int)health);
	        sk.setHealth((int)health);
	        sk.setCanPickupItems(canpickupitems);
	        sk.setCustomName(name);
	        sk.isCustomNameVisible();
	       
	        sk.getEquipment().setBootsDropChance((float)0);
	        sk.getEquipment().setHelmetDropChance((float)0);
	        sk.getEquipment().setChestplateDropChance((float)0);
	        sk.getEquipment().setLeggingsDropChance((float)0);
	        sk.getEquipment().setItemInHandDropChance((float)0);
	}
}
