package pw.kmp.projectether

import io.ktor.server.websocket.WebSocketServerSession
import io.ktor.websocket.Frame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pw.kmp.projectether.model.session.PlayerSession
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class GameSessionManager {
    private val players = ConcurrentHashMap<Long, PlayerSession>()
    private val sessionScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    fun registerPlayer(id: Long, username: String?, session: WebSocketServerSession, worldId: Long = 0) {
        players[id] = PlayerSession(
            playerId = id,
            username = username,
            session = session,
            worldId = worldId,
            startedAt = Clock.System.now()
        )
    }
    fun unregisterPlayer(id: Long) {
        players.remove(id)
    }
    fun getPlayer(id: Long): PlayerSession? {
        return players[id]
    }
    fun getPlayers(): Collection<PlayerSession> {
        return players.values.toList()
    }
    fun broadcast(message: String) {
        sessionScope.launch {
            players.values.forEach { player ->
                player.session.send(Frame.Text(message))
            }
        }
    }
}