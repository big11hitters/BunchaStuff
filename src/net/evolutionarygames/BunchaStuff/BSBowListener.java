package net.evolutionarygames.BunchaStuff;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BSBowListener implements Listener {
	@SuppressWarnings("unused")
	private BunchaStuff plugin;
	
	public BSBowListener(BunchaStuff plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void onArrowHit(ProjectileHitEvent evt){
		if(evt.getEntity() instanceof Arrow){
			Arrow arrow = (Arrow) evt.getEntity();
			
			if(arrow.getShooter() instanceof Player){//if the player is the one shooting the arrow
				Player shooter = (Player) arrow.getShooter(); //get the player
				ItemStack item = shooter.getItemInHand(); //get the item in the player's hand
				ItemMeta itemMeta = item.getItemMeta();   //get the Item Meta for the item
				//Get the arrow's final location and set the yaw and pitch so the player will face the direction
				//upon teleportation they faced when they shot the arrow
				Location arrowLoc = arrow.getLocation();  
				arrowLoc.setPitch(shooter.getLocation().getPitch());
				arrowLoc.setYaw(shooter.getLocation().getYaw());
				if(item.getTypeId() == Material.BOW.getId()){
					switch(itemMeta.getDisplayName()){
					case "Ender Bow": 
						shooter.damage(2.0F);
						shooter.teleport(arrowLoc); break;
					case "Explosive Bow": 
						shooter.getWorld().createExplosion(arrowLoc, 4.0F); break;
					case "Lightning Bow":
						shooter.getWorld().strikeLightning(arrowLoc); break;
					default: 
						shooter.launchProjectile(Arrow.class); break;
					}
				}
			}
		}
		
	}
}
