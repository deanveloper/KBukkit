package com.deanveloper.kbukkit

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException
import java.util.logging.Level
import kotlin.reflect.KClass


/**
 * API for YAML configurations, based on MrZoraman's config API.
 *
 * Modifications: Now in Kotlin, added methods to get values
 * from the config without needing to do getConfig(), and used
 * Kotlin null-safety features to make it easier to use.
 *
 * @property[plugin]    Your plugin
 * @property[fileName]  The file name of your config.
 * @author Dean B <dean@deanveloper.com>
 */
class KConfig(val plugin: Plugin, val fileName: String) {

    /**
     * The [java.io.File] representation of this instance.
     */
    val configFile: File

    /**
     * The [FileConfiguration] representation of this instance.
     */
    lateinit var config: FileConfiguration
        private set

    init {
        if (fileName == "testingUseOnlyDoNotUseThisAsAnInputPlease") {
            this.configFile = File.createTempFile("config", ".yml")
            this.configFile.writeText("""
            |integer: 0
            |double: 0.2
            |string: 'this is a string'
            |boolean: true
            |color:
            |  ==: Color
            |  RED: 12
            |  GREEN: 42
            |  BLUE: 100
            |vector:
            |  ==: Vector
            |  x: 3.0
            |  y: 2.0
            |  z: 9.0
            |listOfInts:
            |  - 3
            |  - 2
            |testObject:
            |  integer: 5
            |  double: 0.5
            |  string: 'this is a string as well'
            |  boolean: false
            |  listOfInts:
            |    -  4
            |    -  78
            |    -  123
            """.trimMargin())
        } else {
            this.configFile = File(plugin.dataFolder!!, fileName)
        }

        saveDefault()
        reload()
    }

    /**
     * Gets the value at a specified [path], null if none.
     */
    operator fun get(path: String): Any? = config[path, null]

    /**
     * Gets the value at [path], [def] if none.
     */
    operator fun <T> get(path: String, def: T): T {
        return config[path, def] as T
    }

    /**
     * Gets the value at [path] with return type [T].
     */
    operator fun <T : Any> get(path: String, type: KClass<T>): T {
        return config[path, null] as T
    }

    /**
     * Sets the value at [path] to [value]
     */
    operator fun set(path: String, value: Any?): KConfig {
        config[path] = value
        return this
    }

    @Deprecated("Seems like a waste...", level = DeprecationLevel.HIDDEN)
    operator fun unaryPlus(): KConfig {
        saveDefault()
        save()
        return this
    }

    /**
     * Reloads the config from file.
     */
    fun reload(): Unit {
        config = YamlConfiguration.loadConfiguration(configFile)!!

        // Look for defaults in the jar
        val defaults = plugin.getResource(fileName)
        if (defaults != null) {
            config.defaults = YamlConfiguration.loadConfiguration(defaults.reader())
        }
    }

    /**
     * Saves the config to file.
     */
    fun save(): Unit {
        try {
            config.save(configFile)
        } catch (ex: IOException) {
            plugin.logger.log(Level.SEVERE, "Could not save config to " + configFile, ex)
        }
    }

    /**
     * Saves the default values of the config if they are not specified.
     */
    fun saveDefault() {
        if (!configFile.exists()) {
            plugin.saveResource(fileName, false)
        }
    }

    /**
     * String representation of the config.
     */
    override fun toString(): String {
        return config.toString()
    }
}