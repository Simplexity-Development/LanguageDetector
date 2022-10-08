package adhdmc.languagedetector.listeners;

import adhdmc.languagedetector.LanguageDetector;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Locale;

public class PlayerJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(LanguageDetector.getPlugin(), () -> {
            Locale locale = player.locale();
            // TODO: Make configurable.
            if (!locale.getLanguage().equalsIgnoreCase("en")) {
                // TODO: Make configurable.
                String message = "<red>You have been kicked from the server.\n<yellow>Your language is not set to a supported language.\n<gray>Your language is: " + locale.getDisplayLanguage();
                player.kick(MiniMessage.miniMessage().deserialize(message));
            }
        }, 20);
    }

}
