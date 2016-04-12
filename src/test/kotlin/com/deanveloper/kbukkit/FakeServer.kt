package com.deanveloper.kbukkit

import com.avaje.ebean.config.ServerConfig
import org.bukkit.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.entity.*
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.help.HelpMap
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permissible
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.plugin.*
import org.bukkit.plugin.messaging.Messenger
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import org.bukkit.scheduler.BukkitWorker
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.util.*
import org.bukkit.util.Vector

import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.logging.Logger

/**
 * Based off of Essentials' FakeServer class

 * @author Essentials Team
 */
object FakeServer : Server {
    private var players: MutableList<Player> = ArrayList()
    private val worlds = ArrayList<World>()
    internal var pluginManager: PluginManager = FakePluginManager

    init {
        if (Bukkit.getServer() == null) {
            Bukkit.setServer(this)
        }
    }

    override fun getName(): String {
        return "Essentials Fake Server"
    }

    override fun getVersion(): String {
        return "1.0"
    }

    override fun _INVALID_getOnlinePlayers(): Array<Player> {
        return players.toTypedArray()
    }

    override fun getOnlinePlayers(): Collection<Player> {
        return players
    }

    fun setOnlinePlayers(players: MutableList<Player>) {
        this.players = players
    }

    override fun getMaxPlayers(): Int {
        return 100
    }

    override fun getPort(): Int {
        return 25565
    }

    override fun getIp(): String {
        return "127.0.0.1"
    }

    override fun getServerName(): String {
        return "Test Server"
    }

    override fun getServerId(): String {
        return "Test Server"
    }

    override fun broadcastMessage(string: String): Int {
        var i = 0
        for (player in players) {
            player.sendMessage(string)
            i++
        }
        return i
    }

    override fun getUpdateFolder(): String {
        return "update"
    }

