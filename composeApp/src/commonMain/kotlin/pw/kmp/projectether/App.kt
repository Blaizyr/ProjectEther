package pw.kmp.projectether

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import pw.kmp.projectether.godot.GodotClientLauncher
import pw.kmp.projectether.ui.game.GameScreen


@Composable
@Preview
fun App() {
    MaterialTheme {
        val gameClient: GameClient = getKoin().get<GameClient>()
        val godotClientLauncher: GodotClientLauncher = getKoin().get<GodotClientLauncher>()
        val componentContext = DefaultComponentContext(LifecycleRegistry()/* TODO("implement active lifecycle") #1 */)
//        LoginScreen(componentContext, gameClient)

        GameScreen(componentContext, godotClientLauncher)
    }
}
