package com.deanveloper.kbukkit

import org.bukkit.Server
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader

/**
 * Mainly just here for convenience. Looks *way* cooler to use a KotlinPlugin over a JavaPlugin!
 *
 * @author Dean B <dean@deanveloper.com>
 */
abstract class KotlinPlugin : JavaPlugin {

    constructor() : super()

    /**
     * Used for tests
     */
    internal constructor(server: Server) : super(
            JavaPluginLoader(server),
            PluginDescriptionFile("KBukkit", "test", KBukkitPlugin::class.qualifiedName),
            null,
            null
    )
}