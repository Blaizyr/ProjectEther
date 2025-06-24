package pw.kmp.projectether.model.session

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Session @ExperimentalTime constructor(
    val playerId: Long,
    val sessionId: Long,
    val worldId: Long,
    val startedAt: Instant, //TODO("2. verify")
)
