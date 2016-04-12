package com.deanveloper.kbukkit

import org.bukkit.scheduler.BukkitRunnable

/**
 * An easy to use version of BukkitRunnable
 *
 * @author Dean Bassett
 */
class KBukkitRunnable (val run: () -> Unit) : BukkitRunnable() {
    override final fun run() {
        run.invoke();
    }
}