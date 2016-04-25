package com.deanveloper.kbukkit

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.lang.ref.SoftReference
import java.util.*

/**
 * API for players through delegation
 *
 * @author Dean B
 */
open class CustomPlayer protected constructor(player: Player) : Player by player {
    init {
        idMap.put(uniqueId, this)
        nameMap.put(name, this)

        KBukkitRunnable {
            idMap.remove(uniqueId)
            nameMap.remove(name)
        }.runTaskLater(KBukkitPlugin.instance, 20L)
    }

    companion object Handler {
        private val idMap = mutableMapOf<UUID, CustomPlayer>()
        private val nameMap = mutableMapOf<String, CustomPlayer>()

        @JvmStatic operator fun get(index: UUID): CustomPlayer {
            return idMap[index] ?: CustomPlayer(Bukkit.getPlayer(index)!!)
        }

        @JvmStatic operator fun get(index: String): CustomPlayer {
            return nameMap[index] ?: CustomPlayer(Bukkit.getPlayer(index)!!)
        }
    }
}