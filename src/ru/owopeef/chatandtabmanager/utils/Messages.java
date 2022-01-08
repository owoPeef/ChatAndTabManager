package ru.owopeef.chatandtabmanager.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import ru.tehkode.permissions.PermissionUser;

public class Messages {
    public static String formatMessage(String original_message, PermissionUser user) {
        return PlaceholderAPI.setPlaceholders(user.getPlayer(), original_message
                .replace("{player_suffix}", user.getSuffix()
                        .replace("&", "§"))
                .replace("{player_prefix}", user.getPrefix()
                        .replace("&", "§"))
                .replace("{player_nickname}", user.getName()));
    }

    public static String formatMessage(String original_message, boolean replace_colors, PermissionUser user) {
        if (replace_colors) {
            return PlaceholderAPI.setPlaceholders(user.getPlayer(), original_message
                    .replace("&", "§")
                    .replace("{player_suffix}", user.getSuffix()
                            .replace("&", "§"))
                    .replace("{player_prefix}", user.getPrefix()
                            .replace("&", "§"))
                    .replace("{player_nickname}", user.getName()));
        } else {
            return PlaceholderAPI.setPlaceholders(user.getPlayer(), original_message
                    .replace("{player_suffix}", user.getSuffix()
                            .replace("&", "§"))
                    .replace("{player_prefix}", user.getPrefix()
                            .replace("&", "§"))
                    .replace("{player_nickname}", user.getName()));
        }
    }

    public static String formatMessage(String original_message, String message, PermissionUser user) {
        return PlaceholderAPI.setPlaceholders(user.getPlayer(), original_message
                .replace("&", "§")
                .replace("{player_suffix}", user.getSuffix()
                        .replace("&", "§"))
                .replace("{player_prefix}", user.getPrefix()
                        .replace("&", "§"))
                .replace("{player_message}", message)
                .replace("{player_nickname}", user.getName()));
    }
}