    override fun getUpdateFolderFile(): File {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun isHardcore(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getPlayer(string: String): Player? {
        for (player in players) {
            if (player.name.equals(string, ignoreCase = true)) {
                return player
            }
        }
        return null
    }

    override fun matchPlayer(string: String): List<Player> {
        val matches = ArrayList<Player>()
        for (player in players) {
            if (player.name.substring(0, Math.min(player.name.length, string.length)).equals(string, ignoreCase = true)) {
                matches.add(player)
            }
        }
        return matches
    }

    override fun getPluginManager(): PluginManager {
        return pluginManager
    }

    override fun getScheduler(): BukkitScheduler {
        return object : BukkitScheduler {
            override fun scheduleSyncDelayedTask(plugin: Plugin, r: Runnable, l: Long): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleSyncDelayedTask(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleSyncDelayedTask(plugin: Plugin, r: Runnable): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleSyncDelayedTask(plugin: Plugin, bukkitRunnable: BukkitRunnable): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleSyncRepeatingTask(plugin: Plugin, r: Runnable, l: Long, l1: Long): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleSyncRepeatingTask(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long, l1: Long): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleAsyncRepeatingTask(plugin: Plugin, r: Runnable, l: Long, l1: Long): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun <T> callSyncMethod(plugin: Plugin, clbl: Callable<T>): Future<T> {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun cancelTask(i: Int) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun cancelTasks(plugin: Plugin) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun cancelAllTasks() {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isCurrentlyRunning(i: Int): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isQueued(i: Int): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getActiveWorkers(): List<BukkitWorker> {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getPendingTasks(): List<BukkitTask> {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTask(plugin: Plugin, r: Runnable): BukkitTask {
                r.run();
                return object : BukkitTask { //dummy bukkit task
                    override fun getTaskId() = 1;

                    override fun getOwner() = KBukkitPlugin.instance;

                    override fun isSync() = true;

                    override fun cancel() {
                        throw UnsupportedOperationException()
                    }
                }
            }

            @Throws(IllegalArgumentException::class)
            override fun runTask(plugin: Plugin, bukkitRunnable: BukkitRunnable): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskAsynchronously(plugin: Plugin, r: Runnable): BukkitTask? {
                r.run()
                return null
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskAsynchronously(plugin: Plugin, bukkitRunnable: BukkitRunnable): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskLater(plugin: Plugin, r: Runnable, l: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskLater(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskLaterAsynchronously(plugin: Plugin, r: Runnable, l: Long): BukkitTask? {
                r.run()
                return null
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskLaterAsynchronously(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskTimer(plugin: Plugin, r: Runnable, l: Long, l1: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskTimer(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long, l1: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskTimerAsynchronously(plugin: Plugin, r: Runnable, l: Long, l1: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            @Throws(IllegalArgumentException::class)
            override fun runTaskTimerAsynchronously(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long, l1: Long): BukkitTask {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleAsyncDelayedTask(plugin: Plugin, r: Runnable, l: Long): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun scheduleAsyncDelayedTask(plugin: Plugin, r: Runnable): Int {
                throw UnsupportedOperationException("Not supported yet.")
            }
        }
    }

    override fun getServicesManager(): ServicesManager {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getWorlds(): List<World> {
        return worlds
    }

    fun createWorld(string: String, e: World.Environment): World {
        val w = FakeWorld(string, e)
        worlds.add(w)
        return w
    }

    fun createWorld(string: String, e: World.Environment, l: Long): World {
        val w = FakeWorld(string, e)
        worlds.add(w)
        return w
    }

    override fun getWorld(string: String): World? {
        for (world in worlds) {
            if (world.name.equals(string, ignoreCase = true)) {
                return world
            }
        }
        return null
    }

    override fun reload() {
    }

    override fun getLogger(): Logger {
        return Logger.getLogger("Minecraft")
    }

    override fun getPluginCommand(string: String): PluginCommand {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun savePlayers() {
    }

    override fun dispatchCommand(cs: CommandSender, string: String): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun configureDbConfig(sc: ServerConfig) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun addRecipe(recipe: Recipe): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    fun addPlayer(base1: Player) {
        players.add(base1)
        pluginManager.callEvent(PlayerJoinEvent(base1, null))
    }

    fun createPlayer(name: String): OfflinePlayer {
        val player = createOPlayer(name)
        return player
    }

    override fun createWorld(creator: WorldCreator): World {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun unloadWorld(string: String, bln: Boolean): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun unloadWorld(world: World, bln: Boolean): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getCommandAliases(): Map<String, Array<String>> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getSpawnRadius(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun setSpawnRadius(i: Int) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getOnlineMode(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    fun getWorld(l: Long): World {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getWorld(uuid: UUID): World {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getViewDistance(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getAllowNether(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun hasWhitelist(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getMap(s: Short): MapView {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun createMap(world: World): MapView {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getAllowFlight(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun setWhitelist(bln: Boolean) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getWhitelistedPlayers(): Set<org.bukkit.OfflinePlayer> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun reloadWhitelist() {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getPlayerExact(string: String): Player {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun shutdown() {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun broadcast(string: String, string1: String): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getOfflinePlayer(string: String): org.bukkit.OfflinePlayer {
        return createOPlayer(string)
    }

    private fun createOPlayer(string: String): org.bukkit.OfflinePlayer {
        return object : org.bukkit.OfflinePlayer {
            override fun isOnline(): Boolean {
                return false
            }

            override fun getName(): String {
                return string
            }

            override fun isBanned(): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun setBanned(bln: Boolean) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isWhitelisted(): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun setWhitelisted(bln: Boolean) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getPlayer(): Player {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isOp(): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun setOp(bln: Boolean) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun serialize(): Map<String, Any> {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getFirstPlayed(): Long {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getLastPlayed(): Long {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun hasPlayedBefore(): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getBedSpawnLocation(): Location {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getUniqueId(): UUID? {
                if (string === "testPlayer1") {
                    return UUID.fromString("3c9ebe1a-9098-43fd-bc0c-a369b76817ba")
                } else if (string === "npc1") {
                    return null
                }
                throw UnsupportedOperationException("Not supported yet.") //To change body of generated methods, choose Tools | Templates.
            }
        }
    }

    override fun getIPBans(): Set<String> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun banIP(string: String) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun unbanIP(string: String) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getBannedPlayers(): Set<org.bukkit.OfflinePlayer> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getDefaultGameMode(): GameMode {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun setDefaultGameMode(gamemode: GameMode) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getConsoleSender(): ConsoleCommandSender {
        return object : ConsoleCommandSender {
            override fun sendMessage(message: String) {
                println("Console message: " + message)
            }

            override fun sendMessage(messages: Array<String>) {
                for (message in messages) {
                    println("Console message: " + message)
                }
            }

            override fun getServer(): Server {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getName(): String {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isPermissionSet(name: String): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isPermissionSet(perm: Permission): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun hasPermission(name: String): Boolean {
                return true
            }

            override fun hasPermission(perm: Permission): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun addAttachment(plugin: Plugin, name: String, value: Boolean): PermissionAttachment {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun addAttachment(plugin: Plugin): PermissionAttachment {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun addAttachment(plugin: Plugin, name: String, value: Boolean, ticks: Int): PermissionAttachment {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun addAttachment(plugin: Plugin, ticks: Int): PermissionAttachment {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun removeAttachment(attachment: PermissionAttachment) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun recalculatePermissions() {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun getEffectivePermissions(): Set<PermissionAttachmentInfo> {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isOp(): Boolean {
                return true
            }

            override fun setOp(value: Boolean) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun isConversing(): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun acceptConversationInput(input: String) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun beginConversation(conversation: Conversation): Boolean {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun abandonConversation(conversation: Conversation) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun abandonConversation(conversation: Conversation, details: ConversationAbandonedEvent) {
                throw UnsupportedOperationException("Not supported yet.")
            }

            override fun sendRawMessage(message: String) {
                throw UnsupportedOperationException("Not supported yet.")
            }
        }
    }

    override fun getOperators(): MutableSet<OfflinePlayer>? {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getBukkitVersion(): String {
        return "Essentials Fake-Server"
    }

    override fun getWorldContainer(): File {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getOfflinePlayers(): Array<OfflinePlayer> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getAllowEnd(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getMessenger(): Messenger {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun sendPluginMessage(plugin: Plugin, string: String, bytes: ByteArray) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getListeningPluginChannels(): Set<String> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun useExactLoginLocation(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getTicksPerAnimalSpawns(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getTicksPerMonsterSpawns(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getRecipesFor(`is`: ItemStack): List<Recipe> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun recipeIterator(): Iterator<Recipe> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun clearRecipes() {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun resetRecipes() {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getHelpMap(): HelpMap {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun createInventory(ih: InventoryHolder, it: InventoryType): Inventory {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun createInventory(ih: InventoryHolder, i: Int): Inventory {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun createInventory(ih: InventoryHolder, i: Int, string: String): Inventory {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getWorldType(): String {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getGenerateStructures(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getConnectionThrottle(): Long {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getMonsterSpawnLimit(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getAnimalSpawnLimit(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getWaterAnimalSpawnLimit(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun isPrimaryThread(): Boolean {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getMotd(): String {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getWarningState(): Warning.WarningState {
        return Warning.WarningState.DEFAULT
    }

    override fun getAmbientSpawnLimit(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getShutdownMessage(): String {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getItemFactory(): ItemFactory {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getScoreboardManager(): ScoreboardManager {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getServerIcon(): CachedServerIcon {
        throw UnsupportedOperationException("Not supported yet.")
    }

    @Throws(IllegalArgumentException::class, Exception::class)
    override fun loadServerIcon(file: File): CachedServerIcon {
        throw UnsupportedOperationException("Not supported yet.")
    }

    @Throws(IllegalArgumentException::class, Exception::class)
    override fun loadServerIcon(bufferedImage: BufferedImage): CachedServerIcon {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun setIdleTimeout(i: Int) {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getIdleTimeout(): Int {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getUnsafe(): UnsafeValues {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun spigot(): Server.Spigot {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getBanList(arg0: BanList.Type): BanList {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun getPlayer(arg0: UUID): Player? {
        for (player in players) {
            if (player.uniqueId == arg0) {
                return player
            }
        }
        return null
    }

    override fun getOfflinePlayer(arg0: UUID): org.bukkit.OfflinePlayer {
        if (arg0.toString().equals("3c9ebe1a-9098-43fd-bc0c-a369b76817ba", ignoreCase = true)) {
            return createOPlayer("testPlayer1")
        }
        if (arg0.toString().equals("f4a37409-5c40-3b2c-9cd6-57d3c5abdc76", ignoreCase = true)) {
            return createOPlayer("npc1")
        }
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun createInventory(arg0: InventoryHolder, arg1: InventoryType, arg2: String): Inventory {
        throw UnsupportedOperationException("Not supported yet.")
    }


    internal object FakePluginManager : PluginManager {
        var listeners = ArrayList<RegisteredListener>()

        @Throws(IllegalArgumentException::class)
        override fun registerInterface(loader: Class<out PluginLoader>) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPlugin(name: String): Plugin {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPlugins(): Array<Plugin> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isPluginEnabled(name: String): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isPluginEnabled(plugin: Plugin): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        @Throws(InvalidPluginException::class, InvalidDescriptionException::class, UnknownDependencyException::class)
        override fun loadPlugin(file: File): Plugin {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun loadPlugins(directory: File): Array<Plugin> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun disablePlugins() {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun clearPlugins() {
            throw UnsupportedOperationException("Not supported yet.")
        }

        @Throws(IllegalStateException::class)
        override fun callEvent(event: Event) {
            Logger.getLogger("Minecraft").info("Called event " + event.eventName)
            if (event is PlayerJoinEvent) {
                for (listener in listeners) {
                    //if we need a listener, call some custom events here
                }
            }
        }

        override fun registerEvents(listener: Listener, plugin: Plugin) {
            listeners.add(RegisteredListener(listener, null, null, plugin, false))
        }

        override fun registerEvent(event: Class<out Event>, listener: Listener, priority: EventPriority, executor: EventExecutor, plugin: Plugin) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun registerEvent(event: Class<out Event>, listener: Listener, priority: EventPriority, executor: EventExecutor, plugin: Plugin, ignoreCancelled: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun enablePlugin(plugin: Plugin) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun disablePlugin(plugin: Plugin) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPermission(name: String): Permission {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun addPermission(perm: Permission) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun removePermission(perm: Permission) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun removePermission(name: String) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getDefaultPermissions(op: Boolean): Set<Permission> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun recalculatePermissionDefaults(perm: Permission) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun subscribeToPermission(permission: String, permissible: Permissible) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unsubscribeFromPermission(permission: String, permissible: Permissible) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPermissionSubscriptions(permission: String): Set<Permissible> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun subscribeToDefaultPerms(op: Boolean, permissible: Permissible) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unsubscribeFromDefaultPerms(op: Boolean, permissible: Permissible) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getDefaultPermSubscriptions(op: Boolean): Set<Permissible> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPermissions(): Set<Permission> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun useTimings(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }
    }

    private class FakeWorld(private val name: String, private val env: World.Environment) : World {

        override fun getBlockAt(i: Int, i1: Int, i2: Int): Block {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getBlockAt(lctn: Location): Block {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getBlockTypeIdAt(i: Int, i1: Int, i2: Int): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getBlockTypeIdAt(lctn: Location): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getHighestBlockYAt(i: Int, i1: Int): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getHighestBlockYAt(lctn: Location): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getChunkAt(i: Int, i1: Int): Chunk {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getChunkAt(lctn: Location): Chunk {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getChunkAt(block: Block): Chunk {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isChunkLoaded(chunk: Chunk): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getLoadedChunks(): Array<Chunk> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun loadChunk(chunk: Chunk) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isChunkLoaded(i: Int, i1: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun loadChunk(i: Int, i1: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun loadChunk(i: Int, i1: Int, bln: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unloadChunk(i: Int, i1: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unloadChunk(i: Int, i1: Int, bln: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unloadChunk(i: Int, i1: Int, bln: Boolean, bln1: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unloadChunkRequest(i: Int, i1: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unloadChunkRequest(i: Int, i1: Int, bln: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun regenerateChunk(i: Int, i1: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun refreshChunk(i: Int, i1: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun dropItem(lctn: Location, `is`: ItemStack): Item {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun dropItemNaturally(lctn: Location, `is`: ItemStack): Item {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun spawnArrow(lctn: Location, vector: Vector, f: Float, f1: Float): Arrow {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun generateTree(lctn: Location, tt: TreeType): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun generateTree(lctn: Location, tt: TreeType, bcd: BlockChangeDelegate): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun spawnCreature(lctn: Location, ct: CreatureType): LivingEntity {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun strikeLightning(lctn: Location): LightningStrike {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun strikeLightningEffect(lctn: Location): LightningStrike {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getEntities(): List<Entity> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getLivingEntities(): List<LivingEntity> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPlayers(): List<Player> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getName(): String {
            return name
        }

        override fun getSpawnLocation(): Location {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setSpawnLocation(i: Int, i1: Int, i2: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getTime(): Long {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setTime(l: Long) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getFullTime(): Long {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setFullTime(l: Long) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun hasStorm(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setStorm(bln: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getWeatherDuration(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setWeatherDuration(i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isThundering(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setThundering(bln: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getThunderDuration(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setThunderDuration(i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getEnvironment(): World.Environment {
            return env
        }

        override fun getSeed(): Long {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPVP(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setPVP(bln: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun save() {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun createExplosion(d: Double, d1: Double, d2: Double, f: Float): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun createExplosion(lctn: Location, f: Float): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getGenerator(): ChunkGenerator {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getPopulators(): List<BlockPopulator> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun playEffect(lctn: Location, effect: Effect, i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun playEffect(lctn: Location, effect: Effect, i: Int, i1: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun createExplosion(d: Double, d1: Double, d2: Double, f: Float, bln: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun createExplosion(lctn: Location, f: Float, bln: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        @Throws(IllegalArgumentException::class)
        override fun <T : Entity> spawn(lctn: Location, type: Class<T>): T {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getEmptyChunkSnapshot(i: Int, i1: Int, bln: Boolean, bln1: Boolean): ChunkSnapshot {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setSpawnFlags(bln: Boolean, bln1: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getAllowAnimals(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getAllowMonsters(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getUID(): UUID {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getHighestBlockAt(i: Int, i1: Int): Block {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getHighestBlockAt(lctn: Location): Block {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getBiome(i: Int, i1: Int): Biome {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getTemperature(i: Int, i1: Int): Double {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getHumidity(i: Int, i1: Int): Double {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun unloadChunk(chunk: Chunk): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getMaxHeight(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getKeepSpawnInMemory(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setKeepSpawnInMemory(bln: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isAutoSave(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setAutoSave(bln: Boolean) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getDifficulty(): Difficulty {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setDifficulty(difficulty: Difficulty) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getSeaLevel(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getWorldFolder(): File {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun <T : Entity> getEntitiesByClass(vararg types: Class<T>): Collection<T> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getWorldType(): WorldType {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun sendPluginMessage(plugin: Plugin, string: String, bytes: ByteArray) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getListeningPluginChannels(): Set<String> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun canGenerateStructures(): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getTicksPerAnimalSpawns(): Long {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setTicksPerAnimalSpawns(i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getTicksPerMonsterSpawns(): Long {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setTicksPerMonsterSpawns(i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun <T : Entity> getEntitiesByClass(type: Class<T>): Collection<T> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getEntitiesByClasses(vararg types: Class<*>): Collection<Entity> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun spawnCreature(arg0: Location, arg1: EntityType): LivingEntity {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun <T> playEffect(lctn: Location, effect: Effect, t: T) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun <T> playEffect(lctn: Location, effect: Effect, t: T, i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setMetadata(string: String, mv: MetadataValue) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getMetadata(string: String): List<MetadataValue> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun hasMetadata(string: String): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun removeMetadata(string: String, plugin: Plugin) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setBiome(arg0: Int, arg1: Int, arg2: Biome) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getMonsterSpawnLimit(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setMonsterSpawnLimit(arg0: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getAnimalSpawnLimit(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setAnimalSpawnLimit(arg0: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getWaterAnimalSpawnLimit(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setWaterAnimalSpawnLimit(arg0: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun spawnEntity(lctn: Location, et: EntityType): Entity {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isChunkInUse(x: Int, z: Int): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        @Throws(IllegalArgumentException::class)
        override fun spawnFallingBlock(location: Location, material: Material, data: Byte): FallingBlock {
            throw UnsupportedOperationException("Not supported yet.")
        }

        @Throws(IllegalArgumentException::class)
        override fun spawnFallingBlock(location: Location, blockId: Int, blockData: Byte): FallingBlock {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun playSound(arg0: Location, arg1: Sound, arg2: Float, arg3: Float) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getAmbientSpawnLimit(): Int {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setAmbientSpawnLimit(i: Int) {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getGameRules(): Array<String> {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getGameRuleValue(string: String): String {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun setGameRuleValue(string: String, string1: String): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun isGameRule(string: String): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun spigot(): World.Spigot {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun createExplosion(d: Double, d1: Double, d2: Double, f: Float, bln: Boolean, bln1: Boolean): Boolean {
            throw UnsupportedOperationException("Not supported yet.")
        }

        override fun getWorldBorder(): WorldBorder {
            throw UnsupportedOperationException("Not supported yet.") //To change body of generated methods, choose Tools | Templates.
        }
    }
}
