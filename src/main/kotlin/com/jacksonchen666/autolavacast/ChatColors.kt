package com.jacksonchen666.autolavacast

import org.bukkit.ChatColor

object ChatColors {
    fun color(s: String?): String {
        return ChatColor.translateAlternateColorCodes('&', s!!)
    }
}