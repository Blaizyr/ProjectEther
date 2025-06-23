package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText

class GameClient(private val client: HttpClient) {
    suspend fun connect(username: String) {
        client.webSocket("ws://192.168.1.2:8080/ws?username=$username") {
            send(Frame.Text("HELLO FROM ${username.uppercase()}"))
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    println("Received: ${frame.readText()}")
                }
            }
        }
    }
}
