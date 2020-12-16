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
    var currentLocation = Location(
        player.world,
        player.location.x,
        player.location.y - 1,
        player.location.z
    )
    var goDown = false
    val blocks: MutableList<Block> = mutableListOf()
    do { // do not check condition on first loop
        blocks.add(currentLocation.block)
        if (goDown) {
            currentLocation = Location(
                player.world,
                currentLocation.x + x,
                currentLocation.y,
                currentLocation.z + z
            )
        }
        else {
            currentLocation = Location(
                player.world,
                currentLocation.x,
                currentLocation.y - 1,
                currentLocation.z
            )
        }
        goDown = !goDown
    } while (blocks.last().type == Material.AIR)
    return blocks
}