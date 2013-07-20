package net.evolutionarygames.BunchaStuff;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;

public class BSCommandExecutor implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private BunchaStuff plugin;

	public BSCommandExecutor(BunchaStuff plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!sender.hasPermission("BunchaStuff.*")){
			sender.sendMessage("You do not have access to this command");
			return false;
		}
		if(!(sender instanceof Player)){ //TODO make it so that the console can't use the no arg commands.
			sender.sendMessage("You do not have this ability. Please use <command> [player]");
			return false;
		}
		else{
			Player player = (Player) sender;
			Location playerLoc = player.getLocation();
			World playerWorld = player.getWorld();
			//Checking the argument length. If there are no arguments, the command will be executed on the player
			//if there is one argument, the command will be executed on the player in the argument
			switch(args.length){
			case 0:
				//now check for the command. There must be an easier way to do this, but I can't think of it now.
				//TODO Rework so case 0 and case 1 are not copy/pastes of each other
				if(cmd.getName().equalsIgnoreCase("heal")){
					player.setHealth(20.0F);
					player.sendMessage("Healed " + player.getDisplayName());
				}
				else if(cmd.getName().equalsIgnoreCase("feed")){
					player.setFoodLevel(20);
					player.sendMessage("Fed " + player.getDisplayName());
				}
				else if(cmd.getName().equalsIgnoreCase("strike")){
					playerWorld.strikeLightning(playerLoc);
					player.sendMessage("Struck " + player.getDisplayName() + " with lightning");
				}
				else if(cmd.getName().equalsIgnoreCase("Smite")){
					player.setHealth(0.0F);
					player.sendMessage("Killed " + player.getDisplayName());
				}
				else if(cmd.getName().equalsIgnoreCase("ignite")){
					player.setFireTicks(100);
					player.sendMessage("Set " + player.getDisplayName() + " on fire!");
				}
				else if(cmd.getName().equalsIgnoreCase("gms")){
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage("Changed gamemode to Survival");
				}
				else if(cmd.getName().equalsIgnoreCase("gmc")){
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage("Changed gamemode to Creative");
				}
				else if(cmd.getName().equalsIgnoreCase("gma")){
					player.setGameMode(GameMode.ADVENTURE);
					player.sendMessage("Changed gamemode to Adventure");
				}
			break;
			case 1: 
				player = sender.getServer().getPlayer(args[0]);
				if(player == null){ //checking that the player is online or correctly entered
					sender.sendMessage("That player is not online or cannot be found");
					return true;
				}
				if(cmd.getName().equalsIgnoreCase("heal")){
					player.setHealth(20.0F);
					player.sendMessage(sender.getName() + " healed " + player.getDisplayName());
				}
				else if(cmd.getName().equalsIgnoreCase("feed")){
					player.setFoodLevel(20);
					player.sendMessage(sender.getName() + " fed " + player.getDisplayName());
				}
				else if(cmd.getName().equalsIgnoreCase("strike")){
					playerWorld.strikeLightning(playerLoc);
					player.sendMessage(sender.getName() + " struck " + player.getDisplayName() + " with lightning");
				}
				else if(cmd.getName().equalsIgnoreCase("Smite")){
					player.setHealth(0.0F);
					player.sendMessage(sender.getName() + " killed " + player.getDisplayName() + " with smite");
				}
				else if(cmd.getName().equalsIgnoreCase("ignite")){
					player.setFireTicks(100);
					player.sendMessage(sender.getName() + " set " + player.getDisplayName() + " on fire");
				}
				else if(cmd.getName().equalsIgnoreCase("gms")){
					player.setGameMode(GameMode.SURVIVAL);
				}
				else if(cmd.getName().equalsIgnoreCase("gmc")){
					player.setGameMode(GameMode.CREATIVE);
				}
				else if(cmd.getName().equalsIgnoreCase("gma")){
					player.setGameMode(GameMode.ADVENTURE);
				}break;
			}
		}
		return true;
	}
}
