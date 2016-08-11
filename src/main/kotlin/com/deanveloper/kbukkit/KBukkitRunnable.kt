package com.deanveloper.kbukkit

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

/**
 * Run a task on the main thread
 */
inline fun runTask(plugin: Plugin, crossinline toRun: BukkitRunnable.() -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            this.toRun()
        }
    }.runTask(plugin)
}

/**
 * Run a task after [delay] of ticks
 */
inline fun runTaskLater(plugin: Plugin, delay: Long, crossinline toRun: BukkitRunnable.() -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            this.toRun()
        }
    }.runTaskLater(plugin, delay)
}

/**
 * Repeat a task every [repeat] ticks after [delay] ticks
 */
inline fun runTaskTimer(plugin: Plugin, delay: Long, repeat: Long, crossinline toRun: BukkitRunnable.() -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            this.toRun()
        }
    }.runTaskTimer(plugin, delay, repeat)
}

/**
 * Run a task on the async thread
 */
inline fun runTaskAsync(plugin: Plugin, crossinline toRun: BukkitRunnable.() -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            this.toRun()
        }
    }.runTaskAsynchronously(plugin)
}

/**
 * Run a task after [delay] ticks on the async thread
 */
inline fun runTaskLaterAsync(plugin: Plugin, delay: Long, crossinline toRun: BukkitRunnable.() -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            this.toRun()
        }
    }.runTaskLaterAsynchronously(plugin, delay)
}

/**
 * Repeat a task every [repeat] ticks after [delay] ticks on the async thread
 */
inline fun runTaskTimerAsync(plugin: Plugin, delay: Long, repeat: Long, crossinline toRun: BukkitRunnable.() -> Unit): BukkitTask {
    return object : BukkitRunnable() {
        override fun run() {
            this.toRun()
        }
    }.runTaskTimerAsynchronously(plugin, delay, repeat)
}