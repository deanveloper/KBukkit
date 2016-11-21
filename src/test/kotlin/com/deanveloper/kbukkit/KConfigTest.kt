package com.deanveloper.kbukkit

import org.bukkit.Color
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.util.Vector
import org.junit.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests KConfig
 *
 * @author Dean B <dean@deanveloper.com>
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
        assertTrue { config["double", Double::class] in 0.19999..0.200001 }
        assertEquals(config["string"], "this is a string")
        assertEquals(config["boolean"], true)
        assertEquals(config["color"], Color.fromRGB(12, 42, 100))
        assertEquals(config["vector"], Vector(3, 2, 9))
        assertEquals(config["listOfInts"], listOf(3, 2))
        val section = config["testObject", ConfigurationSection::class]
        assertEquals(section["integer"], 5)
        assertTrue { section["double", 0.0] as Double in 0.499999..0.50000001 }
        assertEquals(section["string"], "this is a string as well")
        assertEquals(section["boolean"], false)
        assertEquals(section["listOfInts"], listOf(4, 78, 123))
    }
}