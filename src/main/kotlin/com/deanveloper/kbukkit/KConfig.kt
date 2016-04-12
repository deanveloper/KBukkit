package com.deanveloper.kbukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException
import java.util.logging.Level


/**
 * API for YAML configurations, based on MrZoraman's config API.
 *
 * Modifications: Now in Kotlin, added methods to get values
 * from the config without needing to do getConfig(), and used
 * Kotlin null-safety features to make it easier to use.
 *
 * @author Dean Bassett
 */
open class KConfig(val plugin: Plugin, val fileName: String) {

    val configFile: File;
    lateinit var config: FileConfiguration
        private set;

    init {
        this.configFile = File(plugin.dataFolder!!, fileName);
        saveDefault();
        reload();
    }

    operator fun get(path: String): Any? = this[path, null];

    operator fun get(path: String, def: Any?) = config[path, def];

    operator fun set(path: String, value: Any?): KConfig {
        config[path] = value
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
        config.defaults = YamlConfiguration.loadConfiguration(plugin.getResource(fileName).reader());
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
}

