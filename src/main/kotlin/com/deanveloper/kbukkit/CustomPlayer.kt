package com.deanveloper.kbukkit

import org.bukkit.Bukkit
import org.bukkit.entity.Player
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
    }

    companion object : CustomPlayerCompanion<CustomPlayer>({ CustomPlayer(it) })
}

open class CustomPlayerCompanion<T : CustomPlayer>(val factory: (Player) -> T) {
    protected val idMap = WeakHashMap<UUID, T>()
    protected val nameMap = WeakHashMap<String, T>()

    operator fun get(player: Player): T = this[player.uniqueId]

    operator fun get(index: UUID): T {
        return idMap[index] ?: factory(Bukkit.getPlayer(index)!!)
    }

    operator fun get(index: String): CustomPlayer {
        return nameMap[index] ?: factory(Bukkit.getPlayer(index)!!)
    }
}