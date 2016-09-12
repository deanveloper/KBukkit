@file:Suppress("unused")

package com.deanveloper.kbukkit

import com.avaje.ebean.config.ServerConfig
import org.bukkit.*
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarFlag
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.command.CommandException
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.generator.ChunkGenerator
import org.bukkit.help.HelpMap
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.messaging.Messenger
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.util.CachedServerIcon
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import java.util.logging.Logger

/**
 * @author Dean
 */

/**
 * The [Server] singleton
 */
var server: Server
    get() = Bukkit.getServer()
    set(value) = Bukkit.setServer(value)

/**
 * Gets the name of this server implementation.
 *
 * @return name of this server implementation
 */
val name: String
    get() = server.name

/**
 * Gets the version string of this server implementation.
 *
 * @return version of this server implementation
 */
val version: String
    get() = server.version

/**
 * Gets the Bukkit version that this server is running.
 *
 * @return version of Bukkit
 */
val bukkitVersion: String
    get() = server.bukkitVersion

/**
 * Gets a view of all currently logged in players. This [Collection] is a reused
 * object, making some operations like [Collection.size]
 * zero-allocation.
 *
 * The collection is a view backed by the internal representation, such
 * that, changes to the internal state of the server will be reflected
 * immediately. However, the reuse of the returned collection (identity)
 * is not strictly guaranteed for future or all implementations. Casting
 * the collection, or relying on interface implementations (like [Serializable] or [List]), is deprecated.
 *
 * Iteration behavior is undefined outside of self-contained main-thread
 * uses. Normal and immediate iterator use without consequences that
 * affect the collection are fully supported. The effects following
 * (non-exhaustive) [teleportation][Entity.teleport],
 * [death][Player.setHealth], and [kicking][Player.kickPlayer] are undefined. Any use of this collection from
 * asynchronous threads is unsafe.

 * @return a view of currently online players.
 */
val onlinePlayers: Collection<Player>
    get() = server.onlinePlayers

/**
 * Get the maximum amount of players which can login to this server.
 *
 * @return the amount of players this server allows
 */
val maxPlayers: Int
    get() = server.maxPlayers

/**
 * Get the game port that the server runs on.
 *
 * @return the port number of this server
 */
val port: Int
    get() = server.port

/**
 * Get the view distance from this server.
 *
 * @return the view distance from this server.
 */
val viewDistance: Int
    get() = server.viewDistance

/**
 * Get the IP that this server is bound to, or empty string if not
 * specified.
 *
 * @return the IP string that this server is bound to, otherwise empty string
 */
val ip: String
    get() = server.ip

/**
 * Get the name of this server.
 *
 * @return the name of this server
 */
val serverName: String
    get() = server.serverName

/**
 * Get an ID of this server. The ID is a simple generally alphanumeric ID
 * that can be used for uniquely identifying this server.
 *
 * @return the ID of this server
 */
val serverId: String
    get() = server.serverId

/**
 * Get world type (level-type setting) for default world.
 *
 * @return the value of level-type (e.g. DEFAULT, FLAT, DEFAULT_1_1)
 */
val worldType: String
    get() = server.worldType

/**
 * Get generate-structures setting.
 *
 * @return true if structure generation is enabled, false otherwise
 */
val generateStructures: Boolean
    get() = server.generateStructures

/**
 * Gets whether this server allows the End or not.
 *
 * @return whether this server allows the End or not
 */
val allowEnd: Boolean
    get() = server.allowEnd

/**
 * Gets whether this server allows the Nether or not.
 *
 * @return whether this server allows the Nether or not
 */
val allowNether: Boolean
    get() = server.allowNether

/**
 * Gets whether this server has a whitelist or not.
 *
 * @return whether this server has a whitelist or not
 */
var hasWhitelist: Boolean
    get() = server.hasWhitelist()
    set(value) = server.setWhitelist(value)

