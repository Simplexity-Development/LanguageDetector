package adhdmc.languagedetector;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import adhdmc.languagedetector.listeners.PlayerJoin;
import adhdmc.languagedetector.listeners.PlayerLocaleChange;

import java.util.List;

public final class LanguageDetector extends JavaPlugin {

    private static Plugin plugin;
    private static final String bypassPerm = "languagedetector.bypass";

    @Override
    public void onEnable() {
        plugin = this;
        registerEvents();
        setConfigDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() { }

    public static Plugin getPlugin() { return plugin; }

    public static String getBypassPerm() { return bypassPerm; }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLocaleChange(), this);
    }

    private void setConfigDefaults(){
        this.getConfig().addDefault("supported-languages", List.of("en_us"));
        this.getConfig().addDefault("kick-message", "<red>You have been kicked from the server.\n<yellow>Your language is not set to a supported language.\n<gray>Your language is: <playerlocale>");
    }

}
