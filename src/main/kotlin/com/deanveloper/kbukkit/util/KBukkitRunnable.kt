package com.deanveloper.kbukkit.util

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

/**
 * @author Dean B <dean@deanveloper.com>
 */

/**
 * Run a task on the main thread
 */
fun runTask(plugin: Plugin, toRun: BukkitRunnable.() -> Unit): BukkitTask {
	return object : BukkitRunnable() {
		override fun run() {
			this.toRun()
		}
	}.runTask(plugin)
}

/**
 * Run a task after [delay] of ticks
 */
fun runTaskLater(plugin: Plugin, delay: Long, toRun: BukkitRunnable.() -> Unit): BukkitTask {
	return object : BukkitRunnable() {
		override fun run() {
			this.toRun()
		}
	}.runTaskLater(plugin, delay)
}

/**
 * Repeat a task every [repeat] ticks after [delay] ticks
 */
fun runTaskTimer(plugin: Plugin, delay: Long, repeat: Long, toRun: BukkitRunnable.() -> Unit): BukkitTask {
	return object : BukkitRunnable() {
		override fun run() {
			this.toRun()
		}
	}.runTaskTimer(plugin, delay, repeat)
}

/**
 * Run a task on the async thread
 */
fun runTaskAsync(plugin: Plugin, toRun: BukkitRunnable.() -> Unit): BukkitTask {
	return object : BukkitRunnable() {
		override fun run() {
			this.toRun()
		}
	}.runTaskAsynchronously(plugin)
}

/**
 * Run a task after [delay] ticks on the async thread
 */
fun runTaskLaterAsync(plugin: Plugin, delay: Long, toRun: BukkitRunnable.() -> Unit): BukkitTask {
	return object : BukkitRunnable() {
		override fun run() {
			this.toRun()
		}
	}.runTaskLaterAsynchronously(plugin, delay)
}

/**
 * Repeat a task every [repeat] ticks after [delay] ticks on the async thread
 */
fun runTaskTimerAsync(plugin: Plugin, delay: Long, repeat: Long, toRun: BukkitRunnable.() -> Unit): BukkitTask {
	return object : BukkitRunnable() {
		override fun run() {
			this.toRun()
		}
	}.runTaskTimerAsynchronously(plugin, delay, repeat)
}