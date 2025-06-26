package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pw.kmp.projectether.model.dto.message.ClientMessage.Login
import pw.kmp.projectether.model.dto.message.ServerMessage
import pw.kmp.projectether.util.extension.decideWithDiscriminator
import pw.kmp.projectether.util.extension.encodeWithDiscriminator

class GameClient(private val client: HttpClient) {

    suspend fun connect(username: String) {
        client.webSocket("ws://192.168.1.21:8080/ws") {
            send(
                Frame.Text(
                    Login(username).encodeWithDiscriminator()
                )
            )
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    println(frame.readText())
                    CoroutineScope(Dispatchers.Default)
                        .launch { startToListen() }
                }
            }
        }
    }

    private suspend fun startToListen() {
        client.webSocket("ws://192.168.1.21:8080/ws") {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val receivedText = frame.readText()
                    val message = receivedText.decideWithDiscriminator<ServerMessage>()
                    when (message) {
                        is ServerMessage.Welcome -> {
                            // TODO("Handle welcome message") #3
                        }

                        is ServerMessage.Error -> {
                            // TODO("Handle error message") #4
                        }
                    }
                    println("Received: ${frame.readText()}")
                }
            }
        }
    }
}
