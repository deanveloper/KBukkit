package com.deanveloper.kbukkit

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import java.util.*

/**
 * API for custom players through delegation.
 * In order to implement, make sure your
 * companion object extends CustomPlayerCompanion and
 * provides the type of your class.
 *
 * @author Dean B <dean@deanveloper.com>
 */
open class CustomPlayer protected constructor(var bukkitPlayer: Player) : Player by bukkitPlayer {
    // companion object : CustomPlayerCompanion<CustomPlayer>(::CustomPlayer, plugin)
}

/**
 * This class, while not required, provides utility in your
 * CustomPlayer implementation's companion object.
 *
 * Usage -> companion object : CustomPlayerCompanion<YourImpl>(::YourImpl)
 */
open class CustomPlayerCompanion<T : CustomPlayer>(val factory: (Player) -> T, plugin: Plugin) : Listener {
    protected val idMap = mutableMapOf<UUID, T>()
    protected val nameMap = mutableMapOf<String, T>()

    init {
        this.registerEvents(plugin)
    }

    /**
     * Fetches the CustomPlayer instance.
     *
     * @return the CustomPlayer instance for this bukkitPlayer
     */
    operator fun get(bPlayer: Player): T {
        if (bPlayer.uniqueId in idMap) {
            val player = idMap[bPlayer.uniqueId]!!

            if (player.bukkitPlayer !== bPlayer) {
                return update(bPlayer)
            }

            return player
        } else {
            return update(bPlayer)
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

    protected fun update(bPlayer: Player): T {
        val player = idMap[bPlayer.uniqueId] ?: factory(bPlayer)
        player.bukkitPlayer = bPlayer

        idMap[bPlayer.uniqueId] = player
        nameMap[bPlayer.name] = player
        return player
    }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        update(e.player)
    }

    @EventHandler
    fun onLeave(e: PlayerQuitEvent) {
        idMap.remove(e.player.uniqueId)
        nameMap.remove(e.player.name)
    }
}