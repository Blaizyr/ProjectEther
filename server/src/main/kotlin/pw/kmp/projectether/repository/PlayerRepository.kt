package pw.kmp.projectether.repository

import pw.kmp.projectether.model.player.Player
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class PlayerRepository {
    private val playersByUsername = ConcurrentHashMap<String, Player>()
    private val playersById = ConcurrentHashMap<Long, Player>()

    fun add(username: String): Player { // TODO("transform to Result<Player>") #5
        if (getByUsername(username) == null) {
            val id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE
            val player = Player(id, username, null)
            playersById[player.id] = player
            playersByUsername[player.name] = player
            return player
        }
        else { throw IllegalArgumentException("Player with username $username already exists") }
    }

    fun remove(player: Player) {
        playersById.remove(player.id)
        playersByUsername.remove(player.name)
    }

    fun getById(id: Long): Player? {
        return playersById[id]
    }

    fun getByUsername(username: String): Player? {
        return playersByUsername[username]
    }

    fun getAll(): List<Player> {
        return playersById.values.toList()
    }

}