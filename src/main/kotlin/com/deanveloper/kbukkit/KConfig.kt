package com.deanveloper.kbukkit
import org.bukkit.Color
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import org.bukkit.util.Vector
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
 * @author Dean Bassett
 */
open class KConfig(val plugin: Plugin, val fileName: String) {

    val configFile: File;
    lateinit var config: FileConfiguration
        private set;

    init {
        if (fileName == "testingUseOnlyDoNotUseThisAsAnInputPlease") {
            this.configFile = File.createTempFile("config", ".yml");
            this.configFile.writeText("""
            |integer: 0
            |double: 0.00002
            |string: 'this is a string'
            |boolean: true
            |color: ${Color.fromRGB(12, 42, 100)}
            |vector: ${Vector(3, 2, 9)}
            |listOfInts:
            |  - 3
            |  - 2
            """.trimMargin());
        } else {
            this.configFile = File(plugin.dataFolder!!, fileName);
        }

        saveDefault();
        reload();
    }

    operator fun get(path: String): Any? = config[path, null];

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(path: String, def: T) = config[path, def]!! as T;

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(path: String, type: KClass<T>) = config[path, null]!! as T;

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(path: String, type: KClass<T>, def: T) = config[path, def]!! as T;

    operator fun set(path: String, value: Any?): KConfig {
        config[path] = value;
        return this;
    }

    operator fun unaryPlus(): KConfig {
        saveDefault();
        save();
        return this;
    }

    fun reload(): Unit {
        config = YamlConfiguration.loadConfiguration(configFile)!!;

        // Look for defaults in the jar
        val defaults = plugin.getResource(fileName);
        if (defaults != null) {
            config.defaults = YamlConfiguration.loadConfiguration(defaults.reader());
        }
    }

    fun save(): Unit {
        try {
            config.save(configFile);
        } catch (ex: IOException) {
            plugin.logger.log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }

    fun saveDefault() {
        if (!configFile.exists()) {
            plugin.saveResource(fileName, false);
        }
    }

    override fun toString(): String {
        return config.toString();
    }
}

