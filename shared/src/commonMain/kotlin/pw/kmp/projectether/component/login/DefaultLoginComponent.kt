package pw.kmp.projectether.component.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin
import pw.kmp.projectether.GameClient
import pw.kmp.projectether.domain.usecase.login.LoginUseCase

data class LoginState(
    val username: String = "",
    val password: String = "",
    val error: String? = null,
    val success: Boolean = false,
)

interface LoginComponent {
    val onLoginSuccess: (sessionId: String) -> Unit
    val uiState: StateFlow<LoginState>
    fun onLoginClick()
    fun onUsernameChanged(username: String)
    fun onPasswordChanged(password: String)
}

class DefaultLoginComponent(
    override val onLoginSuccess: (sessionId: String) -> Unit,
    private val loginUseCase: LoginUseCase = getKoin().get(),
    private val gameClient: GameClient,
    componentContext: ComponentContext,
) : LoginComponent, ComponentContext by componentContext, InstanceKeeper.Instance {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableStateFlow(LoginState())
    override val uiState = _uiState.asStateFlow()

    override fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    override fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    override fun onLoginClick() = login()

    private fun login() {
        val (username, password) = uiState.value
        if (username.isNotBlank() && password.isNotBlank()) coroutineScope.launch {
            val result = loginUseCase(username, password)
            result.onSuccess {
                _uiState.update { loginState ->
                    loginState.copy(
                        username = "",
                        password = "",
                        success = true
                    )
                }
                onLoginSuccess(it)
            }
            result.onFailure { e ->
                _uiState.update { loginState ->
                    loginState.copy(
                        password = "",
                        error = e.message
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        println("LoginComponent destroyed")
        coroutineScope.cancel()
    }
}