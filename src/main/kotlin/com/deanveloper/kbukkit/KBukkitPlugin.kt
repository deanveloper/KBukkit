package com.deanveloper.kbukkit

import org.bukkit.Server
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader

/**
 * Plugin class created for testing purposes
 *
 * @author Dean Bassett
 */
class KBukkitPlugin : KotlinPlugin {
    companion object {
        lateinit var instance: KBukkitPlugin
            private set
    }

    /**
     * Used for tests
     */
    internal constructor(server: Server) : super(server) {
        instance = this
    }

    override fun onEnable() {
        instance = this
    }
}