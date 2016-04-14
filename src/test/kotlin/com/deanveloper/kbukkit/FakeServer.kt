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
import org.bukkit.enchantments.Enchantment
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
import org.bukkit.inventory.meta.ItemMeta
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
 * Based off of Essentials' FakeServer class, and converted to Kotlin.
 *
 * @author Essentials Team
 */
object FakeServer : Server {
	private var players: MutableList<Player> = ArrayList();
	private val worlds = ArrayList<World>();
	internal var pluginManager: PluginManager = FakePluginManager;

	init {
		if (Bukkit.getServer() == null) {
			Bukkit.setServer(this);
		}
	}

	override fun getName(): String {
		return "Essentials Fake Server";
	}

	override fun getVersion(): String {
		return "1.0";
	}

	override fun _INVALID_getOnlinePlayers(): Array<Player> {
		return players.toTypedArray();
	}

	override fun getOnlinePlayers() = players;

	fun setOnlinePlayers(players: MutableList<Player>) {
		this.players = players;
	}

	override fun getMaxPlayers() = 1;

	override fun getPort() = 25565;

	override fun getIp() = "127.0.0.1";

	override fun getServerName() = "Test Server";

	override fun getServerId() = "Test Server";

	override fun broadcastMessage(string: String): Int {
		var i = 0;
		players.forEach { it.sendMessage(string) };
		return i;
	}

	override fun getUpdateFolder() = "update";

	override fun getUpdateFolderFile(): File {
		throw UnsupportedOperationException()
	}

