package com.github.avroovulcan.messagemapper.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Map;

/**
 * @author AvroVulcan
 */
public class PreCommandListener implements Listener {

    private Map<String, String> mappedMessages;

    public PreCommandListener(Map<String, String> mappedMessages) {
        this.mappedMessages = mappedMessages;
    }

    @EventHandler(ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (!event.getMessage().startsWith("/")) return;
        String label = event.getMessage().toLowerCase().split(" ")[0].substring(1);
        if (event.getPlayer().hasPermission("messagemapper.useall") || event.getPlayer().hasPermission("messagemapper." + label)) {
            String message = mappedMessages.get(label);
            if (message != null) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',message));
            }
        }
    }

}
