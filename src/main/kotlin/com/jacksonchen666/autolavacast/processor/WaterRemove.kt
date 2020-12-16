package com.jacksonchen666.autolavacast.processor

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.scheduler.BukkitRunnable

class WaterRemove(private val block: Block) : BukkitRunnable() {
    override fun run() {
        block.type = Material.COBBLESTONE
    }
}