package ru.owopeef.chatandtabmanager.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import ru.tehkode.permissions.PermissionUser;

import java.util.ArrayList;
import java.util.List;

public class Messages {
    public static boolean isEvenlyDivisable(int a, int b) {
        return a % b == 0;
    }
    
    public static String formatMessage(String original_message, PermissionUser user) {
        List<String> list = new ArrayList<>();
        String[] ss1 = original_message.split("\n");
        for (String s1 : ss1) {
            String[] ss2 = s1.split("%");
            System.out.println(ss1.length);
            if (isEvenlyDivisable(ss1.length, 2) || ss1.length == 3) {
                int i = 0;
                for (String s2 : ss2) {
                    i++;
                    if (i == 2) {
                        i = 0;
                        String strToAdd = "%" + s2 + "%:" + PlaceholderAPI.setPlaceholders(user.getPlayer(), "%" + s2 + "%");
                        System.out.println(strToAdd);
                        list.add(strToAdd);
                    }
                }
                for (String l : list) {
                    String[] split = l.split(":");
                    original_message = original_message.replace(split[0], split[1]);
                }
            }
        }
        return original_message
                .replace("{player_suffix}", user.getSuffix()
                        .replace("&", "§"))
                .replace("{player_prefix}", user.getPrefix()
                        .replace("&", "§"))
                .replace("{player_nickname}", user.getName());
    }

    public static String formatMessage(String original_message, boolean replace_colors, PermissionUser user) {
        List<String> list = new ArrayList<>();
        String[] ss1 = original_message.split("\n");
        for (String s1 : ss1) {
            String[] ss2 = s1.split("%");
            System.out.println(ss1.length);
            if (isEvenlyDivisable(ss1.length, 2) || ss1.length == 3) {
                int i = 0;
                for (String s2 : ss2) {
                    i++;
                    if (i == 2) {
                        i = 0;
                        String strToAdd = "%" + s2 + "%:" + PlaceholderAPI.setPlaceholders(user.getPlayer(), "%" + s2 + "%");
                        System.out.println(strToAdd);
                        list.add(strToAdd);
                    }
                }
                for (String l : list) {
                    String[] split = l.split(":");
                    original_message = original_message.replace(split[0], split[1]);
                }
            }
        }
        if (replace_colors) {
            return original_message
                    .replace("&", "§")
                    .replace("{player_suffix}", user.getSuffix()
                            .replace("&", "§"))
                    .replace("{player_prefix}", user.getPrefix()
                            .replace("&", "§"))
                    .replace("{player_nickname}", user.getName());
        } else {
            return original_message
                    .replace("{player_suffix}", user.getSuffix()
                            .replace("&", "§"))
                    .replace("{player_prefix}", user.getPrefix()
                            .replace("&", "§"))
                    .replace("{player_nickname}", user.getName());
        }
    }

    public static String formatMessage(String original_message, String message, PermissionUser user) {
        return original_message
                .replace("&", "§")
                .replace("{player_suffix}", user.getSuffix()
                        .replace("&", "§"))
                .replace("{player_prefix}", user.getPrefix()
                        .replace("&", "§"))
                .replace("{player_message}", message)
                .replace("{player_nickname}", user.getName());
    }
}
