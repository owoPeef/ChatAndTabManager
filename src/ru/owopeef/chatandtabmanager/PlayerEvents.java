package ru.owopeef.chatandtabmanager;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import ru.owopeef.chatandtabmanager.utils.Config;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.events.PermissionEntityEvent;

import java.util.List;

public class PlayerEvents implements Listener
{
    Plugin plugin = Main.getPlugin(Main.class);
    boolean isGlobalEnabled;
    @EventHandler
    public void onDonateChange(PermissionEntityEvent event) {
        if (event.getAction() == PermissionEntityEvent.Action.INHERITANCE_CHANGED) {
            Player player = Bukkit.getPlayer(event.getEntity().getName());
            PermissionUser user = PermissionsEx.getUser(player);
            String format = Config.readConfig("playerTabFormat");
            format = format.replace("&", "§").replace("{suffix}", user.getSuffix().replace("&", "§")).replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_nick}", player.getName());
            player.setPlayerListName(format);
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PermissionUser user = PermissionsEx.getUser(event.getPlayer());

        String joinMessage = Config.readConfig("joinMessage");

        TextComponent message = new TextComponent("{prefix}{player_nick}".replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_nick}", event.getPlayer().getName()));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/profile {player_nick}".replace("{player_nick}", event.getPlayer().getName())));

        String first_line = "{prefix}{player_nick}".replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_nick}", event.getPlayer().getName());
        String second_line = "§7Уровень: §60".replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_nick}", event.getPlayer().getName());
        String txt = first_line + "\n" + second_line;

        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(txt).create()));

        TextComponent text = new TextComponent(joinMessage.replace("&", "§").replace("{suffix}", user.getSuffix().replace("&", "§")).replace("{prefix}", "").replace("{player_nick}", ""));
        Bukkit.spigot().broadcast(message, text);
        event.setJoinMessage("");

        String format = Config.readConfig("playerTabFormat");
        format = format.replace("&", "§").replace("{suffix}", user.getSuffix().replace("&", "§")).replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_nick}", event.getPlayer().getName());
        event.getPlayer().setPlayerListName(format);
    }
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        PermissionUser user = PermissionsEx.getUser(event.getPlayer());
        boolean playerHear = false;
        isGlobalEnabled = Config.readConfigBoolean("isGlobalEnabled");
        String player_message = event.getMessage();
        Player player = event.getPlayer();
        String player_username = player.getDisplayName();
        String prefix = Config.readConfig("globalPrefix");
        String message;
        event.setCancelled(true);
        if (!isGlobalEnabled)
        {
            String format = Config.readConfig("chat");
            message = format.replace("&", "§").replace("{suffix}", user.getSuffix().replace("&", "§")).replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_message}", player_message).replace("{player_nick}", player_username);
            plugin.getServer().broadcastMessage(message);
        }
        else
        {
            if (player_message.startsWith(prefix))
            {
                player_message = player_message.substring(prefix.length());
                String format = Config.readConfig("globalChat");
                message = format.replace("&", "§").replace("{suffix}", user.getSuffix().replace("&", "§")).replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_message}", player_message).replace("{player_nick}", player_username);
                plugin.getServer().broadcastMessage(message);
            }
            else
            {
                String format = Config.readConfig("localChat");
                String noOneHeard = Config.readConfig("ifNoOneHeardTheMessage");
                message = format.replace("&", "§").replace("{suffix}", user.getSuffix().replace("&", "§")).replace("{prefix}", user.getPrefix().replace("&", "§")).replace("{player_message}", player_message).replace("{player_nick}", player_username);
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