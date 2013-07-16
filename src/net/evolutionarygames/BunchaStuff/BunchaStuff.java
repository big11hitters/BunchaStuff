package net.evolutionarygames.BunchaStuff;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
//import org.bukkit.Server;

public final class BunchaStuff extends JavaPlugin {
	public String[] cmdNames = {"gms","gmc","gma","strike","smite","ignite","heal"};
	@Override
	public void onEnable(){
		PluginManager pm = this.getServer().getPluginManager();
		//Server server = getServer();
		for(int i = 0; i < cmdNames.length; i++){
			getCommand(cmdNames[i]).setExecutor(new BSCommandExecutor(this));
		}
		pm.registerEvents(new BSCustomItemListener(this),this);
	}
	@Override
	public void onDisable(){
		
	}
}
