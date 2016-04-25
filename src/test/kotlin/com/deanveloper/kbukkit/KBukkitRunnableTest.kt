package com.deanveloper.kbukkit

import org.junit.*

/**
 * KBukkitRunnable tester

 * @author Dean Bassett
 */
class KBukkitRunnableTest {
    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer);
    }

    @Test
    fun testRunTask() {
        var s: String = ""
        KBukkitRunnable {
            s = "hi lol"
        }.runTask(KBukkitPlugin.instance)

        Assert.assertEquals(s, "hi lol")
    }
}