package com.jacksonchen666.template

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class Listener : Listener {
    @EventHandler
    fun onEvent(event: PlayerLoginEvent) {
        event.player.sendMessage(event.player.name + ", welcome to the server!")
    }
}