/**
 * Gets a list of whitelisted players.
 *
 * @return a set containing all whitelisted players
 */
val whitelistedPlayers: Set<OfflinePlayer>
    get() = server.whitelistedPlayers

/**
 * Reloads the whitelist from disk.
 */
fun reloadWhitelist() = server.reloadWhitelist()

/**
 * Gets the name of the update folder. The update folder is used to safely
 * update plugins at the right moment on a plugin load.
 *
 *
 * The update folder name is relative to the plugins folder.
 *
 * @return the name of the update folder
 */
val updateFolder: String
    get() = server.updateFolder

/**
 * Gets the update folder. The update folder is used to safely update
 * plugins at the right moment on a plugin load.
 *
 * @return the update folder
 */
val updateFolderFile: File
    get() = server.updateFolderFile

/**
 * Gets the value of the connection throttle setting.
 *
 * @return the value of the connection throttle setting
 */
val connectionThrottle: Long
    get() = server.connectionThrottle

/**
 * Gets default ticks per animal spawns value.
 *
 *
 * **Example Usage:**
 *
 * A value of 1 will mean the server will attempt to spawn monsters
 * every tick.
 * A value of 400 will mean the server will attempt to spawn monsters
 * every 400th tick.
 * A value below 0 will be reset back to Minecraft's default.
 *
 *
 *
 * **Note:** If set to 0, animal spawning will be disabled. We
 * recommend using spawn-animals to control this instead.
 *
 *
 * Minecraft default: 400.
 *
 * @return the default ticks per animal spawns value
 */
val ticksPerAnimalSpawns: Int
    get() = server.ticksPerAnimalSpawns

/**
 * Gets the default ticks per monster spawns value.
 *
 *
 * **Example Usage:**
 *
 *  * A value of 1 will mean the server will attempt to spawn monsters
 * every tick.
 *  * A value of 400 will mean the server will attempt to spawn monsters
 * every 400th tick.
 *  * A value below 0 will be reset back to Minecraft's default.
 *
 *
 *
 * **Note:** If set to 0, monsters spawning will be disabled. We
 * recommend using spawn-monsters to control this instead.
 *
 *
 * Minecraft default: 1.

 * @return the default ticks per monsters spawn value
 */
val ticksPerMonsterSpawns: Int
    get() = server.ticksPerMonsterSpawns

/**
 * Gets a player object by the given username.
 *
 *
 * This method may not return objects for offline players.
 *
 * @param name the name to look up
 * *
 * @return a player if one was found, null otherwise
 */
@Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))
fun getPlayer(name: String): Player? = server.getPlayer(name)

/**
 * Gets the player with the exact given name, case insensitive.
 *
 * @param name Exact name of the player to retrieve
 *
 * @return a player object if one was found, null otherwise
 */
@Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))
fun getPlayerExact(name: String): Player? = server.getPlayerExact(name)

/**
 * Attempts to match any players with the given name, and returns a list
 * of all possibly matches.
 *
 * This list is not sorted in any particular order. If an exact match is
 * found, the returned list will only contain a single result.
 *
 * @param name the (partial) name to match
 *
 * @return list of all possible players
 */
@Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))
fun matchPlayer(name: String): List<Player> = server.matchPlayer(name)

/**
 * Gets the player with the given UUID.
 *
 * @param id UUID of the player to retrieve
 *
 * @return a player object if one was found, null otherwise
 */
fun getPlayer(id: UUID): Player? = server.getPlayer(id)

/**
 * Gets the plugin manager for interfacing with plugins.
 *
 * @return a plugin manager for this Server instance
 */
val pluginManager: PluginManager
    get() = server.pluginManager

/**
 * Gets the scheduler for managing scheduled events.
 *
 * @return a scheduling service for this server
 */
val scheduler: BukkitScheduler
    get() = server.scheduler

/**
 * Gets a services manager.
 *
 * @return s services manager
 */
val servicesManager: ServicesManager
    get() = server.servicesManager

