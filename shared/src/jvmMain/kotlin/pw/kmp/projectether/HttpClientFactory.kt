package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets

actual fun createHttpClient(): HttpClient = HttpClient() {
    install(WebSockets)
}