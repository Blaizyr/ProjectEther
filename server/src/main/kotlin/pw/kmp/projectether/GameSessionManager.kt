package pw.kmp.projectether

import io.ktor.server.websocket.WebSocketServerSession
import io.ktor.websocket.Frame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pw.kmp.projectether.model.player.Player
import pw.kmp.projectether.model.session.PlayerSession
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class GameSessionManager {
    private val players = ConcurrentHashMap<Long, PlayerSession>()
    private val sessionScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    fun registerPlayerSession(player: Player, session: WebSocketServerSession) {
        val playerSession = PlayerSession(
            player = player,
            session = session,
            startedAt = Clock.System.now()
        )
        players[player.id] = playerSession
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