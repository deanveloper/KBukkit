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

		KBukkitRunnable {
			byId.remove(uniqueId);
			byName.remove(name);
		}.runTaskLater(KBukkitPlugin.instance, 20L);
	}

	companion object {
		private val byId: MutableMap<UUID, CustomPlayer> = mutableMapOf();
		private val byName: MutableMap<String, CustomPlayer> = mutableMapOf();

		operator fun get(index: UUID): CustomPlayer = byId[index] ?: CustomPlayer(Bukkit.getPlayer(index)!!);

		operator fun get(index: String): CustomPlayer = byName[index] ?: CustomPlayer(Bukkit.getPlayer(index)!!);
	}
}