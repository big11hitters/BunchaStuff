package net.evolutionarygames.BunchaStuff;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public final class BunchaStuff extends JavaPlugin {
	public String[] cmdNames = {"gms","gmc","gma","strike","smite","ignite","heal","feed"};
	public PluginDescriptionFile p = this.getDescription();
	@Override
	public void onEnable(){
		//getLogger().info(p.getName() + "v" +p.getVersion() + " has been enabled");
		getConfig().options().copyDefaults(true);
		saveConfig();
		this.registerEvents();
		this.executeCommands();
	}
	@Override
	public void onDisable(){
		//getLogger().info(p.getName() + "v" +p.getVersion() + " has been disabled");
	}
	public void registerEvents(){
		PluginManager pm = this.getServer().getPluginManager();
		if(this.getConfig().getString("CustomItems").equals("true"))
			pm.registerEvents(new BSCustomItemListener(this),this);
		if(this.getConfig().getString("CustomBows").equals("true"))
			pm.registerEvents(new BSBowListener(this),this);
		pm.registerEvents(new BlockBreak(this), this);
	}
	public void executeCommands(){
		for(int i = 0; i < cmdNames.length; i++){
			getCommand(cmdNames[i]).setExecutor(new BSCommandExecutor(this));
		}
	}
}
