package com.deanveloper.kbukkit

import org.bukkit.ChatColor
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author Dean
 */
class ChatColorTest {
    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer)
    }

    @Test
    fun testColor() {
        assertEquals("§ahi", ChatColor.GREEN + "hi")
        assertEquals("§a§lhi", ChatColor.GREEN + ChatColor.BOLD + "hi")
    }
}