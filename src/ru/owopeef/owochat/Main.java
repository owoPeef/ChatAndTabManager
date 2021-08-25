package ru.owopeef.owochat;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.owochat.utils.Config;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        Config.loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }
}
