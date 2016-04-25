package com.deanveloper.kbukkit

import org.bukkit.Server
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader

/**
 * Plugin for Bukkit
 *
 * @author Dean Bassett
 */
final class KBukkitPlugin : KotlinPlugin {
    companion object {
        lateinit var instance: KBukkitPlugin
            private set;
    }

    /**
     * Used by Bukkit
     */
    constructor();

    /**
     * Used for tests
     */
    internal constructor(server: Server) : super(
            JavaPluginLoader(server),
            PluginDescriptionFile("KBukkit", "test", KBukkitPlugin::class.qualifiedName),
            null,
            null) {
        instance = this;
    }

    override fun onEnable() {
        instance = this;
    }
}