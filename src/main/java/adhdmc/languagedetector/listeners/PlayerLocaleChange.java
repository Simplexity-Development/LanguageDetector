package adhdmc.languagedetector.listeners;

import adhdmc.languagedetector.LanguageDetector;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class PlayerLocaleChange implements Listener {

    Plugin plugin = LanguageDetector.getPlugin();
    Logger logger = LanguageDetector.getPlugin().getLogger();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(LanguageDetector.getBypassPerm())) return;

        Locale locale = event.locale();
        List<String> supportedLanguages = plugin.getConfig().getStringList("supported-languages");
        String langCode = locale.getLanguage().toLowerCase();
        String localeCode = locale.toLanguageTag();

        if (!supportedLanguages.contains(langCode) && !supportedLanguages.contains(localeCode.toLowerCase())) {
            String message = plugin.getConfig().getString("kick-message",
                """
                <red>You have been kicked from the server.
                <yellow>Your language is not set to a supported language.
                <gray>Your language is: <playerlang> (<playerlocale>)
                """);
            player.kick(MiniMessage.miniMessage().deserialize(message, Placeholder.unparsed("playerlang", locale.getDisplayLanguage()), Placeholder.unparsed("playerlocale", locale.toLanguageTag())));
            logger.info("Kicked player " + player.getName() + " for an invalid locale (" + localeCode + ").");
        }
    }
}
