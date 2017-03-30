@file:Suppress("unused")

package com.deanveloper.kbukkit.util

import org.bukkit.*
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarFlag
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.command.CommandException
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.generator.ChunkGenerator
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.plugin.Plugin
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.util.CachedServerIcon
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import java.util.logging.Logger

/**
 * The Bukkit static class organized into classes.
 *
 * @author Dean B <dean@deanveloper.com>
 */

/**
 * The [Server] singleton
 */
@set:Deprecated("Do not set the server instance unless you know what you're doing!")
var SERVER: Server
    get() = Bukkit.getServer()
    set(value) = Bukkit.setServer(value)

/**
 * Broadcasts the specified message to every user with the given
 * permission name.
 *
 * @param message message to broadcast
 * @param permission the required permission a [permissible][org.bukkit.permissions.Permissible] must have to receive the broadcast
 *
 * @return number of message recipients
 */
fun broadcast(message: String, permission: String = Server.BROADCAST_CHANNEL_USERS): Int {
    return SERVER.broadcast(message, permission)
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
    return SERVER.createBossBar(title, color, style, *flags)
}

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
    get() = SERVER.isPrimaryThread

/**
 * Returns the primary logger associated with this server instance.
 *
 * @return Logger associated with this server
 */
val LOGGER: Logger
    get() = SERVER.logger

/**
 * Gets the instance of the scoreboard manager.
 *
 * This will only exist after the first world has loaded.
 *
 * @return the scoreboard manager or null if no worlds are loaded.
 */
val scoreboardManager: ScoreboardManager
    get() = SERVER.scoreboardManager


fun Listener.registerEvents(plugin: Plugin) = SERVER.pluginManager.registerEvents(this, plugin)

/**
 * A series of methods that are unsafe and should be used minimally.
 */
val UNSAFE: UnsafeValues
    get() = SERVER.unsafe

/**
 * A group of static functions that pertain to the server whitelist.
 */
object Whitelist {
    /**
     * Gets whether this server has a whitelist or not.
     *
     * @return whether this server has a whitelist or not
     */
    @JvmStatic
    var enabled: Boolean
        get() = SERVER.hasWhitelist()
        set(value) = SERVER.setWhitelist(value)

    /**
     * Gets a list of whitelisted players.
     *
     * @return a set containing all whitelisted players
     */
    @JvmStatic
    val players: Set<OfflinePlayer>
        get() = SERVER.whitelistedPlayers

    /**
     * Reloads the whitelist from disk.
     */
    @JvmStatic
    fun reload() = SERVER.reloadWhitelist()
}

/**
 * A group of static functions that deal with players.
 */
object Players {
    /**
     * Gets the player with the exact given name, case insensitive.
     *
     * Alias of [getPlayerExact]
     *
     * @see getPlayerExact
     *
     * @param name Exact name of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @[JvmStatic Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))]
    operator fun get(name: String) = getPlayerExact(name)

    /**
     * Gets the player with the given [UUID]
     *
     * @param id UUID of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @JvmStatic
    operator fun get(uuid: UUID) = getPlayer(uuid)

    /**
     * Gets a player object by the given username.
     *
     * This method may not return objects for offline players.
     *
     * @param name the name to look up
     * @return a player if one was found, null otherwise
     */
    @[JvmStatic Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))]
    fun getPlayer(name: String): Player? = SERVER.getPlayer(name)

    /**
     * Gets the player with the exact given name, case insensitive.
     *
     * @param name Exact name of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @[JvmStatic Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))]
    fun getPlayerExact(name: String): Player? = SERVER.getPlayerExact(name)

    /**
     * Attempts to match any players with the given name, and returns a list
     * of all possibly matches.
     *
     * This list is not sorted in any particular order. If an exact match is
     * found, the returned list will only contain a single result.
     *
     * @param name the (partial) name to match
     * @return list of all possible players
     */
    @[JvmStatic Deprecated("Names are no longer guaranteed to be unique.", ReplaceWith("getPlayer(id)"))]
    fun matchPlayer(name: String): List<Player> = SERVER.matchPlayer(name)

    /**
     * Gets the player with the given UUID.
     *
     * @param id UUID of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @JvmStatic
    fun getPlayer(id: UUID): Player? = SERVER.getPlayer(id)


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
    @JvmStatic
    val online: Collection<Player> = SERVER.onlinePlayers

    /**
     * Gets every player that has ever played on this server.
     *
     * @return an array containing all previous players
     */
    @JvmStatic
    val all: Array<OfflinePlayer> = SERVER.offlinePlayers

    /**
     * Writes loaded players to disk.
     */
    @JvmStatic
    fun savePlayers() = SERVER.savePlayers()

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
    @JvmStatic
    fun getOfflinePlayer(id: UUID): OfflinePlayer? = SERVER.getOfflinePlayer(id)

    /**
     * Gets a set containing all player operators.
     *
     * @return a set containing player operators
     */
    @JvmStatic
    val operators: Set<OfflinePlayer>
        get() = SERVER.operators
}

