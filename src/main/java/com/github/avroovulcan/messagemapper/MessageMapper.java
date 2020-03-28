package com.github.avroovulcan.messagemapper;

import com.github.avroovulcan.messagemapper.listener.PreCommandListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AvroVulcan
 */
public class MessageMapper extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Map<String, String> messages = new HashMap<>();


        if (getConfig().isConfigurationSection("messages")) {
            for (String key : getConfig().getConfigurationSection("messages").getKeys(false)) {
                String value = getConfig().getString("messages." + key);
                if (value != null) {
                    if (!value.equals("")) {
                        messages.put(key, value);
                        getLogger().info("Registered /" + key + " to " + ChatColor.translateAlternateColorCodes('&',value));
                    }
                }
            }
        }

        getServer().getPluginManager().registerEvents(new PreCommandListener(messages),this);
    }
}
