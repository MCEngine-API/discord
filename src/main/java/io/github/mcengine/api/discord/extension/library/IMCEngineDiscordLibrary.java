package io.github.mcengine.api.discord.extension.library;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Discord-based Library module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to provide discord-related library support to the system. Use this to build Discord integrations such as commands, events, and webhooks.
 */
public interface IMCEngineDiscordLibrary {

    /**
     * Called when the Discord {module} is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Discord {module} is unloaded or disabled by the engine.
     * <p>
     * Use this method to release resources, unregister listeners, or perform any necessary cleanup.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Discord {module} instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
