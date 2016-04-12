package com.deanveloper.kbukkit

import org.junit.*

/**
 * KBukkitRunnable tester

 * @author Dean Bassett
 */
class KBukkitRunnableTest {
    lateinit var runnable: KBukkitRunnable;
    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer);

        runnable = KBukkitRunnable {
            FakeServer.logger.info("KBukkit has been run successfully!")
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testRunTask() {
        runnable.runTask(KBukkitPlugin.instance)
    }
}