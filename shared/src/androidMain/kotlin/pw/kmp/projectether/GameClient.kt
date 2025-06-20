package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText

object GameClient {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    suspend fun connect(username: String) {
        client.webSocket("ws://localhost:8080/ws?username=$username") {
            send(Frame.Text("HELLO FROM ${username.uppercase()}"))
            println("Sent!")

            for (message in incoming) {
                if (message is Frame.Text) {
                    println("Received: ${message.readText()}")
                }
            }
        }
    }
}

