package ru.owopeef.chatandtabmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Objects;

public class Commands implements CommandExecutor {
    public static Plugin pl = JavaPlugin.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { return true; }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("chatandtabmanager")) {
            if (args.length == 0) {
                About.sendInfoToPlayer(player, cmd.getName());
            }
            if (args.length == 1) {
                if (Objects.equals(args[0], "reload") && PermissionsEx.getUser(player).has("chatandtabmanager.config_reload")) {
                    pl.reloadConfig();
                    pl.saveConfig();
                    player.sendMessage("§aConfig reloaded!");
                }
            }
        }
        return true;
    }
}
