package com.deanveloper.kbukkit

/**
 * API for YAML configurations, based on MrZoraman's config API.
 *
 * Modifications: Now in Kotlin, added methods to get values
 * from the config without needing to do getConfig(), and used
 * Kotlin null-safety features to make it easier to use.
 *
 * @author Dean Bassett
 */
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException
import java.util.logging.Level

// open so that a test class can be created
open class KConfig(val plugin: Plugin, val fileName: String) {

    val configFile: File;
    lateinit var fileConfiguration: FileConfiguration
        private set;

    init {
        this.configFile = File(plugin.dataFolder!!, fileName);
        saveDefault();
        reload();
    }

    operator fun get(path: String): Any? = this[path, null];

    operator fun get(path: String, def: Any?) = fileConfiguration[path, def];

    operator fun set(path: String, value: Any?): KConfig {
        fileConfiguration[path] = value
        return this;
    }

    operator fun unaryPlus(): KConfig {
        saveDefault();
        save();
        return this;
    }

    fun reload(): Unit {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile)!!;

        // Look for defaults in the jar
        fileConfiguration.defaults = YamlConfiguration.loadConfiguration(plugin.getResource(fileName).reader());
    }

    fun getConfig(): FileConfiguration {
        return fileConfiguration;
    }

    fun save(): Unit {
        try {
            getConfig().save(configFile);
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

