package com.jacksonchen666.template.commands

import com.jacksonchen666.template.ChatColors
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Template(private val plugin: JavaPlugin) : CommandExecutor {
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
        commandSender.sendMessage("This is a template command. This has nothing special.")
        return true
    }

    companion object {
        const val commandName = "template"
    }

    init {
        Objects.requireNonNull(plugin.getCommand(commandName))!!
            .setExecutor(this)
    }
}