package com.jacksonchen666.autolavacast.processor

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TickPlace(val player: Player, private val blocks: MutableList<Block>) : BukkitRunnable() {
    var count: Int = 0

    override fun run() {
        count++
        when {
            count == blocks.size -> {
                val location = blocks.last().location
                Location(player.world, location.x, location.y + 1, location.z).block.type = Material.LAVA
                Location(player.world, location.x, location.y + 2, location.z).block.type = Material.COBBLESTONE
            }
            count > blocks.size -> {
                cancel()
            }
            else -> {
                blocks[count].type = Material.COBBLESTONE
            }
        }
    }
}