package net.evolutionarygames.BunchaStuff;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
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
				String bowName = itemMeta.getDisplayName();
				if(bowName.equalsIgnoreCase("Ender Bow")){
					shooter.setFallDistance(4.0F);
					arrow.setBounce(false);
					shooter.teleport(arrowLoc); 
					arrow.remove();
				}
				else if(bowName.equalsIgnoreCase("Explosive Bow")){
					arrow.setBounce(false);
					shooter.getWorld().createExplosion(arrowLoc, 4.0F);
					arrow.remove();
				}
				else if(bowName.equalsIgnoreCase("Lightning Bow")){
					arrow.setBounce(false);
					shooter.getWorld().strikeLightning(arrowLoc);
					arrow.remove();
				}
				else{
					shooter.getWorld().spawnArrow(arrowLoc, arrow.getVelocity(), 4F, 0.6F);
				}
				
			}
		}
		
	}
}
