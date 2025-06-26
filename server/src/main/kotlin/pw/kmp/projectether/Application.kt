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
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pw.kmp.projectether.di.serverModule
import pw.kmp.projectether.di.sharedModule
import pw.kmp.projectether.model.dto.message.ClientMessage

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
                            val message = Json.decodeFromString<ClientMessage.Login>(receivedJson)
                            send(Frame.Text("Welcome, ${message.username}!"))
                            println("Received from ${message.username}: $message")
                            send(Frame.Text("Echo: $message"))
                            outgoing.send(Frame.Text("Server received: $message"))
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
