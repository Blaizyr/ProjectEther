package pw.kmp.projectether.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import org.jetbrains.compose.ui.tooling.preview.Preview
import pw.kmp.projectether.component.GameComponent
import pw.kmp.projectether.component.GameLauncher

@Composable
@Preview
fun GameScreen(
    componentContext: ComponentContext,
    gameLauncher: GameLauncher,
) {
    val gameComponent = remember(componentContext, gameLauncher) {
        GameComponent(gameLauncher, componentContext)
    }
    GodotFrame()
}

@Composable
@Preview
fun GodotFrame() {
    //TODO("Godot fragment; godot-lib")
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Godot frame")
    }
}
