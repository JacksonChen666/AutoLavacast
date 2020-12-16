package com.jacksonchen666.autolavacast.processor

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player

fun getBlocksFromPlayerToGround(player: Player): MutableList<Block> {
    val x: Int = when (player.facing) {
        BlockFace.EAST -> 1
        BlockFace.WEST -> -1
        else -> 0
    }
    val z: Int = when (player.facing) {
        BlockFace.NORTH -> -1
        BlockFace.SOUTH -> 1
        else -> 0
    }
    var currentLocation: Location = player.location
    var goDown = false
    val blocks: MutableList<Block> = mutableListOf()
    do { // do not check condition on first loop
        blocks.add(currentLocation.block)
        currentLocation = Location(player.world, currentLocation.x + x, if (goDown) currentLocation.y - 1 else currentLocation.y, currentLocation.z + z)
        goDown = !goDown
    } while (blocks.last().type == Material.AIR)
    return blocks
}