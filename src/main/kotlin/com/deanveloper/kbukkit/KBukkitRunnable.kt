package com.deanveloper.kbukkit

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

/**
 * Run a task on the main thread
 */
inline fun runTask(plugin: Plugin, crossinline run: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            run()
        }
    }.runTask(plugin)
}

/**
 * Run a task after [delay] of ticks
 */
inline fun runTaskLater(plugin: Plugin, delay: Long, crossinline run: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            run()
        }
    }.runTaskLater(plugin, delay)
}

/**
 * Repeat a task every [repeat] ticks after [delay] ticks
 */
inline fun runTaskTimer(plugin: Plugin, delay: Long, repeat: Long, crossinline run: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            run()
        }
    }.runTaskTimer(plugin, delay, repeat)
}

/**
 * Run a task on the async thread
 */
inline fun runTaskAsync(plugin: Plugin, crossinline run: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            run()
        }
    }.runTaskAsynchronously(plugin)
}

/**
 * Run a task after [delay] ticks on the async thread
 */
inline fun runTaskLaterAsync(plugin: Plugin, delay: Long, crossinline run: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            run()
        }
    }.runTaskLaterAsynchronously(plugin, delay)
}

/**
 * Repeat a task every [repeat] ticks after [delay] ticks on the async thread
 */
inline fun runTaskTimerAsync(plugin: Plugin, delay: Long, repeat: Long, crossinline run: () -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            run()
        }
    }.runTaskTimerAsynchronously(plugin, delay, repeat)
}