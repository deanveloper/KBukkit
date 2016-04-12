package com.deanveloper.kbukkit

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
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testRunTask() {
        val config = KConfig(KBukkitPlugin.instance, "config.yml");
        Assert.assertEquals(config["test"], "hi");
    }
}