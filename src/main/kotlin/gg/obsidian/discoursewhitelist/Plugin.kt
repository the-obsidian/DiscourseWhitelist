package gg.obsidian.discoursewhitelist

import okhttp3.OkHttpClient
import okhttp3.Request
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.plugin.java.JavaPlugin
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.JSONValue
import java.util.*

class Plugin : JavaPlugin(), Listener {

    val configuration = Configuration(this)
    val httpClient = OkHttpClient()

    override fun onEnable() {
        updateConfig(description.version)


        server.pluginManager.registerEvents(this, this)
        getCommand("discoursewhitelist").executor = CommandHandler(this)
    }

    fun reload() {
        reloadConfig()
        configuration.load()
    }

    // Event handlers

    @EventHandler
    fun onLogin(e: PlayerLoginEvent) {
        if (configuration.GROUP_ID != 0) {
            val disourseGroups = getDiscourseGroupIds(e.player.name)
            if (!disourseGroups.contains(configuration.GROUP_ID)) {
                val message = ChatColor.translateAlternateColorCodes('&', configuration.MESSAGE)
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, message)
            }
        }
    }

    // Utilities

    fun updateConfig(version: String) {
        this.saveDefaultConfig()
        config.options().copyDefaults(true)
        config.set("version", version)
        saveConfig()
        configuration.load()
    }

    private fun getDiscourseGroupIds(username: String): Set<Int> {
        val url = configuration.DISCOURSE_URL + "/users/" + username + ".json"
        val request = Request.Builder().url(url).get().build()
        val response = httpClient.newCall(request).execute()

        if (response.code() != 200) {
            return emptySet()
        }

        val bodyString = response.body().string()
        val body = JSONValue.parse(bodyString) as JSONObject
        val user = body["user"] as JSONObject
        val groups = user["groups"] as JSONArray

        val discourseGroups = HashSet<Int>()

        for (g in groups) {
            val group = g as JSONObject
            val id = group.get("id") as Long
            discourseGroups.add(id.toInt())
        }

        return discourseGroups
    }
}
