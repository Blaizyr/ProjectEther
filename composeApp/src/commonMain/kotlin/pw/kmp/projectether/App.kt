package pw.kmp.projectether

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import pw.kmp.projectether.viewModel.LoginComponent


@Composable
@Preview
fun App() {
    MaterialTheme {
        val gameClient: GameClient = getKoin().get<GameClient>()
        val componentContext = DefaultComponentContext(LifecycleRegistry()/*TODO("1.implement active lifecycle"*/)
        LoginScreen(componentContext, gameClient)
    }
}

@Composable
@Preview
fun LoginScreen(
    componentContext: ComponentContext,
    gameClient: GameClient
) {
    val loginComponent = remember(componentContext, gameClient) {
        LoginComponent(gameClient, componentContext)
    }
    val uiState by loginComponent.uiState.collectAsState()

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Your nickname:",
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = uiState.username,
            onValueChange = { loginComponent.onUsernameChanged(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.padding(8.dp))
        TextButton(
            onClick = { loginComponent.onLoginClicked() }
        ) {
            Text(text = "Connect")
        }
    }
}