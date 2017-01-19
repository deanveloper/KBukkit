package com.deanveloper.kbukkit

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.TranslatableComponent

/**
 * API for creating ChatComponents
 *
 * @author Dean B <dean@deanveloper.com>
 */

@JvmOverloads
/**
 * Creates a text component, and allows adjusting
 * of the component from inside a block of code.
 */
inline fun textComponent(text: String = "", cb: TextComponent.() -> Unit): TextComponent {
    return TextComponent(text).apply { cb() }
}

/**
 * Creates a text component.
 */
fun textComponent(text: String): TextComponent {
    return TextComponent(text)
}

/**
 * Creates a translatable component, and allows adjusting
 * of the component from inside a block of code.
 */
inline fun translatableComponent(translatable: String, cb: TranslatableComponent.() -> Unit): TranslatableComponent {
    return TranslatableComponent(translatable).apply { cb() }
}

/**
 * Creates a translatable component.
 */
fun translatableComponent(text: String): TranslatableComponent {
    return TranslatableComponent(text)
}

@JvmOverloads
/**
 * Utility function to add "extra" text to a component.
 */
inline fun BaseComponent.addExtraText(text: String = "", cb: TextComponent.() -> Unit) {
    addExtra(TextComponent(text).apply { cb() })
}

/**
 * Utility function to add "extra" as a translatable to a component.
 */
inline fun BaseComponent.addExtraTranslate(text: String, cb: TranslatableComponent.() -> Unit) {
    addExtra(TranslatableComponent(text).apply { cb() })
}

/**
 * Utility function to add BaseComponents together to form an array
 */
operator fun BaseComponent.plus(base: BaseComponent): Array<BaseComponent> {
    return arrayOf(this, base)
}

/**
 * Utility function to add BaseComponents together to form an array
 */
operator fun BaseComponent.plus(base: Array<BaseComponent>): Array<BaseComponent> {
    return arrayOf(this, *base)
}