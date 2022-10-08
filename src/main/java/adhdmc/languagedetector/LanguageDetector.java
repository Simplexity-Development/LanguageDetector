package adhdmc.languagedetector;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import adhdmc.languagedetector.listeners.PlayerJoin;
import adhdmc.languagedetector.listeners.PlayerLocaleChange;

public final class LanguageDetector extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerEvents();
    }

    @Override
    public void onDisable() { }

    public static Plugin getPlugin() { return plugin; }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLocaleChange(), this);
    }

}
