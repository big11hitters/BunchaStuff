package net.evolutionarygames.BunchaStuff;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;

public class BSCommandExecutor implements CommandExecutor {

	@SuppressWarnings("unused") //used in Constructor. Not sure why the warning
	private BunchaStuff plugin;
	
	public BSCommandExecutor(BunchaStuff plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length!=1){ //checking the amount of arguments
			sender.sendMessage("Too little arguments");
			return true; 
		}
		else{ //if all the arguments are there, get the player's name, and check if he/she is online
			Player player = sender.getServer().getPlayer(args[0]);
			if(player == null){
				sender.sendMessage("That player is not online or cannot be found");
				return true;
			}
			else if(player.isOp()){//Check if player is OP. Get player's location and world
				Location playerLoc = player.getLocation();
				World playerWorld = player.getWorld();

				//Check the commands
				if(cmd.getName().equalsIgnoreCase("heal")){
					player.setHealth(20.0F);
				}
				else if(cmd.getName().equalsIgnoreCase("strike")){
					playerWorld.strikeLightning(playerLoc);
				}
				else if(cmd.getName().equalsIgnoreCase("Smite")){
					player.setHealth(0.0F);
				}
				else if(cmd.getName().equalsIgnoreCase("ignite")){
					player.setFireTicks(100);
				}
				else if(cmd.getName().equalsIgnoreCase("gms")){
					player.setGameMode(GameMode.SURVIVAL);
				}
				else if(cmd.getName().equalsIgnoreCase("gmc")){
					player.setGameMode(GameMode.CREATIVE);
				}
				else if(cmd.getName().equalsIgnoreCase("gma")){
					player.setGameMode(GameMode.ADVENTURE);
				}
			}
			else{
				sender.sendMessage("You do not have access to this command");
			}
		}
		return true;
	}
	
}
