package io.github.mcengine.discord.api;

import io.github.mcengine.discord.api.util.MCEngineDiscordApiUtil;
import org.bukkit.plugin.Plugin;

/**
 * Main API class for interacting with Discord via webhooks.
 * Provides methods to register webhooks and send messages.
 */
public class MCEngineDiscordApi {
    private final Plugin plugin;

    /**
     * Constructs a new Discord API wrapper for the given plugin.
     *
     * @param plugin the parent Bukkit plugin
     */
    public MCEngineDiscordApi(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Sends a message to the Discord webhook associated with the given server ID.
     *
     * @param serverId the ID representing the target Discord webhook
     * @param msg      the message to send
     */
    public void sendMessage(String serverId, String msg) {
        MCEngineDiscordApiUtil.sendMessage(serverId, msg);
    }

    /**
     * Registers a new webhook URL associated with a specific server ID.
     *
     * @param serverId    the identifier to map to the webhook
     * @param webhookUrl  the full Discord webhook URL
     */
    public void addWebHookId(String serverId, String webhookUrl) {
        MCEngineDiscordApiUtil.addWebHookId(serverId, webhookUrl);
    }

    /**
     * Registers multiple webhook URLs.
     * Each entry in the array should contain two values: serverId and webhookUrl.
     *
     * @param serverWebhookPairs  an array of [serverId, webhookUrl] pairs
     */
    public void addWebHookIds(String[][] serverWebhookPairs) {
        for (String[] pair : serverWebhookPairs) {
            if (pair.length == 2) {
                MCEngineDiscordApiUtil.addWebHookId(pair[0], pair[1]);
            }
        }
    }
}
