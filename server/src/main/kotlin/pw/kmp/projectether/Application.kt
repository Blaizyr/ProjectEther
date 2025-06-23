package pw.kmp.projectether

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pw.kmp.projectether.di.sharedModule

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(WebSockets)
    install(Koin) {
        slf4jLogger()
        modules(sharedModule)
    }
    routing {
        webSocket("/ws") {
            val username = call.request.queryParameters["username"] ?: "unknown"
            send(Frame.Text("Welcome, $username!"))

            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val receivedText = frame.readText()
                        println("Received from $username: $receivedText")
                        send(Frame.Text("Echo: $receivedText"))

                        outgoing.send(Frame.Text("Server received: $receivedText"))
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