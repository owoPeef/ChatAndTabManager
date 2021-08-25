package ru.owopeef.owochat;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owochat.utils.Config;

import java.util.List;

public class PlayerEvents implements Listener
{
    Plugin plugin = Main.getPlugin(Main.class);
    boolean isGlobalEnabled;
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event)
    {
        boolean playerHear = false;
        isGlobalEnabled = Config.readConfigBoolean("isGlobalEnabled");
        String player_message = event.getMessage();
        Player player = event.getPlayer();
        String player_username = player.getName();
        String prefix = Config.readConfig("globalPrefix");
        String message;
        event.setCancelled(true);
        if (!isGlobalEnabled)
        {
            String format = Config.readConfig("chat");
            message = format.replace("&", "§").replace("{player_message}", player_message).replace("{player_nick}", player_username);
            plugin.getServer().broadcastMessage(message);
        }
        else
        {
            if (player_message.startsWith(prefix))
            {
                player_message = player_message.substring(prefix.length());
                String format = Config.readConfig("globalChat");
                message = format.replace("&", "§").replace("{player_message}", player_message).replace("{player_nick}", player_username);
                plugin.getServer().broadcastMessage(message);
            }
            else
            {
                String format = Config.readConfig("localChat");
                String noOneHeard = Config.readConfig("ifNoOneHeardTheMessage");
                message = format.replace("&", "§").replace("{player_message}", player_message).replace("{player_nick}", player_username);
                List<Player> players = plugin.getServer().getWorld("world").getPlayers();
                if (players.size() == 1)
                {
                    player.sendMessage(noOneHeard);
                }
                else
                {
                    int a = 0;
                    while (a != players.size())
                    {
                        // Current player
                        Player currentPlayer = players.get(a);
                        Location currentLoc = currentPlayer.getLocation();
                        int currentPlayerX = currentLoc.getBlockX();
                        int currentPlayerZ = currentLoc.getBlockZ();
                        // Message sender
                        Location playerLoc = player.getLocation();
                        int playerX = playerLoc.getBlockX();
                        int playerZ = playerLoc.getBlockZ();

                        if (currentPlayerX - playerX == Config.readConfigInteger("localRadius") || playerX - currentPlayerX == Config.readConfigInteger("localRadius") || currentPlayerZ - playerZ == Config.readConfigInteger("localRadius") || playerZ - currentPlayerZ == Config.readConfigInteger("localRadius"))
                        {
                            currentPlayer.sendMessage(message);
                            playerHear = true;
                        }
                        a++;
                    }
                    if (playerHear)
                    {
                        player.sendMessage(message);
                    }
                    else
                    {
                        player.sendMessage(noOneHeard);
                    }
                }
            }
        }
    }
}
