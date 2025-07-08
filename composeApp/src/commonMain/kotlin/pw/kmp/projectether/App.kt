package pw.kmp.projectether

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import pw.kmp.projectether.component.GameLauncher
import pw.kmp.projectether.ui.game.GameScreen


@Composable
@Preview
fun App() {
    val platform = getPlatform()
    MaterialTheme {
        val gameClient: GameClient = getKoin().get<GameClient>()
        val gameLauncher: GameLauncher = getKoin().get<GameLauncher>()
        val componentContext = DefaultComponentContext(LifecycleRegistry()/* TODO("implement active lifecycle") #1 */)
//        LoginScreen(componentContext, gameClient)

        GameScreen(platform, componentContext, gameLauncher)
    }
}
