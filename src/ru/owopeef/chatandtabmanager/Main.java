package ru.owopeef.chatandtabmanager;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.chatandtabmanager.utils.Config;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        Config.loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }
}
