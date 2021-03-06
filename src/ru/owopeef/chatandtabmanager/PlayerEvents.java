package ru.owopeef.chatandtabmanager;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import ru.owopeef.chatandtabmanager.utils.Config;
import ru.owopeef.chatandtabmanager.utils.Messages;
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
            player.setPlayerListName(Messages.formatMessage(Config.readConfig("playerTabFormat"), user));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PermissionUser user = PermissionsEx.getUser(event.getPlayer());
        boolean hasJoinPermission = user.getPlayer().hasPermission("chatandtabmanager.join_notify");
        boolean isChatClickable = Config.readConfigBoolean("isChatClickable");
        boolean isChatHover = Config.readConfigBoolean("isChatHover");
        boolean joinNotify = Config.readConfigBoolean("joinNotificationsEnabled");
        boolean joinNotifyDefault = Config.readConfigBoolean("joinNotificationsDefault");
        String joinMessage = Config.readConfig("joinMessage");

        if (!joinNotifyDefault) {
            if (hasJoinPermission && joinNotify && !isChatClickable && !isChatHover) {
                event.setJoinMessage(Messages.formatMessage(joinMessage, true, user));
            }

            if (hasJoinPermission && joinNotify && isChatClickable && !isChatHover) {
                TextComponent msg = new TextComponent(Messages.formatMessage(joinMessage, true, user));
                int clickableType = Config.readConfigInteger("clickableType");
                if (clickableType == 1) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 2) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 3) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                }

                Bukkit.spigot().broadcast(msg);
                event.setJoinMessage("");
            }

            if (hasJoinPermission && joinNotify && isChatClickable && isChatHover) {
                TextComponent msg = new TextComponent(Messages.formatMessage(joinMessage, true, user));
                int clickableType = Config.readConfigInteger("clickableType");
                if (clickableType == 1) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 2) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 3) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                }

                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));

                Bukkit.spigot().broadcast(msg);
                event.setJoinMessage("");
            }

            if (hasJoinPermission && joinNotify && !isChatClickable && isChatHover) {
                TextComponent msg = new TextComponent(Messages.formatMessage(joinMessage, true, user));

                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));

                Bukkit.spigot().broadcast(msg);
                event.setJoinMessage("");
            }

            if (!hasJoinPermission && joinNotify) {
                event.setJoinMessage("");
            }
        } else {
            if (joinNotify && !isChatClickable && !isChatHover) {
                event.setJoinMessage(Messages.formatMessage(joinMessage, true, user));
            }

            if (joinNotify && isChatClickable && !isChatHover) {
                TextComponent msg = new TextComponent(Messages.formatMessage(joinMessage, true, user));
                int clickableType = Config.readConfigInteger("clickableType");
                if (clickableType == 1) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 2) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 3) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                }

                Bukkit.spigot().broadcast(msg);
                event.setJoinMessage("");
            }

            if (joinNotify && isChatClickable && isChatHover) {
                TextComponent msg = new TextComponent(Messages.formatMessage(joinMessage, true, user));
                int clickableType = Config.readConfigInteger("clickableType");
                if (clickableType == 1) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 2) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 3) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                }

                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));

                Bukkit.spigot().broadcast(msg);
                event.setJoinMessage("");
            }

            if (joinNotify && !isChatClickable && isChatHover) {
                TextComponent msg = new TextComponent(Messages.formatMessage(joinMessage, true, user));

                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));

                Bukkit.spigot().broadcast(msg);
                event.setJoinMessage("");
            }

            if (joinNotify) {
                event.setJoinMessage("");
            }
        }

        event.getPlayer().setPlayerListName(Messages.formatMessage(Config.readConfig("playerTabFormat"), user));
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        PermissionUser user = PermissionsEx.getUser(event.getPlayer());
        boolean playerHear = false;
        isGlobalEnabled = Config.readConfigBoolean("isGlobalEnabled");
        String player_message = event.getMessage();
        if (user.has("chatandtabmanager.smiles_chat")) {
            player_message = player_message.replace("<3", ChatColor.RED + "???");
            player_message = player_message.replace(":))", ChatColor.GOLD + "(??? ??? ???)???");
            player_message = player_message.replace("->", "???");
            player_message = player_message.replace("<-", "???");
            player_message = player_message.replace("o/", "(??? ??? ???)???");
        }
        Player player = event.getPlayer();
        String prefix = Config.readConfig("globalPrefix");
        String message;
        event.setCancelled(true);
        boolean isChatClickable = Config.readConfigBoolean("isChatClickable");
        if (!isGlobalEnabled)
        {
            String format = Config.readConfig("chat");
            if (isChatClickable) {
                TextComponent msg = new TextComponent(Messages.formatMessage(format, player_message, user));
                int clickableType = Config.readConfigInteger("clickableType");
                if (clickableType == 1) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 2) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                } else if (clickableType == 3) {
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                }

                if (Config.readConfigBoolean("isChatHover")) {
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));
                }

                Bukkit.spigot().broadcast(msg);
            } else {
                if (Config.readConfigBoolean("isChatHover")) {
                    TextComponent msg = new TextComponent(Messages.formatMessage(format, player_message, user));

                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));

                    Bukkit.spigot().broadcast(msg);
                } else {
                    plugin.getServer().broadcastMessage(Messages.formatMessage(format, player_message, user));
                }
            }
        }
        else
        {
            if (player_message.startsWith(prefix))
            {
                player_message = player_message.substring(prefix.length());
                if (Config.readConfigBoolean("isChatClickable")) {
                    TextComponent msg = new TextComponent(Messages.formatMessage(Config.readConfig("globalChat"), player_message, user));
                    int clickableType = Config.readConfigInteger("clickableType");
                    if (clickableType == 1) {
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                    } else if (clickableType == 2) {
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                    } else if (clickableType == 3) {
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Messages.formatMessage(Config.readConfig("clickableValue"), user)));
                    }

                    if (Config.readConfigBoolean("isChatHover")) {
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));
                    }

                    Bukkit.spigot().broadcast(msg);
                } else {
                    if (Config.readConfigBoolean("isChatHover")) {
                        TextComponent msg = new TextComponent(Messages.formatMessage(Config.readConfig("globalChat"), player_message, user));

                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Messages.formatMessage(Config.readConfig("hoverMessage"), true, user)).create()));

                        Bukkit.spigot().broadcast(msg);
                    } else {
                        plugin.getServer().broadcastMessage(Messages.formatMessage(Config.readConfig("globalChat"), player_message, user));
                    }
                }
            }
            else
            {
                String noOneHeard = Config.readConfig("ifNoOneHeardTheMessage");
                message = Messages.formatMessage(Config.readConfig("localChat"), player_message, user);
                List<Player> players = plugin.getServer().getWorlds().get(0).getPlayers();
                if (players.size() == 1)
                {
                    player.sendMessage(noOneHeard);
                }
                else
                {
                    int a = 0;
                    while (a != players.size())
                    {
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
