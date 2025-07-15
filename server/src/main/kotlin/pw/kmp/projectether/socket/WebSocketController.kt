package pw.kmp.projectether.socket

import io.ktor.server.websocket.WebSocketServerSession
import io.ktor.websocket.Frame
import pw.kmp.projectether.service.GameSessionManager
import pw.kmp.projectether.data.model.dto.message.ClientMessage
import pw.kmp.projectether.data.model.dto.message.ClientMessage.Login
import pw.kmp.projectether.data.model.dto.message.ClientMessage.Logout
import pw.kmp.projectether.data.model.dto.message.ClientMessage.Move
import pw.kmp.projectether.data.model.dto.message.ServerMessage
import pw.kmp.projectether.data.model.dto.message.ServerMessage.Info
import pw.kmp.projectether.useCase.CreatePlayerUseCase
import pw.kmp.projectether.useCase.GetPlayerUseCase
import pw.kmp.projectether.util.extension.decodeWithDiscriminator
import pw.kmp.projectether.util.extension.encodeWithDiscriminator

class WebSocketController(
    private val gameSessionManager: GameSessionManager,
    private val getPlayerUseCase: GetPlayerUseCase,
    private val createPlayerUseCase: CreatePlayerUseCase
) {
    suspend fun handleMessage(json: String, session: WebSocketServerSession) {
        try {
            when (val message = json.decodeWithDiscriminator<ClientMessage>()) {
                is Login -> handleLogin(message, session)
                is Logout -> handleLogout(message)
                is Move -> handleMove(message)
            }
        } catch (e: Exception) {
            println("Error parsing JSON: ${e.message}")
        }
    }

    private suspend fun handleLogin(message: Login, session: WebSocketServerSession) {
        val player = getPlayerUseCase.getPlayerByUsername(message.username)
            ?: createPlayerUseCase.createPlayer(message.username).also {
                session.send(
                    Frame.Text(
                        Info("${it.name} created!").encodeWithDiscriminator<ServerMessage>()
                    )
                )
            }
        gameSessionManager.registerPlayerSession(player, session)
        session.send(Frame.Text(Info("Welcome, ${message.username}!").encodeWithDiscriminator<ServerMessage>()))
    }

    private fun handleMove(message: Move) {
        // TODO("Handle move message") #2
    }

    private fun handleLogout(message: Logout) {
        gameSessionManager.unregisterPlayer(id = message.playerId.toLong())
    }
}