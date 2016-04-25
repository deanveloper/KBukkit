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

    }

    final class KotlinPlayer protected constructor(p: Player) : CustomPlayer(p) {
        init {
            TODO()
        }
    }
}