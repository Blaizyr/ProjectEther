package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import pw.kmp.projectether.model.dto.message.ClientMessage
import pw.kmp.projectether.model.dto.message.ClientMessage.Login
import pw.kmp.projectether.model.dto.message.ServerMessage
import pw.kmp.projectether.model.dto.message.ServerMessage.Error
import pw.kmp.projectether.model.dto.message.ServerMessage.Info
import pw.kmp.projectether.util.extension.decodeWithDiscriminator
import pw.kmp.projectether.util.extension.encodeWithDiscriminator

class GameClient(private val client: HttpClient) {

    suspend fun connect(username: String) {
        client.webSocket("ws://192.168.1.21:8080/ws") {
            send(
                Frame.Text(
                    Login(username).encodeWithDiscriminator<ClientMessage>()
                )
            )
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val receivedText = frame.readText()
                    val message = receivedText.decodeWithDiscriminator<ServerMessage>()
                    handleMessage(message)
                }
            }
        }
    }

    private fun handleMessage(message: ServerMessage) {
        when (message) {
            is ServerMessage.LoggedIn -> Unit
            is Info -> {
                println("Welcome: ${message.message}")
                // TODO("Handle welcome message") #3
            }

            is Error -> {
                println("Error: ${message.reason}")
                // TODO("Handle error message") #4
            }
        }
    }
}
