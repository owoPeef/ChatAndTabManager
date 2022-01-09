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
        if (pm.isPluginEnabled("PermissionsEx") && pm.isPluginEnabled("PlaceholderAPI")) {
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
        } else {
            this.setEnabled(false);
        }
    }
}