/**
 * Gets a list of all worlds on this server.
 *
 * @return a list of worlds
 */
val worlds: List<World>
    get() = server.worlds

/**
 * Creates or loads a world with the given name using the specified
 * options.
 *
 * If the world is already loaded, it will just return the equivalent of
 * getWorld(creator.name()).
 *
 * @param creator the options to use when creating the world
 *
 * @return newly created or loaded world
 */
fun createWorld(creator: WorldCreator): World = server.createWorld(creator)

/**
 * Unloads a world with the given name.
 *
 * @param name Name of the world to unload
 * @param save whether to save the chunks before unloading
 *
 * @return true if successful, false otherwise
 */
fun unloadWorld(name: String, save: Boolean) = server.unloadWorld(name, save)

/**
 * Unloads the given world.
 *
 * @param world the world to unload
 * @param save whether to save the chunks before unloading
 *
 * @return true if successful, false otherwise
 */
fun unloadWorld(world: World, save: Boolean) = server.unloadWorld(world, save)

/**
 * Gets the world with the given name.
 *
 * @param name the name of the world to retrieve
 *
 * @return a world with the given name, or null if none exists
 */
fun getWorld(name: String): World? = server.getWorld(name)

/**
 * Gets the world from the given Unique ID.
 *
 * @param uid a unique-id of the world to retrieve
 *
 * @return a world with the given Unique ID, or null if none exists
 */
fun getWorld(uid: UUID): World? = server.getWorld(uid)

/**
 * Gets the map from the given item ID.
 *
 * @param id the id of the map to get
 *
 * @return a map view if it exists, or null otherwise
 */
@Deprecated("Magic value")
fun getMap(id: Short): MapView? = server.getMap(id)

/**
 * Create a new map with an automatically assigned ID.
 *
 * @param world the world the map will belong to
 *
 * @return a newly created map view
 */
fun createMap(world: World) = server.createMap(world)!!

/**
 * Reloads the server, refreshing settings and plugin information.
 */
fun reload() = server.reload()

/**
 * Returns the primary logger associated with this server instance.
 *
 * @return Logger associated with this server
 */
val logger: Logger
    get() = server.logger

/**
 * Gets a [PluginCommand] with the given name or alias.
 *
 * @param name the name of the command to retrieve
 *
 * @return a plugin command if found, null otherwise
 */
fun getPluginCommand(name: String): PluginCommand? = server.getPluginCommand(name)

/**
 * Writes loaded players to disk.
 */
fun savePlayers() = server.savePlayers()

/**
 * Dispatches a command on this server, and executes it if found.
 *
 * @param sender the apparent sender of the command
 * @param cmdLine the command + arguments. Example: `test abc 123`
 *
 * @return returns false if no target is found
 *
 * @throws CommandException thrown when the executor for the given command fails with an unhandled exception
 */
@Throws(CommandException::class)
fun dispatchCommand(sender: CommandSender, cmdLine: String): Boolean = server.dispatchCommand(sender, cmdLine)

/**
 * Populates a given [ServerConfig] with values attributes to this
 * server.
 *
 * @param config the server config to populate
 */
fun configureDbConfig(config: ServerConfig) = server.configureDbConfig(config)

/**
 * Adds a recipe to the crafting manager.
 *
 * @param recipe the recipe to add
 *
 * @return true if the recipe was added, false if it wasn't for some reason
 */
fun addRecipe(recipe: () -> ItemStack): Boolean {
    return server.addRecipe(recipe)
}

/**
 * Get a list of all recipes for a given item. The stack size is ignored
 * in comparisons. If the durability is -1, it will match any data value.
 *
 * @param result the item to match against recipe results
 *
 * @return a list of recipes with the given result
 */
fun getRecipesFor(result: ItemStack): List<Recipe> = server.getRecipesFor(result)

/**
 * Get an iterator through the list of crafting recipes.
 *
 * @return an iterator
 */
