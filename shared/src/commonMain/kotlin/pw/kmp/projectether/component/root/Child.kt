package pw.kmp.projectether.component.root

import kotlinx.serialization.Serializable

@Serializable
sealed class Child {
    @Serializable
    data object Login : Child()

    @Serializable
    data class Game(val sessionId: String) : Child()
}
