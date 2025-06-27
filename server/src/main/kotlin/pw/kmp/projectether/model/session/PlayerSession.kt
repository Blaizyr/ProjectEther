package pw.kmp.projectether.model.session

import io.ktor.server.websocket.WebSocketServerSession
import pw.kmp.projectether.model.player.Player
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class PlayerSession @ExperimentalTime constructor(
    val player: Player,
    val session: WebSocketServerSession,
    val startedAt: Instant,
)
