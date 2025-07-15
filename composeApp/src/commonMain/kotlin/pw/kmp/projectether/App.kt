package pw.kmp.projectether

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import pw.kmp.projectether.component.game.GameComponent
import pw.kmp.projectether.component.login.LoginComponent
import pw.kmp.projectether.component.root.RootComponent
import pw.kmp.projectether.ui.game.GameScreen
import pw.kmp.projectether.ui.login.LoginScreen


@Composable
fun App(root: RootComponent) {
    val childStack by root.childStack.subscribeAsState()

    Children(stack = childStack) {
        MaterialTheme {
            when (val child = it.instance) {
                is LoginComponent -> LoginScreen(child)
                is GameComponent -> GameScreen(child)
            }
        }
    }
}
