package com.deanveloper.kbukkit

import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

/**
 * Only here to make your plugins in Kotlin look nice
 *
 * @author Dean B
 */
abstract class KotlinPlugin : JavaPlugin {
    constructor() : super();

    constructor(javaPluginLoader: JavaPluginLoader,
                pluginDescriptionFile: PluginDescriptionFile,
                dataFolder: File?,
                file: File?
    ) : super(javaPluginLoader, pluginDescriptionFile, dataFolder, file)
}