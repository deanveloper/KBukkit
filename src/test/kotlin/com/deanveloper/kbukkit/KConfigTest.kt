package com.deanveloper.kbukkit

import org.bukkit.Color
import org.bukkit.util.Vector
import org.junit.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests KConfig
 *
 * @author Dean Bassett
 */
class KConfigTest {

    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer)
    }

    @Test
    fun testConfig() {
        val config = KConfig(KBukkitPlugin.instance, "testingUseOnlyDoNotUseThisAsAnInputPlease")
        assertEquals(config["integer"], 0)
        assertTrue { Math.abs(config["double", Double::class] - 0.00002) < .00000001 }
        assertEquals(config["string"], "this is a string")
        assertEquals(config["boolean"], true)
        assertEquals(config["color"], Color.fromRGB(12, 42, 100))
        assertEquals(config["vector"], Vector(3, 2, 9))
        assertEquals(config["listOfInts"], listOf(3, 2))

    }
}