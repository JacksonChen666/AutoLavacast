package com.jacksonchen666.autolavacast.commands

import com.jacksonchen666.autolavacast.ChatColors
import com.jacksonchen666.autolavacast.processor.TickPlace
import com.jacksonchen666.autolavacast.processor.WaterPlace
import com.jacksonchen666.autolavacast.processor.getBlocksFromPlayerToGround
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class AutoLavacastCommand(private val plugin: JavaPlugin) : CommandExecutor {
    private fun getText(path: String): String {
        return plugin.config.getString(path)!!
    }

    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        if (commandSender !is Player) { // Console. Do your thing or just ignore.
            plugin.server.consoleSender.sendMessage(
                ChatColors.color(
                    getText("errors.console_execution").replace(
                        "{prefix}",
                        getText("errors.error_prefix")
                    )
                )
            )
            return false
        }
        val blocks: MutableList<Block> = getBlocksFromPlayerToGround(commandSender).asReversed()
        TickPlace(commandSender, blocks).runTaskTimer(plugin, 1L, 1L)
        val topBlockLocation = blocks.last().location
        val toBeReplacedWithWater = Location(
            topBlockLocation.world,
            topBlockLocation.x,
            topBlockLocation.y + 2,
            topBlockLocation.z
        ).block
        val x: Int = when (commandSender.facing) {
            BlockFace.EAST -> 1
            BlockFace.WEST -> -1
            else -> 0
        }
        val z: Int = when (commandSender.facing) {
            BlockFace.NORTH -> -1
            BlockFace.SOUTH -> 1
            else -> 0
        }
        val watchBlockLocation = blocks.first().location // bottom
        val watchBlock = Location(
            watchBlockLocation.world,
            watchBlockLocation.x + x,
            watchBlockLocation.y,
            watchBlockLocation.z + z,
        ).block
        WaterPlace(watchBlock, toBeReplacedWithWater, plugin).runTaskTimer(plugin, 20L, 20L)
        return true
    }

    companion object {
        const val commandName = "lavacast"
    }

    init {
        Objects.requireNonNull(plugin.getCommand(commandName))!!
            .setExecutor(this)
    }
}