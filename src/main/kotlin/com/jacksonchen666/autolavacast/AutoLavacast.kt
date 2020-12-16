package com.jacksonchen666.autolavacast

import org.bukkit.plugin.java.JavaPlugin
import com.jacksonchen666.autolavacast.commands.AutoLavacastCommand
import java.io.File
import org.bukkit.configuration.file.YamlConfiguration
import java.io.IOException
import org.bukkit.configuration.InvalidConfigurationException

class AutoLavacast : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        AutoLavacastCommand(this)
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