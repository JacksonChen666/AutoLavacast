package com.jacksonchen666.autolavacast.processor

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class WaterPlace(private val watchBlock: Block, private val placeBlock: Block, val plugin: JavaPlugin) : BukkitRunnable() {
    override fun run() {
        if (watchBlock.type == Material.LAVA) {
            placeBlock.type = Material.WATER
            WaterRemove(placeBlock).runTaskLater(plugin, 20L)
            cancel()
        }
    }
}