package gg.obsidian.discoursewhitelist

class Configuration(val plugin: Plugin) {

    var DISCOURSE_URL: String = ""
    var GROUP_ID: Int = 0
    var MESSAGE: String = ""

    fun load() {
        plugin.reloadConfig()

        DISCOURSE_URL = plugin.config.getString("settings.discourse-url")
        GROUP_ID = plugin.config.getInt("settings.group-id")
        MESSAGE = plugin.config.getString("settings.message", "You are not whitelisted!")
    }
}
