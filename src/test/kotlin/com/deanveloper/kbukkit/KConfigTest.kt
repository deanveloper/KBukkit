package com.deanveloper.kbukkit

import org.junit.*

/**
 * Tests KConfig
 *
 * @author Dean Bassett
 */
class KConfigTest {
    var config: KConfig? = null;

    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer);
        config = KConfig(KBukkitPlugin.instance, "config.yml")
    }

    @After
    fun tearDown() {
        config = null;
    }

    @Test
    fun testRunTask() {
        Assert.assertEquals(config!!["test"], "hi")
    }
}