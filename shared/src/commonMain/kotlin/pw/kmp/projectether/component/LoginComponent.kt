package pw.kmp.projectether.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import pw.kmp.projectether.GameClient

data class LoginState(
    val username: String = "",
    val error: String? = null,
    val success: Boolean = false,
)

class LoginComponent(
    private val gameClient: GameClient,
    componentContext: ComponentContext
) : ComponentContext by componentContext, InstanceKeeper.Instance {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onLoginClicked() {
        onLogin(uiState.value.username)
    }

    private fun onLogin(username: String) {
        coroutineScope.launch {
            try {
                gameClient.connect(username)
                _uiState.update { it.copy(username = username, success = true) }
            } catch (e: IOException) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    override fun onDestroy() {
        println("LoginComponent destroyed")
        coroutineScope.cancel()
    }
}