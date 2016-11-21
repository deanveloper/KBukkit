package com.deanveloper.kbukkit

import org.bukkit.ChatColor as BukkitColor
import net.md_5.bungee.api.ChatColor as BungeeColor

/**
 * Adding a color to a string
 *
 * @author Dean B <dean@deanveloper.com>
 */
operator fun BukkitColor.plus(s: String): String = this.toString() + s
operator fun BungeeColor.plus(s: String): String = this.toString() + s
operator fun BukkitColor.plus(color: BukkitColor): String = this.toString() + color
operator fun BungeeColor.plus(color: BukkitColor): String = this.toString() + color
operator fun BukkitColor.plus(color: BungeeColor): String = this.toString() + color
operator fun BungeeColor.plus(color: BungeeColor): String = this.toString() + color