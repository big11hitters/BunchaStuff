package net.evolutionarygames.BunchaStuff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener{
	private BunchaStuff plugin;
	
	public BlockBreak(BunchaStuff plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent evt){
		int blockID = evt.getBlock().getTypeId();
		switch(blockID){
		case 16: //coal ore
			evt.getBlock().getLocation().getWorld().dropItem(evt.getBlock().getLocation(),new ItemStack(plugin.getConfig().getInt("CoalOre")));
			break;
		case 15: //iron ore
			evt.getBlock().getLocation().getWorld().dropItem(evt.getBlock().getLocation(),new ItemStack(plugin.getConfig().getInt("IronOre")));
			break;
		case 13: //gravel
			evt.getBlock().getLocation().getWorld().dropItem(evt.getBlock().getLocation(),new ItemStack(plugin.getConfig().getInt("Gravel")));
			break;
		}
	}
}
