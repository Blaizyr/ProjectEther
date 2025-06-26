package pw.kmp.projectether.model.dto.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ClientMessage {
    @Serializable
    @SerialName("login")
    data class Login(val username: String) : ClientMessage()

    @Serializable
    @SerialName("logout")
    data class Logout(val playerId: String) : ClientMessage()

    @Serializable
    @SerialName("move")
    data class Move(val direction: String) : ClientMessage()
}

@Serializable
sealed class ServerMessage {
    @Serializable
    @SerialName("error")
    data class Error(val reason: String) : ServerMessage()

    @Serializable
    @SerialName("welcome")
    data class Welcome(val message: String) : ServerMessage()
}