object Worlds {

    /**
     * Gets a list of all worlds on this server.
     *
     * @return a list of worlds
     */
    @JvmStatic
    val worlds: List<World>
        get() = SERVER.worlds

    /**
     * Creates or loads a world with the given name using the specified
     * options.
     *
     * If the world is already loaded, it will just return the equivalent of
     * getWorld(creator.name()).
     *
     * @param creator the options to use when creating the world
     * @return newly created or loaded world
     */
    @JvmStatic
    fun create(creator: WorldCreator): World = SERVER.createWorld(creator)

    /**
     * Unloads a world with the given name.
     *
     * @param name Name of the world to unload
     * @param save whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    @JvmStatic
    fun unload(name: String, save: Boolean) = SERVER.unloadWorld(name, save)

    /**
     * Unloads the given world.
     *
     * @param world the world to unload
     * @param save whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    @JvmStatic
    fun unload(world: World, save: Boolean) = SERVER.unloadWorld(world, save)

    /**
     * Gets the world with the given name.
     *
     * @param name the name of the world to retrieve
     * @return a world with the given name, or null if none exists
     */
    @JvmStatic
    operator fun get(name: String): World? = SERVER.getWorld(name)

    /**
     * Gets the world from the given Unique ID.
     *
     * @param uid a unique-id of the world to retrieve
     * @return a world with the given Unique ID, or null if none exists
     */
    @JvmStatic
    operator fun get(uid: UUID): World? = SERVER.getWorld(uid)

    /**
     * Create a ChunkData for use in a generator.
     *
     * @see [ChunkGenerator.generateChunkData]
     *
     * @param world the world to create the ChunkData for
     *
     * @return a new ChunkData for the world
     */
    @JvmStatic
    fun createChunkData(world: World): ChunkGenerator.ChunkData {
        return SERVER.createChunkData(world)
    }
}

object Commands {

    /**
     * Gets a [PluginCommand] with the given name or alias.
     *
     * @param name the name of the command to retrieve
     * @return a plugin command if found, null otherwise
     */
    @JvmStatic
    operator fun get(name: String) = getPluginCommand(name)

    /**
     * Gets a [PluginCommand] with the given name or alias.
     *
     * @param name the name of the command to retrieve
     * @return a plugin command if found, null otherwise
     */
    @JvmStatic
    fun getPluginCommand(name: String): PluginCommand? = SERVER.getPluginCommand(name)

    /**
     * Dispatches a command on this server, and executes it if found.
     *
     * @param sender the apparent sender of the command
     * @param cmdLine the command + arguments. Example: `test abc 123`
     * @return false if no target is found
     * @throws CommandException thrown when the executor for the given command fails with an unhandled exception
     */
    @[JvmStatic Throws(CommandException::class)]
    fun dispatch(sender: CommandSender, cmdLine: String): Boolean = SERVER.dispatchCommand(sender, cmdLine)

    /**
     * Gets a list of command aliases defined in the server properties.
     *
     * @return a map of aliases to command names
     */
    @JvmStatic
    val commandAliases: Map<String, Array<String>>
        get() = SERVER.commandAliases
}

object Recipes : Iterable<Recipe> {

