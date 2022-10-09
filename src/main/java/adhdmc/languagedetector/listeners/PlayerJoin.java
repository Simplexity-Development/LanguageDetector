package adhdmc.languagedetector.listeners;

import adhdmc.languagedetector.LanguageDetector;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class PlayerJoin implements Listener {
    Plugin plugin = LanguageDetector.getPlugin();
    Logger logger = LanguageDetector.getPlugin().getLogger();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(LanguageDetector.getBypassPerm())) return;
        Bukkit.getScheduler().runTaskLater(LanguageDetector.getPlugin(), () -> {
            Locale locale = player.locale();
            List<String> supportedLocales = plugin.getConfig().getStringList("supported-languages");
            if (!supportedLocales.contains(locale.toLanguageTag().toLowerCase())) {
                String message = plugin.getConfig().getString("kick-message", "<red>You have been kicked from the server.\n<yellow>Your language is not set to a supported language.\n<gray>Your language is: <playerlocale>");
                player.kick(MiniMessage.miniMessage().deserialize(message, Placeholder.unparsed("playerlocale", locale.getDisplayLanguage())));
            }
        }, 20);
    }

}
