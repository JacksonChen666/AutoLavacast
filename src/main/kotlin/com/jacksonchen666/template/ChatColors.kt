package com.jacksonchen666.template

import org.bukkit.ChatColor

object ChatColors {
    fun color(s: String?): String {
        return ChatColor.translateAlternateColorCodes('&', s!!)
    }
}