package com.jacksonchen666.autolavacast.processor

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.scheduler.BukkitRunnable

class WaterPlace(private val watchBlock: Block, private val placeBlock: Block) : BukkitRunnable() {
    override fun run() {
        if (watchBlock.type == Material.LAVA) {
            placeBlock.type = Material.WATER
            cancel()
        }
    }
}