package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.serialization.json.Json
import pw.kmp.projectether.model.dto.LoginMessage

class GameClient(private val client: HttpClient) {
    suspend fun connect(username: String) {
        client.webSocket("ws://192.168.1.21:8080/ws") {
            val loginMsg = LoginMessage(username = username)
            val json = Json.encodeToString(loginMsg)
            send(Frame.Text(json))
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    println("Received: ${frame.readText()}")
                }
            }
        }
    }
}
