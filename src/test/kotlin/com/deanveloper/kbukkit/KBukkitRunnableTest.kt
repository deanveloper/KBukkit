package com.deanveloper.kbukkit

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * KBukkitRunnable tester

 * @author Dean Bassett
 */
class KBukkitRunnableTest {
    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer)
    }

    @Test
    fun testRunTask() {
        var s: String = ""
        runTask(KBukkitPlugin.instance) {
            s = "hi lol"
        }

        assertEquals(s, "hi lol")
    }
}