	override fun isHardcore(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getPlayer(string: String): Player? {
		return players.firstOrNull { it.name.equals(string, true) };
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
				throw UnsupportedOperationException()
			}

			override fun scheduleSyncDelayedTask(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long): Int {
				throw UnsupportedOperationException()
			}

			override fun scheduleSyncDelayedTask(plugin: Plugin, r: Runnable): Int {
				throw UnsupportedOperationException()
			}

			override fun scheduleSyncDelayedTask(plugin: Plugin, bukkitRunnable: BukkitRunnable): Int {
				throw UnsupportedOperationException()
			}

			override fun scheduleSyncRepeatingTask(plugin: Plugin, r: Runnable, l: Long, l1: Long): Int {
				throw UnsupportedOperationException()
			}

			override fun scheduleSyncRepeatingTask(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long, l1: Long): Int {
				throw UnsupportedOperationException()
			}

			override fun scheduleAsyncRepeatingTask(plugin: Plugin, r: Runnable, l: Long, l1: Long): Int {
				throw UnsupportedOperationException()
			}

			override fun <T> callSyncMethod(plugin: Plugin, clbl: Callable<T>): Future<T> {
				throw UnsupportedOperationException()
			}

			override fun cancelTask(i: Int) {
				throw UnsupportedOperationException()
			}

			override fun cancelTasks(plugin: Plugin) {
				throw UnsupportedOperationException()
			}

			override fun cancelAllTasks() {
				throw UnsupportedOperationException()
			}

			override fun isCurrentlyRunning(i: Int): Boolean {
				throw UnsupportedOperationException()
			}

			override fun isQueued(i: Int): Boolean {
				throw UnsupportedOperationException()
			}

			override fun getActiveWorkers(): List<BukkitWorker> {
				throw UnsupportedOperationException()
			}

			override fun getPendingTasks(): List<BukkitTask> {
				throw UnsupportedOperationException()
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
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskAsynchronously(plugin: Plugin, r: Runnable): BukkitTask? {
				r.run()
				return null
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskAsynchronously(plugin: Plugin, bukkitRunnable: BukkitRunnable): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskLater(plugin: Plugin, r: Runnable, l: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskLater(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskLaterAsynchronously(plugin: Plugin, r: Runnable, l: Long): BukkitTask? {
				r.run()
				return null
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskLaterAsynchronously(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskTimer(plugin: Plugin, r: Runnable, l: Long, l1: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskTimer(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long, l1: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskTimerAsynchronously(plugin: Plugin, r: Runnable, l: Long, l1: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			@Throws(IllegalArgumentException::class)
			override fun runTaskTimerAsynchronously(plugin: Plugin, bukkitRunnable: BukkitRunnable, l: Long, l1: Long): BukkitTask {
				throw UnsupportedOperationException()
			}

			override fun scheduleAsyncDelayedTask(plugin: Plugin, r: Runnable, l: Long): Int {
				throw UnsupportedOperationException()
			}

			override fun scheduleAsyncDelayedTask(plugin: Plugin, r: Runnable): Int {
				throw UnsupportedOperationException()
			}
		}
	}

	override fun getServicesManager(): ServicesManager {
		throw UnsupportedOperationException()
	}

	override fun getWorlds(): List<World> = worlds;

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

	override fun getLogger() = Logger.getLogger("Minecraft");

	override fun getPluginCommand(string: String): PluginCommand {
		throw UnsupportedOperationException()
	}

	override fun savePlayers() {
	}

	override fun dispatchCommand(cs: CommandSender, string: String): Boolean {
		throw UnsupportedOperationException()
	}

	override fun configureDbConfig(sc: ServerConfig) {
		throw UnsupportedOperationException()
	}

	override fun addRecipe(recipe: Recipe): Boolean {
		throw UnsupportedOperationException()
	}

	override fun createWorld(creator: WorldCreator): World {
		throw UnsupportedOperationException()
	}

	override fun unloadWorld(string: String, bln: Boolean): Boolean {
		throw UnsupportedOperationException()
	}

	override fun unloadWorld(world: World, bln: Boolean): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getCommandAliases(): Map<String, Array<String>> {
		throw UnsupportedOperationException()
	}

	override fun getSpawnRadius(): Int {
		throw UnsupportedOperationException()
	}

	override fun setSpawnRadius(i: Int) {
		throw UnsupportedOperationException()
	}

	override fun getOnlineMode(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getWorld(uuid: UUID): World {
		throw UnsupportedOperationException()
	}

	override fun getViewDistance(): Int {
		throw UnsupportedOperationException()
	}

	override fun getAllowNether(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun hasWhitelist(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getMap(s: Short): MapView {
		throw UnsupportedOperationException()
	}

	override fun createMap(world: World): MapView {
		throw UnsupportedOperationException()
	}

	override fun getAllowFlight(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun setWhitelist(bln: Boolean) {
		throw UnsupportedOperationException()
	}

	override fun getWhitelistedPlayers(): Set<org.bukkit.OfflinePlayer> {
		throw UnsupportedOperationException()
	}

	override fun reloadWhitelist() {
		throw UnsupportedOperationException()
	}

	override fun getPlayerExact(string: String): Player {
		throw UnsupportedOperationException()
	}

	override fun shutdown() {
		throw UnsupportedOperationException()
	}

	override fun broadcast(string: String, string1: String): Int {
		throw UnsupportedOperationException()
	}

	override fun getOfflinePlayer(string: String): org.bukkit.OfflinePlayer {
		return getPlayer(string) ?: createPlayer(string);
	}

	fun createPlayer(string: String): org.bukkit.OfflinePlayer {
		return object : org.bukkit.OfflinePlayer {
			override fun isOnline(): Boolean {
				return false
			}

			override fun getName(): String {
				return string
			}

			override fun isBanned(): Boolean {
				throw UnsupportedOperationException()
			}

			override fun setBanned(bln: Boolean) {
				throw UnsupportedOperationException()
			}

			override fun isWhitelisted(): Boolean {
				throw UnsupportedOperationException()
			}

			override fun setWhitelisted(bln: Boolean) {
				throw UnsupportedOperationException()
			}

			override fun getPlayer(): Player {
				throw UnsupportedOperationException()
			}

			override fun isOp(): Boolean {
				throw UnsupportedOperationException()
			}

			override fun setOp(bln: Boolean) {
				throw UnsupportedOperationException()
			}

			override fun serialize(): Map<String, Any> {
				throw UnsupportedOperationException()
			}

			override fun getFirstPlayed(): Long {
				throw UnsupportedOperationException()
			}

			override fun getLastPlayed(): Long {
				throw UnsupportedOperationException()
			}

			override fun hasPlayedBefore(): Boolean {
				throw UnsupportedOperationException()
			}

			override fun getBedSpawnLocation(): Location {
				throw UnsupportedOperationException()
			}

			override fun getUniqueId(): UUID? {
				return UUID(string.hashCode().toLong(), string.hashCode().toLong());
			}
		}
	}

	override fun getIPBans(): Set<String> {
		throw UnsupportedOperationException()
	}

	override fun banIP(string: String) {
		throw UnsupportedOperationException()
	}

	override fun unbanIP(string: String) {
		throw UnsupportedOperationException()
	}

	override fun getBannedPlayers(): Set<org.bukkit.OfflinePlayer> {
		throw UnsupportedOperationException()
	}

	override fun getDefaultGameMode(): GameMode {
		throw UnsupportedOperationException()
	}

	override fun setDefaultGameMode(gamemode: GameMode) {
		throw UnsupportedOperationException()
	}

	override fun getConsoleSender(): ConsoleCommandSender {
		return object : ConsoleCommandSender {
			override fun sendMessage(message: String) {
				println("Console message: " + message)
			}

			override fun sendMessage(messages: Array<String>) {
				messages.forEach { println("Console message: " + it) }
				for (message in messages) {
					println("Console message: " + message)
				}
			}

			override fun getServer(): Server {
				throw UnsupportedOperationException()
			}

			override fun getName(): String {
				throw UnsupportedOperationException()
			}

			override fun isPermissionSet(name: String): Boolean {
				throw UnsupportedOperationException()
			}

			override fun isPermissionSet(perm: Permission): Boolean {
				throw UnsupportedOperationException()
			}

			override fun hasPermission(name: String): Boolean {
				return true
			}

			override fun hasPermission(perm: Permission): Boolean {
				throw UnsupportedOperationException()
			}

			override fun addAttachment(plugin: Plugin, name: String, value: Boolean): PermissionAttachment {
				throw UnsupportedOperationException()
			}

			override fun addAttachment(plugin: Plugin): PermissionAttachment {
				throw UnsupportedOperationException()
			}

			override fun addAttachment(plugin: Plugin, name: String, value: Boolean, ticks: Int): PermissionAttachment {
				throw UnsupportedOperationException()
			}

			override fun addAttachment(plugin: Plugin, ticks: Int): PermissionAttachment {
				throw UnsupportedOperationException()
			}

			override fun removeAttachment(attachment: PermissionAttachment) {
				throw UnsupportedOperationException()
			}

			override fun recalculatePermissions() {
				throw UnsupportedOperationException()
			}

			override fun getEffectivePermissions(): Set<PermissionAttachmentInfo> {
				throw UnsupportedOperationException()
			}

			override fun isOp(): Boolean {
				return true;
			}

			override fun setOp(value: Boolean) {
				throw UnsupportedOperationException()
			}

			override fun isConversing(): Boolean {
				throw UnsupportedOperationException()
			}

			override fun acceptConversationInput(input: String) {
				throw UnsupportedOperationException()
			}

			override fun beginConversation(conversation: Conversation): Boolean {
				throw UnsupportedOperationException()
			}

			override fun abandonConversation(conversation: Conversation) {
				throw UnsupportedOperationException()
			}

			override fun abandonConversation(conversation: Conversation, details: ConversationAbandonedEvent) {
				throw UnsupportedOperationException()
			}

			override fun sendRawMessage(message: String) {
				throw UnsupportedOperationException()
			}
		}
	}

	override fun getOperators(): MutableSet<OfflinePlayer>? {
		throw UnsupportedOperationException()
	}

	override fun getBukkitVersion(): String {
		return "Essentials Fake-Server"
	}

	override fun getWorldContainer(): File {
		throw UnsupportedOperationException()
	}

	override fun getOfflinePlayers(): Array<OfflinePlayer> {
		throw UnsupportedOperationException()
	}

	override fun getAllowEnd(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getMessenger(): Messenger {
		throw UnsupportedOperationException()
	}

	override fun sendPluginMessage(plugin: Plugin, string: String, bytes: ByteArray) {
		throw UnsupportedOperationException()
	}

	override fun getListeningPluginChannels(): Set<String> {
		throw UnsupportedOperationException()
	}

	override fun useExactLoginLocation(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getTicksPerAnimalSpawns(): Int {
		throw UnsupportedOperationException()
	}

	override fun getTicksPerMonsterSpawns(): Int {
		throw UnsupportedOperationException()
	}

	override fun getRecipesFor(`is`: ItemStack): List<Recipe> {
		throw UnsupportedOperationException()
	}

	override fun recipeIterator(): Iterator<Recipe> {
		throw UnsupportedOperationException()
	}

	override fun clearRecipes() {
		throw UnsupportedOperationException()
	}

	override fun resetRecipes() {
		throw UnsupportedOperationException()
	}

	override fun getHelpMap(): HelpMap {
		throw UnsupportedOperationException()
	}

	override fun createInventory(ih: InventoryHolder, it: InventoryType): Inventory {
		throw UnsupportedOperationException()
	}

	override fun createInventory(ih: InventoryHolder, i: Int): Inventory {
		throw UnsupportedOperationException()
	}

	override fun createInventory(ih: InventoryHolder, i: Int, string: String): Inventory {
		throw UnsupportedOperationException()
	}

	override fun getWorldType(): String {
		throw UnsupportedOperationException()
	}

	override fun getGenerateStructures(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getConnectionThrottle(): Long {
		throw UnsupportedOperationException()
	}

	override fun getMonsterSpawnLimit(): Int {
		throw UnsupportedOperationException()
	}

	override fun getAnimalSpawnLimit(): Int {
		throw UnsupportedOperationException()
	}

	override fun getWaterAnimalSpawnLimit(): Int {
		throw UnsupportedOperationException()
	}

	override fun isPrimaryThread(): Boolean {
		throw UnsupportedOperationException()
	}

	override fun getMotd(): String {
		throw UnsupportedOperationException()
	}

	override fun getWarningState(): Warning.WarningState {
		return Warning.WarningState.DEFAULT;
	}

	override fun getAmbientSpawnLimit(): Int {
		throw UnsupportedOperationException()
	}

	override fun getShutdownMessage(): String {
		throw UnsupportedOperationException()
	}

	override fun getItemFactory(): ItemFactory {
		throw UnsupportedOperationException()
	}

	override fun getScoreboardManager(): ScoreboardManager {
		throw UnsupportedOperationException()
	}

	override fun getServerIcon(): CachedServerIcon {
		throw UnsupportedOperationException()
	}

	@Throws(IllegalArgumentException::class, Exception::class)
	override fun loadServerIcon(file: File): CachedServerIcon {
		throw UnsupportedOperationException()
	}

	@Throws(IllegalArgumentException::class, Exception::class)
	override fun loadServerIcon(bufferedImage: BufferedImage): CachedServerIcon {
		throw UnsupportedOperationException()
	}

	override fun setIdleTimeout(i: Int) {
		throw UnsupportedOperationException()
	}

	override fun getIdleTimeout(): Int {
		throw UnsupportedOperationException()
	}

	override fun getUnsafe(): UnsafeValues {
		throw UnsupportedOperationException()
	}

	override fun spigot(): Server.Spigot {
		throw UnsupportedOperationException()
	}

	override fun getBanList(arg0: BanList.Type): BanList {
		throw UnsupportedOperationException()
	}

	override fun getPlayer(arg0: UUID): Player? {
		return players.firstOrNull { it.uniqueId == arg0 };
	}

	override fun getOfflinePlayer(arg0: UUID): OfflinePlayer {
		return getPlayer(arg0) ?: throw UnsupportedOperationException();
	}

	override fun createInventory(arg0: InventoryHolder, arg1: InventoryType, arg2: String): Inventory {
		throw UnsupportedOperationException()
	}


	internal object FakePluginManager : PluginManager {
		var listeners = ArrayList<RegisteredListener>()

		@Throws(IllegalArgumentException::class)
		override fun registerInterface(loader: Class<out PluginLoader>) {
			throw UnsupportedOperationException()
		}

		override fun getPlugin(name: String): Plugin {
			throw UnsupportedOperationException()
		}

		override fun getPlugins(): Array<Plugin> {
			throw UnsupportedOperationException()
		}

		override fun isPluginEnabled(name: String): Boolean {
			throw UnsupportedOperationException()
		}

		override fun isPluginEnabled(plugin: Plugin): Boolean {
			throw UnsupportedOperationException()
		}

		@Throws(InvalidPluginException::class, InvalidDescriptionException::class, UnknownDependencyException::class)
		override fun loadPlugin(file: File): Plugin {
			throw UnsupportedOperationException()
		}

		override fun loadPlugins(directory: File): Array<Plugin> {
			throw UnsupportedOperationException()
		}

		override fun disablePlugins() {
			throw UnsupportedOperationException()
		}

		override fun clearPlugins() {
			throw UnsupportedOperationException()
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
			throw UnsupportedOperationException()
		}

		override fun registerEvent(event: Class<out Event>, listener: Listener, priority: EventPriority, executor: EventExecutor, plugin: Plugin, ignoreCancelled: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun enablePlugin(plugin: Plugin) {
			throw UnsupportedOperationException()
		}

		override fun disablePlugin(plugin: Plugin) {
			throw UnsupportedOperationException()
		}

		override fun getPermission(name: String): Permission {
			throw UnsupportedOperationException()
		}

		override fun addPermission(perm: Permission) {
			throw UnsupportedOperationException()
		}

		override fun removePermission(perm: Permission) {
			throw UnsupportedOperationException()
		}

		override fun removePermission(name: String) {
			throw UnsupportedOperationException()
		}

		override fun getDefaultPermissions(op: Boolean): Set<Permission> {
			throw UnsupportedOperationException()
		}

		override fun recalculatePermissionDefaults(perm: Permission) {
			throw UnsupportedOperationException()
		}

		override fun subscribeToPermission(permission: String, permissible: Permissible) {
			throw UnsupportedOperationException()
		}

		override fun unsubscribeFromPermission(permission: String, permissible: Permissible) {
			throw UnsupportedOperationException()
		}

		override fun getPermissionSubscriptions(permission: String): Set<Permissible> {
			throw UnsupportedOperationException()
		}

		override fun subscribeToDefaultPerms(op: Boolean, permissible: Permissible) {
			throw UnsupportedOperationException()
		}

		override fun unsubscribeFromDefaultPerms(op: Boolean, permissible: Permissible) {
			throw UnsupportedOperationException()
		}

		override fun getDefaultPermSubscriptions(op: Boolean): Set<Permissible> {
			throw UnsupportedOperationException()
		}

		override fun getPermissions(): Set<Permission> {
			throw UnsupportedOperationException()
		}

		override fun useTimings(): Boolean {
			throw UnsupportedOperationException()
		}
	}

	private class FakeWorld(private val name: String, private val env: World.Environment) : World {

		override fun getBlockAt(i: Int, i1: Int, i2: Int): Block {
			throw UnsupportedOperationException()
		}

		override fun getBlockAt(lctn: Location): Block {
			throw UnsupportedOperationException()
		}

		override fun getBlockTypeIdAt(i: Int, i1: Int, i2: Int): Int {
			throw UnsupportedOperationException()
		}

		override fun getBlockTypeIdAt(lctn: Location): Int {
			throw UnsupportedOperationException()
		}

		override fun getHighestBlockYAt(i: Int, i1: Int): Int {
			throw UnsupportedOperationException()
		}

		override fun getHighestBlockYAt(lctn: Location): Int {
			throw UnsupportedOperationException()
		}

		override fun getChunkAt(i: Int, i1: Int): Chunk {
			throw UnsupportedOperationException()
		}

		override fun getChunkAt(lctn: Location): Chunk {
			throw UnsupportedOperationException()
		}

		override fun getChunkAt(block: Block): Chunk {
			throw UnsupportedOperationException()
		}

		override fun isChunkLoaded(chunk: Chunk): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getLoadedChunks(): Array<Chunk> {
			throw UnsupportedOperationException()
		}

		override fun loadChunk(chunk: Chunk) {
			throw UnsupportedOperationException()
		}

		override fun isChunkLoaded(i: Int, i1: Int): Boolean {
			throw UnsupportedOperationException()
		}

		override fun loadChunk(i: Int, i1: Int) {
			throw UnsupportedOperationException()
		}

		override fun loadChunk(i: Int, i1: Int, bln: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		override fun unloadChunk(i: Int, i1: Int): Boolean {
			throw UnsupportedOperationException()
		}

		override fun unloadChunk(i: Int, i1: Int, bln: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		override fun unloadChunk(i: Int, i1: Int, bln: Boolean, bln1: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		override fun unloadChunkRequest(i: Int, i1: Int): Boolean {
			throw UnsupportedOperationException()
		}

		override fun unloadChunkRequest(i: Int, i1: Int, bln: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		override fun regenerateChunk(i: Int, i1: Int): Boolean {
			throw UnsupportedOperationException()
		}

		override fun refreshChunk(i: Int, i1: Int): Boolean {
			throw UnsupportedOperationException()
		}

		override fun dropItem(lctn: Location, `is`: ItemStack): Item {
			throw UnsupportedOperationException()
		}

		override fun dropItemNaturally(lctn: Location, `is`: ItemStack): Item {
			throw UnsupportedOperationException()
		}

		override fun spawnArrow(lctn: Location, vector: Vector, f: Float, f1: Float): Arrow {
			throw UnsupportedOperationException()
		}

		override fun generateTree(lctn: Location, tt: TreeType): Boolean {
			throw UnsupportedOperationException()
		}

		override fun generateTree(lctn: Location, tt: TreeType, bcd: BlockChangeDelegate): Boolean {
			throw UnsupportedOperationException()
		}

		override fun spawnCreature(lctn: Location, ct: CreatureType): LivingEntity {
			throw UnsupportedOperationException()
		}

		override fun strikeLightning(lctn: Location): LightningStrike {
			throw UnsupportedOperationException()
		}

		override fun strikeLightningEffect(lctn: Location): LightningStrike {
			throw UnsupportedOperationException()
		}

		override fun getEntities(): List<Entity> {
			throw UnsupportedOperationException()
		}

		override fun getLivingEntities(): List<LivingEntity> {
			throw UnsupportedOperationException()
		}

		override fun getPlayers(): List<Player> {
			throw UnsupportedOperationException()
		}

		override fun getName(): String {
			return name
		}

		override fun getSpawnLocation(): Location {
			throw UnsupportedOperationException()
		}

		override fun setSpawnLocation(i: Int, i1: Int, i2: Int): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getTime(): Long {
			throw UnsupportedOperationException()
		}

		override fun setTime(l: Long) {
			throw UnsupportedOperationException()
		}

		override fun getFullTime(): Long {
			throw UnsupportedOperationException()
		}

		override fun setFullTime(l: Long) {
			throw UnsupportedOperationException()
		}

		override fun hasStorm(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun setStorm(bln: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun getWeatherDuration(): Int {
			throw UnsupportedOperationException()
		}

		override fun setWeatherDuration(i: Int) {
			throw UnsupportedOperationException()
		}

		override fun isThundering(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun setThundering(bln: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun getThunderDuration(): Int {
			throw UnsupportedOperationException()
		}

		override fun setThunderDuration(i: Int) {
			throw UnsupportedOperationException()
		}

		override fun getEnvironment(): World.Environment {
			return env
		}

		override fun getSeed(): Long {
			throw UnsupportedOperationException()
		}

		override fun getPVP(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun setPVP(bln: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun save() {
			throw UnsupportedOperationException()
		}

		override fun createExplosion(d: Double, d1: Double, d2: Double, f: Float): Boolean {
			throw UnsupportedOperationException()
		}

		override fun createExplosion(lctn: Location, f: Float): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getGenerator(): ChunkGenerator {
			throw UnsupportedOperationException()
		}

		override fun getPopulators(): List<BlockPopulator> {
			throw UnsupportedOperationException()
		}

		override fun playEffect(lctn: Location, effect: Effect, i: Int) {
			throw UnsupportedOperationException()
		}

		override fun playEffect(lctn: Location, effect: Effect, i: Int, i1: Int) {
			throw UnsupportedOperationException()
		}

		override fun createExplosion(d: Double, d1: Double, d2: Double, f: Float, bln: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		override fun createExplosion(lctn: Location, f: Float, bln: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		@Throws(IllegalArgumentException::class)
		override fun <T : Entity> spawn(lctn: Location, type: Class<T>): T {
			throw UnsupportedOperationException()
		}

		override fun getEmptyChunkSnapshot(i: Int, i1: Int, bln: Boolean, bln1: Boolean): ChunkSnapshot {
			throw UnsupportedOperationException()
		}

		override fun setSpawnFlags(bln: Boolean, bln1: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun getAllowAnimals(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getAllowMonsters(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getUID(): UUID {
			throw UnsupportedOperationException()
		}

		override fun getHighestBlockAt(i: Int, i1: Int): Block {
			throw UnsupportedOperationException()
		}

		override fun getHighestBlockAt(lctn: Location): Block {
			throw UnsupportedOperationException()
		}

		override fun getBiome(i: Int, i1: Int): Biome {
			throw UnsupportedOperationException()
		}

		override fun getTemperature(i: Int, i1: Int): Double {
			throw UnsupportedOperationException()
		}

		override fun getHumidity(i: Int, i1: Int): Double {
			throw UnsupportedOperationException()
		}

		override fun unloadChunk(chunk: Chunk): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getMaxHeight(): Int {
			throw UnsupportedOperationException()
		}

		override fun getKeepSpawnInMemory(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun setKeepSpawnInMemory(bln: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun isAutoSave(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun setAutoSave(bln: Boolean) {
			throw UnsupportedOperationException()
		}

		override fun getDifficulty(): Difficulty {
			throw UnsupportedOperationException()
		}

		override fun setDifficulty(difficulty: Difficulty) {
			throw UnsupportedOperationException()
		}

		override fun getSeaLevel(): Int {
			throw UnsupportedOperationException()
		}

		override fun getWorldFolder(): File {
			throw UnsupportedOperationException()
		}

		override fun <T : Entity> getEntitiesByClass(vararg types: Class<T>): Collection<T> {
			throw UnsupportedOperationException()
		}

		override fun getWorldType(): WorldType {
			throw UnsupportedOperationException()
		}

		override fun sendPluginMessage(plugin: Plugin, string: String, bytes: ByteArray) {
			throw UnsupportedOperationException()
		}

		override fun getListeningPluginChannels(): Set<String> {
			throw UnsupportedOperationException()
		}

		override fun canGenerateStructures(): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getTicksPerAnimalSpawns(): Long {
			throw UnsupportedOperationException()
		}

		override fun setTicksPerAnimalSpawns(i: Int) {
			throw UnsupportedOperationException()
		}

		override fun getTicksPerMonsterSpawns(): Long {
			throw UnsupportedOperationException()
		}

		override fun setTicksPerMonsterSpawns(i: Int) {
			throw UnsupportedOperationException()
		}

		override fun <T : Entity> getEntitiesByClass(type: Class<T>): Collection<T> {
			throw UnsupportedOperationException()
		}

		override fun getEntitiesByClasses(vararg types: Class<*>): Collection<Entity> {
			throw UnsupportedOperationException()
		}

		override fun spawnCreature(arg0: Location, arg1: EntityType): LivingEntity {
			throw UnsupportedOperationException()
		}

		override fun <T> playEffect(lctn: Location, effect: Effect, t: T) {
			throw UnsupportedOperationException()
		}

		override fun <T> playEffect(lctn: Location, effect: Effect, t: T, i: Int) {
			throw UnsupportedOperationException()
		}

		override fun setMetadata(string: String, mv: MetadataValue) {
			throw UnsupportedOperationException()
		}

		override fun getMetadata(string: String): List<MetadataValue> {
			throw UnsupportedOperationException()
		}

		override fun hasMetadata(string: String): Boolean {
			throw UnsupportedOperationException()
		}

		override fun removeMetadata(string: String, plugin: Plugin) {
			throw UnsupportedOperationException()
		}

		override fun setBiome(arg0: Int, arg1: Int, arg2: Biome) {
			throw UnsupportedOperationException()
		}

		override fun getMonsterSpawnLimit(): Int {
			throw UnsupportedOperationException()
		}

		override fun setMonsterSpawnLimit(arg0: Int) {
			throw UnsupportedOperationException()
		}

		override fun getAnimalSpawnLimit(): Int {
			throw UnsupportedOperationException()
		}

		override fun setAnimalSpawnLimit(arg0: Int) {
			throw UnsupportedOperationException()
		}

		override fun getWaterAnimalSpawnLimit(): Int {
			throw UnsupportedOperationException()
		}

		override fun setWaterAnimalSpawnLimit(arg0: Int) {
			throw UnsupportedOperationException()
		}

		override fun spawnEntity(lctn: Location, et: EntityType): Entity {
			throw UnsupportedOperationException()
		}

		override fun isChunkInUse(x: Int, z: Int): Boolean {
			throw UnsupportedOperationException()
		}

		@Throws(IllegalArgumentException::class)
		override fun spawnFallingBlock(location: Location, material: Material, data: Byte): FallingBlock {
			throw UnsupportedOperationException()
		}

		@Throws(IllegalArgumentException::class)
		override fun spawnFallingBlock(location: Location, blockId: Int, blockData: Byte): FallingBlock {
			throw UnsupportedOperationException()
		}

		override fun playSound(arg0: Location, arg1: Sound, arg2: Float, arg3: Float) {
			throw UnsupportedOperationException()
		}

		override fun getAmbientSpawnLimit(): Int {
			throw UnsupportedOperationException()
		}

		override fun setAmbientSpawnLimit(i: Int) {
			throw UnsupportedOperationException()
		}

		override fun getGameRules(): Array<String> {
			throw UnsupportedOperationException()
		}

		override fun getGameRuleValue(string: String): String {
			throw UnsupportedOperationException()
		}

		override fun setGameRuleValue(string: String, string1: String): Boolean {
			throw UnsupportedOperationException()
		}

		override fun isGameRule(string: String): Boolean {
			throw UnsupportedOperationException()
		}

		override fun spigot(): World.Spigot {
			throw UnsupportedOperationException()
		}

		override fun createExplosion(d: Double, d1: Double, d2: Double, f: Float, bln: Boolean, bln1: Boolean): Boolean {
			throw UnsupportedOperationException()
		}

		override fun getWorldBorder(): WorldBorder {
			throw UnsupportedOperationException() //To change body of generated methods, choose Tools | Templates.
		}
	}
}
