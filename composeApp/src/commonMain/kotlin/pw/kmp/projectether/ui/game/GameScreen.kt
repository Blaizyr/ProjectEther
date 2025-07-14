package pw.kmp.projectether.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.arkivanov.decompose.ComponentContext
import pw.kmp.projectether.godot.GodotClient
import pw.kmp.projectether.godot.GodotClientLauncher
import pw.kmp.projectether.RenderContent
import pw.kmp.projectether.component.GameComponent
import pw.kmp.projectether.ui.VirtualGamepad

@Composable
fun GameScreen(
    componentContext: ComponentContext,
) {
    val gameComponent = remember(componentContext) {
        GameComponent(componentContext)
    }
    val uiState by gameComponent.uiState.collectAsState()

    GameScreen(
        godotClient = uiState.godotClient ?: return,
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
