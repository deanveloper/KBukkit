package com.deanveloper.kbukkit.chat

import net.md_5.bungee.api.ChatColor as BungeeColor
import org.bukkit.ChatColor as BukkitColor

/**
 * Provides utilities for adding colors to other colors.
 *
 * @author Dean B <dean@deanveloper.com>
 */

/**
 * Adds a color to a string.
 */
operator fun org.bukkit.ChatColor.plus(s: String): String = this.toString() + s

/**
 * Adds a color to a string.
 */
operator fun net.md_5.bungee.api.ChatColor.plus(s: String): String = this.toString() + s

/**
 * Adds a color to another color.
 */
operator fun org.bukkit.ChatColor.plus(color: org.bukkit.ChatColor): String = this.toString() + color

/**
 * Adds a color to another color.
 */
operator fun net.md_5.bungee.api.ChatColor.plus(color: org.bukkit.ChatColor): String = this.toString() + color

/**
 * Adds a color to another color.
 */
operator fun org.bukkit.ChatColor.plus(color: net.md_5.bungee.api.ChatColor): String = this.toString() + color

/**
 * Adds a color to another color.
 */
operator fun net.md_5.bungee.api.ChatColor.plus(color: net.md_5.bungee.api.ChatColor): String = this.toString() + color