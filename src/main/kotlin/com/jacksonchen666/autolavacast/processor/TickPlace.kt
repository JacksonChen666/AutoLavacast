package com.jacksonchen666.autolavacast.processor

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TickPlace(private val player: Player, private val blocks: MutableList<Block>) : BukkitRunnable() {
    var count: Int = 0

    override fun run() {
        count++
        when {
            count == blocks.size -> {
                val location = blocks.takeLast(3)[0].location
                Location(player.world, location.x, location.y + 1, location.z).block.type = Material.LAVA
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