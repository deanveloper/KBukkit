# KBukkit
Kotlin library for Bukkit and Spigot

Kotlin is a programming language made by JetBrains, the same people who make IntelliJ IDEA. It compiles into Java, which means that you can interact with the Bukkit and Spigot APIs with it. Kotlin has tons of features that Java doesn't have, so I made a neat library that uses some of the features that Kotlin has to offer.

## Features
* ChatColor addition
  * In Java, if you do `ChatColor.BOLD + ChatColor.BLUE`, you get an error. This is because the `+` operator cannot be put on two enum constants.
  * Kotlin solves this with [operator functions](https://kotlinlang.org/docs/reference/operator-overloading.html) and [extension functions](https://kotlinlang.org/docs/reference/extensions.html). Basically, you can add extension functions to the ChatColor class, and if you add an operator function, then you can use that operator on that class.
  * This means that with KBukkit, you can legally do `ChatColor.BOLD + ChatColor.BLUE`, and it will return `§l§9`
* Custom Player Classes
  * Are you tired of tons of maps of `UUID`s to data types? With KBukkit, you can create custom player classes. These classes take advantage of [Kotlin's delegation pattern](https://kotlinlang.org/docs/reference/delegation.html). Basically, you pass a player as an argument to a constructor, and the class will implement the `Player` interface, mapping all required functions to the argument passed into the constructor. I know that sounds complicated, but it's really powerful.
* Custom Config Handler
  * Having more than one config is annoying. With Kotlin's operator functions that I mentioned earlier, configs get a lot easier. With KBukkit's `KConfig` class, you can simply do `config["foo", Bar::class]` to get an element `foo` from the config that is type `Bar`.
