package com.deanveloper.kbukkit

import org.bukkit.entity.Player
import java.util.*

/**
 * API for custom players through delegation.
 * In order to implement, make sure your
 * companion object extends CustomPlayerCompanion and
 * provides the type of your class.
 *
 * @author Dean B <dean@deanveloper.com>
 */
open class CustomPlayer protected constructor(val bukkitPlayer: Player) : Player by bukkitPlayer {
    companion object : CustomPlayerCompanion<CustomPlayer>(::CustomPlayer)
}

/**
 * This class, while not required, provides utility in your
 * CustomPlayer implementation's companion object.
 *
 * Usage -> companion object : CustomPlayerCompanion<YourImpl>(::YourImpl)
 */
open class CustomPlayerCompanion<T : CustomPlayer>(val factory: (Player) -> T) {
    protected val idMap = WeakHashMap<UUID, T>()
    protected val nameMap = WeakHashMap<String, T>()

    /**
     * Fetches the CustomPlayer instance.
     *
     * @return the CustomPlayer instance for this bukkitPlayer
     */
    operator fun get(bPlayer: Player): T {
        if (bPlayer.uniqueId in idMap) {
            val player = idMap[bPlayer.uniqueId]!!

            if (player.bukkitPlayer !== bPlayer) {
                return put(bPlayer)
            }

            return player
        } else {
            return put(bPlayer)
        }
    }

    /**
     * Fetches the CustomPlayer instance from a UUID.
     *
     * @return the CustomPlayer associated with the UUID, null if the player is not online.
     */
    operator fun get(index: UUID): T? {
        val player = Players[index]

        return if(player === null) null else this[player]
    }

    /**
     * Fetches the CustomPlayer instance from a name.
     *
     * @return the CustomPlayer associated with the name, null if the player is not online.
     */
    @Deprecated("Names are not guaranteed to be unique.", ReplaceWith("get(id)"))
    operator fun get(index: String): T? {
        val player = Players[index]

        return if(player === null) null else this[player]
    }

    protected fun put(bPlayer: Player): T {
        val player = factory(bPlayer)
        idMap[bPlayer.uniqueId] = player
        nameMap[bPlayer.name] = player
        return player
    }
}