    /**
     * Adds a recipe to the crafting manager.
     *
     * @param recipe the recipe to add
     * @return true if the recipe was added, false if it wasn't for some reason
     */
    @JvmStatic
    fun add(recipe: () -> ItemStack): Boolean {
        return SERVER.addRecipe(recipe)
    }

    /**
     * @see add
     */
    @JvmStatic
    operator fun plusAssign(recipe: () -> ItemStack) {
        SERVER.addRecipe(recipe)
    }

    /**
     * Get a list of all recipes for a given item. The stack size is ignored
     * in comparisons. If the durability is -1, it will match any data value.
     *
     * @param result the item to match against recipe results
     * @return a list of recipes with the given result
     */
    @JvmStatic
    fun getRecipesFor(result: ItemStack): List<Recipe> = SERVER.getRecipesFor(result)

    /**
     * Get an iterator through the list of crafting recipes.
     *
     * @return an iterator
     */
    override operator fun iterator(): Iterator<Recipe> = SERVER.recipeIterator()

    /**
     * Clears the list of crafting recipes.
     */
    @JvmStatic
    fun clear() = SERVER.clearRecipes()

    /**
     * Resets the list of crafting recipes to the default.
     */
    @JvmStatic
    fun reset() = SERVER.resetRecipes()
}

object Ban {
    /**
     * Gets a set containing all current IPs that are banned.
     *
     * @return a set containing banned IP addresses
     */
    @JvmStatic
    val ipBans: Set<String>
        get() = SERVER.ipBans

    /**
     * Bans the specified address from the server.
     *
     * @param address the IP address to ban
     */
    @JvmStatic
    fun banIp(address: String) = SERVER.banIP(address)

    /**
     * Unbans the specified address from the server.
     *
     * @param address the IP address to unban
     */
    @JvmStatic
    fun unbanIp(address: String) = SERVER.unbanIP(address)

    /**
     * Gets a set containing all banned players.
     *
     * @return a set containing banned players
     */
    @JvmStatic
    val players: Set<OfflinePlayer>
        get() = SERVER.bannedPlayers

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
    @JvmStatic
    fun getBanList(type: BanList.Type): BanList = SERVER.getBanList(type)
}

object Inventories {

    /**
     * Creates an empty inventory of the specified type. If the type is [InventoryType.CHEST],
     * the new inventory has a size of 27; otherwise the
     * new inventory has the normal size for its type.

     * @param owner the holder of the inventory, or null to indicate no holder
     * @param type the type of inventory to create
     *
     * @return a new inventory
     */
    @JvmStatic
    fun create(owner: InventoryHolder, type: InventoryType, title: String? = null): Inventory {
        return SERVER.createInventory(owner, type, title)
    }

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
    @[JvmStatic Throws(IllegalArgumentException::class)]
    fun createInventory(owner: InventoryHolder, size: Int, title: String? = null): Inventory {
        return SERVER.createInventory(owner, size, title)
    }
}

object ServerFavicon {

    /**
     * Gets an instance of the server's default server-icon.
     *
     * @return the default server-icon; null values may be used by the implementation to indicate no defined icon,
     * but this behavior is not guaranteed
     */
    @JvmStatic
    val serverIcon: CachedServerIcon?
        get() = SERVER.serverIcon

    /**
     * Loads an image from a file, and returns a cached image for the specific
     * server-icon.
     *
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined [Exception].
     *
     * @param file the file to load the from
     * @return a cached server-icon that can be used for a [ServerListPingEvent]
     * @throws IllegalArgumentException if image is null
     * @throws Exception if the image does not meet current server server-icon specifications
     */
    @[JvmStatic Throws(IllegalArgumentException::class, Exception::class)]
    fun loadServerIcon(file: File): CachedServerIcon = SERVER.loadServerIcon(file)

    /**
     * Creates a cached server-icon for the specific image.
     *
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined [Exception].
     *
     * @param image the image to use
     * @return a cached server-icon that can be used for a [ServerListPingEvent]
     * @throws IllegalArgumentException if image is null
     * @throws Exception if the image does not meet current server server-icon specifications
     */
    @[JvmStatic Throws(IllegalArgumentException::class, Exception::class)]
    fun loadServerIcon(image: BufferedImage): CachedServerIcon = SERVER.loadServerIcon(image)
}