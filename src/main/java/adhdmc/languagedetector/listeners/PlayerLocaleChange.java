package adhdmc.languagedetector.listeners;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import java.util.Locale;

public class PlayerLocaleChange implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        Locale locale = event.locale();
        // TODO: Make configurable.
        if (!locale.getLanguage().equalsIgnoreCase("en")) {
            // TODO: Make configurable.
            String message = "<red>You have been kicked from the server.\n<yellow>You changed your language to an unsupported language.\n<gray>Your language is: " + locale.getDisplayLanguage();
            event.getPlayer().kick(MiniMessage.miniMessage().deserialize(message));
        }
    }

}
