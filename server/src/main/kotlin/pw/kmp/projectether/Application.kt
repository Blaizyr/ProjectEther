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
import pw.kmp.projectether.service.GameServerLauncher
import pw.kmp.projectether.socket.WebSocketController

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
    val serverLauncher: GameServerLauncher by inject()
    val controller: WebSocketController by inject()

    serverLauncher.launch()

    routing {
        webSocket("/ws") {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    controller.handleMessage(frame.readText(), this)
                }
            }

            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }
}
