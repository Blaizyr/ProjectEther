package pw.kmp.projectether.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import pw.kmp.projectether.Platform
import pw.kmp.projectether.component.GameComponent
import pw.kmp.projectether.component.GameLauncher
import pw.kmp.projectether.ui.VirtualGamepad

@Composable
fun GameScreen(
    platform: Platform,
    componentContext: ComponentContext,
    gameLauncher: GameLauncher,
) {
    val gameComponent = remember(componentContext, gameLauncher) {
        GameComponent(gameLauncher, componentContext)
    }

    if (platform.isTouch) VirtualGamepad(
        onRespawn = {},
        onJump = {},
        onMove = {},
    ) { GodotFrame() }
    else GodotFrame()
}

@Composable
fun GodotFrame() {
    //TODO("Godot fragment; godot-lib") #6
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Godot frame")
    }
}
