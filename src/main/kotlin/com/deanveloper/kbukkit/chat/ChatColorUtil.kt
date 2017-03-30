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
operator fun BukkitColor.plus(s: String): String = this.toString() + s

/**
 * Adds a color to a string.
 */
operator fun BungeeColor.plus(s: String): String = this.toString() + s

/**
 * Adds a color to another color.
 */
operator fun BukkitColor.plus(color: BukkitColor): String = this.toString() + color

/**
 * Adds a color to another color.
 */
operator fun BungeeColor.plus(color: BukkitColor): String = this.toString() + color

/**
 * Adds a color to another color.
 */
operator fun BukkitColor.plus(color: BungeeColor): String = this.toString() + color

/**
 * Adds a color to another color.
 */
operator fun BungeeColor.plus(color: BungeeColor): String = this.toString() + color