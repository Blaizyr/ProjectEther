package pw.kmp.projectether.model.session

import io.ktor.server.websocket.WebSocketServerSession
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class PlayerSession @ExperimentalTime constructor(
    val playerId: Long,
    val username: String? = "",
    val session: WebSocketServerSession,
    val sessionId: Long? = null,
    val worldId: Long,
    val startedAt: Instant,
)
