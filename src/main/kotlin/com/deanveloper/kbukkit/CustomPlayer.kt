package com.deanveloper.kbukkit

import org.bukkit.entity.Player
import java.util.*

/**
 * API for players through delegation
 *
 * @author Dean B <dean@deanveloper.com>
 */
open class CustomPlayer protected constructor(player: Player) : Player by player {
    companion object : CustomPlayerCompanion<CustomPlayer>(::CustomPlayer)
}

open class CustomPlayerCompanion<out T : CustomPlayer>(val factory: (Player) -> T) {
    operator fun get(player: Player): T = factory(player)

    operator fun get(index: UUID): T = this[Players[index]!!]

    operator fun get(index: String): T = this[Players[index]!!]
}