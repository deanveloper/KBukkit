package com.deanveloper.kbukkit

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * API for players through delegation
 *
 * @author Dean B
 */
open class CustomPlayer private constructor(player: Player) : Player by player {
    init {
        byId.put(uniqueId, this);
        byName.put(name, this);
    }

    companion object {
        private val byId: MutableMap<UUID, CustomPlayer> = mutableMapOf();
        private val byName: MutableMap<String, CustomPlayer> = mutableMapOf();

        operator fun get(index: UUID): CustomPlayer {
            val value = byId[index];
            if (value != null) {
                return value;
            }

            val p = Bukkit.getPlayer(index);
            return CustomPlayer(p!!);
        }

        operator fun get(index: String): CustomPlayer {
            val value = byName[index];
            if (value != null) {
                return value;
            }

            val p = Bukkit.getPlayer(index);
            return CustomPlayer(p!!);
        }
    }
}