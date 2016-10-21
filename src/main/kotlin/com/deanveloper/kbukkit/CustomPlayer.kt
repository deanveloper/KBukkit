package com.deanveloper.kbukkit

import org.bukkit.entity.Player
import java.util.*

/**
 * API for players through delegation
 *
 * @author Dean B
 */
open class CustomPlayer protected constructor(player: Player) : Player by player {
    companion object : CustomPlayerCompanion<CustomPlayer>(::CustomPlayer)
}

open class CustomPlayerCompanion<T : CustomPlayer>(val factory: (Player) -> T) {
    protected val idMap = WeakHashMap<UUID, T>()
    protected val nameMap = WeakHashMap<String, T>()

    operator fun get(player: Player): T {
        if(player.uniqueId in idMap) {
            return idMap[player.uniqueId]!!
        } else {
            val custom = factory(player)
            idMap[player.uniqueId] = custom
            nameMap[player.name] = custom

            return custom
        }
    }

    operator fun get(index: UUID): T = this[Players[index]!!]

    operator fun get(index: String): T = this[Players[index]!!]
}