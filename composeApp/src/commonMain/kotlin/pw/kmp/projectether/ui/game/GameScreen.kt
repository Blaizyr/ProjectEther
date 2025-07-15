package pw.kmp.projectether.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import pw.kmp.projectether.godot.GodotClient
import pw.kmp.projectether.Content
import pw.kmp.projectether.component.game.GameComponent
import pw.kmp.projectether.ui.VirtualGamepad

@Composable
fun GameScreen(gameComponent: GameComponent) {
    val uiState by gameComponent.uiState.collectAsState()
    val godotClientState by gameComponent.godotClientState.collectAsState()

    GameScreen(
        godotClient = godotClientState ?: return,
    )
}

@Composable
private fun GameScreen(
    godotClient: GodotClient,
) {

    if (godotClient.isTouch) VirtualGamepad(
        onRespawn = {},
        onJump = {},
        onMove = {},
        content = { godotClient.Content() }
    )
    else godotClient.Content()
}
