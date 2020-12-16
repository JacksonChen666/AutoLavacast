package com.jacksonchen666.template

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.entity.Player
import com.jacksonchen666.template.ChatColors
import com.jacksonchen666.template.commands.Template
import org.bukkit.event.player.PlayerLoginEvent
import java.io.File
import org.bukkit.configuration.file.YamlConfiguration
import java.io.IOException
import org.bukkit.configuration.InvalidConfigurationException

class Template : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        Template(this)
        server.pluginManager.registerEvents(Listener(), this)
    }

    override fun saveDefaultConfig() { // Allows you to save your config file as from your plugin
        val config = "config.yml"
        val file = File(dataFolder, config)
        if (!file.exists()) {
            file.parentFile.mkdirs()
            saveResource(config, false)
        }
        val yamlConfiguration = YamlConfiguration()
        try {
            yamlConfiguration.load(file)
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }
}