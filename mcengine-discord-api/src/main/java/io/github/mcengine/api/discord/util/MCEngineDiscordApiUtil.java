package io.github.mcengine.discord.api.util;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Utility class for managing Discord webhook interactions.
 */
public class MCEngineDiscordApiUtil {
    /**
     * Map storing server IDs and their corresponding webhook URLs.
     */
    private static final Map<String, String> webhookMap = new ConcurrentHashMap<>();

    /**
     * Adds or updates the webhook URL for a given server ID.
     *
     * @param serverId    the identifier for the server
     * @param webhookUrl  the Discord webhook URL
     */
    public static void addWebHookId(String serverId, String webhookUrl) {
        webhookMap.put(serverId, webhookUrl);
    }

    /**
     * Sends a plain text message to the webhook associated with the given server ID.
     *
     * @param serverId the ID mapped to the webhook
     * @param msg      the message to send
     */
    public static void sendMessage(String serverId, String msg) {
        String webhookUrl = webhookMap.get(serverId);
        if (webhookUrl == null) {
            System.err.println("Webhook URL not found for serverId: " + serverId);
            return;
        }

        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = "{\"content\":\"" + escapeJson(msg) + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 204 && responseCode != 200) {
                System.err.println("Failed to send message: HTTP " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Escapes special characters in the message for safe JSON formatting.
     *
     * @param text the input text
     * @return escaped JSON string
     */
    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n");
    }
}
