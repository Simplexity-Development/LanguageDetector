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

import java.util.Locale;

public class PlayerLocaleChange implements Listener {

    Plugin plugin = LanguageDetector.getPlugin();
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        Locale locale = event.locale();
        Player player = event.getPlayer();
        if (player.hasPermission(LanguageDetector.getBypassPerm())) return;
        if (!plugin.getConfig().getStringList("supported-languages").contains(locale.toLanguageTag().toLowerCase())) {
            String message = plugin.getConfig().getString("kick-message", "<red>You have been kicked from the server.\n<yellow>Your language is not set to a supported language.\n<gray>Your language is: <playerlocale>");
            player.kick(MiniMessage.miniMessage().deserialize(message, Placeholder.unparsed("playerlocale", locale.getDisplayLanguage())));
        }
    }
}
