package pw.kmp.projectether.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
}