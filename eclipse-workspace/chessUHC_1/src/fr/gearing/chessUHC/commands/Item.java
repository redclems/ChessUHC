package fr.gearing.chessUHC.commands;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.gearing.chessUHC.Players;

public class Item {
		
	public static ItemStack ItemRole(Players player) {

      	ItemStack item = new ItemStack(Material.BOOK);
      	ItemMeta meta = item.getItemMeta();
      	meta.setDisplayName(player.getRole().getNom());
      	meta.addEnchant(Enchantment.DURABILITY, 1000, true);
      	item.setItemMeta(meta);
      	
      	return item;
	}
}
