package com.deanveloper.kbukkit

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
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
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testRunTask() {
        val config = KConfig(KBukkitPlugin.instance, "testingUseOnlyDoNotUseThisAsAnInputPlease");
        config["integer"] = 0;
        config["double"] = 0.00002;
        config["string"] = "this is a string"
        config["boolean"] = true;
        config["color"] = Color.fromRGB(12, 42, 100);
        config["itemStack"] = ItemStack(Material.ACACIA_STAIRS);
        config["vector"] = Vector(3, 2, 9);
        config["offlinePlayer"] = FakeServer.createPlayer("lol");
        config["listOfInts"] = listOf(3, 2);
        println(config.config.saveToString());
    }
}