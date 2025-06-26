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
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pw.kmp.projectether.di.serverModule
import pw.kmp.projectether.di.sharedModule
import pw.kmp.projectether.model.dto.message.ClientMessage
import pw.kmp.projectether.model.dto.message.ClientMessage.Login
import pw.kmp.projectether.model.dto.message.ClientMessage.Move
import pw.kmp.projectether.model.dto.message.ClientMessage.Logout
import pw.kmp.projectether.util.extension.decideWithDiscriminator

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
    routing {
        webSocket("/ws") {
            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val receivedJson = frame.readText()
                        try {
                            val message = receivedJson.decideWithDiscriminator<ClientMessage>()
                            when (message) {
                                is Login -> {
                                    println("Player ${message.username} logged in")
                                    GameSessionManager().registerPlayer(id = 0, username = message.username, session = this, worldId = 0)
                                    send(Frame.Text("Welcome, ${message.username}!"))
                                }
                                is Logout -> {
                                    GameSessionManager().unregisterPlayer(0)
                                }
                                is Move -> {
                                    // 2. TODO("Handle move message") #2
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