val recipeIterator: Iterator<Recipe>
    get() = server.recipeIterator()

/**
 * Clears the list of crafting recipes.
 */
fun clearRecipes() = server.clearRecipes()

/**
 * Resets the list of crafting recipes to the default.
 */
fun resetRecipes() = server.resetRecipes()

/**
 * Gets a list of command aliases defined in the server properties.
 *
 * @return a map of aliases to command names
 */
val commandAliases: Map<String, Array<String>>
    get() = server.commandAliases

/**
 * The radius, in blocks, around each worlds spawn point to protect.
 *
 * @get spawn radius, or 0 if none
 */
var spawnRadius: Int
    get() = server.spawnRadius
    set(value) {
        server.spawnRadius = value
    }

/**
 * Gets whether the Server is in online mode or not.
 *
 * @return true if the server authenticates clients, false otherwise
 */
val onlineMode: Boolean
    get() = server.onlineMode

/**
 * Gets whether this server allows flying or not.
 *
 * @return true if the server allows flight, false otherwise
 */
val allowFlight: Boolean
    get() = server.allowFlight

/**
 * Gets whether the server is in hardcore mode or not.
 *
 * @return true if the server mode is hardcore, false otherwise
 */
val isHardcore: Boolean
    get() = server.isHardcore

/**
 * Shutdowns the server, stopping everything.
 */
fun shutdown() {
    server.shutdown()
}

/**
 * Broadcasts the specified message to every user with the given
 * permission name.
 *
 * @param message message to broadcast
 * @param permission the required permission [permissibles][Permissible] must have to receive the broadcast
 *
 * @return number of message recipients
 */
fun broadcast(message: String, permission: String = Server.BROADCAST_CHANNEL_USERS): Int {
    return server.broadcast(message, permission)
}

/**
 * Gets the player by the given UUID, regardless if they are offline or
 * online.
 *
 * This will return an object even if the player does not exist. To this
 * method, all players will exist.
 *
 * @param id the UUID of the player to retrieve
 * *
 * @return an offline player
 */
fun getOfflinePlayer(id: UUID): OfflinePlayer? = server.getOfflinePlayer(id)

/**
 * Gets a set containing all current IPs that are banned.
 *
 * @return a set containing banned IP addresses
 */
val ipBans: Set<String>
    get() = server.ipBans

/**
 * Bans the specified address from the server.
 *
 * @param address the IP address to ban
 */
fun banIp(address: String) = server.banIP(address)

/**
 * Unbans the specified address from the server.
 *
 * @param address the IP address to unban
 */
fun unbanIp(address: String) = server.unbanIP(address)

/**
 * Gets a set containing all banned players.
 *
 * @return a set containing banned players
 */
val bannedPlayers: Set<OfflinePlayer>
    get() = server.bannedPlayers

/**
 * Gets a ban list for the supplied type.
 *
 * Bans by name are no longer supported and this method will return
 * null when trying to request them. The replacement is bans by UUID.
 *
 * @param type the type of list to fetch, cannot be null
 *
 * @return a ban list of the specified type
 */
fun getBanList(type: BanList.Type): BanList = server.getBanList(type)

/**
 * Gets a set containing all player operators.
 *
 * @return a set containing player operators
 */
val operators: Set<OfflinePlayer>
    get() = server.operators

/**
 * The default [GameMode] for new players.
 *
 * @return the default game mode
 */
var defaultGameMode: GameMode
    get() = server.defaultGameMode
    set(value) {
        server.defaultGameMode = value
    }

/**
 * Gets a [ConsoleCommandSender] that may be used as an input source
 * for this server.
 *
 * @return a console command sender
 */
val consoleSender: ConsoleCommandSender
    get() = server.consoleSender

/**
 * Gets the folder that contains all of the various [World]s.
 *
 * @return folder that contains all worlds
 */
val worldContainer: File
    get() = server.worldContainer

