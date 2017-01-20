# KBukkit
Kotlin library for Bukkit and Spigot

Kotlin is a programming language made by JetBrains, the same people who make IntelliJ IDEA.
It compiles into Java, which means that you can interact with the Bukkit and Spigot APIs with it.
Kotlin has tons of features that Java doesn't have, so I made a neat library that uses some of the features
that Kotlin has to offer.

## Features
* ChatColor addition
  * In Java, if you do `ChatColor.BOLD + ChatColor.BLUE`, you get an error.
  This is because the `+` operator cannot be put on two enum constants.
  * Kotlin solves this with [operator functions] and [extension functions].
  Basically, you can add extension functions to the ChatColor class, and if you
  add an operator function, then you can use that operator on that class.
  * This means that with KBukkit, you can legally do `ChatColor.BOLD + ChatColor.BLUE`, and it will return `§l§9`
* Custom Player Classes
  * Are you tired of tons of maps of `UUID`s to data types? With KBukkit,
  you can create custom player classes. These classes take advantage of
  [Kotlin's delegation pattern]. Basically, you pass a player as an
  argument to a constructor, and the class will implement the `Player` interface,
  mapping all required functions to the argument passed into the constructor.
  I know that sounds complicated, but once you get the hang of it, it's super awesome and
  saves a lot of time.
* Custom Config Handler
  * Having more than one config is annoying. With Kotlin's operator functions
  that I mentioned earlier, configs get a lot easier. With KBukkit's `KConfig` class,
  you can simply do `config["foo", Bar::class]` to get an element `foo` from the config that is type `Bar`.
* Easy, more organized access to the `Bukkit.X()` methods
  * Nearly all commonly used methods have been sorted into separate classes
  (ie `Bukkit.getOnlinePlayers()` is now `Players.online`)
* Scheduler functions are much easier
  * Scheduler-related methods are global functions, so running something asynchronously is now as easy
  as `runAsync { /* code here */ }`
* Chat Component Factory
  * A really cool feature that allows the creation of complex chat components
  extremely easily. Takes inspiration from [TornadoFX].
  
## Javadoc
Javadoc is available [here][javadoc]. I'm not really digging the default
KDoc website layout, so I might mess with the CSS a bit when I get a chance.

## Donate
I'd really appreciate any donations, even anything as small as a pack of ramen!

[<img src="https://upload.wikimedia.org/wikipedia/commons/b/b5/PayPal.svg" height="50">][paypal]
  
[operator functions]: https://kotlinlang.org/docs/reference/operator-overloading.html
[extension functions]: https://kotlinlang.org/docs/reference/extensions.html
[Kotlin's delegation pattern]: https://kotlinlang.org/docs/reference/delegation.html
[javadoc]: https://kbukkit.deanveloper.com
[paypal]: PayPal.Me/Dean98/5USD
[TornadoFX]: https://github.com/edvin/tornadofx/wiki/Type-Safe-Builders
