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

	@After
	fun tearDown() {
	}

	@Test
	fun testRunTask() {
		KBukkitRunnable {
			FakeServer.logger.info("KBukkit has been run successfully!")
		}.runTask(KBukkitPlugin.instance)
	}
}