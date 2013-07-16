package net.evolutionarygames.BunchaStuff;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
//import org.bukkit.event.block.Action;
//import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BSCustomItemListener implements Listener {
	@SuppressWarnings("unused")
	private BunchaStuff plugin;
	
	public BSCustomItemListener(BunchaStuff plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt){
		Player player = evt.getPlayer();
		PlayerInventory playerInv = player.getInventory();
		ItemStack flameThrower = new ItemStack(Material.BLAZE_POWDER);
		flameThrower.addUnsafeEnchantment(Enchantment.ARROW_FIRE,1);
		ItemMeta ftMeta = flameThrower.getItemMeta();
		ftMeta.setDisplayName(ChatColor.GOLD + "Flame Thrower");
		if(player.getDisplayName().equalsIgnoreCase("big11hitters") && !playerInv.contains(flameThrower)){
			playerInv.addItem(flameThrower);
		}
		
	}
	//TODO Fix the unhandled Exception when the player clicks a block or air with no items in their hand
	/*@EventHandler
	public void onInteract(PlayerInteractEvent evt){
		Player player = evt.getPlayer();
		if(evt.getItem().equals(Material.BLAZE_POWDER)){
			if(evt.getAction().equals(Action.LEFT_CLICK_AIR)){
				player.sendMessage("You clicked with the Flame Thrower");
			}
			else if(evt.getAction().equals(Action.LEFT_CLICK_BLOCK)){
				player.sendMessage("You clicked a block");
			}
		}
	}*/
}