/**
 * Gets every player that has ever played on this server.
 *
 * @return an array containing all previous players
 */
val offlinePlayers: Array<OfflinePlayer>
    get() = server.offlinePlayers

/**
 * Gets the [Messenger] responsible for this server.
 *
 * @return messenger responsible for this server
 */
val messenger: Messenger
    get() = server.messenger

/**
 * Gets the [HelpMap] providing help topics for this server.
 *
 * @return a help map for this server
 */
val helpMap: HelpMap
    get() = server.helpMap

/**
 * Creates an empty inventory of the specified type. If the type is [InventoryType.CHEST],
 * the new inventory has a size of 27; otherwise the
 * new inventory has the normal size for its type.

 * @param owner the holder of the inventory, or null to indicate no holder
 * @param type the type of inventory to create
 *
 * @return a new inventory
 */
fun createInventory(owner: InventoryHolder, type: InventoryType): Inventory = server.createInventory(owner, type)

/**
 * Creates an empty inventory with the specified type and title. If the type
 * is [InventoryType.CHEST], the new inventory has a size of 27;
 * otherwise the new inventory has the normal size for its type.
 * It should be noted that some inventory types do not support titles and
 * may not render with said titles on the Minecraft client.

 * @param owner The holder of the inventory; can be null if there's no holder.
 * @param type The type of inventory to create.
 * @param title The title of the inventory, to be displayed when it is viewed.
 *
 * @return The new inventory.
 */
fun createInventory(owner: InventoryHolder, type: InventoryType, title: String): Inventory {
    return server.createInventory(owner, type, title)
}

/**
 * Creates an empty inventory of type [InventoryType.CHEST] with the
 * specified size.
 *
 * @param owner the holder of the inventory, or null to indicate no holder
 * @param size a multiple of 9 as the size of inventory to create
 *
 * @return a new inventory
 *
 * @throws IllegalArgumentException if the size is not a multiple of 9
 */
@Throws(IllegalArgumentException::class)
fun createInventory(owner: InventoryHolder, size: Int): Inventory = server.createInventory(owner, size)

/**
 * Creates an empty inventory of type [InventoryType.CHEST] with the
 * specified size and title.
 *
 * @param owner the holder of the inventory, or null to indicate no holder
 * @param size a multiple of 9 as the size of inventory to create
 * @param title the title of the inventory, displayed when inventory is viewed
 *
 * @return a new inventory
 *
 * @throws IllegalArgumentException if the size is not a multiple of 9
 */
@Throws(IllegalArgumentException::class)
fun createInventory(owner: InventoryHolder, size: Int, title: String): Inventory {
    return server.createInventory(owner, size, title)
}

/**
 * Gets user-specified limit for number of monsters that can spawn in a
 * chunk.
 *
 * @return the monster spawn limit
 */
val monsterSpawnLimit: Int
    get() = server.monsterSpawnLimit

/**
 * Gets user-specified limit for number of animals that can spawn in a
 * chunk.
 *
 * @return the animal spawn limit
 */
val animalSpawnLimit: Int
    get() = server.animalSpawnLimit

/**
 * Gets user-specified limit for number of water animals that can spawn in
 * a chunk.
 *
 * @return the water animal spawn limit
 */
val waterAnimalSpawnLimit: Int
    get() = server.waterAnimalSpawnLimit

/**
 * Gets user-specified limit for number of ambient mobs that can spawn in
 * a chunk.
 *
 * @return the ambient spawn limit
 */
val ambientSpawnLimit: Int
    get() = server.ambientSpawnLimit

/**
 * Checks the current thread against the expected primary thread for the
 * server.
 *
 * Note: this method should not be used to indicate the current
 * synchronized state of the runtime. A current thread matching the main
 * thread indicates that it is synchronized, but a mismatch does not
 * preclude the same assumption.
 *
 * @return true if the current thread matches the expected primary thread, false otherwise
 */
val isPrimaryThread: Boolean
    get() = server.isPrimaryThread

