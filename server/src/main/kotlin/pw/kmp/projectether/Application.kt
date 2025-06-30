package pw.kmp.projectether

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pw.kmp.projectether.di.serverModule
import pw.kmp.projectether.di.sharedModule
import pw.kmp.projectether.model.dto.message.ClientMessage
import pw.kmp.projectether.model.dto.message.ClientMessage.Login
import pw.kmp.projectether.model.dto.message.ClientMessage.Logout
import pw.kmp.projectether.model.dto.message.ClientMessage.Move
import pw.kmp.projectether.model.dto.message.ServerMessage
import pw.kmp.projectether.model.dto.message.ServerMessage.Info
import pw.kmp.projectether.useCase.CreatePlayerUseCase
import pw.kmp.projectether.useCase.GetPlayerUseCase
import pw.kmp.projectether.util.extension.decodeWithDiscriminator
import pw.kmp.projectether.util.extension.encodeWithDiscriminator

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(WebSockets)
    install(Koin) {
        slf4jLogger()
        modules(
            sharedModule,
            serverModule
        )
    }
    val gameSessionManager: GameSessionManager by inject()
    val getPlayerUseCase: GetPlayerUseCase by inject()
    val createPlayerUseCase: CreatePlayerUseCase by inject()

    routing {
        webSocket("/ws") {
            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val receivedJson = frame.readText()
                        try {
                            val message = receivedJson.decodeWithDiscriminator<ClientMessage>()
                            when (message) {
                                is Login -> {
                                    gameSessionManager.registerPlayerSession(
                                        player = getPlayerUseCase.getPlayerByUsername(message.username)
                                            ?: createPlayerUseCase.createPlayer(message.username)
                                                .also {
                                                    send(
                                                        Frame.Text(
                                                            Info("${it.name} created!").encodeWithDiscriminator<ServerMessage>()
                                                        )
                                                    )
                                                },
                                        session = this,
                                    )
                                    println("Player ${message.username} logged in")
                                    send(
                                        Frame.Text(
                                            Info("Welcome, ${message.username}!").encodeWithDiscriminator<ServerMessage>()
                                        )
                                    )
                                }

                                is Logout -> {
                                    gameSessionManager.unregisterPlayer(0)
                                }

                                is Move -> {
                                    // TODO("Handle move message") #2
                                }
                            }

                        } catch (e: Exception) {
                            println("Error parsing JSON: ${e.message}")
                        }
                    }

                    else -> Unit
                }
            }
        }

        get("/") {
            call.respondText("Hello, world!")
        }
    }
}
