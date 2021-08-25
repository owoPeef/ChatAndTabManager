package ru.owopeef.owochat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owochat.utils.Config;

public class PlayerEvents implements Listener
{
    Plugin plugin = Main.getPlugin(Main.class);
    boolean isGlobalEnabled = Config.readConfigBoolean("isGlobalEnabled");
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event)
    {
        String player_message = event.getMessage();
        String player_username = event.getPlayer().getName();
        String message;
        if (!isGlobalEnabled)
        {
            message = "§a{"+player_username+"}: §f" + player_message;
            event.setMessage(message);
        }
        else
        {
            if (player_message.startsWith("!"))
            {
                message = "[G] {"+player_username+"}: " + player_message;
                event.setMessage(message);
            }
            else
            {
                event.setCancelled(true);
                message = "[L] {"+player_username+"}: " + player_message;
                plugin.getLogger().info(message);
                //event.setMessage(message);
            }
        }
    }
}
