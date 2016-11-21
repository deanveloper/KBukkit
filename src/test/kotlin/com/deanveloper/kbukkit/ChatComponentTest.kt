package com.deanveloper.kbukkit

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.*
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * @author Dean B on 11/21/2016.
 */
class ChatComponentTest {
    @Before
    fun setUp() {
        KBukkitPlugin(FakeServer)
    }
    
    @Test
    fun testColor() {
        var component: TextComponent
        var built: TextComponent
        
        
        // Test basic functionality
        component = TextComponent("hi!")
        component.color = ChatColor.GREEN
        component.isBold = true
        component.isItalic = true
        
        built = textComponent("hi!") {
            color = ChatColor.GREEN
            isBold = true
            isItalic = true
        }
    
        assertEquals(component.toString(), built.toString())
        
        
        // let's get more complicated
        component = TextComponent("Start ")
        component.color = ChatColor.RED
        component.isBold = true
        component.isStrikethrough = true
        
        val next = TranslatableComponent("item.swordDiamond.name")
        next.isBold = false
        next.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp here")
        
        component.addExtra(next)
        
        built = textComponent("Start ") {
            color = ChatColor.RED
            isBold = true
            isStrikethrough = true
            
            addExtraTranslate("item.swordDiamond.name") {
                isBold = false
                clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp here")
            }
        }
        
        assertEquals(component.toString(), built.toString())
        
        
        // Now let's test to see if arrays work too
        val arr = ComponentBuilder("The quick brown fox").color(ChatColor.GREEN).append(" jumps over the lazy dog").color(ChatColor.WHITE).create()
        val test = textComponent("The quick brown fox") {
            color = ChatColor.GREEN
        } + textComponent(" jumps over the lazy dog")
        
        assertEquals(Arrays.toString(arr), Arrays.toString(test))
    }
}