package com.deanveloper.kbukkit

import org.bukkit.entity.Player
import org.junit.Before
import org.junit.Test

/**
 * Tests the CustomPlayer class
 *
 * @author Dean B
 */
class CustomPlayerTest {
    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer)
    }

    @Test
    fun test() {
        // no good way to test this as we can't use OfflinePlayers for this, but here is the usage!
    }

    class KotlinPlayer protected constructor(p: Player) : CustomPlayer(p) {
        init {
            TODO()
        }

        companion object : CustomPlayerCompanion<KotlinPlayer>({ KotlinPlayer(it) })
    }
}