/**
 * Gets the message that is displayed on the server list.
 *
 * @return the servers MOTD
 */
val motd: String
    get() = server.motd

/**
 * Gets the default message that is displayed when the server is stopped.
 *
 * @return the shutdown message
 */
val shutdownMessage: String
    get() = server.shutdownMessage

/**
 * Gets the current warning state for the server.
 *
 * @return the configured warning state
 */
val warningState: Warning.WarningState
    get() = server.warningState

/**
 * Gets the instance of the item factory (for [ItemMeta]).
 *
 * @return the item factory
 *
 * @see ItemFactory
 */
val itemFactory: ItemFactory
    get() = server.itemFactory

/**
 * Gets the instance of the scoreboard manager.
 *
 *
 * This will only exist after the first world has loaded.
 *
 * @return the scoreboard manager or null if no worlds are loaded.
 */
val scoreboardManager: ScoreboardManager
    get() = server.scoreboardManager

/**
 * Gets an instance of the server's default server-icon.
 *
 * @return the default server-icon; null values may be used by the implementation to indicate no defined icon,
 * but this behavior is not guaranteed
 */
val serverIcon: CachedServerIcon?
    get() = server.serverIcon

/**
 * Loads an image from a file, and returns a cached image for the specific
 * server-icon.
 *
 * Size and type are implementation defined. An incompatible file is
 * guaranteed to throw an implementation-defined [Exception].
 *
 * @param file the file to load the from
 *
 * @throws IllegalArgumentException if image is null
 * @throws Exception if the image does not meet current server server-icon specifications
 *
 * @return a cached server-icon that can be used for a [ServerListPingEvent]
 */
@Throws(IllegalArgumentException::class, Exception::class)
fun loadServerIcon(file: File): CachedServerIcon = server.loadServerIcon(file)

/**
 * Creates a cached server-icon for the specific image.
 *
 *
 * Size and type are implementation defined. An incompatible file is
 * guaranteed to throw an implementation-defined [Exception].
 *
 * @param image the image to use
 * *
 * @throws IllegalArgumentException if image is null
 *
 * @throws Exception if the image does not meet current server server-icon specifications
 *
 * @return a cached server-icon that can be used for a [     ][ServerListPingEvent.setServerIcon]
 */
@Throws(IllegalArgumentException::class, Exception::class)
fun loadServerIcon(image: BufferedImage): CachedServerIcon = server.loadServerIcon(image)

/**
 * Set the idle kick timeout. Any players idle for the specified amount of
 * time will be automatically kicked.
 *
 * A value of 0 will disable the idle kick timeout.
 *
 * @param threshold the idle timeout in minutes
 */
fun setIdleTimeout(threshold: Int) {
    server.idleTimeout = threshold
}

/**
 * Gets the idle kick timeout.
 *
 * @return the idle timeout in minutes
 */
val idleTimeout: Int
    get() = server.idleTimeout

/**
 * Create a ChunkData for use in a generator.
 *
 * @see [ChunkGenerator.generateChunkData]
 *
 * @param world the world to create the ChunkData for
 *
 * @return a new ChunkData for the world
 */
fun createChunkData(world: World): ChunkGenerator.ChunkData {
    return server.createChunkData(world)
}

/**
 * Creates a boss bar instance to display to players. The progress
 * defaults to 1.0
 *
 * @param title the title of the boss bar
 * @param color the color of the boss bar
 * @param style the style of the boss bar
 * @param flags an optional list of flags to set on the boss bar
 *
 * @return the created boss bar
 */
fun createBossBar(title: String, color: BarColor, style: BarStyle, vararg flags: BarFlag): BossBar {
    return server.createBossBar(title, color, style, *flags)
}

/**
 * @see UnsafeValues
 *
 * @return the unsafe values instance
 */
@Deprecated("Unsafe Values")
fun getUnsafe(): UnsafeValues {
    return server.unsafe
}