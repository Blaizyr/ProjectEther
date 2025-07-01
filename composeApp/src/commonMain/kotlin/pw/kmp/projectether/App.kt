package pw.kmp.projectether

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import pw.kmp.projectether.ui.login.LoginScreen


@Composable
@Preview
fun App() {
    MaterialTheme {
        val gameClient: GameClient = getKoin().get<GameClient>()
        val componentContext = DefaultComponentContext(LifecycleRegistry()/* TODO("implement active lifecycle") #1 */)
        LoginScreen(componentContext, gameClient)
    }
}
