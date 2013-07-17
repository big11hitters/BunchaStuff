package net.evolutionarygames.BunchaStuff;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
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
		if(player.getDisplayName().equalsIgnoreCase("big11hitters") && !playerInv.contains(flameThrower)){
			playerInv.addItem(flameThrower);
			ItemMeta ftMeta = flameThrower.getItemMeta();
			ftMeta.setDisplayName(ChatColor.GOLD + "Flame Thrower");
		}
		
	} 
	@EventHandler
	public void OnPlayerInteract(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if (player.getItemInHand().getTypeId() == Material.BLAZE_POWDER.getId()){
	    	if(player.hasPermission("BunchaStuff.flameThrower")){
	    		if(event.getAction().equals(Action.LEFT_CLICK_AIR))
	    			player.launchProjectile(SmallFireball.class);
	    	}
	    	else
	    		player.sendMessage("You do not have permission to use this item");
	    }
	    else if(player.getItemInHand().getTypeId() == Material.BLAZE_ROD.getId()){
	    	if(player.hasPermission("BunchaStuff.explosionCharge")){
	    		if(event.getAction().equals(Action.LEFT_CLICK_AIR))
	    			player.launchProjectile(LargeFireball.class);
	    	}
	    	else
	    		player.sendMessage("You do not have permission to use this item");
	    }	

	}

	@EventHandler //this currently doesn't affect anything. I believe I need ProjectileLaunchEvent before this method
	public void OnProjectileHit(ProjectileHitEvent evt){
		Projectile proj = evt.getEntity();
		Location projLoc = proj.getLocation();
		if(proj.equals(SmallFireball.class)){
			projLoc.getBlock().setType(Material.ANVIL);
		}
		else if(proj.equals(LargeFireball.class)){
			projLoc.getWorld().createExplosion(projLoc.getBlock().getLocation(), 20.0F);
		}
	}
}
