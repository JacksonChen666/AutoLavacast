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

abstract class AutoLavacastCommand(private val plugin: JavaPlugin) : CommandExecutor {
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
        val toBeReplacedWithWater = blocks.last().location.getLocationRelative(y = 2.0).block // above lava source
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
        val watchBlock = blocks.first().location.getLocationRelative(x = x.toDouble(), z = z.toDouble()).block // bottom
        WaterPlace(watchBlock, toBeReplacedWithWater, plugin).runTaskTimer(plugin, 20L, 20L)
        return true
    }

    private fun Location.getLocationRelative(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): Location {
        this.x += x
        this.y += y
        this.z += z
        return this
    }

    companion object {
        const val commandName = "lavacast"
    }

    init {
        Objects.requireNonNull(plugin.getCommand(commandName))!!
            .setExecutor(this)
    }
}