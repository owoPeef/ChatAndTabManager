package ru.owopeef.chatandtabmanager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.chatandtabmanager.utils.Config;

import java.util.Map;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        PluginManager pm = getServer().getPluginManager();
        int both = 0;

        if (pm.getPlugin("PermissionsEx") != null) {
            both = 2;
        }

        if (pm.getPlugin("PlaceholderAPI") != null) {
            if (both == 0) {
                disablePlugin("PermissionsEx not installed.");
            } else {
                both = 4;
                getLogger().info("PermissionsEx hooked.");
                getLogger().info("PlaceholderAPI hooked.");
                enablePlugin();
            }
        }

        if (both == 2) {
            disablePlugin("PlaceholderAPI not installed.");
        }
    }

    public void enablePlugin() {
        Config.loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        for (Map.Entry<String, Map<String, Object>> cmd : getDescription().getCommands().entrySet()) {
            String command = cmd.getKey();
            try {
                getCommand(command).setExecutor(new Commands());
                this.getLogger().info("Command \"" + command + "\" init success");
            } catch (Exception exc) {
                this.getLogger().warning("Command \"" + command + "\" don't init. ("+exc.getMessage()+")");
            }
        }
    }

    public void disablePlugin(String disableReason) {
        getLogger().info("Plugin disable: " + disableReason);
        getServer().getPluginManager().disablePlugin(this);
    }
}
