package com.jacksonchen666.autolavacast.commands

import com.jacksonchen666.autolavacast.ChatColors
import com.jacksonchen666.autolavacast.processor.getBlocksFromPlayerToGround
import org.apache.commons.lang.mutable.Mutable
import org.bukkit.Material
import org.bukkit.block.Block
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
        for (i in blocks) {
            i.type = Material.COBBLESTONE
        }
        blocks.first().type = Material.LAVA
        commandSender.sendMessage("Done")
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