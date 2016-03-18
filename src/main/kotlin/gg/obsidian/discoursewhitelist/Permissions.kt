package gg.obsidian.discoursewhitelist

import org.bukkit.entity.Player

enum class Permissions(val node: String) {
    reload("discoursewhitelist.reload");

    fun has(player: Player): Boolean {
        return player.hasPermission(node)
    }
}
