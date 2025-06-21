package pw.kmp.projectether

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json

actual fun createHttpClient(): HttpClient = HttpClient(OkHttp) {
    install(WebSockets)
    install(ContentNegotiation) {
        json()
    }
}