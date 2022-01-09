package ru.owopeef.chatandtabmanager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class About {
    public static Plugin pl = JavaPlugin.getPlugin(Main.class);

    public static void sendInfoToPlayer(Player player, String command) {
        String str = ChatColor.RED + " " + pl.getDescription().getName() + ChatColor.GRAY + " (v" + pl.getDescription().getVersion() + ") ";
        String first = ChatColor.GOLD + "-===" + str + ChatColor.GOLD + "===-";
        String a = ChatColor.YELLOW + "Автор плагина: " + ChatColor.GREEN + pl.getDescription().getAuthors().get(0);
        String b = ChatColor.YELLOW + "Сайт плагина: " + ChatColor.GREEN + pl.getDescription().getWebsite();
        String c = ChatColor.YELLOW + "Все команды: " + ChatColor.GREEN + "/" + command + " help";
        StringBuilder last = new StringBuilder(ChatColor.GOLD + "-===");
        int i = 0;
        while (i != str.length()) {
            last.append("=");
            i++;
        }
        last.append("===-");
        player.sendMessage(first + "\n" + a + "\n" + b + "\n" + c + "\n" + last);
    }
}
