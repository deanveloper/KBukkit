package com.deanveloper.kbukkit

import org.bukkit.Color
import org.bukkit.util.Vector
import org.junit.*

/**
 * Tests KConfig
 *
 * @author Dean Bassett
 */
class KConfigTest {

    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer);
        FakeServer.logger.info("Starting KConfig test...")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testRunTask() {
        val config = KConfig(KBukkitPlugin.instance, "testingUseOnlyDoNotUseThisAsAnInputPlease");
        Assert.assertEquals(config["integer"], 0);
        Assert.assertEquals(config["double", Double::class], 0.00002, .00000001);
        Assert.assertEquals(config["string"], "this is a string");
        Assert.assertEquals(config["boolean"], true);
        Assert.assertEquals(config["color"], Color.fromRGB(12, 42, 100));
        Assert.assertEquals(config["vector"], Vector(3, 2, 9));
        Assert.assertEquals(config["listOfInts"], listOf(3, 2));

    }
}